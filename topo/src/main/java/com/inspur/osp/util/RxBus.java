package com.inspur.osp.util;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by liuchen_ on 2018/1/13.
 */

public class RxBus {

    private static volatile RxBus mInstance;
    private PublishSubject<Object> bus = PublishSubject.create();

    /**
     * PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
     * Subject同时充当了Observer和Observable的角色，Subject是非线程安全的，要避免该问题，
     * 需要将 Subject转换为一个 SerializedSubject ，上述RxBus类中把线程非安全的PublishSubject包装成线程安全的Subject。
     */
    private RxBus() {

    }

    /**
     * 单例 双重锁
     *
     * @return
     */
    public static RxBus getInstance() {
        if (mInstance == null) {
            synchronized (RxBus.class) {
                if (mInstance == null) {
                    mInstance = new RxBus();
                }
            }
        }
        return mInstance;
    }

    /**
     * 发送一个新的事件
     *
     * @param o
     */
    public void send(Object o) {
        bus.onNext(o);
    }

    /**
     * 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
     *
     * @param type
     * @param <T>
     * @return
     */

    public Observable<Object> toObservable() {
        return bus;
    }
}
