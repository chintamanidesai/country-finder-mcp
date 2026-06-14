package com.chintamani.mcp.countryfinder.resources;

import com.chintamani.mcp.countryfinder.visa.VisaKnowledgeBase;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.mcp.annotation.McpResource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VisaResources {

    private final VisaKnowledgeBase knowledgeBase;

    @McpResource(
            uri = "travel://visa-rules",
            name = "Visa Rules Knowledge Base",
            description = "Available visa information by country"
    )
    public String visaRulesCatalog() {

        return """
                Available visa information for:
                
                %s
                """.formatted(
                String.join(", ",
                        knowledgeBase.getSupportedCountries()));
    }
}