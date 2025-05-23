package com.springboot.project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.project.entity.BusinessRule;
import com.springboot.project.model.GenericObject;
import com.springboot.project.model.ResultObject;
import com.springboot.project.repository.BusinessRuleRepository;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BusinessRuleService {

    private final ObjectMapper objectMapper;
    private final BusinessRuleRepository businessRuleRepository;
    private final DroolsService droolsService;

    public BusinessRule createBusinessRule(String businessRuleJson) {
        BusinessRule businessRule = new BusinessRule();
        Document document = Document.parse(businessRuleJson);
        Document businessRuleDocument = document.get("businessRule", Document.class);
        String ruleName = businessRuleDocument.getString("name");
        businessRule.setId(UUID.randomUUID());
        businessRule.setName(ruleName);
        businessRule.setValue(document);
        return this.businessRuleRepository.save(businessRule);
    }

    public List<BusinessRule> getBusinessRules() {
        return this.businessRuleRepository.findAll();
    }

    public String testInputBusinessRule(String inputData) {
        try {
            JsonNode inputDataJsonNode = this.objectMapper.readTree(inputData);
            JsonNode data = inputDataJsonNode.get("data");
            GenericObject genericObject = new GenericObject(data);
            JsonNode businessRuleJsonNode = inputDataJsonNode.get("businessRule");
            String dumpDrlString =
                    this.droolsService.createDrlFileFromJsonNode(businessRuleJsonNode);
            StatelessKieSession statelessKieSession =
                    this.droolsService.createStatelessKieSession(
                            dumpDrlString, businessRuleJsonNode.get("name").asText());
            ResultObject resultObject = new ResultObject();
            resultObject.setResult(data.deepCopy());
            statelessKieSession.setGlobal("result", resultObject);
            statelessKieSession.execute(Arrays.asList(genericObject, resultObject));
            return this.objectMapper.writeValueAsString(resultObject);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
