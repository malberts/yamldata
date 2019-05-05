package tests;

import models.Person;
import org.testng.annotations.Test;
import utils.DataLoader;

import java.util.List;

public class ListTests {
    @Test
    public void csvList() throws Exception {
        List<Person> people = DataLoader.getDataList("person/people.csv", Person.class);
        for (Person person : people) {
            System.out.println("- Person: " + person.getFirstName() + " " + person.getLastName());
        }
    }

    @Test
    public void ymlList() throws Exception {
        List<Person> people = DataLoader.getDataList("person/people.yml", Person.class);
        for (Person person : people) {
            System.out.println("- Person: " + person.getFirstName() + " " + person.getLastName());
        }
    }
}
