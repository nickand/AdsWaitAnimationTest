package com.mynotes.logapps.animationcircletest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private int time = 4;
    private Timer T;
    private CountDownTimer countDownTimer;
    private int millisInFuture = 4000;
    private CircleTextView circleTextView;
    private CircleAngleAnimation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circleTextView = (CircleTextView) findViewById(R.id.circle);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.container_timer_ads);

        // initialize as invisible (could also do in xml)
        slideUp(this, linearLayout);
        countDownTimer = new CountDownTimer(millisInFuture, 800) {
            @Override
            public void onTick(long millisUntilFinished) {
                animation = new CircleAngleAnimation(circleTextView, 360);
                animation.setDuration(millisUntilFinished);
                circleTextView.startAnimation(animation);
                circleTextView.setText(String.valueOf(time));
                time--;
            }

            @Override
            public void onFinish() {
                animation.reset();
                slideDown(getApplicationContext(), linearLayout);
            }
        }.start();
    }

    public static void slideUp(Context context, View view) {
        Animation bottomUp = AnimationUtils.loadAnimation(context, R.anim.bottom_up);
        view.startAnimation(bottomUp);
        view.setVisibility(View.VISIBLE);
    }

    public static void slideDown(final Context context, final View view) {
        Animation bottomDown = AnimationUtils.loadAnimation(context, R.anim.bottom_down);
        view.startAnimation(bottomDown);
        bottomDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
                Intent intent = new Intent(context, SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
