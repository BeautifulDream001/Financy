package com.zbar.lib.result;

import com.zbar.lib.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Result extends Activity {
	private TextView textView;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.result);
		textView=(TextView) findViewById(R.id.tv_result);
		 Intent intent = getIntent();
		 String result=intent.getStringExtra("result");
		 textView.setText(result);
		 
		 
	}
}
