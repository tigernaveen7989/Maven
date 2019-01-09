@Automation-Practice
Feature: Automation Practice
  all users should be able to register

  @automationpractice
  Scenario Outline: register with valid firstname, lastname, password, date of birth, address, city, country, state, postalcode and phone
    When click on signin
    Given enter valid email "<email>"
    And click on create an account button
    And validate you are on registration page
    And select title radio button
    And enter firstname "<firstname>"
    And enter lastname "<lastname>"
    And enter the password "<password>"
    And select date of birth "<day>" and "<month>" and "<year>"
    And enter firstname in address "<firstname>"
    And enter lastname in address "<lastname>"
    And enter address "<address>"
    And enter city "<city>"
    And enter state "<state>"
    And select country "<country>"
    And enter postalcode "<postalcode>"
    And enter phone "<phone>"
    And enter alias "<alias>"
    When click on register button
    Then validate user is successfully registered

    Examples: 
      | firstname | lastname | phone      | day | month    | year | address                                            | state      | city       | postalcode | country       | username  | password   | email                 | alias   |
      | naveen    | kumar    | 9498869487 |   8 | June     | 1989 | 10, sam street, kondapur                           | Arizona    | hyderabad  |      85001 | United States | naveen874 | naveen@123 | naveen147@gmail.com   | naveen  |
      | santosh   | narayan  | 8485958857 |  10 | April    | 2010 | building no 6, raidurgam post, near police station | California | hyderabad  |      90002 | United States | santosh   | santosh874 | santosh76@yahoo.com   | santosh |
      | deepak    | kura     | 8475837465 |  27 | November | 1990 | 57A, Kennady Street                                | New Jersey | New Jersey |      07920 | United States | deepak    | 123@Apple  | deepakk54@hotmail.com | deepak  |
