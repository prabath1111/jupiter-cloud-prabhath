package com.jupiter.pages;

import org.openqa.selenium.By;

import com.jupiter.commands.CommandsRepository;

public class Page_Contact extends CommandsRepository{

	By btn_Submit = By.xpath("//a[text()='Submit']");

	By ele_lblForeNameError = By.xpath("//span[@id='forename-err']");

	By ele_lblEmailError = By.xpath("//span[@id='email-err']");

	By ele_lblMessageError = By.xpath("//span[@id='message-err']");
	
	By ele_lblMessageSuccess = By.xpath("//div[@class='alert alert-success']");
	
	By tf_ForeName = By.xpath("//input[@id='forename']");
	
	By tf_Email = By.xpath("//input[@id='email']");
	
	By tf_Message = By.xpath("//textarea[@id='message']");

	public void validateMandatoryFieldsError() {

		waitTillElementLoad(btn_Submit, 10);
		clickOnSubmitButton();
		isElementPresent(ele_lblForeNameError, true, 30);
		isElementPresent(ele_lblEmailError, true, 30);
		isElementPresent(ele_lblMessageError, true, 30);
	}

	public void populateMandatoryFields(String foreName, String email, String message) {

		type(tf_ForeName, foreName);
		type(tf_Email, email);
		type(tf_Message, message);
	}

	public void validateSuccessFeedbackMessage() {
		waitTillElementLoad(ele_lblMessageSuccess, 30);
		isElementPresent(ele_lblMessageSuccess, true, 30);
	}
	
	public void validateErrorMessageIsGone() {

		isElementPresent(ele_lblForeNameError, false, 10);
		isElementPresent(ele_lblEmailError, false, 10);
		isElementPresent(ele_lblMessageError, false, 10);
	}
	
	public void clickOnSubmitButton() {
		click(btn_Submit);
	}
}
