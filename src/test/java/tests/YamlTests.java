package tests;

import models.Person;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.DataLoader;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class YamlTests {
    @Test
    public void manual() throws Exception {
        // Create a list of children linked to the parent.
        List<Person> children = Arrays.asList(
                new Person()
                        .setFirstName("Johnny")
                        .setLastName("Doe")
                        .setDateOfBirth(LocalDate.parse("1970-01-10")),
                new Person()
                        .setFirstName("Jane")
                        .setLastName("Doe")
                        .setDateOfBirth(LocalDate.parse("1980-12-20"))
        );

        // Set the model values manually.
        Person parent = new Person()
                .setFirstName("John")
                .setLastName("Doe")
                .setDateOfBirth(LocalDate.parse("1950-06-15"))
                .setGender("male")
                .setHeight(1.8f)
                .setMarried(true)
                .setWeight(80)
                .setChildren(children);

        Assert.assertEquals(parent.getFirstName(), "John");
        Assert.assertEquals(parent.getLastName(), "Doe");

        // Loop over every child of the parent.
        System.out.println("Children for: " + parent.getFirstName() + " " + parent.getLastName());
        for (Person child : parent.getChildren()) {
            System.out.println(child.getFirstName() + " " + child.getLastName());
            Assert.assertEquals(child.getLastName(), "Doe");
            Assert.assertTrue(child.getDateOfBirth().isAfter(parent.getDateOfBirth()));
        }
    }

    @Test
    public void simple() throws Exception {
        // Load a simple model from a YAML file.
        Person parent = DataLoader.getData("person_simple.yml", Person.class);

        Assert.assertEquals(parent.getFirstName(), "John");
        Assert.assertEquals(parent.getLastName(), "Doe");

        // Loop over every child of the parent.
        Assert.assertEquals(parent.getChildren().size(), 2);
        System.out.println("Children for: " + parent.getFirstName() + " " + parent.getLastName());
        for (Person child : parent.getChildren()) {
            System.out.println(child.getFirstName() + " " + child.getLastName());
            Assert.assertEquals(child.getLastName(), "Doe");
            Assert.assertTrue(child.getDateOfBirth().isAfter(parent.getDateOfBirth()));
        }
    }

    @Test
    public void partial() throws Exception {
        // Load a partial model from a YAML file.
        Person parent = DataLoader.getData("person_partial.yml", Person.class);

        // Create a list of children linked to the parent.
        List<Person> children = Arrays.asList(
                new Person()
                        .setFirstName("Johnny")
                        .setLastName("Doe")
                        .setDateOfBirth(LocalDate.parse("1970-01-10")),
                new Person()
                        .setFirstName("Jane")
                        .setLastName("Doe")
                        .setDateOfBirth(LocalDate.parse("1980-12-20"))
        );

        parent.setChildren(children);

        Assert.assertEquals(parent.getFirstName(), "John");
        Assert.assertEquals(parent.getLastName(), "Doe");

        // Loop over every child of the parent.
        Assert.assertEquals(parent.getChildren().size(), 2);
        System.out.println("Children for: " + parent.getFirstName() + " " + parent.getLastName());
        for (Person child : parent.getChildren()) {
            System.out.println(child.getFirstName() + " " + child.getLastName());
            Assert.assertEquals(child.getLastName(), "Doe");
            Assert.assertTrue(child.getDateOfBirth().isAfter(parent.getDateOfBirth()));
        }
    }

    @Test
    public void partialMultiple() throws Exception {
        // Load a partial model from a YAML file.
        Person parent = DataLoader.getData("person_partial.yml", Person.class);
        List<Person> children = Arrays.asList(DataLoader.getData("children_doe.yml", Person[].class));
        parent.setChildren(children);

        Assert.assertEquals(parent.getFirstName(), "John");
        Assert.assertEquals(parent.getLastName(), "Doe");

        // Loop over every child of the parent.
        Assert.assertEquals(parent.getChildren().size(), 2);
        System.out.println("Children for: " + parent.getFirstName() + " " + parent.getLastName());
        for (Person child : parent.getChildren()) {
            System.out.println(child.getFirstName() + " " + child.getLastName());
            Assert.assertEquals(child.getLastName(), "Doe");
            Assert.assertTrue(child.getDateOfBirth().isAfter(parent.getDateOfBirth()));
        }
        // Assert values on specific items in list.
        Assert.assertEquals(parent.getChildren().get(0).getFirstName(), "Mark");
        Assert.assertEquals(parent.getChildren().get(1).getFirstName(), "Sally");
    }

    @Test
    public void nestedFiles() throws Exception {
        // Load a nested model from a YAML file.
        Person parent = DataLoader.getData("person_nested.yml", Person.class);

        Assert.assertEquals(parent.getFirstName(), "John");
        Assert.assertEquals(parent.getLastName(), "Johnson");

        // Loop over every child of the parent.
        Assert.assertEquals(parent.getChildren().size(), 2);
        System.out.println("Children for: " + parent.getFirstName() + " " + parent.getLastName());
        for (Person child : parent.getChildren()) {
            System.out.println(child.getFirstName() + " " + child.getLastName());
            Assert.assertEquals(child.getLastName(), "Johnson");
            Assert.assertTrue(child.getDateOfBirth().isAfter(parent.getDateOfBirth()));
        }
        // Assert values on specific items in list.
        Assert.assertEquals(parent.getChildren().get(0).getFirstName(), "Robert");
        Assert.assertEquals(parent.getChildren().get(1).getFirstName(), "Sue");
    }

    @Test
    public void multipleNestedFiles() throws Exception {
        // Load a nested models from nested files..
        Person parent = DataLoader.getData("person_haraldsson.yml", Person.class);

        Assert.assertEquals(parent.getFirstName(), "Harald");
        Assert.assertEquals(parent.getLastName(), "Haraldsson");

        // Loop over every child of the parent.
        Assert.assertEquals(parent.getChildren().size(), 2);
        System.out.println("Children for: " + parent.getFirstName() + " " + parent.getLastName());
        for (Person child : parent.getChildren()) {
            System.out.println(child.getFirstName() + " " + child.getLastName());
            Assert.assertEquals(child.getLastName(), "Haraldsson");
            Assert.assertTrue(child.getDateOfBirth().isAfter(parent.getDateOfBirth()));
        }
        // Assert values on specific items in list.
        Assert.assertEquals(parent.getChildren().get(0).getFirstName(), "Eric");
        Assert.assertEquals(parent.getChildren().get(1).getFirstName(), "Erica");
    }

    @Test
    public void csvList() throws Exception {
        List<Person> children = DataLoader.getDataList("children_peters.csv", Person.class);

        Assert.assertEquals(children.size(), 2);
        Assert.assertEquals(children.get(0).getFirstName(), "Billy");
        Assert.assertEquals(children.get(1).getFirstName(), "Mary");
    }

    @Test
    public void ymlList() throws Exception {
        List<Person> people = DataLoader.getDataList("children_haraldsson.yml", Person.class);

        Assert.assertEquals(people.size(), 2);
        Assert.assertEquals(people.get(0).getFirstName(), "Eric");
        Assert.assertEquals(people.get(1).getFirstName(), "Erica");

        for (Person person : people) {
            for (Person child : person.getChildren()) {
                System.out.println(person.getFirstName() + " " + person.getLastName() + "'s child: " + child.getFirstName() + " " + child.getLastName());
            }
        }
    }
}
