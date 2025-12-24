# 🛍️ E-Commerce Product Microservice

A backend microservice built with **Java** and **Spring Boot** that manages product data. It integrates with the [Fake Store API](https://fakestoreapi.com/) to seed or fetch remote product information and persists it into a local database using **Spring Data JPA** for reliable storage and querying.

## 🚀 Key Features

* **External API Integration:** Consumes product data (titles, prices, descriptions, images) from `fakestoreapi.com`.
* **Data Persistence:** Automatically saves and updates fetched products into a local relational database (MySQL/PostgreSQL/H2) using **Spring Data JPA**.
* **CRUD Operations:** Exposes REST endpoints to create, read, update, and delete products from the local inventory.
* **DTO Pattern:** Uses Data Transfer Objects to decouple the internal database entities from external API responses.

## 🛠️ Tech Stack

* **Language:** Java 17+
* **Framework:** Spring Boot
* **ORM:** Spring Data JPA (Hibernate)
* **Database:** MySQL / PostgreSQL / H2 (Configure as needed)
* **HTTP Client:** RestTemplate
* **External Source:** Fake Store API


## 📋 Prerequisites

* [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/)
* A database installed locally (e.g., MySQL, PostgreSQL)
* [Maven](https://maven.apache.org/) or [Gradle](https://gradle.org/)

## 🔧 Installation & Setup

1.  **Clone the repository**
    ```bash
    git clone [https://github.com/shlokmotwani/product-service.git](https://github.com/shlokmotwani/product-service.git)
    cd product-service
    ```

2.  **Configure Database**
    Update `src/main/resources/application.properties` with your local database credentials.

    ```properties
    # Server Port
    server.port=8080

    # Database Configuration (Configure according to your local setup)
    spring.datasource.url=jdbc:mysql://localhost:3306/product_db
    spring.datasource.username=YOUR_DATABASE_USERNAME
    spring.datasource.password=YOUR_DATABASE_PASSWORD
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

    # Hibernate / JPA
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

3.  **Build and Run**
    ```bash
    mvn spring-boot:run
    ```

## ⚙️ Architecture

The application defines a flexible service layer strategy:

1.  **FakeStoreProductService**: Acts as the *Data Source Adapter*. It is responsible strictly for retrieving data from the external `fakestoreapi.com` (specifically generic `GET` requests).
2.  **SelfProductService**: Acts as the *Business Logic Handler*. It powers the application's exposed REST API, managing the flow of data between the controller and the local database repository.

## 🔌 API Endpoints

The `FakeStoreProductService` consumes the following upstream endpoints from [fakestoreapi.com](https://fakestoreapi.com/) :

| Action | HTTP Method | Upstream URL | Description |
| :--- | :--- | :--- | :--- |
| **Fetch All** | `GET` | `/products` | Retrieves the full list of products from the external provider. |
| **Fetch One** | `GET` | `/products/{id}` | Retrieves a single product by ID from the external provider. |


The following endpoints are powered by `SelfProductService` to manage your local inventory.

### 1. Create a Product
Adds a new product to the local database.
* **URL:** `/products`
* **Method:** `POST`
* **Body:**
    ```json
    {
      "title": "New Product",
      "price": 29.99,
      "category": "electronics",
      "description": "A great new item",
      "image": "[https://example.com/image.jpg](https://example.com/image.jpg)"
    }
    ```

### 2. Get All Products
Retrieves the list of all products stored in the local database.
* **URL:** `/products`
* **Method:** `GET`

### 3. Get Product by ID
Retrieves a specific product from the local database.
* **URL:** `/products/{id}`
* **Method:** `GET`

### 4. Update a Product
Updates an existing product's details.
* **URL:** `/products/{id}`
* **Method:** `PUT` (or `PATCH`)
* **Body:**
    ```json
    {
      "title": "Updated Product Title",
      "price": 25.00
    }
    ```

### 5. Delete a Product
Removes a product from the local database.
* **URL:** `/products/{id}`
* **Method:** `DELETE`


## 📄 License

This project is licensed under the MIT License.