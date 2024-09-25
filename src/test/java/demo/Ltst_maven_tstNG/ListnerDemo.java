package demo.Ltst_maven_tstNG;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListnerDemo implements ITestListener{
	
	public void onTestSuccess(ITestResult result) {
		
	//	System.out.println("onTestSuccess Listener ran"+result.getMethod().getMethodName()); //calling method to take ss
		System.out.println("onTestSuccess Listener ran for "+result.getName());
	}
	
	public void onTestFailure(ITestResult result) {
		System.out.println("onTestFailure Listener ran for "+result.getName());
	}
	
	public void onTestStart(ITestResult result) {
		System.out.println("onTestStart Listener ran");
	}
	
	public void onStart(ITestContext context) {
		System.out.println("onStart Listener ran");
	}
	
	public void onFinish(ITestContext context) {
		System.out.println("onFinish Listener ran");
	}
	
}
