package layouts.android.umlv.fr.shareview.shapes;


import java.util.Map;

import layouts.android.umlv.fr.shareview.json.JSONObject;

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
    public String toJson() {
        StringBuilder b = new StringBuilder();
        b.append("{\"draw\":{\"shape\":\"rectangle\",\n\"left\": " + left);
        b.append(",\n\"top\": " + top + ",\n\"right\": " + right + ",\n\"bottom\": " + bottom);
        b.append("],\n" + "options: \n" + super.asJson() + "}}");
        return b.toString();
    }
}
