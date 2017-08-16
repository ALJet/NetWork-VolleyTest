package indi.aljet.network_volleytest.net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义Request 可以返回任何类型的
 *
 * Created by PC-LJL on 2017/8/16.
 */


/**
 * json数据如下
 * {
 "date":"20190808",
 "dayTime":"20180909-12:09",
 "night":"寒冷",
 "week":"星期五",
 "wind":"偏南风"
 }
 * @param <T>
 */
public class JsonRequestWithAuth<T> extends
        Request<T>{

    private Gson gson = new Gson();
    private Class<T> clazz;
    private Response.Listener<T> listener;

    public JsonRequestWithAuth(int method, String url,
                               Response.Listener<T> listener1,
                               Class<T> clazz,
                               Response.ErrorListener listener ) {
        super(method, url, listener);
        this.clazz = clazz;
        this.listener = listener1;
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try{
            String jsonStr = new String(response.data
            , HttpHeaderParser.parseCharset(response
            .headers));
            return Response.success(gson.fromJson(jsonStr,
                    clazz),HttpHeaderParser
            .parseCacheHeaders(response));
        }catch (UnsupportedEncodingException e){
            return Response.error(new ParseError(e));
        }catch (JsonSyntaxException e){
            return Response.error(new ParseError(e));
        }
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

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }
}
