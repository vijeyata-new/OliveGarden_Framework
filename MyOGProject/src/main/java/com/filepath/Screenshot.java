package com.filepath;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.ashot.AShot;

import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Screenshot {

	public static void capture(WebDriver driver,String filename) throws IOException, InterruptedException {
		
		File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    File dest = new File("E:\\" + filename+".jpeg");
	    FileUtils.copyFile(scr, dest);
	    System.out.println("Screenshot taken successfully. Page: "+filename);
	}	
}
