package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.DateUtil;

import java.time.LocalDate;

public class DateTests {
    @Test
    public void today() {
        LocalDate date = DateUtil.createDate("today");
        LocalDate expected = LocalDate.now();
        Assert.assertEquals(date, expected);
    }

    @Test
    public void tomorrow() {
        LocalDate date = DateUtil.createDate("tomorrow");
        LocalDate expected = LocalDate.now().plusDays(1);
        Assert.assertEquals(date, expected);
    }

    @Test
    public void yesterday() {
        LocalDate date = DateUtil.createDate("yesterday");
        LocalDate expected = LocalDate.now().minusDays(1);
        Assert.assertEquals(date, expected);
    }

    @Test
    public void lastWeek() {
        LocalDate date = DateUtil.createDate("last week");
        LocalDate expected = LocalDate.now().minusWeeks(1);
        Assert.assertEquals(date, expected);
    }

    @Test
    public void nextWeek() {
        LocalDate date = DateUtil.createDate("next week");
        LocalDate expected = LocalDate.now().plusWeeks(1);
        Assert.assertEquals(date, expected);
    }
}
