package de.uhd.ifi.pokemonmanager.ui.adapter.singleSelection;

import android.view.ViewGroup;

public interface ContentBinder<T> {
    void bindViewToElement(ViewGroup parent, T element);
}
