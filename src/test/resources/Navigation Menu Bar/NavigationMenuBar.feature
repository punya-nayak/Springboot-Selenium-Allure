@NavigationMenuBar


Feature: Testcases to verify the working of Navigation Menu Bar

  @NavigationMenuBar_01 @severity=blocker
  Scenario: To verify all the menus in Navigation Menu Bar
    Given the user is logged in
    Then the navigation menu bar is displayed with the following category menus:
      | Women    |
      | Men      |
      | Gear     |
      | Training |
    Then the navigation menu bar has the following links:
      | What's New |
      | Sale       |
    Then I verify that "Women" category has the following sections:
      | Tops    |
      | Bottoms |
    Then I verify that "Men" category has the following sections:
      | Tops    |
      | Bottoms |
    Then I verify that "Gear" category has the following sections:
      | Bags              |
      | Fitness Equipment |
      | Watches           |

  @NavigationMenuBar_02
  Scenario Outline: Navigation to Subsections of categories
    Given the user is logged in
    Then I navigate to "<SubSection>" subsection in "<Section>" section of "<Category>" category
    Then I verify if "<SubSection>" title is displayed
    Examples:
      | SubSection | Section | Category |
      | Pants      | Bottoms | Women    |
      | Tees       | Tops    | Men      |

  @NavigationMenuBar_03
  Scenario Outline: Navigation to Sections of categories
    Given the user is logged in
    Then I navigate to "<Section>" section of "<Category>" category
    Then I verify if "<Section>" title is displayed
    Examples:
      | Section        | Category |
      | Bottoms        | Women    |
      | Bags           | Gear     |
      | Video Download | Training |
