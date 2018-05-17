package com.example.admin.contentprovidercontact;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DanhBaActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<Contact> contactArrayAdapter;
    ArrayList<Contact> contactArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_ba);

        addControl();
        Show();
    }

    private void Show() {

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        //tra ve ket qua cursor
        Cursor cursor = getContentResolver().query(uri, null,null,null,null);
        contactArrayList.clear();

        //vong lap while duyet tung ROW through cursor
        while (cursor.moveToNext()){

            //lay ten cot
            String laytenCotName = ContactsContract.Contacts.DISPLAY_NAME;

            //lay index cho cot do
            int indexCotName = cursor.getColumnIndex(laytenCotName);

            //lay du lieu dau vao index
            String dulieuname = cursor.getString(indexCotName);


            //lam tuong tu cot phone
            String laytenCotPhone = ContactsContract.CommonDataKinds.Phone.NUMBER;
            int indexCotPhone = cursor.getColumnIndex(laytenCotPhone);
            String dulieuSoDienThoai = cursor.getString(indexCotPhone);


            // truyen du lieu vao doi tuong Contact
            Contact contact = new Contact(dulieuname,dulieuSoDienThoai);

            //truyen doi tuong contact vo Arraylist
            contactArrayList.add(contact);

        }
        cursor.close();
    }

    private void addControl() {

        listView = findViewById(R.id.lv);
        contactArrayList = new ArrayList<>();

        contactArrayAdapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, contactArrayList);

        listView.setAdapter(contactArrayAdapter);
    }

}
