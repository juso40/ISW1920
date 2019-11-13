package de.uhd.ifi.pokemonmanager.ui.adapter.singleSelection;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.function.Consumer;

import de.uhd.ifi.pokemonmanager.R;

public class SelectionViewHolder<T> extends ViewHolder {
    private final SingleSelectionAdapter<T> adapter;
    private CompoundButton selectedMarker;
    private FrameLayout contentRoot;
    private Consumer<T> listener;

    SelectionViewHolder(final SingleSelectionAdapter<T> adapter, final View item) {
        super(item);
        item.setTag(this);
        this.adapter = adapter;
        this.selectedMarker = item.findViewById(R.id.selection_marker);
        this.contentRoot = item.findViewById(R.id.content);
    }

    void setOnSelectionChangedListener(final Consumer<T> listener) {
        this.listener = listener;
        selectedMarker.setOnCheckedChangeListener((btn, state) -> onCheckedChange(btn));
    }

    private void onCheckedChange(final CompoundButton box) {
        if (listener != null && box.getId() == selectedMarker.getId()) {
            onItemClicked(listener);
        }
    }

    private void onItemClicked(final Consumer<T> listener) {
        int position = getAdapterPosition();
        final SelectionProxy<T> element = adapter.getElementByPosition(position);
        listener.accept(element.getObj());
        element.switchEnabled();
    }

    void bindView(int position, ContentBinder<T> binder) {
        final SelectionProxy<T> element = adapter.getElementByPosition(position);
        selectedMarker.setOnCheckedChangeListener(null);
        selectedMarker.setChecked(element.isEnabled());
        selectedMarker.setOnCheckedChangeListener((btn, state) -> onCheckedChange(btn));
        binder.bindViewToElement(contentRoot, element.getObj());
    }
}
