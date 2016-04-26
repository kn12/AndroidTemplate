package custom.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * 网络状态 （读取网络当前状态， 判断网络是否可用） 内部类，若网络不可用，则返回false，可用true
 * 
 * @author qinyn 2011-08-12
 */
public class NetWorkStatusTool {
	private static NetWorkStatusTool mNetWorkStatusTool = new NetWorkStatusTool();
	private Context mContext;
	private int i = 0;
	private boolean mStatus;
	private int mport;
	private String mhost;

	public void setContext(Context context) {
		this.mContext = context;
	}

	public static NetWorkStatusTool getNetWorkStatusToolInstance() {
		return mNetWorkStatusTool;
	}

	// 判断当前网络状态是否可用
	public boolean judgeNetWorkStatus() {
		if (i == 0) {
			return this.readNetWorkStatus();
		} else
			return mStatus;
	}

	// 读取网络当前状态
	public boolean readNetWorkStatus() {
		ConnectivityManager conManager = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		// 获取代表联网状态的NetWorkInfo对象
		NetworkInfo networkInfo = conManager.getActiveNetworkInfo();
		NetworkInfo wifiNetInfo = conManager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		NetworkInfo mobileNetInfo = conManager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		i = 1;
		if (networkInfo == null || !networkInfo.isConnected())// 当前网络不可用
		{
			mStatus = false;
		} else {
			if (wifiNetInfo.isConnected()) {
				mhost = "";
				mport = 0;
			} else {
				mhost = android.net.Proxy.getDefaultHost();
				mport = android.net.Proxy.getDefaultPort();
			}
			mStatus = true;
		}
		String a = networkInfo != null ? networkInfo.isConnected() + ""
				: "null";
		String m = mobileNetInfo != null ? mobileNetInfo.isConnected() + ""
				: "null";
		String w = wifiNetInfo != null ? wifiNetInfo.isConnected() + ""
				: "null";
		Log.i("NetWorkStatusTool", String.format(
				"NetStatus:A:%s,M:%s,W:%s|mStatus:%s|h:%s,p:%s", a, m, w,
				mStatus, mhost, mport));
		return mStatus;
	}

	// 获取端口号
	public int getDefaultPort() {
		return mport;
	}

	// 获取代理服务器
	public String getDefaultHost() {
		return mhost;
	}
}
