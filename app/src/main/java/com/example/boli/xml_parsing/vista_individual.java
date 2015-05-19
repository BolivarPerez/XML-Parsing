package com.example.boli.xml_parsing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class vista_individual extends Activity {

    static final String KEY_N_ARETE = "n_arete";
    static final String KEY_F_NACIMIENTO = "f_nacimiento";
    static final String KEY_NOMBRE = "nombre";
    static final String KEY_SEXO = "sexo";
    static final String KEY_F_GESTACION = "f_gestacion";
    static final String KEY_F_PARTO = "f_parto";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_individual);

        Intent in = getIntent();

        // Get XML values from previous intent
        String n_arete = in.getStringExtra(KEY_N_ARETE);
        String f_nacimiento = in.getStringExtra(KEY_F_NACIMIENTO);
        String nombre = in.getStringExtra(KEY_NOMBRE);
        String sexo = in.getStringExtra(KEY_SEXO);
        String f_gestacion = in.getStringExtra(KEY_F_GESTACION);
        String f_parto = in.getStringExtra(KEY_F_PARTO);



        // Displaying all values on the screen
        TextView are = (TextView) findViewById(R.id.n_arete);
        TextView nac = (TextView) findViewById(R.id.f_nacimiento);
       TextView nom = (TextView) findViewById(R.id.nombre);
        TextView sex = (TextView) findViewById(R.id.sexo);
        TextView gest = (TextView) findViewById(R.id.f_ges);
        TextView part = (TextView) findViewById(R.id.f_par);





        are.setText(n_arete);
        nac.setText(f_nacimiento);
        nom.setText(nombre);
        sex.setText(sexo);
        gest.setText(f_gestacion);
        part.setText(f_parto);



    }

 }
