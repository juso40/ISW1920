package de.moviemanager.ui.masterlist.categorizer;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import de.moviemanager.data.Movie;
import de.moviemanager.ui.masterlist.elements.ContentElement;
import de.moviemanager.ui.masterlist.elements.DividerElement;
import de.moviemanager.ui.masterlist.elements.HeaderElement;
import de.util.DateUtils;

import static de.util.DateUtils.dateToText;
import static de.util.DateUtils.textToDate;
import static de.util.StringUtils.alphabeticalComparison;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;

public class LendDueDate extends Categorizer<String, Movie> {
    @Override
    public String getCategoryNameFor(Movie obj) {
        Date dueDate = obj.getDueDate();
        if (dueDate == null) {
            return "Not rented";
        } else if (dueDate.before(DateUtils.nowAtMidnight())) {
            return "Overdue";
        } else {
            return new SimpleDateFormat("MMMM", Locale.US).format(dueDate);
        }
    }

    @Override
    public HeaderElement<Movie> createHeader(final String category) {
        return new HeaderElement<>(category);
    }

    @Override
    protected ContentElement<Movie> createContent(Movie obj) {
        Date dueDate = obj.getDueDate();
        if (dueDate == null) {
            return new ContentElement<>(obj.name(), "");
        } else {
            return new ContentElement<>(obj.name(), dateToText(obj.getDueDate()));
        }
    }

    @Override
    public DividerElement createDivider() {
        return new DividerElement();
    }

    private int dateComparison(String dat1, String dat2) {
        if (Objects.equals(dat1, "Overdue")) {
            return -1;
        }
        if (Objects.equals(dat2, "Overdue")) {
            return 1;
        }
        if (Objects.equals(dat1, "Not rented"))
            return 1;
        if (Objects.equals(dat2, "Not rented"))
            return -1;
        int dat1_i = 0;
        try {
            Date temp1 =  new SimpleDateFormat("MMMM", Locale.US).parse(dat1);
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(temp1);
            dat1_i = cal1.get(Calendar.MONTH);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int dat2_i = 0;
        try {
            Date temp2 =  new SimpleDateFormat("MMMM", Locale.US).parse(dat2);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(temp2);
            dat2_i = cal2.get(Calendar.MONTH);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (dat1_i < dat2_i) {
            return -1;
        } else if (dat2_i < dat1_i) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int compareCategories(String cat1, String cat2) {
        return dateComparison(cat1, cat2);
    }

    @Override
    public int compareContent(ContentElement<Movie> element1, ContentElement<Movie> element2) {
        Date dateElement1 = textToDate("dd-MM", element1.getMeta());
        Date dateElement2 = textToDate("dd-MM", element2.getMeta());
        if (dateElement1 == null || dateElement2 == null) {
            return alphabeticalComparison(element1.getTitle(), element2.getTitle());
        } else {
            return dateElement1.compareTo(dateElement2);
        }
    }
}