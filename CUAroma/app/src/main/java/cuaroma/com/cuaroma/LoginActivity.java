package cuaroma.com.cuaroma;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignin;
    private TextView textViewSignup;
    private TextView textViewForgotPassword;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth= FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(), MenuActivity.class));
        }

        progressDialog = new ProgressDialog(this);

        buttonSignin = (Button) findViewById(R.id.btn_login);
        editTextEmail = (EditText) findViewById(R.id.input_email);
        editTextPassword = (EditText) findViewById(R.id.input_password);
        textViewSignup = (TextView) findViewById(R.id.link_signup);
        textViewForgotPassword = (TextView) findViewById(R.id.link_forgotpassword);

        buttonSignin.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
        textViewForgotPassword.setOnClickListener(this);
    }
    private void validateLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        //checking if email and passwords are empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Logging in..Please Wait");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    finish();
                    startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Login in Error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    public void onClick(View view) {
        if (view==buttonSignin){
            validateLogin();
        }
        if (view==textViewSignup){
            startActivity(new Intent(this, SignupActivity.class));
        }
        if (view==textViewForgotPassword){
            startActivity(new Intent(this, ResetPasswordActivity.class));
        }
    }
}
