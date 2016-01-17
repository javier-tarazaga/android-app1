package com.tinygrip.android.presentation.view.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.tinygrip.android.presentation.AndroidApplication;
import com.tinygrip.android.presentation.internal.di.components.ApplicationComponent;
import com.tinygrip.android.presentation.internal.di.modules.ActivityModule;
import com.tinygrip.android.presentation.ApplicationRouter;
import javax.inject.Inject;

/**
 * Base {@link android.app.Activity} class for every Activity in this application.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    protected ApplicationRouter applicationRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        addFragment(containerViewId, fragment, false);
    }

    /**
     * Adds a {@link Fragment} to this activity's layout including the possibility to add it to the
     * back stack
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getTag());
        }

        fragmentTransaction.commit();
    }

    /**
     * Replaces a {@link Fragment} contained within this activity's layout including the possibility to add it to the
     * back stack
     *
     * @param containerViewId The container view to where add the fragment.
     * @param newFragment The new fragment to be added.
     */
    protected void replaceFragment(int containerViewId, Fragment newFragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, newFragment);

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(newFragment.getTag());
        }

        fragmentTransaction.commit();
    }

    protected void popFragmentBackStack() {
        getSupportFragmentManager().popBackStack();
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link com.tinygrip.android.presentation.internal.di.components.ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     *
     * @return {@link com.tinygrip.android.presentation.internal.di.modules.ActivityModule}
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    /**
     * Shows a {@link android.widget.Toast} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showToastMessage(String message) {
        Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
