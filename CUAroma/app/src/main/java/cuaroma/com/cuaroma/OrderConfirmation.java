package cuaroma.com.cuaroma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OrderConfirmation extends AppCompatActivity implements View.OnClickListener{

    private Button buttonGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);
        buttonGoBack = (Button) findViewById(R.id.GoBack);
        buttonGoBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==buttonGoBack){
            finish();
            startActivity(new Intent(OrderConfirmation.this,MenuActivity.class));
        }
    }
}
