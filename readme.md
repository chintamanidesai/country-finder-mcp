# Country Finder MCP Server

A **Model Context Protocol (MCP)** server built using **Spring AI** that provides country information, travel guidance, historical data, maps, and tourist attractions through MCP **Tools**, **Resources**, and **Prompts**.

## Repository

GitHub Repository:

https://github.com/chintamanidesai/country-finder-mcp

---

## Features

### MCP Tools

The server exposes the following MCP tools:

| Tool                      | Description                                   |
| ------------------------- | --------------------------------------------- |
| `getCountryInfo`          | Retrieve complete information about a country |
| `getCountryMapLink`       | Get Google Maps link and coordinates          |
| `getBestTimeToVisit`      | Get travel season recommendations             |
| `getCountryHistory`       | Get historical information                    |
| `listSupportedCountries`  | List all supported countries                  |
| `getCountriesByContinent` | Find countries by continent                   |
| `getTopAttractions`       | Get major tourist attractions                 |

---

### MCP Resources

| Resource URI                     | Description                               |
| -------------------------------- | ----------------------------------------- |
| `country://catalogue`            | Complete catalogue of supported countries |
| `country://detail/{countryName}` | Detailed information for a country        |
| `country://travel-guide`         | Travel guide for all countries            |
| `country://history-compendium`   | Historical summaries                      |
| `country://map-links`            | Google Maps reference sheet               |

---

### MCP Prompts

| Prompt             | Description                           |
| ------------------ | ------------------------------------- |
| `Country Explorer` | General country information assistant |
| `Travel Planner`   | Travel planning assistant             |
| `History Guide`    | Historical and cultural guide         |

---

## Supported Countries

* India
* Japan
* France
* Brazil
* Australia
* Egypt
* Canada
* Italy
* USA
* South Africa

---

## Technology Stack

* Java 21
* Spring Boot 4.x
* Spring AI 2.0.0-M8
* Model Context Protocol (MCP)
* Gradle

---

## Running the Application

### Clone Repository

```bash
git clone https://github.com/chintamanidesai/country-finder-mcp.git
cd country-finder-mcp
```

### Build

```bash
./gradlew clean build
```

### Run

```bash
./gradlew bootRun
```

or

```bash
java -jar build/libs/country-finder-mcp-1.0.0.jar
```

---

## Configuration

Example `application.properties`

```properties
spring.application.name=country-finder-mcp

server.port=8080

spring.ai.mcp.server.enabled=true
spring.ai.mcp.server.protocol=streamable
spring.ai.mcp.server.type=SYNC

spring.ai.mcp.server.name=country-finder
spring.ai.mcp.server.version=1.0.0
```

---

## MCP Endpoint

Default MCP endpoint:

```text
http://localhost:8080/mcp
```

Verify endpoint availability:

```bash
curl -i http://localhost:8080/mcp
```

If the endpoint is configured correctly, you should receive an MCP response instead of a 404 error.

---

# Testing with MCP Inspector

## Prerequisites

Install Node.js.

Verify installation:

```bash
node -v
npm -v
```

---

## Launch MCP Inspector

```bash
npx @modelcontextprotocol/inspector
```

Inspector will start and display a URL similar to:

```text
http://localhost:6274
```

Open the URL in your browser.

---

## Connect to the MCP Server

### Transport

Select:

```text
Streamable HTTP
```

### Server URL

```text
http://localhost:8080/mcp
```

Click:

```text
Connect
```

---

## Testing MCP Tools

After connecting:

1. Open the **Tools** tab.
2. Select a tool.
3. Provide input parameters.
4. Execute the tool.

### Example: getCountryInfo

Input:

```json
{
  "countryName": "India"
}
```

Expected Result:

* Capital
* Coordinates
* History
* Best time to visit
* Attractions
* Currency
* Language
* Population

---

### Example: getCountriesByContinent

Input:

```json
{
  "continent": "Asia"
}
```

Expected Result:

```text
Countries in Asia:
• India
• Japan
```

---

### Example: getTopAttractions

Input:

```json
{
  "countryName": "Japan"
}
```

Expected Result:

```text
Top attractions in Japan:
• Mount Fuji
• Tokyo Tower
• Kyoto Temples
...
```

---

## Testing MCP Resources

Open the **Resources** tab.

### Country Catalogue

```text
country://catalogue
```

### Country Detail

```text
country://detail/India
```

### Travel Guide

```text
country://travel-guide
```

### History Compendium

```text
country://history-compendium
```

### Map Links

```text
country://map-links
```

---

## Testing MCP Prompts

Open the **Prompts** tab.

Available prompts:

* Country Explorer
* Travel Planner
* History Guide

Select a prompt and execute it from MCP Inspector.

---

## Project Structure

```text
src/main/java
├── model
│   └── CountryInfo
├── repository
│   └── CountryRepository
├── tools
│   └── CountryTools
├── resources
│   └── CountryResources
├── prompts
│   └── CountryPrompts
└── CountryFinderMcpApplication
```

---

## Example User Questions

The MCP server can answer questions such as:

* Tell me about India.
* What is the best time to visit Japan?
* Show me France on Google Maps.
* List all supported countries.
* Which countries are in Europe?
* What are the top attractions in Australia?
* Give me a brief history of Egypt.

---

## Future Enhancements

* Add more countries
* Integrate live weather APIs
* Add visa and travel requirements
* Add currency conversion
* Add country comparison tools
* Integrate AI-powered travel recommendations

---

## License

This project is provided for learning, experimentation, and demonstration purposes.