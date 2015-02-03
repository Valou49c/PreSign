package test.connect.bdd;

import org.json.JSONObject;

/**
 * Created by Valentin on 03/02/2015.
 */
public class User {

    public String username;
    public String userpassword;


    public User(String username, String userpassword) {
        super();
        this.username = username;
        this.userpassword = userpassword;
    }


    public User(JSONObject jsonObject) throws Exception {

        if (jsonObject.get("loginStatus").equals("yes")) {
            this.username = jsonObject.getString("username");
            this.userpassword = jsonObject.getString("userpassword");
        }


    }
}





