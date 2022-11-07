package com.aws.loadDataMicroservice;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class LoadDataMicroserviceApplicationTests {

//	@Resource
//	LoadDataController loadDataController;
//
//	@Test
//	void GetDataSummary_IfDataSummaryInvoked() {
//		List<String> summary = loadDataController.getDataSummary("heart-clean.arff");
//		System.out.println("dataset："+summary);
//
//		assert summary != null;
//	}
//
//	@Test
//	void GetNullDataSummary_IfDataSummaryInvokedWithCSVFile () {
//		List<String> summary = loadDataController.getDataSummary("testCsv.csv");
//		System.out.println("dataset："+summary);
//
//		assert summary == null;
//	}



//	@Test
//	public void testHandleCsv() {
//		LoadDataService loadDataService = new LoadDataService();
//		String csvPath = "testCsv.csv";
//		boolean b = loadDataService.handleCsv(csvPath);
//		assertEquals(true, b);
//	}
//
//	@Test
//	public void testHandleCsvFalse() {
//		LoadDataService loadDataService = new LoadDataService();
//		String csvPath = "heart-clean.arff";
//		boolean b = loadDataService.handleCsv(csvPath);
//		assertEquals(false, b);
//	}



//	@Test
//	public void testAUploadFile_IfUploadACSVFile() {
//		String oldPathName = "/Users/zehuliu/Desktop/testCsv.csv";
//		File file = new File(oldPathName);
//		String fileName = file.getName();
//		System.out.println(file.getName());
//		try {
//			MultipartFile mFile = new MockMultipartFile("file", file.getName(), null, new FileInputStream(file));
//			loadDataController.uploadFile(mFile);
//
//			String newFilePath = null;
//			if(fileName.endsWith(".csv")){
//				fileName = fileName.replace(".csv","") + ".arff";
//			}
//			newFilePath = "../../Datasets/" + fileName;
//			System.out.println("newFilePath:" + newFilePath);
//			File targetFile = new File(newFilePath);
//			assert targetFile.exists();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Test
//	public void testAUploadFile_IfUploadArffFile() {
//		String oldPathName = "/Users/zehuliu/Desktop/testArff.arff";
//		File file = new File(oldPathName);
//		String fileName = file.getName();
//		System.out.println(file.getName());
//		try {
//			MultipartFile mFile = new MockMultipartFile("file", file.getName(), null, new FileInputStream(file));
//			loadDataController.uploadFile(mFile);
//
//			String newFilePath = null;
//			if(fileName.endsWith(".csv")){
//				fileName = fileName.replace(".csv","") + ".arff";
//			}
//			newFilePath = "../../Datasets/" + fileName;
//			System.out.println("newFilePath:" + newFilePath);
//			File targetFile = new File(newFilePath);
//			assert targetFile.exists();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}
