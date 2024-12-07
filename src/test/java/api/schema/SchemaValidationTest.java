package api.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/*
@author Bhimashankar Teli
 */
public class SchemaValidationTest {

    // This test is going to validate data type and its required attributes of all apis.




    @Test
    public void authSchemaValidationTest() throws IOException, ProcessingException {

        String jsonResponse = """
        {
          "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9",
          "token_type": "bearer",
          "expires_in": 3600
        }
        """;

        File schemaFile = new File("C:\\project\\expense\\comviva\\src\\test\\resources\\authapi\\auth_token_schema.json");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode schemaNode = objectMapper.readTree(schemaFile);

        JsonNode responseNode = objectMapper.readTree(jsonResponse);  // Here we can get all real api and get the response.

        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonSchema jsonSchema = factory.getJsonSchema(schemaNode);

        ProcessingReport report = jsonSchema.validate(responseNode);

        // Check validation result
        if (report.isSuccess()) {
            System.out.println("Validation successful!");
        } else {
            System.out.println("Validation failed: " + report);
        }

    }

    @Test
    public void bankSchemaValidation() throws IOException, ProcessingException {
        String jsonResponse = """
                        {
                           "total_balance": 5000.00,
                           "transactions": [
                             {"date": "2024-12-01", "amount": 50.00, "merchant": "SuperMart", "category": "Groceries"},
                             {"date": "2024-12-02", "amount": 200.00, "merchant": "FlightCo", "category": "Travel"}
                           ],
                           "available_balance": 4800.00,
                           "top_categories": [
                             {"category": "Travel", "amount": 400.00},
                             {"category": "Entertainment", "amount": 300.00}
                           ],
                           "monthly_trends": [
                             {"month": "2024-01", "amount": 1200.00},
                             {"month": "2024-02", "amount": 1500.00},
                             {"month": "2024-03", "amount": 1800.00}
                           ]
                         }
                """;

        File schemaFile = new File("C:\\project\\expense\\comviva\\src\\test\\resources\\bank\\bank_spending_schema.json");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode schemaNode = objectMapper.readTree(schemaFile);

        JsonNode responseNode = objectMapper.readTree(jsonResponse);  // Here we can get all real api and get the response.

        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonSchema jsonSchema = factory.getJsonSchema(schemaNode);

        ProcessingReport report = jsonSchema.validate(responseNode);

        // Check validation result
        if (report.isSuccess()) {
            System.out.println("Validation successful!");
        } else {
            System.out.println("Validation failed: " + report);
        }

    }


    @Test
    public void epfBalanceSchemaValidation() throws IOException, ProcessingException {
        String jsonResponse = """
                        {
                            "epf_balance": 12000.00,
                            "total_contributions": 10000.00,
                            "interest_earned": 2000.00
                          }
                          
                """;

        File schemaFile = new File("C:\\project\\expense\\comviva\\src\\test\\resources\\epfBalance\\epf_balance_schema.json");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode schemaNode = objectMapper.readTree(schemaFile);

        JsonNode responseNode = objectMapper.readTree(jsonResponse);  // Here we can get all real api and get the response.

        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonSchema jsonSchema = factory.getJsonSchema(schemaNode);

        ProcessingReport report = jsonSchema.validate(responseNode);

        // Check validation result
        if (report.isSuccess()) {
            System.out.println("Validation successful!");
        } else {
            System.out.println("Validation failed: " + report);
        }
    }

    @Test
    public void investmentSchemaValidation() throws IOException, ProcessingException {
        String jsonResponse = """
                        {
                             "total_investments": 25000.00,
                             "investment_summary": [
                               {"type": "Mutual Fund", "amount": 15000.00, "current_value": 16000.00},
                               {"type": "Stocks", "amount": 5000.00, "current_value": 4800.00},
                               {"type": "Bonds", "amount": 5000.00, "current_value": 5000.00}
                             ],
                             "top_performing_investment": {"type": "Mutual Fund", "growth": 6.67}
                           }
                           
                          
                """;

        File schemaFile = new File("C:\\project\\expense\\comviva\\src\\test\\resources\\investment\\investments_schema.json");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode schemaNode = objectMapper.readTree(schemaFile);

        JsonNode responseNode = objectMapper.readTree(jsonResponse);  // Here we can get all real api and get the response.

        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonSchema jsonSchema = factory.getJsonSchema(schemaNode);

        ProcessingReport report = jsonSchema.validate(responseNode);

        // Check validation result
        if (report.isSuccess()) {
            System.out.println("Validation successful!");
        } else {
            System.out.println("Validation failed: " + report);
        }
    }

    @Test
    public void loanSchemaValidation() throws IOException, ProcessingException {
        String jsonResponse = """
                        {
                             "total_liabilities": 15000.00,
                             "liability_details": [
                               {"type": "Home Loan", "remaining_balance": 10000.00, "monthly_payment": 1200.00, "interest_rate": 4.5},
                               {"type": "Car Loan", "remaining_balance": 5000.00, "monthly_payment": 500.00, "interest_rate": 5.0}
                             ]
                           }
                           
                           
                          
                """;

        File schemaFile = new File("C:\\project\\expense\\comviva\\src\\test\\resources\\loan\\loan_schema.json");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode schemaNode = objectMapper.readTree(schemaFile);

        JsonNode responseNode = objectMapper.readTree(jsonResponse);  // Here we can get all real api and get the response.

        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonSchema jsonSchema = factory.getJsonSchema(schemaNode);

        ProcessingReport report = jsonSchema.validate(responseNode);

        // Check validation result
        if (report.isSuccess()) {
            System.out.println("Validation successful!");
        } else {
            System.out.println("Validation failed: " + report);
        }
    }

}
