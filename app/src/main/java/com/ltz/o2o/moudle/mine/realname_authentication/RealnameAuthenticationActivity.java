package com.ltz.o2o.moudle.mine.realname_authentication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ltz.o2o.R;
import com.ltz.o2o.base.RxBaseActivity;
import com.ltz.o2o.core.Constants;
import com.ltz.o2o.utils.FileUtil;
import com.ltz.o2o.utils.LodingDialogUtil;
import com.ltz.o2o.utils.ToastUtil;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.api.widget.Widget;

import net.nashlegend.anypref.AnyPref;

import java.io.File;
import java.util.ArrayList;
import butterknife.Bind;
import butterknife.OnClick;
/**
 * 我的-实名认证
 */
public class RealnameAuthenticationActivity extends RxBaseActivity implements RealNameInteractor.IRealNameView{

    private static final int RESUST_CODE = 1;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;

    @Bind(R.id.img_touxiang)
    ImageView img_touxiang;
    @Bind(R.id.img_guohui)
    ImageView img_guohui;

    @Bind(R.id.img_add1)
    ImageView img_add1;
    @Bind(R.id.img_add2)
    ImageView img_add2;

    @Bind(R.id.edt_realname)
    EditText edt_realname;
    @Bind(R.id.edt_no)
    EditText edt_no;

    private RealNamePresenter mPresenter;

    private String pic1_path = "",pic2_path = "";

    @OnClick({R.id.img_add1,R.id.img_touxiang, R.id.img_add2,R.id.img_guohui,R.id.tv_submit})
    public void OnBtnClick(View v) {
        if (v.getId() == R.id.img_add1 || v.getId() == R.id.img_touxiang) {
            openCamera(0);
        } else if (v.getId() == R.id.img_add2 || v.getId() == R.id.img_guohui) {
            openCamera(1);
        }else if(v.getId() == R.id.tv_submit){
            if(TextUtils.isEmpty(edt_realname.getText().toString().trim())){
                ToastUtil.ShortToast("请输入真实姓名");
                return;
            }
            if(TextUtils.isEmpty(edt_no.getText().toString().trim())){
                ToastUtil.ShortToast("请输入身份证号");
                return;
            }
            LodingDialogUtil.showLoding(RealnameAuthenticationActivity.this);
            mPresenter.submitRealName(AnyPref.getDefault().getString(Constants.key_uSessionId,""),AnyPref.getDefault().getString(Constants.key_userId,""),"2",
                    edt_realname.getText().toString(), edt_no.getText().toString(),pic2_path,pic1_path,"","");
        }
    }

    private void openCamera(int code) {
        Album.image(this)
                .multipleChoice()
                .widget(Widget.newDarkBuilder(this).title("图片")
                        .statusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
                        .toolBarColor(ContextCompat.getColor(this, R.color.colorPrimary))
                        .navigationBarColor(ContextCompat.getColor(this, R.color.colorPrimaryBlack))
                        .mediaItemCheckSelector(Color.LTGRAY, Color.RED) // 图片或者视频选择框的选择器。
                        .bucketItemCheckSelector(Color.WHITE, Color.RED) // 切换文件夹时文件夹选择框的选择器。
                        .build())
                .requestCode(RESUST_CODE)
                .camera(true)
                .columnCount(4)
                .selectCount(1)
                .onResult(new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(int requestCode, @NonNull ArrayList<AlbumFile> result) {
                        String path = result.get(0).getThumbPath();
                        if(code == 0){
                            pic1_path = FileUtil.fileToBase64(new File(path));
                            img_add1.setVisibility(View.GONE);
                            img_touxiang.setVisibility(View.VISIBLE);
                            Glide.with(RealnameAuthenticationActivity.this)
                                    .load(path)
                                    .centerCrop()
                                    .crossFade()
                                    .into(img_touxiang);
                        }else{
                            pic2_path = FileUtil.fileToBase64(new File(path));
                            img_add2.setVisibility(View.GONE);
                            img_guohui.setVisibility(View.VISIBLE);
                            Glide.with(RealnameAuthenticationActivity.this)
                                    .load(path)
                                    .centerCrop()
                                    .crossFade()
                                    .into(img_guohui);
                        }

                    }
                })
                .onCancel(new Action<String>() {
                    @Override
                    public void onAction(int requestCode, @NonNull String result) {
                    }
                })
                .start();
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_realname_authentication;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mPresenter = new RealNamePresenter(this);
    }

    @Override
    public void Success(String msg) {
        ToastUtil.ShortToast(msg);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LodingDialogUtil.dissdialog();
                finish();
            }
        },1 * 1000);
    }

    @Override
    public void Fild(String msg) {
        ToastUtil.ShortToast(msg);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LodingDialogUtil.dissdialog();
            }
        },1 * 1000);
    }

    @Override
    public void initToolBar() {
        mToolbar.setTitle("");
        toolbar_title.setText("实名认证");
        mToolbar.setNavigationIcon(R.drawable.ic_back_white);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolbar.setPopupTheme(R.style.ToolBarPopupThemeDay);
    }


}
