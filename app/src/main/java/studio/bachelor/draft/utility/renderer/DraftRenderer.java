package studio.bachelor.draft.utility.renderer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import studio.bachelor.draft.Draft;
import studio.bachelor.draft.utility.Renderable;
import studio.bachelor.muninn.Muninn;

/**
 * Created by BACHELOR on 2016/02/24.
 */
public class DraftRenderer implements Renderable {
    private Draft draft;
    private Bitmap birdview;
    private final Paint paint = new Paint();

    public DraftRenderer(Draft draft) {
        this.draft = draft;
    }

    public void setBirdview(Uri uri) {
        try {
            birdview = MediaStore.Images.Media.getBitmap(Muninn.getContext().getContentResolver(), uri);
        } catch (Exception e) {
            Log.d("DraftRenderer", "setBirdview(Uri uri)" + e.toString());
        }
    }

    public void onDraw(Canvas canvas) {
        if(birdview != null)
            canvas.drawBitmap(birdview, 0, 0, paint);
        float tx = draft.layer.getWidth() / 2;
        float ty = draft.layer.getHeight() / 2;
        canvas.translate(tx, ty);
    }
}
