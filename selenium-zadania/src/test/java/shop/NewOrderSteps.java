package shop;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class NewOrderSteps {

    ShopMainPage shopMainPage;

    ShopLoginPage shopLoginPage;

    WebDriver driver = new ChromeDriver();


    @Given("User is on the main page")
    public void userIsOnTheMainPage() {
        System.setProperty("webdriver.chrome.driver",
                "src/test/resources/drivers/chromedriver.exe");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://mystore-testlab.coderslab.pl/index.php");
    }

    @When("User signs in using email {string} and password {string}")
    public void userSignsInUsingEmailAndPassword(String email, String password) {
        shopMainPage = new ShopMainPage(driver);
        shopMainPage.clickSignInButton();

        shopLoginPage = new ShopLoginPage(driver);
        shopLoginPage.enterEmailAndPassword(email, password);
        shopLoginPage.submitLogin();
    }

    @And("User goes to the Clothes product list page")
    public void userGoesToTheClothesProductListPage() {
        driver.findElement(By.id("category-3")).click();
    }


    @Then("User adds {int} units of Hummingbird Printed Sweater, size {string} to his shopping cart")
    public void userAddsUnitsOfHummingbirdPrintedSweaterSizeToHisShoppingCart(int qty, String size) throws InterruptedException {
        driver.findElement(By.xpath("//article[@data-id-product='2']//h2[@class='h3 product-title']//a")).click();

        WebElement sizeElement = driver.findElement(By.id("group_1"));
        Select ddown = new Select(sizeElement); //handle dropdown by value '2' = size M
        ddown.selectByValue("2");
        Thread.sleep(2000);
        WebElement quantityInput = driver.findElement(By.name("qty"));
       // quantityInput.clear();
        quantityInput.sendKeys(Keys.DELETE);
        quantityInput.sendKeys("5");

        driver.findElement(By.className("add")).click(); // add to cart
    }

    @When("User proceeds to checkout")
    public void userProceedsToCheckout() throws InterruptedException {
        WebDriverWait waiter = new WebDriverWait(driver, Duration.ofSeconds(4));
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a"))).click();
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[2]/div/a"))).click();
        driver.findElement(By.name("confirm-addresses")).click();
        driver.findElement(By.name("confirmDeliveryOption")).click();
        driver.findElement(By.id("payment-option-1")).click();
    }

    @And("User confirms chosen address")
    public void userConfirmsChosenAddress() {
        driver.findElement(By.name("confirm-addresses")).click();}

    @And("User chooses PrestaShop pick up in store delivery method")
    public void userChoosesPrestaShopPickUpInStoreDeliveryMethod() {
        driver.findElement(By.name("confirmDeliveryOption")).click();}

    @And("User chooses Pay by Check payment method")
    public void userChoosesPayByCheckPaymentMethod() {
        driver.findElement(By.id("payment-option-1")).click();}

    @And("User checks the term of service checkbox")
    public void userChecksTheTermOfServiceCheckbox() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"conditions_to_approve[terms-and-conditions]\"]")).click();
    }

    @And("User places an order")
    public void userPlacesAnOrder() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.className("btn btn-primary center-block")).click();
    }

    @Then("User takes a screenshot of the order confirmation page")
    public void userTakesAScreenshotOfTheOrderConfirmationPage() throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        ImageIO.write(ImageIO.read(screenshot), "PNG", new File
                ("C:\\Users\\Dell\\selenium-zadania\\print"));
    }

    @And("User quits the browser")
    public void userQuitsTheBrowser() {driver.quit();}
}
