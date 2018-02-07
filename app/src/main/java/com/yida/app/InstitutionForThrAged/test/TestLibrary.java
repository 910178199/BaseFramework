package com.yida.app.InstitutionForThrAged.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.StackingBehavior;
import com.orhanobut.logger.Logger;
import com.yida.app.InstitutionForThrAged.R;
import com.yida.app.InstitutionForThrAged.api.remote.ApiHelper;
import com.yida.app.InstitutionForThrAged.base.BaseObserver;
import com.yida.app.InstitutionForThrAged.base.ObserverOnNextListener;
import com.yida.app.InstitutionForThrAged.base.ProgressObserver;
import com.yida.app.InstitutionForThrAged.base.ui.BaseActivity;
import com.yida.app.InstitutionForThrAged.model.MovieBean;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by think on 2017/2/7.
 */

public class TestLibrary extends BaseActivity implements View.OnClickListener {

    private static final String TAG = TestLibrary.class.getSimpleName();
    private Button basic_dialog;
    private Button displayaicon;
    private Button stackedActionBtn;
    private Button callbacks;
    private Button checkBoxPrompts;
    private Button listDialogs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        basic_dialog = (Button) findViewById(R.id.basic_dialog);
        displayaicon = (Button) findViewById(R.id.DisplayingAnIcon);
        stackedActionBtn = (Button) findViewById(R.id.StackedActionBtn);
        callbacks = (Button) findViewById(R.id.Callbacks);
        checkBoxPrompts = (Button) findViewById(R.id.CheckBoxPrompts);
        listDialogs = (Button) findViewById(R.id.ListDialogs);


        basic_dialog.setOnClickListener(this);
        displayaicon.setOnClickListener(this);
        stackedActionBtn.setOnClickListener(this);
        callbacks.setOnClickListener(this);
        checkBoxPrompts.setOnClickListener(this);
        listDialogs.setOnClickListener(this);

        //原始版
        Observer<MovieBean> observer = new Observer<MovieBean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(MovieBean movieBean) {
                Log.d(TAG, "onNext: " + movieBean.getTitle());
                List<MovieBean.Subjects> list = movieBean.getSubjects();
                for (MovieBean.Subjects sub : list) {
                    Log.d(TAG, "onNext: " + sub.getId() + "," + sub.getYear() + "," + sub.getTitle());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: Over!");
            }
        };
        ApiHelper.getTopMovie(observer, 0, 10);

        //优化版
        ObserverOnNextListener<MovieBean> beanBaseObserver = new ObserverOnNextListener<MovieBean>() {
            @Override
            public void onNext(MovieBean value) {
                Logger.d(TAG, "onNext: " + value.getTitle());
                List<MovieBean.Subjects> list = value.getSubjects();
                for (MovieBean.Subjects sub : list) {
                    Log.d(TAG, "onNext: " + sub.getId() + "," + sub.getYear() + "," + sub.getTitle());
                }
            }
        };
        ApiHelper.getTopMovie(new BaseObserver<MovieBean>(this, beanBaseObserver), 0, 10);

        //终极版
        ObserverOnNextListener<MovieBean> listener = new ObserverOnNextListener<MovieBean>() {
            @Override
            public void onNext(MovieBean movie) {
                Log.d(TAG, "onNext: " + movie.getTitle());
                List<MovieBean.Subjects> list = movie.getSubjects();
                for (MovieBean.Subjects sub : list) {
                    Log.d(TAG, "onNext: " + sub.getId() + "," + sub.getYear() + "," + sub.getTitle());
                }
            }
        };
        ApiHelper.getTopMovie(new ProgressObserver<MovieBean>(this, listener), 0, 10);

        UserLogger();

