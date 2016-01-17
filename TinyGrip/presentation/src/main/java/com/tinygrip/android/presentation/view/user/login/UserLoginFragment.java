
package com.tinygrip.android.presentation.view.user.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.tinygrip.android.R;
import com.tinygrip.android.presentation.EnvConfig;
import com.tinygrip.android.presentation.model.UserModel;
import com.tinygrip.android.presentation.view.base.BaseFragment;
import com.tinygrip.android.presentation.view.user.UserComponent;
import javax.inject.Inject;

/**
 * Fragment that shows a login screen.
 */
public class UserLoginFragment extends BaseFragment implements UserLoginView {

    private ProgressDialog loadingDialog;

    /**
     * Interface for listening User Login Fragment events
     */
    public interface UserLoginListener {
        void onUpClicked();
        void onRegisterLinkClicked();
        void onLoginSuccessful(UserModel userModel);
    }

    @Inject
    UserLoginPresenter userLoginPresenter;

    @Bind(R.id.tb_login)
    Toolbar toolbar;

    @Bind(R.id.et_username)
    EditText editEmail;

    @Bind(R.id.et_password)
    EditText editPassword;

    @Bind(R.id.rl_progress)
    RelativeLayout rlProgress;

    private UserLoginListener userLoginListener;

    public UserLoginFragment() { super(); }

    public static UserLoginFragment newInstance() {
        return new UserLoginFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof UserLoginListener) {
            this.userLoginListener = (UserLoginListener) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_user_login, container, false);
        ButterKnife.bind(this, fragmentView);

        return fragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.initialize();
        this.setupUI();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.userLoginPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.userLoginPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.userLoginPresenter.destroy();
    }

    private void initialize() {
        this.getComponent(UserComponent.class).inject(this);
        this.userLoginPresenter.setView(this);
    }

    private void setupUI() {
        if (EnvConfig.USE_TEST_CREDS) {
            this.setupTestCredentials();
        }

        this.setToolbar();
        this.setLoadingDialog();
    }

    private void setupTestCredentials() {
        this.editEmail.setText("ostap1010@outlook.com");
        this.editPassword.setText("QaWs1!");
    }

    private void setToolbar() {
        this.toolbar.setTitle("Login");
        this.toolbar.setNavigationIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_action_arrow_back));
        this.toolbar.setNavigationOnClickListener(onToolbarBackClickListener);
    }

    private void setLoadingDialog() {
        this.loadingDialog = new ProgressDialog(getActivity());

        this.loadingDialog.setIndeterminate(true);
        this.loadingDialog.setCancelable(true);
        this.loadingDialog.setInverseBackgroundForced(false);
        this.loadingDialog.setCanceledOnTouchOutside(true);
        this.loadingDialog.setMessage("Loading");
    }

    @Override
    public void showLoading() {
        this.loadingDialog.show();
    }

    @Override
    public void hideLoading() {
        this.loadingDialog.dismiss();
    }

    @Override
    public void showRetry() {
        // Not necessary in this view
    }

    @Override
    public void hideRetry() {
        // Not necessary in this view
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public void goUp() {
        if (this.userLoginListener != null) {
            this.userLoginListener.onUpClicked();
        }
    }

    @Override
    public void showInvalidEmail(String errorMessage) {
        this.editEmail.setError(errorMessage);
    }

    @Override
    public void showInvalidPassword(String errorMessage) {
        this.editPassword.setError(errorMessage);
    }

    @Override
    public void loginSuccessful(UserModel userModel) {
        if (this.userLoginListener != null) {
            this.userLoginListener.onLoginSuccessful(userModel);
        }
    }

    @Override
    public Context getContext() {
        return getActivity().getApplicationContext();
    }

    /**
     * Simply perform a login
     */
    private void loginUser() {
        this.userLoginPresenter.login(editEmail.getText().toString(), editPassword.getText().toString());
    }

    /**
     * Show the registration view
     */
    private void registerLinkClicked() {
        if (this.userLoginListener != null) {
            this.userLoginListener.onRegisterLinkClicked();
        }
    }

    @OnClick(R.id.btn_login)
    void onButtonLoginClick() {
        UserLoginFragment.this.loginUser();
    }

    @OnClick(R.id.tv_register_link)
    void onRegisterClick() {
        UserLoginFragment.this.registerLinkClicked();
    }

    private View.OnClickListener onToolbarBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            UserLoginFragment.this.userLoginPresenter.onUpClicked();
        }
    };
}
