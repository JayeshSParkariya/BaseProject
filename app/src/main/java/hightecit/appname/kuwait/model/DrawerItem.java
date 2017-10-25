package hightecit.appname.kuwait.model;

import hightecit.appname.kuwait.enums.DrawerEnum;

/**
 * Created by HTISPL on 24-Dec-16.
 */

public class DrawerItem {

    private String name;
    private int imgRes;
    private DrawerEnum drawerEnum;

    public DrawerItem(String name, int image, DrawerEnum drawerEnum) {
        this.name = name;
        this.imgRes = image;
        this.drawerEnum = drawerEnum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public DrawerEnum getDrawerEnum() {
        return drawerEnum;
    }

    public void setDrawerEnum(DrawerEnum drawerEnum) {
        this.drawerEnum = drawerEnum;
    }
}