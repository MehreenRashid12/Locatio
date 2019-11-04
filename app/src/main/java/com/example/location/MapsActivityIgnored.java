package com.example.location;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class MapsActivityIgnored extends AppCompatActivity {
    Button btn;
    EditText btnLatitude,btnLongitude;
    EditText edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_ignored);
        btn=findViewById(R.id.setLocationBtnid);
        edt=findViewById(R.id.setLocationedtId);
        btnLatitude=findViewById(R.id.setLatitudeId);
        btnLongitude=findViewById(R.id.setLongitudeId);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value=edt.getText().toString();
                String value2=btnLatitude.getText().toString();
                String value3=btnLongitude.getText().toString();



                Intent intent=new Intent(MapsActivityIgnored.this,NewTaskAdd.class);
                intent.putExtra("keyText",value);
                intent.putExtra("keyLatitude",value2);
                intent.putExtra("keyLongitude",value3);
                /*extras.putString("keyText",value);
                extras.putString("keyLatitude",value2);
                extras.putString("keyLongitude",value3);*/

                /*Intent intent = new Intent(this, YourClass.class);
                intent.putExtra("id", propId);
                intent.putExtra("city", propCity);
                intent.putExtra("place", propPlace);
                intent.putExtra("station", propStation);
                startActivity(intent);*/

                setResult(1,intent);
                finish();

                /*Intent intent = new Intent(this, MyActivity.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA_USERNAME","my_username");
                extras.putString("EXTRA_PASSWORD","my_password");
                intent.putExtras(extras);
                startActivity(intent);*/
            }
        });

    }
}
