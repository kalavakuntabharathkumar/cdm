Feature: Submit a credit application
  Scenario: Applicant submits a valid application
    Given an applicant has a positive income and requested amount
    When the application is submitted
    Then the platform accepts it for asynchronous evaluation
