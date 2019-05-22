package com.example.muslimcyberarmy.koneksiwebservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebServiceJSON extends AppCompatActivity {

    private TextView textHasilJSON;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_json);

        mQueue = Volley.newRequestQueue(this);
        textHasilJSON = findViewById(R.id.textJSON);
        Button tombolJSON = findViewById(R.id.btnJSON);

        tombolJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uraiJSON();
            }
        });
    }
    private void uraiJSON (){
        String url = "https://api.myjson.com/bins/16js6w";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("anggota");

                    for (int i =0; i < jsonArray.length(); i++){

                        JSONObject anggota = jsonArray.getJSONObject(i);

                        String idAnggota = anggota.getString("id_anggota");
                        String namaAnggota = anggota.getString("nama_anggota");
                        String asalDaerah = anggota.getString("asal_daerah");
                        String kelompokKamar = anggota.getString("kelompok_kamar");
                        String sisaSaldo = anggota.getString("sisa_saldo");


                        textHasilJSON.append(idAnggota+ ". " + namaAnggota + ", "
                                + asalDaerah + ", " + kelompokKamar + ", " + sisaSaldo + "\n\n");
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Error1", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(),"Error1", Toast.LENGTH_SHORT).show();

            }
        });
        mQueue.add(request);
    }
}
