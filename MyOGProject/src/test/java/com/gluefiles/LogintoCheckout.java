package com.gluefiles;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.filepath.Constant;
import com.filepath.ExcelUtil;
import com.filepath.Screenshot;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LogintoCheckout {

	public WebDriver d;
	public WebDriverWait w;
	
	String path=Constant.Path_Testdata+Constant.File_Testdata;
	String sheetname="sheet2";
	

//Start-scenario 1: Login to the website 
//------------------------------------------------------------------------------------	
	@Given("^Site is launched$")
	public void site_is_launched() throws Throwable {
		WebDriverManager.chromedriver().setup();
		d=new ChromeDriver();
		d.manage().window().maximize();
		d.manage().deleteAllCookies();
		d.get("https://www.olivegarden.com/home");
		d.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Given("^Login page is opened$")
	public void login_page_is_opened() throws Throwable {
		WebElement loginbt=d.findElement(By.xpath("//*[@id='header-login-link']/a"));
		loginbt.click();
		d.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
	}

	@When("^Enter username and password$")
	public void enter_username_and_password() throws Throwable {
		ExcelUtil.setExcelFile(path, sheetname);
		
		WebElement Inemail=d.findElement(By.xpath("//*[@id='emailid']"));
		Inemail.sendKeys(ExcelUtil.getCellData(1, 0)); 
		
		WebElement Inpass=d.findElement(By.xpath("//*[@id='password']"));
		Inpass.sendKeys(ExcelUtil.getCellData(1, 1));
		Inpass.sendKeys(Keys.ENTER);
		String name="Login";
		Screenshot.capture(d,name);
	}


	@Then("^Click on Login$")
	public void click_on_Login() throws Throwable {
		
		System.out.println("Loggedin Successfully");
		
	/*
		WebElement ButtonLogin=d.findElement(By.xpath("//*[contains(@id,'customerLoginId')]"));
		ButtonLogin.click();
		
		d.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	*/	
	}
	
//End-scenario 1: Logged into the website
//------------------------------------------------------------------------------------------------
//Start of Scenario 2: Checkout Functionality
	
	@Given("^Click Order Now$")
	public void click_Order_Now() throws Throwable {
		
		//d.navigate().refresh();
		WebElement noworder=d.findElement(By.xpath("//*[@href='#']"));
		noworder.click();
		String name="OrderNow";
		Screenshot.capture(d,name);
	}

	@Given("^Select order type as delivery$")
	public void select_order_type_as_delivery() throws Throwable {

		WebElement delivery=d.findElement(By.xpath("//*[@href='/delivery/catering-delivery-address']"));
		delivery.click();
	}

	@When("^Provide Delivery details$")
	public void provide_Delivery_details() throws Throwable {

		WebElement street=d.findElement(By.xpath("//*[@id='addressInput']"));
		street.clear();
		street.sendKeys(ExcelUtil.getCellData(1, 2));
		Thread.sleep(3000);
	//	w.until(ExpectedConditions.visibilityOf(d.findElement(By.xpath("//*[@id='geocoder']/div/div[1]"))));
		street.sendKeys(Keys.ENTER);
		
		WebElement apt=d.findElement(By.xpath("//*[@id='shippaddress2']"));
		apt.sendKeys(ExcelUtil.getCellData(1, 3));
		
		WebElement type=d.findElement(By.xpath("//*[@id='deliveryAddressType']"));
		Select dd=new Select(type);
		dd.selectByVisibleText("Private Residence (House, Apt)");
		
		WebElement continueBt=d.findElement(By.xpath("//*[@id='cont_sub']"));
		Actions act=new Actions(d);
		act.moveToElement(continueBt).build().perform();
		act.click(continueBt).build().perform();
		
		String name="Address";
		Screenshot.capture(d,name);
	}

	@When("^provide date and time$")
	public void provide_date_and_time() throws Throwable {
	    
		WebElement guests=d.findElement(By.xpath("//*[@id='partySize']")); //*[@id='partySize']
		guests.clear();
		guests.sendKeys(""+ExcelUtil.getnumericCell(1, 4));
		
		WebElement contdel=d.findElement(By.xpath("//*[@id='delivery_pickup_continue']"));
		Actions act=new Actions(d);
		act.moveToElement(contdel).build().perform();
		act.click(contdel).build().perform();
		
		String name="DateTime";
		Screenshot.capture(d,name);
	}

	@When("^Choose food$")
	public void choose_food() throws Throwable {
	    
		WebElement food=d.findElement(By.xpath("/html/body/main/section[3]/section/div/div/div/div[1]/div[1]/div/ul/li[1]/div/div[3]/span/div/a"));
		food.click();
		
		
	}

	@Then("^Click add to cart$")
	public void click_add_to_cart() throws Throwable {
		
		WebElement addtocart=d.findElement(By.xpath("//*[@id='popupAddToCart']"));
		addtocart.click();
		
		String name="addtocart";
		Screenshot.capture(d,name);
		
	}

	@Then("^view cart$")
	public void view_cart() throws Throwable {
	    
		WebElement viewcart=d.findElement(By.xpath("//*[@id='view-cart']"));
		viewcart.click();
	}
	
	@Then("^Checkout$")
	public void checkout() throws Throwable {
	    
		WebElement chk=d.findElement(By.xpath("//*[@id='drawer-panel']/div[3]/button[2]"));
		chk.click();
		
		WebElement nothnks=d.findElement(By.xpath("//*[@id='frmusercrosssell']/div[3]/div[1]/a"));
		nothnks.click();
		
		WebElement placeOrder=d.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/div/div[2]/div[1]/div/div/div[2]/div[5]/button"));
		Thread.sleep(5000);
		placeOrder.click();
		
		String name="placeorder";
		Screenshot.capture(d,name);
		
	}

}
