# pig lecture

- 일자별 count 하기
<pre><code>
A = LOAD '/user/hdfs/input/acc/Accidents_2015.csv' USING PigStorage(',') AS ( 
Accident_Index:chararray, Location_Easting_OSGR:chararray, Location_Northing_OSGR:chararray, 
Longitude:chararray, 
Latitude:chararray, 
Police_Force:chararray, 
Accident_Severity:chararray, 
Number_of_Vehicles:chararray, 
Number_of_Casualties:chararray, 
Date_Acc:chararray, 
Day_of_Week:chararray, 
Time:chararray, 
District:chararray, 
Highway:chararray, 
first_Road_Class:chararray, 
first_Road_Number:chararray, 
Road_Type:chararray, 
Speed_limit:chararray, 
Junction_Detail:chararray, 
Junction_Control:chararray, 
second_Road_Class:chararray, 
second_Road_Number:chararray, 
Pedestrian_Crossing_Human_Control:chararray, 
Pedestrian_Crossing_Physical_Facilities:chararray, 
Light_Conditions:chararray, 
Weather_Conditions:chararray, 
Road_Surface_Conditions:chararray, 
Special_Conditions_at_Site:chararray, 
Carriageway_Hazards:chararray, 
Urban_or_Rural_Area:chararray, 
Did_Police_Officer_Attend_Scene_of_Accident:chararray, 
Lower_Super_Ouput_Area_of_Accident_Location:chararray);
 
 
B = GROUP A BY CONCAT(CONCAT(SUBSTRING(Date_Acc,6,10),':'),Weather_Conditions);
C = FOREACH B GENERATE group, COUNT(A);
DUMP C;
</code></pre>
