## Project Overview

This project is an application developed using modern Android technologies and relies on the MVVM architecture to organize code and manage data. The application aims to manage and track orders in a restaurant, with support for navigating between screens and updating the status of orders either via API calls or local storage using the Room database.

---
## Technologies and tools used

- **Navigation:**
The application uses the Navigation library to navigate between Fragments.

- **Local Database (Room):**
To store local data such as user data and orders.

- **Retrofit:**
To execute API calls and retrieve data from the server.

- **Paging:**
To efficiently process the display of a list of orders consisting of multiple items.

- **Architecture (MVVM):**
The application supports the use of ViewModel and LiveData to manage data and update the user interface interactively.



---

## Architecture

### 1. Data Layer

#### 1.1 APIs:
- **ChefApiService.java:**
It contains the definition of Endpoints to call:
- **Login (GetUserDetails)**
- **GetOrders)**
- **Update Order Status (SetOrderProcessed)**

- **RetrofitClient.java:**
A class to initialize the API service and create a Retrofit object.

#### 1.2 Local Database:
- **Entities:**
Create required entities such as:
- **UserEntity**
- **OrderMasterEntity**
- **OrderDetailsEntity**

- **DAOs:**
Define DAO interfaces to interact with stored data:
- **UserDao**
- **OrderDao**

- **AppDatabase:**
Abstract class extends RoomDatabase and contains list of entities and DAOs used.

#### 1.3 Repository:
- Create Repository classes to collect data from local and remote sources and handle errors.
- **UserRepository:** to handle login and logout.
- **ChefRepository:** to retrieve orders and update order status.

### 2. UI Layer

- **Splash Activity:**
Splash screen is designed using XML.

- **Login Activity:**
The login screen is designed using XML with an Event Handler and a ViewModel to store the data state and update the interface.

- **Main Activity:**
The main screen that displays the list of orders and includes:
- The screen is divided into three fragments:
1. **List Order Fragment:** to display the current orders.
2. **History Order Fragment:** to display the history of orders.
3. **Summary Order Fragment:** to display the summary of orders.
- Handling local and remote data sources via Repository.
- Support for automatic and manual data refresh.

---

## How to run and build

1. Clone the repository from GitHub.
2. Open the project using Android Studio.
3. Build the project and run the application on an Android device or emulator.
4. The code is updated frequently through separate Commits throughout the development process.