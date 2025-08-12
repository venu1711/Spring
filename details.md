# 1. How to connect a DB using sql scripts

## Steps:

1. Create `schema.sql`:
    - Define your table structure using standard SQL.
    - Example:
      ```sql
      DROP TABLE IF EXISTS widgets;
 
      CREATE TABLE widgets (
          id BIGINT NOT NULL AUTO_INCREMENT,
          name VARCHAR(100) NOT NULL,
          description TEXT,
          version INT NOT NULL,
          PRIMARY KEY (id)
      );
      ```

2. Create `data.sql`:
    - Insert sample data into your tables.
    - Example:
      ```sql
      INSERT INTO widgets (name, description, version) VALUES
      ('Gadget', 'A very useful gadget', 1),
      ('Tool', 'A powerful tool for work', 1),
      ('Device', 'A smart electronic device', 1);
      ```

3. Enable SQL Initialization in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
   spring.datasource.username=root
   spring.datasource.password=your_password
   spring.sql.init.mode=always
   ```

# 2. How to set up a JDBC Template

## Steps:

1. Add Dependency:
   - Add the JDBC API to the pom.xml file
   - Dependency:
   ```
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
   ```
   
2. Manually create a JDBCTemplate Class:
   - Create a config package
   - Create a DataBaseConfig Class
   - JDBCTemplate class:
   ```
   @Configuration
   public class DataBaseConfig{
      @Bean
      public JdbcTemplate jdbcTemplate(final DataSource dataSource){
         return jdbcTemplate(dataSource);
      }
   }
   ```

This creates a JDBCTemplate which can be used to query with the database

# 3. What are DAOs

DAO - Data Access Object

## Purpose:
   - All services connect to the database in the persistence layer.
   - Every service needs to create a separate JdbcTemplate to connect and query to the database.
   - This creates a lot of duplicate and unnecessary code.
   - To reduce this DAO are classes injected in-between service and persistence layer.
   - These connect the Java Objects created in service layer to the entities in the persistence layer.
   - This helps to maintain code which is way less than before.

# 4. JPA
Java Persistence API

## Note:
    - Will be deleting Author DAOimplTests and BookDaoImplTests which can be done by JPA in an easier way.
    - The AuthorDao exposes all the methods that are required to query the database.
    - No need for unit tests for the DAO layer as it is not a business logic layer.

## Purpose:
   - JPA is a specification for accessing, persisting, and managing data between Java objects and relational databases.
   - It provides a way to map Java objects to database tables and vice versa.
   - JPA allows developers to work with databases using Java objects, reducing the need for boilerplate SQL code.

