package com.example.xrecyclerview;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoadingMoreFooter extends LinearLayout {

    public final static int STATE_LOADING = 0;
    public final static int STATE_COMPLETE = 1;
    public final static int STATE_NOMORE = 2;
    private TextView mText;
    private AnimationDrawable mAnimationDrawable;
    private Animation mAnimation;
    private ImageView mIvProgress;

    public LoadingMoreFooter(Context context) {
        super(context);
        initView(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public LoadingMoreFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.refresh_footer, this);
        mText = (TextView) findViewById(R.id.msg);
        mIvProgress = (ImageView) findViewById(R.id.iv_progress);
        mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_anim);
        mIvProgress.startAnimation(mAnimation);
//        mAnimationDrawable = (AnimationDrawable) mIvProgress.getDrawable();
//        if (!mAnimationDrawable.isRunning()) {
//            mAnimationDrawable.start();
//        }
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public void setState(int state) {
        switch (state) {
            case STATE_LOADING:
                mIvProgress.startAnimation(mAnimation);
                mIvProgress.setVisibility(View.VISIBLE);
                mText.setText(getContext().getText(R.string.listview_loading));
                this.setVisibility(View.VISIBLE);
                break;
            case STATE_COMPLETE:
                mIvProgress.clearAnimation();
                mText.setText(getContext().getText(R.string.listview_loading));
                this.setVisibility(View.GONE);
                break;
            case STATE_NOMORE:
                mIvProgress.clearAnimation();
                mText.setText(getContext().getText(R.string.nomore_loading));
                mIvProgress.setVisibility(View.GONE);
                this.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void reSet() {
        this.setVisibility(GONE);
    }
}
