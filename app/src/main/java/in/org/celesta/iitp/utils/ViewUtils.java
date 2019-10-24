package in.org.celesta.iitp.utils;

import android.graphics.Point;
import android.view.View;

public class ViewUtils {

    public static Point getViewCenter(View view) {
        if (view == null) {
            return new Point();
        }

        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0] + view.getWidth() / 2;
        int y = location[1] + view.getHeight() / 2;
        return new Point(x, y);
    }

}
