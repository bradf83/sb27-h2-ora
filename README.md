# Spring Boot 2.7.0 H2 Oracle Compatibility Issue

When upgrading one of my mature Spring Boot projects to 2.7.0 I ran into an issue with the new version of H2.

I currently use the H2 compatibility mode for Oracle for running tests. I do this by adding `;MODE=Oracle;` to my URL.

This no longer seems to work properly with Spring Data when Spring Data uses the `LIMIT` clause, during paging for example.

I have created this repository to highlight the issue starting with a project generated from [Spring Initializer](https://start.spring.io/).

# Reproduce the Issue

- Run the single test in `DemoApplication.java`.
  - You will notice the following in the test log output
    ```
    Syntax error in SQL statement "select product0_.id as id1_0_, product0_.name as name2_0_ from product product0_ [*]limit ?"; SQL statement:
    select product0_.id as id1_0_, product0_.name as name2_0_ from product product0_ limit ? [42000-212]
    ```
  - The `limit` clause is no longer supported by H2 in Oracle Compatibility mode.
  - If you change the Spring Boot version in the POM back to 2.6.8 the test will pass.

# Possible Workarounds

- Downgrade the version of H2 back to 1.4.200
- Use internal H2 APIs to add the LIMIT clause back to Oracle compatibility mode. Seems fragile, based on the following links:
  - [Stackoverflow Answer](https://stackoverflow.com/a/70702801)
  - [Google Group](https://groups.google.com/g/h2-database/c/yxnv64Ak-u8)
