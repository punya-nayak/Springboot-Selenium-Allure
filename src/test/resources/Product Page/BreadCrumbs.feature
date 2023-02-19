@BreadCrumbs

@severity=critical
Feature: Testcases to verify the working of Breadcrumbs In product page

  @BreadCrumbs_01
  Scenario Outline: Navigate to the parent section from subsection
    Given the user is logged in
    Then I navigate to "<SubSection>" subsection in "<Section>" section of "<Category>" category
    Then I verify if "<SubSection>" title is displayed
    And I navigate to "<SectionToNavigate>" via breadcrumbs and verify if navigation is successful
    Examples:
      | SubSection | Section | Category | SectionToNavigate |
      | Pants      | Bottoms | Women    |Bottoms          |
      | Tees       | Tops    | Men      | Men               |


  @BreadCrumbs_02
  Scenario: Navigation to Sections of categories
    Given the user is logged in
    Then I navigate to "Watches" section of "Gear" category
    Then I verify if "Watches" title is displayed
    And I navigate to "Home" via breadcrumbs and verify if navigation is successful