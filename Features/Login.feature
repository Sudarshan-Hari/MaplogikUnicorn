
Feature: Login to Maplogik application as student and fetch all the Unicorn displayed and store
					in an excel sheet

  Scenario: Fetch Unicorn Ranking from Application and Create an Excel sheet
    Given open maplogik student login url
    When enter multiple student ID and password and fetch Student name college name and district as "details"
    Then save that data to an excel file