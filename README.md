# Online Book Store

**Project:** Online Book Store (User + Admin modules)

## Project Overview

This project is a full-stack online bookstore application with two main modules:

* **User module**: Customers can browse/search books, add/remove books to/from cart, checkout (Cash on Delivery), provide shipping details (name, city, house no, street, etc.), submit feedback after placing an order, and use a chatbot for help.
* **Admin module**: Admin can manage books (add, update, delete, search), view all orders, view customer feedback, manage customers, and log out.

This README explains the features, tech stack, setup, database, and how to run and contribute.

---

## 🛠️ Technologies Used

* **Frontend:** HTML, CSS, Thymeleaf
* **Backend:** Spring Boot (Spring MVC)
* **Database:** MySQL

---

## Key Features

### User Module

* View list of all available books (with pagination support).
* Search books by name (partial matches supported).
* Add books to a shopping cart.
* Update quantities or remove single books from cart.
* Clear all items from cart at once.
* Checkout with a simple form collecting customer details (name, city, house no, street, etc.).
* Payment option: **Cash on Delivery (COD)** only.
* After order placement, automatically open a feedback form.
* Feedback submission leads to a Thank You page with a link back to the homepage.
* Chatbot integrated for user help (FAQ-like assistant).

### Admin Module

* CRUD operations for books (Add / Update / Delete / Search).
* View all customer orders and their details (shipping info, order items, order status).
* View customer feedback and mark or respond (optional UI).
* Manage customer accounts (view-only or simple actions).
* Secure admin login and logout.

---

## Project Structure

```
BOOKSTORE/
├─ .mvn
├─ .settings
├─ src/
│  ├─ main/
│  │  ├─ java/com/crni99/bookstore/
│  │  │  ├─ chatbotmodel/
│  │  │  │  ├─ ChatbotMenu.java
│  │  │  │  ├─ OrderMenu.java
│  │  │  │  ├─ OtherMenu.java
│  │  │  │  ├─ PaymentMenu.java
│  │  │  │  ├─ ReplacementMenu.java
│  │  │  │  └─ TrackOrderMenu.java
│  │  │  ├─ config/
│  │  │  ├─ controller/
│  │  │  │  ├─ BookController.java
│  │  │  │  ├─ CartController.java
│  │  │  │  ├─ ChatbotController.java
│  │  │  │  ├─ CheckoutController.java
│  │  │  │  ├─ FeedBackController.java
│  │  │  │  ├─ HomeController.java
│  │  │  │  ├─ LoginController.java
│  │  │  │  ├─ OrderController.java
│  │  │  ├─ model/
│  │  │  │  ├─ Book.java
│  │  │  │  ├─ Customer.java
│  │  │  │  ├─ CustomerBooks.java
│  │  │  │  ├─ FeedBack.java
│  │  │  │  └─ Order.java
│  │  │  ├─ repository/
│  │  │  │  ├─ BillingRepository.java
│  │  │  │  ├─ BookRepository.java
│  │  │  │  ├─ FeedBackRepository.java
│  │  │  │  └─ OrderRepository.java
│  │  │  ├─ security/
│  │  │  │  ├─ SecurityConfig.java
│  │  │  │  └─ SecurityWebApplicationInitializer.java
│  │  │  └─ service/
│  │  │     ├─ BillingService.java
│  │  │     ├─ BookService.java
│  │  │     ├─ ChatBotService.java
│  │  │     ├─ EmailService.java
│  │  │     ├─ FeedBackService.java
│  │  │     ├─ ShoppingCartService.java
│  │  │     └─ BookStoreApplication.java
│  │  ├─ resources/
│  │  │  ├─ db/
│  │  │  ├─ static/
│  │  │  │  ├─ css/
│  │  │  │  ├─ js/
│  │  │  │  └─ images/
│  │  │  ├─ templates/
│  │  │  │  ├─ cart.html
│  │  │  │  ├─ checkout.html
│  │  │  │  ├─ error.html
│  │  │  │  ├─ feedback.html
│  │  │  │  ├─ form.html
│  │  │  │  ├─ help.html
│  │  │  │  ├─ index.html
│  │  │  │  ├─ layout.html
│  │  │  │  ├─ list.html
│  │  │  │  ├─ login.html
│  │  │  │  ├─ order.html
│  │  │  │  ├─ orders.html
│  │  │  │  ├─ showFeedBack.html
│  │  │  │  └─ thankyou.html
│  │  │  └─ application.properties
├─ target/
├─ .checkstyle
├─ .classpath
├─ .gitignore
├─ .project
├─ Dockerfile
└─ mvnw

```

