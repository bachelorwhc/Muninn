package studio.bachelor.muninn;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by BACHELOR on 2016/03/01.
 */
public class Muninn extends Application {
    private static Context context;
    private static SharedPreferences sharedPreferences;

    public void onCreate() {
        super.onCreate();
        context = this;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static Context getContext() {
        return context;
    }

    public static float getSizeSetting(int key_id, int default_id) {
        String default_str = context.getResources().getString(default_id);
        String key_str = context.getResources().getString(key_id);
        String preference = sharedPreferences.getString(key_str, default_str);
        return Math.abs(Float.parseFloat(preference));
    }

    public static String getColorSetting(int key_id, int default_id) {
        String default_str = context.getResources().getString(default_id);
        String key_str = context.getResources().getString(key_id);
        String preference = sharedPreferences.getString(key_str, default_str);
        return preference;
    }
}
