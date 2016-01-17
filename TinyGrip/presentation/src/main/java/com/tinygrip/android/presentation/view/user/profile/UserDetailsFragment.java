
package com.tinygrip.android.presentation.view.user.profile;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.tinygrip.android.R;
import com.tinygrip.android.presentation.model.UserModel;
import com.tinygrip.android.presentation.presenter.user.UserDetailsPresenter;
import com.tinygrip.android.presentation.view.base.BaseFragment;
import com.tinygrip.android.presentation.view.user.UserComponent;
import javax.inject.Inject;

/**
 * Fragment that shows details of a certain user.
 */
public class UserDetailsFragment extends BaseFragment implements UserDetailsView {

    /**
     * Interface for listening User Details Fragment events
     */
    public interface UserDetailsListener {
        void onUpClicked();
        void logoutSuccessfully();
    }

    @Bind(R.id.tb_user_details)
    Toolbar toolbar;

    @Bind(R.id.iv_cover)
    ImageView ivCover;

    @Bind(R.id.iv_avatar)
    ImageView ivAvatar;

    @Bind(R.id.tv_fullname)
    TextView tvFullName;

    @Bind(R.id.tv_email)
    TextView tvEmail;

    @Bind(R.id.rl_progress)
    RelativeLayout rlProgress;

    @Bind(R.id.rl_retry)
    RelativeLayout rlRetry;

    @Bind(R.id.btn_retry)
    Button btnRetry;

    @Inject
    UserDetailsPresenter userDetailsPresenter;

    private UserDetailsListener userDetailsListener;


    public UserDetailsFragment() { super(); }

    public static UserDetailsFragment newInstance() {
        UserDetailsFragment userDetailsFragment = new UserDetailsFragment();

        Bundle argumentsBundle = new Bundle();
        userDetailsFragment.setArguments(argumentsBundle);

        return userDetailsFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof UserDetailsListener) {
            this.userDetailsListener = (UserDetailsListener) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_user_details, container, false);
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
        this.userDetailsPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.userDetailsPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.userDetailsPresenter.destroy();
    }

    private void initialize() {
        this.getComponent(UserComponent.class).inject(this);
        this.userDetailsPresenter.setView(this);
        this.userDetailsPresenter.initialize();
    }

    private void setupUI() {
        this.setToolbar();
    }

    private void setToolbar() {
        this.toolbar.setTitle("Account");
        this.toolbar.inflateMenu(R.menu.menu_user_details);
        this.toolbar.setNavigationIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_action_arrow_back));
        this.toolbar.setNavigationOnClickListener(onToolbarBackClickListener);
        this.toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    @Override
    public void renderUser(UserModel user) {
        if (user != null) {

            Glide.with(UserDetailsFragment.this).load(user.getCoverUrl()).into(this.ivCover);
            Glide.with(UserDetailsFragment.this).load(user.getAvatarUrl()).into(this.ivAvatar);

            this.tvFullName.setText(user.getFirstName() + " " + user.getLastName());
            this.tvEmail.setText(user.getEmail());
        }
    }

    @Override
    public void goUp() {
        if (this.userDetailsListener != null) {
            this.userDetailsListener.onUpClicked();
        }
    }

    @Override
    public void notifyLogoutSuccessfully() {
        if (this.userDetailsListener != null) {
            this.userDetailsListener.logoutSuccessfully();
        }
    }

    @Override
    public void showLoading() {
        this.rlProgress.setVisibility(View.VISIBLE);
        this.getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {
        this.rlProgress.setVisibility(View.GONE);
        this.getActivity().setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void showRetry() {
        this.rlRetry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        this.rlRetry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context getContext() {
        return getActivity().getApplicationContext();
    }

    /**
     * Loads all users.
     */
    private void loadUserDetails() {
        if (this.userDetailsPresenter != null) {
            this.userDetailsPresenter.initialize();
        }
    }

    @OnClick(R.id.btn_retry)
    void onButtonRetryClick() {
        UserDetailsFragment.this.loadUserDetails();
    }

    private View.OnClickListener onToolbarBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            UserDetailsFragment.this.userDetailsPresenter.onUpClicked();
        }
    };

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_logout:
                    UserDetailsFragment.this.userDetailsPresenter.onLogoutClicked();
                    break;
            }

            return true;
        }
    };
}
