package net.junsun.l08_custom_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

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

        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(new AnimalArrayAdaptor(this, R.layout.custom_row, animals));
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Animal animal = animals.get(position);
        Toast.makeText(getApplicationContext(), "Animal clicked : " + animal.getName(), Toast.LENGTH_SHORT).show();
    }
}
