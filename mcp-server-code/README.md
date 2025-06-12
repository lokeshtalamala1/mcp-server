# MCP Server

MCP (Master Customer Profile) Server is a Spring Boot application that provides APIs for managing customer data, accounts, and transactions.

## Project Structure

```
src/main/java/com/mcp/
├── config/                 # Configuration classes
├── controller/            # REST API controllers
├── model/                 # Entity classes
└── McpServerApplication.java  # Main application class
```

## Technologies Used

- Java 21
- Spring Boot 3.2.3
- PostgreSQL Database
- Maven for dependency management

## Prerequisites

- Java 21 or higher
- Maven
- PostgreSQL database

## Database Configuration

The application uses PostgreSQL with the following default configuration:
- Database Name: mcp_db
- Port: 5432
- Username: postgres
- Password: lokit@181903

## Getting Started

1. Clone the repository
2. Configure the database connection in `application.properties` if needed
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. The server will start on port 8080

## Available Endpoints

### Base URL
```
http://localhost:8080/api/mcp
```

### API Endpoints

1. **Home & Health Check**
   - GET `/` - Welcome message and available endpoints
   - GET `/health` - Server health check

2. **Database Connection**
   - GET `/database-connection` - Database connection details

3. **Account Management**
   - GET `/accounts/count` - Get total number of accounts
   - GET `/accounts` - Get all accounts

4. **Customer Relationships**
   - GET `/customer-relationships/count` - Get total number of customer relationships
   - GET `/customer-relationships` - Get all customer relationships

5. **CASA Transactions**
   - GET `/casa-transactions/count` - Get total number of CASA transactions
   - GET `/casa-transactions` - Get all CASA transactions

6. **Card Transactions**
   - GET `/card-transactions/count` - Get total number of card transactions
   - GET `/card-transactions` - Get all card transactions

7. **Query Endpoint**
   - POST `/query` - Handle custom queries (to be implemented)

## Data Models

### Account
- id (Long)
- accountNumber (String)
- accountType (String)
- customerId (String)
- balance (Double)
- status (String)

### CustomerRelationship
- id (Long)
- customerId (String)
- relationshipType (String)
- status (String)
- startDate (String)
- endDate (String)

### CasaTransaction
- id (Long)
- accountNumber (String)
- transactionType (String)
- amount (Double)
- transactionDate (String)
- description (String)
- status (String)

### CardTransaction
- id (Long)
- cardNumber (String)
- transactionType (String)
- amount (Double)
- transactionDate (String)
- merchantName (String)
- merchantCategory (String)
- status (String)

## Error Handling

The application includes a global exception handler that returns appropriate error messages for:
- Invalid requests
- Database connection issues
- Server errors

## Development

To contribute to the project:
1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Submit a pull request

## License

This project is proprietary and confidential. 