package layouts.android.umlv.fr.shareview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class DrawingView extends View{
    private String boardName = "default";
    private String author = "admin";

    private String selectedTool;
    private Paint drawPaint, canvasPaint;
    private int paintColor = 0xFF660000;
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;
    private int startingX = 0;
    private int startingY = 0;
    private int endX = 0;
    private int endY = 0;
    private List<Shapes> shapes = new ArrayList<>();

    public DrawingView(Context context) {
        super(context);
        setupDrawing();
    }

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }

    public DrawingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setupDrawing();
    }

    public void setSelectedTool(String selectedTool) {
        this.selectedTool = selectedTool;
        Log.d(getClass().getName(), "Selected Tool : "+this.selectedTool);
    }

    public void setupDrawing() {
        drawPaint = new Paint();

        drawPaint.setColor(paintColor);

        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(20);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        canvasPaint = new Paint(Paint.DITHER_FLAG);

        canvasBitmap = Bitmap.createBitmap(2000, 2000, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
        selectedTool = "Line";
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        for (Shapes s : shapes) {
            s.draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        HttpBundle bundle;
        AsyncHttpRequester ahttpr;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startingX = Math.round(touchX);
                startingY = Math.round(touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                // Do things for polyline, but eh, do we realize this feature ?
                break;
            case MotionEvent.ACTION_UP:
                endX = Math.round(touchX);
                endY = Math.round(touchY);
                int[] color = {255, 255, 255, 255};
                int[] fill = {255, 0, 0, 0};
                Log.v(getClass().getName(), "Drawing something with : "+ selectedTool);
                switch (selectedTool) {
                    case "Line":
                        List<Integer[]> coords = new ArrayList<>();
                        Integer[] p1 = {startingX, startingX};
                        Integer[] p2 = {endX, endY};
                        coords.add(p1);
                        coords.add(p2);
                        LineShape line = new LineShape(coords, 0.02f, color, fill);
                        shapes.add(line);
                        // TODO : new Line(), send it to server

                        ahttpr = new AsyncHttpRequester();
                        bundle = new HttpBundle();
                        bundle.setMethod("POST");
                        bundle.setQueueName(boardName);
                        bundle.setAuthor(author);
                        bundle.setMessage(line.toJson());
                        drawCanvas.drawLine(startingX, startingY, endX, endY, drawPaint);
                        break;
                    case "Rectangle":
                        SquareShape rect = new SquareShape(startingX, startingY, endX, endY, 0.02f, color, fill);
                        shapes.add(rect);
                        // TODO : new Rectangle(), send it to server

                        ahttpr = new AsyncHttpRequester();
                        bundle = new HttpBundle();
                        bundle.setMethod("POST");
                        bundle.setQueueName(boardName);
                        bundle.setAuthor(author);
                        bundle.setMessage(rect.toJson());
                        drawCanvas.drawRect(startingX, startingY, endX, endY, drawPaint);
                        break;
                    case "Ellipsis":
                        // TODO : new Ellipsis(), send it to server
                        double radius = Math.sqrt(Math.pow(startingX-endX,2)+Math.pow(startingY-endY,2));
                        CircleShape circle = new CircleShape(startingX, startingY, (int) radius, (int) radius, 0.02f, color, fill);

                        ahttpr = new AsyncHttpRequester();
                        bundle = new HttpBundle();
                        bundle.setMethod("POST");
                        bundle.setQueueName(boardName);
                        bundle.setAuthor(author);
                        bundle.setMessage(circle.toJson());

                        shapes.add(circle);
                        drawCanvas.drawCircle(startingX, startingY, (int) radius, drawPaint);
                        break;
                    default:
                        Log.v(getClass().getName(), "Unknown tool");
                }
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    public void setColor(String newColor) {
        invalidate();
        paintColor = Color.parseColor(newColor);
        drawPaint.setColor(paintColor);
    }

    public void startNew(){
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }

    public void addShape(String output) throws JSONException {
        shapes.add(AbstractShape.fromJson(output));
    }
}