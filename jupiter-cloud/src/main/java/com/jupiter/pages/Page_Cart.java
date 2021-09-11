package com.jupiter.pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;

import com.jupiter.commands.CommandsRepository;

public class Page_Cart extends CommandsRepository{

	By ele_lblCartItemWithQty = null;
	
	public void verifyCartItems(String items) {

		List<String> itemsArray = Arrays.asList(items.split(","));
		for (String itemWithQty : itemsArray) {

			String itm = itemWithQty.split("=")[0];
			int qtyInt = Integer.parseInt(itemWithQty.split("=")[1]);
			isElementPresent(getElementItemCartQty(itm, qtyInt), true, 50);
		}

	}
	
	public void verifyCartItemsSubTotal(String tcName, String items) {

		List<String> itemsArray = Arrays.asList(items.split(","));
		for (String itemWithQty : itemsArray) {

			String itm = itemWithQty.split("=")[0];
			int qtyInt = Integer.parseInt(itemWithQty.split("=")[1]);
			String itemActualPrice = retrieveValue(tcName + "_" + itm.replace(" ", ""));

			double expectedValue = Double.parseDouble(itemActualPrice) * qtyInt;
			isElementPresent(getElementItemSubTotal(itm, expectedValue), true, 50);

		}

	}

	private By getElementItemCartQty(String itemName, int quantity) {

		String xpathItem = "//td[contains(text(),'"+itemName+"')]/following-sibling::td/input[@value='"+quantity+"']";
		return By.xpath(xpathItem);
	}
	
	private By getElementItemSubTotal(String itemName, double expectedPrice) {

		String xpathItem = "//td[contains(text(),'"+itemName+"')]/following-sibling::td[text()='$"+expectedPrice+"']";
		return By.xpath(xpathItem);
	}
	
}
