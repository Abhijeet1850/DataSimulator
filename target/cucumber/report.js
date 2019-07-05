$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("com/BBBY/DataSimulator/features_api/FacilitySchedule.feature");
formatter.feature({
  "line": 1,
  "name": "Title of your feature",
  "description": "I want to use this template for my feature file",
  "id": "title-of-your-feature",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 5,
  "name": "Invalid Login to Facebook",
  "description": "",
  "id": "title-of-your-feature;invalid-login-to-facebook",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 4,
      "name": "@tag1"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "I create a CSV file \"FI_HLD.csv\" with following data",
  "rows": [
    {
      "cells": [
        "Date",
        "Facility"
      ],
      "line": 7
    },
    {
      "cells": [
        "12/25/19",
        "CTT"
      ],
      "line": 8
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "I create a CSV file \"FI_OH.csv\" from following headers",
  "rows": [
    {
      "cells": [
        "From_Date",
        "To_date",
        "Facility",
        "Day_of_Week",
        "Open_time",
        "Close_Time"
      ],
      "line": 10
    }
  ],
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "I create a CSV file \"FI_PT.csv\" from following headers",
  "rows": [
    {
      "cells": [
        "Facility",
        "Shipping_Method",
        "Default",
        "Single_Line",
        "Multi_Line",
        "NonCon",
        "Gift_Wrap",
        "Batch_Pick"
      ],
      "line": 12
    }
  ],
  "keyword": "And "
});
formatter.step({
  "line": 13,
  "name": "I create a CSV file \"FI_ST.csv\" from following headers",
  "rows": [
    {
      "cells": [
        "Facility_type",
        "Facility",
        "Zip_Code",
        "Time_Zone",
        "Shipping_method"
      ],
      "line": 14
    }
  ],
  "keyword": "And "
});
formatter.step({
  "line": 15,
  "name": "I upload below csv files to shared path",
  "rows": [
    {
      "cells": [
        "File",
        "Path"
      ],
      "line": 16
    },
    {
      "cells": [
        "FI_HLD.csv",
        "\\\\dept02cluster\\distributionservices\\Data_Transfer\\EOM\\STC\\EDD\\PROD\\FI\\Test"
      ],
      "line": 17
    },
    {
      "cells": [
        "FI_OH.csv",
        "\\\\dept02cluster\\distributionservices\\Data_Transfer\\EOM\\STC\\EDD\\PROD\\FI\\Test"
      ],
      "line": 18
    },
    {
      "cells": [
        "FI_PT.csv",
        "\\\\dept02cluster\\distributionservices\\Data_Transfer\\EOM\\STC\\EDD\\PROD\\FI\\Test"
      ],
      "line": 19
    },
    {
      "cells": [
        "FI_ST.csv",
        "\\\\dept02cluster\\distributionservices\\Data_Transfer\\EOM\\STC\\EDD\\PROD\\FI\\Test"
      ],
      "line": 20
    }
  ],
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "FI_HLD.csv",
      "offset": 21
    }
  ],
  "location": "ManageFacilitySchedInfo.i_create_a_CSV_file_with_following_data(String,DataTable)"
});
formatter.result({
  "duration": 91926393,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "FI_OH.csv",
      "offset": 21
    }
  ],
  "location": "ManageFacilitySchedInfo.i_create_a_CSV_file_from_following_headers(String,DataTable)"
});
formatter.result({
  "duration": 61410483,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "FI_PT.csv",
      "offset": 21
    }
  ],
  "location": "ManageFacilitySchedInfo.i_create_a_CSV_file_from_following_headers(String,DataTable)"
});
formatter.result({
  "duration": 2377699,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "FI_ST.csv",
      "offset": 21
    }
  ],
  "location": "ManageFacilitySchedInfo.i_create_a_CSV_file_from_following_headers(String,DataTable)"
});
formatter.result({
  "duration": 1665941,
  "status": "passed"
});
formatter.match({
  "location": "ManageFacilitySchedInfo.i_upload_below_csv_files_to_shared_path(DataTable)"
});
formatter.result({
  "duration": 22387991164,
  "status": "passed"
});
});