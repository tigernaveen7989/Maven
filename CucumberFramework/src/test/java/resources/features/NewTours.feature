@Travel
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
      | firstname | lastname | phone       | email                      | address                                            | state      | city       | postalcode | country        | username     | password   | confirmpassword |
      | naveen    | kumar    |  9498869487 | naveen@gmail.com           | 10, sam street, kondapur                           | telangana  | hyderabad  |     500084 | INDIA          | naveen874    | naveen@123 | naveen@123      |
      | santosh   | narayan  |  8485958857 | santosh7@yahoo.com         | building no 6, raidurgam post, near police station | telangana  | hyderabad  |     587463 | INDIA          | santosh      | santosh874 | santosh874      |
      | deepak    | kura     |  8475837465 | deepakkura@hotmail.com     | 57A, Kennady Street                                | New Jersey | New Jersey |      07920 | UNITED STATES  | deepak       | 123@Apple  | 123@Apple       |
      | bhagya    | pulapa   |  8494857857 | bhagyasri90@gmail.com      | building no 10, titus, mindspace                   | telangana  | hyderabad  |     500081 | INDIA          |       746579 | 958@mani   | 958@mani        |
      | murali    | kadam    |  9867486480 | murali@yahoo.com           | flat no 10, behind puma showroom, KPHP colony      | telangana  | hyderabad  |     500072 | INDIA          | urcrazy      | 8475%jones | 8475%jones      |
      | vishal    | pradeep  | 18379503939 | vishal56.pradeep@gmail.com | near victoria palace                               | london     | london     |     587463 | UNITED KINGDOM | 84758pradeep | 1234@bcd   | 1234@bcd        |
      | hari      | krishnan |  9498595899 | hari@hotmail.com           | Indian institute of science                        | karnataka  | bangalore  |     560056 | INDIA          | krishna88    | hari123    | hari123         |
      | siva      | kannan   |  9485869389 | siva@yahoo.com             | koramangala post office, 6th cross                 | karnataka  | bangalore  |     560025 | INDIA          |       847574 | 8576$siva  | 8576$siva       |
      | divya     | s        |  8475738499 | divyas79@hotmail.com       | sri shakthi by pass, neelambur                     | tamil nadu | coimbatore |     658475 | INDIA          | divyasri     | 7989nos    | 7989nos         |
      | daniel    | raj      |  9090875647 | daniel746@yahoo.com        | ondipudur                                          | tamil nadu | coimbatore |     638574 | INDIA          | danielclein  | tamilanda  | tamilanda       |
