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

public class DrawingView extends View{
    private String selectedTool;
    private Paint drawPaint, canvasPaint;
    private int paintColor = 0xFF660000;
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;
    private int startingX = 0;
    private int startingY = 0;


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

        canvasBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
        selectedTool = "Line";
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startingX = Math.round(touchX);
                startingY = Math.round(touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                // Do things for polyline, but eh, do we realize this feature ?
                break;
            case MotionEvent.ACTION_UP:
                Log.v(getClass().getName(), "Drawing something with : "+ selectedTool);
                switch (selectedTool) {
                    case "Line":
                        // TODO : new Line(), send it to server
                        drawCanvas.drawLine(startingX, startingY, touchX, touchY,drawPaint);
                        break;
                    case "Rectangle":
                        // TODO : new Rectangle(), send it to server
                        drawCanvas.clipRect(startingX, startingY, touchX, touchY);
                        break;
                    case "Ellipsis":
                        // TODO : new Ellipsis(), send it to server
                        int radius = 10; // TODO : compute radius with touchX and touchY
                        drawCanvas.drawCircle(startingX, startingY, radius, drawPaint);
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

}