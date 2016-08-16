package com.zbar.lib.result;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpThread extends Thread {
	String httpurl;
	String result;
	public HttpThread(String httpurl,String result)
	{
		this.httpurl=httpurl;
		this.result=result;
	}
	public void doPost()
	{
		
		 URL url=null;
		 try {
			url=new URL(httpurl);
			HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("POST");
			urlConnection.setReadTimeout(5000);
			OutputStream os=urlConnection.getOutputStream();
			String conent="result="+result;
			os.write(conent.getBytes());
//			BufferedReader reader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//			StringBuffer sb=new StringBuffer();
//			String string;
//			while((string=reader.readLine())!=null)
//			{
//				sb.append(string);
//			}
//			System.out.println("result="+sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void run() {
		doPost();
	}
	
}
