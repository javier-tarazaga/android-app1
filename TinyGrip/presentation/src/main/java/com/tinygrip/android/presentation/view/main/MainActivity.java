package com.tinygrip.android.presentation.view.main;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.tinygrip.android.R;
import com.tinygrip.android.presentation.internal.di.HasComponent;
import com.tinygrip.android.presentation.internal.di.components.ActivityComponent;
import com.tinygrip.android.presentation.internal.di.modules.ActivityModule;
import com.tinygrip.android.presentation.model.area.PreviewAreaModel;
import com.tinygrip.android.presentation.presenter.MainPresenter;
import com.tinygrip.android.presentation.view.area.map.AreaMapFragment;
import com.tinygrip.android.presentation.view.base.BaseActivity;
import com.tinygrip.android.presentation.view.navigation.fragment.HomeFragment;
import javax.inject.Inject;

/**
 * Main application screen. This is the app entry point.
 */
public class MainActivity extends BaseActivity implements MainView,
    HasComponent<ActivityComponent>, HomeFragment.HomeListener, AreaMapFragment.AreaMapListener {

    @Inject
    MainPresenter mainPresenter;

    @Bind(R.id.tb_main)
    Toolbar toolbarMain;

    @Bind(R.id.rl_progress)
    RelativeLayout relativeProgress;

    @Bind(R.id.rl_retry)
    RelativeLayout relativeRetry;

    @Bind(R.id.btn_retry)
    Button buttonRetry;

    private MainActivityComponent mainActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        this.initializeInjector();
        this.initialize();
        this.setupUI();
        this.loadRoot();
    }

    private void initializeInjector() {
        this.mainActivityComponent = DaggerMainActivityComponent.builder()
                                                                .applicationComponent(getApplicationComponent())
                                                                .activityModule(getActivityModule())
                                                                .mainActivityModule(
                                                                    new MainActivityModule("Tk029Lrjt50wPLsU"))
                                                                .build();

        this.mainActivityComponent.inject(this);
    }

    private void initialize() {
        this.mainPresenter.setView(this);
    }

    private void setupUI() {
        this.setupToolbarUI();
        this.setupHomeUI();
    }

    private void setupToolbarUI() {
        this.toolbarMain.inflateMenu(R.menu.menu_main_activity);
        this.toolbarMain.setOnMenuItemClickListener(onMenuItemClickListener);
        ViewCompat.setElevation(this.toolbarMain, R.dimen.toolbar_elevation);
    }

    private void setupHomeUI() {
        addFragment(R.id.fl_fragment, HomeFragment.newInstance());
    }

    @Override
    protected ActivityModule getActivityModule() {
        return super.getActivityModule();
    }

    @Override
    public ActivityComponent getComponent() {
        return mainActivityComponent;
    }

    @Override
    public void showLoading() {
        this.relativeProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        this.relativeProgress.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {
        this.relativeRetry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        this.relativeRetry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context getContext() {
        return MainActivity.this;
    }

    /**
     * Load the root element
     */
    private void loadRoot() {
        this.mainPresenter.initialize();
    }

    @OnClick(R.id.btn_retry)
    void onButtonRetryClick() {
        MainActivity.this.loadRoot();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onNewAreaClicked() {
        this.applicationRouter.navigateToNewArea(this);
    }

    @Override
    public void viewProfile() {
        this.applicationRouter.navigateToProfile(this);
    }

    @Override
    public void goToArea(PreviewAreaModel model) {
        this.applicationRouter.navigateToArea(this, model);
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_profile:
                    MainActivity.this.mainPresenter.onActionProfileSelected();
                    break;
            }
            return true;
        }
    };
}
