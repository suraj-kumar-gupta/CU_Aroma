package cuaroma.com.cuaroma;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class SingleFoodActivity extends AppCompatActivity implements View.OnClickListener {

    private String food_key = null;
    private String food_name, food_price, food_desc, food_image;
    private DatabaseReference databaseReference;
    private DatabaseReference userData;
    private TextView singleFoodTitle;
    private TextView singleFoodDesc;
    private TextView singleFoodPrice;
    private TextView textViewQuantity;
    private ImageView singleFoodImage;
    private Button orderButton;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser current_user;
    private Button buttonDecrement;
    private Button buttonIncrement;
    private DatabaseReference mref;

    int integer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_food);

        food_key = getIntent().getExtras().getString("FoodId");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Item");

        singleFoodTitle = (TextView) findViewById(R.id.singleTitle);
        singleFoodDesc = (TextView) findViewById(R.id.singleDesc);
        singleFoodPrice = (TextView) findViewById(R.id.singlePrice);
        singleFoodImage = (ImageView) findViewById(R.id.singleImageView);
        orderButton = (Button) findViewById(R.id.orderItem);
        textViewQuantity = (TextView) findViewById(R.id.quantity);
        buttonIncrement = (Button) findViewById(R.id.increment);
        buttonDecrement = (Button) findViewById(R.id.decrement);
        textViewQuantity = (TextView) findViewById(R.id.quantity);

        buttonDecrement.setOnClickListener(this);
        buttonIncrement.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        current_user = firebaseAuth.getCurrentUser();
        userData = FirebaseDatabase.getInstance().getReference().child("Users").child(current_user.getUid());
        mref = FirebaseDatabase.getInstance().getReference().child("Orders");

        databaseReference.child(food_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                food_name = (String) dataSnapshot.child("name").getValue();
                food_price = (String) dataSnapshot.child("price").getValue();
                food_desc = (String) dataSnapshot.child("desc").getValue();
                food_image = (String) dataSnapshot.child("image").getValue();

                singleFoodTitle.setText(food_name);
                singleFoodDesc.setText(food_desc);
                singleFoodPrice.setText(food_price);
                Picasso.with(SingleFoodActivity.this).load(food_image).into(singleFoodImage);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void OrderItem(View view) {
        final DatabaseReference newOrder = mref.push();
        userData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                newOrder.child("itemname").setValue(food_name);
                newOrder.child("contactno").setValue(dataSnapshot.child("phone").getValue());
                newOrder.child("username").setValue(dataSnapshot.child("name").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(new Intent(SingleFoodActivity.this, OrderConfirmation.class));
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view == buttonDecrement){
            decrement();
        }

        if (view==buttonIncrement){
            increment();
        }

    }

    public void display(int number){
        textViewQuantity = (TextView)findViewById(R.id.quantity);
        textViewQuantity.setText("" + number);
    }

    public void increment() {
        integer = integer + 1;
        display(integer);
    }

    public void decrement() {
        integer = integer - 1;
        if (integer<=0){
            Toast.makeText(this,"Please order atleast 1 ",Toast.LENGTH_LONG).show();
        }
        display(integer);
    }
}