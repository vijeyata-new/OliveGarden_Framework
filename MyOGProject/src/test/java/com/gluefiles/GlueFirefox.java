package com.gluefiles;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class GlueFirefox {

public WebDriver d;

	@Given("^launch Website on firefox$")
	public void launch_Website_on_firefox() throws Throwable {
		WebDriverManager.firefoxdriver().setup();
		d=new FirefoxDriver();
		d.manage().window().maximize();
		d.manage().deleteAllCookies();
		d.get("https://www.olivegarden.com/home");
		d.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
		Thread.sleep(200);
	}

	@When("^Enter Email id$")
	public void enter_Email_id() throws Throwable {
		WebElement email=d.findElement(By.id("emailid"));
		email.sendKeys("vijeyata.thorat@gmail.com");
	}

	@When("^Enter Password$")
	public void enter_Password() throws Throwable {
	    WebElement pass=d.findElement(By.id("password"));
	    pass.sendKeys("Vijeyata@123");
	}

	@Then("^Click on Login Button$")
	public void click_on_Login_Button() throws Throwable {
	    WebElement login=d.findElement(By.id("customerLoginId"));
	    login.click();
	}

	@Then("^Login should be successful$")
	public void login_should_be_successful() throws Throwable {
	    System.out.println("Login Successful");
	}
	

}
