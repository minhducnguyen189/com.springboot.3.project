package com.springboot.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import com.springboot.project.model.Measurement;
import com.springboot.project.model.MeasurementUnit;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;
import org.junit.jupiter.api.Test;

@Slf4j
class RuleTest {

    @Test
    void test() {
        log.info("Creating RuleUnit");
        MeasurementUnit measurementUnit = new MeasurementUnit();

        try (RuleUnitInstance<MeasurementUnit> instance =
                RuleUnitProvider.get().createRuleUnitInstance(measurementUnit)) {
            log.info("Insert data");
            measurementUnit.getMeasurements().add(new Measurement("color", "red"));
            measurementUnit.getMeasurements().add(new Measurement("color", "green"));
            measurementUnit.getMeasurements().add(new Measurement("color", "blue"));

            log.info("Run query. Rules are also fired");
            List<Measurement> queryResult = instance.executeQuery("FindColor").toList("$m");

            assertEquals(3, queryResult.size());
            assertTrue("contains red", measurementUnit.getControlSet().contains("red"));
            assertTrue("contains green", measurementUnit.getControlSet().contains("green"));
            assertTrue("contains blue", measurementUnit.getControlSet().contains("blue"));
        }
    }
}
