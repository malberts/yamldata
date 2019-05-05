package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import utils.DataLoader;
import utils.DateUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Person {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;
    private Float height;
    private Integer weight;
    private String gender;
    private Boolean married;
    @Valid
    private List<Person> children = new ArrayList<>();
    @Valid
    private List<Pet> pets = new ArrayList<>();

    @JsonCreator
    public static Person createFromFile(String filename) throws Exception {
        return DataLoader.getData(filename, Person.class);
    }

    public String getFirstName() {
        return firstName;
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Person setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public Person setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = DateUtil.createDate(dateOfBirth);
        return this;
    }

    public LocalDate getDateOfDeath() {
        return dateOfDeath;
    }

    public Person setDateOfDeath(LocalDate dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
        return this;
    }

    public Person setDateOfDeath(String dateOfBirth) {
        this.dateOfDeath = DateUtil.createDate(dateOfBirth);
        return this;
    }

    public Float getHeight() {
        return height;
    }

    public Person setHeight(Float height) {
        this.height = height;
        return this;
    }

    public Integer getWeight() {
        return weight;
    }

    public Person setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public Person setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Boolean getMarried() {
        return married;
    }

    public Person setMarried(Boolean married) {
        this.married = married;
        return this;
    }

    public List<Person> getChildren() {
        return children;
    }

    public Person setChildren(List<Person> children) {
        this.children = children;
        return this;
    }

    public Person setChildren_fromFile(String filename) {
        setChildren(DataLoader.getDataList(filename, Person.class));
        return this;
    }


    public List<Pet> getPets() {
        return pets;
    }

    public Person setPets(List<Pet> pets) {
        this.pets = pets;
        return this;
    }
}
