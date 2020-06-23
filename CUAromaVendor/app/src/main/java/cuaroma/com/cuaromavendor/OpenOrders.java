package cuaroma.com.cuaromavendor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class OpenOrders extends AppCompatActivity  {
    private RecyclerView mFoodList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_orders);
        mFoodList = (RecyclerView) findViewById(R.id.orderLayout);
        mFoodList.setHasFixedSize(true);
        mFoodList.setLayoutManager(new LinearLayoutManager(this));
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Orders");
    }
    protected void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<Order,OrderViewHolder> FBRA = new FirebaseRecyclerAdapter<Order, OrderViewHolder>(
                Order.class,
                R.layout.singleorderlayout,
                OrderViewHolder.class,
                databaseReference
        ) {
            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, Order model, int position) {
                viewHolder.setUserName(model.getUsername());
                viewHolder.setitemName(model.getItemname());
                viewHolder.setContactNo(model.getContactno());

                final String food_key = getRef(position).getKey().toString();
                viewHolder.orderView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent orderConfirmIntent = new Intent(OpenOrders.this,OrderConfirmation.class);
                        orderConfirmIntent.putExtra("Food_Id",food_key);
                        startActivity(orderConfirmIntent);
                    }
                });
            }
        };
        mFoodList.setAdapter(FBRA);
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder{

        View orderView;
        public OrderViewHolder(View itemView) {
            super(itemView);
            orderView = itemView;
        }

        public void setUserName(String username){
            TextView username_content = (TextView) orderView.findViewById(R.id.orderUserName);
            username_content.setText(username);
        }
        public void setitemName(String itemname){
            TextView itemname_content = (TextView) orderView.findViewById(R.id.orderItemName);
            itemname_content.setText(itemname);
        }
        public void setContactNo(String contactNo){
            TextView contactno_content = (TextView) orderView.findViewById(R.id.orderContactNo);
            contactno_content.setText(contactNo);
        }

        public void setQuantity(String quantity){
            TextView quantity_content = (TextView) orderView.findViewById(R.id.orderQuantity);
            quantity_content.setText(quantity);
        }
    }
}