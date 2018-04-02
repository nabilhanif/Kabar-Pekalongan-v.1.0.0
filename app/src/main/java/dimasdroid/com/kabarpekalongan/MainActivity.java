package dimasdroid.com.kabarpekalongan;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //menginisiasi dan memanggil widget button pada file layout
        Button btn2 =(Button)findViewById(R.id.button2);
        Button btn1 =(Button)findViewById(R.id.button4);
        Button btn3 =(Button)findViewById(R.id.button3);
        Button btn5 =(Button)findViewById(R.id.button5);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //menghubungkan antar activity dengan intent
                Intent pindah = new Intent(MainActivity.this,About.class);
                startActivity(pindah);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //menghubungkan antar activity dengan intent
                Intent pindah = new Intent(MainActivity.this, DetailKategori.class);
                startActivity(pindah);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //menghubungkan antar activity dengan intent
                Intent pindah = new Intent(MainActivity.this,ListActivity.class);
                startActivity(pindah);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



    }


    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Apa kalian ingin Keluar?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("Tidak", null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present. getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }







}





