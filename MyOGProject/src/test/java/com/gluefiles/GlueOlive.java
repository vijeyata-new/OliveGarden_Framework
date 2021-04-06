package com.gluefiles;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import com.filepath.Constant;
import com.graphbuilder.curve.Point;

public class GlueOlive {

	public WebDriver d;
	public WebDriverWait w;
	
	public static Path currentRelativePath = Paths.get("");
	
	File f = new File(Constant.Path_Testdata+Constant.File_Testdata);
	FileInputStream fis;
	XSSFWorkbook wbook;
	XSSFSheet wsheet;
	
	@Given("^launch Website$")
	public void launch_Website() throws Throwable {
		WebDriverManager.chromedriver().setup();
		d=new ChromeDriver();
		d.manage().window().maximize();
		d.manage().deleteAllCookies();
		d.get("https://www.olivegarden.com/home");
		d.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
		Thread.sleep(200);
	}

	@When("^Click Login$")
	public void click_Login() throws Throwable {
			WebElement loginbt=d.findElement(By.xpath("//*[@id='header-login-link']/a"));
			loginbt.click();
			Thread.sleep(200);
	}
	
	@When("^Click Register$")
	public void click_register() throws Throwable {

		WebElement reg=d.findElement(By.id("customerLogoutId"));
		reg.click();
	}

	@When("^Fill form$")
	public void fill_form() throws Throwable {
		
		fis= new FileInputStream(f);
		wbook=new XSSFWorkbook(fis);
		wsheet=wbook.getSheet("Sheet1");
		
		WebElement email=d.findElement(By.id("email-id"));
		WebElement fname=d.findElement(By.id("fname"));
		WebElement lname=d.findElement(By.id("lname"));
		WebElement zipcode=d.findElement(By.id("zip"));
		WebElement cont=d.findElement(By.id("phone-ctn"));
		WebElement month=d.findElement(By.id("dobMonth"));
		Select dm=new Select(month);
		WebElement day=d.findElement(By.id("dobDay"));
		Select dd=new Select(day);
		WebElement year=d.findElement(By.id("dobYear"));
		Select dy=new Select(year);
		WebElement pwd=d.findElement(By.id("pwd"));
		
		String temp="";
		int rowcount = wsheet.getLastRowNum()-wsheet.getFirstRowNum();
		for(int i=0;i<=rowcount;i++) {
			int cellcount = wsheet.getRow(i).getLastCellNum();
			//System.out.println(cellcount);
				//temp=wsheet.getRow(i).getCell(1).getStringCellValue();
				
				if(i==0) {
					email.sendKeys(wsheet.getRow(i).getCell(1).getStringCellValue());
				}
				if(i==1) {
					fname.sendKeys(wsheet.getRow(i).getCell(1).getStringCellValue());
				}
				if(i==2) {
					lname.sendKeys(wsheet.getRow(i).getCell(1).getStringCellValue());
				}
				if(i==3) {
					zipcode.sendKeys(""+wsheet.getRow(i).getCell(1).getNumericCellValue());
				}
				if(i==4) {
					cont.sendKeys(""+wsheet.getRow(i).getCell(1).getNumericCellValue());
				}
				if(i==5) {
					dm.selectByVisibleText(wsheet.getRow(i).getCell(1).getStringCellValue());

				}
				if(i==6) {
					double num= wsheet.getRow(i).getCell(1).getNumericCellValue();
					int tmp=(int)num;
					dd.selectByVisibleText(""+tmp);
					//dd.selectByVisibleText();
	
				}
				if(i==7) {
					double num= wsheet.getRow(i).getCell(1).getNumericCellValue();
					int tmp=(int)num;
					dy.selectByVisibleText(""+tmp);
				}
				if(i==8) {
					pwd.sendKeys(wsheet.getRow(i).getCell(1).getStringCellValue());
				}
			}
	}

	@Then("^Find restaurant$")
	public void find_restaurant() throws Throwable {
		WebElement findresto = d.findElement(By.xpath("//*[@id='selectRestaurant']"));
		findresto.click();
	/*	
		try
		{
			w.until(ExpectedConditions.alertIsPresent());
			d.switchTo().alert();
		}
		catch(Exception e) 
		{
			throw(e);
		}
		//d.switchTo().alert();
		//myRestaurantModal*/
		
		WebElement srcZip=d.findElement(By.id("searchText_overlay"));
		srcZip.clear();
		srcZip.sendKeys(""+44718);
		WebElement src=d.findElement(By.id("preferred_searchIcon"));
		src.click();
		Thread.sleep(200);
	//	List<WebElement> srcRes=d.findElements(By.className("casual_span_overlay"));

	//	System.out.println(srcRes.size());
	//	System.out.println(srcRes.get(2));  
	//	/html/body/div[7]/div/div[3]/div[2]/div[1]/div[3]/div/div[3]/button
	//	/html/body/div[7]/div/div[3]/div[2]/div[1]/div[4]/div/div[3]/button
		
	WebElement chose=d.findElement(By.xpath("/html/body/div[7]/div/div[3]/div[2]/div[1]/div[3]/div/div[3]/button"));
	chose.click();
		
	
		Thread.sleep(2000);		
	}

	@Then("^Complete account$")
	public void complete_account() throws Throwable {
		WebElement regi=d.findElement(By.className("primary-btn"));
		
	/*	Actions act=new Actions(d);
		act.moveToElement(regi).build().perform();
		act.click(regi).build().perform();*/
		//w.until(ExpectedConditions.elementToBeClickable(regi)).click();
		
		regi.submit();
	}

}

