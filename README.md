# Expense-Tracker API

A backend service built with java + Spring Boot for managing daily expenses,
featuring JWT authentication and PostgreSQL database support.

### Features (MVP)
- User Authentication (JWT)
- Add/View/Filter Expenses
- Monthly summary analytics

### Tech Stack
- Java 21
- Spring Boot
- PostgreSQL
- Spring Security + JWT

## Development updates
### 0.0.2-SNAPSHOT
1. Added User and Expense Repositories with JPA annotations
2. Added Dummy data with dataLoader and tested db connections
3. Created controller for User and Expense and tested get api using PathVariables
### 0.0.1-SNAPSHOT
1. Added User and Expense entities with JPA annotations 
2. Established PostgreSQL DB connection 
3. Fixed Expense.amount to BigDecimal for precise monetary values

More features and deployment coming soon.