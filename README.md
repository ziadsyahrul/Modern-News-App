# 📱 Modern News App - Offline First (Clean Architecture)

A high-performance Android news application built with **Modern Android Development (MAD)** practices. This project demonstrates the implementation of an **Offline-First** strategy, ensuring a seamless user experience even without an internet connection.



## 🚀 Features
- **Offline-First Approach**: View previously loaded news anytime, anywhere.
- **Smart Pagination**: Implementing `RemoteMediator` for seamless data fetching and local caching.
- **Modern UI**: Fully built with **Jetpack Compose** for a declarative and reactive interface.
- **Clean Architecture**: Separation of concerns into Data, Domain, and Presentation layers.

## 🛠 Tech Stack
- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Dependency Injection**: Dagger-Hilt
- **Database**: Room (with Paging 3 integration)
- **Networking**: Retrofit & OkHttp
- **Asynchronous**: Kotlin Coroutines & Flow
- **Architecture**: MVVM + Clean Architecture

---

## 🏗 Architecture Overview
This project follows the **Clean Architecture** principles to ensure scalability and testability:

1. **Domain Layer**: Contains the core business logic (Models, Repository Interfaces).
2. **Data Layer**: Responsible for data sources (Remote API via Retrofit and Local DB via Room).
3. **Presentation Layer**: UI logic using StateFlow and Jetpack Compose.



## 🔄 The Offline-First Sync Logic
The app uses a **Single Source of Truth (SSOT)** pattern via the `RemoteMediator`:
1. UI requests data from the **Pager**.
2. **Pager** checks the **Local Database (Room)**.
3. If the database is empty or needs more data, **RemoteMediator** fetches from **NewsAPI**.
4. Data from the API is saved directly into **Room**.
5. UI automatically updates because it is observing the **Local Database**.

---

## 📸 Screenshots

<p align="center">
  <img src="https://github.com/user-attachments/assets/a7aaaa88-f7e7-4df9-b7ee-720cf9493e71" width="300" alt="News List" />
  <img src="https://github.com/user-attachments/assets/28eec636-a8c8-470e-886a-891dbd5b58e0" width="300" alt="Detail News" />
  <br>
  <em>Main News List & Article Detail with Offline Support</em>
</p>

## ⚙️ Setup Instructions
1. Clone this repository.
2. Get an API Key from [NewsAPI.org](https://newsapi.org/).
3. Replace the `apiKey` in `NewsRepositoryImpl.kt`.
4. Build and Run!

---
*Created by [Ziad] - Professional Android Developer Portfolio.*
