package indi.aljet.network_volleytest.net;

import android.util.Log;

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

import indi.aljet.network_volleytest.entity.Nodes;


/**
 * Created by PC-LJL on 2017/8/16.
 */

public class NodeJsonRequest extends
        Request<ArrayList<Nodes>> {

    private Gson gson = new Gson();
    private Response.Listener<ArrayList<Nodes>> listListener;

    public NodeJsonRequest(int method, String url, Response.ErrorListener listener, Response
            .Listener<ArrayList<Nodes>> listListener) {
        super(method, url, listener);
        this.listListener = listListener;
    }


    @Override
    protected Response<ArrayList<Nodes>> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonStr = new String(response.data,
                    HttpHeaderParser.parseCharset(response
                            .headers));
            Log.d("TAG JsonStr",jsonStr);

            return  Response.success((ArrayList<Nodes>)
                    gson.fromJson(jsonStr,new TypeToken<ArrayList<Nodes>>()
            {}.getType())
            ,HttpHeaderParser.parseCacheHeaders(response));


        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }catch (JsonSyntaxException e){
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(ArrayList<Nodes> response) {
        listListener.onResponse(response);
    }
}
