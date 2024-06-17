package TestPac.testcomponents;

import PageObjects.LoginPage;
import PageObjects.ProductCatalog;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.Dimension;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LoginPage Lpage;
    public ProductCatalog PC;
    public WebDriver initializer() throws IOException {

    //Gobledata.properties class
        Properties prop = new Properties();
        FileInputStream fileinput = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//GlobleProperties//Resources//Gobledata.properties");
        prop.load(fileinput);
        String BrowserName = System.getProperty("Browser")!=null ? System.getProperty("Browser") :prop.getProperty("Browser");

        if(BrowserName.contains("Chrome"))
        {
            ChromeOptions options = new ChromeOptions();
            if (BrowserName.contains("headless"))
            {
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1400,900));
        } else if (BrowserName.contains("Edge")) {
            EdgeOptions option = new EdgeOptions();
            if (BrowserName.contains("headless"))
            {
                option.addArguments("headless");
            }
             driver = new EdgeDriver(option);
             driver.manage().window().setSize(new Dimension(1400,900));
        } else if (BrowserName.equalsIgnoreCase("FireFox")) {
             driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;

    }

    public List<HashMap<String,String>> dataReader(String filePath) throws IOException {
        //read Json to string
        String jsonContent = FileUtils.readFileToString(new File(filePath));
        //convert string to list of hasmap -> Jackson DataBind
        ObjectMapper mapper  = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
        return data;
    }
    public String getScreenshot(String TestcaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts =(TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File destination  =new File(System.getProperty("user.dir")+"//reportes//"+TestcaseName+".png");
        FileUtils.copyFile(source, destination);
        return System.getProperty("user.dir")+"//reportes//"+TestcaseName+".png";
    }

    @BeforeMethod(alwaysRun = true)
    public LoginPage loginPage() throws IOException {
        driver=initializer();
        Lpage = new LoginPage(driver);
        Lpage.Url("https://rahulshettyacademy.com/client");
        return Lpage;

    }
}
