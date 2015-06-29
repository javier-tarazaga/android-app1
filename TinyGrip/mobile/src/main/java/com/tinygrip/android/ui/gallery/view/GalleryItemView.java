package com.tinygrip.android.ui.gallery.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tinygrip.android.R;
import com.tinygrip.android.data.api.model.response.Image;
import com.tinygrip.android.ui.misc.AspectRatioImageView;

import butterknife.ButterKnife;
import butterknife.Bind;

public class GalleryItemView extends CardView {
    @Bind(R.id.gallery_image_image)
    AspectRatioImageView image;

    @Bind(R.id.gallery_image_title)
    TextView title;

    public GalleryItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void bindTo(final Image item, final Picasso picasso) {
        float heightRatio = item.height / (float) item.width;
        image.setHeightRatio(heightRatio);
        image.requestLayout();
        image.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                image.getViewTreeObserver().removeOnPreDrawListener(this);
                final int height = image.getMeasuredHeight();
                final int width = image.getMeasuredWidth();
                picasso.load(item.link).resize(width, height).into(image);
                return true;
            }
        });

        title.setText(item.title);
    }
}
