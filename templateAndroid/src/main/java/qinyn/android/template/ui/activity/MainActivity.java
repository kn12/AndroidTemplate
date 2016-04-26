package qinyn.android.template.ui.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import qinyn.android.template.R;
import qinyn.android.template.ui.baseview.BaseFragmentActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends BaseFragmentActivity {

	private ImageView iv1;

	private ImageView iv2;

	// private RequestQueue mQueue;
	// private NetworkImageView mNetworkImageView;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	protected void onContentView() {
		ViewPager vpg = (ViewPager) findViewById(R.id.vpg_main);

		 findViewById(R.id.btn1).setOnClickListener(clickListener);
		// findViewById(R.id.btn2).setOnClickListener(clickListener);
	}

	private OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			int id = v.getId();
			switch (id) {
			case R.id.btn1:
				// Intent intent = new Intent(MainActivity.this,
				// ViewpagerActivity.class);
				// Intent intent = new Intent(MainActivity.this,
				// SlideMenuActivity.class);
				// Intent intent = new Intent(MainActivity.this,
				// PullToRefreshActivity.class);
				// Intent intent = new Intent(MainActivity.this,
				// SSLActivity.class);
				// Intent intent = new Intent(MainActivity.this,
				// WifiConnectActivity.class);
				// startActivity(intent);
				// Log.i("", "------------"+handler.hasMessages(0));

				// bm = getBitmapByPath();
				// Bitmap bt = getBitmapByPath();
				// iv2.setImageBitmap(bt);
				 Intent intent = new Intent(MainActivity.this,
				 GLActivity.class);
				 startActivity(intent);
				break;
			// case R.id.btn2:
			// SysTimer.cancel();
			// test();
			// break;
			default:
				break;
			}

		}
	};

	private Bitmap getBitmapByPath() {

		try {

			// 新的
			// File file = new File(picURL);
			// FileInputStream fis = new FileInputStream(file);
			InputStream fis = getAssets().open("test2.png");

			byte[] imageData = readInputStream(fis);
			// byte[] imageData = readInputStream(iv.get);

			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeByteArray(imageData, 0, imageData.length,
					options);

			int size = (options.outWidth / 480); // 通过文件分辨率缩放一次
			int fromFileSize = imageData.length / (1024 * 1024); // 每大于1M，缩小一倍,最多缩小3倍
			size += fromFileSize > 3 ? 3 : fromFileSize;

			int inSampleSize = size == 0 ? 1 : size; // 缩放比例

//			options.outHeight = options.outHeight * 320 / options.outWidth;
//			options.outWidth = 240;
//			options.inSampleSize = inSampleSize;
//			Log.i("", "------------" + inSampleSize);
			options.inSampleSize = 2;
			options.inJustDecodeBounds = false;

			// if (isGif) {
			// gifBytes = imageData;
			// }
			Bitmap bm = BitmapFactory.decodeByteArray(imageData, 0,
					imageData.length, options);
			return bm;
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	/**
	 * 计算SampleSize
	 * 
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	private static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {

		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			if (height > reqHeight && reqHeight != 0) {
				inSampleSize = (int) Math.floor((double) height
						/ (double) reqHeight);
			}

			int tmp = 0;

			if (width > reqWidth && reqWidth != 0) {
				tmp = (int) Math.floor((double) width / (double) reqWidth);
			}

			inSampleSize = Math.max(inSampleSize, tmp);

		}
		int roundedSize;
		if (inSampleSize <= 8) {
			roundedSize = 1;
			while (roundedSize < inSampleSize) {
				roundedSize <<= 1;
			}
		} else {
			roundedSize = (inSampleSize + 7) / 8 * 8;
		}

		return roundedSize;
	}
	
	/**
	 * 图片压缩
	 * 
	 * @param context
	 *            上下文
	 * @param picPath
	 *            图片路径
	 * @return
	 */
	public static String compressPic(Context context, String picPath) {

		if (TextUtils.isEmpty(picPath)) {
			return "";
		}

		if (picPath.endsWith(".gif") || picPath.endsWith(".GIF")) {
			return picPath;
		}

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(picPath, options);
		options.inSampleSize = calculateInSampleSize(options,
				480, 800);
		options.inJustDecodeBounds = false;
		options.inPurgeable = true;
		options.inInputShareable = true;

		Bitmap bitmap = BitmapFactory.decodeFile(picPath, options);
		FileOutputStream stream = null;
//		File f = DiskLruCache.getDiskCacheDir(context,
//				ImageCacheUtil.IMAGE_CACHE_PHOTO_THUMB_DIR + File.separator
//						+ "compress_pic" + System.currentTimeMillis());
		File f = new File(Environment.getRootDirectory()+"test.png");
		String tmp = f.getPath();
		try {
			new File(tmp).getParentFile().mkdirs();
			new File(tmp).createNewFile();
			stream = new FileOutputStream(new File(tmp));
		} catch (IOException ignored) {

		}
		int qu = 100;//压缩率
		bitmap.compress(Bitmap.CompressFormat.JPEG, qu, stream);

		if (stream != null) {
			try {
				stream.close();
				bitmap.recycle();
			} catch (IOException ignored) {

			}
		}
		return tmp;
	}

	
	public byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		try {
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			byte[] data = outStream.toByteArray();
			outStream.flush();
			outStream.close();
			inStream.close();
			outStream = null;
			return data;
		} catch (OutOfMemoryError e) {
			outStream = null;
			System.gc();
			return null;
		} catch (Exception e) {
			outStream = null;
			System.gc();
			return null;
		}

	}

	private HandlerThread thread = new HandlerThread("login_background");

	private MyHandler handler;

	private class MyHandler extends Handler {

		public MyHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				try {
					Log.i("", "--------start----------");
					Thread.sleep(30000);
					Log.i("", "--------end----------");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
		}

	};

	private void test() {
		thread.start();
		handler = new MyHandler(thread.getLooper());
		handler.sendEmptyMessage(0);
	}

	@Override
	protected void onPause() {
		super.onPause();
		System.exit(0);
	}

}
