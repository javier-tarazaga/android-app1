package com.tinygrip.android.ui.image;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tinygrip.android.R;
import com.tinygrip.android.base.ComponentFinder;
import com.tinygrip.android.base.mvp.BaseView;
import com.tinygrip.android.data.api.model.response.Image;
import com.tinygrip.android.ui.misc.BetterViewAnimator;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Bind;

public class ImgurImageView extends BetterViewAnimator implements BaseView {

    @Inject Picasso picasso;

    public ImgurImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ImgurImageComponent component = ComponentFinder.findActivityComponent(context);
        component.inject(this);
    }

    @Bind(R.id.imgur_image_content)
    ImageView imageView;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void bindTo(Image image) {
        picasso.load(image.link).into(imageView);
    }

    @Override
    public void showLoading() {
        setDisplayedChildId(R.id.imgur_image_progress);
    }

    @Override
    public void showContent() {
        setDisplayedChildId(R.id.imgur_image_content);
    }

    @Override
    public void showError(Throwable throwable) {
        setDisplayedChildId(R.id.imgur_image_error_view);
    }

    public interface Injector {
        void inject(ImgurImageView view);
    }
}
