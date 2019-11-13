package de.uhd.ifi.pokemonmanager.ui.adapter.singleSelection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.List;

import de.uhd.ifi.pokemonmanager.R;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public class SingleSelectionAdapter<T>
        extends Adapter<SelectionViewHolder<T>>
        implements IndexedAdapter<SelectionProxy<T>> {
    private final LayoutInflater inflater;
    private final List<SelectionProxy<T>> data;

    @LayoutRes private final int elementLayoutId;

    private SingleSelectionListener<T> listener;
    private final ContentBinder<T> binder;

    private SingleSelectionAdapter(final Context context,
                                   @LayoutRes int elementLayoutId,
                                   final List<T> data,
                                   final ContentBinder<T> binder) {
        this.inflater = LayoutInflater.from(context);
        this.elementLayoutId = elementLayoutId;
        this.data = data.stream().map(SelectionProxy::new).collect(toList());
        this.binder = binder;
    }

    private void setOnSelectionChangedListener(SingleSelectionListener<T> listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public SelectionViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = inflater.inflate(R.layout.listitem_one_selectable, parent, false);
        final FrameLayout root = view.findViewById(R.id.content);
        inflater.inflate(elementLayoutId, root, true);
        final SelectionViewHolder<T> holder = new SelectionViewHolder<>(this, view);
        holder.setOnSelectionChangedListener(this::onSelectionChanged);
        return holder;
    }

    private void onSelectionChanged(T element) {
        if (listener != null) {
            listener.currentSelected(element);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull SelectionViewHolder<T> holder, int position) {
        holder.bindView(position, binder);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public SelectionProxy<T> getElementByPosition(int position) {
        return data.get(position);
    }

    public static <T> Builder<T> builder(Context context) {
        return new Builder<>(context);
    }

    public static class Builder<T> {
        private final Context context;
        @LayoutRes private int elementLayoutId;
        private List<T> data;
        private ContentBinder<T> binder;
        private SingleSelectionListener<T> onSelectionChanged;

        Builder(final Context context) {
            this.context = context;
        }

        public Builder<T> setElementLayoutId(@LayoutRes int elementLayoutId) {
            this.elementLayoutId = elementLayoutId;
            return this;
        }

        public Builder<T> setData(final List<T> data) {
            this.data = data;
            return this;
        }

        public Builder<T> setContentBinder(final ContentBinder<T> binder) {
            this.binder = binder;
            return this;
        }

        public Builder<T> setOnSelectionChanged(final SingleSelectionListener<T> onSelectionChanged) {
            this.onSelectionChanged = onSelectionChanged;
            return this;
        }

        public SingleSelectionAdapter<T> build() {
            final SingleSelectionAdapter<T> result = new SingleSelectionAdapter<>(
                    requireNonNull(context),
                    elementLayoutId,
                    requireNonNull(data),
                    requireNonNull(binder)
            );
            result.setOnSelectionChangedListener(onSelectionChanged);
            return result;
        }
    }
}

