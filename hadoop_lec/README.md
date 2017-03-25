# hadoop 강의
## hdfs 실습 - 명령어

- 사용자 계정 변경 (hdfs 서비스를 시작한 계정이 hdfs 에서의 superuser임)
<pre><code>
su - hdfs
</code></pre>

- 영국 교통사고  데이터 받기
<pre><code>
cd ~
mkdir hadoop_edu
mkdir data
cd hadoop_ecu/data
 
# 영국 교통사고
wget https://github.com/jypstory/bigdata_tech_lecture/raw/master/sample_data/DfTRoadSafety_Accidents.zip
unzip DfTRoadSafety_Accidents.zip
 
# 영국 교통사고 사상자 정보
wget https://github.com/jypstory/bigdata_tech_lecture/raw/master/sample_data/DfTRoadSafety_Casualties.zip
unzip DfTRoadSafety_Casualties.zip
 
# 지역 코드
wget https://github.com/jypstory/bigdata_tech_lecture/raw/master/sample_data/DfTRoadSafety_District.zip
unzip DfTRoadSafety_District.zip

# [참고] 데이터 활용 가이드 : https://github.com/jypstory/bigdata_tech_lecture/raw/master/sample_data/Road-Accident-Safety-Data-Guide.xls
</code></pre>
<br>

- HDFS에  디렉터리 만들기
<pre><code>
hadoop fs -mkdir -p /user/hdfs/input/acc
hadoop fs -mkdir -p /user/hdfs/input/cas 
</code></pre>

- 데이터 HDFS에 업로드 하기
<pre><code>
hadoop fs -copyFromLocal Acc*.csv /user/hdfs/input/acc

# 결과 확인
[hdfs@sandbox data]$ hadoop fs -ls /user/hdfs/input/acc
Found 11 items
-rw-r--r--   1 hdfs hdfs   27292673 2017-03-25 11:55 /user/hdfs/input/acc/Accidents_2005.csv
-rw-r--r--   1 hdfs hdfs   25983761 2017-03-25 11:55 /user/hdfs/input/acc/Accidents_2006.csv
-rw-r--r--   1 hdfs hdfs   24939874 2017-03-25 11:55 /user/hdfs/input/acc/Accidents_2007.csv
-rw-r--r--   1 hdfs hdfs   23299726 2017-03-25 11:55 /user/hdfs/input/acc/Accidents_2008.csv
-rw-r--r--   1 hdfs hdfs   22458294 2017-03-25 11:55 /user/hdfs/input/acc/Accidents_2009.csv
-rw-r--r--   1 hdfs hdfs   21209185 2017-03-25 11:55 /user/hdfs/input/acc/Accidents_2010.csv
-rw-r--r--   1 hdfs hdfs   20814048 2017-03-25 11:55 /user/hdfs/input/acc/Accidents_2011.csv
-rw-r--r--   1 hdfs hdfs   19999817 2017-03-25 11:55 /user/hdfs/input/acc/Accidents_2012.csv
-rw-r--r--   1 hdfs hdfs   19044905 2017-03-25 11:55 /user/hdfs/input/acc/Accidents_2013.csv
-rw-r--r--   1 hdfs hdfs   20098386 2017-03-25 11:55 /user/hdfs/input/acc/Accidents_2014.csv
-rw-r--r--   1 hdfs hdfs   19238472 2017-03-25 11:55 /user/hdfs/input/acc/Accidents_2015.csv

[hdfs@sandbox data]$ hadoop fs -copyFromLocal Cas*.csv /user/hdfs/input/cas

# 결과 확인
[hdfs@sandbox data]$ hadoop fs -ls /user/hdfs/input/cas
Found 11 items
-rw-r--r--   1 hdfs hdfs   11486700 2017-03-25 11:57 /user/hdfs/input/cas/Casualties_2005.csv
-rw-r--r--   1 hdfs hdfs   10943696 2017-03-25 11:57 /user/hdfs/input/cas/Casualties_2006.csv
-rw-r--r--   1 hdfs hdfs   10488549 2017-03-25 11:57 /user/hdfs/input/cas/Casualties_2007.csv
-rw-r--r--   1 hdfs hdfs    9775592 2017-03-25 11:57 /user/hdfs/input/cas/Casualties_2008.csv
-rw-r--r--   1 hdfs hdfs    9396261 2017-03-25 11:57 /user/hdfs/input/cas/Casualties_2009.csv
-rw-r--r--   1 hdfs hdfs    8824767 2017-03-25 11:57 /user/hdfs/input/cas/Casualties_2010.csv
-rw-r--r--   1 hdfs hdfs    8421406 2017-03-25 11:57 /user/hdfs/input/cas/Casualties_2011.csv
-rw-r--r--   1 hdfs hdfs    8080173 2017-03-25 11:57 /user/hdfs/input/cas/Casualties_2012.csv
-rw-r--r--   1 hdfs hdfs    7584289 2017-03-25 11:57 /user/hdfs/input/cas/Casualties_2013.csv
-rw-r--r--   1 hdfs hdfs    8605601 2017-03-25 11:57 /user/hdfs/input/cas/Casualties_2014.csv
-rw-r--r--   1 hdfs hdfs    8246008 2017-03-25 11:57 /user/hdfs/input/cas/Casualties_2015.csv
</code></pre>

- 전처리 용도로 연도별 데이터 merge 처리
<pre><code>
[hdfs@sandbox data]$ hadoop fs -getmerge /user/hdfs/input/acc/Accidents*.csv    Accidents_2005_2015.csv
[hdfs@sandbox data]$ hadoop fs -getmerge /user/hdfs/input/cas/Casualties*.csv   Casualties_2005_2015.csv
</code></pre>

- 기존 업로드 데이터 삭제
<pre><code>
[hdfs@sandbox data]$ hadoop fs -rmr /user/hdfs/input/*
rmr: DEPRECATED: Please use 'rm -r' instead.
17/03/25 12:01:11 INFO fs.TrashPolicyDefault: Moved: 'hdfs://sandbox.hortonworks.com:8020/user/hdfs/input/acc' to trash at: hdfs://sandbox.hortonworks.com:8020/user/hdfs/.Trash/Current/user/hdfs/input/acc
17/03/25 12:01:11 INFO fs.TrashPolicyDefault: Moved: 'hdfs://sandbox.hortonworks.com:8020/user/hdfs/input/cas' to trash at: hdfs://sandbox.hortonworks.com:8020/user/hdfs/.Trash/Current/user/hdfs/input/cas
</code></pre>

- merge 처리한 파일 업로드
<pre><code>
[hdfs@sandbox data]$ hadoop fs -mkdir /user/hdfs/input/acc
[hdfs@sandbox data]$ hadoop fs -mkdir /user/hdfs/input/cas
[hdfs@sandbox data]$ hadoop fs -copyFromLocal Accidents_2005_2015.csv /user/hdfs/input/acc
[hdfs@sandbox data]$ hadoop fs -copyFromLocal Casualties_2005_2015.csv /user/hdfs/input/cas
</code></pre>

## hdfs 실습 - HDFS API 활용





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




