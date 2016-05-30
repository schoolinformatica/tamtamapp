package com.example.steven.tamtam.Httprequester;

/**
 * Created by steven on 5/30/16.
 */
public class HttpParam {
    private String key;
    private String value;

    public HttpParam(String key, String value) {
        this.key = key;
        this.value = value;
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
