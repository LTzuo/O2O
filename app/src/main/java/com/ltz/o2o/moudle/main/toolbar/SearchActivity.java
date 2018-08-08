package com.ltz.o2o.moudle.main.toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.ltz.o2o.R;
import com.ltz.o2o.base.RxBaseActivity;
import com.ltz.o2o.utils.ToastUtil;
import com.ltz.o2o.widget.flowlayout.FlowLayout;
import org.litepal.crud.DataSupport;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.OnClick;
import cn.yhq.dialog.core.DialogBuilder;

/**
 * 搜索  search
 */
public class SearchActivity extends RxBaseActivity {

    @Bind(R.id.mFlowLayout)
    FlowLayout mFlowLayout;
    @Bind(R.id.edt)
    EditText edt;

    //用于存放临时数据
    List<SearchNowDao> mVals = new ArrayList<>();

    @OnClick({R.id.img_back, R.id.tv_search,R.id.img_delete})
    public void OnBtnClick(View v) {
        if (v.getId() == R.id.img_back) {
            finish();
        } else if (v.getId() == R.id.tv_search) {
            if(!TextUtils.isEmpty(edt.getText().toString())){
                SearchNowDao tag = new SearchNowDao();
                tag.setTitle(edt.getText().toString());
                tag.save();
                if (tag.save()) {
                    mVals.add(tag);
                    addTag(tag.getTitle());
                } else {
                    ToastUtil.ShortToast("储存失败");
                }
            }
        } else if(v.getId() == R.id.img_delete){
            showDeleteAllDialog();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        initDataBase();
        mFlowLayout.setOnItemTagClickListener(new FlowLayout.ItemTagClickListener() {
            @Override
            public void click(View v, int i) {
                ToastUtil.ShortToast(mVals.get(i).getTitle());
            }
        });
    }

    //查询表中所有数据
    private void initDataBase() {
        mVals.clear();
        mVals.addAll(DataSupport.findAll(SearchNowDao.class));
        if (!mVals.isEmpty()) {
            for (int i = 0; i < mVals.size(); i++) {
               addTag(mVals.get(i).getTitle());
            }
        }
    }

    private void addTag(String title){
        TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.tv,
                mFlowLayout, false);
        tv.setText(title);
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mFlowLayout.addView(tv);
            }
        });
    }

    public  void showDeleteAllDialog(){
        DialogBuilder.alertDialog(this).setMessage("确定要删除全部历史记录?")
                .setCancelable(true)
                .setOnPositiveButtonClickListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //删除全表数据
                        DataSupport.deleteAll(SearchNowDao.class);
                        SearchActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mVals.clear();
                                mFlowLayout.removeAllViews();
                            }
                        });
                    }
                })
                .create().show();
    }

    @Override
    public void initToolBar() {

    }

}
