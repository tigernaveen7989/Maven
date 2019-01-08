Feature: New Tours Demo
  all users should be able to register

  @Register
  Scenario Outline: register with valid firstname, lastname, phone, email, address, state, country, username, password and confirmpassword
    Given user navigates to newtours demo website homepage
    And click on register link
    And enter firstname as "<firstname>"
    And enter lastname as "<lastname>"
    And enter phone as "<phone>"
    And enter email as "<email>"
    And enter address as "<address>"
    And enter city as "<city>"
    And enter state as "<state>"
    And enter postalcode as "<postalcode>"
    And select country as "<country>"
    And enter username "<username>"
    And enter password "<password>"
    And enter confirm password as "<confirmpassword>"
    Then click on submit button
    
    Examples: 
      | firstname  | lastname | phone  		 | email 						  | address 								 														| state		  | city 			| postalcode |  country | username  | password   | confirmpassword | 
      | naveen		 | kumar	  | 9498869487 | naveen@gmail.com   |	10, sam street, kondapur 														| telangana | hyderabad | 500084		 | INDIA	  | naveen874 | naveen@123 | naveen@123      |
      |santosh		 | narayan	|	8485958857 | santosh7@yahoo.com | building no 6, raidurgam post, near police station  | telangana | hyderabad | 587463		 | INDIA		| santosh		| santosh874 | santosh874			 |