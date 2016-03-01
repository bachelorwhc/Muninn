package studio.bachelor.muninn;

import android.app.Application;
import android.content.Context;

/**
 * Created by BACHELOR on 2016/03/01.
 */
public class Muninn extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }

}
