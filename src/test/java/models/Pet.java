package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import utils.DataLoader;

public class Pet {
    private String type;
    private String name;
    private int age;

    @JsonCreator
    public static Pet createPetFromFile(String filename) throws Exception {
        return DataLoader.getData(filename, Pet.class);
    }

    public String getType() {
        return type;
    }

    public Pet setType(String type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public Pet setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Pet setAge(int age) {
        this.age = age;
        return this;
    }
}
