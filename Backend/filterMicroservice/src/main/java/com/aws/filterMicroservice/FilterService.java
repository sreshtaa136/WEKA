package com.aws.filterMicroservice;

import org.springframework.stereotype.Service;
import weka.core.Attribute;
import weka.core.AttributeStats;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;
import weka.filters.unsupervised.attribute.ReplaceMissingWithUserConstant;
import java.io.File;
import java.util.*;


@Service
public class FilterService {

    private String filePath;

    // Remove selected attribute based on user input
    public List<String> removeAttribute (String input) {
        Instances newData;
        try {
            Instances dataset = loadDataSet(this.filePath);
            //delete an Attribute
            Attribute target = dataset.attribute(input);
            String target_index = String.valueOf(target.index() + 1);
            //use filter to remove a certain attribute
            //set up options to remove input attribute
            String[] opts = new String[]{"-R", target_index};
            //create a Remove object (this is the filter class)
            Remove remove = new Remove();
            //set the filter options
            remove.setOptions(opts);
            //pass the dataset to the filter
            remove.setInputFormat(dataset);
            //apply the filter
            newData = Filter.useFilter(dataset, remove);

            String newPath = getNewFilePath(this.filePath, "RemoveAttr");
            boolean saved = saveData(newData, newPath);

            if(saved) {
                return createSummaryString(newData);
            } else {
                return null;
            }

        } catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }


    // Replace missing value with constant
    public List<String> replaceMissingWithConstant (String input, String type) {

        try {
            //create NonSparseToSparse object to save in sparse ARFF format
            ReplaceMissingWithUserConstant sp = new ReplaceMissingWithUserConstant();
            //specify the dataset
            Instances dataset = loadDataSet(this.filePath);

            if(type.equals("numeric")) {
                List<String> attributes = getAttributes("numeric");
                String attribute_string = attributes.toString();
                attribute_string = attribute_string.substring(1,attribute_string.length()-1);
                sp.setAttributes(attribute_string);
                sp.setNumericReplacementValue(input);
            } else {
                List<String> attributes = getAttributes("nominal");
                String attribute_string = attributes.toString();
                attribute_string = attribute_string.substring(1,attribute_string.length()-1);
                sp.setAttributes(attribute_string);
                sp.setNominalStringReplacementValue(input);
            }

            sp.setInputFormat(dataset);
            //apply
            Instances newData = Filter.useFilter(dataset, sp);
            String newPath = getNewFilePath(this.filePath, "ReplaceConst_" + type);
            saveData(newData, newPath);

            List<String> summary = createSummaryString(newData);
            return summary;
        } catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }


    //Replace missing value with mean
    public List<String> replaceMissingValueMean () {

        try {
            //create NonSparseToSparse object to save in sparse ARFF format
            ReplaceMissingValues sp = new ReplaceMissingValues();
            //specify the dataset
            Instances dataset = loadDataSet(this.filePath);
            sp.setInputFormat(dataset);
            //apply
            sp.setIgnoreClass(true);
            Instances newData = Filter.useFilter(dataset, sp);
            String newPath = getNewFilePath(this.filePath, "ReplaceMean");
            saveData(newData, newPath);

            List<String> summary = createSummaryString(newData);
            return summary;
        } catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }


    public List<String> getAttributes(String type) {
        Instances dataset = loadDataSet(this.filePath);
        List<String> attributes = new ArrayList<>();
        for(int i=0; i<dataset.numAttributes(); i++) {
            String name = dataset.attribute(i).name();
            Attribute attribute = dataset.attribute(i);
            String attribute_type = attribute.typeToString(attribute);
//            System.out.println(attribute.typeToString(attribute));
            if(!type.equals("")) {
                if(attribute_type.equals(type)) {
                    attributes.add(name);
                }
            } else {
                attributes.add(name);
            }
        }
        return attributes;
    }

    public List<String> getAttributesWithMissingValues(String type) {
        Instances dataset = loadDataSet(this.filePath);
        List<String> attributes = new ArrayList<>();
        for(int i=0; i<dataset.numAttributes(); i++) {
            String name = dataset.attribute(i).name();
            Attribute attribute = dataset.attribute(i);
            String attribute_type = attribute.typeToString(attribute);
            if(attribute_type.equals(type)) {
                AttributeStats stats = dataset.attributeStats(i);
                if(stats.missingCount > 0) {
                    attributes.add(name);
//                    System.out.println(name);
                }
            }
        }
        return attributes;
    }

    public List<String> getAttributeValues(String attributeName) {
        Instances dataset = loadDataSet(this.filePath);
        List<String> attributeVals = new ArrayList<>();
        for(int i=0; i<dataset.numAttributes(); i++) {
            Attribute attribute = dataset.attribute(i);
            if(attribute.name().equals(attributeName)) {
                Enumeration<Object> vals = attribute.enumerateValues();
                List<Object> values = Collections.list(vals);
                for(int j=0; j<values.size(); j++) {
                    attributeVals.add(values.get(j).toString());
//                    System.out.println(values.get(j).toString());
                }
            }
        }
        return attributeVals;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    // HELPER METHODS ---------------------------------------------------------------------------------------------------------

    private String getNewFilePath(String oldFilePath, String method) {
        String old_path = oldFilePath.substring(0,oldFilePath.length()-5);
        String newPath = old_path + method + ".arff";
        int count = 0;
        while(new File(newPath).isFile()) {
            count++;
            newPath = old_path + method + String.valueOf(count) + ".arff";
        }
        return newPath;
    }

    private List<String> createSummaryString(Instances dataset) {
        List<String> final_summary = new ArrayList<String>();
        try{
            List<String> ls = new ArrayList<String>(Arrays.asList(dataset.toSummaryString().split("\n")));
            for(String s:ls) {
                String data = s.replaceAll("(\\s*)/(\\s*)", "\\/");
                data = data.replaceAll("\\s+", " ");
                final_summary.add(data);
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("SUMMARY PROCESS UNSUCCESSFUL");
            return null;
        }
        return final_summary;
    }

    private Instances loadDataSet(String filePath) {
        try {
            //load dataset
            ConverterUtils.DataSource source = new ConverterUtils.DataSource(filePath);
            Instances dataset = source.getDataSet();
            return dataset;
        } catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private boolean saveData(Instances instances, String filePath) {
        try {
            ArffSaver saver = new ArffSaver();
            saver.setInstances(instances);
            saver.setFile(new File(filePath));
            saver.writeBatch();
            return true;
        } catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }

}
