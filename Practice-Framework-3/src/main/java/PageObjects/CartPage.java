package PageObjects;

import ReUsableCode.AbstractClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends AbstractClass {
    WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    //check if the selected items are present inside the cart
    @FindBy(xpath = "//div[@class='cartSection']/h3")
    List<WebElement> elemetInsideCart;

    @FindBy(css = "div[class*='subtotal']  button[class*='btn']")
    WebElement button;

   /* public String[] assertSelectedItems(List<String> CheckCartItems) {
        String[] result = new String[2];
        // Concatenate the text content of all WebElements into a single string
        String cartText = elemetInsideCart.stream()
                .map(WebElement::getText)
                .collect(Collectors.joining());
        // Concatenate all items in NeedItems into a single string
       // List<String> needItems = itemsList();// items in arraylist
        String needItemsText = String.join("", CheckCartItems);
        result[0] = cartText;
        result[1] = needItemsText;
        return result;
    }*/
    public boolean asserSelectedItems(List<String> CheckCartItems) {
        // Check if all items in CheckCartItems are present in any of the WebElement texts
        return elemetInsideCart.stream()
                .map(WebElement::getText) // Extract text from each WebElement
                .anyMatch(text -> CheckCartItems.stream().anyMatch(text::contains)); // Check if any item is present

    }
    public boolean assertSelectedItem(List<String> CheckCartItems) {
        // Check if all items in CheckCartItems are present in any of the WebElement texts
        return elemetInsideCart.stream()
                .map(WebElement::getText) // Extract text from each WebElement
                .allMatch(text -> CheckCartItems.stream().anyMatch(text::contains));

    }
    public OrderPage  clickCheckoutButton()
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", button);
        js.executeScript("window.scrollBy(0,900)");
        OrderPage OP = new OrderPage(driver);
        return OP;
    }

}
