package shop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserAddressesPage {


    @FindBy(xpath = "//div[@class='address-body']")
    private List<WebElement> addresses;


    @FindBy(xpath = "//div[@class='addresses-footer']//a")
    private WebElement createNewAddressButton;

    public UserAddressesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickCreateNewAddressButton() {
        createNewAddressButton.click();
    }

    public String getSavedAddress() {
        return addresses.get(1).getText();
    }

}