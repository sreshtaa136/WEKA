package com.aws.loadDataMicroservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api/load-data")
public class LoadDataController {

    @Autowired
    private LoadDataService loadDataService;
    private String fileName;


    // link: http://localhost:8081/api/load-data/getFilename
    @GetMapping("/getFilename")
    public String getFilename() {
        return this.fileName;
    }

    // returns data summary
    // link: http://localhost:8081/api/load-data/getDataSummary
    @GetMapping("/getDataSummary")
    public List<String> getDataSummary(String fileName){
        String name = fileName.toString();
        return loadDataService.createSummaryString(name);
    }


    // Method to upload file
    // link: http://localhost:8081/api/load-data/uploadFile
    @PostMapping("/uploadFile")
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        boolean uploadCheck = loadDataService.uploadFile(file);
        if(uploadCheck) {
            System.out.println("SUCCESSFULLY SAVED THE DATASET: " + file.getOriginalFilename());
            setFileName(file.getOriginalFilename());
        } else {
            System.out.println("ATTEMPT UNSUCCESSFUL");
        }
    }

    private void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
