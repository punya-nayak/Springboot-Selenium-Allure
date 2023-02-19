@Sort

@severity=normal
Feature: Testcases to verify the working of Navigation Menu Bar

  @Sort_01
  Scenario Outline: Navigation to <SubSection> in <Section> of <Category> category
    Given the user is logged in
    Then I navigate to "<SubSection>" subsection in "<Section>" section of "<Category>" category
    Then I verify if "<SubSection>" title is displayed
    And I verify if sort by label is displayed
    And I verify if sort by drop down and sort order arrow are visible
    And I verify default sort by option selected is "Position" and default sort order is ascending
    And I select "<SortBy>" from sort by drop down in "<SortOrder>" order
    And I verify if products are sorted according to "<SortBy>" in "<SortOrder>" order
    Examples:
      | SubSection            | Section | Category | SortBy       | SortOrder  |
      | Hoodies & Sweatshirts | Tops    | Men      | Product Name | Descending |
      | Shorts                | Bottoms | Women    | Price        | Ascending  |
