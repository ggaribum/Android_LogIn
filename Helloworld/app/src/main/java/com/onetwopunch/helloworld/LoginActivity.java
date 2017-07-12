package com.onetwopunch.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private Button CustomloginButton;
    private CallbackManager callbackManager;

    EditText et_logId;
    EditText et_logPw;
    Button bt_logReturn;
    Button bt_logIn;
    TextView findProfile;
    CheckBox checkBox;

    Exception exceptionManager = new Exception();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext()); // SDK 초기화 (setContentView 보다 먼저 실행되어야합니다. 안그럼 에러납니다.)
        setContentView(R.layout.activity_login);


        et_logId = (EditText) findViewById(R.id.et_logId);
        et_logPw = (EditText) findViewById(R.id.et_logPw);
        bt_logReturn=(Button) findViewById(R.id.bt_logReturn);
        bt_logIn=(Button) findViewById(R.id.bt_logIn);
        checkBox=(CheckBox) findViewById(R.id.checkBox);

        findProfile =(TextView) findViewById(R.id.findProfile);

        callbackManager = CallbackManager.Factory.create();  //로그인 응답을 처리할 콜백 관리자
        loginButton = (LoginButton)findViewById(R.id.buttonId); //페이스북 로그인 버튼
        //유저 정보, 친구정보, 이메일 정보등을 수집하기 위해서는 허가(퍼미션)를 받아야 합니다.
        loginButton.setReadPermissions("public_profile", "user_friends","email");
        //버튼에 바로 콜백을 등록하는 경우 LoginManager에 콜백을 등록하지 않아도됩니다.
        //반면에 커스텀으로 만든 버튼을 사용할 경우 아래보면 CustomloginButton OnClickListener안에 LoginManager를 이용해서
        //로그인 처리를 해주어야 합니다.
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) { //로그인 성공시 호출되는 메소드
                Log.e("토큰",loginResult.getAccessToken().getToken());
                Log.e("유저아이디",loginResult.getAccessToken().getUserId());
                Log.e("퍼미션 리스트",loginResult.getAccessToken().getPermissions()+"");

                //loginResult.getAccessToken() 정보를 가지고 유저 정보를 가져올수 있습니다.
                GraphRequest request =GraphRequest.newMeRequest(loginResult.getAccessToken() ,
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    Log.e("user profile",object.toString());
                                } catch (java.lang.Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                request.executeAsync();
            }

            @Override
            public void onError(FacebookException error) { }

            @Override
            public void onCancel() { }
        });

        bt_logIn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                String logID=et_logId.getText().toString();
                String logPW=et_logPw.getText().toString();
                String logResult=exceptionManager.logIn(logID,logPW);

                //여기서 이름만 아니라 그냥 arrayList로 넘겨받자.
                if(!logResult.equals("f"))
                {
                    Toast.makeText(getApplicationContext(),"Welcome 상남자 club, "+logResult+" bro !!!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),FunctionActivity.class);
                    startActivity(intent);
                    onResume();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Check your ID or PASSWORD",Toast.LENGTH_SHORT).show();
                    onResume();
                }
            }
        });

        bt_logReturn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        findProfile.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"클릭됨",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
