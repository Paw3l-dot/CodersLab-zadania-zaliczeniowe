package shop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class NewAddressPage {

    private static WebDriver driver;

    private String enteredAddress;

    @FindBy(id="field-firstname")
    private WebElement firstName;

    @FindBy(id="field-lastname")
    private WebElement lastName;

    @FindBy(id="field-alias")
    private WebElement aliasInput;

    @FindBy(id="field-address1")
    private WebElement streetInput;

    @FindBy(id="field-city")
    private WebElement cityInput;

    @FindBy(id="field-postcode")
    private WebElement zipInput;

    @FindBy(id="field-phone")
    private WebElement phoneInput;

    @FindBy(id="field-id_country")
    private WebElement countrySelect;

    @FindBy(xpath = "//footer[@class='form-footer clearfix']/button")
    private WebElement formSubmitButton;

    public NewAddressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillTheAddress(String alias, String address, String city,
                                      String zip, String country, String phone) {

        aliasInput.clear();
        aliasInput.sendKeys(alias);

        streetInput.clear();
        streetInput.sendKeys(address);

        cityInput.clear();
        cityInput.sendKeys(city);

        zipInput.clear();
        zipInput.sendKeys(zip);

        Select ddown = new Select(countrySelect); //handle dropdown by value '17' = UK
        ddown.selectByValue("17");

        phoneInput.clear();
        phoneInput.sendKeys(phone);

        this.enteredAddress = alias + "\n" + firstName.getAttribute("value") + " "
                + lastName.getAttribute("value")
                + "\n" + address + "\n" + city + "\n" + zip
                + "\n" + country + "\n" + phone;
    }

    public void saveAddress() {
        formSubmitButton.click();
    }

    public String getEnteredAddress() {
        return this.enteredAddress;
    }
}