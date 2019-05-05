package models;

import javax.validation.constraints.NotEmpty;

public class ProcessFour {
    @NotEmpty
    private String startDate;
    @NotEmpty
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public ProcessFour setStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public String getEndDate() {
        return endDate;
    }

    public ProcessFour setEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }
}
