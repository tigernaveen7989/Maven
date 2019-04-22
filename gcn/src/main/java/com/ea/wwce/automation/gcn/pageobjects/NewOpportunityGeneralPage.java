package com.ea.wwce.automation.gcn.pageobjects;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.gcn.config.GcnDataConstants;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import io.qameta.allure.Step;

public class NewOpportunityGeneralPage extends GcnBasePageObjects{
	
	/*
	 * This class defines the UI elements and methods to update / change the General Tab in Opportunity
	 * settings page from admin view, admin can create and edit existing opportunity.
	 */
	
	public static final Logger logger=Logger.getLogger(NewOpportunityGeneralPage.class);
	
	public NewOpportunityGeneralPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

	}
		
	String attribute_src="src";
	String attribute_value="value";
	String prevImgLink="";
	
	@FindBy(id="title")
	WebElement titleBox;
	
	@FindBy(id="opportunity-settings-game-id")
	WebElement gameSelect;
	
	@FindBy(id="opportunity-settings-registration-start-date")
	WebElement startDate;
	
	@FindBy(id="opportunity-settings-start-time")
	WebElement startTime;
	
	@FindBy(id="opportunity-settings-registration-end-date")
	WebElement endDate;
	
	@FindBy(id="opportunity-settings-end-time")
	WebElement endTime;
	
	@FindBy(id="opportunity-settings-visibility")
	WebElement visibilitySelection;
	
	@FindBy(id="opportunity-settings-approval")
	WebElement approvalSelection;
	
	@FindBy(xpath="//div[contains(text(),'Upload New')]")
	WebElement uploadImageBtn;
	
	@FindBy(xpath="//div[@id='images-placeholder']/ul/li[3]/div/img[@class='image_picker_image']")
	WebElement previewImage;
	
	@FindBy(xpath="//div[@id='images-placeholder']/ul/li[1]/div/img[@class='image_picker_image']")
	WebElement firstGalleryImg;
	
	@FindBy(xpath="//div[@class='thumbnail selected']/img")
	WebElement setImg;
	
	@FindBy(xpath="//div[@id='images-placeholder']/ul/li")
	List<WebElement> imgList;
	
	@FindBy(xpath="//div/ul[@class='thumbnails image_picker_selector']")
	WebElement imgHolder;
	
	@FindBy(id="images-nextbutton")
	WebElement imgNextBtn;
	
	@FindBy(id="images-prevbutton")
	WebElement imgPrevBtn;
	
	@FindBy(xpath="//iframe[@id='opportunity-settings-content-description_ifr']")
	WebElement oppDescIframe;
	
	@FindBy(xpath="//body[@id='tinymce']")
	WebElement oppDescBox;
	
	@FindBy(id="button_save_opportunity_settings")
	WebElement createOppBtn;
	
	@FindBy(css="button.btn.btn-default")
	WebElement successOkBtn;
	
	@FindBy(xpath="//img[@id='image-to-display'][contains(@src,'amazon')]")
	WebElement previewImg;
	
	@FindBy(xpath="//a[@id='button_save_opportunity_settings']")
	WebElement saveBtn;
	
	@FindBy(xpath="//ul[@class='error-list']")
	WebElement errorFrame;
	
	String galleryList="//ul[@class='thumbnails image_picker_selector']/li/div/img[contains(@src,'.jpg')]";	
	String randImg="//div[@id='images-placeholder']/ul/li[#]/div/img[@class='image_picker_image']";
	String errors="//ul[@class='error-list']/li";
	

	// UI Checks
	
	public boolean isTitleBoxDisplayed() {
		this.waitForElementToBeVisible(titleBox, GcnDataConstants.IMPLICIT_TIMEOUT);
		logger.info("Check for Title Box");
		return this.isElementVisible(titleBox, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	// Below commented code will be removed before next PR after re-usability analysis.
	
	/*public void waitForOppTitle() {
		this.waitForElementToBeVisible(titleBox, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	
	
	public boolean isGameSelectDisplayed() {
		logger.info("Check for Game Select Drop Down");
		return this.isElementVisible(gameSelect, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isStartDateDisplayed() {
		logger.info("Check for Start Date Field");
		return this.isElementVisible(startDate, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isStartTimeDisplayed() {
		logger.info("Check for Start Time Drop Down");
		return this.isElementVisible(startTime, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isEndDateDisplayed() {
		logger.info("Check for End Date Field");
		return this.isElementVisible(endDate, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isEndTimeDisplayed() {
		logger.info("Check for End Time Drop Down");
		return this.isElementVisible(endTime, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isVisibilityDisplayed() {
		logger.info("Check for Visibility Field");
		return this.isElementVisible(visibilitySelection, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isApprovalDisplayed() {
		logger.info("Check for Approval Selection Field");
		return this.isElementVisible(approvalSelection, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isPreviewImageDisplayed() {
		logger.info("Check for Default Preview Image in Gallery");
		return this.isElementVisible(previewImage, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
		
	public boolean isImgNextBtnDisplayed() {
		logger.info("Check for Image Next Button");
		return this.isElementVisible(imgNextBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
	}*/
	
	public boolean isImgPrevBtnDisplayed() {
		logger.info("Check for Image Prev Button");
		return this.isElementVisible(imgPrevBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	/*public boolean isOppDescIframeDisplayed() {
		logger.info("Check for Opportunity Description IFrame");
		return this.isElementVisible(oppDescIframe, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isOppDescBoxDisplayed() {
		logger.info("Check for Opportunity Description Box");
		return this.isElementVisible(oppDescBox, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
		
	public boolean isImgHolderDisplayed() {
		logger.info("Check for Image gallery to be selected for Opportunity Preview image.");
		return this.isElementVisible(imgHolder, GcnDataConstants.IMPLICIT_TIMEOUT);
	}*/
	
	@Step("Verify if Opportunity Preview image is Set")
	public boolean isOppPreviewImgDisplayed() {
		boolean a=false;
		logger.info("Check for Opportunity Preview image.");
		this.waitForElementToBeVisible(previewImg, GcnDataConstants.IMPLICIT_TIMEOUT);
		String prevImgLink=this.getSourceOfImage(previewImg);
		if(prevImgLink.contains("jpg")) {
			logger.info("Opportunity Preview image is displayed");
			a=true;
		}
		return a;
	}
	
	public String getPrevImgLink() {
		prevImgLink=this.getSourceOfImage(previewImg);
		return prevImgLink;
	}
	
	public boolean isUploadedImgDisplayed() throws AWTException, InterruptedException {
		this.waitForElementToBeVisible(setImg, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(previewImg, GcnDataConstants.IMPLICIT_TIMEOUT);

		String fn=uploadOppImage();
		boolean b=false;
		logger.info("Check if uploaded image is listed in the gallery and set as Opp preview image");
		String s1=this.getSourceOfImage(setImg);
		String s2=this.getSourceOfImage(previewImg);
		if(s1.contains(fn) && s2.contains(fn)) {
			logger.info("Uploaded Image is Displayed in Gallery and Set as Opportunity Preview img");
			b=true;
		}else {
			logger.info("Image not displayed in Gallery or not Set as preview image");
		}
		return b;
	}
	
	public String uploadImage() throws AWTException, InterruptedException {
		this.waitForElementToBeVisible(setImg, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(previewImg, GcnDataConstants.IMPLICIT_TIMEOUT);

		String fn=uploadOppImage();
		return fn;		
	}
	
	
	public boolean isCreateBtnDisplayed() {
		logger.info("Check for Create Button.");
		return this.isElementVisible(createOppBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isSaveBtnDisplayed() {
		logger.info("Check for Save Button");
		return this.isElementVisible(saveBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isOkBtnDisplayed() {
		logger.info("Check for OK Button.");
		return this.isElementVisible(successOkBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	public boolean isErrorFrameDisplayed() {
		this.windowScrollDwn();
		return this.isElementVisible(errorFrame, GcnDataConstants.IMPLICIT_TIMEOUT);
	}
	
	
	// UI Actions
	
	public String enterTitle() {
		String oppTitle= getSelectedGame()+"_Opportunity_"+Integer.toString(getRandNum());
		this.sendKeys(titleBox, oppTitle);
		logger.info("Opportunity Title entered as :"+oppTitle);
		return oppTitle;
	}
	
	public String selectGame() {
		try {
			if(gameSelect.getText().contains("Select a game")) {
				this.randomSelect(gameSelect);
				logger.info("Game Selected with Random Index ");
			}
		}catch(ElementNotFoundException e) {
			logger.info(e.getMessage());
		}
		
		return valueOfSelectedItem(gameSelect);
	}
	
	public String getSelectedGame() {
		return valueOfSelectedItem(gameSelect);
	}
	
	public void setStartDateTime() {
		this.sendKeys(startDate, this.setPrevWeekDate());
		logger.info("Setting the Start Date");
		this.randomSelect(startTime);
		logger.info("Setting the Start Time");
	}
	
	public void setEndDateTime() {
		this.sendKeys(endDate, this.setNextMonthDate());
		logger.info("Setting the End Date");
		this.randomSelect(endTime);
		logger.info("Setting the End Time");
	}
	
	public void setOppDateInvalid1() {
		//Start date is after end date
		this.sendKeys(startDate, this.setNextMonthDate());
		this.sendKeys(endDate, this.setNextWeekDate());
	}
		
	/*public void selVisibility(int v) {
		this.selectByIndex(visibilitySelection, v);
		logger.info("Selecting the Opportunity Visibility");
	}*/
	
	public void selApproval(int approval) {
		this.selectByIndex(approvalSelection, approval);
		logger.info("Selecting the Approval Required Status");
	}
	
	// upload a random image.
	public String uploadOppImage() throws AWTException, InterruptedException {
		//String fileName=this.getFilenameFromFolder(GcnDataConstants.IMG_FOLDER_PATH);
		clickOnUploadImgBtn();
		String path=GcnDataConstants.PROJECT_ROOT_LOCATION+GcnDataConstants.GCN_TEST_OPP_IMAGES;
		String fName=uploadFile(path);		
		return fName;
	}
	
	// upload an image.
	public String uploadSingleImage() throws AWTException, InterruptedException {
		String path=GcnDataConstants.PROJECT_ROOT_LOCATION+GcnDataConstants.GCN_TEST_OPP_IMAGES;
		String fName=uploadFile(path);	
		createORSaveOpportunity();
		return fName;
	}
	
	
	public String getImgFileName(String folderPath) {
		return this.getFilenameFromFolder(folderPath);
	}
	
	public String selPreviewImg() {
		logger.info("Selecting the Opportunity Preview Image");
		int r1=imgList.size();
		String s=String.valueOf(getRandNum(r1));
		this.waitForElementToBeVisible(imgNextBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		for(int i=1;i<=getRandNum(r1);i++) {
			this.waitForClickableElement(GcnDataConstants.IMPLICIT_TIMEOUT, imgNextBtn);
			this.click(imgNextBtn);
		}
		this.waitForClickableElement(GcnDataConstants.IMPLICIT_TIMEOUT, imgNextBtn);
		WebElement img=this.findDynamicElement(randImg, s);
		this.waitForElementToBeVisible(img, GcnDataConstants.IMPLICIT_TIMEOUT);
		img.click();
		String setImgSrc=this.getSourceOfImage(img);
		return setImgSrc;
		//this.click(previewImage);
	}
	
	public void fillOpportunityDesc(String oppDesc) {
		this.waitAndSwitchToIframe(GcnDataConstants.IMPLICIT_TIMEOUT, oppDescIframe);
		logger.info("Switched to Opportunity Description iFrame");
		this.sendKeys(oppDescBox, oppDesc);
		this.driver.switchTo().defaultContent();
	}
	
	public void clickOnUploadImgBtn() {
		logger.info("Clicking on Upload New button");
		this.click(uploadImageBtn);
	}
	/*public void clickOnCreateNewOpp() {
		logger.info("Clicking on Create button");
		this.waitForElementToBeVisible(createOppBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.click(createOppBtn);
	}
	*/
	public void clickOnOkBtn() {
		logger.info("Clicking on OK button on Success popup");
		this.waitForElementToBeVisible(successOkBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.isElementVisible(successOkBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.click(successOkBtn);
	}
	
	/*public void clickOnSaveBtn() {
		logger.info("Clicking on Opportunity Save button");
		this.isElementVisible(saveBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.click(saveBtn);
	}*/
	
	public void clickOnImgNxtBtn() {
		this.waitForElementToBeVisible(imgNextBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		logger.info("Clicking on Next button from image gallery");
		this.click(imgNextBtn);
	}
	
	public void clickOnImgPreBtn() {
		logger.info("Clicking on Prev button from image gallery");
		this.click(imgPrevBtn);
	}
	
	public boolean checkForErrorsDisplayed() {
		List<WebElement> errorList=this.driver.findElements(By.xpath(errors));
		List<String> errors=new ArrayList<String>();
		for(WebElement e:errorList) {
			if(e.getText().contains("Title")) {
				//logger.info("Title is Not Set");
				errors.add(e.getText());
			}else if(e.getText().contains("Game")) {
				//logger.info("Game is Not Selected");
				errors.add(e.getText());
			}else if(e.getText().contains("Start Time")) {
				//logger.info("Start Date and Time is not Set Correctly");
				errors.add(e.getText());
			}else if(e.getText().contains("End Time")) {
				//logger.info("End Date and Time not Set Correctly");
				errors.add(e.getText());
			}else if(e.getText().contains("Description")) {
				//logger.info("Opportunity Description is not Provided");
				errors.add(e.getText());
			}else if(e.getText().contains("Start date")) {
				//logger.info("Opportunity Description is not Provided");
				errors.add(e.getText());
			}
		}
		
		if(errors.size()<=0) {
			logger.info("No Error Messages");
		}else {
			logger.info("List of errors are as follows");
			for(String s:errors) {
				logger.info(s);
			}
		}
		
		return ((errorList == null) ? false : (errorList.isEmpty() ? false : true));
	}
	
	
	// Methods to Clear the Text Fields
	
	/*public void clearOppTitle() {
		clearTextField(titleBox);
	}
		
	public void resetGameSelection() {
		Select sel=new Select(gameSelect);
		sel.selectByIndex(0);
	}*/
	
	@Step("Clear Opp Description")
	public void clearOppDesc() {
		this.windowScrollDwn();
		this.waitAndSwitchToIframe(GcnDataConstants.IMPLICIT_TIMEOUT, oppDescIframe);
		clearTextField(oppDescBox);
		this.switchToDefaultContent();
	}
	
	public String getOppTitle() {
		return this.getAttributeValue(titleBox, attribute_value);
	}
	
	@Step("Fill Opp general tab")
	public String[] fillOppGeneralTab(String visibility,String approvalReq,String oppDesc) {
		String[] arr1 = new String[2];
		String oppTitle="";
		String imgSrc="";
		this.waitForElementToBeVisible(titleBox, GcnDataConstants.IMPLICIT_TIMEOUT);
		String game=selectGame();
		if(!game.contains("(")) {
			oppTitle=enterTitle();
		}
		
		setStartDateTime();
		setEndDateTime();
		imgSrc=selPreviewImg();
		this.selectVisibleText(visibilitySelection, visibility);
		//selVisibility(1);
		this.selectVisibleText(approvalSelection, approvalReq);
		//selApproval(1);		
		fillOpportunityDesc(oppDesc);
		arr1[0]=oppTitle;
		arr1[1]=imgSrc;
		return arr1;
	}
	
	/*public String[] editOppGeneralTab() {
		String[] arr1 = new String[2];
		String oppTitle="";
		String imgSrc="";
		this.waitForElementToBeVisible(titleBox, GcnDataConstants.IMPLICIT_TIMEOUT);
		oppTitle=this.getAttributeValue(titleBox, attribute_value);
		setStartDateTime();
		setEndDateTime();
		imgSrc=selPreviewImg();
		this.selectVisibleText(visibilitySelection, visibility);
		//selVisibility(1);
		this.selectVisibleText(approvalSelection, approvalReq);
		fillOpportunityDesc("Description edited");
		arr1[0]=oppTitle;
		arr1[1]=imgSrc;
		return arr1;
	}*/
	
	@Step("Fill Opp general tab without image")
	public String fillOppGeneralTabWithoutImg(String visibility,String approvalReq,String oppDesc) {
		this.waitForElementToBeVisible(titleBox, GcnDataConstants.IMPLICIT_TIMEOUT);
		selectGame();
		String oppTitle=enterTitle();
		setStartDateTime();
		setEndDateTime();
		this.selectVisibleText(visibilitySelection, visibility);
		//selVisibility(1);
		this.selectVisibleText(approvalSelection, approvalReq);
		//selApproval(1);			
		fillOpportunityDesc(oppDesc);
		return oppTitle; 
	}
	
	@Step("Verify first image is set as default Opp image")
	public boolean verifyDefaultImageSet() {
		boolean a=false;
		String s=setImg.getAttribute(attribute_src);
		String s2=previewImg.getAttribute(attribute_src);
		if(s.equalsIgnoreCase(s2)) {
			a=true;
		}
		return a;
	}
	
	public void createORSaveOpportunity() {
		if(this.isElementVisible(createOppBtn, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			this.click(createOppBtn);
		}else if(this.isElementVisible(saveBtn, GcnDataConstants.IMPLICIT_TIMEOUT)){
			this.click(saveBtn);
		}

		this.waitForElementToBeVisible(successOkBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.isElementVisible(successOkBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.click(successOkBtn);
	}
	
	public void clearAllGeneralFields() {
		//clearOppTitle();
		//resetGameSelection();
		clearTextField(startDate);
		clearTextField(endDate);
		clearOppDesc();
		this.click(saveBtn);
	}
	
	public void fillOppGeneralInvalidDate() {
		this.sendKeys(startDate, this.setNextMonthDate());
		this.sendKeys(endDate, this.setNextWeekDate());
		fillOpportunityDesc("Description for Opportunity");
		this.click(saveBtn);
	}
	
	@Step("Verify gallery images are Listed")
	public boolean verifyGalleryImgDisplayed() throws InterruptedException {
		Thread.sleep(4000);
		WebElement el;
		boolean a=false;
		for(int i=0;i<4;i++) {
			el=this.findDynamicElement(galleryList, Integer.toString(i));
			if(el.isDisplayed()) {
				a=true;
			}
			
		}
		return a;		
	}
	
	@Step("Verify General Tab UI Elements")
	public boolean verifyOppGeneralTabUi() {
		boolean a=false;
		
		this.waitForElementToBeVisible(gameSelect, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(startDate, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(startTime, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(endDate, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(endTime, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(visibilitySelection, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(approvalSelection, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(previewImage, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(imgNextBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(imgHolder, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(oppDescIframe, GcnDataConstants.IMPLICIT_TIMEOUT);
		this.waitForElementToBeVisible(uploadImageBtn, GcnDataConstants.IMPLICIT_TIMEOUT);
		
		if(this.isElementVisible(gameSelect, GcnDataConstants.IMPLICIT_TIMEOUT) && 
				this.isElementVisible(startDate, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(startTime, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(endDate, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(endTime, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(visibilitySelection, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(approvalSelection, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(previewImage, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(imgNextBtn, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(imgHolder, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(oppDescIframe, GcnDataConstants.IMPLICIT_TIMEOUT) &&
				this.isElementVisible(uploadImageBtn, GcnDataConstants.IMPLICIT_TIMEOUT)) {
			a=true;
		}
		return a;
	}
		
}
