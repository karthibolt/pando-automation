Feature: Data Manager - Depot Management

  Background:
    Given User launches the URL
    When user enter the username and password
    And user clicks the submit button
    Then user login successfully

 @datamanager
  Scenario: Create a new depot with unique name using timestamp
    When User navigates to the Data Manager module
    And User opens the Depot module
    And User clicks on Add Depot
    And User enters depot details
    And User clicks Create
    Then Depot creation success message should be displayed
    And User searches for depot
     And User creates a gate for the searched depot



  Scenario: Create a new Consignees
    When user navigates to the consignees module in dropdown
    And User clicks on Add consignee
    And user enters the consigneees mandate details
    And user clicks on save button
    And user searches the for the created consignee name in search textbox

  Scenario: Create a new Transporter
    When user navigates to the transporter module in dropdown
    And user clicks on Add Transporter
    And user enters transporter details
    Then Transporter creation success message should be displayed
    And user searches for transporter

  Scenario: Create a new Material
    When user navigates to the material module in dropdown
    And user clicks on Add Material
    And user enters material details
    Then Material creation success message should be displayed
    And user searches for material


  Scenario: Create a new Vehicle
    When user navigates to the vehicle module in dropdown
    And user clicks on Add Vehicle
    And user enters vehicle details
    Then Vehicle creation success message should be displayed
    And user searches for vehicle


  Scenario: Create a new Indent
    When user navigates to the indent module in dropdown
    And user clicks on Add Indent
    And user search for Depot name
  And user search for Consignee  name
  And user enters indent details
  And user searches for indent
    And user enters truck assignment details and submits
    And user reports the truck and marks it arrived

#  Scenario: Upload bulk excel file for indent creation
#    When user uploads bulk excel file "IndentUpload.xlsx"
#    Then bulk excel file should be uploaded successfully