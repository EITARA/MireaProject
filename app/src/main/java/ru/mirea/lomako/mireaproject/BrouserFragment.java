package ru.mirea.lomako.mireaproject;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BrouserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class BrouserFragment extends Fragment {
    private WebView webView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public BrouserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BrouserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BrouserFragment newInstance(String param1, String param2) {
        BrouserFragment fragment = new BrouserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            WebView webView = (WebView) getView().findViewById(R.id.webview);
            // включаем поддержку JavaScript
            webView.getSettings().setJavaScriptEnabled(true);
            // указываем страницу загрузки
            webView.loadUrl("http://www.mirea.ru");
            webView.setWebViewClient(new MyWebViewClient());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_brouser, null);
        // Inflate the layout for this fragment
        webView = (WebView) v.findViewById(R.id.webview);
        // включаем поддержку JavaScript
        WebSettings webSettings = webView.getSettings();
        webView.getSettings().setJavaScriptEnabled(true);
        // указываем страницу загрузки
        webView.loadUrl("https://www.google.com");
        webView.setWebViewClient(new MyWebViewClient());
       // return inflater.inflate(R.layout.fragment_brouser, container, false);

        return v;

    }

    private class MyWebViewClient extends WebViewClient {
        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        // Для старых устройств
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        }

    }