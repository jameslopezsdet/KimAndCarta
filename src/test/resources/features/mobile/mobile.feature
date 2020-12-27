
Feature: Test calculator application




  Scenario: The user uses the calculator
    Given the user opens the calculator app
    And add Ninety five plus twenty three
    And then takes the sum and gets the square root
    And then multiplies it by negative one
    Then the result should be "−10.8627804912"
    Then the result rounded to four digits after decimal should be "-10.8628"