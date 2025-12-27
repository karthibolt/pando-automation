package BasePackage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;


public class Base {

    //singleton base
    private static WebDriver driver;

    private Base(){

    }


    public static WebDriver getInstance (){

        if(driver == null){
            WebDriverManager.chromedriver().setup();

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_setting_values.notifications", 2);

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            driver = new ChromeDriver(options);
            //driver.manage().window().maximize();
        }
        return driver;
    }

    public static  void quitDriver (){

        if(driver != null){

            driver.quit();
            driver = null;
        }
    }
}
