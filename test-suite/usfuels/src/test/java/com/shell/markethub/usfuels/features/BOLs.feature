Feature: USFuels
  BOLs submodule under USFuels

  @bols
  Scenario Outline: verify_download_using_bol_number
    Given user navigates to markethub website homepage
    And enter username as "<username>"
    And enter password as "<password>"
    Then click on login button
    And click on all tab
    And click on bols link
    Then verify bols page title
    And click on ship to dropdown
    And click on ship to all dropdown checkbox
    And click on ship to dropdown
    And click on from calender icon
    And click on from calender prev month button
    And click on from calender day button
    And enter bol number "<bolnumber>"
    And click on bol page search button
    Then verify bols no column values "<bolnumber>"
    And select all checkbox
    And click on download button
    Then verify download header
    And select xml radio button
    And click on download button1

    Examples: 
      | username   | password  | bolnumber |
      | USFTesttne | 2Cookies! |   1765281 |
