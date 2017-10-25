package hightecit.appname.kuwait;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import hightecit.appname.kuwait.model.Label;

/**
 * Created by HTISPL on 5/29/2017.
 */

public class Biding {

    @BindingAdapter("bind:imgRes")
    public static void setImage(ImageView view, @DrawableRes int res) {
        Glide.with(view).load(res).into(view);
    }

    @BindingAdapter({"bind:imageUrl", "bind:pro"})
    public static void loadImage(final ImageView view, String imageUrl, final ProgressBar mProgressBar) {
        mProgressBar.setVisibility(View.VISIBLE);
        Glide.with(view).load(imageUrl).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                /*view.setImageResource(R.drawable.defaultimg);*/
                mProgressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                mProgressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(view);
    }

    @BindingAdapter({"bind:label"})
    public static void loadLabel(TextView view, String labelId) {
        view.setText(Label.getLabel(labelId));
    }

    @BindingAdapter({"bind:label", "bind:value"})
    public static void loadLabel(TextView view, String labelId, String value) {
        view.setText(String.format("%s %s", Label.getLabel(labelId), value));
    }

    @BindingAdapter({"bind:label"})
    public static void loadLabel(EditText view, String labelId) {
        view.setHint(Label.getLabel(labelId));
    }

    @BindingAdapter({"bind:label"})
    public static void loadLabel(Button view, String labelId) {
        view.setText(Label.getLabel(labelId));
    }

    @BindingAdapter({"bind:setAmount"})
    public static void setAmount(TextView view, double amount) {
        view.setText(Utils.getFormattedAmount(view.getContext(), amount));
    }
}
