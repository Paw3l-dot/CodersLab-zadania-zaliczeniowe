package shop;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class UserNewAddressSteps {
    ShopMainPage shopMainPage;

    ShopLoginPage shopLoginPage;

    UserAccPage userAccPage;

    UserAddressesPage userAddressesPage;

    NewAddressPage newAddressPage;

    WebDriver driver = new ChromeDriver();

    @Given("User is on shop's main page")
    public void userIsOnMainPage() {
        System.setProperty("webdriver.chrome.driver",
                "src/test/resources/drivers/chromedriver.exe");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://mystore-testlab.coderslab.pl/index.php");
    }

    @When("User logs in using email {string} and password {string}")
    public void userLogsInUsingEmailAndPassword(String email, String password) {
        shopMainPage = new ShopMainPage(driver);
        shopMainPage.clickSignInButton();

        shopLoginPage = new ShopLoginPage(driver);
        shopLoginPage.enterEmailAndPassword(email, password);
        shopLoginPage.submitLogin();
    }

    @And("User clicks on Addresses button")
    public void userClicksOnAddressesButton() {
        userAccPage = new UserAccPage(driver);
        userAccPage.clickAdressesIcon();
    }

    @When("User clicks on Create new address button")
    public void userClicksOnCreateNewAddressButton() {
        userAddressesPage = new UserAddressesPage(driver);
        userAddressesPage.clickCreateNewAddressButton();
    }

    @And("User enters new address {string}, {string}, {string}, {string},{string}, {string}")
    public void userEntersNewAddress(String alias, String street, String city, String zip, String country, String phone) {
        newAddressPage = new NewAddressPage(driver);
        newAddressPage.fillTheAddress(alias, street, city, zip, country, phone);
        newAddressPage.saveAddress();
    }

    @Then("User can check entered data")
    public void userCanCheckEnteredData() {
        Assertions.assertEquals(newAddressPage.getEnteredAddress(), userAddressesPage.getSavedAddress());
    }

    @And("User closes the browser")
    public void userClosesTheBrowser() {driver.quit();}

}
