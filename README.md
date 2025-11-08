# 🎞️ CineVerse Universe – Search & Report Edition

### 🎬 *A Java OOP Console Project Inspired by the World of Cinema*

---

## 🌟 Overview
**CineVerse Universe** is a console-based Java application that manages movie productions, films, and crew members such as actors, actresses, directors, editors, and cinematographers.  
It demonstrates the **core principles of Object-Oriented Programming (OOP)** — Abstraction, Inheritance, Polymorphism, and Encapsulation — along with **File Handling and Serialization** for persistent data storage.

> 🗣️ *“It’s where storytelling meets software — coding a universe of cinema.”*

---

## ⚙️ Technology Stack
- **Language:** Java  
- **Concepts Used:** OOP (Abstraction, Inheritance, Polymorphism, Encapsulation)  
- **File Handling:** Serialization (using `.ser` file for permanent data storage)  
- **Collections Framework:** ArrayList  
- **IDE:** IntelliJ IDEA / VS Code  

---

## 🧱 Architecture Overview

| Component | Description |
|------------|-------------|
| **Person (Abstract Class)** | Base class for all crew members. Defines shared fields like name and abstract method `work()`. |
| **Actor / Actress / Director / Cinematographer / Editor** | Subclasses extending `Person`. Each provides its own `work()` implementation. |
| **Movie** | Represents a film. Contains objects of crew roles – shows *composition* (a Movie *has* a Director, Actor, etc.). |
| **Production** | Holds multiple movies using `ArrayList`. Demonstrates *aggregation* and dynamic data storage. |
| **CineVerseUniversePro (Main Class)** | Controls the console UI, handles input, serialization, and searching. |

---

## 🧠 OOP Concepts in Action

| Concept | Where Used | Description |
|----------|-------------|-------------|
| **Encapsulation** | Private fields like `name`, `movies` | Data hidden and accessed through methods |
| **Inheritance** | `Actor extends Person` | Common attributes inherited from `Person` |
| **Polymorphism** | Overridden `work()` methods | Each role performs differently |
| **Abstraction** | `abstract class Person` | Defines general structure for crew members |
| **Composition** | `Movie` contains crew objects | “Has-a” relationships between classes |

---

## 🎮 Features

✨ **Production Management** – Create and manage multiple productions.  
🎬 **Movie Management** – Add new movies with full crew details.  
🔍 **Search System** – Search movies by title, director, or production.  
💾 **Persistent Storage** – All data saved to a serialized file (`productions_data.ser`).  
📊 **Reporting** – View all productions, movie counts, and search results neatly.  

---

## 🧮 User Menu
1. Add Production
2. Add Movie to Production
3. Show All Productions and Movies
4. Search Movie by Name
5. Show Movies by Director
6. Show All Movies under a Production
7. Show Total Movie Count
8. Save and Exit


---

## 💾 File Handling & Serialization
- **Serialization** saves all data (`Production`, `Movie`, `Person` objects) into `productions_data.ser`.  
- **Deserialization** loads them when the program starts again.  

Example:
```java
try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
    oos.writeObject(productions);
} catch (IOException e) {
    System.out.println("Error saving: " + e.getMessage());
}
```

