package layouts.android.umlv.fr.shareview;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by François on 22/05/2016.
 */
public abstract class AbstractShape {
    private float strokeWidth;
    private int[] strokeColor;
    private int[] fillColor;


    public AbstractShape(float strokeWidth, int[] strokeColor, int[] fillColor) {
        this.strokeWidth = strokeWidth;
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
    }

    public String asJson() {
        StringBuilder b = new StringBuilder();
        b.append("{strokeWidth\" : " + strokeWidth);
        b.append(",\"strokeColor\": [" + strokeColor[0] + "," + strokeColor[1]);
        b.append("," + strokeColor[2] + "," + strokeColor[3] + "],\"fillColor\": [");
        b.append(fillColor[0] + "," + fillColor[1] + "," + fillColor[2] + "," + fillColor[3] + "]}");
        return b.toString();
    }

    public static Shapes fromJson(String s) throws JSONException {
        JSONObject obj = new JSONObject(s);
        JSONObject shapeMsg = obj.getJSONObject("message").getJSONObject("draw");
        String shapeName = obj.getString("shape");
        switch(shapeName) {
            case "ellipsis":
                return createEllipse(shapeMsg);
            case "rectangle":
                return createRect(shapeMsg);
            case "polyline":
                return createPoly(shapeMsg);
            default:
                return null;
        }
    }

    private static Shapes createEllipse(JSONObject shapeMsg) throws JSONException {
        JSONArray coord = shapeMsg.getJSONArray("center");
        int x = Integer.parseInt(coord.getString(0));
        int y = Integer.parseInt(coord.getString(1));

        JSONArray rads = shapeMsg.getJSONArray("radius");
        int r1 = Integer.parseInt(rads.getString(0));
        int r2 = Integer.parseInt(rads.getString(1));

        JSONObject options = shapeMsg.getJSONObject("options");
        float width = Integer.parseInt(options.getString("strokeWidth"));

        JSONArray jcolor = options.getJSONArray("strokeColor");
        JSONArray jfcolor = options.getJSONArray("fillColor");
        int[] color = new int[4];
        int[] fillColor = new int[4];
        for (int i = 0; i < jcolor.length(); i++) {
            color[i] = Integer.parseInt(jcolor.getString(i));
            fillColor[i] = Integer.parseInt(jfcolor.getString(i));
        }

        return new CircleShape(x,y,r1,r2,width,color,fillColor);
    }

    private static Shapes createRect(JSONObject shapeMsg) throws JSONException {
        int left = Integer.parseInt(shapeMsg.getString("left"));
        int top = Integer.parseInt(shapeMsg.getString("top"));
        int bottom = Integer.parseInt(shapeMsg.getString("bottom"));
        int right = Integer.parseInt(shapeMsg.getString("right"));

        JSONObject options = shapeMsg.getJSONObject("options");
        float width = Integer.parseInt(options.getString("strokeWidth"));

        JSONArray jcolor = options.getJSONArray("strokeColor");
        JSONArray jfcolor = options.getJSONArray("fillColor");
        int[] color = new int[4];
        int[] fillColor = new int[4];
        for (int i = 0; i < jcolor.length(); i++) {
            color[i] = Integer.parseInt(jcolor.getString(i));
            fillColor[i] = Integer.parseInt(jfcolor.getString(i));
        }

        return new SquareShape(left, top, right, bottom, width, color, fillColor);
    }

    private static Shapes createPoly(JSONObject shapeMsg) throws JSONException {
        JSONArray coords = shapeMsg.getJSONArray("coordinates");
        List<Integer[]> polyCoords = new ArrayList<>();
        for (int i = 0; i < coords.length(); i++) {
            Integer[] curr = new Integer[2];
            curr[0] = Integer.parseInt(coords.getJSONArray(i).getString(0));
            curr[1] = Integer.parseInt(coords.getJSONArray(i).getString(1));
            polyCoords.add(curr);
        }

        JSONObject options = shapeMsg.getJSONObject("options");
        float width = Integer.parseInt(options.getString("strokeWidth"));

        JSONArray jcolor = options.getJSONArray("strokeColor");
        JSONArray jfcolor = options.getJSONArray("fillColor");
        int[] color = new int[4];
        int[] fillColor = new int[4];
        for (int i = 0; i < jcolor.length(); i++) {
            color[i] = Integer.parseInt(jcolor.getString(i));
            fillColor[i] = Integer.parseInt(jfcolor.getString(i));
        }

        return new LineShape(polyCoords, width, color, fillColor);
    }
}