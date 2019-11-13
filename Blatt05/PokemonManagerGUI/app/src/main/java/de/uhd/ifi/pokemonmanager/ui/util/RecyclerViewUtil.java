package de.uhd.ifi.pokemonmanager.ui.util;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewUtil {

    private RecyclerViewUtil(){

    }

    public static RecyclerView.LayoutManager createLayoutManager(final Context context) {
        final LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        return manager;
    }
}
