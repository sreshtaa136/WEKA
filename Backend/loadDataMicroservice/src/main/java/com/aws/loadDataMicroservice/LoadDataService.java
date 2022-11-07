package com.aws.loadDataMicroservice;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LoadDataService {

    private String fileName = "";

    // method that saves the file uploaded by user
    public boolean uploadFile(MultipartFile file) {
        // replace folderPath with the path to your Datasets folder
        String folderPath = "/Users/sreshtaa/Desktop/P000177CSITCP/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/";
        // read and write the file to the local folder
        Arrays.asList(file).stream();
        byte[] bytes;
        try {
            bytes = file.getBytes();
            String fileName = file.getOriginalFilename();
            setFileName(fileName);
            // writing the file in the given path
            Files.write(Paths.get(folderPath + fileName), bytes);
            // checking if it is a csv file and handling it
            String extension = fileName.substring(fileName.length() - 4);
            if(extension.equals(".csv")) {
                boolean update = handleCsv(fileName);
                if(!update) {
                    return false;
                }
            }
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }

    // method to convert csv file uploaded by user to arff
    public boolean handleCsv(String fileName) {
        // CONVERTING CSV TO ARFF
        CSVLoader loader = new CSVLoader();
        // replacing '.csv' in the filename with '.arff'
        String newName = fileName.substring(0, fileName.length() - 4) + ".arff";
        try{
            // replace "./Datasets/" with the path to your Datasets folder
            File csvFile = new File("./Datasets/" + fileName);
            loader.setSource(csvFile);
            Instances data = loader.getDataSet();
            ArffSaver saver = new ArffSaver();
            saver.setInstances(data);
            // replace "./Datasets/" with the path to your Datasets folder
            saver.setFile(new File("./Datasets/" + newName));
            saver.writeBatch();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    // create a summary of the uploaded dataset file
    public List<String> createSummaryString(String fileName) {
        // replacing '.csv' in the filename with '.arff'
        String extension = fileName.substring(fileName.length() - 4);
        if(extension.equals(".csv")) {
            fileName = fileName.substring(0, fileName.length() - 4) + ".arff";
        }

        try{
            // replace "./Datasets/" with the path to your Datasets folder
            Instances dataset = new Instances(new BufferedReader(new FileReader("./Datasets/" + fileName)));
            List<String> ls = new ArrayList<String>(Arrays.asList(dataset.toSummaryString().split("\n")));
            List<String> testing = new ArrayList<String>();
            for(String s:ls) {
                // replace parts of the string which have the pattern ' / '
                // with '/' to make summary displayable in html
                String data = s.replaceAll("(\\s*)/(\\s*)", "\\/");
                // replace one or more spaces with a single space
                data = data.replaceAll("\\s+", " ");
                testing.add(data);
            }
            return testing;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("SUMMARY PROCESS UNSUCCESSFUL");
        }
        return null;
    }

    private void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
