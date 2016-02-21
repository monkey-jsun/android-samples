package net.junsun.l19_canvas_drawing;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    Paint paint;
    Bitmap bitmap;
    Canvas canvas;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);

        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(60);

        bitmap = Bitmap.createBitmap(600, 630, Bitmap.Config.RGB_565);

        canvas = new Canvas(bitmap);
        canvas.drawText("empty canvas", 100, 300, paint);

        imageView.setImageBitmap(bitmap);
        imageView.setScaleType(ImageView.ScaleType.CENTER);

        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPicture();
            }
        });

        ((Button)findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawSomething();
            }
        });

        ((Button)findViewById(R.id.button3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                imageView.invalidate();
            }
        });

    }

    private void loadPicture() {
        AssetManager assetManager = getApplicationContext().getAssets();
        InputStream istr;
        try {
            istr = assetManager.open("pig.jpg");
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        canvas.drawBitmap(bitmap, 0f, 0f, null);

        imageView.invalidate();
    }


    private void drawSomething() {
        canvas.drawRect(400, 500, 600, 600, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        canvas.drawCircle(250, 150, 120, paint);

        imageView.invalidate();
    }
}
