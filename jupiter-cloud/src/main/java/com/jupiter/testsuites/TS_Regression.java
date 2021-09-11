package com.jupiter.testsuites;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jupiter.rutime.ExecutionConfig;
import com.jupiter.utils.cons.DataConstants;
import com.jupiter.utils.cons.PageConstants;

public class TS_Regression extends ExecutionConfig{

	@Test(dataProvider = "tc_001")
	public void tc_001(final String foreName, final String Email, final String Message) {
		
		PageConstants.page_Home.OpenApplication();
		PageConstants.page_Home.navigateToContactPage();
		PageConstants.page_Contact.validateMandatoryFieldsError();
		PageConstants.page_Contact.populateMandatoryFields(foreName, Email, Message);
		PageConstants.page_Contact.validateErrorMessageIsGone();
	}
	
	 /**
     * Data provider for Test case tc_001.
     * @return data table
     */
    @DataProvider(name = "tc_001")
    public Object[][] dataTable_tc_001() {     	
    	
    	return DataConstants.dataUtils.getData("tc_001");
    }
    
    /**
     * 
     * Test Method tc_002
     * 
     * @param foreName
     * @param Email
     * @param Message
     */
    @Test(dataProvider = "tc_002")
	public void tc_002(final String foreName, final String Email, final String Message) {
		
    	PageConstants.page_Home.OpenApplication();
		PageConstants.page_Home.navigateToContactPage();
		PageConstants.page_Contact.validateMandatoryFieldsError();
		PageConstants.page_Contact.populateMandatoryFields(foreName, Email, Message);
		PageConstants.page_Contact.clickOnSubmitButton();
		PageConstants.page_Contact.validateSuccessFeedbackMessage();
	}
	
	 /**
     * Data provider for Test case tc_002.
     * @return data table
     */
    @DataProvider(name = "tc_002")
    public Object[][] dataTable_tc_002() {     	
    	
    	return DataConstants.dataUtils.getData("tc_002");
    }
    
    /**
     * 
     * Test Method tc_003
     * 
     * @param foreName
     * @param Email
     * @param Message
     */
    @Test(dataProvider = "tc_003")
	public void tc_003(final String items) {
		
    	PageConstants.page_Home.OpenApplication();
		PageConstants.page_Home.navigateToShopPage();
		PageConstants.page_Shop.buyProducts(items);
		PageConstants.page_Home.navigateToCartPage();
		PageConstants.page_Cart.verifyCartItems(items);
	}
	
	 /**
     * Data provider for Test case tc_003.
     * @return data table
     */
    @DataProvider(name = "tc_003")
    public Object[][] dataTable_tc_003() {     	
    	
    	return DataConstants.dataUtils.getData("tc_003");
    }
    
    /**
     * 
     * Test Method tc_004
     * 
     * @param foreName
     * @param Email
     * @param Message
     */
    @Test(dataProvider = "tc_004")
	public void tc_004(final String tcName, final String items) {
		
    	PageConstants.page_Home.OpenApplication();
		PageConstants.page_Home.navigateToShopPage();
		PageConstants.page_Shop.storePriceByItems(tcName, items);
		PageConstants.page_Shop.buyProducts(items);
		PageConstants.page_Home.navigateToCartPage();
		PageConstants.page_Cart.verifyCartItemsSubTotal(tcName, items);
	}
	
	 /**
     * Data provider for Test case tc_004.
     * @return data table
     */
    @DataProvider(name = "tc_004")
    public Object[][] dataTable_tc_004() {     	
    	
    	return DataConstants.dataUtils.getData("tc_004");
    }
    
}
