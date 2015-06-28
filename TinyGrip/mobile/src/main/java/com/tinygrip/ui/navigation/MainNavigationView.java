package com.tinygrip.ui.navigation;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;
import com.tinygrip.R;
import com.tinygrip.base.ComponentFinder;
import com.tinygrip.base.mvp.BaseView;
import com.tinygrip.data.api.model.response.Image;
import com.tinygrip.ui.gallery.GalleryActivity;
import com.tinygrip.ui.gallery.view.GalleryAdapter;
import com.tinygrip.ui.image.ImgurImageComponent;
import com.tinygrip.ui.misc.BetterViewAnimator;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Bind;

public class MainNavigationView extends RelativeLayout implements BaseView {

    @Bind(R.id.main_toolbar) Toolbar toolbar;
    @Bind(R.id.main_progress) ProgressBar progressBar;
    @Bind(R.id.main_viewpager)  ViewPager viewPager;
    @Bind(R.id.main_sliding_tabs) TabLayout tabLayout;
    @Bind(R.id.main_error_view) View errorView;

    @Inject
    MainNavigationActivity.Presenter presenter;

    @Inject
    FragmentManager fragmentManager;

    private final MainTabPagerAdapter adapter;

    public MainNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        MainNavigationComponent component = ComponentFinder.findActivityComponent(context);
        component.inject(this);

        adapter = new MainTabPagerAdapter(fragmentManager, getContext());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public MainTabPagerAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(VISIBLE);
        errorView.setVisibility(GONE);
        showTabLayout(false);
    }

    @Override
    public void showContent() {
        progressBar.setVisibility(GONE);
        errorView.setVisibility(GONE);
        showTabLayout(true);
    }

    @Override
    public void showError(Throwable throwable) {
        errorView.setVisibility(VISIBLE);
        progressBar.setVisibility(GONE);
        showTabLayout(false);
    }

    public interface Injector {
        void inject(MainNavigationView view);
    }

    private void showTabLayout(boolean show) {
        tabLayout.setVisibility(show ? VISIBLE : GONE);
        viewPager.setVisibility(show ? VISIBLE : GONE);
    }
}
