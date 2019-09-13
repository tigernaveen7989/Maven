#Author: n.kumar8@shell.com
#Feature: UAM
Feature: UAM
  User registration submodule under UAM module

  @searchuser
  Scenario Outline: verify_impersonate_customer_user
    Given user navigates to markethub website homepage
    And enter username as "<username>"
    And enter password as "<password>"
    Then click on login button
    And click on manage profile icon
    Then verify search customer user title
    And enter search email as "<searchemail>"
    And click on search button
    Then verify user list table
    And click on user list checkbox1
    And click on impersonate button
    Then verify "<youareimpersonatingtext>" text

    Examples: 
      | username | password  | searchemail          | youareimpersonatingtext |
      | n.kumar8 | Shakthi1! | r-t.mendez@shell.com | You are                 |

  @searchuser
  Scenario Outline: verify_search_customer_user
    Given user navigates to markethub website homepage
    And enter username as "<username>"
    And enter password as "<password>"
    Then click on login button
    And click on manage profile icon
    Then verify search customer user title
    And enter search email as "<searchemail>"
    And click on search button
    Then verify user list table

    Examples: 
      | username | password  | searchemail          |
      | n.kumar8 | Shakthi1! | r-t.mendez@shell.com |
