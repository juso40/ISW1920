package de.moviemanager.data;


import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;
import java.util.Date;

import de.util.DateUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class MovieLendTest {
    @Test
    @DisplayName("Test Default DueDate equals Null")
    public void testDefaultDueDateEqualsNull(){
        final Movie m1 = createNewMovie(0);
        assertNull(m1.getDueDate());
    }

    @Test
    @DisplayName("Test Set DueDate not equals Null")
    public void testSetDueDate(){
        final Movie m1 = createNewMovie(0);
        final Date d1 = new Date();
        m1.setDueDate(d1);
        assertNotNull(m1.getDueDate());
    }

    @Test
    @DisplayName("Test Set/Get of DueDate")
    public void testSetGetDueDate(){
        Movie m1 = createNewMovie(0);
        Date d1 = DateUtils.now();
        m1.setDueDate(d1);
        assertEquals(m1.getDueDate(), d1);
    }

    @Test
    @DisplayName("Test Set DueDate then delete DueDate aka return movie")
    public void testSetThenDeleteDueDate(){
        Movie m1 = createNewMovie(0);
        Date d1 = DateUtils.now();
        m1.setDueDate(d1);
        assertNotNull(m1.getDueDate());
        m1.setDueDate(null);
        assertNull(m1.getDueDate());
    }

    private Movie createNewMovie(int id){
        return new Movie(id);
    }
}
