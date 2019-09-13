Feature: USFuels
  RackPrices submodule under USFuels

  @rackprice
  Scenario Outline: verify_search_using_terminal
    Given user navigates to markethub website homepage
    And enter username as "<username>"
    And enter password as "<password>"
    Then click on login button
    And click on all tab
    And click on rack prices link
    Then verify rack prices page title
    And click on terminal dropdown
    And click on terminal dropdown first checkbox
    And click on terminal dropdown
    And click on search button
    Then verify rack prices row count
    Then verify terminal column values

    Examples: 
      | username   | password  |
      | USFTesttne | 2Cookies! |
