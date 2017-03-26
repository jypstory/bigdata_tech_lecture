# presto_lecture

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
launcher start <br>
http://localhost:9090

- connect presto-cli
> cd bin <br>
./presto --server localhost:9090 --catalog hive

