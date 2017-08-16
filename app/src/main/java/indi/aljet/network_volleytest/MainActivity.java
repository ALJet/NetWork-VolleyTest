package indi.aljet.network_volleytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import indi.aljet.network_volleytest.entity.MobWeather;
import indi.aljet.network_volleytest.entity.Nodes;
import indi.aljet.network_volleytest.net.JsonRequestList;
import indi.aljet.network_volleytest.net.JsonRequestNodeList;
import indi.aljet.network_volleytest.net.JsonRequestWithAuth;
import indi.aljet.network_volleytest.net.NodeJsonRequest;

public class MainActivity extends AppCompatActivity {
    Button btn_click;
    TextView tv_info;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_click = (Button)findViewById(R.id
        .btn_click);
        tv_info = (TextView)findViewById(R.id
        .tv_info);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonRequestList();
            }
        });
    }


    private void getJsonWithT(){

        RequestQueue requestQueue = Volley
                .newRequestQueue(MainActivity.this);

        //api 搭建自己电脑的一个Tomcat 上的一个json文件 (tomcat乱码还没有解决)
        JsonRequestWithAuth<MobWeather> mobWeatherJsonRequestWithAuth =
                new JsonRequestWithAuth<>(Request.Method.GET
                        , "http://192.168.1.38:8080/Test/weather.json",
                        new Response.Listener<MobWeather>() {
                    @Override
                    public void onResponse(MobWeather response) {

                        //获取风力
                        tv_info.setText(response.getResults()
                                .get(0).getWeather_data().get(0)
                                .getWind());
                    }
                }, MobWeather.class,
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                tv_info.setText("getJsonWithT Error");
                            }
                        });
        requestQueue.add(mobWeatherJsonRequestWithAuth);
    }


    private void NodeListJson(){

        RequestQueue requestQueue = Volley
                .newRequestQueue(MainActivity.this);


        //api http://www.v2ex.com/api/nodes/all.json
        // 网络api接口
        JsonRequestNodeList nodeList = new
                JsonRequestNodeList(Request.Method.GET
                , "https://www.v2ex.com/api/nodes/all.json", new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tv_info.setText("NodeListJson Error");
            }
        }, new Response.Listener<ArrayList<Nodes>>() {
            @Override
            public void onResponse(ArrayList<Nodes> response) {
                tv_info.setText(response.get(0).getFooter());
            }
        });

        requestQueue.add(nodeList);
    }


    private void NodeJsonRequest(){
        RequestQueue requestQueue = Volley
                .newRequestQueue(MainActivity.this);
        NodeJsonRequest jsonRequest = new NodeJsonRequest(
                Request.Method.GET,
                "https://www.v2ex.com/api/nodes/all.json",
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tv_info.setText("NodeJsonRequest error");
                    }
                },
                new Response.Listener<ArrayList<Nodes>>() {
                    @Override
                    public void onResponse(ArrayList<Nodes> response) {
                        tv_info.setText(response.get(0)
                        .getFooter());
                    }
                });
        requestQueue.add(jsonRequest);
    }


    /**
     * 时做不出来 这个泛型的 数组json
     * 日后才研究
     */
    private void JsonRequestList(){

//        暂时做不出来 这个泛型的 数组json
        RequestQueue requestQueue = Volley
                .newRequestQueue(MainActivity.this);
        JsonRequestList<Nodes> jsonRequestList = new
                JsonRequestList<>(Request.Method.GET
                , "https://www.v2ex.com/api/nodes/all.json",
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tv_info.setText("JsonRequestList Error");
                    }
                }, new Response.Listener<ArrayList<Nodes>>() {
            @Override
            public void onResponse(ArrayList<Nodes> response) {
                tv_info.setText(response
                .get(0).getFooter());
            }
        });

        requestQueue.add(jsonRequestList);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