        TestLibrary
                .clicks(basic_dialog)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<View>() {
                    @Override
                    public void accept(View aVoid) throws Exception {
                        Logger.d("hhhhhhhhhhhhhhhhhhh");
                    }
                });
    }

    public static Observable<View> clicks(@NonNull View view) {
        return Observable.create(new ViewClickOnSubscribe(view));
    }

    @Override
    protected int getLayout() {
        return 0;
    }

    private void UserLogger() {


        Logger.d("mytag", "hello");
        Logger.d("hello");
        Logger.t("mytag").d("hello");
        Logger.e("hello");
        Logger.w("hello");
        Logger.v("hello");
        Logger.wtf("hello");
        Logger.json("{ \"people\": [\n" +
                "\n" +
                "{ \"firstName\": \"Brett\", \"lastName\":\"McLaughlin\", \"email\": \"aaaa\" },\n" +
                "\n" +
                "{ \"firstName\": \"Jason\", \"lastName\":\"Hunter\", \"email\": \"bbbb\"},\n" +
                "\n" +
                "{ \"firstName\": \"Elliotte\", \"lastName\":\"Harold\", \"email\": \"cccc\" }\n" +
                "\n" +
                "]}");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.basic_dialog:
                new MaterialDialog.Builder(this)
                        .title(R.string.title)
                        .content(R.string.content)
                        .positiveText(R.string.agree)
                        .negativeText(R.string.disagree)
                        .show();
                break;
            case R.id.DisplayingAnIcon:
                /**
                 * 显示icon
                 */

                new MaterialDialog.Builder(this).
                        title(R.string.title)
                        .content(R.string.content)
                        .positiveText(R.string.agree)
                        .iconRes(R.mipmap.ic_launcher)
                        .maxIconSize(1000)
                        .show();
                break;
            case R.id.StackedActionBtn:
                /**
                 * 设置按钮位置显示
                 * ALWAYS：上下
                 * ADAPTIVE：默认显示
                 * NEVER：不显示
                 */
                new MaterialDialog.Builder(this)
                        .title(R.string.title)
                        .content(R.string.content)
                        .positiveText(R.string.longer_positive)
                        .negativeText(R.string.negative)
                        .stackingBehavior(StackingBehavior.ADAPTIVE)
                        //中间选择按钮
                        .neutralText(R.string.more_info)
                        .show();
                break;
            case R.id.Callbacks:
                new MaterialDialog.Builder(this)
                        .title(R.string.title)
                        .content(R.string.content)
                        .positiveText(R.string.longer_positive)
                        .negativeText(R.string.negative)
                        .neutralText(R.string.more_info)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                Toast.makeText(getApplicationContext(), "onPositive", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                Toast.makeText(getApplicationContext(), "onNegative", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .onNeutral(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                Toast.makeText(getApplicationContext(), "onNeutral", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .onAny(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                /**
                                 * 任何一个响应
                                 */
                                Toast.makeText(getApplicationContext(), "onAny", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                break;
            case R.id.CheckBoxPrompts:
                new MaterialDialog.Builder(this)
                        .iconRes(R.mipmap.ic_launcher)
                        .limitIconToDefaultSize()
                        .title(R.string.title)
                        .positiveText(R.string.longer_positive)
                        .negativeText(R.string.negative)
                        .onAny(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                Toast.makeText(getApplicationContext(), "Prompt checked? " + dialog.isPromptCheckBoxChecked(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .checkBoxPromptRes(R.string.dont_ask_again, false, null)
                        .show();
                break;
            case R.id.ListDialogs:
                /**
                 * 列表选项
                 */
                /*new MaterialDialog.Builder(this)
                        .title(R.string.title)
                        .iconRes(R.mipmap.ic_launcher)
                        .limitIconToDefaultSize()
                        .positiveText(R.string.longer_positive)
                        .negativeText(R.string.negative)
                        .items(R.array.list_dialogs)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                Toast.makeText(getApplicationContext(),"which:"+which+",text"+text,Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();*/

                /**
                 * 单选列表框
                 */
               /* new MaterialDialog.Builder(this)
                        .title(R.string.title)
                        .items(R.array.list_dialogs)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                *//**
             * If you use alwaysCallSingleChoiceCallback(), which is discussed below,
             * returning false here won't allow the newly selected radio button to actually be selected.
             **//*
                                return true;
                            }
                        })
                        .positiveText(R.string.choose)
                        .show();*/


                /**
                 * 多选列表对话框
                 */

              /*  new MaterialDialog.Builder(this)
                        .title(R.string.title)
                        .items(R.array.list_dialogs)
                        .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                                *//**
             * If you use alwaysCallMultiChoiceCallback(), which is discussed below,
             * returning false here won't allow the newly selected check box to actually be selected
             * (or the newly unselected check box to be unchecked).
             * See the limited multi choice dialog example in the sample project for details.
             **//*
                                return true;
                            }
                        })
                        .positiveText(R.string.choose)
                        .show();*/


                /**
                 * 分配列表id
                 */

               /* new MaterialDialog.Builder(this)
                        .title(R.string.socialNetworks)
                        .items(R.array.socialNetworks)
                        .itemsIds(R.array.itemIds)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                Toast.makeText(Activity.this, which + ": " + text + ", ID = " + view.getId(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();*/

                /**
                 * 自定义list dialog
                 */

             /*   new MaterialDialog.Builder(this)
                        .title(R.string.socialNetworks)
                        // second parameter is an optional layout manager. Must be a LinearLayoutManager or GridLayoutManager.
                        .adapter(new ButtonItemAdapter(this, R.array.socialNetworks), null)
                        .show();*/


                /**
                 * 自定义view
                 */

               /* MaterialDialog dialog = new MaterialDialog.Builder(this)
                        .title(R.string.title)
                        .customView(R.layout.dialog_custom_view, true)
                        .positiveText(R.string.longer_positive)
                        .show();
                View customView = dialog.getCustomView();
                Button button = (Button) customView.findViewById(R.id.dialog_custom_view);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "HELLO", Toast.LENGTH_SHORT).show();
                    }
                });*/


                /**
                 * 获取和设置动作按钮
                 */

               /* MaterialDialog materialDialog = new MaterialDialog.Builder(this)
                        .positiveText(R.string.longer_positive)
                        .neutralText(R.string.neutral)
                        .negativeText(R.string.negative)
                        .show();

                View positive = materialDialog.getActionButton(DialogAction.POSITIVE);
                View negative = materialDialog.getActionButton(DialogAction.NEGATIVE);
                View neutral = materialDialog.getActionButton(DialogAction.NEUTRAL);

                positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

                materialDialog.setActionButton(DialogAction.NEGATIVE, "new NEGATIVE");
*/


                /**
                 * 主题
                 */
                /*new MaterialDialog.Builder(this)
                        .content("Hi")
                        .theme(Theme.LIGHT)
                        .show();*/

                /**
                 * 颜色
                 */
              /*  new MaterialDialog.Builder(this)
                        .title("HI")
                        .content("12312312")
                        .positiveText(R.string.longer_positive)
                        .neutralText(R.string.neutral)
                        .negativeText(R.string.negative)
                        .titleColorRes(R.color.material_red_500)
                        .contentColor(Color.WHITE) // notice no 'res' postfix for literal color
                        .linkColorAttr(R.attr.colorAccent)  // notice attr is used instead of none or res for attribute resolving
                        .dividerColorRes(R.color.colorAccent)
                        .backgroundColorRes(R.color.colorPrimaryDark)
                        .positiveColorRes(R.color.material_red_500)
                        .neutralColorRes(R.color.material_red_500)
                        .negativeColorRes(R.color.material_red_500)
                        .widgetColorRes(R.color.material_red_500)
                        .buttonRippleColorRes(R.color.material_red_500)
                        .show();*/
                break;
        }
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
