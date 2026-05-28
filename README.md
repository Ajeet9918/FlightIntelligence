# ✈️ SkyPulse — Airline Booking Intelligence App

A full-stack flight analytics web application built with **Java 17** and **Spring Boot 3**, integrating the AviationStack REST API to deliver real-time route insights, simulated price trends, and AI-generated travel intelligence.

---

## 🚀 Live Demo

> Coming soon — deploy on Railway or Render for a live URL.
https://flight-intelligence-production-0549.up.railway.app/
---

## 📸 Screenshots

| Homepage | Analytics Dashboard |
|---|---|
| Dark editorial split layout | Fixed sidebar + route table + price trends |

---

## 🧰 Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3, Spring MVC |
| Templating | Thymeleaf |
| Styling | Bootstrap 5, Custom CSS |
| HTTP Client | Spring RestTemplate |
| JSON Parsing | Jackson (ObjectMapper) |
| Build Tool | Maven |
| External API | AviationStack REST API |

---

## ✨ Features

- **Live Flight Data** — Fetches real-time global flight records via the AviationStack API on demand
- **Route Frequency Analysis** — Aggregates flight records using Java Stream API to rank the most traveled airport pairs
- **7-Day Price Trends** — Simulates per-route pricing data with color-coded bar charts (green = cheap, red = expensive)
- **AI Insights** — Generates travel intelligence highlighting high-demand routes and booking recommendations
- **Professional Dashboard UI** — Fixed sidebar navigation, ranked data tables with proportional bar indicators, and responsive card layout

---

## 📁 Project Structure

```
src/main/
├── java/com/airline/
│   ├── AirlineBookingApplication.java   ← Entry point
│   ├── controller/
│   │   └── FlightController.java        ← GET / and POST /results
│   ├── service/
│   │   └── AviationService.java         ← API calls + data logic
│   └── model/
│       ├── RouteStats.java              ← Route + frequency model
│       └── PriceTrend.java             ← Route + 7-day prices model
└── resources/
    ├── application.properties
    └── templates/
        ├── index.html                   ← Homepage (Thymeleaf)
        └── results.html                 ← Analytics dashboard (Thymeleaf)
```

---

## ⚙️ Architecture

The app follows a clean **MVC pattern** with a dedicated service layer:

```
Browser → FlightController → AviationService → AviationStack API
                ↓
          Thymeleaf Template (HTML rendered server-side)
```

- **Controller** — Handles HTTP GET/POST requests, delegates to service
- **Service** — All business logic: API calls, route analysis, price simulation, AI insights
- **Model** — Plain Java classes (`RouteStats`, `PriceTrend`) passed to templates
- **Templates** — Thymeleaf renders data server-side into HTML

---

## 🛠️ Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+

### Run Locally

```bash
# Clone the repository
git clone https://github.com/Ajeet9918/FlightIntelligence.git
cd FlightIntelligence

# Run with Maven
mvn spring-boot:run
```

Open your browser at **http://localhost:8080**

### Build JAR

```bash
mvn clean package
java -jar target/airline-booking-app-1.0.0.jar
```

---

## 🔧 Configuration

Edit `src/main/resources/application.properties`:

```properties
server.port=8080
spring.thymeleaf.cache=false   # set true in production
```

Replace the API key in `AviationService.java`:

```java
private static final String API_KEY = "your_aviationstack_api_key";
```

Get a free API key at [aviationstack.com](https://aviationstack.com)

---

## 📦 Dependencies

| Dependency | Purpose |
|---|---|
| `spring-boot-starter-web` | HTTP server + MVC |
| `spring-boot-starter-thymeleaf` | Server-side HTML templating |
| `jackson-databind` | JSON parsing from API responses |
| `lombok` | Reduces boilerplate getters/setters |

---

## 👤 Author

**Ajeet Kumar Gupta**
- GitHub: [@Ajeet9918](https://github.com/Ajeet9918)
- Email: mrajeetkumargupta8840@gmail.com

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
