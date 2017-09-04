package Model;

import java.text.DateFormat;

/**
 * Created by Hesen on 9/4/2017.
 */

public class User {

    private String Uid;
    private String name;
    private String user_name;
    private String email;
    private String password;
    private int age;


    //getters


    public String getUid() {
        return Uid;
    }

    public String getName() {
        return name;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }


    // setters


    public void setUid(String uid) {
        Uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(String uid, String name, String user_name, String email, int age) {
        Uid = uid;
        this.name = name;
        this.user_name = user_name;
        this.email = email;
        this.password = password;
        this.age = age;
    }
}
