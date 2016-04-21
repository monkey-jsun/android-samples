package net.junsun.l08_custom_list;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by jsun on 1/19/2016.
 */
public class AnimalArrayAdaptor extends ArrayAdapter<Animal> {
    private final List<Animal> animals;
    private Context ctxt;

    public AnimalArrayAdaptor(Context context, int resource, List<Animal> animals) {
        super(context, resource, animals);
        this.animals = animals;
        ctxt = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ScrapViewHolder holder;

        View row = convertView;

        if (row == null) {
            Log.i("jsun", "Row is null; Need to be inflated.");

            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.custom_row, parent, false);

            holder = new ScrapViewHolder();
            holder.label = (TextView) row.findViewById(R.id.label);
            holder.icon = (ImageView) row.findViewById(R.id.icon);
            holder.menu = (ImageView) row.findViewById(R.id.menu);

            row.setTag(holder);

        } else {
            Log.i("jsun", "Row is NOT null; reusing it!");
            holder = (ScrapViewHolder) row.getTag();
        }

        holder.label.setText(animals.get(position).getName());

        try {
            String filename = animals.get(position).getFilename();
            InputStream inputStream = getContext().getAssets().open(filename);
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            holder.icon.setImageDrawable(drawable);
            holder.icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } catch (IOException e) {
                e.printStackTrace();
        }

        // holder.menu.setImageResource(R.mipmap.ic_launcher);

        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "MENU clicked for " + animals.get(position).getName();
                Toast.makeText(ctxt, msg, Toast.LENGTH_SHORT).show();
            }
        });

        return row;
    }

    public class ScrapViewHolder {
        TextView label;
        ImageView icon;
        ImageView menu;
    }
}


