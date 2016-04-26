package qinyn.android.framework;

import custom.utils.NetWorkStatusTool;
import custom.utils.OriginalContext;
import custom.utils.Res;
import android.app.Application;

public class FrameworkApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		OriginalContext.setContext(getApplicationContext());
		Res.setContext(getApplicationContext());
		NetWorkStatusTool.getNetWorkStatusToolInstance().setContext(this);
	}

}
