package models;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class ProcessTwo {
    @NotEmpty
    private String listName;
    @NotEmpty
    @Valid
    private List<Checkbox> items;

    public String getListName() {
        return listName;
    }

    public ProcessTwo setListName(String listName) {
        this.listName = listName;
        return this;
    }

    public List<Checkbox> getItems() {
        return items;
    }

    public ProcessTwo setItems(List<Checkbox> items) {
        this.items = items;
        return this;
    }
}
