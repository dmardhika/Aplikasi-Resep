package dhianadini.aplikasiresep;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Food extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchfeedback = new Intent(Food.this, Feedback.class);
                startActivity(launchfeedback);
//                Toast.makeText(getBaseContext(), "Enter your feedback", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
