package rohitkadam.ownbrowser;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    EditText editTextUrl;
    ProgressDialog progressDialog;
    Button buttonGo,buttonBack,buttonForward,buttonRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser);

        webView=findViewById(R.id.webview2);

        editTextUrl=findViewById(R.id.editTextUrl);
        buttonBack=findViewById(R.id.buttonBack);
        buttonForward=findViewById(R.id.ButtonForward);
        buttonGo=findViewById(R.id.buttonGo);
        buttonRefresh=findViewById(R.id.buttonRefresh);

        progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading the URL");
        webView.loadUrl("https:/www.google.com");
        progressDialog.show();
        //webView.loadUrl("file:///android_asset/login.html");

        /*String strhtml="<html>\n" +
                "<body>\n" +
                "<h1>\n" +
                "    Welcome to my browser . type the url\n" +
                "</h1>\n" +
                "</body>\n" +
                "</html>";

        webView.loadData(strhtml,"text/html",null);*/
       CustomWebViewClient client=new CustomWebViewClient();
       webView.setWebViewClient(client);

        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh();
            }
        });

        buttonForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forward();
            }
        });
    }
    private void go(){
        String url=editTextUrl.getText().toString();
        webView.loadUrl(url);
    }

    private void back(){
        webView.goBack();
    }

    private void forward(){
        webView.goForward();
    }
    private void refresh(){
        webView.reload();
    }

    class CustomWebViewClient extends WebViewClient{

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            if(!progressDialog.isShowing())
                progressDialog.show();
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if(progressDialog.isShowing())
                progressDialog.dismiss();
        }

    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack())
            webView.goBack();
        else
            super.onBackPressed();
    }
}
