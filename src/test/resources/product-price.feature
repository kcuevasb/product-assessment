Feature: ProductPrices
  Background:
    * url 'http://localhost:8080'

  Scenario: RequestAt10AMOnDay14
    Given path '/product-assessment/search'
    And param productId = '35455'
    And param brandId = '1'
    And param appDate = '2020-06-14T10:00:00'
    When method get
    Then status 200
    And match response.finalPrice == '35.5'

  Scenario: RequestAt4PMOnDay14
    Given path '/product-assessment/search'
    And param productId = '35455'
    And param brandId = '1'
    And param appDate = '2020-06-14T16:00:00'
    When method get
    Then status 200
    And match response.finalPrice == '25.45'

  Scenario: RequestAt9PMOnDay14
    Given path '/product-assessment/search'
    And param productId = '35455'
    And param brandId = '1'
    And param appDate = '2020-06-14T21:00:00'
    When method get
    Then status 200
    And match response.finalPrice == '35.5'

  Scenario: RequestAt10AMOnDay15
    Given path '/product-assessment/search'
    And param productId = '35455'
    And param brandId = '1'
    And param appDate = '2020-06-15T10:00:00'
    When method get
    Then status 200
    And match response.finalPrice == '30.5'

  Scenario: RequestAt9PMOnDay16
    Given path '/product-assessment/search'
    And param productId = '35455'
    And param brandId = '1'
    And param appDate = '2020-06-16T21:00:00'
    When method get
    Then status 200
    And match response.finalPrice == '38.95'