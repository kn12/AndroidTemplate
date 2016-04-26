/**		
 * @author qinyn
 */

package qinyn.android.framework.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * @author qinyn
 */
public abstract class AbstractBaseFragmentActivity extends FragmentActivity {
    private static final String TAG = "AbstractFragmentActivity";

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(getLayoutId());
        onContentView();
    }

    protected abstract int getLayoutId();

    protected abstract void onContentView();

}
