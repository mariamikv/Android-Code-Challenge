# Android Code Challenge Assignment

An Android application built as part of a code challenge. This app demonstrates modern Android development practices including Jetpack Compose, modularization and reactive UI state management.

---
## Visual showcase
| Dark Theme                                           | Light Theme                                           |
|--------------------------------------------------|-------------------------------------------------|
| <video src="https://github.com/user-attachments/assets/cfed0d03-3965-46f5-8395-d20da272fb98" size="300"/> | <video src="https://github.com/user-attachments/assets/ebab0f2a-9241-4fea-bb08-6a8e9f6b87d1" size="300"/> |
---

## 🚀 Tech Stack

- **Kotlin**
- **Jetpack Compose** – Modern declarative UI toolkit
- **Hilt** – Dependency Injection
- **Retrofit** – Networking
- **Moshi** – JSON Parsing
- **Room** - Database
- **Jetpack Navigation 3** – Navigation for Compose
- **JUnit** – Unit testing framework

---

## 🧠 Architecture

### 🔗 Multi-Modular Architecture

The project is structured as a **multi-module Gradle project** to enforce clean separation of concerns, modular testing, and better build performance.

**Module Structure:**

<pre> 
  android-code-challenge/
  │ ├── app/ # Application entry point and DI setup
  │ ├── core/
  | │ ├── data/ # Retrofit, Moshi, API clients, database
  | │ ├── domain/ # State model and Repository interface
  | │ ├── navigation/ # Navigation setup, Routs, screen args
  | │ ├── ui/ # Shared UI Components
  │ ├── features/
  │ ├── Home/ # Start Destination
  │ │ ├── ui/ # Jetpack Compose screens
  │ │ ├── viewmodel/ # ViewModels and business logic
  │ │ ├── model/ # UiState, data models
  │ │ └── di/ # Hilt DI setup for the feature 
  | ├── Details/ 
  │ │ ├── ui/ # Jetpack Compose screens
  │ │ ├── viewmodel/ # ViewModels and business logic
  │ │ ├── model/ # UiState, data models
  │ │ └── di/ # Hilt DI setup for the feature 
</pre>


---

### 🧠 MVVM + MVI Hybrid Pattern

This project combines the strengths of **MVVM** and **MVI** to ensure a reactive and scalable UI architecture.

- **MVVM (Model-View-ViewModel)**  
  ViewModels expose state and handle business logic using Kotlin Coroutines and `StateFlow`.

- **MVI (Model-View-Intent)**  
  UI state is represented by immutable data classes (`state`) and updated in response to user/system actions. This leads to a predictable, unidirectional data flow.

### Clone the Repository

```bash
git clone https://github.com/mariamikv/Android-Code-Challenge.git
cd android-code-challenge
