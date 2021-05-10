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
import com.filepath.ExcelUtil;
import com.graphbuilder.curve.Point;

public class GlueOlive {

	public WebDriver d;
	public WebDriverWait w;
	
	
	
	@Given("^launch Website$")
	public void launch_Website() throws Throwable {
		WebDriverManager.chromedriver().setup();
		d=new ChromeDriver();
		d.manage().window().maximize();
		d.manage().deleteAllCookies();
		d.get("https://www.olivegarden.com/home");
		d.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
	}

	@When("^Click Login$")
	public void click_Login() throws Throwable {
			WebElement loginbt=d.findElement(By.xpath("//*[@id='header-login-link']/a"));
			loginbt.click();
			Thread.sleep(200);
	}
	
	@When("^Click Register$")
	public void click_register() throws Throwable {

		WebElement register=d.findElement(By.xpath("//*[contains(@id,'customerLogoutId')]"));
		register.click();
	}

	@When("^Fill form$")
	public void fill_form() throws Throwable {

		String path=Constant.Path_Testdata+Constant.File_Testdata;
		String sheetname="sheet1";
		
		ExcelUtil.setExcelFile(path, sheetname);
		
		
		WebElement email=d.findElement(By.xpath("//*[@id='email-id']"));
		WebElement fname=d.findElement(By.xpath("//*[@id='fname']"));
		WebElement lname=d.findElement(By.xpath("//*[@id='lname']"));
		WebElement zipcode=d.findElement(By.xpath("//*[@id='zip']"));
		WebElement cont=d.findElement(By.xpath("//*[@id='phone-ctn']"));
		WebElement month=d.findElement(By.xpath("//*[@id='dobMonth']"));
		Select dm=new Select(month);
		WebElement day=d.findElement(By.xpath("//*[@id='dobDay']"));
		Select dd=new Select(day);
		WebElement year=d.findElement(By.xpath("//*[@id='dobYear']"));
		Select dy=new Select(year);
		WebElement pwd=d.findElement(By.xpath("//*[@id='pwd']"));
		
		int rowcnt=ExcelUtil.getRowCountInSheet();
		System.out.println(rowcnt);
		
		for(int i=0;i<=rowcnt;i++) {
			//int cellcount = wsheet.getRow(i).getLastCellNum();
			//System.out.println(cellcount);
				//temp=wsheet.getRow(i).getCell(1).getStringCellValue();
				
				if(i==0) {
					email.sendKeys(ExcelUtil.getCellData(i,1));
				}
				if(i==1) {
					fname.sendKeys(ExcelUtil.getCellData(i,1));
				}
				if(i==2) {
					lname.sendKeys(ExcelUtil.getCellData(i,1));
				}
				if(i==3) {
					//String x=ExcelUtil.getCellData(i,1);
					//double temp=Double.valueOf(x);	
					zipcode.sendKeys(""+ExcelUtil.getnumericCell(i,1));
				}
				if(i==4) {
					//double temp=Double.valueOf(ExcelUtil.getCellData(i,1));	
					cont.sendKeys(""+ExcelUtil.getnumericCell(i,1));
				}
				if(i==5) {
					dm.selectByVisibleText(ExcelUtil.getCellData(i,1));

				}
				if(i==6) {
					//double temp=Double.valueOf(ExcelUtil.getCellData(i,1));	
					dd.selectByVisibleText(""+ExcelUtil.getnumericCell(i,1));
					
	
				}
				if(i==7) {
					//double temp=Double.valueOf(ExcelUtil.getCellData(i,1));	
					dy.selectByVisibleText(""+ExcelUtil.getnumericCell(i,1));
				}
				if(i==8) {
					pwd.sendKeys(ExcelUtil.getCellData(i,1));
					d.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
		
		WebElement srcZip=d.findElement(By.xpath("//*[@id='searchText_overlay']"));
		srcZip.clear();
		srcZip.sendKeys(""+44718);
		WebElement src=d.findElement(By.xpath("//*[@id='preferred_searchIcon']"));
		src.click();
		Thread.sleep(200);
	
	//	/html/body/div[7]/div/div[3]/div[2]/div[1]/div[3]/div/div[3]/button
	//	/html/body/div[7]/div/div[3]/div[2]/div[1]/div[4]/div/div[3]/button
		
	WebElement chose=d.findElement(By.xpath("/html/body/div[8]/div/div[3]/div[2]/div[1]/div[6]/div/div[3]/button"));
	chose.click();
		
	
		Thread.sleep(2000);		
		
	}

	@Then("^Complete account$")
	public void complete_account() throws Throwable {
		
		WebElement complete=d.findElement(By.xpath("//*[@type='submit']"));
		//complete.click();
	
		
		Actions act=new Actions(d);
		act.moveToElement(complete).build().perform();
		act.click(complete).build().perform();

	}
//--------------------------------------------------------------------------------------------------	
}

