package com.assignment.bluestacks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import io.restassured.RestAssured;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Game {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo Pc\\Downloads\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://game.tv");
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1000)");
		List<WebElement> gameList = driver.findElements(By.cssSelector(".games-list>li>a"));
		int totalGames = gameList.size();
		ArrayList<String>[] table = new ArrayList[totalGames];
		for (int i = 0; i < 10; i++) {  // put i < totalGames
			int x = i+1;
			table[i] = new ArrayList<String>();
			WebElement btn = driver.findElement(By.cssSelector(".games-list>li:nth-of-type("+x+")"));
			btn.click();
			WebElement name = driver.findElement(By.xpath("//h2[@class='heading']"));
			WebElement tour = driver.findElement(By.cssSelector(".count-tournaments"));
			table[i].add(String.valueOf(x));
            table[i].add(name.getText());
    		table[i].add(driver.getCurrentUrl());
    		table[i].add(String.valueOf(RestAssured.get(driver.getCurrentUrl()).statusCode()));
    		table[i].add(tour.getText());
    		driver.navigate().back();
        }
		for (int i = 0; i < 10; i++) { // put i < totalGames
            for (int j = 0; j < table[i].size(); j++) { 
                System.out.print(table[i].get(j) + "    "); 
            } 
            System.out.println(); 
        } 
		driver.close();
	}
}
