package dimasdroid.com.kabarpekalongan;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity{

    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private String[] programmingLang={
            "Hiburan","Pendidikan","Kesehatan","Satlantas",
            "Politik"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.listViewProgramming);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, programmingLang);
        listView.setAdapter(arrayAdapter);

        //jika salah satu kategori diklik
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent i = new Intent(ListActivity.this, DetailKategori.class);
                startActivity(i);
            }
        });
    }

}