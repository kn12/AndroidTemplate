package qinyn.android.template.ui.activity;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import qinyn.android.net.http.DemoHttp;
import qinyn.android.template.R;
import qinyn.android.template.bean.User;
import qinyn.android.template.ui.baseview.BaseFragmentActivity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class SSLActivity extends BaseFragmentActivity {

	private static final String TAG = "AsyncDemo";
	private static int BUFFSIZE = 8192;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_jason;
	}

	@Override
	protected void onContentView() {
		String url = "www.baidu.com";
		int type = 1;
//		doDemo(type, url, OriginalContext.getContext());
		findViewById(R.id.btn1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				test();
			}
		});;
		
	}

	private void test() {
		  //将TableData放入List中  
		Log.i("", "---------"+System.currentTimeMillis());;
	}

	private static String parseString(HttpEntity entity) {
		try {
			ByteArrayOutputStream ostr = new ByteArrayOutputStream();
			InputStream istr = entity.getContent();
			byte[] buf = new byte[BUFFSIZE];
			int count = -1;
			while ((count = istr.read(buf, 0, BUFFSIZE)) != -1) {
				ostr.write(buf, 0, count);
			}
			buf = null;
			return new String(ostr.toByteArray());
		} catch (Throwable e) {
			e.printStackTrace();
			Log.e(TAG, e.getMessage());
			return "";
		}
	}

	public static String doDemo(int type, String url, Context context) {
		HttpUriRequest request = null;
		try {
			request = new HttpGet(url);
		} catch (Throwable e) {
			Log.e(TAG, e.getMessage());
		}
		HttpClient client;
		switch (type) {
		case 1:
			client = DemoHttp.getClient();
			break;
		case 2:
			client = DemoHttp.getHttpsClient();
			break;
		case 3:
			client = DemoHttp.getTrustAllClient();
			break;
		case 4:
			client = DemoHttp.getCustomClient(context);
			break;
		default:
			return "Invalid selection";
		}
		try {
			HttpResponse httpResponse = client.execute(request);
			int responseCode = httpResponse.getStatusLine().getStatusCode();
			String message = httpResponse.getStatusLine().getReasonPhrase();
			HttpEntity entity = httpResponse.getEntity();
			if (responseCode == 200 && entity != null) {
				return parseString(entity);
			} else {
				return String.format("Http error!\n %d: %s", responseCode,
						message);
			}
		} catch (ClientProtocolException e) {
			return e.getMessage();
		} catch (Throwable e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {
			client.getConnectionManager().shutdown();
		}
	}
}
