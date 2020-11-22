package Network;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.adjspproject.MenuActivity;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginAction extends AsyncTask<String,Void,String> {

    private URL Url;
    private String URL_Adress = "http://아이피주소:8090/adJspProject/loginAction.jsp";
    private Context context;

    public LoginAction(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {

        String res = "";

        try {
            Url = new URL(URL_Adress);
            HttpURLConnection conn = (HttpURLConnection)Url.openConnection();

            //전송모드 설정
            conn.setDefaultUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");

            //content-type 설정
            conn.setRequestProperty("Content-type","application/x-www-form-urlencoded; charset=utf-8");

            //전송값 설정
            StringBuffer buffer = new StringBuffer();
            buffer.append("userID").append("=").append(strings[0]);
            buffer.append("&userPassword").append("=").append(strings[1]);

            //서버로 전송
            OutputStreamWriter outStream = new OutputStreamWriter(conn.getOutputStream(),"utf-8");
            PrintWriter writer = new PrintWriter(outStream);
            writer.write(buffer.toString());
            writer.flush();
            
            // 응답 받기
            StringBuilder builder = new StringBuilder();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));

            String line;

            while ((line = in.readLine()) != null) {
                builder.append(line + "\n");
            }

            res = builder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        Log.i("Get result",res);
        return res;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        int res = 0;

        try {
            res = JsonParser.getResultJson(s);
        } catch (JSONException e){
            e.printStackTrace();
        }

        if (res == 1) {
            Intent intent = new Intent(context, MenuActivity.class);
            context.startActivity(intent);
        } else if (res == 0) {
            Toast.makeText(context, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
        } else if (res == -1) {
            Toast.makeText(context, "존재하지 않는 아이디입니다", Toast.LENGTH_SHORT).show();
        } else if (res == -2) {
            Toast.makeText(context, "DB서버 오류 발생", Toast.LENGTH_SHORT).show();
        }
    }
}