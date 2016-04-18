package net.junsun.l08_custom_list;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by jsun on 1/19/2016.
 */
public class AnimalArrayAdaptor extends ArrayAdapter<Animal> {
    private final List<Animal> animals;

    public AnimalArrayAdaptor(Context context, int resource, List<Animal> animals) {
        super(context, resource, animals);
        this.animals = animals;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View row = convertView;
        if (convertView == null) {
            // This is an expensive operation! Avoid and reuse as much as possible.
            row = inflater.inflate(R.layout.custom_row, parent, false);
        }

        TextView textView = (TextView) row.findViewById(R.id.label);
        textView.setText(animals.get(position).getName());

        ImageView imageView = (ImageView) row.findViewById(R.id.icon);
        try {
            String filename = animals.get(position).getFilename();
            InputStream inputStream = getContext().getAssets().open(filename);
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(drawable);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return row;
    }
}
