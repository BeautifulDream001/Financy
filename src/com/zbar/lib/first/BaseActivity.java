package com.zbar.lib.first;

import com.zbar.lib.R;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class BaseActivity extends Activity {
	private int guideResourceId=0;
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		addGuideImage();
	}
	private void addGuideImage() {
		// TODO Auto-generated method stub
		View view =getWindow().getDecorView().findViewById(R.id.main_layout);
		if(!(view!=null))
		{
			return;
		}
		if(Guide.activityIsGuide(this, this.getClass().getName()))
		{
			return;
			
		}
		ViewParent viewParent=view.getParent();
		if(viewParent instanceof FrameLayout)
		{
			final FrameLayout frameLayout=(FrameLayout) viewParent;
			if(guideResourceId !=0)
			{
				final ImageView guideImage=new ImageView(this);
				FrameLayout.LayoutParams params =new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
				guideImage.setLayoutParams(params);
				guideImage.setScaleType(ScaleType.FIT_XY);
				guideImage.setImageResource(guideResourceId);
				guideImage.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						frameLayout.removeView(guideImage);
						Guide.setIsGuided(getApplicationContext(),BaseActivity.this.getClass().getName());
					}
				});
				frameLayout.addView(guideImage);
			}
			
			
		}
	}
	protected void setGuideResId(int resId)
    {
        this.guideResourceId = resId;
    }
}
