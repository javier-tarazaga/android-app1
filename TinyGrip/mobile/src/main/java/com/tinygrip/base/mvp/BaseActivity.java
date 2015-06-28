package com.tinygrip.base.mvp;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tinygrip.TinyGripApp;
import com.tinygrip.TinyGripComponent;
import com.tinygrip.ui.AppContainer;

import javax.inject.Inject;


public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    AppContainer appContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle params = getIntent().getExtras();
        if (params != null) {
            onExtractParams(params);
        }
        super.onCreate(savedInstanceState);

        TinyGripApp app = TinyGripApp.get(this);
        onCreateComponent(app.component());
        if (appContainer == null) {
            throw new IllegalStateException("No injection happened. Add component.inject(this) in onCreateComponent() implementation.");
        }
        Registry.add(this, viewId(), presenter());
        final LayoutInflater layoutInflater = getLayoutInflater();
        ViewGroup container = appContainer.get(this);
        layoutInflater.inflate(layoutId(), container);
    }

    protected void onExtractParams(@NonNull Bundle params) {
        // default no implemetation
    }

    /**
     * Must be implemented by derived activities. Injection must be performed here.
     * Otherwise IllegalStateException will be thrown. Derived activity is
     * responsible to create and store it's component.
     * @param tinyGripComponent application level component
     */
    protected abstract void onCreateComponent(TinyGripComponent tinyGripComponent);
    protected abstract @LayoutRes int layoutId();
    protected abstract BasePresenter<? extends BaseView> presenter();
    protected abstract @IdRes int viewId();
}
