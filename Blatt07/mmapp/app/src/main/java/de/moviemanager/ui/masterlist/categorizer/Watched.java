package de.moviemanager.ui.masterlist.categorizer;

import androidx.arch.core.util.Function;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import de.moviemanager.data.Movie;
import de.moviemanager.data.Nameable;
import de.moviemanager.ui.masterlist.elements.ContentElement;
import de.moviemanager.ui.masterlist.elements.DividerElement;
import de.moviemanager.ui.masterlist.elements.HeaderElement;


public class Watched<T extends Nameable> extends Categorizer<String, Movie> {

    private static final String DATE_FORMAT = "yyyy - MMMM";

    @Override
    public String getCategoryNameFor(Movie obj) {
        Date watchDate = obj.getWatchDate();
        if(watchDate == null)
            return "Never";
        Calendar cal = Calendar.getInstance();
        cal.setTime(watchDate);
        SimpleDateFormat month_date = new SimpleDateFormat(DATE_FORMAT, Locale.US);
        return month_date.format(cal.getTime());
    }

    @Override
    public HeaderElement<Movie> createHeader(String category) {
        return new HeaderElement<>(category);
    }

    @Override
    protected ContentElement<Movie> createContent(Movie obj) {
        if (obj.getWatchDate() == null){
            return new ContentElement<>(obj.name(), "Never");
        }
        return new ContentElement<>(obj.name(), new SimpleDateFormat("yyyy - MM - dd", Locale.US).format(obj.getWatchDate()));
    }

    @Override
    public DividerElement createDivider() {
        return new DividerElement();
    }

    @Override
    public int compareCategories(String cat1, String cat2) {
        if (cat1 == "Never"){
            return -1;
        }
        if (cat2 == "Never"){
            return 1;
        }
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = new SimpleDateFormat(DATE_FORMAT, Locale.US).parse(cat1);
            date2 = new SimpleDateFormat(DATE_FORMAT, Locale.US).parse(cat2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1.compareTo(date2);
    }

    @Override
    public int compareContent(ContentElement<Movie> element1, ContentElement<Movie> element2) {
        Date date1 = element1.retrieveContentModel().getWatchDate();
        Date date2 = element2.retrieveContentModel().getWatchDate();
        if (date1 == null){
            return -1;
        }
        if (date2 == null){
            return 1;
        }

        return date1.compareTo(date2);
    }
}