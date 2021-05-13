package com.inspur.osp.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.inspur.osp.ui.inter.IWaitDialog;
import com.inspur.osp.util.DialogHelp;
import com.inspur.topo.R;


//import butterknife.BindView;
//import butterknife.ButterKnife;

/**
 * Created by liuchen_ on 2017/12/6.
 */

public abstract class BaseActivity extends AppCompatActivity implements IWaitDialog {

    @Nullable
    protected Toolbar mToolbar;
    @Nullable
    protected TextView mTitleView;
    @Nullable
    CoordinatorLayout mCoordinatorContainer;
//    @Nullable
//    @BindView(R.id.appbar)
//    protected AppBarLayout appBarLayout;
//    @Nullable
//    @BindView(R.id.right_button)
//    protected TextView rightBtn;

    protected Context mContext;
//    protected ProgressDialog mProgressLoading;

    protected AlertDialog mProgressLoading;

    private boolean isBackground = false;
    private boolean progressShow;

    private Snackbar mSnackbar;

    private View progressBarView;
    private TextView loadText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeSetView(savedInstanceState);
        setContentView(getLayoutId());
        afterSetView(savedInstanceState);

        mContext = this;
        setUpProgressbar();
        getIntentData();
        initView();
        initListener();
        initData();
    }

    private void setUpProgressbar() {
//        progressBarView = LayoutInflater.from(mContext).inflate(R.layout.layout_progressbar, null);
//        loaderView = progressBarView.findViewById(R.id.blv);
//        loadText = progressBarView.findViewById(R.id.load_text);
    }

    private ViewGroup getRootView() {
        return (ViewGroup) (((ViewGroup) findViewById(android.R.id.content)).getChildAt(0));
    }

    protected void getIntentData() {
        setTitle("");
        String title = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(title)) {
            setTitleMsg(title);
        }
    }

    protected abstract int getLayoutId();

    protected void initView() {
        setupToolBar();
    }

    protected abstract void initData();

    protected void initListener() {

    }

    //设置ToolBar
    protected void setupToolBar() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);//设置不显示Title
        }
    }

    /**
     * 设置标题
     *
     * @param title
     */
    protected void setTitleMsg(String title) {
        if (mTitleView != null) {
            mTitleView.setText(title);
        }
    }

    protected void setBackIcon() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(v -> back());
    }

    protected void back() {
        finish();
    }

    public void hideSoftInputMethod() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void showSoftInputMethod(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.showSoftInput(view, 0);
        }
    }


    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        isBackground = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isBackground = false;
    }

    protected void beforeSetView(@Nullable Bundle savedInstanceState) {

    }

    protected void afterSetView(@Nullable Bundle savedInstanceState) {

        mToolbar = findViewById(R.id.toolbar);
        mTitleView = findViewById(R.id.title);
        mCoordinatorContainer = findViewById(R.id.view_coordinator_container);
    }

    public boolean isProgressShow() {
        return progressShow;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (isProgressShow() && mProgressLoading != null) {
            dismissProgressLoading();
            mProgressLoading = null;
        }
    }


    @Override
    public void showProgressLoading(int resId) {
        if (!isBackground)
            showProgressLoading(getString(resId));
    }

    @Override
    public void showProgressLoading(String message) {
        if (isBackground)
            return;
        mProgressLoading = DialogHelp.getWaitDialog(mContext, message);
        mProgressLoading.show();
//        progressBarView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
//
//        if (mProgressLoading == null) {
//            mProgressLoading = new AlertDialog.Builder(mContext).create();
//            mProgressLoading.setView(progressBarView);
//            mProgressLoading.setCanceledOnTouchOutside(false);
//            mProgressLoading.setOnCancelListener(dialog -> progressShow = false);
//        }
//        if (!TextUtils.isEmpty(message)) {
//            loadText.setText(message);
//        } else {
//            loadText.setText(null);
//        }
//        progressShow = true;
//        mProgressLoading.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//        mProgressLoading.show();


        // to show loading
//        WindowManager m = getWindowManager();
//        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
//        android.view.WindowManager.LayoutParams p = mProgressLoading.getWindow().getAttributes();  //获取对话框当前的参数值
//        p.width = 480;
//        mProgressLoading.getWindow().setAttributes(p);     //设置生效
    }

    @Override
    public void dismissProgressLoading() {
        if (mProgressLoading != null) {
            mProgressLoading.dismiss();
        }
    }

    public void showLongSnackMessage(String message) {
        if (mCoordinatorContainer != null) {
            mSnackbar = Snackbar.make(mCoordinatorContainer, message, Snackbar.LENGTH_INDEFINITE);
            mSnackbar.setAction("确定", v -> {
                mSnackbar.dismiss();
            }).show();
        }
    }

    public void showSnackMessage(String message) {
        if (mCoordinatorContainer != null)
            Snackbar.make(mCoordinatorContainer, message, Snackbar.LENGTH_SHORT)
                    .show();
    }

    public void hideSnackMsg() {
        if (mSnackbar != null) {
            mSnackbar.dismiss();
        }
    }


    @Override
    public void showHint(String message) {
        showSnackMessage(message);
    }

}
