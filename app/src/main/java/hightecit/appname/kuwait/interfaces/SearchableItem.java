package hightecit.appname.kuwait.interfaces;

/**
 * Created by HTISPL on 8/9/2017.
 */

public interface SearchableItem<T> {
    public String getName();

    public boolean isSelected();

    public T setSelected(boolean isSelected);
}
