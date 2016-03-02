package studio.bachelor.draft.utility.renderer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.HashMap;
import java.util.Map;

import studio.bachelor.draft.toolbox.Toolbox;
import studio.bachelor.draft.utility.Position;
import studio.bachelor.draft.utility.Renderable;
import studio.bachelor.draft.utility.TouchableGroup;
import studio.bachelor.muninn.Muninn;
import studio.bachelor.muninn.R;

/**
 * Created by BACHELOR on 2016/02/24.
 */
public class ToolboxRenderer implements TouchableGroup, Renderable {
    // TODO: 效能優化
    public final Toolbox toolbox;
    public final Paint paint = new Paint();
    public final Position upperLeftCorner;
    public float width;
    public float height;
    private static final Map<Toolbox.Tool, Bitmap> iconMap = new HashMap<Toolbox.Tool, Bitmap>();

    static {
        iconMap.put(Toolbox.Tool.DELETER, createBitmapByType(R.drawable.ic_delete_forever_black_48dp));
    }

    private static Bitmap createBitmapByType(int id) {
        return BitmapFactory.decodeResource(Muninn.getContext().getResources(), id);
    }

    public ToolboxRenderer(Toolbox toolbox, Position upper_left_corner, float width, float height) {
        this.toolbox = toolbox;
        this.upperLeftCorner = upper_left_corner;
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean inGroupRange(Position position) {
        double min_x = upperLeftCorner.x;
        double min_y = upperLeftCorner.y;
        double max_x = upperLeftCorner.x + width;
        double max_y = upperLeftCorner.y + height;
        if (position.x >= min_x && position.x <= max_x && position.y >= min_y && position.y <= max_x)
            return true;
        else
            return false;
    }

    @Override
    public boolean canBeTouched(Position position, double threshold) {
        if (inGroupRange(position)) {
            return getDistanceTo(position) < threshold;
        } else
            return false;
    }

    @Override
    public double getDistanceTo(Position position) {
        double min_disance = Double.MAX_VALUE;
        if(inGroupRange(position)) {
            int i = 0;
            for (Toolbox.Tool tool : toolbox.tools) {
                Position tool_position = getToolPosition(tool, i);
                double distance = tool_position.getDistanceTo(position);
                if (distance < min_disance)
                    min_disance = distance;
                ++i;
            }
        }
        else {
            double c_x = upperLeftCorner.x + width / 2;
            double c_y = upperLeftCorner.y + height / 2;
            min_disance = position.getDistanceTo(new Position(c_x, c_y));
        }
        return min_disance;
    }

    @Override
    public Toolbox.Tool getInstance(Position position, double threshold) {
        double min_disance = Double.MAX_VALUE;
        Toolbox.Tool return_instance = null;
        if(inGroupRange(position)) {
            int i = 0;
            for (Toolbox.Tool tool : toolbox.tools) {
                Position tool_position = getToolPosition(tool, i);
                double distance = tool_position.getDistanceTo(position);
                if (distance < min_disance && distance < threshold) {
                    min_disance = distance;
                    return_instance = tool;
                }
                ++i;
            }
        }
        return return_instance;
    }

    private Position getToolPosition(Toolbox.Tool tool, int index) {
        float unit = width / toolbox.tools.size();
        Bitmap bitmap = getToolBitmap(tool);
        double x = upperLeftCorner.x + index * unit / 2 - bitmap.getWidth() / 2;
        double y = upperLeftCorner.y + height / 2 - bitmap.getHeight() / 2;
        return new Position(x, y);
    }

    private Bitmap getToolBitmap(Toolbox.Tool tool) {
        Bitmap bitmap = iconMap.get(tool);
        return bitmap;
    }

    @Override
    public void onDraw(Canvas canvas) {
        int i = 0;
        for (Toolbox.Tool tool : toolbox.tools) {
            Bitmap bitmap = getToolBitmap(tool);
            Position position = getToolPosition(tool, i);
            canvas.drawBitmap(bitmap, (float) position.x, (float) position.y, paint);
            ++i;
        }
    }
}
