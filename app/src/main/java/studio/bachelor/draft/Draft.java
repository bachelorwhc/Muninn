package studio.bachelor.draft;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by BACHELOR on 2016/02/24.
 */
public class Draft{
    private static final DraftDirector director = DraftDirector.instance;
    private static final Draft instance = new Draft();
    public static Draft getInstance() {
        return instance;
    }

    private Draft() {

    };
}
