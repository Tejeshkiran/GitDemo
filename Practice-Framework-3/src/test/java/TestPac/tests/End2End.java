package TestPac.tests;

import PageObjects.CartPage;
import PageObjects.OrderPage;
import PageObjects.ProductCatalog;
import TestPac.testcomponents.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class End2End extends BaseTest {
       // WebDriver driver;
        @Test(dataProvider = "getData", groups = {"purchase"})
        public void SubmitOrder(HashMap<String, String> input ) throws InterruptedException, IOException {
            //perform action on login page
            ProductCatalog PC = Lpage.loginAction(input.get("email"),input.get("password"));
            //add the items(ZARA COAT 3", "ADIDAS ORIGINAL) to cart
            List<String> NeedItems = Arrays.asList("ZARA COAT 3", "ADIDAS ORIGINAL","IPHONE 13 PRO");
            PC.FilterItems(NeedItems);// to filter the items inside the productCatalog page as per NeedItems
            CartPage CP = PC.AddFilteredItemToCart();//click add to cart on filtered items and click on cart
            //check if the selected items are present inside the cart
            List<String> CheckCartItems = Arrays.asList("ADIDAS ORIGINAL");
            boolean val =CP.asserSelectedItems(CheckCartItems);
            Assert.assertTrue(val);

            OrderPage OP = CP.clickCheckoutButton();// creating object in CartPage and passing the object reffrence
            //SelectCountry
            OP.SelectTheContry("India");
            OP.ClickOrderButton();
        }

        /*@DataProvider
        public Object[][] getData()
        {
           return new Object[][] {{"bheeshmacharya@gmail.com","Bheeshma@123"},{"Karna@gmail.com","Bheeshma@123"}};
        }*/
        @DataProvider
        public  Object[][] getData() throws IOException {
          /*  HashMap<String, String> map1 = new HashMap<String, String>();
            map1.put("email", "bheeshmacharya@gmail.com");
            map1.put("password", "Bheeshma@123");
            HashMap<String, String> map2 = new HashMap<String, String>();
            map2.put("email", "Karna@gmail.com");
            map2.put("password", "Bheeshma@123");
            return new Object[][] {{map1},{map2}};*/

            List<HashMap<String,String>> data =
                    dataReader(System.getProperty("user.dir")+
                            "//src//main//java//Datas//purchaseData.json");
            return new Object[][] {{data.get(0)},{data.get(1)}};
        }


    }



