@order
Feature: Placing new order

  Scenario: New order for Hummingbird Printed Sweater
    Given User is on the main page
    When User signs in using email "djodjnygfugbpcejsp@tcwlx.com" and password "haslopassword"
    And User goes to the Clothes product list page
    Then User adds 5 units of Hummingbird Printed Sweater, size "M" to his shopping cart
    When User proceeds to checkout
    And User confirms chosen address
    And User chooses PrestaShop pick up in store delivery method
    And User chooses Pay by Check payment method
    And User checks the term of service checkbox
    And User places an order
    Then User takes a screenshot of the order confirmation page
    And User quits the browser