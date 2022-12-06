package com.example.courseproject_fayala2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PhotoListActivity extends AppCompatActivity {
    ListView listView;
    private MyCustomAdapter myAdapter;
    private SQLiteDatabase db = null;
    private DatabaseOpenHelper dbHelper = null;
    ImageView imgFavorite;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap bp;
    Cursor mCursor;

    final static String[] all_columns = { DatabaseOpenHelper.ID, DatabaseOpenHelper.PHOTO};
    final static String[] print_columns = { DatabaseOpenHelper.ID, DatabaseOpenHelper.PHOTO};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_list);
        listView = (ListView)findViewById(R.id.mylist);
        myAdapter = new MyCustomAdapter();//ArrayAdapter<Bitmap>(this, R.layout.line);
        listView.setAdapter(myAdapter);
        //dbHelper = new DatabaseOpenHelper(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "Item " + mAdapter.getItem(position), Toast.LENGTH_SHORT).show();


                //-------------before database implementation

                Intent displayPic = new Intent(getApplicationContext(), ImageActivity.class);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bp = myAdapter.getItem(position);
                bp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                displayPic.putExtra("image", byteArray);
                startActivity(displayPic);

                //--------------end



                /*
                Intent intent = new Intent(getApplicationContext(), ImageActivity.class);

                ContentValues cv = new ContentValues(2);

                String myQuery = "SELECT * FROM photolist";

                db = dbHelper.getWritableDatabase();
                Cursor cursor = db.rawQuery(myQuery, null);
                cursor.moveToPosition(position);
                String photo_id = cursor.getString(0);
                byte[] photo = cursor.getBlob(1);

                intent.putExtra("image", photo);
                startActivity(intent);
                */
            }
        });
    }

    public void onTakePhotoButtonClicked(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        //-----------before database

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        ImageView thumbnail = (ImageView)findViewById(R.id.image);

        super.onActivityResult(requestCode, resultCode, data);
        bp = (Bitmap) data.getExtras().get("data");

        //thumbnail.setImageBitmap(bp);
        myAdapter.addItem(bp);

        //-------------end



        //mCursor = db.query(dbHelper.DBNAME, all_columns, null, null, null, null, null);

        /*
        super.onActivityResult(requestCode, resultCode, data);
        bp = (Bitmap)data.getExtras().get("data");

        //myAdapter.addItem(bp);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues(1);

        cv.put(DatabaseOpenHelper.PHOTO, byteArray);
        db.insert(DatabaseOpenHelper.DBNAME, null, cv);

        mCursor.requery();
        myAdapter.notifyDataSetChanged();

        */

    }

    /*
    public void onPause() {
        super.onPause();
        if (db != null) db.close();
        mCursor.close();
    }

    */

    /*
    public void onResume(){
        super.onResume();
        db = dbHelper.getWritableDatabase();
        //ArrayAdapter myAdapter2;

        mCursor = db.query(dbHelper.DBNAME, all_columns, null, null, null, null, null);
        //myAdapter = new ArrayAdapter<Bitmap> (this, R.layout.line, mCursor, print_columns, R.id.image, 0);
        //myAdapter = new MyCustomAdapter(this, R.layout.line, mCursor, print_columns, R.id.image, 0);
        myAdapter = new MyCustomAdapter();

        for (int index = 0; index < all_columns.length; index++) {
            Bitmap temp = StringToBitMap(all_columns[index]);
            myAdapter.addItem(temp);
        }

        listView.setAdapter(myAdapter);
        //myAdapter = new SimpleCursorAdapter(this, R.layout.item_info, mCursor, print_columns, new int[] { R.id.ID, R.id.ITEM },0);
        //listView.setAdapter(myAdapter);

    }
    */

    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }
        catch(Exception e){
            e.getMessage();
            return null;
        }
    }

    private class MyCustomAdapter extends BaseAdapter {

        private ArrayList<Bitmap> mData = new ArrayList<Bitmap>();
        private LayoutInflater mInflater;
        //private Bitmap bp;

        public MyCustomAdapter() {
            mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void addItem(final Bitmap item) {
            mData.add(item);
            notifyDataSetChanged();
        }

        public void removeItem(final Bitmap item) {
            mData.remove(item);
            notifyDataSetChanged();
        }


        public int getCount() {
            return mData.size();
        }


        public Bitmap getItem(int position) {
            return mData.get(position);
        }


        public long getItemId(int position) {
            return position;
        }

        public void clear() {
            mData.clear();
            notifyDataSetChanged();
        }


        public View getView(int position, View convertView, ViewGroup parent) {
            System.out.println("getView " + position + " " + convertView);
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.line, null);
                holder = new ViewHolder();
                //holder.textView = (TextView)convertView.findViewById(R.id.label);
                holder.imageView = (ImageView)convertView.findViewById(R.id.image);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            Bitmap s = mData.get(position);
            //holder.textView.setText("Click for Photo");
            holder.imageView.setImageBitmap(s);
            //holder.imageView.setImageResource(R.drawable.bp);
            //if (s.length() < 4) holder.imageView.setImageResource(R.drawable.bp);
            //else if (s.length() < 8) holder.imageView.setImageResource(R.drawable.emo_im_undecided);
            //else holder.imageView.setImageResource(R.drawable.emo_im_happy);

            return convertView;
        }

    }
    public static class ViewHolder {
        public TextView textView;
        public ImageView imageView;
    }

}