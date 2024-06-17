package PageObjects;

import ReUsableCode.AbstractClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.PageFactoryFinder;

import javax.swing.text.html.CSS;
import javax.xml.xpath.XPath;

public class LoginPage extends AbstractClass {

    WebDriver driver;
    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "userEmail")
    WebElement userName;
    @FindBy(xpath="//input[@formcontrolname='userPassword']")
    WebElement password;
    @FindBy(css="input[name='login']")
    WebElement loginbutton;

    //div[@aria-label='Incorrect email or password.']
    //.ng-tns-c4-12.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
    @FindBy(css = "[class*='flyInOut']")
    WebElement ErrorElement;
    By findBy = By.xpath("//input[@formcontrolname='userPassword']");
    public void Url(String url)
    {
        driver.get(url);
    }
    public ProductCatalog loginAction(String user, String pass)
    {
        userName.sendKeys(user);
        elementToLoad(findBy);
        password.sendKeys(pass);
        loginbutton.click();
        return new ProductCatalog(driver);

    }
    public String getTheError()
    {
         errorToLoad(ErrorElement);
         return ErrorElement.getText();
    }


}
