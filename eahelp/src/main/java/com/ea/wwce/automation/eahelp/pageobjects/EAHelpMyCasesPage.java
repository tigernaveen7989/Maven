package com.ea.wwce.automation.eahelp.pageobjects;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ea.wwce.automation.eahelp.config.EAHELPDataConstants;

import io.qameta.allure.Step;

/**
 * 
 * @author sadabala
 *
 */
public class EAHelpMyCasesPage extends EAHelpBasePageObject {

	public static Logger logger = Logger.getLogger(EAHelpMyCasesPage.class);

	public EAHelpMyCasesPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "link")
	WebElement caseLink;
	@FindBy(css = "b.icon-paper-clip")
	WebElement attachmentsIcon;
	@FindAll(@FindBy(xpath = "//li[@class='case-attachments']//li"))
	List<WebElement> totalAttachments;
	@FindBy(className = "icon-paper-clip")
	WebElement attachButton;
	@FindBy(className = "file progress")
	WebElement fileProgress;
	@FindBy(css = ".content-summary > div:nth-of-type(1) p.content-summary")
	WebElement customerEvent;
	@FindBy(css = ".content-summary > div:nth-of-type(2) td#note-text")
	WebElement advsiorEvent;
	@FindBy(css = ".icon-remove-sign.delete-file")
	WebElement fileDeleteIcon;
	@FindBy(css = ".next")
	WebElement nextLink;
	@FindBy(css = "#error .content")
	WebElement OriginImage;
	@FindBy(css = ".resume-conversation")
	WebElement resume;
	@FindBy(css = ".conversation.chat")
	WebElement chattranscript;
	@FindBy(css = ".conversation.email")
	WebElement emailtranscript;
	@FindBy(css=".icon-paper-clip")
	WebElement attachement;
	@FindBy(css=".status.open")
	WebElement caseStatus;
	@FindBy(xpath = "//*[@class='btn-close']")
	WebElement endCase;
	@FindBy(xpath = "//*[@class='confirm-close']")
	WebElement submit;
	// By locators
	By loginButton = (By.cssSelector(".content .login"));

	// Dynamic locators
	String filename = "//li[@class='case-attachments']//li[#]//strong";
	String caseID = "//*[@id='case-#']";
	String caseIdIsOnTop = "//*[@id='my-cases']//ul/li[1][@id='case-#']";
	String paginationLink = "//a[@data-page='#']";
	//String caseStatus="//*[@id='case-#']//strong[@class='case-status open']";

	// -------------------------------------------------------------------------------------------
    @Step("Get case status from case")
	public String getCaseStaus(){		
		return this.getText(caseStatus);		
	}
	
	@Step("Verify chat transcript is shown")
	public boolean isChatTranscriptShown() {
		logger.info("Verify chat transcript is shown");
		return this.isElementVisible(chattranscript, 60);
	}

	@Step("Verify email transcript is shown")
	public boolean isEmailTranscriptShown() {
		logger.info("Verify email transcript is shown");
		return this.isElementVisible(emailtranscript, 60);
	}

	@Step("Click on resume case button")
	public void clickOnResumeButton() {
		logger.info("Click on resume button");
		try {
			this.waitForClickableElement(60, resume);
			this.click(resume);
		} catch (Exception e) {
			logger.info("Not able to click on resume button");
		}
	}

	@Step("Verify resume button is present")
	public boolean isCaseResumeButtonPresent() {
		logger.info("Verify resume button is present");
		return this.isElementVisible(resume, 60);
	}

	@Step("Verify attachment button is present")
	public boolean isAttachmentButtonPresent() {
		logger.info("Verify attachment button is present");
		return this.isElementVisible(attachement, 60);
	}

	@Step("Verify case id is on top")
	public boolean isCaseIDOnTop(String caseNumebr) {
		logger.info("Verify case id is on top");
		return this.isDynamicElementPresent(caseIdIsOnTop, caseNumebr,60);
	}

	@Step("Verify login button is alinged with Origin message")
	public String verifyLoginButtonIsAlginedWithOriginLogo() {
		String val = null;
		try {
			logger.info("Verify login button is alinged with Origin message");
			this.isElementPresent(loginButton, 10);
			val = this.getCSSValue(OriginImage, "width");
		} catch (Exception e) {
			logger.warn("Fail to verify login button is alinged with Origin message");
		}
		return val;
	}

	@Step("Delete file from attachements")
	public void deleteFile() {
		try {
			logger.info("Delete file from attachements");
			this.waitForElementToBeVisible(attachmentsIcon, 120);
			this.click(attachmentsIcon);
			this.waitForClickableElement(10, fileDeleteIcon);
			this.click(fileDeleteIcon);
		} catch (Exception e) {
			logger.warn("Fail to delete file from attachements");
		}
	}

	@Step("Verify case number is created")
	public boolean isCaseNumberIsPresent(String txtReplace) {
		boolean isTrue = false;
		logger.info("Verify case number is created");
		try {
			return this.isDynamicElementPresent(caseID, txtReplace, 10);
		} catch (Exception e) {
			logger.info("Fail to verify case number");
		}
		return isTrue;
	}

	@Step("Click on case number")
	public void clickOnCaseDetailsLink(String txtReplace) {
		logger.info("Click on case number");
		try {
			this.clickOnDynamicElement(caseID, txtReplace);
		} catch (Exception e) {
			logger.info("Fail to click on case number");
		}
	}

	@Step("Verify events are present")
	public boolean isCustomerLoggedEventsPresent() {
		logger.info("Verify customer events are present");
		return this.isElementVisible(customerEvent, 15);
	}

	@Step("Verify first event in case details")
	public boolean isAdvsiorLoggedEventsPresent() {
		logger.info("Verify first event in case details");
		return this.isElementVisible(advsiorEvent, 15);
	}

	// Verify attachments are added
	public boolean verifyAttachmentsAdded() {
		boolean isTrue = false;
		try {
			logger.info("Verifying files are added successfully ...");
			this.waitForElementToBeVisible(attachmentsIcon, 120);

			if (totalAttachments != null && !totalAttachments.isEmpty()) {
				int totalFiles = this.getSize(totalAttachments);
				logger.info("Total files are added  " + totalFiles);
				isTrue = true;
			}

		} catch (Exception e) {
			isTrue = false;
			logger.info("No attachments added to this case  " + e.getMessage());
		}
		return isTrue;
	}

	// Verify attached file name
	public boolean verifyAttachedFileName(String fileName) {
		boolean isTrue = false;
		String[] actualFile = null;
		String[] expectedFile = null;

		try {
			logger.info("Verifying added file name...");
			this.waitForElementToBeVisible(attachmentsIcon, 8);
			this.click(attachmentsIcon);

			if (totalAttachments != null && !totalAttachments.isEmpty()) {
				int totalFiles = this.getSize(totalAttachments);
				logger.info("Total files are added  " + totalFiles);
				for (WebElement file : totalAttachments) {
					logger.info("Total files are added  " + file.getText());
					actualFile = file.getText().split(" ")[0].split("_");
					String actualFileExt = file.getText().split("\\.")[1].split("\n")[0];
					expectedFile = fileName.split("\\.");
					if (actualFile[0].equals(expectedFile[0]) && actualFileExt.equals(expectedFile[1])) {
						this.click(attachmentsIcon);
						logger.info("Verifying added file name...Expected" + filename + "Actaul file name "
								+ file.getText());
						isTrue = true;
						break;
					}

				}
			}

		} catch (Exception e) {
			isTrue = false;
			logger.info("Expected file name is not found  " + e.getMessage());
		}
		return isTrue;
	}

	// Verify attachments added icon is not present
	public boolean verifyAttachmentsNotAdded() {
		boolean isTrue = false;
		logger.info("verify attachments are not present  ");
		try {
			Thread.sleep(6000);
			isTrue = attachmentsIcon.isDisplayed();
			logger.info("Attachments are present  ");
		} catch (Exception e) {
			logger.info("verify attachments are not present  ");
		}
		return isTrue;
	}

	// download attachments
	public boolean downloadFile(int index) {
		String fileName = null;
		boolean isTrue = false;
		try {
			this.waitForElementToBeVisible(attachmentsIcon, 30);
			this.click(attachmentsIcon);
			if (totalAttachments != null && !totalAttachments.isEmpty()) {
				Thread.sleep(3000);
				fileName = totalAttachments.get(index).getText();
				fileName = fileName.split("\n")[0];
				this.clickOnListElement(totalAttachments, index);
				logger.info("Get file name from attached file list " + fileName);

				String home = System.getProperty("user.home");
				File file = new File(home + "/Downloads/" + fileName);

				Thread.sleep(5000);

				if (file.isFile()) {
					logger.info("File downloaded successfully " + fileName);
					isTrue = true;
				} else {
					logger.warn("File is not downloaded " + fileName);
				}
			}
		} catch (Exception e) {
			logger.warn("No attachments added to this case  " + e.getMessage());
		}
		return isTrue;
	}

	public void attachFileFromMyCases(String fileName) {
		try {

			String filePath = EAHELPDataConstants.PROJECT_ROOT_LOCATION
					+ EAHELPDataConstants.EAHELP_TEST_DATA_ATTACHMENT_PATH;

			// add file path to file name
			fileName = filePath + fileName;

			this.waitForClickableElement(60, attachButton);
			this.click(attachButton);

			// Wait for 1 secs
			Thread.sleep(1000);

			// upload attachment
			this.uploadAttachment(fileName);

			// wait for file to upload
			this.waitForElementToBeInVisible(fileProgress, 6);

			// Wait for 2 secs
			Thread.sleep(2000);

		} catch (Exception e) {
			logger.warn("No attachments added to this case  " + e.getMessage());
		}

	}

	// Verify focus is shifted to top
	public boolean verifyFocusIsShiftedToTop() {
		boolean isTrue = false;
		try {
			this.waitForElementToBeVisible(nextLink, 10);
			this.click(nextLink);
			Thread.sleep(5000);
			WebElement el1 = this.verifyFocus();
			String caseNumber1 = el1.findElement(org.openqa.selenium.By.className("case-number")).getText();
			logger.info(caseNumber1.split(" ")[0]);
			this.waitForElementToBeVisible(nextLink, 10);
			this.click(nextLink);
			Thread.sleep(5000);
			WebElement el2 = this.verifyFocus();
			String caseNumber2 = el2.findElement(org.openqa.selenium.By.className("case-number")).getText();
			logger.info(caseNumber2.split(" ")[0]);
			isTrue = caseNumber1.equalsIgnoreCase(caseNumber2);

		} catch (Exception e) {
			logger.warn("Failed to find current focused element  " + e.getMessage());
		}
		return isTrue;
	}
	
	@Step("Click on End case button")
	public void clickOnEndCaseButton() {
		logger.info("Click on End Case button");
		try {
			this.waitForClickableElement(60, endCase);
			this.click(endCase);
		} catch (Exception e) {
			logger.info("Not able to click on End Case button");
		}
	}
	
	@Step("Click on Submit button")
	public void clickOnSubmitButton() {
		logger.info("Click on Submit button");
		try {
			this.waitForClickableElement(60, submit);
			this.click(submit);
		} catch (Exception e) {
			logger.info("Not able to click on Submit button");
		}
	}
}
