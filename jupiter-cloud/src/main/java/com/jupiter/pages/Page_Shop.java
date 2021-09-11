package com.jupiter.pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;

import com.jupiter.commands.CommandsRepository;

public class Page_Shop extends CommandsRepository{

	By ele_lblNavShop = By.xpath("//li[@id='nav-shop\"");

	By btn_item = null;

	public void buyProducts(String items) {

		List<String> itemsArray = Arrays.asList(items.split(","));
		for (String itemWithQty : itemsArray) {

			String itm = itemWithQty.split("=")[0];
			String qtyString = itemWithQty.split("=")[1];

			for (int i = 0; i < Integer.parseInt(qtyString); i++) {
				click(getElementItemBuyNow(itm));
			}
		}

	}
	
	public void storePriceByItems(String tcName, String items) {
			
		List<String> itemsArray = Arrays.asList(items.split(","));
		for (String itemWithQty : itemsArray) {

			String itm = itemWithQty.split("=")[0];

			String actualPrice = getElementString(getElementItemPrice(itm), 50);
			StoreValue(tcName + "_" + itm.replace(" ", ""), actualPrice.replace("$", ""));
		}
		
		
	}

	private By getElementItemBuyNow(String itemName) {

		String xpathItem = "//h4[text()='" + itemName + "']/..//a";
		return By.xpath(xpathItem);
	}
	
	private By getElementItemPrice(String itemName) {

		String xpathItem = "//h4[text()='"+itemName+"']/..//span";
		return By.xpath(xpathItem);
	}
	
}
