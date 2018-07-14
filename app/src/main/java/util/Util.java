package util;


import model.Friend;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static List JSONArrayStringtoList(String s) throws JSONException {
        List<Friend> list =new ArrayList<>();
        JSONArray jsonArray=null;
        jsonArray= new JSONArray(s);
        for (int i=0;i<jsonArray.length();i++){
            JSONObject jobj =jsonArray.getJSONObject(i);
            Friend friend=new Friend();
            friend.setId(jobj.getLong("id"));
            friend.setName(jobj.getString("name"));
            friend.setPhoneNum(jobj.getString("phoneNum"));
            friend.setPwd(jobj.getString("pwd"));
            friend.setSex(jobj.getString("sex"));
            friend.setStuId(jobj.getString("stuId"));

            list.add(friend);
        }

        return list;
    }
}
