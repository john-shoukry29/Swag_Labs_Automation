# Swag Labs Automation Testing

Selenium WebDriver automation project for testing the [Swag Labs](https://www.saucedemo.com/) e-commerce application.

The project uses Java, Selenium WebDriver, TestNG, Maven, and the Page Object Model (POM). Test data is managed using JSON to support data-driven testing.

---

## 🛠️ Technologies & Tools

- **Java**
- **Selenium WebDriver**
- **TestNG**
- **Maven**
- **Jackson** – JSON data processing
- **Page Object Model (POM)**
- **Data-Driven Testing**
- **IntelliJ IDEA**
- **Google Chrome**
- **Git & GitHub**

---

## 📁 Project Structure

```text
SwagLabs/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── pages/
│   │       │   ├── loginPage.java
│   │       │   ├── inventoryPage.java
│   │       │   ├── CartPage.java
│   │       │   ├── CheckoutStepOnePage.java
│   │       │   └── CheckoutStepTwoPage.java
│   │       │
│   │       └── utils/
│   │           └── DataDriven.java
│   │
│   └── test/
│       ├── java/
│       │   ├── base/
│       │   │   └── BaseTest.java
│       │   │
│       │   └── tests/
│       │       ├── LoginTest.java
│       │       ├── InventoryTest.java
│       │       └── CartTest.java
│       │
│       └── testData/
│           └── testData.json
│
├── pom.xml
└── README.md
