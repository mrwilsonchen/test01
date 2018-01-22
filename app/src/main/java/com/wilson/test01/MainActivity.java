package com.wilson.test01;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView img;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //顯示SDCard圖片的ImageView與進行讀取SDCard圖片的Button
        img = (ImageView) findViewById(R.id.img);
        btn = (Button) findViewById(R.id.sd);

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                //確認是否有插入SDCard
                if(checkSDCard())
                {
                    //帶入SDCard內的圖片路徑(SDCard: DCIM資料夾/100MEDIA資料夾/001圖片)
                    img.setImageBitmap(getBitmapFromSDCard("DCIM/IMG_20180119_053714.jpg"));
                }
                else Toast.makeText(MainActivity.this,"尚未插入SDCard",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //確認是否有插入SDCard
    private static boolean checkSDCard()
    {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            return true;
        }
        return false;
    }

    //讀取SDCard圖片，型態為Bitmap
    private static Bitmap getBitmapFromSDCard(String file)
    {
        try
        {
            String sd = Environment.getExternalStorageDirectory().toString();
            Bitmap bitmap = BitmapFactory.decodeFile(sd + "/" + file);
            return bitmap;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}

