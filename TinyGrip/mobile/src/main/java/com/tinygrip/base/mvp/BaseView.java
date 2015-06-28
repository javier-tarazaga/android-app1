package com.tinygrip.base.mvp;

public interface BaseView {
    void showLoading();
    void showContent();
    void showError(Throwable throwable);
}
