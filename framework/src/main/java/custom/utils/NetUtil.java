package custom.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

public class NetUtil {

	// public static String getWifiIpAddress(Context context) {
	// // 获取wifi服务
	// WifiManager wifiManager = (WifiManager) context
	// .getSystemService(Context.WIFI_SERVICE);
	// // 判断wifi是否开启
	// if (!wifiManager.isWifiEnabled()) {
	// wifiManager.setWifiEnabled(true);
	// }
	// WifiInfo wifiInfo = wifiManager.getConnectionInfo();
	// int ipAddress = wifiInfo.getIpAddress();
	// String ip = intToIp(ipAddress);
	// return ip;
	// }
	//
	// private static String intToIp(int i) {
	// return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF)
	// + "." + (i >> 24 & 0xFF);
	// }

	// 得到本机ip地址
	public static String getLocalHostIp() {
		String ipaddress = "";
		try {
			Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces();
			// 遍历所用的网络接口
			while (en.hasMoreElements()) {
				NetworkInterface nif = en.nextElement();// 得到每一个网络接口绑定的所有ip
				Enumeration<InetAddress> inet = nif.getInetAddresses();
				// 遍历每一个接口绑定的所有ip
				while (inet.hasMoreElements()) {
					InetAddress ip = inet.nextElement();
					if (!ip.isLoopbackAddress()
							&& ip instanceof Inet4Address) {
						return ipaddress = ip.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return ipaddress;

	}

	// 得到本机Mac地址
	public static String getLocalMac(Context context) {
		String mac = "";
		// 获取wifi管理器
		WifiManager wifiMng = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfor = wifiMng.getConnectionInfo();
		mac = wifiInfor.getMacAddress();
		return mac;
	}

	public static String getIp() throws IOException {
		Socket socket = null;
		try {
			socket = new Socket("www.baidu.com", 80);
			return socket.getInetAddress().toString();
		} catch (Exception e) {
		} finally {
			socket.close();
		}
		return "";
	}
}
