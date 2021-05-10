
Feature: Login to the website and Checkout functionality
	

	Background: Login to the app
	Given Site is launched
	And Login page is opened
	When Enter username and password
	Then Click on Login
	
	
  Scenario: To verify checkout functionality
    Given Click Order Now
    And Select order type as delivery
    When Provide Delivery details
    And provide date and time
    And Choose food
    Then Click add to cart
    And view cart
    And Checkout


