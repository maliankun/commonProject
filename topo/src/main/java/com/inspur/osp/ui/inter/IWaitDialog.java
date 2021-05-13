package com.inspur.osp.ui.inter;

/**
 * 项目名称：OnesgoWMS
 * 类描述：
 * 创建人：lch
 * 创建时间：2017/4/12 0012 10:01
 * 修改人：lch
 * 修改时间：2017/4/12 0012 10:01
 * 修改备注：
 */
public interface IWaitDialog {

    void showProgressLoading(int resId);

    void showProgressLoading(String message);

    void dismissProgressLoading();

    void showHint(String message);

}