---

## Database

Use these example tables as a starting point. Adjust fields to your requirements.

```sql
CREATE TABLE users (
  username VARCHAR(255) PRIMARY KEY,
  password VARCHAR(255) NOT NULL,
  enabled TINYINT(1) NOT NULL
);

CREATE TABLE authorities (
  username VARCHAR(255) NOT NULL,
  authority VARCHAR(255) NOT NULL
);

CREATE TABLE books (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  authors VARCHAR(255) NOT NULL,
  isbn VARCHAR(255) NOT NULL,
  price DECIMAL(19,2) NOT NULL,
  published_on DATE NOT NULL,
  publisher VARCHAR(255) NOT NULL
);

CREATE TABLE customers (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  surname VARCHAR(50) NOT NULL,
  email VARCHAR(254) NOT NULL,
  phone_number VARCHAR(15) NOT NULL,
  city VARCHAR(60) NOT NULL,
  country_region VARCHAR(55) NOT NULL,
  postal_code VARCHAR(18) NOT NULL,
  street_and_house_number VARCHAR(100) NOT NULL
);

CREATE TABLE orders (
  id INT AUTO_INCREMENT PRIMARY KEY,
  order_date DATE NOT NULL,
  book_id BIGINT,
  customer_id BIGINT,
  ans VARCHAR(255),
  menu VARCHAR(255)
);

CREATE TABLE chatbot (
  id INT AUTO_INCREMENT PRIMARY KEY,
  ans VARCHAR(255),
  menu VARCHAR(255)
);

CREATE TABLE payment (
  id INT AUTO_INCREMENT PRIMARY KEY,
  ans VARCHAR(255),
  menu VARCHAR(255)
);

CREATE TABLE feed_back (
  id INT PRIMARY KEY,
  message VARCHAR(255),
  name VARCHAR(255)
);

CREATE TABLE replacement (
  id INT AUTO_INCREMENT PRIMARY KEY,
  ans VARCHAR(255),
  menu VARCHAR(255)
);

CREATE TABLE track (
  id INT AUTO_INCREMENT PRIMARY KEY,
  ans VARCHAR(255),
  menu VARCHAR(255)
);

CREATE TABLE orderes (
  id INT AUTO_INCREMENT PRIMARY KEY,
  ans VARCHAR(255),
  menu VARCHAR(255)
);

CREATE TABLE other (
  id INT AUTO_INCREMENT PRIMARY KEY,
  ans VARCHAR(255),
  menu VARCHAR(255)
);

```

---

## How to Run Locally (Spring Boot + MySQL)

1. **Prerequisites**

   * Java 11 or later
   * Maven
   * MySQL

2. **Clone repository**

```bash
git clone https://github.com/<your-username>/<your-repo>.git
cd <your-repo>
```

3. **Configure application properties**

Edit `src/main/resources/application.properties` and set your DB credentials:

```
spring.datasource.url=jdbc:mysql://localhost:3306/bookstore_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080
```

4. **Build & run**

```bash
mvn clean package
mvn spring-boot:run
```

Open `http://localhost:8080/` in your browser.

---

## Flows and UX Notes

* **Cart behavior**: Provide buttons for `Add to cart`, `Remove item`, `Decrease/Increase quantity`, and `Clear cart`.
* **Checkout**: Show a summary of the cart and a form asking for name, phone, city, house no, street, and a final `Place Order` button. Use server-side validation for required fields.
* **Order placement**: When the user confirms the order, save `order_table` and `order_item` rows and clear the user's cart. Then immediately show the feedback form. After feedback submission show Thank You page with a `Back to Home` link.
* **Feedback**: Keep rating optional but recommended (1–5). Store feedback linked to the order.
* **Chatbot**: Implement a simple chatbot panel (a floating widget) with FAQ-style answers. Optionally expand later.

---

## Admin Endpoints (suggested)

* `/admin/login` — Admin login page
* `/admin/books` — List books with search & pagination
* `/admin/books/new` — Add book form
* `/admin/books/{id}/edit` — Edit book
* `/admin/books/{id}/delete` — Delete book
* `/admin/orders` — List orders, view details
* `/admin/feedbacks` — View feedback

---

## Deployment

* Build JAR using `mvn package` and deploy to a server with Java installed.
* Alternatively use Docker: create a Dockerfile for the Spring Boot app and docker-compose for the app + MySQL.

---
