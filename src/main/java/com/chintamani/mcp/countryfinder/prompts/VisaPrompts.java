package com.chintamani.mcp.countryfinder.prompts;

import org.springframework.ai.mcp.annotation.McpPrompt;
import org.springframework.stereotype.Component;

@Component
public class VisaPrompts {

    @McpPrompt(
            name = "visa-assistant",
            description = "Instructions for handling visa-related queries"
    )
    public String visaAssistantPrompt() {
        return """
            You are a travel assistant.

            For any visa-related question:
            - Determine the destination country.
            - Call get_visa_rules.
            - Use only information returned by the tool.
            - Never invent visa requirements.
            - Assume Indian nationality unless specified otherwise.
            """;
    }
}