package com.zbar.lib;

import java.util.ArrayList;
import java.util.List;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.zbar.lib.first.BaseActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends BaseActivity implements OnClickListener {
	
	private ViewPager mViewPager;
	private PagerAdapter mPagerAdapter;
	private List<View> views=new ArrayList<View>();
	//四个layout
	private LinearLayout mUpLayout;
	private LinearLayout mDownLayout;
	private LinearLayout mLeftLayout;
	private LinearLayout mRightLayout;
	//四张图片
	private ImageButton mUpImg;
	private ImageButton mDownImg;
	private ImageButton mLeftImg;
	private ImageButton mRightImg;
	
	private ImageButton mScannerImageButton;
	private Button mButton2Code;
	private ImageView codeImg;
	private EditText tabEditText;
	public WebView tabWebView;
	public View tab01;
	public View tab02;
	public View tab03;
	public View tab04;
	@SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        initEvents();
		setGuideResId(R.drawable.app_logo);
		
    }
    private void initEvents() {
		mUpImg.setOnClickListener(this);
		mDownImg.setOnClickListener(this);
		mLeftImg.setOnClickListener(this);
		mRightImg.setOnClickListener(this);
		mScannerImageButton.setOnClickListener(this);
		mButton2Code.setOnClickListener(this);
		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			public void onPageSelected(int arg0) {
				choose();
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
    /**
     * 初始化点击按钮的图片的颜色
     */
	private void resetImg() {
		mUpImg.setImageResource(R.drawable.ic_launcher_1);
		mDownImg.setImageResource(R.drawable.ic_launcher_3);
		mLeftImg.setImageResource(R.drawable.ic_launcher_5);
		mRightImg.setImageResource(R.drawable.ic_launcher_7);
		
		
	}
	/**
	 * 对于界面的初始化
	 */
	private void initView() {
		
		
		mViewPager=(ViewPager) findViewById(R.id.view_pager);
		mUpLayout=(LinearLayout) findViewById(R.id.weixin_up);
		mDownLayout=(LinearLayout) findViewById(R.id.weixin_down);
		mLeftLayout=(LinearLayout) findViewById(R.id.weixin_left);
		mDownLayout=(LinearLayout) findViewById(R.id.weixin_right);
		
		mUpImg=(ImageButton) findViewById(R.id.weixin_up_img);
		mDownImg=(ImageButton) findViewById(R.id.weixin_down_img);
		mLeftImg=(ImageButton) findViewById(R.id.weixin_left_img);
		mRightImg=(ImageButton) findViewById(R.id.weixin_right_img);
		
		mScannerImageButton=(ImageButton) findViewById(R.id.btn_scanner);
		
		LayoutInflater mLayoutInflater=LayoutInflater.from(this);
		tab01=mLayoutInflater.inflate(R.layout.tab01, null);
		tab02=mLayoutInflater.inflate(R.layout.tab02, null);
		tab03=mLayoutInflater.inflate(R.layout.tab03, null);
		tab04=mLayoutInflater.inflate(R.layout.tab04, null);
		
		
		mButton2Code=(Button)tab01.findViewById(R.id.btn_TwoCode);
		codeImg=(ImageView)tab01.findViewById(R.id.img);
		tabEditText=(EditText) tab01.findViewById(R.id.et_code_text);
		
		
		views.add(tab01);
		views.add(tab02);
		views.add(tab03);
		views.add(tab04);
		
	        initWebView();
		mPagerAdapter=new PagerAdapter() {

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView(views.get(position));
				
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				View view=views.get(position);
				container.addView(view);
				return view;
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0==arg1;
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return views.size();
			}
		};
		mViewPager.setAdapter(mPagerAdapter);
		
	}

	private void initWebView() {
		// TODO Auto-generated method stub
		tabWebView=(WebView)tab02.findViewById(R.id.webView);
	       
        tabWebView.setWebViewClient(new WebViewClient(){
        	@Override
        	public boolean shouldOverrideUrlLoading(WebView view, String url) {
        		view.loadUrl(url);
        		return true;
        	}
        	
        }); 
        tabWebView.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if(keyCode==KeyEvent.KEYCODE_BACK&&tabWebView.canGoBack())
				{
					tabWebView.goBack();
					return true;
				}
				return false;
			}
		});
        WebSettings mWebSettings=tabWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setUseWideViewPort(true); 
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setSupportZoom(true); // 支持缩放  
        mWebSettings.setLoadWithOverviewMode(true);  
        mWebSettings.setDomStorageEnabled(true);
        tabWebView.addJavascriptInterface(getHtmlObj(), "js");
        tabWebView.loadUrl("http://132.147.10.111:8080/androidWeb/jsp/login.jsp");
	}
	public Object getHtmlObj() {
		Object object=new Object(){
		@ android.webkit.JavascriptInterface
			public void HtmlCallAndroid()
			{
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						tabWebView.loadUrl("javascript:fun()");
						Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_LONG).show();
					}
				});
				
			}
		@android.webkit.JavascriptInterface
		public void HtmlCallAndroidFail()
		{
			runOnUiThread(new Runnable( ) {
				
				@Override
				public void run() {
					tabWebView.loadUrl("javascript:fail()");
					Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
				}
			});
			
		}
			
		};
		return object;
	}
	/**
	 * 点击事件导致按钮颜色变化
	 */
	@Override
	public void onClick(View v) {
		resetImg();
		switch (v.getId()) {
		case R.id.weixin_up_img:
			mViewPager.setCurrentItem(0);
			mUpImg.setImageResource(R.drawable.ic_launcher_2);
			break;
		case R.id.weixin_down_img:
			mViewPager.setCurrentItem(1);
			mDownImg.setImageResource(R.drawable.ic_launcher_4);
			break;
		case R.id.weixin_left_img:
			mViewPager.setCurrentItem(2);
			mLeftImg.setImageResource(R.drawable.ic_launcher_6);
			break;
		case R.id.weixin_right_img:
			mViewPager.setCurrentItem(3);
			mRightImg.setImageResource(R.drawable.ic_launcher_8);
			break;
		case R.id.btn_scanner:
			statrScanner();
			choose();
			break;
		case R.id.btn_TwoCode:
			String str = tabEditText.getText().toString().trim();
            Bitmap bmp = null;
            try {
                if (str != null && !"".equals(str)) {
                    bmp = CreateTwoDCode(str);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (bmp != null) {
                codeImg.setImageBitmap(bmp);
            };
            choose();
			break;
		}
		
	}
	private Bitmap CreateTwoDCode(String content) throws WriterException {
		 // 生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
        BitMatrix matrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, 500, 500);
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        // 二维矩阵转为一维像素数组,也就是一直横着排了
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = 0xff00FF99;
                }
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        // 通过像素数组生成bitmap,具体参考api
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
	}
	private void statrScanner() {
		Intent intent=new Intent();
		intent.setAction("android.intent.action.scanner");
		startActivity(intent);
		
	}
	private void choose()
	{
		int currentItem=mViewPager.getCurrentItem();
		resetImg();
		switch (currentItem) {
		case 0:
			mUpImg.setImageResource(R.drawable.ic_launcher_2);
			break;
		case 1:
			mDownImg.setImageResource(R.drawable.ic_launcher_4);
			break;
		case 2:
			mLeftImg.setImageResource(R.drawable.ic_launcher_6);				
			break;
		case 3:
			mRightImg.setImageResource(R.drawable.ic_launcher_8);
			break;
		default:
			break;
		}
		
	}
 

protected void onStop() {
	// TODO Auto-generated method stub
	if(tabWebView!=null){
		tabWebView.destroy();
		tabWebView.clearHistory();
		tabWebView.clearCache(true);
		tabWebView.loadUrl("about:blank");
		tabWebView.freeMemory(); 
		tabWebView.pauseTimers();
		tabWebView = null;
		System.out.println("aaaaa");
		System.out.println("bbb");
	}
}


}
