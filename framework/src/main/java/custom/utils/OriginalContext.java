/**
 * 
 */

package custom.utils;

import android.content.Context;

/**
 * @author qinyn 获取系统context，此处生命周期是完整的，跟随整个应用的
 */
public class OriginalContext {
    private static Context context;

    public static void setContext(Context context) {
        OriginalContext.context = context;
    }

    public static Context getContext() {
        return context;
    }
}
