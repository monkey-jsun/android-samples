package net.junsun.l08_custom_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    List<Animal> animals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // build animal list
        animals = new ArrayList<>();
        animals.add(new Animal("Yellow Fish", "fish.png"));
        animals.add(new Animal("Brown Owl", "owl.png"));
        animals.add(new Animal("Pink Pig", "pig.png"));
        animals.add(new Animal("Orange Tiger", "tiger.png"));
        animals.add(new Animal("Green Turtle", "turtle.png"));

        Spinner lv = (Spinner) findViewById(R.id.spinner);
        lv.setAdapter(new AnimalArrayAdaptor(this, R.layout.custom_row, animals));
        lv.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "Position: " + position + ", Data: " + animals.get(position).getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getApplicationContext(), "Nothing selected", Toast.LENGTH_SHORT).show();
    }
}
