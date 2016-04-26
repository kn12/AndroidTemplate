/**		
 * @author qinyn
 */

package qinyn.android.framework.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author qinyn
 */
public abstract class AbstractBaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        onContentView(view);
        return view;
    }

    protected abstract int getLayoutId();

    protected abstract void onContentView(View view);

}
