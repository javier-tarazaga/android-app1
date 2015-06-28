package com.tinygrip.ui.navigation;

import android.os.Bundle;

import com.tinygrip.R;
import com.tinygrip.TinyGripComponent;
import com.tinygrip.base.HasComponent;
import com.tinygrip.base.mvp.BaseActivity;
import com.tinygrip.base.mvp.BasePresenter;
import com.tinygrip.base.mvp.BaseView;

import javax.inject.Inject;


public class MainNavigationActivity extends BaseActivity implements HasComponent<MainNavigationComponent> {

    @Inject
    Presenter presenter;

    private MainNavigationComponent mainNavigationComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onCreateComponent(TinyGripComponent tinyGripComponent) {
        mainNavigationComponent = DaggerMainNavigationComponent.builder().
                tinyGripComponent(tinyGripComponent).
                mainNavigationModule(new MainNavigationModule(this)).build();
        mainNavigationComponent.inject(this);
    }

    @Override
    protected void onDestroy() {
        mainNavigationComponent = null;
        super.onDestroy();
    }

    @Override
    protected int viewId() {
        return R.id.main_navigation_view;
    }

    @Override
    protected int layoutId() {
        return R.layout.main_navigation_view;
    }

    @Override
    protected BasePresenter<? extends BaseView> presenter() {
        return presenter;
    }

    @Override
    public MainNavigationComponent getComponent() {
        return mainNavigationComponent;
    }

    @MainNavigationScope
    public static class Presenter extends BasePresenter<MainNavigationView> {

        @Inject
        public Presenter() {}

        @Override
        protected void onLoad() {
            super.onLoad();

            getView().showContent();
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
        }

        public void refresh() {
            final MainNavigationView view = getView();
            if (view != null) {
                //view.setRefreshed();
            }
        }
    }
}
