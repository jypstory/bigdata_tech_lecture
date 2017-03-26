# hive_lecture
- reset data
<pre><code>
hadoop fs -rm /user/hdfs/input/acc/*
hadoop fs -copyFromLocal Accidents_2015.csv  /user/hdfs/input/acc
hadoop fs -rm /user/hdfs/input/cas/*
hadoop fs -copyFromLocal Casualties_2015.csv  /user/hdfs/input/cas
</code></pre>

- create table
<pre><code>
> hive
 
CREATE EXTERNAL TABLE IF NOT EXISTS ACCIDENTS ( 
Accident_Index STRING, 
Location_Easting_OSGR INT, 
Location_Northing_OSGR INT, 
Longitude DOUBLE, 
Latitude DOUBLE, 
Police_Force INT, 
Accident_Severity INT, 
Number_of_Vehicles INT, 
Number_of_Casualties INT, 
Date_Acc STRING, 
Day_of_Week INT, 
Time STRING, 
District INT, 
Highway STRING, 
first_Road_Class INT, 
first_Road_Number INT, 
Road_Type INT, 
Speed_limit INT, 
Junction_Detail INT, 
Junction_Control INT, 
second_Road_Class INT, 
second_Road_Number INT, 
Pedestrian_Crossing_Human_Control INT, 
Pedestrian_Crossing_Physical_Facilities INT, 
Light_Conditions INT, 
Weather_Conditions INT, 
Road_Surface_Conditions INT, 
Special_Conditions_at_Site INT, 
Carriageway_Hazards INT, 
Urban_or_Rural_Area INT, 
Did_Police_Officer_Attend_Scene_of_Accident INT, 
Lower_Super_Ouput_Area_of_Accident_Location STRING 
) 
ROW FORMAT DELIMITED FIELDS TERMINATED BY ","
LOCATION '/user/hdfs/input/acc';
 
 
 
CREATE EXTERNAL TABLE IF NOT EXISTS CASUALTIES ( 
Acc_Index STRING, 
Vehicle_Reference INT, 
Casualty_Reference INT, 
Casualty_Class INT, 
Sex_of_Casualty INT, 
Age_Band_of_Casualty INT, 
Casualty_Severity INT, 
Pedestrian_Location INT, 
Pedestrian_Movement INT, 
Car_Passenger INT, 
Bus_or_Coach_Passenger INT, 
Pedestrian_Road_Maintenance_Worker INT, 
Casualty_Type INT, 
Casualty_Home_Area_Type INT 
) 
ROW FORMAT DELIMITED FIELDS TERMINATED BY ","
LOCATION '/user/hdfs/input/cas';
</code></pre>

- 년도 및 기상조건 별 건수 확인
<pre><code>

set hive.execution.engine=mr;
select key, count(1)  from ( select concat(concat(substr(Date_Acc,7),':'), Weather_Conditions) as key  from ACCIDENTS ) as a group by a.key;

</code></pre>

- 요일별, 성별 건수 확인
<pre><code>

set hive.execution.engine=mr;
select a.Day_of_Week,c.Sex_of_Casualty, count(1) from accidents a join casualties c on a.accident_index = c.acc_index group by Day_of_Week,Sex_of_Casualty;

</code></pre>

