package com.chintamani.mcp.countryfinder.tools;

import com.chintamani.mcp.countryfinder.visa.VisaKnowledgeBase;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VisaTools {

    private final VisaKnowledgeBase knowledgeBase;

    @McpTool(
            name = "get_visa_rules",
            description = """
                    Retrieves visa guidance for Indian nationals travelling
                    to a specified country.

                    Use for:
                    - visa requirements
                    - eVisa questions
                    - visa on arrival
                    - travel restrictions
                    - entry requirements
                    - visa application process
                    """
    )
    public String getVisaRules(
            @McpToolParam(
                    description = "Destination country name")
            String country) {

        String rule =
                knowledgeBase.getVisaRule(country);

        if (rule == null) {

            return """
                    Visa information is currently unavailable
                    for country: %s
                    
                    Available countries:
                    %s
                    """
                    .formatted(
                            country,
                            String.join(", ",
                                    knowledgeBase.getSupportedCountries()));
        }

        return rule;
    }
}