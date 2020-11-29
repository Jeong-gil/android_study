package com.example.adjspproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import Network.JoinAction;
import Network.LoginAction;

public class LoginActivity extends AppCompatActivity {

    Button btn_login, btn_join;
    TextInputEditText edit_id, edit_pwd;
    AlertDialog.Builder dialog_join;

    // 마지막으로 뒤로가기 버튼을 눌렀던 시간 저장
    private long backKeyPressedTime = 0;
    // 첫 번째 뒤로가기 버튼을 누를때 표시
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edit_id = (TextInputEditText) findViewById(R.id.edit_id);
        edit_pwd = (TextInputEditText) findViewById(R.id.edit_pwd);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = edit_id.getText().toString().trim();
                String userPassword = edit_pwd.getText().toString().trim();

                new LoginAction(LoginActivity.this).execute(userID, userPassword);
            }
        });


        btn_join = (Button) findViewById(R.id.btn_join);
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = getLayoutInflater().inflate(R.layout.dialog_join,null);
                Button btnJoin = (Button) view.findViewById(R.id.btnJoin);

                dialog_join = new AlertDialog.Builder(LoginActivity.this);
                dialog_join
                        .setTitle("회원가입")
                        .setView(view)
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setCancelable(false)
                        .show();

                btnJoin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String userID = ((EditText)view.findViewById(R.id.edtUserID)).getText().toString().trim();
                        String userPassword = ((EditText)view.findViewById(R.id.edtUserPassword)).getText().toString().trim();
                        String userName = ((EditText)view.findViewById(R.id.edtUserName)).getText().toString().trim();
                        String userEmail = ((EditText)view.findViewById(R.id.edtUserEmail)).getText().toString().trim();
                        String userGender = "";
                        int genderOk = -1;
                        RadioGroup rgUserGender = (RadioGroup) view.findViewById(R.id.rgUserGender);
                        switch (rgUserGender.getCheckedRadioButtonId()) {
                            case R.id.rbUserGenderM:
                                userGender = "남자";
                                genderOk = 1;
                                break;
                            case R.id.rbUserGenderW:
                                userGender = "여자";
                                genderOk = 1;
                                break;
                            default:
                                System.out.println("성별을 선택하세요");
                                Toast.makeText(LoginActivity.this, "성별을 선택하세요", Toast.LENGTH_SHORT).show();
                        }

                        if (genderOk == 1) {
                            System.out.println("userID : " + userID);
                            System.out.println("userPassword : " + userPassword);
                            System.out.println("userName : " + userName);
                            System.out.println("userEmail : " + userEmail);
                            System.out.println("userGender : " + userGender);
                            System.out.println("회원가입실행");

                            new JoinAction(LoginActivity.this, dialog_join).execute(userID, userPassword, userName, userEmail, userGender);
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        // 기존 뒤로가기 버튼의 기능을 막기위해 주석처리 또는 삭제
        // super.onBackPressed();

        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지났으면 Toast Show
        // 2000 milliseconds = 2 seconds
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지나지 않았으면 종료
        // 현재 표시된 Toast 취소
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            toast.cancel();
            ActivityCompat.finishAffinity(this);
            System.exit(0);

        }
    }
}