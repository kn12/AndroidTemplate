package custom.utils;

import java.lang.reflect.Method;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;

public class DeviceTool {
	
	public static final String UNKNOWN = "unknown";
	
	/**
	 * Get IMEI of the device. If it has no IMEI or no
	 * {@link Manifest.permission#READ_PHONE_STATE} permission or it is an
	 * emulator, {@link #UNKNOWN} will be returned.
	 */
	public static String getIMEI(Context context) {
		String id = null;
		if (checkPermission(context, Manifest.permission.READ_PHONE_STATE)) {
			id = (String) invokeTelephonyManagerMethod("getD" + "evi" + "ceId",
					context);
		}
		if (TextUtils.isEmpty(id) || isZero(id)) {
			return UNKNOWN;
		}
		return id;
	}
	
	private static boolean isZero(String id) {
		for (int i = 0; i < id.length(); i++) {
			char index = id.charAt(i);
			if (index != '0')
				return false;
		}
		return true;
	}
	
	private static Object invokeTelephonyManagerMethod(String methodName,
			Context cxt) {
		try {
			Method m = Context.class.getMethod("getS" + "yste" + "mSer"
					+ "vice", new Class<?>[] { String.class });
			Object phone = m.invoke(cxt, new Object[] { "phone" });

			Method m2 = phone.getClass().getMethod(methodName,
					(Class<?>[]) null);
			return m2.invoke(phone, (Object[]) null);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * Check whether the specified permission is granted to the current package.
	 * 
	 * @param context
	 * @param permissionName
	 *            The permission.
	 * @return True if granted, false otherwise.
	 */
	public static boolean checkPermission(Context context, String permissionName) {
		PackageManager packageManager = context.getPackageManager();
		String pkgName = context.getPackageName();
		return packageManager.checkPermission(permissionName, pkgName) == PackageManager.PERMISSION_GRANTED;
	}

}
