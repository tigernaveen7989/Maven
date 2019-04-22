package com.ea.wwce.automation.thor.pageobjects;

import com.ea.wwce.automation.base.pageobjects.BasePageObject;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;


/**
 * 
 * @author mohan
 * Base Page object for Thor application
 */
public class ThorBasePageObject extends BasePageObject{

 	public ThorBasePageObject(WebDriver driver){
       super(driver);
    }
     
}