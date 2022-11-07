package com.aws.filterMicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/filter")
public class FilterController {

    @Autowired
    private FilterService filterService;

    // link: http://localhost:8083/api/filter/setFilename
    @PostMapping("/setFilename")
    public void setFileName(String fileName) {
        String fn = fileName;
        // replace './Datasets/' with your own path to Datasets folder
        filterService.setFilePath("./Datasets/"+ fn);
    }

    // link: http://localhost:8083/api/filter/removeAttribute
    @PostMapping("/removeAttribute")
    public List<String> removeAttribute(String attribute) {
        return filterService.removeAttribute(attribute);
    }

    // link: http://localhost:8083/api/filter/replaceMissing-constant
    @PostMapping("/replaceMissing-constant")
    public List<String> replaceMissingWithConstant(String constant, String type) {
        return filterService.replaceMissingWithConstant(constant, type);
    }

    // link: http://localhost:8083/api/filter/replaceMissing-mean
    @PostMapping("/replaceMissing-mean")
    public List<String> replaceMissingWithMean() {
        return filterService.replaceMissingValueMean();
    }

    // link: http://localhost:8083/api/filter/getAttributes
    @GetMapping("/getAttributes")
    public List<String> getAttributes(String type) {
        return filterService.getAttributes(type);
    }

    // link: http://localhost:8083/api/filter/getAttributesWithMissingValues
    @GetMapping("/getAttributesWithMissingValues")
    public List<String> getAttributesWithMissingValues(String type) {
        return filterService.getAttributesWithMissingValues(type);
    }

    // link: http://localhost:8083/api/filter/getAttributeValues
    @GetMapping("/getAttributeValues")
    public List<String> getAttributeValues(String attribute) {
        return filterService.getAttributeValues(attribute);
    }

}
