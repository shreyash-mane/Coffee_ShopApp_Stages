# Coffee Shop Ordering System (Java, OOP)

This repository contains a **Java-based Coffee Shop Ordering System** developed as part of **group coursework at Heriot-Watt University**.  
The application demonstrates **object-oriented design**, **modular architecture**, and **file-based data persistence**, with a graphical user interface for ordering, processing, and reporting.

---

## 📌 Project Overview

The system allows customers to place orders through a graphical user interface, while employees can process orders and generate sales reports.  
It was developed using **Java (Swing)** following software engineering best practices, including UML-based design, modular packaging, testing, and version control.

---

## 📂 Project Structure

```
src/
 ├── main/java        # Application source code
 ├── main/resources  # Application resources
 ├── test/java       # Test classes
data/                # File-based storage (users, orders, menu items, completed orders)
```

---

## 📦 Package Structure

**Root Package:** `uk.ac.hw.group20`

- `admin` – Identity and user management  
- `errorhandler` – Custom exception handling  
- `main` – Main application logic and GUI  
- `order` – Order creation and management  
- `order.bill` – Billing functionality  
- `report` – Sales and order reporting  
- `utils` – Common utility classes  

---

## 🚀 How to Run the Application (Stage 2)

1. Download and extract the runnable JAR package  
2. Ensure the extracted folder contains:
   - `.jar` file  
   - `data/` directory  
3. Double-click the `.jar` file to launch the application  
4. Log in using the test credentials below  
5. Use the GUI menus to place, process, and report orders  

> ⚠️ **Note:** Credentials are provided for coursework demonstration purposes only.

---

## 🔐 Test Credentials (Case Sensitive)

### Customer
- **Username:** IMA  
- **Password:** IMA  

### Employee
- **Username:** admin  
- **Password:** password  

---

## 🛒 Application Features

### Make an Order
- Browse items by category (Beverage, Food, Other)
- Add items to cart and adjust quantities
- Complete and pay for orders through the GUI

### Process Orders
- View queued orders
- Process orders sequentially
- Archive completed orders automatically

### Reports
- View aggregated order reports
- Analyse total quantities and sales values

---

## 🧠 Software Engineering Practices Used
- Object-Oriented Programming (OOP)
- UML-based system design
- Modular package structure
- File-based persistence
- GUI development using Java Swing
- Incremental development and documentation
- Version control and collaborative development

---
