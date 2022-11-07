package com.aws.classifyMicroservice;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
/**
 * @author Yitao Ma
 * */

/**NOTE: Actual testing is not here, please go to final-test branch, not in this react branch*/
@SpringBootTest
class ClassifyMicroserviceApplicationTests {
    private static ClassifyController classifyController;
    private static ClassifyService classifyService;

    @BeforeAll
    public static void initialise() { // initialise the controller and service
        classifyController = new ClassifyController(); // for test the
        // controller class's methods
        classifyController.initialiseClassifyServiceAttribute(); //
        // initialise the service attribute in the controller class
        classifyService = new ClassifyService();// for directly test the
        // service class
    }

    @AfterEach
    public void reset() {
        // Reset file name
        classifyController.setFileName("");
       classifyService.setFilePath("");
        classifyController.getClassifyService().setDetails(null);
        classifyController.getClassifyService().setMatrix(null);
    }

    // Below test the controller class's methods
    @Test
    void getFileName_UploadedFileName_IfSetFileNameInvoked() {
        String fileName = "test.arff";
        classifyController.setFileName(fileName);
        assertEquals(fileName, classifyController.getFileName());
    }

    @Test
    void getFileName_null_IfSetFileNameNotInvoked() {
        assertEquals("", classifyController.getFileName());
    }
    // Since setFileName() tests are same for getFileName() tests , here not test them again

    //----- NaiveBayes

