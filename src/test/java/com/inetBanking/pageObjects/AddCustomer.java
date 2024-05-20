package com.inetBanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomer {
    WebDriver ldriver;
    public AddCustomer(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }
    @FindBy(xpath="//a[normalize-space()='New Customer']")
    WebElement addNewCustomerBtn;
    
    @FindBy(xpath="//input[@name='name']")
    WebElement	txtCustName;
    
    @FindBy(name="rad1")
    WebElement	gender;

    @FindBy(name="dob")
    WebElement dob	;
    
    @FindBy(xpath="//textarea[@name='addr']")
    WebElement txtaddress	;
    
    @FindBy(xpath="//input[@name='city']")
    WebElement txtcity	;
    
    @FindBy(xpath="//input[@name='state']")
    WebElement txtstate	;
    
    @FindBy(xpath="//input[@name='pinno']")
    WebElement custPincode	;
    
    @FindBy(xpath="//input[@name='telephoneno']")
    WebElement custTelephone	;
    
    @FindBy(xpath="//input[@name='emailid']")
    WebElement txtemail	;
    
    @FindBy(xpath="//input[@name='password']")
    WebElement txtpass	;
    
    @FindBy(xpath="//input[@name='sub']")
    WebElement submitBtn	;
    
    public void ClickNewCustBtn() {
    	addNewCustomerBtn.click();
    }
    
    public void custName(String name) {
    	txtCustName.sendKeys(name);
    }
    
    public void selectGender() {
    	gender.click();
    }
    
    public void custDOB(String dd,String mm,String yy) {
    	dob.sendKeys(dd);
    	dob.sendKeys(mm);
    	dob.sendKeys(yy);
    }
    
    public void custAddress(String caddress) {
    	txtaddress.sendKeys(caddress);
    }
    public void custCity(String c) {
    	txtcity.sendKeys(c);
    }
    public void custState(String s) {
    	txtstate.sendKeys(s);
    }
    public void cPincode( int p) {
    	custPincode.sendKeys(String.valueOf(p));
    }
    public void custTele(String t) {
    	custTelephone.sendKeys(t);
    }
    public void custEmail(String e) {
    	txtemail.sendKeys(e);
    }
    public void custpass(String pa) {
    	txtpass.sendKeys(pa);
    }
    public void clickSubmit() {
    	submitBtn.click();
    }



 
}