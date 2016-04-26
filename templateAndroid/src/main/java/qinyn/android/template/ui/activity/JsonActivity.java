package qinyn.android.template.ui.activity;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import qinyn.android.template.R;
import qinyn.android.template.bean.User;
import qinyn.android.template.ui.baseview.BaseFragmentActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;
import android.util.JsonReader;
import android.util.Log;

public class JsonActivity extends BaseFragmentActivity {

	@Override
	protected int getLayoutId() {
		return R.layout.activity_jason;
	}

	@Override
	protected void onContentView() {
		test3();
	}

	private void test() {
		try {
			JSONObject j = new JSONObject(js);
			String data = j.getString("data");
			JSONObject jd = new JSONObject(data);

			JSONArray jd_s = jd.getJSONArray("list");

			for (int i = 0; i < jd_s.length(); i++) {
				JSONObject jsono = (JSONObject) jd_s.get(i);
				String ss = jsono.getString("name");
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String js = "{code=100,msg=\"ss\",data:{msg:\"a\"}}";
	private String aa = "{\"result\":{\"openid\":\"cf73851b5bb55526450268d751761043\","
			+ "\"sessionid\":\"b84877e6916024a5cb47a83c69f80f18053ec545d\"}}";

	
	private void test2() {
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(js);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		try {
			JSONObject data = jsonObject.getJSONObject("data");
			int code = jsonObject.getInt("code");
			Log.i("", "-------------" + data.getString("msg"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void test3() {
//		Object o = null;
//		String strings = js;
//		JSONObject jObj;
//		try {
//			jObj = new JSONObject(strings);
//
//			if (jObj.has("data")) {
//				Object resultObj = jObj.get("data");
//				if (resultObj instanceof JSONObject) {
//					 o =  new Gson().fromJson(
//							((JSONObject) resultObj).toString(),
//							Re.class);
//				} else if (resultObj instanceof JSONArray) {
//					 o =  new Gson().fromJson(
//							((JSONArray) resultObj).toString(),
//							Re.class);
//				} 
//			}
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
		

	}

	public static ArrayList<User> ParseXml(XmlResourceParser parser) {
		ArrayList<User> userArray = new ArrayList<User>();
		User user = null;
		try {
			// 开始解析事件
			int eventType = parser.getEventType();
			// 处理事件，不碰到文档结束就一直处理
			while (eventType != XmlResourceParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlResourceParser.START_DOCUMENT:// 不做任何操作或初开始化数据
					break;
				case XmlResourceParser.START_TAG: // 解析XML节点数据
					// 给当前标签起个名字
					String tagName = parser.getName();
					if (tagName.equals("user")) {
						user = new User();
					} else if (tagName.equals("id")) {
						user.setUserId(Integer.parseInt(parser.nextText()));
					} else if (tagName.equals("name")) {
						user.setUsername(parser.nextText());
					}
					break;
				case XmlResourceParser.END_TAG:// 判断当前事件是否是标签元素结束事件
					userArray.add(user);
					break;
				case XmlResourceParser.END_DOCUMENT:
					break;
				}
				// 别忘了用next方法处理下一个事件，忘了的结果就成死循环#_#
				eventType = parser.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userArray;
	}

	private String jsonData = "[{\"request_id\":000000001,\"request_id\":000000001,\"request_id\":000000001,\"request_id\":000000001,\"request_id\":000000001}]";

	@SuppressLint("NewApi")
	public void parseJsonArrayPerson(String json) {
		try {
			JsonReader reader = new JsonReader(new StringReader(jsonData));
			reader.beginArray();
			while (reader.hasNext()) {
				reader.beginObject();
				while (reader.hasNext()) {
					String tagName = reader.nextName();
					if (tagName.equals("username")) {
						System.out.println(reader.nextString());
					} else if (tagName.equals("userId")) {
						System.out.println(reader.nextString());
					}
				}
				reader.endObject();
			}
			reader.endArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
