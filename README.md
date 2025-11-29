# 🧑‍🍳 **TheMealDB Explorer – Full Stack Project (Spring Boot + Redis + React)**

This is my implementation of **TheMealDB Explorer**, a full-stack project built using:

* **Spring Boot** (Backend)
* **Redis** (Caching Layer)
* **React + Vite + Tailwind** (Frontend)

The goal of the project was simple:
👉 *fetch meals, categories, and recipes from TheMealDB API and display them in a clean UI — but in a faster and optimized way.*

So instead of calling TheMealDB directly from frontend, I built my own backend layer with caching, error handling, and simplified API endpoints.
# 🚀 Features

### ✔ Search for meals

Type a keyword like “chicken” → see matching meals.

### ✔ Browse categories

Explore by category like Chicken, Beef, Dessert, etc.

### ✔ View meals inside a category

### ✔ Meal Details

Ingredients, instructions, and YouTube tutorial.

### ✔ Random Meal

“I’m feeling hungry” button → shows something random.

### ✔ Redis Caching (Performance Boost)

All API responses are cached for 30 minutes.

### ✔ Proper Error Handling

Clean JSON responses:

```json
{
  "success": false,
  "error": "No meals found"
}
```

### ✔ Modern UI

Built using Tailwind + React Router.

---

# 🏗️ **Project Architecture (Simple Explanation)**

```
React UI  →  My Backend  →  Redis Cache  →  TheMealDB API
```

### Why not call TheMealDB directly from frontend?

Because:

* I want caching
* I want proper error messages
* I want cleaner endpoints
* I want to avoid CORS issues
* I want to meet assignment requirements
* And I want the project to feel “mine”, not just API forwarding

---

# 🗂️ **Backend Endpoints**

| Endpoint                            | Description           |
| ----------------------------------- | --------------------- |
| **GET /api/meals?search=NAME**      | Search meals          |
| **GET /api/categories**             | Get categories        |
| **GET /api/categories/{cat}/meals** | Meals inside category |
| **GET /api/meals/{id}**             | Meal details          |
| **GET /api/meals/random**           | Random meal           |

All responses follow a single predictable format:

```json
{
  "success": true,
  "data": { ... },
  "error": null
}
```

---

# ⚙️ **Backend Tech Stack**

* **Spring Boot**
* **Redis (Caching)**
* **RestTemplate**
* **GlobalExceptionHandler**
* **ApiResponse wrapper**
* **Java 21**

Backend is written in clean, structured layers:

* `controller/` → REST endpoints
* `service/` → business logic + caching
* `config/` → Redis config
* `exception/` → centralized error handling
* `model/` → ApiResponse wrapper

---

# 🧠 **How the System Works (Human Explanation)**

1. User types something like: **chicken**
2. React sends request → `GET /api/meals?search=chicken`
3. Backend checks Redis

   * If cached → return instantly
   * If not → call TheMealDB API → save to Redis
4. Backend sends clean JSON back
5. Frontend renders the UI

Basically:

👉 The backend is the smart middle-layer
👉 Redis is the speed booster
👉 React is the pretty face

---

# 🐳 **Redis Setup (Simple)**

If you're using Docker:

```bash
docker run -d -p 6379:6379 redis
```

That’s literally all.

---

# 🎨 **Frontend Tech Stack**

* React (Vite)
* Tailwind CSS
* React Router
* Axios

Pages located in:

```
src/pages/
```

Reusable components:

```
src/components/Navbar.jsx
```

---

# 🔥 **Why This Project is Good for Interviews**

* Shows full-stack ability
* Shows caching knowledge
* Shows clean API design
* Shows error handling
* Shows React + Spring Boot integration
* Shows how you optimize external API calls
* Simple enough to explain clearly

Whenever interviewer asks:

> “Why did you build your own backend?”

You can reply:

> “To add caching, validation, simplified endpoints, error handling, and avoid exposing third-party APIs directly.”

---

# 📦 **How to Run**

### Backend:

```
mvn spring-boot:run
```

### Redis:

```bash
docker run -d -p 6379:6379 redis
```

### Frontend:

```
npm install
npm run dev
```

---

# 🤝 Final Words

This project was built with the intention of keeping things:

* clean
* understandable
* optimized
* and professional
