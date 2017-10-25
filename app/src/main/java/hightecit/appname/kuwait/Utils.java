package hightecit.appname.kuwait;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static String amountDigits = "0.000";
    private static DecimalFormat decimalFormat;

    public static String getClientKey(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("clientKey", "");
    }

    public static void setClientKey(Context context, String clientKey) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("clientKey", clientKey).apply();
    }

    public static String getTimeZone(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("timezone", "Asia/Kuwait");
    }


    public static void setUserId(Context context, String userAddressId) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("userId", userAddressId).apply();
    }

    public static String getUserId(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("userId", "");
    }

    public static String getEmail(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("userEmail", "");
    }

    public static void setEmail(Context context, String userId) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("userEmail", userId).apply();
    }

    public static void setUserImage(Context context, String userImage) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("userImage", userImage).apply();
    }

    public static String getUserImage(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("userImage", "");
    }

    public static void setCountryId(Context context, String countryId) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("countryId", countryId).apply();
    }

    public static String getCountryId(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("countryId", "");
    }

    public static void setCountryCode(Context context, String countryCode) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("countryCode", countryCode).apply();
    }

    public static String getCountryCode(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("countryCode", "");
    }

    public static void setUserContactNumber(Context context, String countryCode) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("ContactNumber", countryCode).apply();
    }

    public static String getUserContactNumber(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("ContactNumber", "");
    }

    public static void setIsFirstTime(Context context, boolean isFirstTime) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putBoolean("IsFirstTime", isFirstTime).apply();
    }

    public static boolean getIsFirstTime(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean("IsFirstTime", true);
    }

    public static void setMatchResult(Context context, boolean isFirstTimeResult) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putBoolean("FirstTimeResult", isFirstTimeResult).apply();
    }

    public static boolean getMatchResult(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean("FirstTimeResult", true);
    }

    public static void setOrderId(Context context, String orderId) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("orderId", orderId).apply();
    }

    public static String getOrderId(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("orderId", "");
    }

    public static void setUserAddressId(Context context, String userAddressId) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("userAddressId", userAddressId).apply();
    }

    public static String getUserAddressId(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("userAddressId", "");
    }


    public static void setCountryName(Context context, String countryName) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("countryName", countryName).apply();
    }

    public static String getCountryName(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("countryName", "");
    }

    public static void setCountryFlag(Context context, String countryFlag) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("countryFlag", countryFlag).apply();
    }

    public static String getCountryFlag(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("countryFlag", "");
    }

    public static void setImages(Context context, String images) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("images", images).apply();
    }

    public static String getImages(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("images", "");
    }

    public static void setIsContactUpdated(Context context, boolean isContactUpdated) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putBoolean("isContactUpdated", isContactUpdated).apply();
    }

    public static boolean getIsContactUpdated(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean("isContactUpdated", false);
    }

    public static String getReservationId(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("reservationId", "");
    }

    public static void setReservationId(Context context, String reservationId) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("reservationId", reservationId).apply();
    }

    public static String getCart(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("cart", "");
    }

    public static void setCart(Context context, String cart) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("cart", cart).apply();
    }

    public static String getSelectedCountry(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("selectedCountry", "");
    }

    public static void setSelectedCountry(Context context, String country) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("selectedCountry", country).apply();
    }

    public static String getUserName(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("fullname", "");
    }

    public static void setUserName(Context context, String userName) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("fullname", userName).apply();
    }

    public static String getFirstName(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("firstName", "");
    }

    public static void setFirstName(Context context, String firstName) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("firstName", firstName).apply();
    }

    public static String getLastName(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("lastName", "");
    }

    public static void setLastName(Context context, String lastName) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("lastName", lastName).apply();
    }

    public static float getGovernmentTax(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getFloat("tax", 0);
    }

    public static void setGovernmentTax(Context context, float tax) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putFloat("tax", tax).apply();
    }

   /* public static int getCurrencyDigits(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getInt("currencyDigits", 3);
    }

    public static void setCurrencyDigits(Context context, int currencyDigits) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putInt("currencyDigits", currencyDigits).apply();
    }*/

    public static double getDeliveryCharge(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getFloat("deliveryCharge", 0);
    }

    public static void setDeliveryCharge(Context context, double deliveryCharge) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putFloat("deliveryCharge", (float) deliveryCharge).apply();
    }

    public static String getCurrencySymbol(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("currencySymbol", "KWD");
    }

    public static void setCurrencySymbol(Context context, String currencySymbol) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("currencySymbol", currencySymbol).apply();
    }

    public static String getContactNumber(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("contactNumber", "");
    }

    public static void setMinimumAmount(Context context, String minimumAmount) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("minimumAmount", minimumAmount).apply();
    }

    public static String getMinimumAmount(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("minimumAmount", "0");
    }

    public static void setContactNumber(Context context, String contactNumber) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("contactNumber", contactNumber).apply();
    }

    public static void setPushStatus(Context context, boolean status) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putBoolean("NotificationStatus", status).apply();
    }

    public static boolean getPushStatus(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean("NotificationStatus", false);
    }


    public static void setFirstTime(Context context, boolean status) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putBoolean("isFirstTime", status).apply();
    }

    public static boolean isFirstTime(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean("isFirstTime", false);
    }

    public static void setLanguage(Context context, String language) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("language", language).apply();
    }

    public static String getLanguage(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("language", "");
    }

    public static String getApiLanguage(Context context) {
        if (getLanguage(context).equalsIgnoreCase("ar")) {
            return "arabic";
        } else {
            return "english";
        }
    }

    public static void setLanguageDirection(Context context, String languageDir) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("languageDirection", languageDir).apply();
    }

    public static String getLanguageDirection(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("languageDirection", "ltr");
    }

    public static String getLangageName(Context context) {
        if (getLanguage(context).equalsIgnoreCase("ar")) {
            return "arabic";
        } else {
            return "english";
        }
    }

    public static String getGCMToken(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("GCMToken", "");
    }

    public static void setGCMToken(Context context, String GCMToken) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("GCMToken", GCMToken).apply();
    }

    public static boolean isRegistered(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean("isRegistered", false);
    }

    public static void setRegistered(Context context, boolean isRegistered) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putBoolean("isRegistered", isRegistered).apply();
    }

    public static int getNotificationBaseCount(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getInt("notificationBaseCount", 0);
    }

    public static void setNotificationBaseCount(Context context, int notificationBaseCount) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putInt("notificationBaseCount", notificationBaseCount).apply();
    }

    public static String getLocale(Context mContext) {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = mContext.getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = mContext.getResources().getConfiguration().locale;
        }
        return String.valueOf(locale.getLanguage().charAt(0));
    }


    public static String getCountries(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("countries", "");
    }

    public static void setCountries(Context context, String countries) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("countries", countries).apply();
    }

    public static String getAreas(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("areas", "");
    }

    public static void setAreas(Context context, String areas) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("areas", areas).apply();
    }

    public static String getAddress(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("address", "");
    }

    public static void setAddress(Context context, String address) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("address", address).apply();
    }

    public static String getLocations(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("locations", "");
    }

    public static void setLocations(Context context, String locations) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("locations", locations).apply();
    }

    public static String getCategories(Context context, String categoryId) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(categoryId, "");
    }

    public static void setCategories(Context context, String categoryId, String categories) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString(categoryId, categories).apply();
    }

    public static String getSubMenuItem(Context context, String subCategoryId) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(subCategoryId, "");
    }

    public static void setSubMenuItem(Context context, String subCategoryId, String subMenuItems) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString(subCategoryId, subMenuItems).apply();
    }

    public static String getDeviceFavorite(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("deviceFavorite", "");
    }

    public static void setDeviceFavorite(Context context, String deviceFavorite) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("deviceFavorite", deviceFavorite).apply();
    }

    public static String getUserFavorite(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("userFavorite", "");
    }

    public static void setUserFavorite(Context context, String userFavorite) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("userFavorite", userFavorite).apply();
    }

    public static String getContactUs(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("contactUs", "");
    }

    public static void setContactUs(Context context, String contactUs) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("contactUs", contactUs).apply();
    }

    public static String getSignatureDishes(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("signatureDishes", "");
    }

    public static void setSignatureDishes(Context context, String signatureDishes) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("signatureDishes", signatureDishes).apply();
    }

    public static String getDeviceHistory(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("deviceHistory", "");
    }

    public static void setDeviceHistory(Context context, String deviceHistory) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("deviceHistory", deviceHistory).apply();
    }

    public static String getUserHistory(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("userHistory", "");
    }

    public static void setUserHistory(Context context, String userHistory) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("userHistory", userHistory).apply();
    }

    public static boolean checkConnectivity(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info == null) {
                return false;
            } else if (info.isConnectedOrConnecting()) {
                return true;
            }
        } catch (Exception e) {
            Log.e("Error ", e.toString());
        }
        return false;
    }// End of checkConnectivity

    public static InputFilter[] getTextFilter() {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    int type = Character.getType(source.charAt(i));
                    if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL) {
                        return "";
                    }
                }
                return null;
            }
        };
        return new InputFilter[]{filter};
    }

    /**
     * Hides the soft keyboard
     */
    public static void hideSoftKeyboard(Activity me) {
        if (me.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) me.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(me.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static void openKeyBoard(EditText edt, Activity a) {
        InputMethodManager imm = (InputMethodManager) a.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(edt, InputMethodManager.SHOW_IMPLICIT);
    }

    public static void HideKeyBoard(EditText edt, Activity a) {
        InputMethodManager imm = (InputMethodManager) a.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edt.getWindowToken(), 0);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
        }
    }

    public static String getVersionName(Context context) {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
    }

    public static boolean checkPermission(Context mContext, String permission) {
        return ContextCompat.checkSelfPermission(mContext, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isValidEmail(String email) {
        Pattern p = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static String getB64Auth(String key) {
        String source = "X-MAKI-SecurityToken" + ":" + key;
        return Base64.encodeToString(source.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP);
    }

    public static String getBASE64Encoded(String key) {
        return Base64.encodeToString(key.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP);
    }

    public static void setMyLocale(Context context, String lang) {
        Locale myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    /*public static void setMyLocale(Context context, String lang) {
        Locale myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            context.getResources().getConfiguration().setLocale(myLocale);
        } else {
            context.getResources().getConfiguration().locale = myLocale;
        }
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }*/

    public static String getBase64Encoded(File file) {
        String encodedBase64 = "";
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);
            encodedBase64 = new String(Base64.encode(bytes, Base64.DEFAULT));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodedBase64;
    }

    public static String getBase64Encoded(Bitmap image) {
        //b is the Bitmap
        //calculate how many bytes our image consists of.
        int bytes = image.getByteCount();
        //or we can calculate bytes this way. Use a different value than 4 if you don't use 32bit images.
        //int bytes = b.getWidth()*b.getHeight()*4;
        ByteBuffer buffer = ByteBuffer.allocate(bytes); //Create a new buffer
        image.copyPixelsToBuffer(buffer); //Move the byte data to the buffer
        byte[] array = buffer.array(); //Get the underlying array containing the data.
        return Base64.encodeToString(array, Base64.DEFAULT);
    }

    static String getFormattedAmount(Context context, double amount) {
        if (decimalFormat == null) {
            // NumberFormat format = NumberFormat.getInstance(Locale.US);
            DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.US);
            // decimalFormat = (DecimalFormat) DecimalFormat.getNumberInstance(Locale.US);
            // decimalFormat = (DecimalFormat) format;
            decimalFormat = new DecimalFormat(amountDigits, formatSymbols);
        }
        return decimalFormat.format(amount) + " " + getCurrencySymbol(context);
    }

    public static void updateCurrency() {
        if (decimalFormat != null) {
            decimalFormat = null;
        }
    }

    public static void openInstagram(Context context) {
        try {
            Intent intentInstagram = context.getPackageManager().getLaunchIntentForPackage("com.instagram.android");
            context.startActivity(intentInstagram);

        } catch (Exception e) {
            e.printStackTrace();
            String url = "https://www.instagram.com";
            Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            Intent chooser = Intent.createChooser(intent1, "Select app");
            context.startActivity(chooser);
        }
    }

    public static void openTwitter(Context context) {
        try {
            // get the Twitter app if possible
            Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.twitter.android");
            context.startActivity(intent);
        } catch (Exception e) {
            // no Twitter app, revert to browser
            String url = "https://twitter.com";
            Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            Intent chooser = Intent.createChooser(intent1, "Select app");
            context.startActivity(chooser);
        }
    }

    public static void openGooglePlus(Context context) {
        try {
            Intent intentInstagram = context.getPackageManager().getLaunchIntentForPackage("com.google.android.apps.plus");
            context.startActivity(intentInstagram);

        } catch (Exception e) {
            e.printStackTrace();
            String url = "https://plus.google.com";
            Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            Intent chooser = Intent.createChooser(intent1, "Select app");
            context.startActivity(chooser);
        }
    }

    public static void openFacebook(Context context) {
        try {
            Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.facebook.katana");
            context.startActivity(intent);//Checks if FB is even installed.
        } catch (Exception e) {
            String url = "https://www.facebook.com";
            Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            Intent chooser = Intent.createChooser(intent1, "Select app");
            context.startActivity(chooser);
        }
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    private static final int ANIMATION_DURATION = 300;//in milisecond

    public static void expand(final View v) {

        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targtetHeight = v.getMeasuredHeight();
        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int) (targtetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        // 1dp/ms
        a.setDuration(ANIMATION_DURATION);
        // a.setDuration((int)(targtetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration(ANIMATION_DURATION);
        // a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static Spannable getColoredString(String mString, int colorId) {
        Spannable spannable = new SpannableString(mString);
        spannable.setSpan(new ForegroundColorSpan(colorId), 0, spannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

}