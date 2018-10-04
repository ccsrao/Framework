package com.utilities;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.tools.zip.ZipEntry;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utilities {

	// Capture Screen Shot and save in the location
	public static String captureScreenshot(WebDriver driver, String screenShotName) {
		String path = "";
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		Date dt = new Date();
		String html = "";
		try {
			System.out.println(dateFormat.format(dt));
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			html = covertScreenshotToBase64(source , screenShotName);
			path = System.getProperty("user.dir") + File.separator + "screenshots";
			createDirectory(path);
			FileUtils.copyFile(source, new File(path + File.separator  + dateFormat.format(dt) + "_" + screenShotName + ".png"));
			System.out.println("screenshot is taken");

		} catch (Exception e) {
			System.out.println("exception while taking screenshot" + e.getMessage());
		}
		
		return html;
	}

	
	@SuppressWarnings("resource")
	public static String covertScreenshotToBase64(File file, String name) {
		try {		
		FileInputStream fis = new FileInputStream(file);
		byte byteArray[] = new byte[(int)file.length()];
		fis.read(byteArray);
		String imageString = Base64.encodeBase64String(byteArray);
		return doImageClickAnimation(imageString, name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String doImageClickAnimation(String img, String screenName) {
		
		String image = "<img onclick='clickImage(this)' src=\"data:image/png;base64, " + img + "\" alt=\""+ screenName +"\" width=\"500\" height=\"250\"/>";
		return image;
		
	}
	
	// make zip of reports
	public static void zip(String filepath) {
		try {
			File inFolder = new File(filepath);
			File outFolder = new File("Reports.zip");
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFolder)));
			BufferedInputStream in = null;
			byte[] data = new byte[1000];
			String files[] = inFolder.list();
			for (int i = 0; i < files.length; i++) {
				in = new BufferedInputStream(new FileInputStream(inFolder.getPath() + File.separator + files[i]), 1000);
				out.putNextEntry(new ZipEntry(files[i]));
				int count;
				while ((count = in.read(data, 0, 1000)) != -1) {
					out.write(data, 0, count);
				}
				out.closeEntry();
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean createDirectory(String directory) {
		File fileDirectory = new File(directory);
		if (!fileDirectory.exists()) {
			fileDirectory.mkdir();
			return true;
		}
		return false;
	}

}