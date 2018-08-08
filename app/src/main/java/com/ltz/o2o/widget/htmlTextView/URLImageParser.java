package com.ltz.o2o.widget.htmlTextView;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ltz.o2o.app.App;
import com.ltz.o2o.imageloader.GlideImageLoader;
import com.youth.banner.loader.ImageLoader;

import java.net.URL;


/**
 * Created by 1 on 2018/7/26.
 */
public class URLImageParser implements Html.ImageGetter {
    TextView mTextView;

    public URLImageParser(TextView textView) {
        this.mTextView = textView;
    }

    @Override
    public Drawable getDrawable(String source) {
        final URLDrawable urlDrawable = new URLDrawable();
//        Log.d("ChapterActivity", Consts.BASE_URL + source);
//        ImageLoader.getInstance().loadImage(Consts.BASE_URL + source, new SimpleImageLoadingListener() {
//            @Override
//            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//                urlDrawable.bitmap = loadedImage;
//                urlDrawable.setBounds(0, 0, loadedImage.getWidth(), loadedImage.getHeight());
//                mTextView.invalidate();
//                mTextView.setText(mTextView.getText()); // 解决图文重叠
//            }
//        });
        Drawable drawable = null;
        URL url = null;
        try {
            url = new URL(source);
            drawable = Drawable.createFromStream(url.openStream(), "img");
        } catch (Exception e) {
            return null;
        }
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable
                .getIntrinsicHeight());
        return drawable;
       // return urlDrawable;
    }
}
