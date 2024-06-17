package ReUsableCode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class AbstractClass {
    WebDriver driver;
    WebDriverWait w;
    public AbstractClass(WebDriver driver) {
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }
    public void elementToLoad(By findBy)
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        w = new WebDriverWait(driver, Duration.ofSeconds(5));
        w.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
   // public List<String> itemsList(){
    //    return Arrays.asList("ZARA COAT 3", "ADIDAS ORIGINAL");
    //}
    public void errorToLoad(WebElement findBy)
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        w = new WebDriverWait(driver, Duration.ofSeconds(5));
        w.until(ExpectedConditions.visibilityOf(findBy));

    }

}
