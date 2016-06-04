package com.example.gallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.servercode.BackendlessConfig;

import android.app.*;

public class Registration extends AppCompatActivity {
	AppCompatButton signupBtn;
	AppCompatTextView loginLink;
	AppCompatEditText textEmail;
	AppCompatEditText textPass;
	AppCompatEditText textName;
	private static final String TAG = "SignupActivity";
	private static final String APP_ID = "Gallery";
	private static final String SECRET_KEY = "4CE788A4-D73A-9391-FF8B-ED8968C5B400";
	String appVersion = "v1";

	String email, name, pass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		signupBtn = (AppCompatButton) findViewById(R.id.btn_signup);
		loginLink = (AppCompatTextView) findViewById(R.id.link_login);

		textEmail = (AppCompatEditText) findViewById(R.id.emailReg);
		textPass = (AppCompatEditText) findViewById(R.id.PassReg);
		textName = (AppCompatEditText) findViewById(R.id.nameReg);

		signupBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				signUp();
			}

		});

		loginLink.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void signUp() {
		Log.d(TAG, "SignUp");

		if (!validate()) {
			onSignUpFailed();
			return;
		}
		signupBtn.setEnabled(false);

		final ProgressDialog progressDialog = new ProgressDialog(Registration.this, R.style.Animation_AppCompat_Dialog);
		progressDialog.setIndeterminate(true);
		progressDialog.setMessage("..." + "در حال ساختن حساب کاربری");
		progressDialog.show();

		
		new android.os.Handler().postDelayed(new Runnable() {
			public void run() {
				// On complete call either onSignupSuccess or onSignupFailed
				long sucess = storeData();
				
				if(sucess!= -1){
				// depending on success
				progressDialog.dismiss();
				onSignupSuccess();
				
				// onSignupFailed();
				}
				else{
					onSignUpFailed();
				}
			}
		}, 3000);
	}

	private long storeData() {
		Database db = new Database(getApplicationContext());
		User user = new User();
		name = textName.getText().toString();
		email = textEmail.getText().toString();
		pass = textPass.getText().toString();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(pass);
		long id =db.insertUser(user);
		Log.d(this.getLocalClassName(), "data stored successfully");
		return id;

	}

	public void onSignupSuccess() {
		signupBtn.setEnabled(true);
		
		setResult(RESULT_OK, null);
		finish();
	}

	public void onSignUpFailed() {
		Toast.makeText(getBaseContext(), "ثبت نام نا موفق", Toast.LENGTH_LONG).show();
		signupBtn.setEnabled(true);
	}

	public boolean validate() {

		boolean valid = true;

		name = textName.getText().toString();
		email = textEmail.getText().toString();
		pass = textPass.getText().toString();

		if (name.isEmpty() || name.length() < 3) {
			textName.setError("حد اقل 3 کاراکتر وارد شود");
			valid = false;

		} else {
			textName.setError(null);
		}

		if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
			textEmail.setError("ایمیل معتبر وارد کنید");
			valid = false;
		} else {
			textEmail.setError(null);

		}
		if (pass.isEmpty() || pass.length() < 6 || pass.length() > 10) {
			textPass.setError("رمز باید حداقل 6 و حداکثر 10 کاراکتر باشد");
			valid = false;
		} else {
			textPass.setError(null);
		}

		return valid;
	}

}
