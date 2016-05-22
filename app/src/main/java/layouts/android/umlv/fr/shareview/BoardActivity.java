package layouts.android.umlv.fr.shareview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class BoardActivity extends AppCompatActivity {
    private String selectedTool = "Line";
    private DrawingView canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
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

}
