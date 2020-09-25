package com.coderio.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInOutPage extends Base{
	
	By loginLinkLocator = By.id("login2");
	By loginPageLocator = By.cssSelector("h5.modal-title[id='logInModalLabel']");
	By loginUserLocator = By.id("loginusername");
	By loginPWLocator = By.id("loginpassword");
	By confirmLoginBtnLocator = 
			By.cssSelector("button[onclick='logIn()']");
	By userLoggedLocator = By.id("nameofuser");
	By logOutBtnLocator =  By.id("logout2");


	public LogInOutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void loginUser() throws InterruptedException {
		click(loginLinkLocator);
		Thread.sleep(2000);
		if(isDisplayed(loginPageLocator)) {
			type("seleniumQA",loginUserLocator);
			type("seleniumQA",loginPWLocator);
			click(confirmLoginBtnLocator);
			Thread.sleep(2000);

		}
		else {
			System.out.println("Fallo login: Pagina de login no encontrado.");
		}
	}
	
	public void loginUser(String user, String password) throws InterruptedException {
		click(loginLinkLocator);
		Thread.sleep(2000);
		if(isDisplayed(loginPageLocator)) {
			type(user,loginUserLocator);
			type(password,loginPWLocator);
			click(confirmLoginBtnLocator);
			Thread.sleep(2000);

		}
		else {
			System.out.println("Fallo login: Pagina de login no encontrado.");
		}
	}
	
	public void logOutUser() throws InterruptedException {

		if(isDisplayed(logOutBtnLocator)) {
			click(logOutBtnLocator);
			Thread.sleep(2000);
		
		}
		else {
			System.out.print("Fallo logout: No se encuentra el elemento.");
		}
	}
	
	public String isLogged() {
		return getText(userLoggedLocator);
	}
	
	
}
