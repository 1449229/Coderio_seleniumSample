package com.coderio.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PurchasePage extends Base{

	//Log
	By loginLinkLocator = By.id("login2");
	By loginPageLocator = By.cssSelector("h5.modal-title[id='logInModalLabel']");
	By loginUserLocator = By.id("loginusername");
	By loginPWLocator = By.id("loginpassword");
	By confirmLoginBtnLocator = By.cssSelector("button[onclick='logIn()']");
	By userLoggedLocator = By.id("nameofuser");
	//Add product locators
	By cartLocator = By.id("cartur");
	By addCartLocator = By.cssSelector("a[class='btn btn-success btn-lg']");
	By randomPhoneLocator= By.xpath("//*[@href='prod.html?idp_=1'][@class='hrefch']"); //i.e: Samsung Galaxy s6
	//Order locators
	By placeOrderLocator = By.cssSelector("button[class='btn btn-success']");
	By orderPageLocator = By.cssSelector("h5.modal-title[id='orderModalLabel']");
	By orderNameLocator = By.cssSelector("input.form-control[id='name']");
	By orderCountryLocator = By.cssSelector("input.form-control[id='country']");
	By orderCityLocator = By.cssSelector("input.form-control[id='city']");
	By orderCCLocator = By.cssSelector("input.form-control[id='card']");
	By orderMonthLocator = By.cssSelector("input.form-control[id='month']");
	By orderYearLocator = By.cssSelector("input.form-control[id='year']");
	By buttonPurchaseLocator = By.cssSelector("button[onclick='purchaseOrder()']");
	By purchaseOKLocator = By.cssSelector("div[class='sa-icon sa-success animate']");

	public PurchasePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void logIn() throws InterruptedException {
		click(loginLinkLocator);
		Thread.sleep(2000);
		type("seleniumQA",loginUserLocator);
		type("seleniumQA",loginPWLocator);
		click(confirmLoginBtnLocator);
		Thread.sleep(2000);
	}
	
	public void addCart() throws InterruptedException {
		click(randomPhoneLocator);
		Thread.sleep(2000);
			if(isDisplayed(addCartLocator)) {
				click(addCartLocator);
				Thread.sleep(2000);
			}
			else {
				System.out.print("Fallo busqueda producto.");
			}
		}
	
	public void purchaseCart() throws InterruptedException {
		click(cartLocator);
		Thread.sleep(2000);
		click(placeOrderLocator);
		Thread.sleep(2000);
		
		if(isDisplayed(orderPageLocator)) {
			type("testName",orderNameLocator);
			type("Argentina",orderCountryLocator);
			type("CABA",orderCityLocator);
			type("12345678",orderCCLocator);
			type("12",orderMonthLocator);
			type("2022",orderYearLocator);
			click(buttonPurchaseLocator);
			Thread.sleep(2000);		
		}
		else {
			System.out.print("Fallo confirmar pedido: elemento no encontrado.");
		}

	}
	
	public void purchaseCart(String name, String country, String city, int creditCard, int month, int year) throws InterruptedException {
		click(cartLocator);
		Thread.sleep(2000);
		click(placeOrderLocator);
		Thread.sleep(2000);
		
		if(isDisplayed(orderPageLocator)) {
			type(name,orderNameLocator);
			type(country,orderCountryLocator);
			type(city,orderCityLocator);
			type(creditCard,orderCCLocator);
			type(month,orderMonthLocator);
			type(year,orderYearLocator);
			click(buttonPurchaseLocator);
			Thread.sleep(2000);		
		}
		else {
			System.out.print("Fallo confirmar pedido: elemento no encontrado.");
		}

	}
	public boolean purchaseOkDisplayed() {
		return isDisplayed(purchaseOKLocator);
	}

	public String alertActive() throws InterruptedException {
		String mensaje= alertMessage();
		alertAccept();
		Thread.sleep(500);
		return mensaje;
	}

	
	public String isLogged() {
		return getText(userLoggedLocator);
	}
	

}
