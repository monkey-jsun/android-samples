package net.junsun.l11_sqlite_cursoradaptor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by jsun on 4/21/2016.
 */
public class ContactDbHelper extends SQLiteOpenHelper {

    static private final int VERSION=3;
    static private final String DB_NAME="L11-SQLite-CursorAdaptor.db";

    static private final String SQL_CREATE_TABLE =
            "CREATE TABLE contact (" +
            "  _id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "  surname TEXT," +
            "  name TEXT," +
            "  email TEXT," +
            "  phone TEXT);";

    static private final String SQL_DROP_TABLE = "DROP TABLE contact";

    Context context;

    public ContactDbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);     // we use default cursor factory (null, 3rd arg)
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // a simple crude implementation that does not preserve data on upgrade
        db.execSQL(SQL_DROP_TABLE);
        db.execSQL(SQL_CREATE_TABLE);

        Toast.makeText(context, "Upgrading DB and dropping data!!!", Toast.LENGTH_SHORT).show();
    }

    public int getMaxRecID() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT MAX(_id) FROM contact;", null);

        if (cursor.getCount() == 0) {
            return 0;
        } else {
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
    }

    public Cursor fetchAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM contact;", null);
    }

    public void add(ContactInfo ci) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", ci.name);
        contentValues.put("surname", ci.surname);
        contentValues.put("email", ci.email);
        contentValues.put("phone", ci.phone);

        db.insert("contact", null, contentValues);

        /*
        String SQL_ADD =
                "INSERT INTO contact (name, surname, email, phone) VALUES ('"
                + ci.name + "', '"
                + ci.surname + "', '"
                + ci.email + "', '"
                + ci.phone +"');";
        db.execSQL(SQL_ADD);
        */
    }

    public void delete(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("contact", "_id=?", new String[]{String.valueOf(id)});

        /*
        String SQL_DELETE="DELETE FROM contact WHERE _id=" + id + ";";
        db.execSQL(SQL_DELETE);
         */
    }
}
