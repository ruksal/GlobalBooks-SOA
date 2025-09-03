## GlobalBooks-SOA

A sample Service-Oriented Architecture (SOA) project for a fictitious GlobalBooks platform. It demonstrates SOAP and REST services, basic governance, orchestration (BPEL), and end-to-end testing artifacts.

### Monorepo layout

- **catalog-service**: SOAP service for book catalog (JAX-WS)
- **orders-service**: REST service for order management (Spring Boot)
- **payments-service**: Messaging/producer service (Spring Boot)
- **shipping-service**: Messaging/consumer service (Spring Boot)
- **orchestration**: BPEL process (`PlaceOrder.bpel`) and WSDLs for orchestration
- **governance**: Governance policy and SLA examples
- **tests**: Postman collection/environment and SoapUI project for integration testing

### Prerequisites

- Java 17+ (tested with Temurin/OpenJDK)
- Apache Maven 3.9+
- Git
- Optional for orchestration: Apache ODE (or compatible BPEL runtime)
- Optional for tests: Postman and SoapUI

### Quick start

Build all modules from the repo root:

```bash
mvn -q -DskipTests clean install
```

Run services in separate terminals (or background):

```bash
# Catalog (SOAP)
cd catalog-service && mvn -q spring-boot:run

# Orders (REST)
cd orders-service && mvn -q spring-boot:run

# Payments (producer)
cd payments-service && mvn -q spring-boot:run

# Shipping (consumer)
cd shipping-service && mvn -q spring-boot:run
```

Windows PowerShell users can run the same commands in PowerShell. Use separate windows/tabs for each service.

### Services overview

#### Catalog Service (SOAP)

- Entry WSDL: `catalog-service/src/main/resources/CatalogService.wsdl`
- Deployment: JAX-WS (endpoint configured via `sun-jaxws.xml` and `web.xml`)
- Security: simple handler in `catalog-service/src/main/java/.../security/SecurityHandler.java`
- Key classes: `CatalogService`, `CatalogServiceImpl`, `GetBookRequest/Response`, `SearchRequest/Response`

Run:

```bash
cd catalog-service
mvn -q spring-boot:run
```

WSDL should be accessible at runtime according to the configured servlet context (see `web.xml`).

#### Orders Service (REST)

- Spring Boot app exposing order APIs
- Key classes: `OrdersApplication`, `OrdersController`, `OrdersService`, `Order`, `OrderCreate`
- Configuration: `orders-service/src/main/resources/application.properties`

Run:

```bash
cd orders-service
mvn -q spring-boot:run
```

#### Payments Service (Producer)

- Spring Boot app simulating payment events
- Key classes: `PaymentsApplication`, `PaymentsProducer`
- Configuration: `payments-service/src/main/resources/application.properties`

Run:

```bash
cd payments-service
mvn -q spring-boot:run
```

#### Shipping Service (Consumer)

- Spring Boot app consuming shipment-related events
- Key classes: `ShippingApplication`, `ShippingConsumer`
- Configuration: `shipping-service/src/main/resources/application.properties`

Run:

```bash
cd shipping-service
mvn -q spring-boot:run
```

### Orchestration (BPEL)

- Artifacts in `orchestration/`
  - BPEL: `PlaceOrder.bpel`
  - WSDLs: `wsdl/CatalogService.wsdl`, `wsdl/OrdersService.wsdl`
  - Deployment descriptor: `deploy.xml`

To deploy, use a BPEL engine such as Apache ODE. Refer to your engine's documentation to deploy `deploy.xml` with the included BPEL and WSDLs. Ensure service endpoints match running services.

### Governance

- `governance/governance-policy.md`: Example policy document
- `governance/sla.json`: Sample SLA definition

### Testing

- Postman: `tests/GlobalBooks_Postman_Collection.json` and `tests/GlobalBooks_Postman_Environment.json`
- SoapUI: `tests/GlobalBooks_SOAPUI_Project.xml`

Import the artifacts into Postman/SoapUI, update environment variables/endpoints if needed, and run the test suites.

### Building and packaging

```bash
mvn -DskipTests clean package
```

Individual service JARs will be created under each module's `target/` directory. You can run a packaged service with:

```bash
java -jar target/<service-jar>.jar
```

### Configuration

- Each service has `src/main/resources/application.properties` (Spring Boot) or XML descriptors (JAX-WS) controlling ports, contexts, and dependencies.
- Adjust ports or credentials as needed before running multiple services concurrently.

### Contributing

1. Create a feature branch
2. Make changes with clear commits
3. Open a pull request against `main`

### License

This project is for educational/demo purposes. Provide your own license if using beyond learning.


