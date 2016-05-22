package layouts.android.umlv.fr.shareview;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by François on 22/05/2016.
 */
public class SquareShape extends AbstractShape implements Shapes {
    private int left;
    private int top;
    private int right;
    private int bottom;

    public SquareShape(int left, int top, int right, int bottom, float strokeWidth, int[] strockeColor, int[] fillColor) {
        super(strokeWidth, strockeColor, fillColor);
        this.left = left;
        this.top = top;
        this.bottom = bottom;
        this.right = right;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(super.strokeWidth);
        paint.setARGB(super.strokeColor[0],super.strokeColor[1],super.strokeColor[2],super.strokeColor[3]);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(left, top, right, bottom, paint);
    }

    @Override
    public String toJson() {
        StringBuilder b = new StringBuilder();
        b.append("{\"draw\":{\"shape\":\"rectangle\",\"left\": " + left);
        b.append(",\"top\": " + top + ",\"right\": " + right + ",\"bottom\": " + bottom);
        b.append("]," + "options: " + super.asJson() + "}}");
        return b.toString();
    }
}