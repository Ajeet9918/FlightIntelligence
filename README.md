# ✈️ Airline Booking App — Java / Spring Boot

Migrated from Python (Flask + Jinja2 + pandas) to **Java 17 + Spring Boot 3 + Thymeleaf**.

---

## 🔄 Stack Migration Map

| Python / Flask                  | Java / Spring Boot                    |
|---------------------------------|---------------------------------------|
| Flask                           | Spring Boot (spring-boot-starter-web) |
| Jinja2 templates                | Thymeleaf templates                   |
| `requests.get()` + `.json()`   | `RestTemplate` + Jackson `ObjectMapper` |
| pandas DataFrame                | `List<RouteStats>` + Stream API       |
| `@app.route('/', ...)`         | `@GetMapping` / `@PostMapping`        |
| `render_template(tpl, **kw)`   | `model.addAttribute()` + return view  |
| `requirements.txt`             | `pom.xml` (Maven)                     |
| `python app.py`                | `mvn spring-boot:run`                 |

---

## 📁 Project Structure

```
airline-booking-app/
├── pom.xml
└── src/main/
    ├── java/com/airline/
    │   ├── AirlineBookingApplication.java   ← main()
    │   ├── controller/
    │   │   └── FlightController.java        ← GET / and POST /results
    │   ├── service/
    │   │   └── AviationService.java         ← API + data logic
    │   └── model/
    │       ├── RouteStats.java              ← route + count
    │       └── PriceTrend.java             ← route + prices + days
    └── resources/
        ├── application.properties
        └── templates/
            ├── index.html                   ← home page (Thymeleaf)
            └── results.html                 ← results page (Thymeleaf)
```

---

## 🚀 Running the App

### Prerequisites
- Java 17+
- Maven 3.8+

### Steps
```bash
# Clone / extract the project
cd airline-booking-app

# Run with Maven
mvn spring-boot:run

# Or build a JAR first
mvn clean package
java -jar target/airline-booking-app-1.0.0.jar
```

Open your browser at **http://localhost:8080**

---

## 🔧 Configuration

Edit `src/main/resources/application.properties`:

```properties
server.port=8080              # change port here
spring.thymeleaf.cache=false  # set true in production
```

Replace the API key in `AviationService.java`:
```java
private static final String API_KEY = "your_aviationstack_key";
```

---

## 📦 Dependencies (pom.xml)

| Dependency                          | Purpose                            |
|-------------------------------------|------------------------------------|
| spring-boot-starter-web             | HTTP server + REST (replaces Flask)|
| spring-boot-starter-thymeleaf       | HTML templating (replaces Jinja2)  |
| jackson-databind                    | JSON parsing (replaces json())     |
| lombok                              | Reduces boilerplate getters/setters|
