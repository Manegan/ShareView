package layouts.android.umlv.fr.shareview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void newGame(View v) {
        Intent intent = new Intent(this, BoardActivity.class);
        intent.putExtra("state", "new");
        startActivity(intent);
    }

    public void loadGame(View v) {
        Intent intent = new Intent(this, BoardActivity.class);
        intent.putExtra("state", "join");
        startActivity(intent);
    }


}
