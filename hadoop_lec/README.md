# bigdata_tech_lecture
==================
## 실습 데이터 받기
- 실습 데이터 (영국 교통사고 데이터)
> 사고 이력 : https://github.com/jypstory/bigdata_tech_lecture/sample_data/DfTRoadSafety_Accidents.zip

> 사상자 정보 : https://github.com/jypstory/bigdata_tech_lecture/sample_data/DfTRoadSafety_Casualties.zip

> 지역 코드 : https://github.com/jypstory/bigdata_tech_lecture/sample_data/DfTRoadSafety_District.zip

> 데이터 활용 가이드 : https://github.com/jypstory/bigdata_tech_lecture/sample_data/Road-Accident-Safety-Data-Guide.xls

<br>

- MapReduce Application 다운로드

> http://github.com/longnym/lecture/raw/master/build/hadoop_ex01.jar

> http://github.com/longnym/lecture/raw/master/build/hadoop_ex02.jar

> http://github.com/longnym/lecture/raw/master/build/hadoop_ex03.jar

<br>

- Driver Class의 실행 (실습 1)

>hadoop jar hadoop_ex01.jar -D inputPath=/input/acc/Accidents_2005_2015.csv -D outputPath=/output/result01 -D numReduceTasks=3 skill.coach.TestDriver

<br>

- Driver Class의 실행 (실습 2)

>hadoop jar hadoop_ex02.jar -D inputPath=/input/acc/Accidents_2005_2015.csv -D outputPath=/output/result02 -D numReduceTasks=3 skill.coach.TestDriver

<br>

- Driver Class의 실행 (실습 3)

>hadoop jar hadoop_ex03.jar -D inputPath1=/input/acc/Accidents_2005_2015.csv -D inputPath2=/input/cas/Casualties_2005_2015.csv -D cachePath=/input/dis/District.txt -D tempPath=/output/result03_1 -D outputPath=/output/result03_2 -D numReduceTasks=3 skill.coach.TestDriver

<br>

- Yarn Application의 실행

>yarn jar simple-yarn-app-1.1.0.jar com.hortonworks.simpleyarnapp.Client /bin/date 2 hdfs:///test/simple-yarn-app-1.1.0.jar

<br>




