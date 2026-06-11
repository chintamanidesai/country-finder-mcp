package com.chintamani.mcp.countryfinder.resources;

import com.chintamani.mcp.countryfinder.model.CountryInfo;
import com.chintamani.mcp.countryfinder.repository.CountryRepository;
import org.springframework.ai.mcp.annotation.McpResource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * MCP Resources for the Country Finder server.
 *
 * Resources are URI-addressable content that an LLM can read —
 * think of them as files or database records the model can access
 * by URI. Each @McpResource method exposes static or dynamic data
 * as an MCP resource endpoint.
 */
@Component
public class CountryResources {

    private final CountryRepository countryRepository;

    public CountryResources(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    /**
     * Resource 1: Catalogue of all supported countries.
     * URI: country://catalogue
     */
    @McpResource(
            uri = "country://catalogue",
            name = "Country Catalogue",
            description = "A complete catalogue of all 10 supported countries with basic details.",
            mimeType = "text/plain"
    )
    public String countryCatalogue() {
        List<String> names = countryRepository.getAllCountryNames();
        StringBuilder sb = new StringBuilder();
        sb.append("=== Country Finder — Supported Country Catalogue ===\n\n");
        sb.append("Total supported countries: %d\n\n".formatted(names.size()));

        names.forEach(name -> {
            countryRepository.findByName(name).ifPresent(c ->
                    sb.append("• %s\n  Capital: %s | Continent: %s | Language: %s\n\n"
                            .formatted(c.name(), c.capital(), c.continent(), c.language()))
            );
        });

        sb.append("Use the 'getCountryInfo' tool to get full details on any country.");
        return sb.toString();
    }

    /**
     * Resource 2: Country detail by name — dynamic URI template.
     * URI: country://detail/{countryName}
     */
    @McpResource(
            uri = "country://detail/{countryName}",
            name = "Country Detail",
            description = "Full details for a specific country, including location, history, best time to visit, and top attractions.",
            mimeType = "text/plain"
    )
    public String countryDetail(String countryName) {
        Optional<CountryInfo> opt = countryRepository.findByName(countryName);
        if (opt.isEmpty()) {
            return "No data found for country: " + countryName +
                   "\nSupported countries: " + String.join(", ", countryRepository.getAllCountryNames());
        }
        CountryInfo c = opt.get();
        return """
                COUNTRY: %s
                ==========================================
                Capital          : %s
                Continent        : %s
                Region           : %s
                Coordinates      : %s
                Google Maps      : %s
                Currency         : %s
                Language         : %s
                Population       : %s
                
                HISTORY:
                %s
                
                BEST TIME TO VISIT:
                %s
                
                TOP ATTRACTIONS:
                %s
                """.formatted(
                c.name(), c.capital(), c.continent(), c.region(),
                c.coordinates(), c.googleMapLink(),
                c.currency(), c.language(), c.population(),
                c.briefHistory(),
                c.bestTimeToVisit(),
                c.topAttractions().stream().map(a -> "  • " + a).reduce("", (a, b) -> a + "\n" + b)
        );
    }

    /**
     * Resource 3: Travel guide — best times to visit all countries.
     * URI: country://travel-guide
     */
    @McpResource(
            uri = "country://travel-guide",
            name = "Global Travel Guide",
            description = "A travel guide listing the best time to visit all supported countries.",
            mimeType = "text/plain"
    )
    public String travelGuide() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Country Finder — Global Travel Guide ===\n\n");
        sb.append("Best times to visit each country:\n\n");

        countryRepository.getAllCountryNames().forEach(name ->
                countryRepository.findByName(name).ifPresent(c -> {
                    sb.append("📍 %s\n".formatted(c.name()));
                    sb.append("   %s\n\n".formatted(c.bestTimeToVisit()));
                })
        );
        return sb.toString();
    }

    /**
     * Resource 4: History compendium for all countries.
     * URI: country://history-compendium
     */
    @McpResource(
            uri = "country://history-compendium",
            name = "History Compendium",
            description = "A compendium of brief historical summaries for all supported countries.",
            mimeType = "text/plain"
    )
    public String historyCompendium() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Country Finder — History Compendium ===\n\n");

        countryRepository.getAllCountryNames().forEach(name ->
                countryRepository.findByName(name).ifPresent(c -> {
                    sb.append("🏛️  %s\n".formatted(c.name()));
                    sb.append("   %s\n\n".formatted(c.briefHistory()));
                })
        );
        return sb.toString();
    }

    /**
     * Resource 5: Country map links reference sheet.
     * URI: country://map-links
     */
    @McpResource(
            uri = "country://map-links",
            name = "Map Links Reference",
            description = "A quick-reference sheet with Google Maps links and coordinates for all supported countries.",
            mimeType = "text/plain"
    )
    public String mapLinksReference() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Country Finder — Google Maps Reference ===\n\n");

        countryRepository.getAllCountryNames().forEach(name ->
                countryRepository.findByName(name).ifPresent(c -> {
                    sb.append("🗺️  %s\n".formatted(c.name()));
                    sb.append("   Coordinates : %s\n".formatted(c.coordinates()));
                    sb.append("   Google Maps : %s\n\n".formatted(c.googleMapLink()));
                })
        );
        return sb.toString();
    }
}
