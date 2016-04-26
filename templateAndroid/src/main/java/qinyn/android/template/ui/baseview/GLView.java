package qinyn.android.template.ui.baseview;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class GLView extends GLSurfaceView {

	public GLView(Context context, AttributeSet attrs) {
		super(context, attrs);
//		setEGLContextClientVersion(2);
		// Render the view only when there is a change in the drawing data
//		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
		setRenderer(mRender);
 
	}

	public GLView(Context context) {
		super(context);
		setRenderer(mRender);
	}

	private Renderer mRender = new Renderer() {

		@Override
		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			// Set the background frame color
			GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
		}

		@Override
		public void onSurfaceChanged(GL10 gl, int width, int height) {
			GLES20.glViewport(0, 0, width, height);
		}

		@Override
		public void onDrawFrame(GL10 gl) {
			// Redraw background color
			GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
		}
	};

}
