package com.aws.classifyMicroservice;

import org.springframework.stereotype.Service;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.Logistic;
import weka.classifiers.rules.ZeroR;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils;

import java.util.*;

@Service
public class ClassifyService {

    private List<List<String>> matrix;
    private List<List<String>> details;
    private String filePath;


    public String naiveBayes(String trainPercentage) throws Exception {

        if (tpInputValid(trainPercentage)) { // if input is valid
            double sanitizedInputTP = Double.parseDouble(trainPercentage);
            List<Instances> trainAndTest = splitDataset(this.filePath,
                    sanitizedInputTP);
            Instances train = trainAndTest.get(0);
            Instances test = trainAndTest.get(1);
            Attribute target = test.attribute(train.numAttributes()-1); // last attribute in the dataset

            //build model
            NaiveBayes naiveBayes = new NaiveBayes();
            naiveBayes.buildClassifier(train);
            // evaluate model
            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(naiveBayes, test);

            generateDetailsTable(eval);
            generateConfusionMatrix(eval, target);
            String result = generateResultString(naiveBayes, null, null, eval, trainPercentage);
            return result;
        } else { // if input is invalid
            return "Input should not be empty and percentage must be a number" +
                    " between 0 and 100";
        }
    }

    public String zeroR(String trainPercentage) throws Exception {
        if (tpInputValid(trainPercentage)) { // if input is valid
            double sanitizedInputTP = Double.parseDouble(trainPercentage);
            List<Instances> trainAndTest = splitDataset(this.filePath,
                    sanitizedInputTP);
            Instances train = trainAndTest.get(0);
            Instances test = trainAndTest.get(1);
            Attribute target = test.attribute(train.numAttributes()-1);

            //build model
            ZeroR zeroR = new ZeroR();
            zeroR.buildClassifier(train);

            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(zeroR, test);

            generateDetailsTable(eval);
            generateConfusionMatrix(eval, target);
            String result = generateResultString(null, zeroR, null, eval, trainPercentage);

            return result;
        } else { // if input is invalid
            return "Input should not be empty and percentage must be a number" +
                    " between 0 and 100";
        }
    }

    public String logistic(String trainPercentage) throws Exception {
        if (tpInputValid(trainPercentage)) { // if input is valid
            double sanitizedInputTP = Double.parseDouble(trainPercentage);
            List<Instances> trainAndTest = splitDataset(this.filePath,
                    sanitizedInputTP);
            Instances train = trainAndTest.get(0);
            Instances test = trainAndTest.get(1);
            Attribute target = test.attribute(train.numAttributes()-1);

            //build model
            Logistic logistic = new Logistic();
            logistic.setOptions(Utils.splitOptions("-R 1.0E-8 -M -1 " +
                    "-num-decimal-places 4"));
            logistic.buildClassifier(train);

            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(logistic, test);

            generateDetailsTable(eval);
            generateConfusionMatrix(eval, target);
            String result = generateResultString(null, null, logistic, eval, trainPercentage);
            return result;
        } else { // if input is invalid
            return "Input should not be empty and percentage must be a number" +
                    " between 0 and 100";
        }
    }

    public List<List<String>> getMatrix() {
        return this.matrix;
    }

