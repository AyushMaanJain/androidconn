package com.sm.realonekart.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.sm.realonekart.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private static int targetFragment=R.layout.fragment_login;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
            switch (index){
                case 1:targetFragment=R.layout.fragment_login;
                break;
                case 2:targetFragment=R.layout.fragment_signup;
            }
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        //signup request:?username="+user+"&email="+email+"&password="+pass+"&woocommerce-register-nonce=d6a34824c0&_wp_http_referer=%2Fmy-account%2F&register=Register
        View root = inflater.inflate(targetFragment, container, false);
        if(targetFragment==R.layout.fragment_signup){
            EditText email=root.findViewById(R.id.email);
            EditText user=root.findViewById(R.id.user);
            EditText pass=root.findViewById(R.id.password);
            TextView button=root.findViewById(R.id.register);

            /*GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();
            GoogleSignInClient googleClient = GoogleSignIn.getClient(getContext(), gso);
            GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(getContext());*/

            if(email==null||pass==null||user==null){
                Log.e("?????????????/",email.toString()+"::"+pass.toString()+"::"+user.toString());
            }

        }
        else if(targetFragment==R.layout.fragment_login){
            //login request: username="+email+"&password="+pass+"&woocommerce-login-nonce=3cc2c4f6d5&_wp_http_referer=%2F&login=Log%20in
            TextView button=root.findViewById(R.id.log_in);
            EditText email=root.findViewById(R.id.email);
            EditText pass=root.findViewById(R.id.password);
            if(email==null||pass==null){
                Log.e("?????????????/",email.toString()+"::"+pass.toString());
            }


            SignInButton signInButton = root.findViewById(R.id.sign_in_button);
            signInButton.setSize(SignInButton.SIZE_STANDARD);
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();
// Build a GoogleSignInClient with the options specified by gso.
            GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);
            signInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                    Log.e("93 placeholeder","sign in requested");

                    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                    startActivityForResult(signInIntent, 1001);}
                    catch (Exception e){
                        Log.e("placeholder-98-exception",e.toString());
                    }
                }
            });

        }
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);


            if (requestCode == 1001) {
                Log.e("placeholder-109", "working");
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                Log.e("result 120 placeholder","is success?:"+task.isSuccessful());
                GoogleSignInAccount acc=task.getResult(ApiException.class);
            }
        }catch (ApiException e){

            Log.e("Sign in failed code",e.getStatusCode()+"");
        }
    }
    /*
    * <form method="post" class="woocommerce-form woocommerce-form-register register">
            <input type="text" class="woocommerce-Input woocommerce-Input--text input-text" name="username" id="reg_username" autocomplete="username" value="ayushjain1133@gmail.com">						</p>
            <input type="email" class="woocommerce-Input woocommerce-Input--text input-text" name="email" id="reg_email" autocomplete="email" value="ayushjain1133@gmail.com">					</p>
            <input type="password" class="woocommerce-Input woocommerce-Input--text input-text" name="password" id="reg_password" autocomplete="new-password" aria-autocomplete="list"><div class="woocommerce-password-strength strong" aria-live="polite">Strong</div>
			<input type="hidden" id="woocommerce-register-nonce" name="woocommerce-register-nonce" value="d6a34824c0">
			<input type="hidden" name="_wp_http_referer" value="/">
			<button type="submit" class="woocommerce-Button woocommerce-button button woocommerce-form-register__submit" name="register" value="Register">Register</button>
    */

    /*logged in
    <div class="header-button"> <a href="https://realonkart.com/my-account/"
class="account-link */
    /*not logged in
<div class="header-button"> <a href="https://realonkart.com/my-account/"
class="nav-top-link*/
}