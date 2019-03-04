package cn.hosiang.carzymarx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        web = (WebView) findViewById(R.id.web);
        web.getSettings().setJavaScriptEnabled(true);

        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            web.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            web.getSettings().setUseWideViewPort(true); //将图片调整到适合webview的大小
            web.getSettings().setLoadWithOverviewMode(true); // 缩放至屏幕的大小
            web.getSettings().setLoadsImagesAutomatically(true); //支持自动加载图片
            web.loadUrl("http://www.hosiang.cn");
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && web.canGoBack()) {
            //创建退出对话框
            AlertDialog.Builder isExit = new AlertDialog.Builder(this);
            //设置对话框标题
            isExit.setTitle("消息提醒");
            //设置对话框消息
            isExit.setMessage("确定要退出吗");
            // 添加选择按钮并注册监听
            isExit.setPositiveButton("确定", diaListener);
            isExit.setNegativeButton("取消", diaListener);
            //对话框显示
            isExit.show();

            //web.goBack();
            //return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    //监听对话框的点击事件
    DialogInterface.OnClickListener diaListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int buttonId) {
            // TODO Auto-generated method stub
            switch (buttonId) {
                case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
                    finish();
                    web.goBack();
                    break;
                case AlertDialog.BUTTON_NEGATIVE:// "确认"按钮退出程序
                    //什么都不做
                    break;
                default:
                    break;
            }
        }

    };
}
