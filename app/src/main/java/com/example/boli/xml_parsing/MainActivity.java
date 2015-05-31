package com.example.boli.xml_parsing;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends ListActivity {


    // DIRECCION DONDE OBTENDRA LOS DATOS DE LA BASE DA DATOS
    static final String URL = "http://iin8.szhernandez.dx.am/bbdd2.xml";
    // Nodos keys Del XML
   static final String KEY_GANADO = "ganado"; // NODO PADRE

    static final String KEY_N_ARETE = "n_arete";
    static final String KEY_F_NACIMIENTO = "f_nacimiento";
    static final String KEY_NOMBRE = "nombre";
    static final String KEY_SEXO = "sexo";
    static final String KEY_F_GESTACION = "f_gestacion";
    static final String KEY_F_PARTO = "f_parto";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

        XMLParser parser = new XMLParser();
        String xml = parser.getXmlFromUrl(URL); // OBTENIENDO XML
        Document doc = parser.getDomElement(xml); // OBTIENE EL ELEMENTO DEL DOCUMENTO

        NodeList nl = doc.getElementsByTagName(KEY_GANADO);
        // looping through all item nodes <item>
        for (int i = 0; i < nl.getLength(); i++) {
            // CREA U NUEVO HashMap
            HashMap<String, String> map = new HashMap<String, String>();
            Element e = (Element) nl.item(i);
            // Se añaden cada nodo hijo al HashMap con su respectiva clave
            map.put(KEY_N_ARETE, "N_ARETE: " + parser.getValue(e, KEY_N_ARETE));
            map.put(KEY_F_NACIMIENTO, "FECHA NAC: " + parser.getValue(e, KEY_F_NACIMIENTO));
            map.put(KEY_NOMBRE, "NOMBRE: " + parser.getValue(e, KEY_NOMBRE));
            map.put(KEY_SEXO, "SEXO: " + parser.getValue(e, KEY_SEXO));
            map.put(KEY_F_GESTACION, "FECHA GES: " + parser.getValue(e, KEY_F_GESTACION));
            map.put(KEY_F_PARTO, "FECHA PAR: " + parser.getValue(e, KEY_F_PARTO));

            // adding HashList to ArrayList
            menuItems.add(map);
        }

        // MANDA LOS DATOS A LA LISTA
        ListAdapter adapter = new SimpleAdapter(this, menuItems,
                R.layout.lista_registros,
                new String[]{KEY_N_ARETE, KEY_F_NACIMIENTO, KEY_NOMBRE, KEY_SEXO, KEY_F_GESTACION, KEY_F_PARTO}, new int[]{
                R.id.no_arete, R.id.f_nacimiento, R.id.nombres, R.id.sexo1, R.id.fe_ges, R.id.fe_par});

        setListAdapter(adapter);

        // Seleccion de los elementos que contendra el listview
        ListView lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Obtiene la selecciona de todos valores del ListItem
                String no_arete = ((TextView) view.findViewById(R.id.no_arete)).getText().toString();
                String f_naci = ((TextView) view.findViewById(R.id.f_nacimiento)).getText().toString();
                String nombres = ((TextView) view.findViewById(R.id.nombres)).getText().toString();
                String sexo1 = ((TextView) view.findViewById(R.id.sexo1)).getText().toString();
                String fe_ges = ((TextView) view.findViewById(R.id.fe_ges)).getText().toString();
                String fe_par = ((TextView) view.findViewById(R.id.fe_par)).getText().toString();

                // Starting new intent
                Intent in = new Intent(getApplicationContext(), vista_individual.class);
                in.putExtra(KEY_N_ARETE, no_arete);
                in.putExtra(KEY_F_NACIMIENTO, f_naci);
                in.putExtra(KEY_NOMBRE, nombres);
                in.putExtra(KEY_SEXO, sexo1);
                in.putExtra(KEY_F_GESTACION, fe_ges);
                in.putExtra(KEY_F_PARTO, fe_par);

                startActivity(in);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



}
