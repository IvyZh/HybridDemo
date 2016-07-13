package com.example.ivy.hybriddemo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Person person;
    private Handler handler = new Handler();
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.webView);

        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.addJavascriptInterface(person = new Person(), "person");

        mWebView.setWebChromeClient(new MyWebChromeClient());


        String url = "http://192.168.1.4:8080/demo1.html";
        mWebView.loadUrl(url);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }


    class MyWebChromeClient extends WebChromeClient {
        @Override
        public boolean onConsoleMessage(ConsoleMessage cm) {
            Log.d("main", cm.message());
            return true;
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

            return true;
        }
    }

    public class Person {
        public String name;
        public String age;
        public String phone;


        @JavascriptInterface
        public void setName(String name) {
            this.name = name;

            handler.post(new Runnable() {
                @Override
                public void run() {
                    mWebView.addJavascriptInterface(person, "person");
                    mWebView.loadUrl("http://192.168.1.4:8080/demo2.html");
                }
            });
            Log.d("main", "setName");

        }

        @JavascriptInterface
        public void setAge(String age) {
            this.age = age;
            handler.post(new Runnable() {
                @Override
                public void run() {
//                    mWebView.addJavascriptInterface(person,"person");
                    mWebView.loadUrl("http://192.168.1.4:8080/demo3.html");
                }
            });

            Log.d("main", "setAge");
        }


        @JavascriptInterface
        public void setPhone(String phone) {
            this.phone = phone;
            handler.post(new Runnable() {
                @Override
                public void run() {
//                  mWebView.addJavascriptInterface(person,"person");
                    mWebView.loadUrl("http://192.168.1.4:8080/demoresult.html");
                }
            });

            Log.d("main", "setPhone");
        }

        @JavascriptInterface
        public void getResult() {
            Log.d("main", "getResult");

            try {

                JSONArray array = new JSONArray();
                JSONObject personObj = new JSONObject();
                personObj.put("name", name);
                personObj.put("age", age);
                personObj.put("phone", phone);

                array.put(personObj);
                final String result = array.toString();

                Log.d("main", result);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mWebView.loadUrl("javascript:show('" + result + "')");
                    }
                });


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}
