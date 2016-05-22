package layouts.android.umlv.fr.shareview;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by François on 22/05/2016.
 */
public class LineShape extends AbstractShape implements Shapes {
    private List<Integer[]> coordinates = new ArrayList<>();

    public LineShape(List<Integer[]> coordinates, float strokeWidth, int[] strokeColor, int[] fillColor) {
        super(strokeWidth, strokeColor, fillColor);
        this.coordinates = coordinates;
    }


    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(super.strokeWidth);
        paint.setARGB(super.strokeColor[0],super.strokeColor[1],super.strokeColor[2],super.strokeColor[3]);
        paint.setStyle(Paint.Style.STROKE);
        for (int i = 1; i < coordinates.size(); i++) {
            canvas.drawLine(coordinates.get(i-1)[0], coordinates.get(i-1)[1], coordinates.get(i)[0], coordinates.get(i)[1], paint);
        }
    }

    @Override
    public String toJson() {
        StringBuilder b = new StringBuilder();
        b.append("{\"draw\": {\"shape\": \"polyline\",\"coordinates\": [");
        for (int i = 0; i < coordinates.size(); i++) {
            b.append("[" + coordinates.get(i)[0] + "," + coordinates.get(i)[1] + "]");
            if (i != coordinates.size() - 1) {
                b.append(",");
            }
        }
        b.append(",options:" + super.asJson() + "}}");
        return b.toString();
    }
}