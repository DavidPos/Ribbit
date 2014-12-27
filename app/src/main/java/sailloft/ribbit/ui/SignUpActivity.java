package sailloft.ribbit.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import sailloft.ribbit.R;


public class SignUpActivity extends Activity {
    protected EditText mUsername;
    protected EditText mPassword;
    protected EditText mEmail;
    protected Button mSignUpButton;
    protected Button mCancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ActionBar actionBar = getActionBar();
        actionBar.hide();

        mUsername = (EditText)findViewById(R.id.usernameField);
        mPassword = (EditText)findViewById(R.id.passwordField);
        mEmail = (EditText)findViewById(R.id.emailField);

        mCancelButton = (Button)findViewById(R.id.cancelButton);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mSignUpButton = (Button)findViewById(R.id.signupButton);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String username = mUsername.getText().toString();
             String password = mPassword.getText().toString();
             String email = mEmail.getText().toString();

             username.trim();
             password.trim();
             email.trim();

             if (username.isEmpty() || password.isEmpty() || email.isEmpty()){
                 AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                 builder.setMessage(R.string.sign_up_error_message)
                        .setTitle(R.string.sign_up_error_title)
                        .setPositiveButton(android.R.string.ok, null);
                 AlertDialog dialog = builder.create();
                 dialog.show();
             }
             else {
                 //create new user
                 ParseUser newUser = new  ParseUser();
                 newUser.setUsername(username);
                 newUser.setPassword(password);
                 newUser.setEmail(email);
                 newUser.signUpInBackground(new SignUpCallback() {
                     @Override
                     public void done(ParseException e) {
                         if (e == null){
                             //Success
                             Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                             intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                             intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                             startActivity(intent);

                         }
                         else {
                             AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                             builder.setMessage(e.getMessage())
                                     .setTitle(R.string.sign_up_error_title)
                                     .setPositiveButton(android.R.string.ok, null);
                             AlertDialog dialog = builder.create();
                             dialog.show();
                         }
                     }
                 });

             }
            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}