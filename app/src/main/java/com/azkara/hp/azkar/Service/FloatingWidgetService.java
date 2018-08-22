package com.azkara.hp.azkar.Service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.azkara.hp.azkar.R;
import com.azkara.hp.azkar.Storage.SharedPref.SharedPrefManager;
import com.azkara.hp.azkar.Util.Constants;
import com.azkara.hp.azkar.Util.GeneralMethods;
import com.easyandroidanimations.library.Animation;
import com.easyandroidanimations.library.AnimationListener;
import com.easyandroidanimations.library.ExplodeAnimation;
import com.easyandroidanimations.library.SlideInAnimation;

/**
 * Created by NaderNabil216@gmail.com on 7/10/2018.
 */
public class FloatingWidgetService extends Service {


    int mWidth;
    TextView textView;
    private WindowManager mWindowManager;
    private View mOverlayView;
    private String zekr_text;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @SuppressLint({"ClickableViewAccessibility", "InflateParams", "RtlHardcoded"})
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        zekr_text = GeneralMethods.getRundomZekr(this);

        if (!zekr_text.isEmpty()) {


            if (mOverlayView == null) {

                mOverlayView = LayoutInflater.from(this).inflate(R.layout.overlay_layout, null);


                final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.TYPE_PHONE,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                        PixelFormat.TRANSLUCENT);


                //Specify the view position
                params.gravity = Gravity.TOP | Gravity.RIGHT;        //Initially view will be added to top-left corner
                params.x = 0;
                params.y = 100;


                mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
                assert mWindowManager != null;
                mWindowManager.addView(mOverlayView, params);

                Display display = mWindowManager.getDefaultDisplay();
                final Point size = new Point();
                display.getSize(size);

                textView = mOverlayView.findViewById(R.id.txt_overlay);
                textView.setText(zekr_text);

                switch (SharedPrefManager.getInstance().doStuff(this).getThemeColor()) {
                    case Constants.ConstantsValues.LightTheme:
                        textView.setBackgroundResource(R.drawable.light_round_bg);
                        break;
                    case Constants.ConstantsValues.DarkTheme:
                        textView.setBackgroundResource(R.drawable.dark_round_bg);
                        break;
                    case Constants.ConstantsValues.GreenTheme:
                        textView.setBackgroundResource(R.drawable.green_round_bg);
                        break;
                    case Constants.ConstantsValues.PinkTheme:
                        textView.setBackgroundResource(R.drawable.pink_round_shape);
                        break;
                }


                final RelativeLayout layout = mOverlayView.findViewById(R.id.layout);
                ViewTreeObserver vto = layout.getViewTreeObserver();
                vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        int width = layout.getMeasuredWidth();

                        //To get the accurate middle of the screen we subtract the width of the floating widget.
                        mWidth = size.x - width;

                    }
                });

                textView.setOnTouchListener(new View.OnTouchListener() {
                    private int initialX;
                    private int initialY;
                    private float initialTouchX;
                    private float initialTouchY;

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:

                                //remember the initial position.
                                initialX = params.x;
                                initialY = params.y;


                                //get the touch location
                                initialTouchX = event.getRawX();
                                initialTouchY = event.getRawY();


                                return true;
                            case MotionEvent.ACTION_UP:


                                //Logic to auto-position the widget based on where it is positioned currently w.r.t middle of the screen.
                                int middle = mWidth / 2;
                                float nearestXWall = params.x >= middle ? mWidth : 0;
                                params.x = (int) nearestXWall;

                                mWindowManager.updateViewLayout(mOverlayView, params);

                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        exitAnimation();
                                    }
                                });

                                return true;
                            case MotionEvent.ACTION_MOVE:


                                int xDiff = Math.round(event.getRawX() - initialTouchX);
                                int yDiff = Math.round(event.getRawY() - initialTouchY);


                                //Calculate the X and Y coordinates of the view.
                                params.x = initialX - xDiff;  // minus in arabic , and plus in english
                                params.y = initialY + yDiff;

                                //Update the layout with new X & Y coordinates
                                mWindowManager.updateViewLayout(mOverlayView, params);


                                return true;
                        }
                        return false;
                    }
                });

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        entranceAnimation();
                    }
                });

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exitAnimation();
                    }
                }, 50000);
            }

        }

        return super.onStartCommand(intent, flags, startId);


    }

    public void entranceAnimation() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (mOverlayView != null) {
                    new SlideInAnimation(textView)
                            .setDuration(500)
                            .setDirection(Animation.DIRECTION_RIGHT)
                            .animate();
                }
            }
        });

    }

    public void exitAnimation() {
        if (mOverlayView != null) {
            new ExplodeAnimation(textView)
                    .setExplodeMatrix(ExplodeAnimation.MATRIX_2X2)
                    .setInterpolator(new DecelerateInterpolator())
                    .setDuration(500)
                    .setListener(new AnimationListener() {
                        @Override
                        public void onAnimationEnd(com.easyandroidanimations.library.Animation animation) {
                            stopSelf();
                        }
                    })
                    .animate();
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
        setTheme(R.style.AppTheme);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mOverlayView != null)
            mWindowManager.removeView(mOverlayView);
    }

}