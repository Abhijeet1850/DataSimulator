Feature: Title of your feature
  I want to use this template for my feature file

  @tag1
  Scenario: Invalid Login to Facebook
   Given I create a CSV file "FI_HLD.csv" with following data
    | Date     | Facility |
    | 12/25/19 |   CTT    |     
   And I create a CSV file "FI_OH.csv" from following headers
    |From_Date | To_date  | Facility | Day_of_Week | Open_time  |  Close_Time |
   And I create a CSV file "FI_PT.csv" from following headers
    | Facility | Shipping_Method | Default | Single_Line | Multi_Line | NonCon  | Gift_Wrap  | Batch_Pick |
   And I create a CSV file "FI_ST.csv" from following headers
    | Facility_type | 	Facility	| Zip_Code | 	Time_Zone	| Shipping_method |
   And I upload below csv files to shared path
    |  File          |  Path                                                                                   |
    |  FI_HLD.csv    |  \\\\dept02cluster\\distributionservices\\Data_Transfer\\EOM\\STC\\EDD\\PROD\\FI\\Test  | 
    |  FI_OH.csv     |  \\\\dept02cluster\\distributionservices\\Data_Transfer\\EOM\\STC\\EDD\\PROD\\FI\\Test  |
    |  FI_PT.csv     |  \\\\dept02cluster\\distributionservices\\Data_Transfer\\EOM\\STC\\EDD\\PROD\\FI\\Test  |
    |  FI_ST.csv     |  \\\\dept02cluster\\distributionservices\\Data_Transfer\\EOM\\STC\\EDD\\PROD\\FI\\Test  |
  # And I trigger the Data load for csv on path "\\\\dept02cluster\\distributionservices\\Data_Transfer\\EOM\\STC\\EDD\\PROD\\FI\\Test"  
  # And I verify the count of new record added and existing records are not changed for Table "STG_GetfulfilmentSchedule"
  # And I verify "Facility" is one of the following values
  #    |  NLV  |
  #    |  ATL  |
  #    |  LEW  |
  #    |  CTT  |
  # And I verify "Shipping_Method" is one of the following values
  #    |  STANDARD  |
  #    |  EXPRESS   |
  #    |  EXPEDITED |
  # And I verify "Order_Type" is one of the following values
  #    |  STANDARD  |
  #    |  EXPRESS   |
  #    |  EXPEDITED |
  # And I verify "Time_Zone" is one of the following values
  #    |  EST  |
  #    |  PST  |
  #    |  CST  |  
  # And I verify "Day_Of_Week" is one of the following values
  #    | Monday    |
  #    | Tuesday   |
  #    | Wednesday |
  #    | Thursday  |
  #    | Friday    |
  #    | Saturday  |
  #    | Sunday    |   
  #   And Validate the Format for "ProcessingTime" is "integer"     
  #  And validate the ZipCode is "5" digits
   
       
      
    
   #@tag1
  Scenario: Test DB Connection
   Given I connect to Database