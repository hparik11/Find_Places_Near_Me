package com.mad.project.team3.places_near_me;

/**
 * Created by shiv on 4/27/2016.
 */
public class User_Attribute {

    private String key;
    private String value;

    public User_Attribute(String key,String value){
        this.key=key;
        this.value=value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
