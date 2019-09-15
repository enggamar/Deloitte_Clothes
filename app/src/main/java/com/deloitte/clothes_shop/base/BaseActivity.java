package com.deloitte.clothes_shop.base;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.deloitte.clothes_shop.R;
import com.deloitte.clothes_shop.model.FailureResponse;


public abstract class BaseActivity extends AppCompatActivity {

    private Observer<Throwable> errorObserver;
    private Observer<FailureResponse> failureResponseObserver;
    private Observer<Boolean> loadingStateObserver;
    private RelativeLayout baseContainer;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        baseContainer = findViewById(R.id.rl_base_container);
        setLayout();
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

        /**
         * experimental
         * */
        loadingStateObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
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

    }

    protected void onFailure(FailureResponse failureResponse) {
        showToastShort("failure:" + failureResponse.getErrorMessage());
        Log.e("onFailure: ", failureResponse.getErrorMessage() + "   " + failureResponse.getErrorCode());
    }

    protected void onErrorOccurred(Throwable throwable) {
        showToastShort("error");
        Log.e("onErrorOccurred: ", throwable.getMessage());
    }

    /**
     * Method is used to set the layout in the Base Activity.
     * Layout params of the inserted child is match parent
     */
    private void setLayout() {
        if (getResourceId() != -1) {
            removeLayout();
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT
                    , RelativeLayout.LayoutParams.MATCH_PARENT);

            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            if (layoutInflater != null) {
                View view = layoutInflater.inflate(getResourceId(), null);
                baseContainer.addView(view, layoutParams);
            }
        }
    }


    /**
     * hides keyboard onClick anywhere besides edit text
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * Method is used by the sub class for passing the id of the layout ot be inflated in the relative layout
     *
     * @return id of the resource to be inflated
     */
    protected abstract int getResourceId();


    public void addFragment(int layoutResId, BaseFragment fragment, String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) == null)
            getSupportFragmentManager().beginTransaction()
                    .add(layoutResId, fragment, tag)
                    .commit();
    }

    public void addFragmentWithBackstack(int layoutResId, BaseFragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction()
                .add(layoutResId, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }


    /**
     * This method is used to remove the view already present as a child in relative layout.
     */
    private void removeLayout() {
        if (baseContainer.getChildCount() >= 1)
            baseContainer.removeAllViews();
    }

    public void showToastShort(CharSequence message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showProgressDialog() {
        try {
            if (mProgressDialog != null)
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
            mProgressDialog = ProgressDialog.show(this, null, null, true);
            mProgressDialog.setContentView(R.layout.progress_bar_view);

            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            mProgressDialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideProgressDialog() {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing())
                mProgressDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void popFragment() {
        if (getSupportFragmentManager() != null) {
            getSupportFragmentManager().popBackStackImmediate();
        }
    }

    public Fragment getCurrentFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        String fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
        Fragment currentFragment = fragmentManager.findFragmentByTag(fragmentTag);
        return currentFragment;
    }

}
