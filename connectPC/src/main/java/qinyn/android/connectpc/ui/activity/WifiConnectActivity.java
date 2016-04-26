package qinyn.android.connectpc.ui.activity;

import qinyn.android.connectpc.R;
import custom.utils.NetUtil;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author evan.qin
 * 
 */
public class WifiConnectActivity extends BaseFragmentActivity {

	private EditText edt_port;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_wifi;
	}

	@Override
	protected void onContentView() {
		Button btn_start = (Button) findViewById(R.id.btn_connect);
		Button btn_stop = (Button) findViewById(R.id.btn_stop);
		TextView tv = (TextView) findViewById(R.id.tv_ip);
		edt_port = (EditText) findViewById(R.id.edt_port);
		String s = NetUtil.getLocalHostIp();
		tv.setText("当前手机ip为： " + s);
		btn_start.setOnClickListener(onClickListener);
		btn_stop.setOnClickListener(onClickListener);
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			int id = v.getId();
			switch (id) {
			case R.id.btn_connect:
				String s = edt_port.getText().toString();
				if (TextUtils.isEmpty(s)) {
					s = "5554";
				}
				start(s);
				break;
			case R.id.btn_stop:
				stop();
				break;
			default:
				break;
			}
		}

	};

	private void start(String port) {
		Process localProcess;
		try {
			localProcess = Runtime.getRuntime().exec("su");
			DataOutputStream localDataOutputStream = new DataOutputStream(
					localProcess.getOutputStream());
			localDataOutputStream.writeBytes("setprop service.adb.tcp.port "
					+ port + "\n");
			localDataOutputStream.writeBytes("stop adbd" + "\n");
			localDataOutputStream.writeBytes("start adbd" + "\n");
			localDataOutputStream.flush();
			localDataOutputStream.close();
			localProcess.waitFor();
			localProcess.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void stop() {
		Process localProcess;
		try {
			localProcess = Runtime.getRuntime().exec("su");
			DataOutputStream localDataOutputStream = new DataOutputStream(
					localProcess.getOutputStream());
			localDataOutputStream.writeBytes("stop adbd" + "\n");
			localDataOutputStream.flush();
			localDataOutputStream.close();
			localProcess.waitFor();
			localProcess.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
