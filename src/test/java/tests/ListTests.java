package tests;

import models.Person;
import org.testng.annotations.Test;
import utils.DataFileUtil;

import java.util.List;

public class ListTests {
    @Test
    public void csvList() throws Exception {
        List<Person> people = DataFileUtil.getDataList("person/people.csv", Person.class);
        for (Person person : people) {
            System.out.println("- Person: " + person.getFirstName() + " " + person.getLastName());
        }
    }

    @Test
    public void ymlList() throws Exception {
        List<Person> people = DataFileUtil.getDataList("person/people.yml", Person.class);
        for (Person person : people) {
            System.out.println("- Person: " + person.getFirstName() + " " + person.getLastName());
        }
    }
}
