Feature: Air Asia
	all users should be able to search flights
	
	@flight
Scenario: search flights with valid origin and destination and depart and return date
Given user navigates to airasia website
And verify its title
And enter origin as "Bengaluru (BLR)"
And enter destination as "Pune"
And select depart date as "07/01/2019"
And select return date as "One Way"
And scroll down page to confirm button
And click on confirm button
When click on search button
Then verify that user navigated successfully to select flight page

@Login
Scenario: login with invalid username and password and validate unsuccessful error message
Given user navigates to airasia website
And verify its title
And click on login button
And enter username as "a@gmail.com"
And enter password as "India1234"
When click on signin button
Then verify that error message your login attempt has been unsuccessfull is displayed