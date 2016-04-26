package qinyn.android.template.ui.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;

public class MainVpAdapter extends FragmentPagerAdapter {

	private List<Fragment> list;

	public MainVpAdapter(FragmentActivity act) {
		super(act.getSupportFragmentManager());
	}

	public void setData(List<Fragment> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	@Override
	public Fragment getItem(int arg0) {
		return list == null ? null : list.get(arg0);
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

}
