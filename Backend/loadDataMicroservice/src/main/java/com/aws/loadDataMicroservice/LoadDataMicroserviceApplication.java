package com.aws.loadDataMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import weka.core.Instances;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class LoadDataMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoadDataMicroserviceApplication.class, args);
	}

}
