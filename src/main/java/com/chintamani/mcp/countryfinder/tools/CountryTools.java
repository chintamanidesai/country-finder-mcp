package com.chintamani.mcp.countryfinder.tools;

import com.chintamani.mcp.countryfinder.model.CountryInfo;
import com.chintamani.mcp.countryfinder.repository.CountryRepository;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * MCP Tools for the Country Finder server.
 *
 * Tools are callable functions that an LLM can invoke to retrieve
 * or act on data. Each method annotated with @Tool becomes an
 * MCP tool exposed to connected clients.
 */
@Component
public class CountryTools {

    private final CountryRepository countryRepository;

    public CountryTools(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    /**
     * Tool 1: Get full country information by name.
     */
    @McpTool(description = """
            Retrieve complete information about a country by its name.
            Returns location details, Google Maps link, brief history,
            best time to visit, top attractions, currency, language, and population.
            Supported countries: India, Japan, France, Brazil, Australia,
            Egypt, Canada, Italy, USA, South Africa.
            """)
    public String getCountryInfo(
            @McpToolParam(description = "The name of the country (e.g., 'India', 'Japan', 'USA')")
            String countryName) {

        return countryRepository.findByName(countryName)
                .map(this::formatCountryInfo)
                .orElse("Country '%s' is not found in our database. Supported countries are: %s"
                        .formatted(countryName, String.join(", ", countryRepository.getAllCountryNames())));
    }

    /**
     * Tool 2: Get Google Maps link for a country.
     */
    @McpTool(description = """
            Get the Google Maps link for a specific country.
            Use this when the user wants to see a country's location on a map.
            """)
    public String getCountryMapLink(
            @McpToolParam(description = "The name of the country")
            String countryName) {

        return countryRepository.findByName(countryName)
                .map(c -> "Google Maps link for %s: %s\nCoordinates: %s"
                        .formatted(c.name(), c.googleMapLink(), c.coordinates()))
                .orElse("Country '%s' not found.".formatted(countryName));
    }

    /**
     * Tool 3: Get best time to visit a country.
     */
    @McpTool(description = """
            Get the best time to visit a specific country with seasonal travel advice.
            Returns detailed information about optimal travel months and what to expect.
            """)
    public String getBestTimeToVisit(
            @McpToolParam(description = "The name of the country")
            String countryName) {

        return countryRepository.findByName(countryName)
                .map(c -> "Best Time to Visit %s:\n%s".formatted(c.name(), c.bestTimeToVisit()))
                .orElse("Country '%s' not found.".formatted(countryName));
    }

    /**
     * Tool 4: Get country history.
     */
    @McpTool(description = """
            Get a brief but informative history of a specific country.
            Returns key historical events and milestones.
            """)
    public String getCountryHistory(
            @McpToolParam(description = "The name of the country")
            String countryName) {

        return countryRepository.findByName(countryName)
                .map(c -> "Brief History of %s:\n%s".formatted(c.name(), c.briefHistory()))
                .orElse("Country '%s' not found.".formatted(countryName));
    }

    /**
     * Tool 5: List all supported countries.
     */
    @McpTool(description = """
            List all countries supported by this Country Finder MCP server.
            Use this to show users what countries they can query.
            """)
    public String listSupportedCountries() {
        List<String> countries = countryRepository.getAllCountryNames();
        return "Supported Countries (%d total):\n%s"
                .formatted(countries.size(), String.join("\n", countries.stream()
                        .map(c -> "  • " + c)
                        .toList()));
    }

    /**
     * Tool 6: Find countries by continent.
     */
    @McpTool(description = """
            Find all supported countries in a specific continent.
            Continents: Asia, Europe, Africa, North America, South America, Oceania.
            """)
    public String getCountriesByContinent(
            @McpToolParam(description = "The continent name (e.g., 'Asia', 'Europe', 'Africa')")
            String continent) {

        List<CountryInfo> countries = countryRepository.findByContinent(continent);
        if (countries.isEmpty()) {
            return "No countries found for continent '%s'. Try: Asia, Europe, Africa, North America, South America, Oceania."
                    .formatted(continent);
        }
        StringBuilder sb = new StringBuilder("Countries in %s:\n".formatted(continent));
        countries.forEach(c -> sb.append("  • %s (Capital: %s)\n".formatted(c.name(), c.capital())));
        return sb.toString();
    }

    /**
     * Tool 7: Get top attractions for a country.
     */
    @McpTool(description = """
            Get the top tourist attractions for a specific country.
            Returns a list of must-see places and landmarks.
            """)
    public String getTopAttractions(
            @McpToolParam(description = "The name of the country")
            String countryName) {

        return countryRepository.findByName(countryName)
                .map(c -> {
                    StringBuilder sb = new StringBuilder("Top Attractions in %s:\n".formatted(c.name()));
                    c.topAttractions().forEach(a -> sb.append("  • %s\n".formatted(a)));
                    return sb.toString();
                })
                .orElse("Country '%s' not found.".formatted(countryName));
    }

    // ── private helpers ──────────────────────────────────────────────────────

    private String formatCountryInfo(CountryInfo c) {
        return """
                ╔══════════════════════════════════════╗
                  %s
                ╚══════════════════════════════════════╝
                
                📍 Location
                   Capital    : %s
                   Continent  : %s
                   Region     : %s
                   Coordinates: %s
                   🗺️  Google Maps: %s
                
                🏛️  Brief History
                   %s
                
                🌤️  Best Time to Visit
                   %s
                
                🎯 Top Attractions
                %s
                
                ℹ️  Quick Facts
                   Currency  : %s
                   Language  : %s
                   Population: %s
                """.formatted(
                c.name(),
                c.capital(),
                c.continent(),
                c.region(),
                c.coordinates(),
                c.googleMapLink(),
                c.briefHistory(),
                c.bestTimeToVisit(),
                c.topAttractions().stream().map(a -> "   • " + a).reduce("", (a, b) -> a + "\n" + b),
                c.currency(),
                c.language(),
                c.population()
        );
    }
}
