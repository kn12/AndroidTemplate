package custom.utils;

import android.app.Activity;
import android.graphics.Rect;

public class ViewTool {

	/**
	 * 得到状态栏高度
	 */
	public static int getStatusBarHeight(Activity act) {
		Rect frame = new Rect();
		act.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;
		return statusBarHeight;
	}
	
}
