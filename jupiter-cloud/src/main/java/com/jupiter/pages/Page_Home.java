
package com.jupiter.pages;

import org.openqa.selenium.By;

import com.jupiter.commands.CommandsRepository;

public class Page_Home extends CommandsRepository {

	By ele_lblNavContact = By.xpath("//li[@id='nav-contact']/a");

	By ele_lblNavShop = By.xpath("//li[@id='nav-shop']/a");

	By ele_lblNavCart = By.xpath("//li[@id='nav-cart']/a");

	public void OpenApplication() {

		openBrowser(2000);
	}

	public void navigateToContactPage() {

		click(ele_lblNavContact);
	}

	public void navigateToShopPage() {

		click(ele_lblNavShop);
	}

	public void navigateToCartPage() {

		click(ele_lblNavCart);
	}
}
