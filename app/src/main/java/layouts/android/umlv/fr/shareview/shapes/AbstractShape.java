package layouts.android.umlv.fr.shareview.shapes;

import java.util.Map;


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
        b.append(",\n\"strokeColor\": [" + strokeColor[0] + "," + strokeColor[1]);
        b.append("," + strokeColor[2] + "," + strokeColor[3] + "],\n\"fillColor\": [");
        b.append(fillColor[0] + "," + fillColor[1] + "," + fillColor[2] + "," + fillColor[3] + "]}\n");
        return b.toString();
    }
/*
    public static Shapes fromJson(String s) {
        String[] sArray = s.split("\n");
        int i = 0;
        while (i < sArray.length && !sArray[i].contains("options")) {
            i++;
        }
        i++;
        float strokeWidth = Float.parseFloat(sArray[i].substring(15).replace(",", " ").trim());
        i++;

        sArray[i] =
        int[] strokeColor;
        int[] fillColor;


        if (s.contains("ellipsis")) {
            int[] center = extractCoord(sArray[1]);
            int centerX = center[0];
            int centerY = center[1];

            int[] radius = extractCoord(sArray[2]);
            int radiusX = radius[0];
            int radiusY = radius[1];
            return new CircleShape(centerX, centerY, radiusX, radiusY, strokeWidth, strokeColor, fillColor);
        } else if (s.contains("rectangle")) {
            int left = Integer.parseInt(sArray[1].substring(8).replace(",", " ").trim());
            int top = Integer.parseInt(sArray[2].substring(7).replace(",", " ").trim());
            int right = Integer.parseInt(sArray[3].substring(9).replace(",", " ").trim());
            int bottom = Integer.parseInt(sArray[4].substring(10).replace(",", " ").trim());
            return new SquareShape(left, top, right, bottom, strokeWidth, strokeColor, fillColor);
        } else if (s.contains("polyline")) {
            return LineShape.fromJson(s);
        }
        return null;
    }

    private static int[] extractCoord(String s) {
        int[] result = new int[2];
        int debut = s.indexOf('[');
        int fin = s.indexOf(']');
        s = s.substring(debut, fin);
        int virgule = s.indexOf(',');
        result[1] = Integer.parseInt(s.substring(virgule).replace(",", " ").trim());
        return result;
    }

    private static[] extractCoord*/
}
