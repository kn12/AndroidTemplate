package custom.utils;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;

/**
 * 工具类
 * 
 * @author qinyn
 * 
 */
public class ActivityUtils {
	/**
	 * 检测软件是否在前台运行
	 * 
	 * @param activity
	 * @return
	 */
	public static boolean isTopActivity(Context activity,String packageName) {
		ActivityManager activityManager = (ActivityManager) activity
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
		if (tasksInfo.size() > 0) {
			// 应用程序位于堆栈的顶层
			if (packageName.equals(tasksInfo.get(0).topActivity
					.getPackageName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 软件是否运行着
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isAppRunning(Context context,String packageName) {
		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(100);
		for (RunningTaskInfo info : tasksInfo) {
			if (info.topActivity.getPackageName().equals(packageName)
					&& info.baseActivity.getPackageName().equals(packageName)) {
				return true;
			}
		}
		return false;
	}

}
