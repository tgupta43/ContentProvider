package edu.sjsu.android.mycontentprovider;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.database.Cursor;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickAddName(View view) {
        // Add a new student record
        ContentValues values = new ContentValues();

        values.put(StudentsProvider.NAME,
                ((EditText) findViewById(R.id.txtName)).getText().toString());

        values.put(StudentsProvider.GRADE,
                ((EditText) findViewById(R.id.txtGrade)).getText().toString());

        Uri uri = getContentResolver().insert(
                StudentsProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }
    //request permission
    //getpermission

    public void onClickRetrieveStudents(View view) {
        // Retrieve student records
        String URL = "content://edu.sjsu.android.mycontentprovider.College/students";
        Uri students = Uri.parse(URL);
        Cursor c = getContentResolver().query(students, null, null, null,
                "name");



         if (c.moveToFirst()) {
            do{
                Toast.makeText(this,
                        c.getString(c.getColumnIndexOrThrow(StudentsProvider._ID)) +
                                ", " +  c.getString(c.getColumnIndexOrThrow( StudentsProvider.NAME)) +
                                ", " + c.getString(c.getColumnIndexOrThrow( StudentsProvider.GRADE)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }

    }
}