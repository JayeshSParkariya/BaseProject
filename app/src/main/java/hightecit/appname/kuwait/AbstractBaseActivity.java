package hightecit.appname.kuwait;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import hightecit.appname.kuwait.databinding.DialogConfirmationBinding;
import hightecit.appname.kuwait.databinding.DialogSimpleBinding;
import hightecit.appname.kuwait.interfaces.OnConfirmationDialog;
import hightecit.appname.kuwait.interfaces.OnDialog;
import hightecit.appname.kuwait.interfaces.OnKeyboardVisibilityListener;

/**
 * Created by HTISPL on 7/17/2017.
 */

public abstract class AbstractBaseActivity extends AppCompatActivity {

    private Dialog mDialog;
    private static Class<?> redirectClass;
    private static final String CONNECTIVITY_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";
    private InternetReceiver internetReceiver;

    /**
     * <!-- Add this line application theme-->
     * <item name="android:windowContentTransitions" tools:targetApi="lollipop">true</item>
     * <!-- Add this lines to main view and details view->
     * <android:transitionName="@string/transition_string"
     * tools:targetApi="lollipop">
     ***/
    public void startActivity(View viewStart, String transactionName, Intent intent) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, viewStart, transactionName);
        ActivityCompat.startActivity(this, intent, options.toBundle());
    }

    @Override
    protected void onResume() {
        super.onResume();
        /**RegisterInternetReceiver */
        if (internetReceiver == null) {
            internetReceiver = new InternetReceiver();
            registerReceiver(internetReceiver, new IntentFilter(CONNECTIVITY_CHANGE));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        /**UnregisterInternetReceiver */
        if (internetReceiver != null) {
            unregisterReceiver(internetReceiver);
            internetReceiver = null;
        }
    }

    public static Class<?> getRedirectClass() {
        return redirectClass;
    }

    public static void setRedirectClass(Class<?> redirectClass) {
        AbstractBaseActivity.redirectClass = redirectClass;
    }


    /**
     * Simple dialog to user to show some information
     *
     * @param msg      Message of going to show user along with some kind of information.
     * @param onDialog Is Ok button click listener it can'be null
     */
    public void showSimpleDialog(final String msg, final OnDialog onDialog) {

        final Dialog dialog = new Dialog(this);//, R.style.customDialog
        DialogSimpleBinding dialogSimpleBinding = DialogSimpleBinding.inflate(getLayoutInflater());
        dialogSimpleBinding.setMsg(msg);
        dialogSimpleBinding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (onDialog != null) {
                    onDialog.onOk();
                }
            }
        });
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(dialogSimpleBinding.getRoot());
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        dialog.show();
        WindowManager.LayoutParams windowManager = getWindow().getAttributes();
        windowManager.dimAmount = 0.2f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    /**
     * Confirmation dialog is used to take conformation from the user.
     *
     * @param msg                Message of going to show user along with some kind of information.
     * @param confirmationDialog Is OnConfirmation interface that contain main two method positive
     *                           button click event and negative button click event to handle user
     *                           conformation. Its can'be null.
     */
    public void showConfirmationDialog(final String msg, final OnConfirmationDialog confirmationDialog) {

        final Dialog dialog = new Dialog(this);//, R.style.customDialog
        DialogConfirmationBinding dialogConfirmationBinding = DialogConfirmationBinding.inflate(getLayoutInflater());
        dialogConfirmationBinding.setMsg(msg);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(dialogConfirmationBinding.getRoot());
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        dialogConfirmationBinding.btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (confirmationDialog != null)
                    confirmationDialog.onNo();

            }
        });
        dialogConfirmationBinding.btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (confirmationDialog != null)
                    confirmationDialog.onYes();
            }
        });
        dialog.show();
        WindowManager.LayoutParams windowManager = getWindow().getAttributes();
        windowManager.dimAmount = 0.2f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    public void showOnlyProgressDialog() {
        try {
            if (mDialog != null && mDialog.isShowing()) {
                return;
            }
            mDialog = new Dialog(this);
            mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mDialog.setCancelable(false);
            mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            mDialog.setContentView(R.layout.custom_only_progress_dialog);
            mDialog.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
            mDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }// custom

    /**
     * This common dialog is call the web services success full call then
     * progressDialog is close
     */
    public void dismissDialog() {
        try {
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method allow us to know status of the Keyboard of the system weather its open or close
     *
     * @param listener Is OnKeyboardVisibilityListener interface in which onVisibilityChanged(boolean visible) method
     *                 which is fire while System Keyboard is open or close. visible is true when System Keyboard is open
     *                 or its false while System Keyboard is close.
     */
    public void setKeyboardListener(final OnKeyboardVisibilityListener listener) {
        final View activityRootView = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);

        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            private boolean wasOpened;

            private final int DefaultKeyboardDP = 100;

            // From @nathanielwolf answer...  Lollipop includes button bar in the root. Add height of button bar (48dp) to maxDiff
            private final int EstimatedKeyboardDP = DefaultKeyboardDP + (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? 48 : 0);

            private final Rect r = new Rect();

            @Override
            public void onGlobalLayout() {
                // Convert the dp to pixels.
                int estimatedKeyboardHeight = (int) TypedValue
                        .applyDimension(TypedValue.COMPLEX_UNIT_DIP, EstimatedKeyboardDP, activityRootView.getResources().getDisplayMetrics());

                // Conclude whether the keyboard is shown or not.
                activityRootView.getWindowVisibleDisplayFrame(r);
                int heightDiff = activityRootView.getRootView().getHeight() - (r.bottom - r.top);
                boolean isShown = heightDiff >= estimatedKeyboardHeight;

                if (isShown == wasOpened) {
//                    Log.d("Keyboard state", "Ignoring global layout change...");
                    return;
                }
                wasOpened = isShown;
                if (listener != null)
                    listener.onVisibilityChanged(isShown);
            }
        });
    }

    /**
     * The ANDROID_ID value won't change on package uninstall/reinstall, as long as the package name
     * and signing key are the same. Apps can rely on this value to maintain state across reinstalls.
     * <p>
     * If an app was installed on a device running an earlier version of Android, the Android ID remains
     * the same when the device is updated to Android O, unless the app is uninstalled and reinstalled.
     * <p>
     * The Android ID value only changes if the device is factory reset or if the signing key rotates between
     * uninstall and reinstall events.
     * <p>
     * This change is only required for device manufacturers shipping with Google Play services and Advertising ID.
     * Other device manufacturers may provide an alternative resettable ID or continue to provide ANDROID ID.
     */
    @SuppressLint("HardwareIds")
    public static String getAndroidId(Context mContext) {
        return Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * This class responsible to intercept the broadcast of internet connectivity
     */
    public class InternetReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (!Utils.checkConnectivity(context)) {
                if (AbstractBaseActivity.this instanceof NoInternetActivity) return;
                startActivity(new Intent(AbstractBaseActivity.this, NoInternetActivity.class));
            } else if (AbstractBaseActivity.this instanceof NoInternetActivity) {
                finish();
            }
        }
    }
}
