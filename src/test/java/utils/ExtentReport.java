package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

        public static ExtentReports extent;
        public ExtentSparkReporter sparkReporter;
        public static ExtentTest test;

        public ExtentReport() {

            sparkReporter = new ExtentSparkReporter("target/seleniumreport.html");
            sparkReporter.config().setReportName("ui report");
            sparkReporter.config().setDocumentTitle("selenium script report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            extent.setSystemInfo(" tester", "karthick");


        }

        public static void createTest(String testname){

            test = extent.createTest(testname);
        }

        public static void info(String message){

            if(test!=null){
                test.info(message);


            }
        }

        public static void pass(String message){

            if(test!=null){
                test.pass(message);


            }
        }

        public static void fail(String message){

            if(test!=null){
                test.fail(message);


            }
        }

        public void flushReport(){

            if(extent!=null){

                extent.flush();
            }
        }

    }

