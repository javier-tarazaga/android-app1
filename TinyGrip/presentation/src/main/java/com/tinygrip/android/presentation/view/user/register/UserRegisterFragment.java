
package com.tinygrip.android.presentation.view.user.register;

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
import com.tinygrip.android.presentation.presenter.user.UserRegisterPresenter;
import com.tinygrip.android.presentation.view.base.BaseFragment;
import com.tinygrip.android.presentation.view.user.UserComponent;
import javax.inject.Inject;

/**
 * Fragment that shows a register screen.
 */
public class UserRegisterFragment extends BaseFragment implements UserRegisterView {

    private ProgressDialog loadingDialog;

    /**
     * Interface for listening User Register Fragment events
     */
    public interface UserRegisterListener {
        void onUpClicked();
        void onRegisterSuccessful();
    }

    @Inject
    UserRegisterPresenter userRegisterPresenter;

    @Bind(R.id.tb_login)
    Toolbar toolbar;

    @Bind(R.id.et_email)
    EditText editEmail;

    @Bind(R.id.et_password)
    EditText editPassword;

    @Bind(R.id.et_confirm_password)
    EditText editConfirmPassword;

    @Bind(R.id.rl_progress)
    RelativeLayout rlProgress;

    private UserRegisterListener userRegisterListener;

    public UserRegisterFragment() { super(); }

    public static UserRegisterFragment newInstance() {
        return new UserRegisterFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof UserRegisterActivity) {
            this.userRegisterListener = (UserRegisterActivity) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_user_register, container, false);
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
        this.userRegisterPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.userRegisterPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.userRegisterPresenter.destroy();
    }

    private void initialize() {
        this.getComponent(UserComponent.class).inject(this);
        this.userRegisterPresenter.setView(this);
    }

    private void setupUI() {
        this.setToolbar();
        this.setLoadingDialog();
    }

    private void setToolbar() {
        this.toolbar.setTitle("Register");
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
        // Not implemented in this view
    }

    @Override
    public void hideRetry() {
        // Not implemented in this view
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public void goUp() {
        if (this.userRegisterListener != null) {
            this.userRegisterListener.onUpClicked();
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
    public void showInvalidConfirmPassword(String errorMessage) {
        this.editConfirmPassword.setError(errorMessage);
    }

    @Override
    public void showRegisterSuccessful() {
        if (this.userRegisterListener != null) {
            this.userRegisterListener.onRegisterSuccessful();
        }
    }

    @Override
    public Context getContext() {
        return getActivity().getApplicationContext();
    }

    /**
     * Simply try to register the user
     */
    private void registerUser() {
        this.userRegisterPresenter.registerUser(
            editEmail.getText().toString(),
            editPassword.getText().toString(),
            editConfirmPassword.getText().toString());
    }

    @OnClick(R.id.btn_register)
    void onButtonRegisterClick() {
        UserRegisterFragment.this.registerUser();
    }

    private View.OnClickListener onToolbarBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            UserRegisterFragment.this.userRegisterPresenter.onUpClicked();
        }
    };
}
