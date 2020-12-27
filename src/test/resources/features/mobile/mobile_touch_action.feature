Feature: Test calculator with touch actions

  Scenario: The user uses the calculator
    Given the user opens the calculator application by swiping
    And taps square root of addition of 95 Plus 23 and multiplies it by negative 1
    Then after getting the result the result should be "−10.86278049120"
    Then also the result rounded to four digits after decimal should be "-10.8628"