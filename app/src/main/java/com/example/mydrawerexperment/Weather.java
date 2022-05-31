package com.example.mydrawerexperment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.xml.transform.ErrorListener;

public class Weather extends AppCompatActivity {

    AppCompatTextView showtext;
    AppCompatTextView showcity;
    AppCompatTextView windspeed1;
    AppCompatTextView winddirection;
    AppCompatTextView cloudy;
    AppCompatTextView sunsett;
    AppCompatTextView sunrisee;
    AppCompatTextView mydate;
    String cityname1;
    String temprature;
    String winspeedyy;
    String widirect;
    String cloudyyyyy;
    String sunseth;
    String sunrose;
    String mydateee;

    String url="https://api.weatherbit.io/v2.0/current?lat=27.1751&lon=78.0421&key=218f5c11dcbd43c7b144cd0e46fa5b08&";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        showtext=(AppCompatTextView) findViewById(R.id.tv_showdata);
        showcity=(AppCompatTextView) findViewById(R.id.tv_city);
        windspeed1=(AppCompatTextView) findViewById(R.id.tv_windspeed);
        winddirection=(AppCompatTextView) findViewById(R.id.tv_winddir);
        cloudy=(AppCompatTextView) findViewById(R.id.tv_cloudy);
        sunsett=(AppCompatTextView) findViewById(R.id.tv_sunset);
        sunrisee=(AppCompatTextView) findViewById(R.id.tv_sunrise);
        mydate=(AppCompatTextView) findViewById(R.id.tv_datetime);
        volley_api();
    }

    private void volley_api() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest =new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object=new JSONObject(response);
                    String mydata=object.getString("data");
                    JSONArray object2= new JSONArray(mydata);
                    JSONObject object3=object2.getJSONObject(0);
                    cityname1=object3.getString("city_name");
                   temprature=object3.getString("temp");
                   winspeedyy=object3.getString("wind_spd");
                   widirect=object3.getString("wind_cdir_full");
                   cloudyyyyy=object3.getString("clouds");
                   sunseth=object3.getString("sunset");
                   sunrose=object3.getString("sunrise");
                   mydateee=object3.getString("datetime");
                   showcity.setText("City : "+cityname1);
                   showtext.setText("Temprature : "+temprature);
                   windspeed1.setText("Wind Speed : "+winspeedyy);
                   winddirection.setText("Wind Direction"+widirect);
                   cloudy.setText("Cloudy : "+cloudyyyyy);
                   sunsett.setText("Sunset : "+sunseth);
                   sunrisee.setText(("Sunrise : "+sunrose));
                   mydate.setText("DateTime : "+mydateee);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Weather.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String>hashMap=new HashMap<>();
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }
}