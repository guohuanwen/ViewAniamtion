package com.bcgtgjyb.test.mylibrary;

import android.util.Log;
import android.view.View;

import com.nineoldandroids.animation.Animator;

/**
 * Created by Administrator on 2015/8/17.
 */
public class AnimationCirculateListener implements Animator.AnimatorListener{



    private View v;
    private boolean mCanceled;
        private String TAG="AnimationCirculateListener";
        public void onAnimationStart(Animator animation) {
            Log.i(TAG, "onAnimationStart ");
            mCanceled = false;

        }
        @Override
        public void onAnimationRepeat(Animator animation) {
            Log.i(TAG, "onAnimationRepeat ");
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            Log.i(TAG, "onAnimationEnd ");
            if (!mCanceled) {
                animation.start();
            }

        }

        @Override
        public void onAnimationCancel(Animator animation) {
            Log.i(TAG, "onAnimationCancel ");
            mCanceled = true;
        }

}
