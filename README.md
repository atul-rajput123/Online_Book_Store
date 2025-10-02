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

## Project Structure (recommended)

```
bookstore/
├─ src/main/java/com/yourorg/bookstore
│  ├─ controller
│  ├─ model
│  ├─ repository
│  ├─ service
│  └─ config
├─ src/main/resources
│  ├─ templates   (Thymeleaf pages)
│  └─ static      (css, js, images)
├─ src/test/java
└─ pom.xml
```

---

## Database (example schema)

Use these example tables as a starting point. Adjust fields to your requirements.

```sql
CREATE TABLE `book` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `title` VARCHAR(255) NOT NULL,
  `author` VARCHAR(255),
  `description` TEXT,
  `price` DECIMAL(10,2) NOT NULL,
  `stock` INT DEFAULT 0,
  `category` VARCHAR(100),
  `image_url` VARCHAR(255),
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `customer` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) UNIQUE,
  `phone` VARCHAR(50),
  `city` VARCHAR(100),
  `house_no` VARCHAR(100),
  `street` VARCHAR(255)
);

CREATE TABLE `cart_item` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `customer_id` INT,
  `book_id` INT,
  `quantity` INT DEFAULT 1,
  FOREIGN KEY (customer_id) REFERENCES customer(id),
  FOREIGN KEY (book_id) REFERENCES book(id)
);

CREATE TABLE `order_table` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `customer_id` INT,
  `total_amount` DECIMAL(10,2),
  `status` VARCHAR(50) DEFAULT 'PLACED',
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE `order_item` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `order_id` INT,
  `book_id` INT,
  `quantity` INT,
  `price` DECIMAL(10,2),
  FOREIGN KEY (order_id) REFERENCES order_table(id),
  FOREIGN KEY (book_id) REFERENCES book(id)
);

CREATE TABLE `feedback` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `order_id` INT,
  `customer_id` INT,
  `rating` INT,
  `comment` TEXT,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (order_id) REFERENCES order_table(id),
  FOREIGN KEY (customer_id) REFERENCES customer(id)
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
