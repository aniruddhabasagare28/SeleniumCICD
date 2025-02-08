package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import utilities.PropertyHandler;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {

    private static ThreadLocal<WebDriver> threadLocalDriver= new ThreadLocal<>();

    public static WebDriver initDriver(){
        String gridUrl=getGridUrl();
        if(!(gridUrl==null || gridUrl.isEmpty())){
            try {
                threadLocalDriver.set(new RemoteWebDriver(new URL(gridUrl),getChromeOptions()));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        threadLocalDriver.get().manage().window().maximize();
        return threadLocalDriver.get();
    }

    public static ChromeOptions getChromeOptions(){
        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--no-sandbox");
//        chromeOptions.addArguments("--disable-dev-shm-usage");
//        chromeOptions.addArguments("--headless");
        return chromeOptions;
    }

    private static String getGridUrl(){
        String gridUrl=null;
        String gridUrlProp=System.getProperty("selenium.gridurl", "http://localhost:4444/wd/hub");
        System.out.println("Running tests on Selenium Grid: " + gridUrl);
        if(gridUrlProp!=null && !gridUrlProp.isEmpty()){
            gridUrl =  String.format(gridUrlProp);
        }else{

            gridUrl = System.getProperty("selenium.gridurl");
        }
        return gridUrl;
    }

    public static WebDriver getDriver(){
        return threadLocalDriver.get();
    }

    public static void quitDriver(){
        threadLocalDriver.get().quit();
        threadLocalDriver.remove();
    }

}