    public List<List<String>> getDetails() {
        return this.details;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    // Actual testing is not here, please go to final-test branch, not in this react branch
    public void setMatrix(List<List<String>> matrix) { // Actual testing is not here, please go to final-test branch, not in this react branch
        this.matrix = matrix;
    }

    // Actual testing is not here, please go to final-test branch, not in this react branch
    public void setDetails(List<List<String>> details) { // Actual testing is not here, please go to final-test branch, not in this react branch
        this.details = details;
    }

    // HELPER METHODS ---------------------------------------------------------------------------------------------------------

    private String generateResultString(NaiveBayes nb, ZeroR zr, Logistic l, Evaluation eval, String trainPercentage) throws Exception {
        // need this method because the result string returned from Weka API is not going to be well formatted if pass directly to the front end.
        String scheme = "";
        if (nb != null) {
            scheme = nb.getClass().getName();
        } else if (zr != null) {
            scheme = zr.getClass().getName();
        } else if (l != null) {
            scheme = l.getClass().getName();
        }

        String information = "<h2>Information: </h2><br/>" +
                "<p><b>Scheme:</b> " + scheme + "</p>" +
                "<p><b>Predicted attribute:</b> " + getLastAttributeNameType(this.filePath) + "</p>" +
                "<p><b>Mode:</b> " + "Split " + trainPercentage + "% for train, " +
                (100-Integer.parseInt(trainPercentage)) + "% for test</p>";

        String summary = eval.toSummaryString("<br/><h2>Summary </h2><br/>", false) ;
        List<String> s = new ArrayList<>(Arrays.asList(summary.split("\n")));
        for(int i=1; i<s.size(); i++) {
            String data = s.get(i);
            data = "<p><b>" + data + "</b></p>";
            s.set(i, data);
        }
        summary = String.join("", s);
        String result = information + summary;
        return result;
    }

    private void generateDetailsTable(Evaluation eval) throws Exception { // sanitize Weka API result string
        this.details = new ArrayList<>();
        String details = eval.toClassDetailsString();
        List<String> rows = new ArrayList<>(Arrays.asList(details.split("\n")));
        for(int i=2; i<rows.size(); i++) {
            String row = rows.get(i);
            row = row.replaceAll("(\\s\\s)+", ",");
            List<String> row_data = new ArrayList<>(Arrays.asList(row.split(",")));
//            System.out.println(row_data);
            this.details.add(row_data);
        }
    }

    private void generateConfusionMatrix(Evaluation eval, Attribute target) {// sanitize Weka API result string
        this.matrix = new ArrayList<>();
        double[][] double_matrix = eval.confusionMatrix();
        List<String> labels = new ArrayList<>();
        char[] alphabets = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        int size = double_matrix[0].length;
        for(int i=0; i < size; i++) {
            String class_label = String.valueOf(alphabets[i]);
            labels.add(class_label);
        }
        labels.add("<-- Classified as");
        this.matrix.add(labels);

        Enumeration<Object> vals = target.enumerateValues();
        List<Object> values = Collections.list(vals);
        for(int i=0; i<double_matrix.length; i++) {
            List<String> row = new ArrayList<>();
            for(double j: double_matrix[i]) {
                row.add(Double.toString(j));
            }
            String desc = alphabets[i] + " = " + values.get(i).toString();
            row.add(desc);
            this.matrix.add(row);
        }
    }

    private String getLastAttributeNameType(String splitFilePath) throws Exception {
        ConverterUtils.DataSource source =
                new ConverterUtils.DataSource(splitFilePath);

        Instances originalDataset = source.getDataSet();
        Attribute predictedAttribute =
                originalDataset.attribute(originalDataset.numAttributes() -
                        1);
        return predictedAttribute.name() + " (" + predictedAttribute.typeToString(predictedAttribute) + ")";
    }

    private boolean tpInputValid(String inputTrainPercentage) {
        if (inputTrainPercentage == null || inputTrainPercentage.isEmpty()) {
            return false;
        }
        try {
            Double trainPercentage = Double.parseDouble(inputTrainPercentage);
            if (trainPercentage <= 0 || trainPercentage >= 100) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private List<Instances> splitDataset(String splitFilePath,
                                         Double trainPercentage) throws Exception {

        double sanitizedTrainPercentage = trainPercentage / 100.0;

        List<Instances> result = new ArrayList<>();

        ConverterUtils.DataSource source =
                new ConverterUtils.DataSource(splitFilePath);

        Instances originalDataset = source.getDataSet();
        // assume the last attribute is the target attribute (predicted
        // attribute) in the dataset.
        // User can not specify the target attribute
        originalDataset.setClassIndex(originalDataset.numAttributes() - 1);

        // must use the seed 1. Otherwise, the split will be different every
        // time
        originalDataset.randomize(new java.util.Random(1));

        int trainSize =
                (int) Math.round(originalDataset.numInstances() * sanitizedTrainPercentage);
        int testSize = originalDataset.numInstances() - trainSize;
        Instances train = new Instances(originalDataset, 0, trainSize);
        Instances test = new Instances(originalDataset, trainSize, testSize);

        result.add(train);
        result.add(test);

        return result;
    }
}
