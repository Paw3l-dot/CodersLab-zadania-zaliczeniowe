@new
Feature: Add new address to the user account

  Scenario Outline: Adding new address
    Given User is on shop's main page
    When User logs in using email "djodjnygfugbpcejsp@tcwlx.com" and password "haslopassword"
    And User clicks on Addresses button
    When User clicks on Create new address button
    And User enters new address "<alias>", "<street>", "<city>", "<zip>","<country>", "<phone>"
    Then User can check entered data
    And User closes the browser
    Examples:
      | alias         | street  | city       | zip    | country        | phone     |
      | FirstAddress  | Testowa | Test Vegas | 00-001 | United Kingdom | 123321123 |