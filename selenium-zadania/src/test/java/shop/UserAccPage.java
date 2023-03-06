package shop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserAccPage {

    private static WebDriver driver;

    @FindBy(id = "addresses-link")
    private WebElement addressesIcon;

    public UserAccPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAdressesIcon() {
        addressesIcon.click();
    }
}
