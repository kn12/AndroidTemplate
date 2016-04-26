package qinyn.android.template.ui.activity;

import java.util.ArrayList;
import java.util.List;

import qinyn.android.template.R;
import qinyn.android.template.ui.adapter.MainVpAdapter;
import qinyn.android.template.ui.baseview.BaseFragmentActivity;
import qinyn.android.template.ui.fragment.Fragment1;
import qinyn.android.template.ui.fragment.Fragment2;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ViewpagerActivity extends BaseFragmentActivity {

	@Override
	protected int getLayoutId() {
		return R.layout.activity_viewpager;
	}

	@Override
	protected void onContentView() {
		Button btn_title1 = (Button) findViewById(R.id.btn_title1);
		Button btn_title2 = (Button) findViewById(R.id.btn_title2);
		btn_title1.setOnClickListener(onClickListener);
		btn_title2.setOnClickListener(onClickListener);

		ViewPager viewpager = (ViewPager) findViewById(R.id.vpg_main);
		MainVpAdapter adapter = new MainVpAdapter(this);
		viewpager.setAdapter(adapter);
		List<Fragment> list = new ArrayList<>();
		list.add(new Fragment1());
		list.add(new Fragment2());
		adapter.setData(list);
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			int id = v.getId();
			switch (id) {
			case R.id.btn_title1:

				break;
			case R.id.btn_title2:

				break;
			}

		}
	};

}
