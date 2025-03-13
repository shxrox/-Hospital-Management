# Hospital Management System

## 📌 Project Overview
The **Hospital Management System** is a simple Java-based application that helps manage hospital operations, including patient management, doctor information, and appointment scheduling. It uses **Java, MySQL, and JDBC** for database connectivity.

## 🏗️ Features
- **Add Patients** – Register new patients.
- **View Patients** – Display a list of all patients.
- **View Doctors** – Display a list of available doctors.
- **Book Appointments** – Schedule appointments between patients and doctors.
- **Check Doctor Availability** – Ensure a doctor is available before booking.

## 🛠️ Technologies Used
- **Java** (Core Java, JDBC)
- **MySQL** (MariaDB)
- **phpMyAdmin** (Database management)
- **JDBC** (Database connection)

## 📂 Project Structure
```
HospitalManagementSystem/
├── src/
│   ├── HospitalManagement.java  # Main application
│   ├── Patient.java             # Handles patient operations
│   ├── Doctors.java             # Handles doctor operations
│   ├── DatabaseConnection.java  # Manages MySQL connection
├── database/
│   ├── hospital.sql             # Database schema & sample data
├── README.md                    # Project documentation
└── .gitignore                    # Git ignored files
```

## 🛠️ Database Setup
### 1️⃣ **Create Database**
```sql
CREATE DATABASE hospital;
USE hospital;
```

### 2️⃣ **Create Tables**
```sql
CREATE TABLE patients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(10) NOT NULL
);

CREATE TABLE doctors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    dept VARCHAR(250) NOT NULL
);

CREATE TABLE appointments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    appointment_date DATE NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES patients(id),
    FOREIGN KEY (doctor_id) REFERENCES doctors(id)
);
```

### 3️⃣ **Insert Sample Data**
```sql
INSERT INTO doctors (name, dept) VALUES
('Dr. John Smith', 'Cardiology'),
('Dr. Sarah Johnson', 'Neurology'),
('Dr. Michael Brown', 'Orthopedics'),
('Dr. Emily Davis', 'Pediatrics'),
('Dr. David Wilson', 'General Surgery');

INSERT INTO patients (name, age, gender) VALUES
('Alice Brown', 30, 'Female'),
('Bob Smith', 45, 'Male'),
('Charlie Wilson', 28, 'Male'),
('Diana White', 38, 'Female'),
('Edward Green', 50, 'Male');
```

## 🔌 How to Run the Project
### 1️⃣ **Clone the Repository**
```sh
git clone https://github.com/yourusername/HospitalManagementSystem.git
cd HospitalManagementSystem
```

### 2️⃣ **Import the Project**
- Open **IntelliJ IDEA** or **Eclipse**.
- Load the **src/** folder into your Java project.

### 3️⃣ **Set Up Database Connection**
Modify `DatabaseConnection.java` with your **MySQL credentials**:
```java
private static final String URL = "jdbc:mysql://localhost:3306/hospital";
private static final String USER = "root";
private static final String PASSWORD = "";
```

### 4️⃣ **Compile & Run**
```sh
javac -cp .:mysql-connector-java.jar src/HospitalManagement.java
java -cp .:mysql-connector-java.jar src/HospitalManagement
```

## 🚀 Future Enhancements
- Add **Graphical User Interface (GUI)** using Java Swing or JavaFX.
- Implement **Admin Authentication** for secure access.
- Add **Appointment Cancellation & Rescheduling**.



