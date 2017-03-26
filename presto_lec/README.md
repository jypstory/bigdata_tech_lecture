# presto_lecture
## presto 설치    

- install presto

> wget https://repo1.maven.org/maven2/com/facebook/presto/presto-server/0.169/presto-server-0.169.tar.gz <br>
> tar -zxvf presto-server-0.169.tar.gz <br>
> cd presto-server-0.169 <br>
> mkdir data <br>
> mkdir etc <br>
> mkdir etc/catalog <br>


- create config.properties
> cd etc <br>
> vi config.properties
<pre><code>
coordinator=true
node-scheduler.include-coordinator=true
http-server.http.port=9090
query.max-memory=5GB
query.max-memory-per-node=1GB
discovery-server.enabled=true
discovery.uri=http://127.0.0.1:9090
</code></pre>

- create jvm.config
> cd etc <br>
> vi jvm.config
<pre><code>
-server
-Xmx16G
-XX:+UseG1GC
-XX:G1HeapRegionSize=32M
-XX:+UseGCOverheadLimit
-XX:+ExplicitGCInvokesConcurrent
-XX:+HeapDumpOnOutOfMemoryError
-XX:+ExitOnOutOfMemoryError
</code></pre>

- create node.properties
> cd etc <br>
> vi node.properties
<pre><code>
node.environment=production
node.id=n01
node.data-dir=/home/hdfs/presto/presto-server-0.169/data
</code></pre>

- create hive.properties
> cd etc/catalog <br>
> vi hive.properties
<pre><code>
connector.name=hive-hadoop2
hive.metastore.uri=thrift://sandbox.hortonworks.com:9083
hive.config.resources=/etc/hadoop/conf/core-site.xml,/etc/hadoop/conf/hdfs-site.xml
</code></pre>

- create mysql.properties
> cd etc/catalog <br>
> vi mysql.properties 
<pre><code>
connector.name=mysql
connection-url=jdbc:mysql://localhost:3306
connection-user=root
connection-password=hadoop
</code></pre>

- install presto-client
> cd bin <br>
> wget https://repo1.maven.org/maven2/com/facebook/presto/presto-cli/0.169/presto-cli-0.169-executable.jar <br>
> mv presto-cli-0.169-executable.jar presto

- start presto-server
> cd bin <br>
./launcher start <br>
http://localhost:9090

- connect presto-cli
> cd bin <br>
./presto --server localhost:9090 --catalog hive

## presto 실습
- query (hive)
<pre><code>
presto> help

presto> show catalogs;

presto> show schemas;
       Schema
--------------------
 default
 foodmart
 information_schema
 xademo
(4 rows)

presto:default> use foodmart;
presto:foodmart> select count(1) from customer ;
 _col0
-------
 10281
(1 row)

Query 20170325_180733_00013_6k6ej, FINISHED, 1 node
Splits: 18 total, 18 done (100.00%)
0:01 [10.3K rows, 703KB] [19.3K rows/s, 1.29MB/s]

presto:foodmart> select * from customer limit 10;
 customer_id | account_num |   lname   |  fname  | mi |        address1        | address2 | address3 | address4 |    city     | state_province | postal_code | country | customer_region_id |    phon
-------------+-------------+-----------+---------+----+------------------------+----------+----------+----------+-------------+----------------+-------------+---------+--------------------+--------
           1 | 87462024688 | Nowmer    | Sheri   | A. | 2433 Bailey Road       |          |          |          | Tlaxiaco    | Oaxaca         | 15057       | Mexico  |                 30 | 271-555
           2 | 87470586299 | Whelply   | Derrick | I. | 2219 Dewing Avenue     |          |          |          | Sooke       | BC             | 17172       | Canada  |                101 | 211-555
           3 | 87475757600 | Derry     | Jeanne  |    | 7640 First Ave.        |          |          |          | Issaquah    | WA             | 73980       | USA     |                 21 | 656-555
           4 | 87500482201 | Spence    | Michael | J. | 337 Tosca Way          |          |          |          | Burnaby     | BC             | 74674       | Canada  |                 92 | 929-555
           5 | 87514054179 | Gutierrez | Maya    |    | 8668 Via Neruda        |          |          |          | Novato      | CA             | 57355       | USA     |                 42 | 387-555
           6 | 87517782449 | Damstra   | Robert  | F. | 1619 Stillman Court    |          |          |          | Lynnwood    | WA             | 90792       | USA     |                 75 | 922-555
           7 | 87521172800 | Kanagaki  | Rebecca |    | 2860 D Mt. Hood Circle |          |          |          | Tlaxiaco    | Oaxaca         | 13343       | Mexico  |                 30 | 515-555
           8 | 87539744377 | Brunner   | Kim     | H. | 6064 Brodia Court      |          |          |          | San Andres  | DF             | 12942       | Mexico  |                106 | 411-555
           9 | 87544797658 | Blumberg  | Brenda  | C. | 7560 Trees Drive       |          |          |          | Richmond    | BC             | 17256       | Canada  |                 90 | 815-555
          10 | 87568712234 | Stanz     | Darren  | M. | 1019 Kenwal Rd.        |          |          |          | Lake Oswego | OR             | 82017       | USA     |                 64 | 847-555
(10 rows)



