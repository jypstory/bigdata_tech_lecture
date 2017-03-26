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
wget https://github.com/jypstory/bigdata_tech_lecture/raw/master/hadoop_lec/sample_data/DfTRoadSafety_Accidents.zip
unzip DfTRoadSafety_Accidents.zip
 
# 영국 교통사고 사상자 정보
wget https://github.com/jypstory/bigdata_tech_lecture/raw/master/hadoop_lec/sample_data/DfTRoadSafety_Casualties.zip
unzip DfTRoadSafety_Casualties.zip
 
# 지역 코드
wget https://github.com/jypstory/bigdata_tech_lecture/raw/master/hadoop_lec/sample_data/DfTRoadSafety_District.zip
unzip DfTRoadSafety_District.zip

# [참고] 데이터 활용 가이드 : https://github.com/jypstory/bigdata_tech_lecture/raw/master/hadoop_lec/sample_data/Road-Accident-Safety-Data-Guide.xls
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
### 개발환경 세팅
- Ecllipse 다운로드

> URL : http://www.eclipse.org/downloads/packages/release/Luna/SR2

- workspace 생성 (각자 적절한 위치에 생성함)
<pre><code>
# eclipse 에서 workspace 지정
/Users/josh/myWorkspace/hadoop_lec

# hadoop lib 복사 
[hdfs@sandbox hadoop_edu]$ cp /usr/hdp/current/hadoop-client/client/*2.7.3.* /home/hdfs/hadoop_edu/lib
[hdfs@sandbox hadoop_edu]$ cd lib
[hdfs@sandbox lib]$ ls
hadoop-annotations-2.7.3.2.5.0.0-1245.jar           hadoop-mapreduce-client-common-2.7.3.2.5.0.0-1245.jar     hadoop-yarn-client-2.7.3.2.5.0.0-1245.jar
hadoop-auth-2.7.3.2.5.0.0-1245.jar                  hadoop-mapreduce-client-core-2.7.3.2.5.0.0-1245.jar       hadoop-yarn-common-2.7.3.2.5.0.0-1245.jar
hadoop-common-2.7.3.2.5.0.0-1245.jar                hadoop-mapreduce-client-jobclient-2.7.3.2.5.0.0-1245.jar  hadoop-yarn-registry-2.7.3.2.5.0.0-1245.jar
hadoop-hdfs-2.7.3.2.5.0.0-1245.jar                  hadoop-mapreduce-client-shuffle-2.7.3.2.5.0.0-1245.jar    hadoop-yarn-server-common-2.7.3.2.5.0.0-1245.jar
hadoop-mapreduce-client-app-2.7.3.2.5.0.0-1245.jar  hadoop-yarn-api-2.7.3.2.5.0.0-1245.jar
</code></pre>

- ftp 프로그램으로 jar파일을 hadoop_lec 폴더로 이동
> ftp 프로그램 url : 아래중 선택 <br> 
> wincp : https://winscp.net/eng/download.php<br>
> filezilla : https://filezilla-project.org/

> hadoop jar파일 바로 다운로드 <br>
> url : https://github.com/jypstory/bigdata_tech_lecture/raw/master/hadoop_lec/hadoop_lib/hadoop_lib.zip
        

### Eclipse에서 프로그램 작성
- hadoop lec 프로젝트 생성   
- Eclipse에서 hadoop jar 파일 외부  라이브러리 등록
- class 생성 및 코드 작성 "HdfsTest"
<pre><code>
package hdfs;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsTest {

	public static void main(String[] args) {
		try {
			Configuration conf = new Configuration();
			FileSystem hdfs = FileSystem.get(conf);
			Path inputPath = new Path("/user/hdfs/input/acc/Accidents_2005_2015.csv");
			Path outputPath = new Path("/user/hdfs/input/acc/Accidents_2005_2015_sun.csv");
			FSDataOutputStream outStream = hdfs.create(outputPath);
			BufferedReader br = new BufferedReader(new InputStreamReader(hdfs.open(inputPath)));
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] fields = line.split(",");
				if (fields[10].equals("1")) {
					outStream.writeUTF(line + "\n");
				}
			}
			outStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
</code></pre>

- hadoop jar 파일 생성   
> export > java > jar file > hadoop_lec.jar 

- hadoop_lec.jar 파일 실행을 위해 서버로 복사
> 위치 : /home/hdfs/hadoop_edu/jar

- run hdfs
<pre><code>
[hdfs@sandbox jar]$ cd /home/hdfs/hadoop_edu/jar
[hdfs@sandbox jar]$ hadoop jar hadoop_lec.jar HdfsTest
[hdfs@sandbox jar]$ hadoop fs -ls /user/hdfs/input/acc/
Found 2 items
-rw-r--r--   1 hdfs hdfs  244379141 2017-03-25 12:03 /user/hdfs/input/acc/Accidents_2005_2015.csv
-rw-r--r--   1 hdfs hdfs   27012378 2017-03-25 13:06 /user/hdfs/input/acc/Accidents_2005_2015_sun.csv

#input 확인
[hdfs@sandbox jar]$ hadoop fs -tail /user/hdfs/input/acc/Accidents_2005_2015.csv
9,566993,-3.136722,54.992202,98,3,2,1,01/12/2015,3,17:15,917,S12000006,3,75,6,60,0,-1,-1,0,0,0,6,4,2,0,0,2,1,
2015984137615,319301,566593,-3.262676,54.987365,98,3,2,1,02/12/2015,4,16:30,917,S12000006,4,721,6,30,0,-1,-1,0,0,5,4,2,2,0,0,2,1,
2015984139015,304440,580166,-3.499388,55.106659,98,3,1,1,13/12/2015,1,02:30,917,S12000006,3,709,6,60,0,-1,-1,0,0,0,6,7,4,0,0,2,2,
2015984139115,312087,570791,-3.376671,55.023855,98,3,3,1,11/12/2015,6,13:24,917,S12000006,3,75,6,60,0,-1,-1,0,0,0,1,1,2,0,0,2,1,
2015984139715,320671,569791,-3.242159,55.016316,98,3,2,1,02/12/2015,4,13:50,917,S12000006,4,722,6,60,3,4,6,0,0,0,1,1,2,0,0,2,1,
2015984140215,311731,586343,-3.387067,55.163502,98,2,1,4,23/12/2015,4,00:01,917,S12000006,2,74,3,70,0,-1,-1,0,0,0,6,4,2,0,0,2,1,
2015984140515,328273,570137,-3.123385,55.020580,98,3,3,3,26/12/2015,7,12:40,917,S12000006,4,7076,6,60,5,4,2,74,0,0,1,2,2,0,0,2,1,
2015984141415,314050,579638,-3.348646,55.103676,98,3,13,7,31/12/2015,5,16:37,917,S12000006,2,74,3,70,0,-1,-1,0,0,0,6,3,4,0,0,2,1,

#output 확인
[hdfs@sandbox jar]$ hadoop fs -tail /user/hdfs/input/acc/Accidents_2005_2015_sun.csv
46,585331,-2.997407,55.158219,98,2,1,1,19/07/2015,1,13:50,917,S12000006,3,7,9,30,3,4,6,0,0,0,1,1,2,7,0,2,1,
�2015984123615,331519,567775,-3.072060,54.999817,98,3,1,1,12/07/2015,1,17:00,917,S12000006,3,75,3,70,0,-1,-1,0,0,0,1,1,1,0,0,2,1,
2015984125315,330469,568884,-3.088740,55.009635,98,1,1,5,02/08/2015,1,19:05,917,S12000006,2,74,3,70,5,4,6,0,0,0,1,1,1,0,0,2,1,
�2015984127515,319848,566515,-3.254108,54.986752,98,3,2,1,23/08/2015,1,14:00,917,S12000006,4,721,6,30,3,4,6,0,0,0,1,1,1,0,0,2,2,
�2015984128215,327982,570650,-3.128065,55.025147,98,1,1,2,30/08/2015,1,06:00,917,S12000006,2,74,3,70,0,-1,-1,0,0,0,1,1,1,0,0,2,1,
�2015984130315,312312,605860,-3.384031,55.338930,98,3,2,1,27/09/2015,1,13:50,917,S12000006,3,708,6,60,0,-1,-1,0,0,0,1,1,1,0,0,2,2,
�2015984131215,328774,567001,-3.114769,54.992477,98,3,1,1,27/09/2015,1,06:00,917,S12000006,3,75,6,60,0,-1,-1,0,0,0,6,1,1,0,0,2,1,
�2015984139015,304440,580166,-3.499388,55.106659,98,3,1,1,13/12/2015,1,02:30,917,S12000006,3,709,6,60,0,-1,-1,0,0,0,6,7,4,0,0,2,2,
</code>
</pre>

## hdfs 실습 - MR
- MapReduce Application 다운로드
> wget https://github.com/jypstory/bigdata_tech_lecture/raw/master/hadoop_lec/build/hadoop_lec.jar

<br>
> 다운로드 받은 jar 파일을 sandbox의 /home/hdfs/hadoop_edu/jar 폴더로 복사 

- Driver Class의 실행 (실습 1)
<pre><code>
[hdfs@sandbox jar]$ cd /home/hdfs/hadoop_edu/jar
[hdfs@sandbox jar]$ hadoop jar hadoop_lec.jar skill.coach01.TestDriver -D inputPath=/user/hdfs/input/acc/Accidents_2005_2015.csv -D outputPath=/user/hdfs/output/result01 -D numberReduceTesks=3
</code></pre>

- input file 확인     
<pre><code>
[hdfs@sandbox jar]$ hadoop fs -tail /user/hdfs/input/acc/Accidents_2005_2015.csv
9,566993,-3.136722,54.992202,98,3,2,1,01/12/2015,3,17:15,917,S12000006,3,75,6,60,0,-1,-1,0,0,0,6,4,2,0,0,2,1,
2015984137615,319301,566593,-3.262676,54.987365,98,3,2,1,02/12/2015,4,16:30,917,S12000006,4,721,6,30,0,-1,-1,0,0,5,4,2,2,0,0,2,1,
2015984139015,304440,580166,-3.499388,55.106659,98,3,1,1,13/12/2015,1,02:30,917,S12000006,3,709,6,60,0,-1,-1,0,0,0,6,7,4,0,0,2,2,
2015984139115,312087,570791,-3.376671,55.023855,98,3,3,1,11/12/2015,6,13:24,917,S12000006,3,75,6,60,0,-1,-1,0,0,0,1,1,2,0,0,2,1,
2015984139715,320671,569791,-3.242159,55.016316,98,3,2,1,02/12/2015,4,13:50,917,S12000006,4,722,6,60,3,4,6,0,0,0,1,1,2,0,0,2,1,
2015984140215,311731,586343,-3.387067,55.163502,98,2,1,4,23/12/2015,4,00:01,917,S12000006,2,74,3,70,0,-1,-1,0,0,0,6,4,2,0,0,2,1,
2015984140515,328273,570137,-3.123385,55.020580,98,3,3,3,26/12/2015,7,12:40,917,S12000006,4,7076,6,60,5,4,2,74,0,0,1,2,2,0,0,2,1,
2015984141415,314050,579638,-3.348646,55.103676,98,3,13,7,31/12/2015,5,16:37,917,S12000006,2,74,3,70,0,-1,-1,0,0,0,6,3,4,0,0,2,1,
</code></pre>

- check output file
<pre><code>
hdfs@sandbox jar]$ hadoop fs -tail /user/hdfs/output/result01/part-r-00000
2007:9 = 4115
2008:1 = 132513
2008:2 = 21984
2008:3 = 719
2008:4 = 2861
</code></pre>

<br>

- Driver Class의 실행 (실습 2)
<pre><code>
[hdfs@sandbox jar]$ hadoop jar hadoop_lec.jar skill.coach02.TestDriver -D inputPath=/user/hdfs/input/acc/Accidents_2005_2015.csv -D outputPath=/user/hdfs/output/result02 -D numberReduceTesks=3
</code></pre>

- check output file
<pre><code>
[hdfs@sandbox jar]$ hadoop fs -tail /user/hdfs/output/result02/part-r-00000

2007:9 = 4115
2008:1 = 132513
2008:2 = 21984
2008:3 = 719
2008:4 = 2861
2008:5 = 3130
</code></pre>


## hdfs 실습 - Join
- copy District.txt file to sandbox
<pre><code>
[hdfs@sandbox data]$ hadoop fs -mkdir -p /user/hdfs/input/dist
[hdfs@sandbox data]$ hadoop fs -copyFromLocal ./District.txt /user/hdfs/input/dist/.
[hdfs@sandbox data]$ hadoop fs -ls /user/hdfs/input/dist
Found 1 items
-rw-r--r--   1 hdfs hdfs       6628 2017-03-25 14:21 /user/hdfs/input/dist/District.txt
</code></pre>


- Driver Class의 실행 (실습 3)
<pre><code>
[hdfs@sandbox ~]$ cd /home/hdfs/hadoop_edu/jar
[hdfs@sandbox jar]$ hadoop jar hadoop_lec.jar skill.coach03.TestDriver -D inputPath1=/user/hdfs/input/acc/Accidents_2005_2015.csv -D inputPath2=/user/hdfs/input/cas/Casualties_2005_2015.csv -D cachePath=/user/hdfs/input/dist/District.txt -D tempPath=/user/hdfs/output/result03_1 -D outputPath=/user/hdfs/output/result03_2 -D numReduceTasks=3
</code></pre>

- check output file
<pre><code>
[hdfs@sandbox jar]$ hadoop fs -tail /user/hdfs/output/result03_2/part-r-00000
Swale,Male : 3331
Swansea,Female : 4443
Swansea,Male : 5234
Tandridge,Female : 2628
Teesdale,Male : 284
Teignbridge,Female : 2229
Teignbridge,Male : 3218
</code></pre>

<br>

## Yarn Application의 실행


>yarn jar simple-yarn-app-1.1.0.jar com.hortonworks.simpleyarnapp.Client /bin/date 2 hdfs:///test/simple-yarn-app-1.1.0.jar

<br>




