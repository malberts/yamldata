package models;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class E2EScenario {
    /**
     * A Scenario must have a title.
     */
    @NotEmpty
    private String scenarioTitle;

    /**
     * A Jira key must be specified.
     */
    @NotEmpty
    @Pattern(regexp = "[A-Z]+-[0-9]+")
    private String jiraKey;

    /**
     * Process One is required.
     */
    @NotNull
    @Valid
    private ProcessOne processOne;

    /**
     * Process Two is optional.
     */
    @Valid
    private ProcessTwo processTwo;

    /**
     * Process Three is required.
     */
    @NotNull
    @Valid
    private ProcessThree processThree;

    /**
     * Process Four is optional.
     */
    @Valid
    private ProcessFour processFour;

    public String getScenarioTitle() {
        return scenarioTitle;
    }

    public E2EScenario setScenarioTitle(String scenarioTitle) {
        this.scenarioTitle = scenarioTitle;
        return this;
    }

    public String getJiraKey() {
        return jiraKey;
    }

    public E2EScenario setJiraKey(String jiraKey) {
        this.jiraKey = jiraKey;
        return this;
    }

    public ProcessOne getProcessOne() {
        return processOne;
    }

    public E2EScenario setProcessOne(ProcessOne processOne) {
        this.processOne = processOne;
        return this;
    }

    public ProcessTwo getProcessTwo() {
        return processTwo;
    }

    public E2EScenario setProcessTwo(ProcessTwo processTwo) {
        this.processTwo = processTwo;
        return this;
    }

    public ProcessThree getProcessThree() {
        return processThree;
    }

    public E2EScenario setProcessThree(ProcessThree processThree) {
        this.processThree = processThree;
        return this;
    }

    public ProcessFour getProcessFour() {
        return processFour;
    }

    public E2EScenario setProcessFour(ProcessFour processFour) {
        this.processFour = processFour;
        return this;
    }
}

