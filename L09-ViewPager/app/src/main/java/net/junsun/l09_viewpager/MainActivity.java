package net.junsun.l09_viewpager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // build animal list
        List<Animal> animals;
        animals = new ArrayList<>();
        animals.add(new Animal("Yellow Fish", R.drawable.fish));
        animals.add(new Animal("Brown Owl", R.drawable.owl));
        animals.add(new Animal("Pink Pig", R.drawable.pig));
        animals.add(new Animal("Orange Tiger", R.drawable.tiger));
        animals.add(new Animal("Green Turtle", R.drawable.turtle));

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new AnimalPagerAdapter(getApplicationContext(), animals));

    }
}
