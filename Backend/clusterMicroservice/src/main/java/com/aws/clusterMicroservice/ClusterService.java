package com.aws.clusterMicroservice;

import org.springframework.stereotype.Service;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.EM;
import weka.clusterers.SimpleKMeans;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class ClusterService {

    public String SimpleKmeansClusterer(String splitFilePath, String trainPercentage) throws Exception {

        if (tpInputValid(trainPercentage)) { // if percentage input is valid

            // split dataset into train and test
            double sanitizedInputTP = Double.parseDouble(trainPercentage);
            List<Instances> trainAndTest = splitDataset(splitFilePath,
                    sanitizedInputTP);
            Instances train = trainAndTest.get(0);
            Instances test = trainAndTest.get(1);

            // ignore class attribute in train set
            train.setClassIndex(train.numAttributes() - 1);
            weka.filters.unsupervised.attribute.Remove filter = new weka.filters.unsupervised.attribute.Remove();
            filter.setAttributeIndices("" + (train.classIndex() + 1));
            filter.setInputFormat(train);
            Instances dataClusterer = Filter.useFilter(train, filter);

            //new instance of clusterer
            SimpleKMeans model = new SimpleKMeans();
            // set number of clusters
            model.setNumClusters(4);
            //set distance function
            model.setDistanceFunction(new weka.core.EuclideanDistance());
            // set seed
            model.setSeed(10);

            // build the clusterer
            model.buildClusterer(dataClusterer);
            //Evaluation
            ClusterEvaluation eval = new ClusterEvaluation();
            eval.setClusterer(model);
            eval.evaluateClusterer(test); // evaluate on test dataset

            String information = "<h2>Information: </h2><br/>" +
                    "<p><b>Scheme:</b> " + model.getClass().getName() + "</p>" +
                    "<p><b>Instances in test set:</b> " + String.valueOf(test.numInstances()) + "</p>" +
                    "<p><b>Attributes:</b> " + String.valueOf(test.numAttributes()) +
                    "<br/><br/>" + getAttributesList(dataClusterer) + "</p>" +
                    "<p><b>Ignored attribute:</b> " + train.attribute(train.classIndex()).name() + "</p>" +
                    "<p><b>Mode:</b> " + "Split " + trainPercentage + "% for train, " +
                    (100-Integer.parseInt(trainPercentage)) + "% for test <br/>" +
                    "<br/>Classes to clusters evaluation on training data </p>";

            String summary = generateSummaryString(eval);
//            System.out.println(information);

            return information + summary;

        } else { // if input is invalid
            return "Input should not be empty and percentage must be a number" +
                    " between 0 and 100";
        }
    }

    public String EMClusterer(String splitFilePath, String trainPercentage) throws Exception {

        if (tpInputValid(trainPercentage)) { // if percentage input is valid

            // split dataset into train and test
            double sanitizedInputTP = Double.parseDouble(trainPercentage);
            List<Instances> trainAndTest = splitDataset(splitFilePath,
                    sanitizedInputTP);
            Instances train = trainAndTest.get(0);
            Instances test = trainAndTest.get(1);

            // ignore class attribute in train set
            train.setClassIndex(train.numAttributes() - 1);
            weka.filters.unsupervised.attribute.Remove filter = new weka.filters.unsupervised.attribute.Remove();
            filter.setAttributeIndices("" + (train.classIndex() + 1));
            filter.setInputFormat(train);
            Instances dataClusterer = Filter.useFilter(train, filter);

            //new instance of clusterer
            EM model = new EM();
            // set number of clusters
            model.setNumClusters(4);
            // set seed
            model.setSeed(10);

            // build the clusterer
            model.buildClusterer(dataClusterer);
            //Evaluation
            ClusterEvaluation eval = new ClusterEvaluation();
            eval.setClusterer(model);
            eval.evaluateClusterer(test); // evaluate on test dataset

            String information = "<h2>Information: </h2><br/>" +
                    "<p><b>Scheme:</b> " + model.getClass().getName() + "</p>" +
                    "<p><b>Instances in test set:</b> " + String.valueOf(test.numInstances()) + "</p>" +
                    "<p><b>Attributes:</b> " + String.valueOf(test.numAttributes()) +
                    "<br/><br/>" + getAttributesList(dataClusterer) + "</p>" +
                    "<p><b>Ignored attribute:</b> " + train.attribute(train.classIndex()).name() + "</p>" +
                    "<p><b>Mode:</b> " + "Split " + trainPercentage + "% for train, " +
                    (100-Integer.parseInt(trainPercentage)) + "% for test <br/>" +
                    "<br/>Classes to clusters evaluation on training data </p>";

            String summary = generateSummaryString(eval);
//            System.out.println(eval.clusterResultsToString());

            return information + summary;
        } else { // if input is invalid
            return "Input should not be empty and percentage must be a number" +
                    " between 0 and 100";
        }
    }


    // HELPER METHODS ---------------------------------------------------------------------------------------------------------

    private String generateSummaryString(ClusterEvaluation eval) {
        String summary_raw = eval.clusterResultsToString();
        List<String> rows = new ArrayList<>(Arrays.asList(summary_raw.split("\n")));

        String summary_final = "";
        for(int i=0; i<rows.size(); i++) {
            String row = rows.get(i);
            if(!row.equals("")) {
                String r;
                if(row.contains("kMeans") || row.contains("EM") ||
                        row.contains("Final cluster centroids") || row.contains("Clustered Instances")
                ) {
                    summary_final += "<br/><h2>" + rows.get(i) + "</h2><br/>";
                } else if (row.contains("Number of iterations performed")) {
                    summary_final += "<p>" + rows.get(i) + "</p><br/>";
                } else if (row.contains("Cluster 0") || row.contains("<-- assigned to cluster") ||
                        row.contains("Incorrectly clustered") || row.contains("Class attribute") ||
                        row.contains("Log likelihood")
                ) {
                    summary_final += "<br/><p>" + rows.get(i) + "</p>";
                } else {
                    summary_final += "<p>" + rows.get(i) + "</p>";
                }
            }
        }

//        for(String row: rows) {
//            System.out.println(row);
//        }

        return summary_final;
    }

    private String getAttributesList(Instances dataClusterer) {
        String attributeString = "";
        for(int i=0; i<dataClusterer.numAttributes(); i++) {
            String name = dataClusterer.attribute(i).name();
            attributeString += name + "<br/>";
        }
        return attributeString;
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

    private String getLastAttributeNameType(String splitFilePath) throws Exception {
        ConverterUtils.DataSource source =
                new ConverterUtils.DataSource(splitFilePath);

        Instances originalDataset = source.getDataSet();
        Attribute predictedAttribute =
                originalDataset.attribute(originalDataset.numAttributes() -
                        1);
        return predictedAttribute.name() + " (" + predictedAttribute.typeToString(predictedAttribute) + ")";
    }
}
