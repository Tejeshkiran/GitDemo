package PageObjects;

import ReUsableCode.AbstractClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OrderPage extends AbstractClass {
    WebDriver driver;
    JavascriptExecutor js;
    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver =driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement CountrySelector;

    By findby = By.cssSelector("input[placeholder='Select Country']");

    @FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
    WebElement SelectedCountry;

    @FindBy(css = ".action__submit")
    WebElement button1;


    public void SelectTheContry(String country)
    {
        elementToLoad(findby);
        CountrySelector.click();
        Actions a = new Actions(driver);
        a.sendKeys(CountrySelector, country).build().perform();
        SelectedCountry.click();
    }
    public void ClickOrderButton()
    {
        js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", button1);

    }

}
