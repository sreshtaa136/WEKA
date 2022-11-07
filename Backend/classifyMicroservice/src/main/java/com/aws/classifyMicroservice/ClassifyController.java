package com.aws.classifyMicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api/classify")
public class ClassifyController {

    @Autowired
    private ClassifyService classifyService;
    private String fileName;


    // link: http://localhost:8082/api/classify/setFilename
    @PostMapping("/setFilename")
    public void setFileName(String fileName) {
        this.fileName = fileName;
        // replace './Datasets/' with your own path to Datasets folder
        classifyService.setFilePath("./Datasets/" + fileName);
    }


    // link: http://localhost:8082/api/classify/getDataSummary
    @GetMapping("/getDataSummary")
    public String getDataSummary(String algorithm, String percentage) {
        String summary = "";
        try {
            switch (algorithm) {
                case "NaiveBayes":
                    summary = classifyService.naiveBayes(percentage);
                    break;
                case "ZeroR":
                    summary = classifyService.zeroR(percentage);
                    break;
                case "Logistic":
                    summary = classifyService.logistic(percentage);
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return summary;
    }

    // link: http://localhost:8082/api/classify/getConfusionMatrix
    @GetMapping("/getConfusionMatrix")
    public List<List<String>> getConfusionMatrix() {
        return classifyService.getMatrix();
    }

    // link: http://localhost:8082/api/classify/getDetails
    @GetMapping("/getDetails")
    public List<List<String>> getDetails() {
        return classifyService.getDetails();
    }

    public void initialiseClassifyServiceAttribute(){ // for the test purpose
        // Actual testing is not here, please go to final-test branch, not in this react branch
        this.classifyService = new ClassifyService();
    }

    public ClassifyService getClassifyService(){
        // Actual testing is not here, please go to final-test branch, not in this react branch
        return this.classifyService;
    }

    public String getFileName() { // for the test purpose
        // Actual testing is not here, please go to final-test branch, not in this react branch
        return this.fileName;
    }


}
