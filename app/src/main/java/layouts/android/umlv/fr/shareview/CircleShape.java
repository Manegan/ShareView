package layouts.android.umlv.fr.shareview;


import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by François on 22/05/2016.
 */
public class CircleShape extends AbstractShape implements Shapes {
    private int centerX;
    private int centerY;
    private int radiusX;
    private int radiusY;

    public CircleShape(int centerX, int centerY, int radiusX, int radiusY, float strokeWidth, int[] strokeColor, int[] fillColor) {
        super(strokeWidth, strokeColor, fillColor);
        this.centerX = centerX;
        this.centerY = centerY;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(super.strokeWidth);
        paint.setARGB(super.strokeColor[0],super.strokeColor[1],super.strokeColor[2],super.strokeColor[3]);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(centerX, centerY, radiusX, paint);
    }

    @Override
    public String toJson() {
        return " {\"draw\": {\"shape\":\"ellipsis\",\"center\": [" +
                centerX + "," + centerY + "],\"radius\":[" + radiusX + ","
                + radiusY + "],\"options\": " + super.asJson() + "}}";
    }

}