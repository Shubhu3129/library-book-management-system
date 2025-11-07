# Library Book Management (Java Spring Boot)

A production-ready starter for a web-based Library Book Management System.

## Features
- Book catalog (add/update/delete/search)
- Book issue & return with automatic due dates
- Overdue listing & fine calculation
- Role-based access (LIBRARIAN, STUDENT) with HTTP Basic auth
- Student can see only their issue records via `/api/issues/my`
- Reports for inventory & overdue
- H2 in-memory DB by default (Postgres-ready with minimal changes)
- Daily scheduled log reminder for overdue items

## Quickstart
1. **Requirements**: Java 17+, Maven.
2. **Build & Run**
   ```bash
   mvn spring-boot:run
   ```
3. **Sign in** using HTTP Basic:
   - librarian / password (role LIBRARIAN)
   - alice / password (role STUDENT)
4. **H2 Console**: `http://localhost:8080/h2-console` (JDBC URL `jdbc:h2:mem:librarydb`, user `sa`, no password).

## Example API Calls
- List books (any authenticated user):
  ```bash
  curl -u alice:password http://localhost:8080/api/books
  ```
- Search:
  ```bash
  curl -u alice:password "http://localhost:8080/api/books?q=algo"
  ```
- Add book (LIBRARIAN):
  ```bash
  curl -u librarian:password -X POST -H "Content-Type: application/json"   -d '{"title":"DB Systems","author":"Ramakrishnan","isbn":"9780072465631","category":"Databases"}'   http://localhost:8080/api/books
  ```
- Issue a book to a student (LIBRARIAN):
  ```bash
  curl -u librarian:password -X POST http://localhost:8080/api/issues/issue/1/to/alice
  ```
- Return the book:
  ```bash
  curl -u librarian:password -X POST http://localhost:8080/api/issues/return/1
  ```
- View my issues (STUDENT):
  ```bash
  curl -u alice:password http://localhost:8080/api/issues/my
  ```
- Calculate fine:
  ```bash
  curl -u librarian:password http://localhost:8080/api/issues/1/fine
  ```
- Reports:
  ```bash
  curl -u librarian:password http://localhost:8080/api/reports/inventory
  curl -u librarian:password http://localhost:8080/api/reports/overdue
  ```
- Create new user (LIBRARIAN):
  ```bash
  curl -u librarian:password -X POST -H "Content-Type: application/json"   -d '{"username":"chitra","password":"password","role":"STUDENT","fullName":"Chitra","email":"c@example.com"}'   http://localhost:8080/api/users
  ```

## Notes
- Configurable settings:
  - `app.loan-days` (default 14 days)
  - `app.fine-per-day` (default 2 units/day)
- To switch to Postgres, add driver dependency and change `spring.datasource` in `application.yml`.

## Tests
Add your tests under `src/test/java`. Starter uses Spring Boot Test.

---
Made for Aaryan: clean structure, minimal setup, extendable.
