package dhianadini.aplikasiresep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Feedback extends AppCompatActivity {
    public static final String FOOD_NAME = "net.simplifiedcoding.firebasedatabaseexample.foodname";
    public static final String FOOD_ID = "net.simplifiedcoding.firebasedatabaseexample.foodid";

    EditText editTextFeedback;
    Button buttonAddFeedback;

    //a list to store all the feedbackfood from firebase database / table name
    List<DBFeedback> feedbackfood;

    //our database reference object
    DatabaseReference feedbackresep; //nama db

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
//getting the reference of feedback food node
        feedbackresep = FirebaseDatabase.getInstance().getReference("feedbackfood"); //nama tabel

        //getting views
        editTextFeedback = (EditText) findViewById(R.id.editTextFeedback);
        buttonAddFeedback = (Button) findViewById(R.id.buttonAddFeedback);

        //list to store feedback food
        feedbackfood = new ArrayList<>();

        //adding an onclicklistener to button
        buttonAddFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the method addArtist()
                //the method is defined below
                //this method is actually performing the write operation
                addFeedbackFood();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        feedbackresep.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                feedbackfood.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    DBFeedback ffood = postSnapshot.getValue(DBFeedback.class);
                    //adding artist to the list
                    feedbackfood.add(ffood);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addFeedbackFood() {
        //getting the values to save
        String food = editTextFeedback.getText().toString().trim();
        ;

        //checking if the value is provided
        if (!TextUtils.isEmpty(food)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = feedbackresep.push().getKey();

            //creating an Artist Object
            DBFeedback feedbackFood = new DBFeedback(id, food);

            //Saving the Artist
            feedbackresep.child(id).setValue(feedbackFood);

            //setting edittext to blank again
            editTextFeedback.setText("");

            //displaying a success toast
            Toast.makeText(this, "Feedback food added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a feedback", Toast.LENGTH_LONG).show();
        }
    }
}
