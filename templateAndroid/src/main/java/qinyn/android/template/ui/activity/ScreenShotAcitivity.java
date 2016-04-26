package qinyn.android.template.ui.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import qinyn.android.template.R;
import qinyn.android.template.ui.baseview.BaseFragmentActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

public class ScreenShotAcitivity extends BaseFragmentActivity {

	private Display mDisplay;
	private WindowManager mWindowManager;
	private DisplayMetrics mDisplayMetrics;
	private Bitmap mScreenBitmap;
	private Matrix mDisplayMatrix;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_screen_shot;
	}

	@Override
	protected void onContentView() {
		new Thread(new Runnable() {

			@Override
			public void run() {
//				takeScreenshot();

			}
		}).start();
	}

	private float getDegreesForRotation(int value) {
		switch (value) {
		case Surface.ROTATION_90:
			return 360f - 90f;
		case Surface.ROTATION_180:
			return 360f - 180f;
		case Surface.ROTATION_270:
			return 360f - 270f;
		}
		return 0f;
	}

	@SuppressLint("NewApi")
	private void takeScreenshot() {
		mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		mDisplay = mWindowManager.getDefaultDisplay();
		mDisplayMetrics = new DisplayMetrics();
		mDisplay.getRealMetrics(mDisplayMetrics);
		mDisplayMatrix = new Matrix();
		float[] dims = { mDisplayMetrics.widthPixels,
				mDisplayMetrics.heightPixels };

		int value = mDisplay.getRotation();
		String hwRotation =null; 
//				SystemProperties.get("ro.sf.hwrotation", "0");
		if (hwRotation.equals("270") || hwRotation.equals("90")) {
			value = (value + 3) % 4;
		}
		float degrees = getDegreesForRotation(value);

		boolean requiresRotation = (degrees > 0);
		if (requiresRotation) {
			// Get the dimensions of the device in its native orientation
			mDisplayMatrix.reset();
			mDisplayMatrix.preRotate(-degrees);
			mDisplayMatrix.mapPoints(dims);

			dims[0] = Math.abs(dims[0]);
			dims[1] = Math.abs(dims[1]);
		}

		mScreenBitmap =null; 
//				Surface.screenshot((int) dims[0], (int) dims[1]);

		if (requiresRotation) {
			// Rotate the screenshot to the current orientation
			Bitmap ss = Bitmap.createBitmap(mDisplayMetrics.widthPixels,
					mDisplayMetrics.heightPixels, Bitmap.Config.ARGB_8888);
			Canvas c = new Canvas(ss);
			c.translate(ss.getWidth() / 2, ss.getHeight() / 2);
			c.rotate(degrees);
			c.translate(-dims[0] / 2, -dims[1] / 2);
			c.drawBitmap(mScreenBitmap, 0, 0, null);
			c.setBitmap(null);
			mScreenBitmap = ss;
		}

		// If we couldn't take the screenshot, notify the user
		if (mScreenBitmap == null) {
			return;
		}

		// Optimizations
		mScreenBitmap.setHasAlpha(false);
		mScreenBitmap.prepareToDraw();

		try {
			saveBitmap(mScreenBitmap);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void saveBitmap(Bitmap bitmap) throws IOException {
		String imageDate = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")
				.format(new Date(System.currentTimeMillis()));
		File file = new File("/mnt/sdcard/Pictures/" + imageDate + ".png");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream out;
		try {
			out = new FileOutputStream(file);
			if (bitmap.compress(Bitmap.CompressFormat.PNG, 70, out)) {
				out.flush();
				out.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
