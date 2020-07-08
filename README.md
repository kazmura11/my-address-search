# 前提条件と前準備
Maria DB 10.4  
Java 8  
Spring Boot 2  

とりあえず使っているものは  
Thymeleaf  
Hibernate  
Vue  
JQuery DataTable  

起動前に下記の環境変数の設定とデータベース作成を実施する  

## 環境変数
システム環境変数 `PATH`: `C:\Program Files\MariaDB 10.4\bin`を追加

## ユーザとデータベースの作成
下記はbash前提 Windowsの場合はGit Bashが使える(mysqlでうまくいかない場合は winpty mysql)  
```
$ mysql -uroot -proot
CREATE USER 'demo'@'localhost' IDENTIFIED BY 'demo';
GRANT ALL PRIVILEGES ON *.* TO 'demo'@'localhost' IDENTIFIED BY 'demo' WITH GRANT OPTION;
FLUSH PRIVILEGES;

CREATE DATABASE `my_address_search` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE USER demo IDENTIFIED BY 'demo';
GRANT ALL ON my_address_search.* TO demo@'%' IDENTIFIED BY 'demo';
```

# Flyway Tips
Flywayを使うと自動で、flyway_schema_historyというディレクトリが作成される  
参考: http://www.kojion.com/posts/806  

# 郵便番号データについて
http://zipcloud.ibsnet.co.jp/
元のken-all.csvは様々問題があるので、よくある前処理を事前に行ってくれているCSVを利用しています。

# ログインユーザ
ID: demo@example.com  
PASSWORD: demo  

# 起動したらデータ投入

```bash
cd my-address-search/src/main/resources/db/data
bash import_x-ken-all.sh
```

### JQuery DataTablesメモ
https://datatables.net/manual/server-side  
https://github.com/darrachequesne/spring-data-jpa-datatables  
https://frontbackend.com/thymeleaf/spring-boot-bootstrap-thymeleaf-datatable  

デフォルトでは  
http://localhost:8080/foo?draw=1&column[0][data]=bar&order[0][column]=0&order[0][dir]=asc&start=100&length=100&search[value]=&search[regex]=false  
のようなクエリが生成される  
この中で、配列の配列（というより配列のハッシュなのだが…）`column[0][data]`はSpringでは対応していないので、`column[0].data`のように変換してあげる必要がある。  
こうすればJava側で`List<T>`で受け取れる。
そのために、[jquery.spring-friendly.js](https://github.com/darrachequesne/spring-data-jpa-datatables/blob/master/jquery.spring-friendly.js)を使用する。

### 気が向いたらやりたいこと

■編集・削除  
  
■Vue版でのソート  
https://github.com/xaksis/vue-good-table  

