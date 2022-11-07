package com.aws.clusterMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ClusterMicroserviceApplication {

	public static void main(String[] args) {

//		ClusterService cs = new ClusterService();
//		try {
//			cs.EMClusterer("../../Datasets/segment-challenge.arff", "80");
//		} catch(Exception e) {
//			System.out.println(e);
//		}

		SpringApplication.run(ClusterMicroserviceApplication.class, args);
	}

}
