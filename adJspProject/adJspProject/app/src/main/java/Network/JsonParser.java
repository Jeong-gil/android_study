package Network;

import com.example.adjspproject.UserContents;
import com.example.adjspproject.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonParser {

    public static int getUserInfoJson(String response, ArrayList<UserInfo> userList) throws JSONException {

        String strID;
        String strName;
        String strPhone;
        String strGrade;
        String strWriteTime;

        JSONObject rootJSON = new JSONObject(response);
        JSONArray jsonArray = new JSONArray(rootJSON.getString("datas"));

        for (int i=0; i<jsonArray.length();i++) {

            JSONObject jsonObj = (JSONObject)jsonArray.get(i);
            if (jsonObj.getString("ID").toString().equals("null"))
                strID = "-";
            else
                strID = jsonObj.getString("ID").toString();

            if (jsonObj.getString("NAME").toString().equals("null"))
                strName = "-";
            else
                strName = jsonObj.getString("NAME").toString();

            if (jsonObj.getString("PHONE").toString().equals("null"))
                strPhone = "-";
            else
                strPhone=jsonObj.getString("PHONE").toString();

            if (jsonObj.getString("GRADE").toString().equals("null"))
                strGrade = "-";
            else
                strGrade = jsonObj.getString("GRADE").toString();

            if(jsonObj.getString("WRITE_TIME").toString().equals("null"))
                strWriteTime = "-";
            else {
                strWriteTime = jsonObj.getString("WRITE_TIME").toString();
                String temp[] = strWriteTime.split(" ");
                strWriteTime = temp[0] + "\n" + temp[1];
            }
            userList.add(new UserInfo(strID, strName, strPhone, strGrade, strWriteTime));
        }
        return jsonArray.length();
    }

    public static int getTitleJson(String response, ArrayList<UserContents> contentsList) throws JSONException {

        String strNo;
        String strUserID;
        String strTitle;
        String strContent;

        JSONObject rootJSON = new JSONObject(response);
        JSONArray jsonArray = new JSONArray(rootJSON.getString("datas"));

        for (int i=0; i < jsonArray.length(); i++) {

            JSONObject jsonObj = (JSONObject)jsonArray.get(i);

            if (jsonObj.getString("no").toString().equals("null"))
                strNo = "-";
            else {
                strNo = jsonObj.getString("no").toString();
            }

            if (jsonObj.getString("userID").toString().equals("null"))
                strUserID = "-";
            else
                strUserID = jsonObj.getString("userID").toString();

            if (jsonObj.getString("title").toString().equals("null"))
                strTitle = "-";
            else
                strTitle = jsonObj.getString("title").toString();

            if (jsonObj.getString("content").toString().equals("null"))
                strContent = "-";
            else
                strContent = jsonObj.getString("content").toString();

            contentsList.add(new UserContents(strNo, strUserID, strTitle, strContent));
        }
        return jsonArray.length();
    }

    public static String getContentJson(String response) throws JSONException {

        String strContent;

        JSONObject rootJSON = new JSONObject(response);
        JSONArray jsonArray = new JSONArray(rootJSON.getString("datas"));

        for (int i=0; i < jsonArray.length(); i++) {

            JSONObject jsonObj = (JSONObject)jsonArray.get(i);
            if (jsonObj.getString("content").toString().equals("null"))
                strContent = "-";
            else {
                strContent = jsonObj.getString("content").toString();
            }
            return strContent;
        }
        return "";
    }

    static public int getResultJson(String response) throws JSONException {

        JSONArray jsonArray = new JSONArray(response);
        JSONObject jsonObject = new JSONObject(jsonArray.getString(0));

        return Integer.parseInt(jsonObject.getString("RESULT_OK"));
    }
}