    @Test
    void getDataSummary_NaiveBayesSummary_IfNaiveBayesInvoked() throws
    Exception {
        String filePath = "/Users/ethan/Desktop/Programming project/Github-Repo/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/heart-clean.arff";
        // must use the same classifyService object in the controller class not to use the object in this test as they
        //are 2 different objects
        classifyController.getClassifyService().setFilePath(filePath);
        // as long as the summary contains information it is correct
        assertThat(classifyController.getDataSummary("NaiveBayes", "80"),
                CoreMatchers.containsString("80"));
    }
    @Test
    void getDataSummary_EmptyString_IfNaiveBayesNotInvoked() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("", classifyController.getDataSummary("notExist", "70"));
    }

    @Test
    void getDataSummary_ErrorMessage_IfNaiveBayesInvokedWithWrongPercentageRange() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                        "number between 0 and 100",
                classifyController.getDataSummary("NaiveBayes", "101"));
    }

    @Test
    void getDataSummary_ErrorMessage_IfNaiveBayesInvokedWithWrongPercentageRangeNegative() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                        "number between 0 and 100",
                classifyController.getDataSummary("NaiveBayes", "-1"));
    }

    @Test
    void getDataSummary_ErrorMessage_IfNaiveBayesInvokedWithWrongPercentageRangeZero() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                        "number between 0 and 100",
                classifyController.getDataSummary("NaiveBayes", "0"));
    }

    @Test
    void getDataSummary_ErrorMessage_IfNaiveBayesInvokedWithWrongPercentageRange100() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                        "number between 0 and 100",
                classifyController.getDataSummary("NaiveBayes", "100"));
    }

    @Test
    void getDataSummary_ErrorMessage_IfNaiveBayesInvokedWithEmptyPercentage() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                        "number between 0 and 100",
                classifyController.getDataSummary("NaiveBayes", ""));
    }

    @Test
    void getDataSummary_ErrorMessage_IfNaiveBayesInvokedWithCharacterPercentage() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                        "number between 0 and 100",
                classifyController.getDataSummary("NaiveBayes", "sdfdsf"));
    }

    //----- ZeroR
    @Test
    void getDataSummary_ZeroRSummary_IfZeroRInvoked() throws
            Exception {
        String filePath = "/Users/ethan/Desktop/Programming project/Github-Repo/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/heart-clean.arff";
        // must use the same classifyService object in the controller class not to use the object in this test as they
        //are 2 different objects
        classifyController.getClassifyService().setFilePath(filePath);
        // as long as the summary contains information it is correct
        assertThat(classifyController.getDataSummary("ZeroR", "80"),
                CoreMatchers.containsString("80"));
    }
    @Test
    void getDataSummary_EmptyString_IfZeroRNotInvoked() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("", classifyController.getDataSummary("notExist", "70"));
    }

    @Test
    void getDataSummary_ErrorMessage_IfZeroRInvokedWithWrongPercentageRange() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                        "number between 0 and 100",
                classifyController.getDataSummary("ZeroR", "101"));
    }

    @Test
    void getDataSummary_ErrorMessage_IfZeroRInvokedWithWrongPercentageRangeNegative() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                        "number between 0 and 100",
                classifyController.getDataSummary("ZeroR", "-1"));
    }

    @Test
    void getDataSummary_ErrorMessage_IfZeroRInvokedWithWrongPercentageRangeZero() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                        "number between 0 and 100",
                classifyController.getDataSummary("ZeroR", "0"));
    }

    @Test
    void getDataSummary_ErrorMessage_IfZeroRInvokedWithWrongPercentageRange100() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                        "number between 0 and 100",
                classifyController.getDataSummary("ZeroR", "100"));
    }
    @Test
    void getDataSummary_ErrorMessage_IfZeroRInvokedWithEmptyPercentage() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                        "number between 0 and 100",
                classifyController.getDataSummary("ZeroR", ""));
    }

    @Test
    void getDataSummary_ErrorMessage_IfZeroRInvokedWithCharacterPercentage() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                        "number between 0 and 100",
                classifyController.getDataSummary("ZeroR", "sdfdsf"));
    }

    //Logistic
    @Test
    void getDataSummary_LogisticSummary_IfLogisticInvoked() throws
            Exception {
        String filePath = "/Users/ethan/Desktop/Programming project/Github-Repo/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/heart-clean.arff";
        // must use the same classifyService object in the controller class not to use the object in this test as they
        //are 2 different objects
        classifyController.getClassifyService().setFilePath(filePath);
        // as long as the summary contains information it is correct
        assertThat(classifyController.getDataSummary("Logistic", "80"),
                CoreMatchers.containsString("80"));
    }
    @Test
    void getDataSummary_EmptyString_IfLogisticNotInvoked() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("", classifyController.getDataSummary("notExist", "70"));
    }

    @Test
    void getDataSummary_ErrorMessage_IfLogisticInvokedWithWrongPercentageRange() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                        "number between 0 and 100",
                classifyController.getDataSummary("Logistic", "101"));
    }

    @Test
    void getDataSummary_ErrorMessage_IfLogisticInvokedWithWrongPercentageRangeNegative() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                        "number between 0 and 100",
                classifyController.getDataSummary("Logistic", "-1"));
    }

    @Test
    void getDataSummary_ErrorMessage_IfLogisticInvokedWithWrongPercentageRangeZero() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                        "number between 0 and 100",
                classifyController.getDataSummary("Logistic", "0"));
    }

    @Test
    void getDataSummary_ErrorMessage_IfLogisticInvokedWithWrongPercentageRange100() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                        "number between 0 and 100",
                classifyController.getDataSummary("Logistic", "100"));
    }

    @Test
    void getDataSummary_ErrorMessage_IfLogisticInvokedWithEmptyPercentage() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                        "number between 0 and 100",
                classifyController.getDataSummary("Logistic", ""));
    }

    @Test
    void getDataSummary_ErrorMessage_IfLogisticInvokedWithCharacterPercentage() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                        "number between 0 and 100",
                classifyController.getDataSummary("Logistic", "sdfdsf"));
    }

    //below test getConfusionMatrix()
    @Test
    void getConfusionMatrix_null_IfServiceClassMatrixAttributeNotInitialised() throws Exception {
        assertNull(classifyController.getConfusionMatrix());
    }

    @Test
    void getConfusionMatrix_NotNull_IfServiceClassMatrixAttributeIsInitialised() throws Exception {
String filePath = "/Users/ethan/Desktop/Programming project/Github-Repo/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/heart-clean.arff";
        classifyController.getClassifyService().setFilePath(filePath);
        // as long as the get DataSummary is invoked the matrix will be initialised
        String result = classifyController.getDataSummary("Logistic", "80");
        assertNotNull(classifyController.getConfusionMatrix());
    }

    //below test getDetails()
    @Test
    void getDetails_null_IfServiceClassDetailsAttributeNotInitialised() throws Exception {
        assertNull(classifyController.getDetails());
    }

    // ----- below test classifyService class's methods -----
    @Test
    void naiveBayes_CorrectSummary_IfNaiveBayesInvoked() throws Exception {
        String fileName = "/Users/ethan/Desktop/Programming project/Github-Repo/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/heart-clean.arff";
        classifyService.setFilePath(fileName);
        assertThat(classifyService.naiveBayes( "78"),
                CoreMatchers.containsString("78"));
    }

    @Test
    void naiveBayes_ErrorMessage_IfNaiveBayesInvokedWithWrongPercentageRange() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                "number between 0 and 100", classifyService.naiveBayes("200"));
    }

    @Test
    void naiveBayes_ErrorMessage_IfNaiveBayesInvokedWithCharacterPercentage() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                "number between 0 and 100", classifyService.naiveBayes("fsdfdsf"));
    }

    @Test
    void naiveBayes_ErrorMessage_IfNaiveBayesInvokedWithEmptyPercentage() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                "number between 0 and 100", classifyService.naiveBayes(""));
    }

}