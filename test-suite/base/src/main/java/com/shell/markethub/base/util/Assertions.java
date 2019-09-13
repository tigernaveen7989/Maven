package com.shell.markethub.base.util;


import java.util.Map;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;
import org.testng.collections.Maps;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.shell.markethub.base.util.listeners.BaseListener;

/**
 * @author n.kumar8@shell.com
 * @description Below utility need to be used when the test should not fail immediately on failure
 * an assertion.
 */
public class Assertions extends SoftAssert {

	// LinkedHashMap to preserve the order of failures
	Map<AssertionError, IAssert> m_errors = Maps.newLinkedHashMap();
	public static ThreadLocal<ExtentTest> test = BaseListener.getTest();

	@Override
	public void doAssert(IAssert assertion) {
		
		onBeforeAssert(assertion);
	    
		try {
	    	assertion.doAssert();
	    	onAssertSuccess(assertion);	
	    	try {test.get().log(Status.PASS, "Actual " +"["+assertion.getActual()+"]"+ " Expected "+"["+assertion.getExpected()+"]"+ " Asserion message "+ assertion.getMessage());}catch(NullPointerException e) {}
	    } catch (AssertionError error) {
	    	onAssertFailure(assertion, error);
	    	m_errors.put(error, assertion);	
	    	try {test.get().log(Status.FAIL, error.getMessage());}catch(NullPointerException e) {}
	    } finally {
	    	onAfterAssert(assertion);
	    }
	  }
	
	// When more than assertions exist in a test, use below method when the test need to go even
	//if there is a failure at a step.Captures all the assertions within a test,
	@Override
	public void assertAll() {
		
		if (!m_errors.isEmpty()) {
			
			StringBuilder sb = new StringBuilder("The following assertions failed:");
	        boolean first = true;
			
	        for (Map.Entry<AssertionError, IAssert> errors : m_errors.entrySet()) {
	        	
	        	if (first) {
	        		first = false;
	            } else {
	            	sb.append(",");
	            }
	            
	        	sb.append("\n\t");
	            sb.append("FAILED - " + errors.getKey().getMessage());
	        }
	        
	        throw new AssertionError(sb.toString());
		}
	}
}
