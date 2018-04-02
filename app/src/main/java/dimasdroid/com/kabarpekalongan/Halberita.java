package dimasdroid.com.kabarpekalongan;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import dimasdroid.com.kabarpekalongan.Adapter.AdapterData;
import dimasdroid.com.kabarpekalongan.Model.ModelData;
import dimasdroid.com.kabarpekalongan.Util.AppController;
import dimasdroid.com.kabarpekalongan.Util.ServerAPI;

public class Halberita extends AppCompatActivity {

    RecyclerView mRecyclerview;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    List<ModelData> mItems;
    ProgressDialog pd;
    SwipeRefreshLayout swLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi SwipeRefreshLayout
        swLayout = (SwipeRefreshLayout) findViewById(R.id.swlayout);
        mRecyclerview = (RecyclerView)findViewById(R.id.recycler);
        pd = new ProgressDialog(Halberita.this);
        mItems = new ArrayList<>();
        mAdapter = new AdapterData(Halberita.this, mItems);
        mManager = new LinearLayoutManager(Halberita.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerview.setLayoutManager(mManager);
        mRecyclerview.setAdapter(mAdapter);
        swLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimary, R.color.colorPrimaryDark);
        swLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });


        loadJson();

    }

    //untuk fungsi refresh
    private void refreshContent(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mItems.clear();
                loadJson();
                swLayout.setRefreshing(false);
            }
        },1000);
    }

    //search
    private void loadJson(){
        pd.setTitle("Sedang memuat data");
        pd.setMessage("Tunggu sebentar ya, pastikan kamu terhubung ke internet :)");
        pd.setCancelable(false);
        pd.show();
        JsonArrayRequest requestdata = new JsonArrayRequest(Request.Method.POST, ServerAPI.URL_DATA, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.cancel();
                        Log.d("volley", "response : "+ response.toString());

                        for (int i=0; i<response.length();i++){
                            try {
                                JSONObject data = response.getJSONObject(i);
                                ModelData md = new ModelData();
                                md.setJudul(data.getString("judul"));
                                md.setTanggal(data.getString("tanggal"));
                                //md.setDetail_siswa(data.getString("nama_siswa"));
                                mItems.add(md);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Toast.makeText(Halberita.this, "Data berhasil dimuat... :)", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        showDisconnDialog();
                        //Toast.makeText(MainActivity.this, "Perikssa koneksi internet kamu, kemudian tarik ke bawah untuk memperbarui", Toast.LENGTH_LONG).show();
                        Log.d("volley", "Error "+error.getMessage());
                    }
                });
        AppController.getInstance().AddToRequestQueue(requestdata);
    }

    private void showDisconnDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Oops... ada yang salah nih :(")
                .setMessage("Periksa koneksi internet kamu, kemudian tarik ke bawah untuk memuat data")
                .setCancelable(false)
                .setNegativeButton("Ok", null)
                .show();
    }
}

