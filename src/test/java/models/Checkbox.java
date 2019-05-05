package models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Checkbox {
    @NotEmpty
    private String label;
    @NotNull
    private boolean enabled;

    public String getLabel() {
        return label;
    }

    public Checkbox setLabel(String label) {
        this.label = label;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Checkbox setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}
