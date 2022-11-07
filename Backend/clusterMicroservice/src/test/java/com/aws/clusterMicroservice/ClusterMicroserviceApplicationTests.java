package com.aws.clusterMicroservice;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class ClusterMicroserviceApplicationTests {
	private static ClusterController clusterController;
	private static ClusterService clusterService;

	@BeforeAll
	public static void initialise() { // initialise the controller and service
		clusterController = new ClusterController(); // for test the
		// controller class's methods
		clusterController.initialiseClusterServiceAttribute();
		// initialise the service attribute in the controller class
		clusterService = new ClusterService();// for directly test the
//		// service class
	}

	@AfterEach
	public void reset() {
		// Reset file name
		clusterController.setFileName("");

	}

	// test the controller class's methods
	@Test
	void getFileName_UploadedFileName() {
		String fileName = "segment-challenge.arff";
		clusterController.setFileName(fileName);
		assertEquals(fileName, clusterController.getFileName());
	}

	@Test
	void getFileName_null_IfSetFileNameNotInvoked() {
		assert clusterController.getFileName() == "";
	}


	// SimpleKmeans

	@Test
	void getDataSummary_SimpleKmeansSummary_IfSimpleKmeansInvoked() throws Exception{
		String fileName = "segment-challenge.arff";
		clusterController.setFileName(fileName);

		clusterController.getClusterService().SimpleKmeansClusterer("../../Datasets/segment-challenge.arff", "80");
		assertThat(clusterController.getSummary("SimpleKmeans","80"),CoreMatchers.containsString("80"));

	}

	@Test
	void getDataSummary_EmptyString_IfSimpleKmeansNotInvoked() throws Exception{
		String fileName = "segment-challenge.arff";
		clusterController.setFileName(fileName);
		assertEquals("", clusterController.getSummary("notExist", "70"));
	}

	@Test
	void getDataSummary_ErrorMessage_IfSimpleKmeansWithWrongPercentageRange() throws Exception{
		String fileName = "segment-challenge.arff";
		clusterController.setFileName(fileName);
		assertEquals("Input should not be empty and percentage must be a " +
						"number between 0 and 100",
				clusterController.getSummary("SimpleKmeans", "101"));
	}


	@Test
	void getDataSummary_ErrorMessage_IfSimpleKmeansWithWrongPercentageRangeNegative() throws Exception{
		String fileName = "segment-challenge.arff";
		clusterController.setFileName(fileName);
		assertEquals("Input should not be empty and percentage must be a " +
						"number between 0 and 100",
				clusterController.getSummary("SimpleKmeans", "-1"));
	}

	@Test
	void getDataSummary_ErrorMessage_IfSimpleKmeansWithWrongPercentageRangeZero() throws Exception{
		String fileName = "segment-challenge.arff";
		clusterController.setFileName(fileName);
		assertEquals("Input should not be empty and percentage must be a " +
						"number between 0 and 100",
				clusterController.getSummary("SimpleKmeans", "0"));
	}

	@Test
	void getDataSummary_ErrorMessage_IfSimpleKmeansWithWrongPercentageRange100() throws Exception{
		String fileName = "segment-challenge.arff";
		clusterController.setFileName(fileName);
		assertEquals("Input should not be empty and percentage must be a " +
						"number between 0 and 100",
				clusterController.getSummary("SimpleKmeans", "100"));
	}

	@Test
	void getDataSummary_ErrorMessage_IfSimpleKmeansWithEmptyPercentage() throws Exception{
		String fileName = "segment-challenge.arff";
		clusterController.setFileName(fileName);
		assertEquals("Input should not be empty and percentage must be a " +
						"number between 0 and 100",
				clusterController.getSummary("SimpleKmeans", ""));
	}

	@Test
	void getDataSummary_ErrorMessage_IfSimpleKmeansWithCharacterPercentage() throws Exception{
		String fileName = "segment-challenge.arff";
		clusterController.setFileName(fileName);
		assertEquals("Input should not be empty and percentage must be a " +
						"number between 0 and 100",
				clusterController.getSummary("SimpleKmeans", "lol"));
	}

	// ---EM

	@Test
	void getDataSummary_EMSummary_IfEMInvoked() throws Exception{
		String fileName = "segment-challenge.arff";
		clusterController.setFileName(fileName);

		clusterController.getClusterService().SimpleKmeansClusterer("../../Datasets/segment-challenge.arff", "80");
		assertThat(clusterController.getSummary("EM","80"),CoreMatchers.containsString("80"));

	}

	@Test
	void getDataSummary_EmptyString_IfEMInvoked() throws Exception{
		String fileName = "segment-challenge.arff";
		clusterController.setFileName(fileName);
		assertEquals("", clusterController.getSummary("notExist", "70"));
	}

	@Test
	void getDataSummary_ErrorMessage_IfEMInvokedWithWrongPercentageRange() throws Exception{
		String fileName = "segment-challenge.arff";
		clusterController.setFileName(fileName);
		assertEquals("Input should not be empty and percentage must be a " +
						"number between 0 and 100",
				clusterController.getSummary("EM", "101"));
	}

	@Test
	void getDataSummary_ErrorMessage_IfEMInvokedWithWrongPercentageRangeNegative() throws Exception{
		String fileName = "segment-challenge.arff";
		clusterController.setFileName(fileName);
		assertEquals("Input should not be empty and percentage must be a " +
						"number between 0 and 100",
				clusterController.getSummary("EM", "-1"));
	}

	@Test
	void getDataSummary_ErrorMessage_IfEMInvokedWithWrongPercentageRangeZero() throws Exception{
		String fileName = "segment-challenge.arff";
		clusterController.setFileName(fileName);
		assertEquals("Input should not be empty and percentage must be a " +
						"number between 0 and 100",
				clusterController.getSummary("EM", "0"));
	}

	@Test
	void getDataSummary_ErrorMessage_IfEMInvokedWithWrongPercentageRange100() throws Exception{
		String fileName = "segment-challenge.arff";
		clusterController.setFileName(fileName);
		assertEquals("Input should not be empty and percentage must be a " +
						"number between 0 and 100",
				clusterController.getSummary("EM", "100"));
	}

	@Test
	void getDataSummary_ErrorMessage_IfEMInvokedWithEmptyPercentage() throws Exception{
		String fileName = "segment-challenge.arff";
		clusterController.setFileName(fileName);
		assertEquals("Input should not be empty and percentage must be a " +
						"number between 0 and 100",
				clusterController.getSummary("EM", ""));
	}

	@Test
	void getDataSummary_ErrorMessage_IfEMInvokedWithCharacterPercentage() throws Exception{
		String fileName = "segment-challenge.arff";
		clusterController.setFileName(fileName);
		assertEquals("Input should not be empty and percentage must be a " +
						"number between 0 and 100",
				clusterController.getSummary("EM", "lol"));
	}


}



