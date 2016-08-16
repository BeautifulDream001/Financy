package com.zbar.lib.first;

import android.content.Context;

public class Guide {
	public static final String SHAREDPREFERENCES_NAME= "SHAREDPREFERENCES_NAME";
	private static final  String GUIDE_ACTIVITY ="GUIDE_ACTIVITY";
	public static boolean activityIsGuide(Context context,String className)
	{
		if(context==null||className==null||"".equalsIgnoreCase(className))
		return false;
		String []classNames=context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_WORLD_READABLE).getString(GUIDE_ACTIVITY, "").split("\\|");
		for(String string:classNames)
		{
			if(className.equalsIgnoreCase(string))
			{
				return true;
			}
		}
		return false;
	}
	public static void setIsGuided(Context context,String className)
	{
		if(context==null||className==null||"".equalsIgnoreCase(className))
			return ;
		String classNames=context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_WORLD_READABLE).getString(GUIDE_ACTIVITY, "");
		StringBuilder sb=new StringBuilder(classNames).append("|").append(className);
		context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_WORLD_READABLE)
		.edit()
		.putString(GUIDE_ACTIVITY, sb.toString())
		.commit();
	}
}
