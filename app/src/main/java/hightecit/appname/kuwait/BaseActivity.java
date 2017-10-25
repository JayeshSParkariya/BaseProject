package hightecit.appname.kuwait;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import hightecit.appname.kuwait.adapters.SearchableAdapters;
import hightecit.appname.kuwait.databinding.ActivityBaseBinding;
import hightecit.appname.kuwait.databinding.DialogSearchableBinding;
import hightecit.appname.kuwait.enums.Events;
import hightecit.appname.kuwait.enums.SearchableType;
import hightecit.appname.kuwait.interfaces.OnConfirmationDialog;
import hightecit.appname.kuwait.interfaces.OnDialog;
import hightecit.appname.kuwait.interfaces.OnSearchableDialog;
import hightecit.appname.kuwait.interfaces.OnSelectedChanged;
import hightecit.appname.kuwait.interfaces.SearchableItem;
import hightecit.appname.kuwait.model.DrawerItem;
import hightecit.appname.kuwait.model.Label;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by ANDROID-10 on 05-01-2017.
 */

public class BaseActivity extends AbstractBaseActivity implements View.OnClickListener {

    public BaseActivity me;
    public ActivityBaseBinding baseBinding;
    private List<DrawerItem> drawerItemArrayList = new ArrayList<>();
    private DrawerRecyclerAdapter adapter;
    public FragmentManager fragmentManager;
    public String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**Initialize mint*/
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(null/*getString(R.string.regular_font)*/)
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        Utils.setMyLocale(this, Utils.getLanguageDirection(this).equalsIgnoreCase("ltr") ? "en" : "ar");
        baseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base);
        init();
    }

    /**
     * Initialization of object members
     */
    private void init() {

        /**Initialize me object*/
        me = this;

        url = "android.resource://" + getPackageName() + "/";

        /**Setup window setting*/
        setToolBar(baseBinding.mToolbar);

        /**Setup drawer**/
        /*setDrawerData();*/

        /**Initialize of fragmentManager*/
        fragmentManager = getSupportFragmentManager();

        baseBinding.mSearchView.setHint(Label.getLabel(getString(R.string.search)));

        if (getRedirectClass() == null)
            setRedirectClass(HomeActivity.class);

        EventBus.getDefault().register(this);
    }

    protected void setToolBar(Toolbar mToolBar) {
        try {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            getWindow().setWindowAnimations(0);
            setSupportActionBar(mToolBar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            /*getSupportActionBar().setHomeAsUpIndicator(MyApplication.isLTR ? R.drawable.ic_back_arrow : R.drawable.ic_back_arrow_rtl);*/
            baseBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Subscribe
    public void onEvent(Events event) {
        switch (event) {
            case onRefreshSideMenu:
                setDrawerData();
                break;
        }
    }

    @Subscribe
    public void onFinish(Class<?> aClass) {
        if (aClass == this.getClass()) {
            finish();
        }
    }

    /**
     * Setup toolbar and navigation drawer
     */
    protected void setNavigationView() {
        /*setDrawerData();*/
        DrawerLayout drawer = baseBinding.drawerLayout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, baseBinding.mToolbar, 0, 0);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        /*getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_user);*/
        baseBinding.drawerLayout.setScrimColor(ContextCompat.getColor(me, android.R.color.transparent));
        baseBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    protected void setDrawerData() {
        drawerItemArrayList.clear();
        if (adapter == null) {
            adapter = new DrawerRecyclerAdapter();
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
            baseBinding.drawerRecycler.setLayoutManager(mLayoutManager);
            baseBinding.drawerRecycler.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    public Gson getGsonInstance() {
        return new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC).create();
    }

    /**
     * This method automatically load the label
     **/
    public void showErrorDialog(@StringRes int msg, @NonNull final EditText editText) {
        /*editText.clearFocus();*/
        showSimpleDialog(Label.getLabel(getString(msg)), new OnDialog() {
            @Override
            public void onOk() {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
                editText.setSelection(editText.length());
                editText.requestFocus();
            }
        });
    }

    /**
     * This method automatically load the label
     **/
    public void showErrorDialog(@StringRes int msg, @NonNull final View fireOnClick) {
        showSimpleDialog(Label.getLabel(getString(msg)), new OnDialog() {
            @Override
            public void onOk() {
                fireOnClick.performClick();
            }
        });
    }

    /**
     * This method automatically load the label
     **/
    public void showSimpleDialog(@StringRes int res, OnDialog... onDialogs) {
        showSimpleDialog(Label.getLabel(getString(res)), onDialogs.length > 0 ? onDialogs[0] : null);
    }

    /**
     * This method automatically load the label
     **/
    public void showConfirmationDialog(@StringRes int msg, OnConfirmationDialog... onConfirmationDialogs) {
        showConfirmationDialog(Label.getLabel(getString(msg)), onConfirmationDialogs.length > 0 ? onConfirmationDialogs[0] : null);
    }

    /**
     * This method show list of items
     **/
    public void showSearchableDialog(String title, final ArrayList<Object> arrayList, @Nullable final OnSearchableDialog onSearchableDialog, SearchableType... type) {
        final DialogSearchableBinding searchableBinding = DialogSearchableBinding.inflate(getLayoutInflater());
        searchableBinding.setTitle(title);
        /*searchableBinding.ivDone.setVisibility(getSelectedItems(arrayList).isEmpty() ? View.INVISIBLE : View.VISIBLE);*/
        final BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(searchableBinding.getRoot());
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        searchableBinding.ivCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        searchableBinding.ivDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (onSearchableDialog != null)
                    onSearchableDialog.onItemSelected(getSelectedNames(arrayList));
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (searchableBinding.ivDone.getVisibility() != View.VISIBLE) {
                    setSelectedItems(arrayList, false);
                }
            }
        });
        OnSelectedChanged onSelectedChanged = new OnSelectedChanged() {
            @Override
            public void onSelectedChanged() {
                /*searchableBinding.ivDone.setVisibility(getSelectedItems(arrayList).isEmpty() ? View.INVISIBLE : View.VISIBLE);*/
            }
        };
        searchableBinding.rvSearchable.setAdapter(new SearchableAdapters(me, arrayList, type.length > 0 ? type[0] : SearchableType.Dialog).setOnSelectedChanged(onSelectedChanged));
        dialog.show();
    }

    protected String getSelectedNames(ArrayList<Object> arrayList) {
        String selectedItems = "";
        for (Object item : arrayList) {
            if (((SearchableItem) item).isSelected()) {
                if (selectedItems.isEmpty()) {
                    selectedItems = ((SearchableItem) item).getName();
                } else {
                    selectedItems += ", " + ((SearchableItem) item).getName();
                }
            }
        }
        return selectedItems;
    }

    protected void setSelectedItems(ArrayList<Object> arrayList, boolean isSelected) {
        for (Object item : arrayList) {
            if (((SearchableItem) item).isSelected()) {
                ((SearchableItem) item).setSelected(isSelected);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void closeDrawer() {
        baseBinding.drawerLayout.closeDrawers();
    }

    private class DrawerRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private static final int TYPE_HEADER = 0;
        private static final int TYPE_ITEM = 1;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        }

        @Override
        public int getItemCount() {
            return drawerItemArrayList.size() + 1;
        }

        @Override
        public int getItemViewType(int position) {
            if (isPositionHeader(position))
                return TYPE_HEADER;
            return TYPE_ITEM;
        }

        private boolean isPositionHeader(int position) {
            return position == 0;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }

        class ViewHolderHeader extends RecyclerView.ViewHolder {
            public ViewHolderHeader(View itemView) {
                super(itemView);
            }
        }
    }

    public void setTitle(String title) {
        baseBinding.toolbarTitle.setText(title);
    }

    public void setTitle(@StringRes int title) {
        baseBinding.toolbarTitle.setText(title);
    }

    public void setFullScreen() {
        try {
            getSupportActionBar().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }

    @Override
    public void onBackPressed() {
        if (baseBinding.mSearchView.isSearchOpen()) {
            baseBinding.mSearchView.closeSearch();
            return;
        }
        Utils.hideSoftKeyboard(me);
        super.onBackPressed();
    }
}