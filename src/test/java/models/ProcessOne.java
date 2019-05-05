package models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class ProcessOne {
    /**
     * First Name is required.
     */
    @NotEmpty
    private String firstName;

    /**
     * Last Name is required.
     */
    @NotEmpty
    private String lastName;

    /**
     * Description is optional.
     */
    private String description;

    /**
     * Phone Number is optional, but must be valid if present.
     */
    @Pattern(regexp = "\\+[0-9]{11}")
    private String phoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public ProcessOne setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ProcessOne setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProcessOne setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ProcessOne setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
