package hightecit.appname.kuwait;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.body.FilePart;
import com.koushikdutta.async.http.body.Part;
import com.koushikdutta.async.http.body.StringPart;
import com.koushikdutta.async.util.Charsets;
import com.koushikdutta.ion.Ion;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by HTISPL on 8/2/2017.
 * New Updated by Jayesh S. Parkariya
 * 1 => Now all requests are asynchronously
 * 2 => All request can be cancelled
 * 3 => ION support added
 * <p>
 * Note: Authentication Header are not implement yet
 * <p>Gradle compile 'com.koushikdutta.ion:ion:2.+' is required to use this library</p>
 */

public class PHPService {

    private static final boolean ENABLE_LOG = true;
    private static final String AUTH_HEADERS_KEY = "not implement yet";

    /**
     * @doGet() method used to calls web services it must be called in
     * doInBackground it is return the JSON string.This method send the
     * request through the GET method.
     */
    public static Builder doGet(Context mContext) {
        return new Builder(RequestMethods.GET, mContext);
    }

    /**
     * @doPost method used to calls web services it must be called in
     * doInBackground it is return the JSON string. This method send the
     * request through the POST method.
     */
    public static Builder doPost(Context mContext) {
        return new Builder(RequestMethods.POST, mContext);
    }

    public static class Builder {

        private RequestMethods requestMethods;
        private Context mContext;
        private String url;
        private Uri.Builder requestParam = new Uri.Builder();
        private ArrayList<Part> partArrayList = new ArrayList<>();

        public Builder(RequestMethods requestMethods, Context mContext) {
            this.requestMethods = requestMethods;
            this.mContext = mContext;
        }

        public Builder load(String url) {
            this.url = url;
            return this;
        }

        public Builder requestParameter(String key, String value) {
            this.requestParam.appendQueryParameter(key, value);
            this.partArrayList.add(new StringPart(key, TextUtils.isEmpty(value) ? "" : value));
            return this;
        }

        public Builder requestParameter(String key, File file) {
            if (file == null) {
                return this;
            }
            this.requestParam.appendQueryParameter(key, file.getAbsolutePath());
            this.partArrayList.add(new FilePart(key, file));
            return this;
        }

        private String getRequestParam() {
            return TextUtils.isEmpty(this.requestParam.build().getQuery()) ? "" : this.requestParam.build().getQuery();
        }

        public void OnApiResponse(final OnApiResponse<String> apiResponse) {
            String reqParam = getRequestParam();
            switch (requestMethods) {
                case GET:
                    String requestUrl = url;
                    requestUrl += TextUtils.isEmpty(reqParam) ? "" : "?" + reqParam;
                    printLogs(requestUrl, reqParam, requestMethods.name());
                    Ion.with(mContext).load(requestUrl).asString(Charsets.UTF_8).setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            printResponse(result);
                            apiResponse.onApiResponse(e, result);
                        }
                    });
                    break;
                case POST:
                    printLogs(url, reqParam, requestMethods.name());
                    Part[] parts = new Part[partArrayList.size()];
                    parts = partArrayList.toArray(parts);
                    Ion.with(mContext).load(url).addMultipartParts(parts).asString(Charsets.UTF_8).setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            printResponse(result);
                            apiResponse.onApiResponse(e, result);
                        }
                    });
                    break;
            }
        }
    }

    // This method generate the log of request parameters
    private static void printLogs(String webUrl, String reqParam, String method) {
        if (ENABLE_LOG) {
            char[] chars = reqParam.toCharArray();
            String reqParameter = "";
            for (char aChar : chars) {
                if ('&' == aChar) {
                    reqParameter += "\n";
                } else {
                    reqParameter += aChar;
                }
            }
            Log.e("Web URl ==>", String.valueOf(webUrl));
            Log.e("Method ==>", String.valueOf(method));
            Log.e("Request Parameters ==>>", String.valueOf(reqParameter));
        }
    }

    private static void printResponse(String response) {
        if (ENABLE_LOG) {
            Log.e("Response ==>", String.valueOf(response));
        }
    }

    public static void printMsg(String msg) {
        if (ENABLE_LOG) {
            Log.e("Msg ==>", String.valueOf(msg));
        }
    }

    public static void printMsg(Object msg) {
        if (ENABLE_LOG) {
            Log.e("Msg ==>", String.valueOf(msg));
        }
    }

    public static void printMsg(String tag, String msg) {
        if (ENABLE_LOG) {
            Log.e(String.valueOf(tag), String.valueOf(msg));
        }
    }

    public static void printMsg(String tag, Object msg) {
        if (ENABLE_LOG) {
            Log.e(String.valueOf(tag), String.valueOf(msg));
        }
    }

    public static void cancel(Context mContext) {
        Ion.getDefault(mContext).cancelAll(mContext);
    }

    public static void cancelAll(Context mContext) {
        Ion.getDefault(mContext).cancelAll();
    }

    public interface OnApiResponse<T> {
        void onApiResponse(Exception e, T response);
    }

    public enum RequestMethods {
        POST, GET
    }
}
