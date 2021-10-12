package com.example.qr_scanner;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.WriterException;

import java.io.Writer;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class Generateqr extends AppCompatActivity {

    private TextView qrcodetv;
    private ImageView qrcodeiv;
    private TextInputEditText dataedt;
    private Button generateqrbtn;
    private QRGEncoder qrgEncoder;
    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generateqr);
        qrcodetv=findViewById(R.id.tvgenerateqr);
        qrcodeiv =findViewById(R.id.ivqrcode);
        dataedt =findViewById(R.id.edtdata);
        generateqrbtn =findViewById(R.id.btngenarate);
        generateqrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = dataedt.getText().toString();
                if (data.isEmpty()){
                    Toast.makeText(Generateqr.this,"Please enter some data to generate QR code...", "", Toast.LENGTH_SHORT).show();
                }else{
                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);
                    int width=point.x;
                    int height=point.y;
                    int dimen= width<height ? width:height;
                    dimen=dimen*3/4;

                    qrgEncoder =new QRGEncoder(dataedt.getText().toString(),null, QRGContents.Type.TEXT,dimen);
                    try{
                        bitmap =qrgEncoder.encodeAsBitmap();
                        qrcodetv.setVisibility(View.GONE);
                        qrcodeiv.setImageBitmap(bitmap);

                    }catch (WriterException e){
                        e.printStackTrace();
                    }


                }
            }
        });



    }
}