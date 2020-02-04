package de.moviemanager.ui.masterlist.categorizer;

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

public class LendDueDate extends Categorizer<String, Movie> {
    @Override
    public String getCategoryNameFor(Movie obj) {
        Date dueDate = obj.getDueDate();
        if (dueDate == null) {
            return "Not rented";
        } else if (dueDate.before(DateUtils.nowAtMidnight())) {
            return "Overdue";
        } else {
            return (String) new SimpleDateFormat("MMMM", Locale.US).format(obj.getDueDate());
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

    public int dateComparison(String dat1, String dat2) {
        if (dat1 == "Overdue") {
            return 1;
        }
        if (dat2 == "Overdue") {
            return 0;
        }
        if (dat1 == "Not rented")
            return 1;
        if (dat2 == "Not rented")
            return 2;
        int dat1 = new SimpleDateFormat("MMMM", Locale.US).parse(dat1).getMonth();
        int dat2 = new SimpleDateFormat("MMMM", Locale.US).parse(dat2).getMonth();
        if (dat1 < dat2) {
            return 1;
        } else if (dat2 < dat1) {
            return 2;
        } else {
            return 0;
        }
        return null;
    }

    @Override
    public int compareCategories(String cat1, String cat2) {
        return alphabeticalComparison(cat1, cat2);
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

