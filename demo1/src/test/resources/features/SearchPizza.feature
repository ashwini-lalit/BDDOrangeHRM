Feature: Pizza Search
  As a customer
  I want to search for pizzas
  So that I can find my preferred pizza quickly

  Scenario Outline: User should be able to find pizza by filtering
    Given store has pizza menu as below (product catalogue)
      | Name/toppings | Size   | Price  |
      | Pepperoni    | Small  | $14.99 |
      | Margherita   | Medium | $12.99 |
      | Vegetarian   | Large  | $13.99 |
    When Jane searches "<pizza-searched>"
    Then she should be able to find "<pizza-found>"

    Examples:
      | Description           | pizza-searched | pizza-found |
      | Search name 2 chars   | ma             | Not Found   |
      | Search 'small' size   | small          | Pepperoni   |
      | Search $12 price      | $12            | Margherita  |
      | Search 'Veg' 3 char   | veg            | Vegetarian  |
      | Search non-existent   | Capsicum       | Not Found   |




