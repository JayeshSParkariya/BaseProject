package hightecit.appname.kuwait.model;

import android.support.annotation.StringRes;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import hightecit.appname.kuwait.MyApplication;
import hightecit.appname.kuwait.TruncatableModel;


/**
 * Created by HTISPL on 5/12/2017.
 */
@Table(name = "LABEL")
public class Label extends TruncatableModel implements Serializable {

    @Column(name = "label_Id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    @SerializedName("id")
    private long labelId;
    @Column
    @SerializedName("label_value")
    private String label;

    public long getLabelId() {
        return labelId;
    }

    public Label setLabelId(long labelId) {
        this.labelId = labelId;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public Label setLabel(String label) {
        this.label = label;
        return this;
    }

    public static String getLabel(String labelId) {
        try {
            Label label = new Select().from(Label.class).where("label_Id=?", labelId).executeSingle();
            return label != null ? label.getLabel() : labelId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return labelId;
    }

    public static String getLabel(@StringRes int res) {
        String labelId = MyApplication.getInstance().getString(res);
        try {
            Label label = new Select().from(Label.class).where("label_Id=?", labelId).executeSingle();
            return label != null ? label.getLabel() : labelId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return labelId;
    }

    public static List<Label> getAllLabels() {
        return new Select().from(Label.class).execute();
    }

    public static int getCount() {
        return new Select().from(Label.class).count();
    }
}
