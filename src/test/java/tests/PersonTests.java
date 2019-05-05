package tests;

import models.Person;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class PersonTests {
    @Test
    public void singleYaml() throws Exception {
        // Load a YML file with all the data.
        Person erik = Person.createFromFile("person/erik_the_red_single.yml");
        checkErik(erik);
    }

    @Test
    public void nestedYaml() throws Exception {
        // Load a YML file with nested YML files.
        Person erik = Person.createFromFile("person/erik_the_red_nested.yml");
        checkErik(erik);
    }

    private void checkErik(Person erik) {
        // Check Erik's details
        Assert.assertEquals(erik.getFirstName(), "Erik");
        Assert.assertEquals(erik.getLastName(), "Thorvaldsson");
        Assert.assertEquals(erik.getGender(), "Male");

        // Check Erik's children
        List<Person> children = erik.getChildren();
        Assert.assertEquals(children.size(), 4);
        for (Person child : children) {
            // None of Erik's children had pets
            Assert.assertEquals(child.getPets().size(), 0);
            // We don't know if they were married
            Assert.assertNull(child.getMarried());
        }

        /*
        Erik's daughter: Freydis
         */
        Person freydis = children.get(0);
        Assert.assertEquals(freydis.getFirstName(), "Freydís");
        Assert.assertEquals(freydis.getLastName(), "Eiríksdóttir");
        Assert.assertEquals(freydis.getGender().toLowerCase(), "female");

        /*
        Erik's son: Leif
         */
        Person leif = children.get(1);
        Assert.assertEquals(leif.getFirstName(), "Leif");
        Assert.assertEquals(leif.getLastName(), "Eiriksson");
        Assert.assertEquals(leif.getGender().toLowerCase(), "male");
        // Leif had children
        List<Person> leifChildren = leif.getChildren();
        Assert.assertEquals(leifChildren.size(), 2);
        Assert.assertEquals(leifChildren.get(0).getFirstName(), "Thorgils");
        Assert.assertEquals(leifChildren.get(1).getFirstName(), "Thorkell");

        /*
        Erik's son: Thorvald
         */
        Person thorvald = children.get(2);
        Assert.assertEquals(thorvald.getFirstName(), "Thorvald");
        Assert.assertEquals(thorvald.getLastName(), "Eiriksson");
        Assert.assertEquals(leif.getGender().toLowerCase(), "male");

        /*
        Erik's son: Thorstein
         */
        Person thorstein = children.get(3);
        Assert.assertEquals(thorstein.getFirstName(), "Thorstein");
        Assert.assertEquals(thorstein.getLastName(), "Eiriksson");
        Assert.assertEquals(leif.getGender().toLowerCase(), "male");

        // And Erik had a horse.
        Assert.assertEquals(erik.getPets().get(0).getType(), "Horse");
        Assert.assertEquals(erik.getPets().get(0).getName(), "Hestur");
    }
}
