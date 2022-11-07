package com.aws.clusterMicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/cluster")
public class ClusterController {
    @Autowired
    private ClusterService clusterService;
    private String fileName;


    // link: http://localhost:8084/api/cluster/setFilename
    @PostMapping("/setFilename")
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    // link: http://localhost:8084/api/cluster/getSummary
    @GetMapping("/getSummary")
    public String getSummary(String algorithm, String percentage) {

        String summary = "";
        try {
            switch (algorithm) {
                // replace './Datasets/' with your own path to Datasets folder
                case "SimpleKmeans":
                    summary = clusterService.SimpleKmeansClusterer("./Datasets/"+ this.fileName, percentage);
                    break;
                case "EM":
                    summary = clusterService.EMClusterer("./Datasets/"+ this.fileName, percentage);
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return summary;
    }

    public void initialiseClusterServiceAttribute(){ // for the test purpose
        this.clusterService = new ClusterService();
    }

    public String getFileName() { // for the test purpose
        return this.fileName;
    }

    public ClusterService getClusterService(){
        return this.clusterService;
    }

}
