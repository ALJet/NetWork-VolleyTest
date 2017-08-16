package indi.aljet.network_volleytest.net;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import indi.aljet.network_volleytest.entity.Nodes;

/**
 * json返回为Node数值的数据
 * Created by PC-LJL on 2017/8/16.
 */


/**
 * json数据如下：
 *
 * [

 {
 "id" : 1,
 "name" : "babel",
 "url" : "http://www.v2ex.com/go/babel",
 "title" : "Project Babel",
 "title_alternative" : "Project Babel",
 "topics" : 1119,

 "header" : "Project Babel \u002D 帮助你在云平台上搭建自己的社区",


 "footer" : "V2EX 基于 Project Babel 驱动。Project Babel 是用 Python 语言写成的，运行于 Google App Engine 云计算平台上的社区软件。Project Babel 当前开发分支 2.5。最新版本可以从 \u003Ca href\u003D\u0022http://github.com/livid/v2ex\u0022 target\u003D\u0022_blank\u0022\u003EGitHub\u003C/a\u003E 获取。",

 "created" : 1272206882
 },

 {
 "id" : 2,
 "name" : "v2ex",
 "url" : "http://www.v2ex.com/go/v2ex",
 "title" : "V2EX",
 "title_alternative" : "V2EX",
 "topics" : 2879,

 "header" : "这里讨论和发布关于 V2EX 站点的发展。",


 "footer" : null,

 "created" : 1272207021
 },

 {
 "id" : 3,
 "name" : "olivida",
 "url" : "http://www.v2ex.com/go/olivida",
 "title" : "OLIVIDA",
 "title_alternative" : "OLIVIDA",
 "topics" : 14,

 "header" : "Across the Universe",


 "footer" : null,

 "created" : 1272207042
 }
 ]
 */


public class JsonRequestNodeList extends
        Request<ArrayList<Nodes>>{
    private Gson gson = new Gson();
    private Response.Listener<ArrayList<Nodes>> listListener;

    public JsonRequestNodeList(int method, String url, Response.ErrorListener listener, Response
            .Listener<ArrayList<Nodes>> listListener) {
        super(method, url, listener);
        this.listListener = listListener;
    }


    @Override
    protected Response<ArrayList<Nodes>> parseNetworkResponse(NetworkResponse response) {
        try{
            String jsonStr = new String(response
            .data, HttpHeaderParser.parseCharset(response
            .headers));
            Log.d("TAG  Node :    ",jsonStr);
            return Response.success((ArrayList<Nodes>)
            gson.fromJson(jsonStr,new TypeToken<ArrayList<Nodes>>(){}
            .getType()),HttpHeaderParser.parseCacheHeaders(response));
        }catch (UnsupportedEncodingException e){
            Log.e("request error",e.toString());
            return Response.error(new ParseError(e));

        }catch (JsonSyntaxException e){
            Log.e("request error",e.toString());
            return Response.error(new ParseError(e));
        }
    }


    @Override
    protected void deliverResponse(ArrayList<Nodes> response) {
        listListener.onResponse(response);
    }






    /**
     * 据说加了这个两个方法，可以解决乱码问题，其实然并卵!!!
     * @return
     * @throws AuthFailureError
     */
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String,String> headers = new HashMap<>();
        headers.put("Charset","UTF-8");
        headers.put("Content-Type","application/x-javascript");
        headers.put("Accept-Encoding","gzip deflate");
        return headers;
    }


    /**
     * 据说加了这个两个方法，可以解决乱码问题，其实然并卵!!!
     * @return
     */
    @Override
    public String getBodyContentType() {
        return String.format("application/x-www-form-urlencoded;charset=%s",
                "utf-8");
    }
}
