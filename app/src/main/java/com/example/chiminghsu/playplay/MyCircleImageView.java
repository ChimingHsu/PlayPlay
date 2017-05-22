package com.example.chiminghsu.playplay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import android.util.Log;
import android.widget.ImageView;

/**
 * Created by Chiming Hsu on 2017/5/6.
 */

public class MyCircleImageView extends android.support.v7.widget.AppCompatImageView {
    private Drawable drawable;
    private int iw_wide = 0;
    private int iw_height = 0;

    public MyCircleImageView(Context context) {
        super(context);
    }

    public MyCircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //自訂view元件一定要override的方法
    @Override
    protected void onDraw(Canvas canvas) {
        drawable = getDrawable();
        if (drawable == null) {
            Log.d("!!!!","drawable == null");
            return;
        }

        //先取得MyCircleImageView的長寬
        if (getWidth() == 0 || getHeight() == 0) {
            Log.d("!!!!","getWidth() == 0 || getHeight() == 0");
            return;
        }
        iw_wide = getWidth();
        iw_height = getHeight();

        //確認圖片為點陣圖避免轉型失敗
        //Android 支援的檔案類型有 .png (preferred), .jpg (acceptable), .gif (discouraged).
        if (!(drawable instanceof BitmapDrawable)) {
            Log.d("!!!!","!(drawable instanceof BitmapDrawable)");
            return;
        }

        Bitmap orgBmp = ((BitmapDrawable) drawable).getBitmap();
        if (null == orgBmp) {
            Log.d("!!!!","null == orgBmp");
            return;
        }

        //設定色彩廣度複製一份原圖
        Bitmap copyBmp = orgBmp.copy(Bitmap.Config.ARGB_8888, true);

        Bitmap roundBitmap = getCroppedBitmap(copyBmp, iw_wide);
        canvas.drawBitmap(roundBitmap, 0, 0, null);
    }

    /*
    *originalBmp 原始圖
    * radius 圓形的直徑
    * */
    public static Bitmap getCroppedBitmap(Bitmap originalBmp, int radius) {
        Bitmap scaledBmp;
        //將原圖缩放裁剪成正方形
        if (originalBmp.getWidth() != radius || originalBmp.getHeight() != radius)
            scaledBmp = Bitmap.createScaledBitmap(originalBmp, radius, radius, false);
        else
            scaledBmp = originalBmp;

        Bitmap output = Bitmap.createBitmap(scaledBmp.getWidth(), scaledBmp.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, scaledBmp.getWidth(), scaledBmp.getHeight());

        //去除鋸齒
        paint.setAntiAlias(true);

        paint.setFilterBitmap(true);
        paint.setDither(true);

        //畫布的顏色
        canvas.drawARGB(0, 0, 0, 0);

        //設定畫筆的顏色並畫圓
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(scaledBmp.getWidth() / 2 + 0.7f,
                scaledBmp.getHeight() / 2 + 0.7f, scaledBmp.getWidth() / 2 + 0.1f, paint);

        //選擇將兩張圖疊在一起所呈現的效果
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(scaledBmp, rect, rect, paint);

        return output;
    }
}
