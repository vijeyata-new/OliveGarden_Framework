Feature: Signup to the website
	Scenario: Signup new account
		Given launch Website
		When Click Login
		And Click Register
		And Fill form
	  Then Find restaurant
		And Complete account
