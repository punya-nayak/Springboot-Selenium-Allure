@MyAccount

@severity=critical
Feature: Testcases to verify the my account page

  @MyAccount_01
  Scenario Outline: To verify <Subsection> subsection of <Section> section in My Account Page
    Given the user is logged in
    And I click on "My Account" in customer menu on the header
    Then I verify if "<Section>" section is present in My account Page
    Then I verify if "<Subsection>" subsection is present in My account Page
    Examples:
      | Section             | Subsection               |
      | Account Information | Contact Information      |
      | Account Information | Newsletters              |
      | Address Book        | Default Billing Address  |
      | Address Book        | Default Shipping Address |

  @MyAccount_02 @specific_user
  Scenario: To verify Address Book Page for User with Address
    Given I log in with "UserWithAddress" user
    And I click on "My Account" in customer menu on the header
    And I click on "Address Book" in the left navigation panel
    And I verify the page for a user with address added
