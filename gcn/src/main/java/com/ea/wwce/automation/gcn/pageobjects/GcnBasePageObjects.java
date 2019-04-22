package com.ea.wwce.automation.gcn.pageobjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.ea.wwce.automation.base.pageobjects.BasePageObject;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class GcnBasePageObjects extends BasePageObject{
	
	private static final Logger logger = Logger.getLogger(GcnBasePageObjects.class);
	
	public GcnBasePageObjects(WebDriver driver){
		super(driver);
	}
	
	String attribute_src="src";
	
	public Robot getRobot() {
		Robot rb=null;
		try{
			new Robot();
		}catch(AWTException e) {
			e.printStackTrace();
		}
		return rb;
	}

	public void randomSelect(WebElement element) {
		Select sel=new Select(element);
		int noOfEl=sel.getOptions().size();
		//System.out.println("No of Elements : "+noOfEl);
		Random rand=new Random();
		int randIndex=rand.nextInt(noOfEl);
		if(randIndex<1) {
			randIndex=randIndex+2;
		}else if(randIndex>noOfEl) {
			randIndex=randIndex-1;
		}
		//System.out.println("Random Index : "+randIndex);
		sel.selectByIndex(randIndex);

	}

	public String valueOfSelectedItem(WebElement element) {
		Select sel=new Select(element);
		System.out.println(sel.getFirstSelectedOption().getText());
		return sel.getFirstSelectedOption().getText();
	}
	
	//public List<WebElement> 
	
	public boolean verifyElementIsClickable(WebElement e) {
		boolean a;
		try {
			this.click(e);
			a=true;
		}catch(ElementNotInteractableException ex) {
			a=false;
		}
		return a;
	}


	public String setPrevWeekDate() {
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.DATE, -7);
		String pDate=sdf.format(cal.getTime());
		return pDate;
	}

	public String setNextWeekDate() {
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.DATE, 7);
		String pDate=sdf.format(cal.getTime());
		return pDate;
	}

	public String setPrevMonthDate() {
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.MONTH, -1);
		String pDate=sdf.format(cal.getTime());
		return pDate;
	}

	public String setNextMonthDate() {
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.MONTH, 1);
		String pDate=sdf.format(cal.getTime());
		return pDate;
	}
	
	public String getSourceOfImage(WebElement e) {
		String s=this.getAttributeValue(e, attribute_src);
		return s;
	}

	public int getRandNum() {

		int n1=1111;
		int n2=9999;
		Random ran=new Random();
		int rN=ran.nextInt((n2-n1)-1)+n1;
		return rN;
	}

	public int getRandNum(int n) {
		Random ran=new Random();
		int rN=Math.abs(ran.nextInt(n));
		if(rN==0) {
			rN=rN+1;
		}
		return rN;
	}

	public String getRandWord() {
		Random rand=new Random();
		//rand.nextInt(4);
		String rW="";
		String charList= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for(int i=0;i<4;i++) {
			String rW1=Character.toString(charList.charAt(rand.nextInt(charList.length())));
			rW=rW+rW1;
		}
		System.out.println("Random string is : "+rW);

		return rW;
	}
	
	public String getFilenameFromFolder(String folderPath){
		File file = new File(folderPath);
        String[] fileList = file.list();
        int noOfFiles=fileList.length;
        
        return fileList[getRandNum(noOfFiles)]; 
        
	}
	
	public String uploadFile(String path) throws AWTException, InterruptedException {
		String fName=getFilenameFromFolder(path);
		
		try {
			this.uploadAttachment(path+fName);
		}catch(AWTException e){
			logger.info("Failed to attached file " + e.getMessage());
			throw e;
		}catch(InterruptedException e) {
			logger.info("Failed to attached file " + e.getMessage());
			throw e;
		}
		
		Thread.sleep(10000);
		
		return fName;
	}
	
	public void clearTextField(WebElement e) {
		e.clear();
	}
	
	public void singleTab() throws InterruptedException {
		Robot r1=getRobot();
		r1.keyPress(KeyEvent.VK_TAB);
		Thread.sleep(1000);
		r1.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(1000);
	}
	
	public List<String> firstCharToUpper(List<String> list){
		List<String> temp = new ArrayList<String>();
		for(String s:list) {
			temp.add(s.substring(0, 1).toUpperCase()+s.substring(1));
			
		}
		/*System.out.println("Original List is : "+list);
		System.out.println("Changed List is : "+temp);*/
		return temp;
	}
	
	//Validating descending order
		public boolean isListReverseSorted(List<String> list1, List<String> list2){
		  boolean sorted=false;
		  try{
			   List<String> temp = new ArrayList<String>(list1);
			   Collections.reverse(temp);
			   temp.remove(0);
			   list2.remove(list2.size()-1);
			   sorted = temp.equals(list2);
			  
		  }catch (Exception e) {
				logger.info("Failed to Sort the list  " + e.getMessage());
			}
		  
		  return sorted;
		}
		
		public List<String> revList(List<String> list){
			List<String> temp = new ArrayList<String>(list);
			Collections.reverse(temp);
			return temp;
		}
		
		public void clearGcnCookies() throws InterruptedException {
			
			Set<Cookie> allCookies=this.driver.manage().getCookies();
			
			for(Cookie cookie:allCookies) {
				this.driver.manage().deleteCookieNamed(cookie.getName());
			}
			
			Thread.sleep(5000);
		}
		
		public boolean isAttributePresent(WebElement element , String attrib) {
			Boolean isPresent=false;
			
			try{
				String val=getAttributeValue(element, attrib);
				if(val!=null) {
					isPresent=true;
				}
			}catch(ElementNotFoundException e) {}
			
			return isPresent;
		}
		
		
	
}
