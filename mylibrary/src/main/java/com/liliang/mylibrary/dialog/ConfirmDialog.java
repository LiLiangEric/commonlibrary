package com.liliang.mylibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.liliang.mylibrary.R;

import androidx.annotation.NonNull;

import com.liliang.mylibrary.inter.OnNegativeClickedListener;
import com.liliang.mylibrary.inter.OnPostiveClickedListener;
import com.liliang.mylibrary.utils.ResourceUtils;


/**
 * @author LiLiang
 * @date 2018/7/14
 */
public class ConfirmDialog extends Dialog {
    public ConfirmDialog(@NonNull Context context) {
        super(context);
    }

    public ConfirmDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder implements View.OnClickListener {
        private Context mContext;
        private ConfirmDialog mDialog;
        private String mTitle, mNegativeText, mPostiveText, mDesc;
        private OnPostiveClickedListener mListener;
        private OnNegativeClickedListener mNegativeListener;
        private boolean mCanCancel = true;
        private boolean mIsReverse = false;

        public Builder(Context context) {
            this.mContext = context;
        }

        public ConfirmDialog.Builder setTitle(String title) {
            this.mTitle = title;
            return this;
        }

        public ConfirmDialog.Builder setDesc(String desc) {
            this.mDesc = desc;
            return this;
        }


        public ConfirmDialog.Builder setPostiveButton(String postiveText, OnPostiveClickedListener listener) {
            this.mPostiveText = postiveText;
            this.mListener = listener;
            return this;
        }

        public ConfirmDialog.Builder setPostiveButton(String postiveText) {
            this.mPostiveText = postiveText;
            return this;
        }

        public ConfirmDialog.Builder setNegativeButton(String negativeText) {
            this.mNegativeText = negativeText;
            return this;
        }

        public ConfirmDialog.Builder setNegativeButton(String negativeText, OnNegativeClickedListener listener) {
            this.mNegativeText = negativeText;
            this.mNegativeListener = listener;
            return this;
        }

        public ConfirmDialog.Builder setCanCancel(boolean canCancel) {
            this.mCanCancel = canCancel;
            return this;
        }

        public ConfirmDialog.Builder setReverse(boolean isReverse) {
            this.mIsReverse = isReverse;
            return this;
        }


        public ConfirmDialog create() {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.dialog_confirm, null);
            TextView mTvTitle = layout.findViewById(R.id.tv_title);
            TextView mTvNegative = layout.findViewById(R.id.tv_negative);
            TextView mTvPositive = layout.findViewById(R.id.tv_positive);
            TextView mTvDesc = layout.findViewById(R.id.tv_desc);

            mTvTitle.setText(mTitle);
            mTvPositive.setText(mPostiveText);
            mTvNegative.setText(mNegativeText);
            if (TextUtils.isEmpty(mDesc)) {
                mTvDesc.setVisibility(View.GONE);
            } else {
                mTvDesc.setVisibility(View.VISIBLE);
                mTvDesc.setText(mDesc);
            }
            if (mIsReverse) {
                mTvPositive.setTextColor(Color.parseColor("#424242"));
                mTvNegative.setTextColor(ResourceUtils.getColor(R.color.theme));
            }

            mTvPositive.setOnClickListener(this);
            mTvNegative.setOnClickListener(this);

            mDialog = new ConfirmDialog(mContext, R.style.dialog_style);
            mDialog.setCancelable(mCanCancel);
            mDialog.setCanceledOnTouchOutside(mCanCancel);
            mDialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return mDialog;
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.tv_positive) {
                if (mListener != null) {
                    mListener.onPostiveClicked();
                }
            } else if (view.getId() == R.id.tv_negative) {
                if (mNegativeListener != null) {
                    mNegativeListener.onNegativeClicked();
                }
            }
            mDialog.dismiss();
        }
    }
}
