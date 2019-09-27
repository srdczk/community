## 社区项目

## 资料
[Spring 文档](https://spring.io/guides)

[Spring web 文档](https://spring.io/guides/gs/serving-web-content/)

[Spring MVC 文档(配置拦截器)](https://docs.spring.io/spring/docs/5.0.3.RELEASE/spring-framework-reference/web.html#spring-web)

[elastic 中文社区](https://elasticsearch.cn/)

[Bootstrap 中文文档](https://v3.bootcss.com/)

[Github OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)

[Maven 仓库](https://mvnrepository.com/)

[Flyway](https://flywaydb.org/)
## 工具
[H2数据库](http://www.h2database.com/html/main.html)

## 脚本

```sql
CREATE TABLE user
(
  id INT AUTO_INCREMENT PRIMARY KEY,
  account_id VARCHAR(100),
  name VARCHAR(50),
  token CHAR(50),
  gmt_create BIGINT,
  gmt_modified BIGINT,
  UNIQUE (name)
);

ALTER TABLE USER ADD bio VARCHAR(256) NULL;


CREATE TABLE question
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50),
    description TEXT,
    gmt_create BIGINT,
    gmt_modified BIGINT,
    creator INT,
    comment_count INT DEFAULT 0,
    view_count INT DEFAULT 0,
    like_count INT DEFAULT 0,
    tag VARCHAR(256)
);

ALTER TABLE USER ADD avatar VARCHAR(100) NULL;


```
```bash
mvn flyway:migrate 
```
