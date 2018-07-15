package util;


import model.Friend;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Util {
    
    public final static String SERVERIP="192.168.31.228";

    public static List JSONArrayStringtoList(String s) throws JSONException {
        List<Friend> list = new ArrayList<>();
        JSONArray jsonArray = null;
        jsonArray = new JSONArray(s);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jobj = jsonArray.getJSONObject(i);
            Friend friend = new Friend();
            friend.setId(jobj.getLong("id"));
            friend.setName(jobj.getString("name"));
            friend.setPhoneNum(jobj.getString("phoneNum"));
            friend.setSex(jobj.getString("sex"));
            friend.setStuId(jobj.getString("stuId"));
            friend.setPwd(jobj.getString("pwd"));
            list.add(friend);
        }

        return list;
    }

    public static Friend string2Friend(String s){
        String[] ss=s.split(",");

        Friend friend = new Friend();
        friend.setId(Long.parseLong(ss[0]));
        friend.setName(ss[1]);
        friend.setPhoneNum(ss[2]);
        friend.setSex(ss[3]);
        friend.setStuId(ss[4]);
        friend.setPwd(ss[5]);

        return friend;
    }
}
