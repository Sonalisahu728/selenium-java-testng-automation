# Selenium Web Automation Framework

Automated UI tests for the public practice site https://www.saucedemo.com

## Tech Stack
- Java 17
- Selenium WebDriver 4
- TestNG
- Maven
- Page Object Model

## Test Scenarios
1. Valid login
2. Invalid login (data-driven: wrong password, unknown user, locked-out user)
3. Empty username / empty password validation
4. Logout
5. Products listed after login
6. Add product to cart
7. Remove product from cart
8. Cart page validation

## Automation Features
- Page Object Model (pages/ separated from tests/)
- Explicit waits (WebDriverWait + ExpectedConditions)
- ID / CSS selector locators
- TestNG assertions and @DataProvider
- Screenshot capture on test failure
- Headless mode support
- Maven execution via testng.xml

## Project Structure
```
src/test/java/
  base/BaseTest.java
  pages/LoginPage.java, ProductsPage.java
  tests/LoginTest.java, ProductTest.java
pom.xml
testng.xml
```

## How to Run
Requires JDK 17+, Maven, and Google Chrome.

```
mvn clean test
mvn clean test -Dheadless=true
```

Reports are generated in `target/surefire-reports/`. Failure screenshots are saved to `screenshots/`.
