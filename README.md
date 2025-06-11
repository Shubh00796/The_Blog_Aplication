# 📝 Blog Application

A modern, secure, and fully-featured backend for a **multi-user blogging platform**, built using industry standards. Designed to simulate production-level blogging engines like Medium or Hashnode, with authentication, content publishing, and comment moderation.

---

## 🚀 Features

- 🧑‍💻 **User Auth & Roles**: Register/Login with JWT auth
- ✍️ **CRUD APIs**: Blogs, Comments, Categories, Tags
- 📚 **Author-Specific Access**: Only owners can edit/delete their content
- 🔎 **Search & Filter**: Filter blogs by category, author, tags
- 🧾 **Pagination + Sorting** for all list endpoints
- 🛡️ **Secure Routes** with Role-Based Access
- 🪝 **Exception Handling, Validation, and Logging**
- 🔧 **Swagger-Enabled API Docs**

---

## 🧠 Architecture

Built using **Clean Architecture**:
- 🔹 `Controller` → API layer
- 🔹 `Service` → Business logic
- 🔹 `Repository` → Data layer
- 🔹 `DTO` + `Mapper` → Data transformation layer

✅ **SOLID**, **KISS**, **DRY**, **SoC**, and **Decoupling** implemented

---

## 🛠️ Tech Stack

| Technology | Usage |
|------------|-------|
| Java 17 | Core language |
| Spring Boot 3 | API development |
| Spring Security + JWT | Auth |
| PostgreSQL | Relational DB |
| MapStruct | Entity-DTO mapping |
| Lombok | Boilerplate reduction |
| Swagger | API testing |
| Flyway | DB migrations |
| Logback | Logging |

---

## 📁 Folder Structure

```shell
blog-application/
├── controller/
├── dto/
├── entity/
├── mapper/
├── repository/
├── service/
├── config/
├── exception/
├── util/
└── resources/
    ├── application.yml
    └── db/migration/


 📌 Real-World Brag Points
	•	Realistic multi-user publishing model
	•	Secure, modular, and testable
	•	Designed for real blogging SaaS platforms
	•	Scalable enough to integrate analytics, newsletters, AI writing assistant APIs


