package indi.aljet.network_volleytest.net;

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

/**
 * 还是没有成功
 * 任意类型的 json 返回一个数据json
 * Created by PC-LJL on 2017/8/16.
 */

public class JsonRequestList<T> extends
        Request<ArrayList<T>>{

    private Gson gson = new Gson();
    private Response.Listener<ArrayList<T>> listListener;
//    private Class<T> tClass;

    //定义的 Class 到底是怎样的
//    private Class<ArrayList<T>> clazz;


    public JsonRequestList(int method, String url, Response.ErrorListener listener, Response
            .Listener<ArrayList<T>> listListener) {
        super(method, url, listener);
        this.listListener = listListener;
//        this.clazz = clazz;
    }

    @Override
    protected Response<ArrayList<T>> parseNetworkResponse(NetworkResponse response) {
        try{
            String jsonStr = new String(response
                    .data, HttpHeaderParser.parseCharset(response
                    .headers));

            return Response.success((ArrayList<T>)
                    gson.fromJson(jsonStr,new TypeToken<ArrayList<T>>(){}
                            .getType()),HttpHeaderParser.parseCacheHeaders(response));
        }catch (UnsupportedEncodingException e){
            return Response.error(new ParseError(e));

        }catch (JsonSyntaxException e){
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(ArrayList<T> response) {
        listListener.onResponse(response);
    }

}
