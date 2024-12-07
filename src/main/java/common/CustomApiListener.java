package common;
/*
@author Bhimashankar Teli
 */

import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import org.testng.TestListenerAdapter;
import org.testng.annotations.Test;

public class CustomApiListener extends Begin implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTestManager.startTest(result.getMethod().getMethodName());
        System.out.println(
                "============================================================================================================================");
        System.out.println("STARTED - " + result.getTestClass().getName() + "#" + result.getName());
        System.out.println(
                "============================================================================================================================");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
        System.out.println(
                "============================================================================================================================");
        System.out.println("PASSED - " + tr.getTestClass().getName() + "#" + tr.getName());
        System.out.println(
                "============================================================================================================================");
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
        System.out.println(
                "============================================================================================================================");
        System.out.println("FAILED - " + tr.getTestClass().getName() + "#" + tr.getName());

        // Added for B-26825 (unRegister User check condition starts)


        // Added for B-26825 (unRegister User check condition ends)

        System.out.println(
                "============================================================================================================================");
        System.out.println(
                "============================================================================================================================");
        System.out.println("FAILURE REASON");
        System.out.println(tr.getThrowable());
        System.out.println(
                "============================================================================================================================");
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
        System.out.println(
                "============================================================================================================================");
        System.out.println("SKIPPED - " + tr.getTestClass().getName() + "#" + tr.getName());
        System.out.println(
                "============================================================================================================================");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext result) {

        ExtentManager.getInstance();
               // getMethod().getMethodName());
        System.out.println(
                "============================================================================================================================");
        System.out.println("STARTED - " + result.getClass().getName()+ "#" + result.getName());
        System.out.println(
                "============================================================================================================================");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println(("*** Test Suite " + iTestContext.getName() + " ending ***"));
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
    }

}