package com.example.steven.tamtam.Httprequester;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by steven on 5/30/16.
 */
public class HttpParamManager {
    List<HttpParam> params = new ArrayList<>();

    public void setParam(HttpParam param) {
        this.params.add(param);
    }

    public String toHttpBody() throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        for (HttpParam p : this.params
             ) {
            result.append(URLEncoder.encode(p.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(p.getValue(), "UTF-8"));
            result.append("&");
        }
        return result.toString();
    }

}
