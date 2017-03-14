package com.example.algolabs.mqttapptest;

import android.content.Context;
import android.util.Log;



import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by algolabs on 22/2/17.
 */

public class PlatformJson extends BaseJson {//singleton class
    String mkey, mvalue;
    String mtext="";
    String mmap;




    private static PlatformJson obj;


    Map<String, String> prog = new HashMap<String, String>();

    private PlatformJson(Context context) {
        super(context, "platform.json");


    }


    private void platformJson() {



    }


    protected void platform() {   //fetch value from json array
        try {
            jsonObject = new JSONObject(m);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsonArray = jsonObject.optJSONArray("Data");
        int arraylength = jsonArray.length();

        for (int i = 0; i < arraylength; i++) {


            try {
                JSONObject jsonChildNode = jsonArray.getJSONObject(i);
                prog.put("com", jsonChildNode.getString("_comment"));
                prog.put("os", jsonChildNode.getString("os"));
                prog.put("phython", jsonChildNode.getString("python_ver"));
                prog.put("role", jsonChildNode.getString("role"));
                prog.put("board", jsonChildNode.getString("board_type"));
                prog.put("msg", jsonChildNode.getString("messaging"));
                writejson();
                hashData();
                Log.e("value", "data" + mvalue);


                ((HashMap<String, String>) prog).put("rohit:","yadav");//add new entry in original json file
                ((HashMap<String, String>) prog).put("algo:","labs"); //add new entry in json file
                mmap = new JSONObject(prog).toString(4);
                Log.e("plat","form"+(new JSONObject(prog).toString(4)));


                Log.d(prog.toString(), "tags");

            } catch (JSONException e) {
                e.printStackTrace();

            }

        }
    }



    protected void hashData() { //print value in an proper format (key, value)
        Set keys = prog.keySet();

        for (Iterator k = keys.iterator(); k.hasNext(); ) {
            mkey = (String) k.next();
            mvalue = prog.get(mkey);
            mvalue.toString();
            mtext += mkey + " = " + mvalue;
            Log.e("value", "value" + mtext);
        }

    }

    public static PlatformJson getInstance(Context ctx) {

        if (obj == null) {
            obj = new PlatformJson(ctx);
        }

        return obj;
    }
    protected void writejson(){  //remove and add new value in json file
        prog.remove("com");
        prog.put("com","mqttapptest");
    }
}

