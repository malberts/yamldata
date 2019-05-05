package tests;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import models.*;
import org.testng.annotations.Test;
import utils.DataLoader;

import javax.validation.ValidationException;

public class ValidationTests {
    @Test(
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Missing data file.*"
    )
    public void missingFile() throws Exception {
        E2EScenario scenario = DataLoader.getData("scenarios/Missing.yml", E2EScenario.class);
    }

    @Test
    public void requiredFieldsPresent() throws Exception {
        E2EScenario scenario = DataLoader.getData("scenarios/RequiredFieldsPresent.yml", E2EScenario.class);

        ProcessOne one = scenario.getProcessOne();
        if (one != null) {
            System.out.println("Process One: " + one.getFirstName() + " " + one.getLastName());
        }

        ProcessTwo two = scenario.getProcessTwo();
        if (two != null) {
            System.out.println("Process Two: " + two.getListName());
            for (Checkbox checkbox : two.getItems()) {
                System.out.println(" - " + checkbox.getLabel() + ": " + checkbox.isEnabled());
            }
        }

        ProcessThree three = scenario.getProcessThree();
        if (three != null) {
            System.out.println("Process Three: ");
            System.out.println(" One: " + three.getValueOne());
            System.out.println(" Two: " + three.getValueTwo());
        }

        ProcessFour four = scenario.getProcessFour();
        if (four == null) {
            System.out.println("Optional Process Four skipped");
        }
    }

    @Test(
            expectedExceptions = ValidationException.class,
            expectedExceptionsMessageRegExp = "Invalid data file.*" +
                    "jiraKey: must not be empty but was: null.*" +
                    "processOne: must not be null but was: null.*" +
                    "processThree: must not be null but was: null.*"

    )
    public void requiredFieldsMissing() throws Exception {
        E2EScenario scenario = DataLoader.getData("scenarios/RequiredFieldsMissing.yml", E2EScenario.class);
    }

    @Test(
            expectedExceptions = UnrecognizedPropertyException.class,
            expectedExceptionsMessageRegExp = "Unrecognized field \"extra\".*"
    )
    public void undefinedFields() throws Exception {
        E2EScenario scenario = DataLoader.getData("scenarios/UndefinedFields.yml", E2EScenario.class);
    }

    @Test(
            expectedExceptions = ValidationException.class,
            expectedExceptionsMessageRegExp = "Invalid data file.*" +
                    "jiraKey: must match \".*\" but was: -123A.*" +
                    "processOne.firstName: must not be empty but was: null.*" +
                    "processOne.lastName: must not be empty but was: null.*" +
                    "processThree: must not be null but was: null.*" +
                    "processTwo.items\\[0\\].label: must not be empty but was: null.*"
    )
    public void invalidValues() throws Exception {
        E2EScenario scenario = DataLoader.getData("scenarios/InvalidValues.yml", E2EScenario.class);
    }
}
