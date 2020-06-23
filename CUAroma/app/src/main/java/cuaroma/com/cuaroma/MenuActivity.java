package cuaroma.com.cuaroma;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import static cuaroma.com.cuaroma.R.*;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView mFoodList;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_menu);

        mFoodList = (RecyclerView) findViewById(id.foodList);
        mFoodList.setHasFixedSize(true);
        mFoodList.setLayoutManager(new LinearLayoutManager(this));
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Item");
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()==null){
                    Intent loginIntent = new Intent(MenuActivity.this,LoginActivity.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(loginIntent);
                }
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mmenuInflater = getMenuInflater();
        mmenuInflater.inflate(R.menu.mymenu,menu);
        return true;

    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case id.action_logout:
                firebaseAuth.signOut();
                Intent intent = new Intent(MenuActivity.this,SignupActivity.class);
                startActivity(intent);
            case id.aboutus:
                startActivity(new Intent(MenuActivity.this,AboutUs.class));
        }

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
        FirebaseRecyclerAdapter <Food,FoodViewHolder> FBRA = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(
                Food.class,
                layout.singlemenuitem,
                FoodViewHolder.class,
                databaseReference
        ) {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, Food model, int position) {
                viewHolder.setName(model.getName());
                viewHolder.setPrice(model.getPrice());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(getApplicationContext(),model.getImage());

                final String food_key = getRef(position).getKey().toString();
                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent singleFoodActivity = new Intent(MenuActivity.this,SingleFoodActivity.class);
                        singleFoodActivity.putExtra("FoodId",food_key);
                        startActivity(singleFoodActivity);
                    }
                });
            }
        };

        mFoodList.setAdapter(FBRA);

    }
    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        View mview;

        public FoodViewHolder(View itemView) {
            super(itemView);
            mview=itemView;
        }

        public void setName(String name){
            TextView food_name = (TextView) mview.findViewById(id.foodName);
            food_name.setText(name);
        }
        public void setPrice(String price){
            TextView food_price = (TextView) mview.findViewById(id.foodPrice);
            food_price.setText(price);
        }
        public void setDesc(String desc){
            TextView food_desc = (TextView) mview.findViewById(id.foodDesc);
            food_desc.setText(desc);
        }
        public void setImage(Context context,String image){
            ImageView food_image =(ImageView) mview.findViewById(id.foodImage);
            Picasso.with(context).load(image).into(food_image);
        }

    }
}