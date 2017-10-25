package hightecit.appname.kuwait;

import android.databinding.BaseObservable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by HTISPL on 6/28/2017.
 */

public class Validator extends BaseObservable {

    public boolean isEmpty(@Nullable String str) {
        return str == null || str.length() == 0;
    }

    public boolean minLength(@NonNull String str, int minLength) {
        return str.length() >= minLength;
    }

    public boolean maxLength(@NonNull String str, int maxLength) {
        return str.length() <= maxLength;
    }

    public boolean isBetween(@NonNull String str, int minLength, int maxLength) {
        return str.length() >= minLength || str.length() <= maxLength;
    }

    public boolean isEmpty(Class aClass) {
        Field[] fieldsSuper = super.getClass().getFields();
        for (Field f : aClass.getDeclaredFields()) {
            try {
                f.setAccessible(true);
                boolean skip = false;
                for (Field field : fieldsSuper) {
                    skip = f.getName().equalsIgnoreCase(field.getName());
                    if (skip) break;
                }
                if (skip) continue;
                if (f.get(this) != null && f.get(this) instanceof List) {
                    if (!((List) f.get(this)).isEmpty()) {
                        return false;
                    }
                } else if (f.get(this) != null && !TextUtils.isEmpty(f.get(this).toString())) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

}
