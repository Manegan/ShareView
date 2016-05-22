package layouts.android.umlv.fr.shareview.shapes;

/**
 * Created by François on 22/05/2016.
 */
public abstract class AbstractShape  {
    private float strokeWidth;
    private int[] strokeColor;
    private int[] fillColor;


    public AbstractShape(float strokeWidth, int[] strokeColor, int[]fillColor){
        this.strokeWidth = strokeWidth;
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
    }

    public String asJson(){
        StringBuilder b = new StringBuilder();
        b.append("{strokeWidth\":" + strokeWidth);
        b.append(",\"strokeColor\": [" + strokeColor[0] + "," + strokeColor[1]);
        b.append("," + strokeColor[2] + "," + strokeColor[3] + "],\"fillColor\": [");
        b.append( fillColor[0] + "," + fillColor[1] + "," +fillColor[2]+ "," +fillColor[3]+"]}" );
        return b.toString();
    }

    public static String fromJson(String s){

        return null;
    }



}
