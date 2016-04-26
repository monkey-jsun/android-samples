package net.junsun.l11_sqlite_cursoradaptor;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jsun on 4/17/2016.
 */
public class ContactAdapter extends CursorAdapter {

    public ContactAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.namecard_layout, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String surname = cursor.getString(cursor.getColumnIndex("surname"));
        String name = cursor.getString(cursor.getColumnIndex("name"));
        String email = cursor.getString(cursor.getColumnIndex("email"));
        String phone = cursor.getString(cursor.getColumnIndex("phone"));

        ((TextView)view.findViewById(R.id.txtSurname)).setText(surname);
        ((TextView)view.findViewById(R.id.txtName)).setText(name);
        ((TextView)view.findViewById(R.id.txtEmail)).setText(email);
        ((TextView)view.findViewById(R.id.txtPhone)).setText(phone);
        ((TextView)view.findViewById(R.id.title)).setText(name + " " + surname);
    }
}