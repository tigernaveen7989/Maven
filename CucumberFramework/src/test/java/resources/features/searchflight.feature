Feature: search flights
	all users should be able to search flights
Scenario: search flights with valid origin and destination and depart and return date
Given user navigates to airasia website
And verify its title
And click on flights icon
And enter origin as "Bengaluru (BLR)"
And enter destination as "Pune"
And select depart date as "15/12/2018"
And select return date as "One Way"
And scroll down page to confirm button
And click on confirm button
When click on search button
Then verify that user navigated successfully to search flight page