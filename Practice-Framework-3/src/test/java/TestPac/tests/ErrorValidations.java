package TestPac.tests;

import PageObjects.CartPage;
import PageObjects.ProductCatalog;
import TestPac.testcomponents.BaseTest;
import TestPac.testcomponents.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class ErrorValidations extends BaseTest {

    public List<String> NeedItems;
    public CartPage CP;

    @Test(groups = {"loginerror"} ,retryAnalyzer = Retry.class)
    public void LoginerrorTest()
    {
        PC =Lpage.loginAction("bheeshmacharya@gmail.com","Bheeshm@123");
        String Errormessage = Lpage.getTheError();
        Assert.assertEquals("Incorrect email or password.",Errormessage);
    }

    @Test(dependsOnMethods = {"LoginerrorTest"})
    public void CartErrorTest() throws InterruptedException {

        ProductCatalog PC = Lpage.loginAction("bheeshmacharya@gmail.com","Bheeshma@123");
        //add the items(ZARA COAT 3", "ADIDAS ORIGINAL) to cart
        List<String> NeedItems = Arrays.asList("ZARA COAT 3", "ADIDAS ORIGINAL", "IPHONE 13 PRO");
        PC.FilterItems(NeedItems);
        CartPage CP = PC.AddFilteredItemToCart();
        //check if the selected items are present inside the cart
        List<String> CheckCartItems = Arrays.asList("ZARA COAT 3","ADIDAS ORIGINAL");
        boolean val =CP.asserSelectedItems(CheckCartItems);
        Assert.assertTrue(val);
    }
    @Test(groups = {"purchase"})
    public void AllCartErrorTest() throws InterruptedException {

        ProductCatalog PC = Lpage.loginAction("bheeshmacharya@gmail.com","Bheeshma@123");
        //add the items(ZARA COAT 3", "ADIDAS ORIGINAL) to cart
        List<String> NeedItems = Arrays.asList("ZARA COAT 3", "ADIDAS ORIGINAL");
        PC.FilterItems(NeedItems);
        CP = PC.AddFilteredItemToCart();
        //check if all the selected items are present inside the cart
        List<String> CheckCartItems = Arrays.asList("ZARA COAT 3","ADIDAS ORIGINAL");
        boolean val =CP.assertSelectedItem(CheckCartItems);
        Assert.assertTrue(val);
    }
}
//asserSelectedItems(String Item)