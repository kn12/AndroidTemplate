package qinyn.android.template.ui.activity;

import qinyn.android.template.R;
import qinyn.android.template.ui.baseview.BaseFragmentActivity;
import android.view.View;
import custom.widget.SlideHolder;

public class SlideMenuActivity extends BaseFragmentActivity {

	private SlideHolder mSlideHolder;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_slide_menu;
	}

	@Override
	protected void onContentView() {
		mSlideHolder = (SlideHolder) findViewById(R.id.slideHolder);

		/*
		 * toggleView can actually be any view you want. Here, for simplicity,
		 * we're using TextView, but you can easily replace it with button.
		 * 
		 * Note, when menu opens our textView will become invisible, so it quite
		 * pointless to assign toggle-event to it. In real app consider using UP
		 * button instead. In our case toggle() can be replaced with open().
		 */

		View toggleView = findViewById(R.id.textView);
		toggleView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mSlideHolder.toggle();
			}
		});
	}

}
