package com.chintamani.mcp.countryfinder.visa;

import jakarta.annotation.PostConstruct;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class VisaKnowledgeBase {

    private final Map<String, String> visaRules = new HashMap<>();

    @PostConstruct
    public void initialize() throws Exception {

        var resolver = new PathMatchingResourcePatternResolver();

        Resource[] resources =
                resolver.getResources("classpath:travel-data/visa/*.md");

        for (Resource resource : resources) {

            String filename = resource.getFilename();

            if (filename == null) {
                continue;
            }

            String country =
                    filename.replace(".md", "").toLowerCase();

            String content =
                    new String(
                            resource.getInputStream().readAllBytes(),
                            StandardCharsets.UTF_8);

            visaRules.put(country, content);
        }
    }

    public String getVisaRule(String country) {

        if (country == null) {
            return null;
        }

        return visaRules.get(
                country.trim()
                        .toLowerCase()
                        .replace(" ", "-"));
    }

    public Set<String> getSupportedCountries() {
        return visaRules.keySet();
    }
}