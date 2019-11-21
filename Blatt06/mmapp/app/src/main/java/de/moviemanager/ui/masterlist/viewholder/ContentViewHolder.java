package de.moviemanager.ui.masterlist.viewholder;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import de.moviemanager.R;

import static de.moviemanager.ui.masterlist.elements.Type.CONTENT;

public class ContentViewHolder extends TypedViewHolder {

    private final ImageView imageView;
    private final TextView titleView;
    private final TextView metadataView;

    public ContentViewHolder(@NonNull View itemView) {
        super(itemView, CONTENT);
        imageView = itemView.findViewById(R.id.content_image);
        titleView = itemView.findViewById(R.id.content_title);
        metadataView = itemView.findViewById(R.id.content_subtitle);
    }

    public void setImage(Drawable d) {
        imageView.setImageDrawable(d);
    }

    public void setTitle(String title) {
        titleView.setText(title);
    }

    public void setMetaText(String metaText) {
        this.metadataView.setText(metaText);
    }
}
