package automation.practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import static automation.practice.CommonUtils.*;
import java.util.Locale;

public class AutomationEndToEnd {

    final public static String APP_URL = "http://automationpractice.com/index.php";

    public static void main(String[] args) {


        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        driver.get(APP_URL);


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).
                executeScript("return document.readyState").equals("complete"));


        String title = driver.getTitle();

        if (title.equals("My Store")) {
            System.out.println("Title is correct");
        } else {
            System.out.println("Title is not correct");
        }

        WebElement signInLink = driver.findElement(By.linkText("Sign in"));

        if (signInLink.isDisplayed()) {
            signInLink.click();
            System.out.println("Clicking sign in link, because it exists");
        } else {
            System.out.println("Sign in link does not exist on the page");
        }


        String emailAddress = randomEmail();
        System.out.println("Random email was generated" + emailAddress);

        WebElement newUserEmailInput = driver.findElement(By.id("email_create"));
        newUserEmailInput.clear();
        newUserEmailInput.sendKeys(emailAddress);


        WebElement createAccountBtn = driver.findElement(By.id("SubmitCreate"));
        if (createAccountBtn.isEnabled()) {
            createAccountBtn.click();
            System.out.println("Create account button is clicked");
        } else {
            System.out.println("Create account button isn't clicked");
        }

        title = driver.getTitle();
        if (title.equals("Login - My Store")) {
            System.out.println("Title is correct");
        } else {
            System.out.println("Title is not correct");
        }

        WebElement mainHeader = driver.findElement(By.className("page-subheading"));
        if (mainHeader.getText().equals("CREATE AN ACCOUNT")) {
            System.out.println("Main header is correct");
        } else {
            System.out.println("Main header is not correct");
        }

        int randomGender = (int) (Math.random() * (2 - 0) + 0); // I can also call the Method by its class so[ int randomGander = CommonUtils.randomNumber(1,2);]
        WebElement genderRadioBtn = driver.findElement(By.xpath("//input[@id='id_gender" + randomGender + "']"));
        //click mr or mrs randomly
        genderRadioBtn.click();


        int atSign = emailAddress.indexOf("@");
        String[] fullName = emailAddress.substring(0, atSign).split("\\.");
        String firstName = fullName[1].substring(0, 1).toLowerCase() + fullName[1].substring(1);
        String lastName = fullName[0].substring(0, 1).toLowerCase() + fullName[1].substring(1);
        String password = lastName + "124" + firstName.charAt(0) + "!";

        WebElement inputCustomerFirstNM = driver.findElement(By.id("customer_firstname"));
        inputCustomerFirstNM.sendKeys(firstName);

        WebElement inputCustomerLastNM = driver.findElement(By.id("customer_lastname"));
        inputCustomerLastNM.sendKeys(lastName);

        WebElement emailInputRegistration = driver.findElement(By.id("email"));
        emailInputRegistration.getAttribute("value");

        //compare the randomly generated  email with populated email on the register page
        if (emailInputRegistration.equals((emailAddress))) {
            System.out.println("Email ids are correct");
        } else {
            System.out.println("Email ids are not correct");
        }

        WebElement passwdInput = driver.findElement(By.id("passwd"));
        passwdInput.sendKeys(password);

        String dob = randomDOBAbove18();
        String[] splitDob = dob.split("-");
        int splitDobYear = Integer.parseInt(splitDob[0]);
        int splitDobMonth = Integer.parseInt(splitDob[1]);
        int splitDobDay = Integer.parseInt(splitDob[2]);


        WebElement days = driver.findElement(By.id("days"));
        WebElement months = driver.findElement(By.id("months"));
        WebElement years = driver.findElement(By.id("years"));

        Select select = new Select(days);
        select.selectByValue(String.valueOf(splitDobDay));

        select = new Select(months);
        select.selectByValue(String.valueOf(splitDobMonth));

        select = new Select(years);
        select.selectByValue(String.valueOf(splitDobYear));


        WebElement newsletterBX = driver.findElement(By.id("newsletter"));
        WebElement specialOffersBx = driver.findElement(By.id("optin"));

        if (!newsletterBX.isSelected()) {
            System.out.println("Checked newsletter checkbox");
            newsletterBX.click();

        } else {
            System.out.println("Checked newsletter is already checkbox");

        }
        if (!specialOffersBx.isSelected()) {
            System.out.println("Checked special offers checkbox");
            specialOffersBx.click();

        } else {
            System.out.println("Checked special offers is already checkbox");


            WebElement addrsFirstName = driver.findElement(By.id("firstname"));
            String valueGetFName = addrsFirstName.getAttribute("value");
            if (valueGetFName.equals(firstName)){
                System.out.println("First name is correct");
            }else {
                System.out.println("First name is NOT correct in address first name input field");
            }


            WebElement addrsLastName = driver.findElement(By.id("lastname"));
            String valueGetLName = addrsLastName.getAttribute("value");
            if (valueGetLName.equals(lastName)){
                System.out.println("Last name is correct");
            }else{
                System.out.println("Last name is NOT correct in address first name input field");
            }


            // RandomDate rd = new RandomDate(LocalDate.of(1900, 1, 1), LocalDate.of(2010, 1, 1));
        }

        //company
        WebElement companyNames = driver.findElement(By.id("company"));
        String randomCompanyName = randomCompanyName();
        companyNames.sendKeys(randomCompanyName);

        //address
        WebElement address1 = driver.findElement(By.id("address1"));
        String randomAddr = randomStreetAddress();
        address1.sendKeys(randomAddr);


        WebElement city = driver.findElement(By.id("city"));
        String randomCityX = randomCity();
        city.sendKeys(randomCityX);

        WebElement id_state = driver.findElement(By.id("id_state"));
        select = new Select(id_state);
        String valueRandomState = randomState();
        select.selectByVisibleText(valueRandomState);


        WebElement additionalInfo = driver.findElement(By.id("other"));
        String randomAdditional = generateRandomString(100);
        additionalInfo.sendKeys(randomAdditional);


        WebElement phone_mobile = driver.findElement(By.id("phone_mobile"));
        String randomChecker = randomPhoneNumber();
        phone_mobile.sendKeys(randomChecker);




    }
}