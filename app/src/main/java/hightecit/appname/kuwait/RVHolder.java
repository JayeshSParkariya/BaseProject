package hightecit.appname.kuwait;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by HTISPL on 8/28/2017.
 */

public abstract class RVHolder<T> extends RecyclerView.ViewHolder {

    public RVHolder(View itemView) {
        super(itemView);
    }

    public abstract void setData(T data);
}
