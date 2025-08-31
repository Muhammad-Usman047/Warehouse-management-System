# Warehouse-management-System
Java-OOP-Project

# Warehouse Management System

A comprehensive Object-Oriented Java application for managing warehouse operations with a graphical user interface built using Swing. This system provides complete CRUD (Create, Read, Update, Delete) functionality with persistent data storage through file handling.

## Features

### 🔐 Authentication System
- login (Admin/Manager)
- Secure password handling
- Session management

### 📦 Inventory Management
- **Add** new items to inventory
- **View** all items with pagination support
- **Update** existing item details
- **Delete** items from inventory
- Search and filter functionality

### 📊 Dashboard & Reporting
- Visual overview of inventory status
- Stock level alerts
- Movement tracking history
- Summary statistics

### 💾 Data Persistence
- File handling for all data storage
- No database required
- Automatic backup system
- Data integrity checks

### 👥 User Management
- Add new system users
- Modify user permissions
- Track user activities

## Technologies Used

- **Java 17** - Core programming language
- **Swing GUI** - Frontend framework (JFrame, JTable, JLabel, etc.)
- **Object-Oriented Programming** - Design principles
- **File Handling** - Data persistence (no database)
- **NetBeans IDE** - Development environment

## System Architecture
WarehouseManagementSystem112/
│
├── 📁 Source Packages/
│ └── 📁 warehousemanagementsystem112/
│ ├── Dashboard.java # Main dashboard UI
│ ├── FileHandling.java # Data read/write operations
│ ├── InventoryLevel.java # Inventory management logic
│ ├── Item.java # Item entity class
│ ├── ManagerLogin.java # Admin authentication
│ ├── StockMovement.java # Stock transaction tracking
│ ├── UserLogin.java # User authentication
│ ├── WarehouseManagementSystem112.java # Main class
│ └── WarehouseOperation.java # Core operations logic
│
├── 📁 Test Packages/ # Unit tests
├── .gitignore # Git exclusion rules
└── README.md


## Installation & Setup

1. **Prerequisites**:
   - Java JDK 17 or higher
   - NetBeans IDE (recommended)

2. **Clone the repository**:
   ```bash
   git clone https://github.com/Muhammad-Usman047/Warehouse-management-System.git
