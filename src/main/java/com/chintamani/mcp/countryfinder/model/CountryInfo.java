package com.chintamani.mcp.countryfinder.model;

import java.util.List;

public record CountryInfo(
        String name,
        String capital,
        String continent,
        String region,
        String coordinates,
        String googleMapLink,
        String briefHistory,
        String bestTimeToVisit,
        List<String> topAttractions,
        String currency,
        String language,
        String population
) {
}
