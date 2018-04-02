package dimasdroid.com.kabarpekalongan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DetailKategori extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private String[] judulBerita={
            "Demo Terhadap Ojek Online di Kantor Walikota","Jalan Utama Lebakbarang Terputus Karena Longsor","Seorang Pria di Pekalongan Biayai Gurunya ke Luar Negeri","Pagi Hari Pecah Rekor Mewarnai Rambu Lalu Lintas",
            "Seorang mahasiswa AKN ditemukan pingsan setelah ngoding Android","Sidang proyek 3 akan dilaksanakan pada 31 Oktober 2017",
            "Sepeda motor menabrak sebuah Truk bermuatan pasir, 1 tewas"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kategori);

        listView = (ListView) findViewById(R.id.listDetailKategori);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, judulBerita);
        listView.setAdapter(arrayAdapter);

        //jika salah satu judul diklik
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent i = new Intent(DetailKategori.this, DetailBerita.class);
                startActivity(i);
            }
        });
    }
}
