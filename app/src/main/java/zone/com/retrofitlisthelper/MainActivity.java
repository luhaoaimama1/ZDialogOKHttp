package zone.com.retrofitlisthelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ezy.ui.layout.LoadingLayout;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import zone.com.retrofitlib.callwrapper.DialogCall;
import zone.com.retrofitlib.utils.HandlerUiUtil;
import zone.com.retrofitlib.views.LoadingDialog;
import zone.com.retrofitlib.views.LoadingPopWindow;
import zone.com.sdk.API.gank.api.GankImpl;
import zone.com.sdk.API.gank.bean.MeiZiData;
import zone.com.sdk.API.gank2.api.Gank2Impl;
import zone.com.retrofitlisthelper.utils.GsonUtils;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.ll_root)
    LinearLayout llRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.bt_rxjavaSync)
    public void onRxjavaSyncClick() {
        new Gank2Impl().getPics("5", "2",2)
                .popWindow(new LoadingPopWindow(this))
                .delayDismiss(5000)
                .enqueueObservable()
                .subscribe(o -> System.out.println("Sync 妹子==>：" + GsonUtils.toJson(o))
                        , throwable -> System.out.println("Sync 异常==>" + throwable)
                        , () -> System.out.println("Sync 成功==>"));
    }

    @OnClick(R.id.bt_rxjava)
    public void onRxjavaClick() {
        new Gank2Impl().getPics( "5", "2",2)
                .executeObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> System.out.println("同步 妹子==>：" + GsonUtils.toJson(o))
                        , throwable -> System.out.println("同步  异常==>" + throwable)
                        , () -> System.out.println("同步  成功==>"));
    }

    @OnClick(R.id.bt_pop)
    public void onPopClick() {
        new GankImpl()
                .getPics("5", "5")
                .popWindow(new LoadingPopWindow(this))
                .delayDismiss(5000)
                .enqueue(new Callback<MeiZiData>() {
                    @Override
                    public void onResponse(Call<MeiZiData> call, Response<MeiZiData> response) {
                        //UI线程
                        System.out.println("pop==>onResponse");
                    }

                    @Override
                    public void onFailure(Call<MeiZiData> call, Throwable t) {
                        System.out.println("pop==>onFailure");
                    }
                });
    }

    @OnClick(R.id.bt_Dlg)
    public void onDlgClick() {
        new GankImpl()
                .getPics("5", "5")
                .dialog(new LoadingDialog(this))
                .enqueue(new Callback<MeiZiData>() {
                    @Override
                    public void onResponse(Call<MeiZiData> call, Response<MeiZiData> response) {
                        //UI线程
                        System.out.println("dialog==>onResponse");
                    }

                    @Override
                    public void onFailure(Call<MeiZiData> call, Throwable t) {
                        System.out.println("dialog==>onFailure");
                    }
                });
    }

    @OnClick(R.id.bt_DelayDialog)
    public void onDelayDialogClick() {
        final LoadingDialog dialog = new LoadingDialog(this);
        new GankImpl()
                .getPics("5", "5")
                .OnLoadingListener(new DialogCall.OnLoadingListener() {
                    @Override
                    public void onLoading(DialogCall.State state) {
                        switch (state) {
                            case Loading:
                                dialog.show();
                                break;
                            case Success:
                            case Error:
                                HandlerUiUtil.postDelay(new Runnable() {
                                    @Override
                                    public void run() {
                                        dialog.dismiss();
                                    }
                                }, 2000);
                                break;
                        }
                    }
                })
                .enqueue(new Callback<MeiZiData>() {
                    @Override
                    public void onResponse(Call<MeiZiData> call, Response<MeiZiData> response) {

                    }

                    @Override
                    public void onFailure(Call<MeiZiData> call, Throwable t) {

                    }
                });
    }

    @OnClick(R.id.bt_FristLoading)
    public void onFristLoadingClick() {
        new GankImpl()
                .getPics("5", "5")
                .firstLoading(LoadingLayout.wrap(llRoot))
                .enqueue(new Callback<MeiZiData>() {
                    @Override
                    public void onResponse(Call<MeiZiData> call, Response<MeiZiData> response) {
                        System.out.println("onFristLoadingClick==>onResponse");
                    }

                    @Override
                    public void onFailure(Call<MeiZiData> call, Throwable t) {
                        System.out.println("onFristLoadingClick==>onFailure");
                    }
                });
    }

    @OnClick(R.id.bt_List)
    public void onListClick() {
        startActivity(new Intent(this, ListActivity.class));

    }
}
