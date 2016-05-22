package layouts.android.umlv.fr.shareview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.json.JSONException;

public class BoardActivity extends AppCompatActivity implements AsyncResponse {
    private String boardName = "default";
    private int currentId = 0;
    private AsyncHttpRequester ahttpr = new AsyncHttpRequester();
    private String selectedTool = "Line";
    private DrawingView canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        ahttpr.delegate = this;

        HttpBundle bundle = new HttpBundle();
        bundle.setQueueName(boardName);
        bundle.setId(Integer.toString(currentId));
        bundle.setMethod("GET");

        ahttpr.execute(bundle);
    }

    @Override
    protected void onStart() {
        super.onStart();
        canvas = (DrawingView) findViewById(R.id.canvas);

    }

    public void changeCursorLine(View view) {
        selectedTool = "Line";
        changeTool(selectedTool);
    }

    public void changeCursorRectangle(View view) {
        selectedTool = "Rectangle";
        changeTool(selectedTool);
    }

    public void changeCursorEllipsis(View view) {
        selectedTool = "Ellipsis";
        changeTool(selectedTool);
    }

    private void changeTool(String toolName) {
        canvas.setSelectedTool(toolName);
    }

    @Override
    public void processFinish(String output) {
        if (output != null && output != "") {
            try {
                currentId++;
                canvas.addShape(output);
            } catch (JSONException e) {

            }
        }

        ahttpr = new AsyncHttpRequester();
        ahttpr.delegate = this;

        HttpBundle bundle = new HttpBundle();
        bundle.setQueueName(boardName);
        bundle.setId(Integer.toString(currentId));
        bundle.setMethod("GET");

        ahttpr.execute(bundle);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ahttpr.cancel(true);
    }
}