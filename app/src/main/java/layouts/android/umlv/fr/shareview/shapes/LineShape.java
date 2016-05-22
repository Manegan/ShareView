package layouts.android.umlv.fr.shareview.shapes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import layouts.android.umlv.fr.shareview.json.JSONObject;

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
    public String toJson() {
        StringBuilder b = new StringBuilder();
        b.append("{\"draw\": {\"shape\": \"polyline\",\n\"coordinates\": [");
        for (int i = 0; i < coordinates.size(); i++) {
            b.append("[" + coordinates.get(i)[0] + "," + coordinates.get(i)[1] + "]");
            if (i != coordinates.size() - 1) {
                b.append(",");
            }
        }
        b.append(",\noptions:\n" + super.asJson() + "}}");
        return b.toString();
    }
}