package com.chintamani.mcp.countryfinder.repository;

import com.chintamani.mcp.countryfinder.model.CountryInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CountryRepository {

    private static final Map<String, CountryInfo> COUNTRIES = Map.ofEntries(

            Map.entry("india", new CountryInfo(
                    "India",
                    "New Delhi",
                    "Asia",
                    "South Asia",
                    "20.5937° N, 78.9629° E",
                    "https://www.google.com/maps/place/India",
                    "India is one of the world's oldest civilizations with a history spanning over 5,000 years. " +
                    "It was a major center of trade and culture in the ancient world, home to the Indus Valley Civilization. " +
                    "India gained independence from British rule in 1947 under the leadership of Mahatma Gandhi, " +
                    "becoming the world's largest democracy.",
                    "October to March is the best time to visit most of India. " +
                    "North India is pleasant during winter months (Nov–Feb). " +
                    "Avoid monsoon season (June–September) unless you enjoy lush greenery.",
                    List.of("Taj Mahal", "Jaipur's Amber Fort", "Kerala Backwaters", "Varanasi Ghats", "Goa Beaches"),
                    "Indian Rupee (INR)",
                    "Hindi, English (and 21 other official languages)",
                    "1.4 billion"
            )),

            Map.entry("japan", new CountryInfo(
                    "Japan",
                    "Tokyo",
                    "Asia",
                    "East Asia",
                    "36.2048° N, 138.2529° E",
                    "https://www.google.com/maps/place/Japan",
                    "Japan's history stretches back over 2,000 years. Known for its samurai warriors and feudal era, " +
                    "Japan rapidly modernized during the Meiji Restoration (1868). After World War II, " +
                    "Japan rebuilt itself into the world's third-largest economy, blending ancient traditions " +
                    "with cutting-edge technology.",
                    "Spring (March–May) for cherry blossoms and autumn (September–November) for fall foliage " +
                    "are the most popular times. Summer (June–August) is hot and humid with festivals. " +
                    "Winter is great for snow sports in Hokkaido.",
                    List.of("Mount Fuji", "Kyoto Temples", "Tokyo Shibuya Crossing", "Hiroshima Peace Memorial", "Osaka Castle"),
                    "Japanese Yen (JPY)",
                    "Japanese",
                    "125 million"
            )),

            Map.entry("france", new CountryInfo(
                    "France",
                    "Paris",
                    "Europe",
                    "Western Europe",
                    "46.2276° N, 2.2137° E",
                    "https://www.google.com/maps/place/France",
                    "France has a rich history dating back to the Gauls and Roman Empire. " +
                    "The French Revolution (1789) transformed not just France but the entire world " +
                    "with ideals of liberty, equality, and fraternity. France built one of history's " +
                    "largest empires and remains a global cultural powerhouse.",
                    "Spring (April–June) and fall (September–October) offer mild weather and fewer crowds. " +
                    "Summer (July–August) is peak tourist season. " +
                    "December is magical for Christmas markets in Alsace.",
                    List.of("Eiffel Tower", "Louvre Museum", "Mont Saint-Michel", "French Riviera", "Palace of Versailles"),
                    "Euro (EUR)",
                    "French",
                    "68 million"
            )),

            Map.entry("brazil", new CountryInfo(
                    "Brazil",
                    "Brasília",
                    "South America",
                    "South America",
                    "14.2350° S, 51.9253° W",
                    "https://www.google.com/maps/place/Brazil",
                    "Brazil was colonized by Portugal in 1500 and gained independence in 1822. " +
                    "It is the largest country in South America and home to the Amazon rainforest, " +
                    "covering 60% of the country. Brazil is known for its vibrant culture, " +
                    "Carnival celebrations, and football (soccer).",
                    "December to March is summer in Brazil — great for Rio's beaches and Carnival (February/March). " +
                    "April to September (dry season) is ideal for Amazon exploration and Pantanal wildlife.",
                    List.of("Christ the Redeemer", "Amazon Rainforest", "Iguazu Falls", "Copacabana Beach", "Pantanal Wetlands"),
                    "Brazilian Real (BRL)",
                    "Portuguese",
                    "215 million"
            )),

            Map.entry("australia", new CountryInfo(
                    "Australia",
                    "Canberra",
                    "Oceania",
                    "Australia and New Zealand",
                    "25.2744° S, 133.7751° E",
                    "https://www.google.com/maps/place/Australia",
                    "Australia's indigenous Aboriginal people have lived on the continent for over 65,000 years, " +
                    "making them one of the oldest continuous cultures on Earth. " +
                    "British colonization began in 1788. Australia became a federation in 1901 " +
                    "and today is one of the world's most developed and diverse nations.",
                    "September to November (spring) and March to May (autumn) are ideal — mild weather nationwide. " +
                    "December to February is summer — perfect for beaches but very hot in the outback. " +
                    "June to August is winter — great for Great Barrier Reef diving.",
                    List.of("Sydney Opera House", "Great Barrier Reef", "Uluru (Ayers Rock)", "Great Ocean Road", "Daintree Rainforest"),
                    "Australian Dollar (AUD)",
                    "English",
                    "26 million"
            )),

            Map.entry("egypt", new CountryInfo(
                    "Egypt",
                    "Cairo",
                    "Africa",
                    "North Africa",
                    "26.8206° N, 30.8025° E",
                    "https://www.google.com/maps/place/Egypt",
                    "Egypt is one of the world's oldest civilizations, with a history spanning over 7,000 years. " +
                    "Ancient Egypt was a cradle of civilization — home to pharaohs, pyramids, and the Nile River. " +
                    "Conquered by Alexander the Great and later Rome, Egypt became an Islamic civilization in 641 AD " +
                    "and has remained a cultural heart of the Arab world.",
                    "October to April is the best time to visit — cooler and comfortable. " +
                    "Avoid May to September when temperatures in the desert can exceed 40°C. " +
                    "Winter months are ideal for Nile cruises.",
                    List.of("Pyramids of Giza", "Egyptian Museum", "Luxor Temple", "Valley of the Kings", "Abu Simbel"),
                    "Egyptian Pound (EGP)",
                    "Arabic",
                    "105 million"
            )),

            Map.entry("canada", new CountryInfo(
                    "Canada",
                    "Ottawa",
                    "North America",
                    "North America",
                    "56.1304° N, 106.3468° W",
                    "https://www.google.com/maps/place/Canada",
                    "Canada's indigenous peoples have lived there for over 15,000 years. " +
                    "European colonization began in the 15th century, with French and British settlers " +
                    "competing for control. Canada became a self-governing dominion in 1867 " +
                    "and is now the world's second-largest country by total area.",
                    "June to August is peak summer — warm weather and outdoor festivals. " +
                    "September to October is stunning for fall foliage in Ontario and Quebec. " +
                    "December to March is ideal for skiing in Banff and Whistler.",
                    List.of("Niagara Falls", "Banff National Park", "CN Tower", "Old Quebec City", "Rocky Mountains"),
                    "Canadian Dollar (CAD)",
                    "English, French",
                    "38 million"
            )),

            Map.entry("italy", new CountryInfo(
                    "Italy",
                    "Rome",
                    "Europe",
                    "Southern Europe",
                    "41.8719° N, 12.5674° E",
                    "https://www.google.com/maps/place/Italy",
                    "Italy is the birthplace of the Roman Empire and the Renaissance. " +
                    "Ancient Rome dominated the Western world for centuries. " +
                    "The Renaissance (14th–17th centuries) saw an explosion of art, science, and culture. " +
                    "Italy unified as a nation in 1861 and is now a founding member of the European Union.",
                    "April to June and September to October are the best months — pleasant weather and manageable crowds. " +
                    "July and August are very hot and crowded, especially coastal areas. " +
                    "Christmas in Rome and Venice is magical.",
                    List.of("Colosseum", "Vatican City", "Venice Canals", "Florence Duomo", "Amalfi Coast"),
                    "Euro (EUR)",
                    "Italian",
                    "60 million"
            )),

            Map.entry("usa", new CountryInfo(
                    "United States of America",
                    "Washington, D.C.",
                    "North America",
                    "North America",
                    "37.0902° N, 95.7129° W",
                    "https://www.google.com/maps/place/United+States",
                    "The United States declared independence from Britain in 1776, becoming the world's " +
                    "first modern democracy. It expanded westward through the 19th century, survived a " +
                    "Civil War (1861–1865), and emerged as a global superpower after World War II. " +
                    "It remains the world's largest economy.",
                    "The USA is vast — best time varies by region. " +
                    "Spring (April–May) and fall (September–October) are great nationwide. " +
                    "New York shines in fall; national parks are best in summer; " +
                    "Florida is ideal in winter.",
                    List.of("Grand Canyon", "Statue of Liberty", "Yellowstone National Park", "Times Square", "Golden Gate Bridge"),
                    "US Dollar (USD)",
                    "English",
                    "335 million"
            )),

            Map.entry("south africa", new CountryInfo(
                    "South Africa",
                    "Pretoria (administrative), Cape Town (legislative), Bloemfontein (judicial)",
                    "Africa",
                    "Southern Africa",
                    "30.5595° S, 22.9375° E",
                    "https://www.google.com/maps/place/South+Africa",
                    "South Africa is home to some of the world's oldest human fossils, dating back 3 million years. " +
                    "European colonization began in 1652 with the Dutch. " +
                    "The apartheid era (1948–1994) was a dark period of racial segregation. " +
                    "Nelson Mandela led the country to democracy in 1994, becoming its first Black president.",
                    "May to September (dry winter season) is the best time for safari in Kruger National Park. " +
                    "October to April is summer — lush, warm, great for Cape Town and Garden Route. " +
                    "Whale watching is best in September–November along the coast.",
                    List.of("Kruger National Park", "Table Mountain", "Cape of Good Hope", "Robben Island", "Garden Route"),
                    "South African Rand (ZAR)",
                    "11 official languages including Zulu, Xhosa, Afrikaans, English",
                    "60 million"
            ))
    );

    public Optional<CountryInfo> findByName(String countryName) {
        if (countryName == null || countryName.isBlank()) {
            return Optional.empty();
        }
        return Optional.ofNullable(COUNTRIES.get(countryName.trim().toLowerCase()));
    }

    public List<String> getAllCountryNames() {
        return COUNTRIES.values().stream()
                .map(CountryInfo::name)
                .sorted()
                .toList();
    }

    public List<CountryInfo> findByContinent(String continent) {
        return COUNTRIES.values().stream()
                .filter(c -> c.continent().equalsIgnoreCase(continent.trim()))
                .toList();
    }

    public boolean exists(String countryName) {
        return countryName != null &&
               COUNTRIES.containsKey(countryName.trim().toLowerCase());
    }
}
