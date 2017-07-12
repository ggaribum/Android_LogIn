package com.onetwopunch.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.onetwopunch.helloworld.BaseActivity.dbmanager;

public class JoinActivity extends AppCompatActivity {

    EditText et_Name;
    EditText et_Id;
    EditText et_Pw;
    EditText et_Pw2;
    EditText et_Email;

    Button bt_IDcheck;
    Button bt_Return;
    Button bt_Join;

    boolean idFlag=false;
    boolean pwFlag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        final Exception exceptionManager = new Exception();

        //데이터베이스 출력 연습용 !
        bt_Join =(Button) findViewById(R.id.bt_Join);
        bt_IDcheck=(Button) findViewById(R.id.bt_IDcheck);
        bt_Return=(Button) findViewById(R.id.bt_Return);

        et_Name =(EditText) findViewById(R.id.et_Name);
        et_Id =(EditText) findViewById(R.id.et_Id);
        et_Pw =(EditText) findViewById(R.id.et_Pw);
        et_Pw2 =(EditText) findViewById(R.id.et_Pw2);
        et_Email =(EditText) findViewById(R.id.et_Email);

        //return 버튼 눌렀을 시 메인화면으로 돌아가기.
        bt_Return.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JoinActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        //Join버튼 누르면 회원가입과 동시에(예외발생 안한다면) 데이터베이스에 저장.
        bt_Join.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                String name=et_Name.getText().toString();
                String id=et_Id.getText().toString();
                String pw=et_Pw.getText().toString();
                String pw2=et_Pw2.getText().toString();
                String email=et_Email.getText().toString();

                //양식이 맞는지 검사 Q.★☆★☆질문!! : 메서드 종료시키듯이(return) 하는 방법은 ?? ★☆★☆
                if(idFlag==false){
                    Toast.makeText(JoinActivity.this,"Check your ID !",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                pwFlag=exceptionManager.pwCheck(pw,pw2);
                if(pwFlag==false)
                {
                    Toast.makeText(JoinActivity.this,"The passwords are not correspond !!.",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                if(idFlag&&pwFlag) //id와pw에 문제가 없다면 == 둘다 true 라면 데이터베이스에 담기.
                {
                    dbmanager.insert(name, id, pw, email);
                    /*String result = "";
                    result = dbmanager.getResult();//데이터베이스 모든 자료 출력하기 위한 부분*/

                    Toast.makeText(getApplicationContext(), "Welcom to 상남자 club", Toast.LENGTH_SHORT).show();
                    onStop();
                    Intent intent = new Intent(JoinActivity.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        });

        bt_IDcheck.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(exceptionManager.idCheck(et_Id.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"It's okay",Toast.LENGTH_SHORT).show();
                    idFlag=true;
                }
                else {
                    Toast.makeText(getApplicationContext(), "It's duplicated", Toast.LENGTH_SHORT).show();
                    idFlag = false;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        et_Name.setText("");
        et_Id.setText("");
        et_Pw.setText("");
        et_Pw2.setText("");
        et_Email.setText("");
    }
}
