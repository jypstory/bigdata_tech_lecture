# set environment

- Hortonworks sandbox 이미지 다운로드

> URL : https://ko.hortonworks.com/downloads/#sandbox<br>

> hortonworks Sandbox 이미지 로딩 <br> 
> 파일-가상시스템가져오기-이미지선택 

- 윈도우 hosts file 수정

> c://Windows/System32/Drivers/etc/hosts 파일에 아래 내용 추가
<pre><code>
127.0.0.1  sandbox.hortonworks.com
</code></pre>

- putty 접속  

> putty download url : http://www.chiark.greenend.org.uk/~sgtatham/putty/latest.html  <br>

> sandbox.hortonworks.com     Port : 2222<br>
> 계정 : root<br>
> 패스워드 : hadoop<br>
> 패스워드는 설정에 맞도록 변경 가능 (passwd root)<br>

- ambari password 세팅

<pre><code>
[root@sandbox ~]# ambari-admin-password-reset
Please set the password for admin: 
Please retype the password for admin:
</code></pre>

- ambari에 admin 로그인  

> http://sandbox.hortonworks.com:8080 


- git clone으로 데이터 load
> su - hdfs
> git clone https://github.com/jypstory/bigdata_tech_lecture.git

> 만약 접근이 안된다면 ping 8.8.8.8 수행으로 외부망 오픈 확인 (구글DNS)
> 안될경우 /etc/resolv.conf 파일 오픈 후   아래 내용 수정
<pre><code>
nameserver 168.126.63.1
</code></pre>
