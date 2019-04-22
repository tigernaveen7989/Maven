package com.ea.wwce.automation.base.util;


import java.util.Map;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;
import org.testng.collections.Maps;

/**
 * @author rdronamraju
 * @description Below utility need to be used when the test should not fail immediately on failure
 * an assertion.
 */
public class Assertions extends SoftAssert {

	// LinkedHashMap to preserve the order of failures
	Map<AssertionError, IAssert> m_errors = Maps.newLinkedHashMap();

	@Override
	public void doAssert(IAssert assertion) {
		
		onBeforeAssert(assertion);
	    
		try {
	    	assertion.doAssert();
	    	onAssertSuccess(assertion);			
	    } catch (AssertionError error) {
	    	onAssertFailure(assertion, error);
	    	m_errors.put(error, assertion);			
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
