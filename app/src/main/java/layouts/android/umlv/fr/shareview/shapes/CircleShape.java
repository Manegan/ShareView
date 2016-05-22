package layouts.android.umlv.fr.shareview.shapes;

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
    public String toJson() {
        return " {\"draw\": {\"shape\":\"ellipsis\",\"center\": [" +
                centerX + "," + centerY + "],\"radius\":[" + radiusX + ","
                + radiusY + "],\"options\": " + super.asJson() + "}}";
    }
}
