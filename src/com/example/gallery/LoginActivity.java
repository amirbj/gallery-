package com.example.gallery;

import com.backendless.Backendless;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
	AppCompatButton loginBtn;
	AppCompatTextView signupLink;
	private static final int REQUEST_SIGNUP = 0;
	
	AppCompatEditText text_pass;
	AppCompatEditText text_email;
	private String email, password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.login);
		loginBtn = (AppCompatButton) findViewById(R.id.btn_login);
		signupLink = (AppCompatTextView) findViewById(R.id.link_signup);

		 text_email = (AppCompatEditText) findViewById(R.id.input_email);
		 text_pass = (AppCompatEditText) findViewById(R.id.input_pass);


		loginBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				login();

			}

		
		});

		signupLink.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), Registration.class);
				startActivity(intent);

			}

		});
	}
	
	private void login(){
		
		 if (!validate()) {
	            onLoginFailed();
	            return;
	        }

	        loginBtn.setEnabled(false);

	        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
	                R.style.AppTheme);
	        progressDialog.setIndeterminate(true);
	        progressDialog.setMessage("برسی هویت");
	        progressDialog.show();

	        //email = text_email.getText().toString();
	       // password = text_pass.getText().toString();

	        // TODO: Implement your own authentication logic here.

	        new android.os.Handler().postDelayed(
	                new Runnable() {
	                    public void run() {
	                    	Database db = new Database(getApplicationContext());
	                    	if(db.authenticate(email, password)){
	                        // On complete call either onLoginSuccess or onLoginFailed
	                        onLoginSuccess();}
	                    	else{
	                        // onLoginFailed();
	                        progressDialog.dismiss();
	                        Toast.makeText(getApplicationContext(), "رمز عبور یا ایمیل اشتباهاست" , Toast.LENGTH_LONG).show();
	                    	}
	                    }
	                }, 3000);
	    }

	

	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (requestCode == REQUEST_SIGNUP) {
	            if (resultCode == RESULT_OK) {

	                // TODO: Implement successful signup logic here
	                // By default we just finish the Activity and log them in automatically
	                this.finish();
	            }
	        }
	    }

	    @Override
	    public void onBackPressed() {
	        // disable going back to the MainActivity
	        moveTaskToBack(true);
	    }

	    public void onLoginSuccess() {
	        loginBtn.setEnabled(true);
	        finish();
	        Intent intent = new Intent(this, Main_Activity.class);
	        startActivity(intent);
	    }

	    public void onLoginFailed() {
	        Toast.makeText(getBaseContext(), "ورود ناموفق", Toast.LENGTH_LONG).show();

	        loginBtn.setEnabled(true);
	    }
		

	
	private boolean validate(){
		boolean valid = true;
		
		email = text_email.getText().toString();
		password = text_pass.getText().toString();
		
		if(email.isEmpty()|| !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
			text_email.setError("ایمیل واردشده معتبر نیست");
			valid = false;
		}
		else{
			text_email.setError(null);
		}
		
		if(password.isEmpty() || password.length()<6 || password.length()>10){
			text_pass.setError("رمز باید حداقل 6 و حداکثر 10 کاراکتر باشد");
			valid = false;
		}
		else{
			text_pass.setError(null);
		}
		
		return valid;
	}
}
