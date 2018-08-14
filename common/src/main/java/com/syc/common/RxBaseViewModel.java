package com.syc.common;

import com.syc.common.network.BaseResponse;
import com.syc.framework.framework.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shiyucheng on 2018/8/14.
 */
public class RxBaseViewModel extends BaseViewModel {
    private List<Disposable> requests = new ArrayList<>();

    protected <T> Disposable subscribe(Observable<BaseResponse<T>> observable, final RxCallBack<T> callBack) {
        Disposable disposable = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxConsumer<T>(callBack), new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callBack.onError(throwable.getMessage());
                    }
                });
        requests.add(disposable);
        return disposable;
    }

    @Override
    public void onDestroy() {
        for (Disposable item : requests) {
            if (!item.isDisposed()) {
                item.dispose();
            }
        }
        super.onDestroy();
    }

    public interface RxCallBack<T> {
        void onFinish(T result);

        void onError(String msg);
    }

    public class RxConsumer<T> implements Consumer<BaseResponse<T>> {
        private RxCallBack callBack;

        public RxConsumer(RxCallBack callBack) {
            this.callBack = callBack;
        }

        @Override
        public void accept(BaseResponse br) throws Exception {
            if (br.getCode() == 200) {
                callBack.onFinish((T) br.getData());
            } else {
                callBack.onError(br.getErrorMsg());
            }
        }
    }

}
