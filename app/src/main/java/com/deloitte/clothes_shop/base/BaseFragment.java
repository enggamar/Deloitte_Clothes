package com.deloitte.clothes_shop.base;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;


import com.deloitte.clothes_shop.model.FailureResponse;

import java.util.Objects;


public class BaseFragment extends Fragment {
//
    private Observer<Throwable> errorObserver;
    private Observer<FailureResponse> failureResponseObserver;
    private Observer<Boolean> loadingStateObserver;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initObservers();
    }

    private void initObservers() {
        errorObserver = new Observer<Throwable>() {
            @Override
            public void onChanged(@Nullable Throwable throwable) {
                onErrorOccurred(throwable);
            }
        };

        failureResponseObserver = new Observer<FailureResponse>() {
            @Override
            public void onChanged(@Nullable FailureResponse failureResponse) {
                onFailure(failureResponse);
            }
        };

        loadingStateObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(aBoolean!=null)
                    onLoadingStateChanged(aBoolean);
            }
        };
    }

    public Observer<Throwable> getErrorObserver() {
        return errorObserver;
    }

    public Observer<FailureResponse> getFailureResponseObserver() {
        return failureResponseObserver;
    }

    public Observer<Boolean> getLoadingStateObserver() {
        return loadingStateObserver;
    }

    protected void onLoadingStateChanged(boolean aBoolean) {
        if (aBoolean) {
            showProgressDialog();
        } else {
            hideProgressDialog();
        }
    }

    protected void onFailure(FailureResponse failureResponse) {
        hideProgressDialog();
        showToastShort(failureResponse.getErrorMessage());
        Log.e("onFailure: ", failureResponse.getErrorMessage() + "   " + failureResponse.getErrorCode());
    }

    protected void onErrorOccurred(Throwable throwable) {
        hideProgressDialog();
        showToastShort(throwable.getMessage());
        Log.e("onErrorOccurred: ", throwable.getMessage());
    }

    public void showToastLong(CharSequence message) {
        ((BaseActivity) Objects.requireNonNull(getActivity())).showToastLong(message);
    }

    public void showToastShort(CharSequence message) {
        ((BaseActivity) Objects.requireNonNull(getActivity())).showToastShort(message);
    }

    public void showProgressDialog() {
        ((BaseActivity) Objects.requireNonNull(getActivity())).showProgressDialog();
    }

    public void hideProgressDialog() {
        ((BaseActivity) Objects.requireNonNull(getActivity())).hideProgressDialog();
    }


    public void popFragment() {
        if (getFragmentManager() != null) {
            getFragmentManager().popBackStackImmediate();
        }
    }

}
