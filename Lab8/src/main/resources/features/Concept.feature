Feature: Concept
  Scenario :
    Given Connection to API with "api-key"
    When I get constraint by "lxg:350231317898"
    Then I receive status code 200
  Scenario :
    Given Connection to API with "api-key"
    When I get constraint by "1"
    Then I receive status code 200