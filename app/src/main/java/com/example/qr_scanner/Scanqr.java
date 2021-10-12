package com.example.qr_scanner;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.VIBRATE;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import eu.livotov.labs.android.camview.ScannerLiveView;
import eu.livotov.labs.android.camview.scanner.decoder.zxing.ZXDecoder;

public class Scanqr extends AppCompatActivity {

    private ScannerLiveView scannerLiveView;
    private TextView scannertv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generateqr);
        ScannerLiveView=findViewById(R.id.camview);
        scannertv=findViewById(R.id.tvscanneddata);
        if (checkPermission()){
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }else{
            requestPermission();
        }
        scannerLiveView.setScannerViewEventListener(new ScannerLiveView.ScannerViewEventListener() {
            @Override
            public void onScannerStarted(ScannerLiveView scanner) {
                Toast.makeText(Scanqr.this, "Scanner Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onScannerStopped(ScannerLiveView scanner) {
                Toast.makeText(Scanqr.this, "Scanner Stopped", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onScannerError(Throwable err) {

            }

            @Override
            public void onCodeScanned(String data) {
                scannertv.setText(data);

            }
        });

    }

    private boolean checkPermission(){
        int camer_permission= ContextCompat.checkSelfPermission(getApplicationContext(),CAMERA);
        int vibrate_permission= ContextCompat.checkSelfPermission(getApplicationContext(),VIBRATE);
        return camer_permission== PackageManager.PERMISSION_GRANTED && vibrate_permission==PackageManager.PERMISSION_GRANTED;

    }
    private void  requestPermission(){
        int  PERMISSION_CODE=200;
        ActivityCompat.requestPermissions(this,new String[]{CAMERA,VIBRATE},PERMISSION_CODE);

    }

    @Override
    protected void onPause() {
        ScannerLiveView.stopScanner();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ZXDecoder decoder = new ZXDecoder();
        decoder.setScanAreaPercent(0.8);
        scannerLiveView.setDecoder(decoder);
        scannerLiveView.startScanner();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length>0){
            boolean camerAccepted = grantResults[0]==PackageManager.PERMISSION_GRANTED;
            boolean vibrationAccepted = grantResults[1]==PackageManager.PERMISSION_GRANTED;
            if (camerAccepted && vibrationAccepted){
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}