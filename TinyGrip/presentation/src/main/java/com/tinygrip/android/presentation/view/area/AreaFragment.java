package com.tinygrip.android.presentation.view.area;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.tinygrip.android.R;
import com.tinygrip.android.domain.model.area.Area;
import com.tinygrip.android.presentation.model.area.PreviewAreaModel;
import com.tinygrip.android.presentation.view.base.BaseFragment;
import javax.inject.Inject;

/**
 * Fragment that shows the first step when creating a new area
 **/
public class AreaFragment extends BaseFragment implements AreaView {

    private static final String ARGUMENT_KEY_PREVIEW_AREA = "com.tinygrip.android.ARGUMENT_PREVIEW_AREA";

    /**
     * Interface for listening to Area related events
     */
    public interface AreaListener {
        void onBackClicked();
    }

    @Bind(R.id.tb_area) Toolbar toolbar;
    @Bind(R.id.rl_progress) RelativeLayout rlProgress;
    @Bind(R.id.tv_areaName) TextView tvAreaName;
    @Bind(R.id.tv_areaDesc) TextView tvAreaDesc;
    @Bind(R.id.tv_areaImage) ImageView ivAreaImage;
    @Bind(R.id.rl_retry) RelativeLayout rlRetry;

    @Inject
    AreaPresenter areaPresenter;

    private AreaListener areaListener;
    private PreviewAreaModel previewAreaModel;

    public static AreaFragment newInstance(PreviewAreaModel previewArea) {
        AreaFragment areaFragment = new AreaFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(ARGUMENT_KEY_PREVIEW_AREA, previewArea);
        areaFragment.setArguments(bundle);

        return areaFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof AreaActivity) {
            this.areaListener = (AreaActivity) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_area, container, false);
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
        this.areaPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.areaPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        this.areaPresenter.destroy();
        super.onDestroy();
    }

    @Override
    public void renderArea(Area area) {
        if (area != null) {
            this.tvAreaName.setText(area.getName());
            this.tvAreaDesc.setText(area.getDescription());

            Glide.with(AreaFragment.this).load(area.getImages().iterator().next().getLink().getHref()).into(this.ivAreaImage);
        }
    }

    @Override
    public void goBack() {
        if (this.areaListener != null) {
            this.areaListener.onBackClicked();
        }
    }

    @Override
    public void showLoading() {
        this.rlProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        this.rlProgress.setVisibility(View.GONE);
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

    private void initialize() {
        this.getComponent(AreaComponent.class).inject(this);
        this.areaPresenter.setView(this);
        this.previewAreaModel = (PreviewAreaModel) getArguments().get(ARGUMENT_KEY_PREVIEW_AREA);
        this.areaPresenter.initialize(this.previewAreaModel);
    }

    private void setupUI() {
        setToolbar();
    }

    private void setToolbar() {
        this.toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_arrow_back));
        this.toolbar.setNavigationOnClickListener(onToolbarBackClickListener);
    }

    @OnClick(R.id.btn_retry)
    void onButtonRetryClick() {
        AreaFragment.this.onButtonRetryClick();
    }

    private View.OnClickListener onToolbarBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AreaFragment.this.areaPresenter.onBackClicked();
        }
    };
}
