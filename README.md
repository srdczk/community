## 社区项目

## 资料
[Spring 文档](https://spring.io/guides)

[Spring web 文档](https://spring.io/guides/gs/serving-web-content/)

[Spring MVC 文档(配置拦截器)](https://docs.spring.io/spring/docs/5.0.3.RELEASE/spring-framework-reference/web.html#spring-web)

[elastic 中文社区](https://elasticsearch.cn/)

[文档](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)

[Bootstrap 中文文档](https://v3.bootcss.com/)

[Github OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)

[Maven 仓库](https://mvnrepository.com/)

[Flyway](https://flywaydb.org/)
## 工具
[H2数据库](http://www.h2database.com/html/main.html)

## 脚本

```sql
CREATE TABLE USER(
    ID INT DEFAULT AUTO_INCREMENT PRIMARY KEY,
    ACCOUNT_ID VARCHAR(100),
    NAME VARCHAR(50),
    TOKEN CHAR(50),
    GMT_CREATE BIGINT,
    GMT_MODIFIED BIGINT
)
ALTER TABLE USER ADD bio VARCHAR(256) NULL;

```
