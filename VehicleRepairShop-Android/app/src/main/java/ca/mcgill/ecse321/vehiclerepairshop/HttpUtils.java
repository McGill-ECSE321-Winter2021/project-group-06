package ca.mcgill.ecse321.vehiclerepairshop;

import android.content.Context;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.HttpEntity;

public class HttpUtils {
    public static final String DEFAULT_BASE_URL = "https://vehiclerepairshop-backend-g06.herokuapp.com/";

    private static String baseUrl;
    private static AsyncHttpClient client = new AsyncHttpClient();

    static {
        baseUrl = DEFAULT_BASE_URL;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void setBaseUrl(String baseUrl) {
        HttpUtils.baseUrl = baseUrl;
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void put(String url,  AsyncHttpResponseHandler responseHandler) {
        client.put(getAbsoluteUrl(url), responseHandler);
    }

//    public static void put(Context context, String url, HttpEntity entity, String contentType, String token, AsyncHttpResponseHandler responseHandler) {
//        if (token != null) {
//            String authHeader = "Bearer " + token;
//            client.addHeader("Authorization", authHeader);
//        }
//        client.put(context, baseUrl + url, entity, contentType, responseHandler);
//    }

    public static void getByUrl(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }

    public static void postByUrl(String url, AsyncHttpResponseHandler responseHandler) {
        client.post(url, responseHandler);
    }

    public static void putByUrl(String url, AsyncHttpResponseHandler responseHandler) {
        client.put(url, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return baseUrl + relativeUrl;
    }
}
