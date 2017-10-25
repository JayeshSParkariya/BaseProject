package hightecit.appname.kuwait.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import hightecit.appname.kuwait.BaseActivity;
import hightecit.appname.kuwait.RVHolder;
import hightecit.appname.kuwait.databinding.RowSearchableDialogListItemBinding;
import hightecit.appname.kuwait.enums.SearchableType;
import hightecit.appname.kuwait.interfaces.OnSelectedChanged;
import hightecit.appname.kuwait.interfaces.SearchableItem;

/**
 * Created by HTISPL on 8/9/2017.
 */

public class SearchableAdapters extends RecyclerView.Adapter<RVHolder<SearchableItem>> {

    private BaseActivity activity;
    private ArrayList<Object> objectArrayList;
    private OnSelectedChanged onSelectedChanged;
    private SearchableType type;
    private int lastSelected = -1;

    public SearchableAdapters(BaseActivity activity, ArrayList<Object> objectArrayList, SearchableType type) {
        this.activity = activity;
        this.objectArrayList = objectArrayList;
        this.type = type;
        if (type == SearchableType.DialogSingleSelection) {
            for (int i = 0; i < objectArrayList.size(); i++) {
                if (((SearchableItem) objectArrayList.get(i)).isSelected()) {
                    lastSelected = i;
                    break;
                }
            }
        }
    }

    @Override
    public RVHolder<SearchableItem> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderDialog(RowSearchableDialogListItemBinding.inflate(activity.getLayoutInflater(), parent, false));
    }

    @Override
    public void onBindViewHolder(RVHolder<SearchableItem> holder, int position) {
        holder.setData((SearchableItem) objectArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return objectArrayList.size();
    }

    public ArrayList<Object> getObjectArrayList() {
        return objectArrayList;
    }

    public SearchableAdapters setObjectArrayList(ArrayList<Object> objectArrayList) {
        this.objectArrayList = objectArrayList;
        return this;
    }

    private class ViewHolderDialog extends RVHolder<SearchableItem> {
        RowSearchableDialogListItemBinding itemBinding;

        ViewHolderDialog(final RowSearchableDialogListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
            this.itemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (type) {
                        case DialogSingleSelection:
                            if (lastSelected != -1) {
                                ((SearchableItem) objectArrayList.get(lastSelected)).setSelected(false);
                                notifyItemChanged(lastSelected);
                            }
                            lastSelected = getAdapterPosition();
                        default:
                            itemBinding.getItem().setSelected(!itemBinding.getItem().isSelected());
                            notifyItemChanged(getAdapterPosition());
                            if (onSelectedChanged != null) {
                                onSelectedChanged.onSelectedChanged();
                            }
                    }
                }
            });
        }

        @Override
        public void setData(SearchableItem data) {
            itemBinding.setItem(data);
            itemBinding.executePendingBindings();
        }
    }

    public SearchableAdapters setOnSelectedChanged(OnSelectedChanged onSelectedChanged) {
        this.onSelectedChanged = onSelectedChanged;
        return this;
    }
}
