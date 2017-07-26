package parentTest;

import libs.Utils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.LoginPage;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by Asus on 17.07.2017.
 */
public class ParentTest {
    public WebDriver webDriver;
    public LoginPage loginPage;
    public HomePage homePage;
    public Utils utils;
    public Logger logger = Logger.getLogger(getClass());
    private String pathToScreenShot; // инициация создания переменной Пути к скриншоту


    public ParentTest() {

    }
    @Rule
    public TestName testName = new TestName();// для того чтобы получать имя каждого текущего теста

    @Before
    public void setUp(){
        File file = new File("");//создаем файл для того чтобы использовать его путь для создания нового файла скриншота
        pathToScreenShot = file.getAbsolutePath()
                +"\\Target\\ScreenShots"
                + this.getClass().getPackage().getName()+"\\"
                + this.getClass().getSimpleName()
                + "\\"+this.testName.getMethodName()+".jpg";
        File fileFF = new File(".././drivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", fileFF.getAbsolutePath());
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS );
        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);
        utils = new Utils();


    }
    @After
    public void tearDown(){
         if(!(webDriver == null)){ webDriver.quit();} // снимает скриншот при завершении теста и закрытии вебдрайвера
          //utils.screenShot(pathToScreenShot,webDriver);
    }

    /**
     * check Acceptance criteria;
     * @param message
     * @param actualResult
     * @param expectedResult
     */
    public void checkAC(String message, boolean actualResult, boolean expectedResult){

       // if(!( actualResult == expectedResult)){
          //  utils.screenShot(pathToScreenShot,webDriver);
        //}
        Assert.assertThat(message, actualResult,is(expectedResult));
    }
}