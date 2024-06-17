package PageObjects;

import ReUsableCode.AbstractClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProductCatalog extends AbstractClass {
    WebDriver driver;
    List<WebElement> filterdItems;
    public ProductCatalog(WebDriver driver) {
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[@class='card-body']")
    List<WebElement> presentitems;

    By findBy = By.cssSelector("div[class*='mb-3']");
    public void FilterItems(List<String> NeedItems)
    {
       // List<String> NeedItems = itemsList();// items in arraylist
        elementToLoad(findBy);
        filterdItems = presentitems.stream().filter(p -> {
            String st = p.findElement(By.tagName("b")).getText();
            return NeedItems.stream().anyMatch(st::contains);
        }).collect(Collectors.toList());
        System.out.println(filterdItems.size());
    }
    public CartPage AddFilteredItemToCart() throws InterruptedException {
        for (WebElement element : filterdItems) {
            //element.findElement(By.cssSelector("button:last-of-type")).click();
            element.findElement(By.xpath("button[2]")).click();
            Thread.sleep(2000);
        }
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
        return new CartPage(driver);
    }





}
