package com.demo.susan.androidbeginner;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CoffeActivity extends AppCompatActivity {
    TextView sTextNama;
    TextView sTextHarga, sTextQty;
    Button sButtonOrder, sButtonPlus, sButtonMin;
    //Spinner
    Spinner sSpinnerMenu;
    List<String> sListMenu = new ArrayList<>();

    int qty = 0;
    int harga = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    sTextNama = (TextView) findViewById(R.id.sTextNama);
        sTextHarga = (TextView) findViewById(R.id.sTextHarga);
    sButtonOrder = (Button) findViewById(R.id.sButtonOrder);
        sTextQty = (TextView) findViewById(R.id.sTextQty);
        sButtonPlus = (Button) findViewById(R.id.sButtonPlus);
        sButtonMin = (Button) findViewById(R.id.sButtonMin);
        sSpinnerMenu = (Spinner) findViewById(R.id.sSpinnerMenu);

        sListMenu.add("......");
        sListMenu.add("Martabak");
        sListMenu.add("Piscok Bakar");
        sListMenu.add("Ice Cream Sandwitch");
        sListMenu.add("Lumpia Basah");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sListMenu);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sSpinnerMenu.setAdapter(dataAdapter);
//    sButtonOrder.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Toast.makeText(getApplicationContext(),sTextNama.getText(),Toast.LENGTH_LONG).show();
//            sTextHarga.setText(sTextNama.getText());
//        }
//    });

    }
  public void onClickOrder (View view) {
//        Toast.makeText(getApplicationContext(),sTextNama.getText(),Toast.LENGTH_LONG).show();
//            sTextHarga.setText(sTextNama.getText());
      Intent emailIntent = new Intent(Intent.ACTION_SEND);
      emailIntent.setData(Uri.parse("mail to :"));
      emailIntent.setType("Text/Plain");
      emailIntent.putExtra(Intent.EXTRA_EMAIL, "arisssusanti@gmail.com");
      emailIntent.putExtra(Intent.EXTRA_SUBJECT, sTextNama.getText());
      emailIntent.putExtra(Intent.EXTRA_TEXT,
                "Saya Pesan"
                        +sSpinnerMenu.getSelectedItem()

              +
                "Sebanyak"
                        +sTextQty.getText() +
                "Seharga"
                                +sTextHarga.getText());
      try{
          startActivity(Intent.createChooser(emailIntent,"Send mail..."));
          finish();
      }catch (android.content.ActivityNotFoundException ex){
          Toast.makeText(this, "there is no email client installed", Toast.LENGTH_SHORT).show();
      }
   }
    public void onClickPlus (View view) {
        harga =  harga + 5 ;
        qty = qty + 1;
        sTextQty.setText(qty+"");
        sTextHarga.setText("$"+harga);
    }
    public void OnClickMin (View view) {
        if (harga !=0){
            harga = harga - 5;
            qty = qty - 1;
            sTextQty.setText(qty+"");
            sTextHarga.setText("$"+harga);
        }
        else {
            harga = 0;
            qty = 0;
            sTextQty.setText(qty+"");
            sTextHarga.setText("$"+harga);
        }
    }
    public void OnClickReset (View view){
        harga = 0;
        qty = 0 ;
        sTextNama.setText("");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}

