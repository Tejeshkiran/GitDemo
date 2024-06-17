package TestPac.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class E2E {
    public static void main(String[] args) throws InterruptedException {
        JavascriptExecutor js;
        WebDriver driver  = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys("bheeshmacharya@gmail.com");
        driver.findElement(By.xpath("//input[@formcontrolname='userPassword']")).
                sendKeys("Bheeshma@123");
        driver.findElement(By.cssSelector("input[name='login']")).click();
        //add the items(ZARA COAT 3", "ADIDAS ORIGINAL) to cart
        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class*='mb-3']")));
        List<String> Needitems = Arrays.asList("ZARA COAT 3", "ADIDAS ORIGINAL");
        List<WebElement> presentitems = driver.findElements(By.xpath("//div[@class='card-body']"));
        List<WebElement> filterdItems = presentitems.stream().filter(p->{
            String st = p.findElement(By.tagName("b")).getText();
            return Needitems.stream().anyMatch(st::contains);}).collect(Collectors.toList());
        System.out.println(filterdItems.size());
        for(WebElement element : filterdItems)
        {
            //element.findElement(By.cssSelector("button:last-of-type")).click();
            element.findElement(By.xpath("button[2]")).click();
            Thread.sleep(2000);
        }
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
        //check if the selected items are present inside the cart
        List<WebElement> elemetInsideCart = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));

// Concatenate the text content of all WebElements into a single string
        String cartText = elemetInsideCart.stream()
                .map(WebElement::getText)
                .collect(Collectors.joining());

// Concatenate all items in Needitems into a single string
        String needItemsText = String.join("", Needitems);

// Assert that both concatenated strings are equal
        Assert.assertEquals(needItemsText, cartText);
        WebElement button =driver.findElement(By.cssSelector("div[class*='subtotal']  button[class*='btn']"));
        js =(JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", button);
        js.executeScript("window.scrollBy(0,900)");
        //js.executeScript("window.scrollBy(0,1300)");
        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Select Country']")));
        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).click();
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"India").build().perform();
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
        WebElement button1 =driver.findElement(By.cssSelector(".action__submit"));
        //JavascriptExecutor js1 = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();",button1);


    }
}
