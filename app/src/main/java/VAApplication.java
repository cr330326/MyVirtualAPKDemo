import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.didi.virtualapk.PluginManager;

/**
 * Created by Allen on 2017/7/22.
 */

public class VAApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        long start = System.currentTimeMillis();
        PluginManager.getInstance(base).init();
        Log.d("ryg", "use time:" + (System.currentTimeMillis() - start));
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
