Feature: Login to Maplogik application as student and fetch all the Unicorn displayed and store
					in an excel sheet and compare the files
 
 Scenario: Compare source file and created excel file
    Given I want to read the source file and fetch data from both excel sheets
    And Compare both the sheets and print the values