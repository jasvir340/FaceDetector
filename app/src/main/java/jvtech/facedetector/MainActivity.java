package jvtech.facedetector;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button btnProgress,browse,capture;
    Spinner spinner;
    Bitmap myBitmap,tempBitmap;
    Canvas canvas;
    int count;

    int CAMERA_REQUEST = 999;
    int CAMERA_CODE = 1000;

    private final int PICK_IMAGE = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data!=null && data.getData()!=null)
        {
            Uri uri = data.getData();
            try
            {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                imageView.setImageBitmap(bitmap);
                myBitmap = bitmap;
                tempBitmap=Bitmap.createBitmap(myBitmap.getWidth(),myBitmap.getHeight(), Bitmap.Config.RGB_565);
                canvas=new Canvas(tempBitmap);
                canvas.drawBitmap(myBitmap,0,0,null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK)
        {
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            myBitmap =bitmap;
            tempBitmap= Bitmap.createBitmap(myBitmap.getWidth(),myBitmap.getHeight(), Bitmap.Config.RGB_565);
            canvas=new Canvas(tempBitmap);
            canvas.drawBitmap(myBitmap,0,0,null);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.imageView);
        btnProgress=(Button)findViewById(R.id.btnProgress);
        spinner=(Spinner)findViewById(R.id.spinner);
        browse=(Button)findViewById(R.id.browse);
        capture=(Button)findViewById(R.id.capture);
        getSupportActionBar().hide();

        if(checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED)
        {
            requestPermissions(new String[]{android.Manifest.permission.CAMERA},CAMERA_CODE);
        }

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i,"Select Picture"),PICK_IMAGE);
            }
        });

        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,CAMERA_REQUEST);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Rajni"))
                {
                    myBitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.rajni);
                    imageView.setImageBitmap(myBitmap);
                    tempBitmap=Bitmap.createBitmap(myBitmap.getWidth(),myBitmap.getHeight(), Bitmap.Config.RGB_565);
                    canvas=new Canvas(tempBitmap);
                    canvas.drawBitmap(myBitmap,0,0,null);
                }
                else if (parent.getItemAtPosition(position).equals("Jassi"))
                {
                    myBitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.sample);
                    imageView.setImageBitmap(myBitmap);
                    tempBitmap=Bitmap.createBitmap(myBitmap.getWidth(),myBitmap.getHeight(), Bitmap.Config.RGB_565);
                    canvas=new Canvas(tempBitmap);
                    canvas.drawBitmap(myBitmap,0,0,null);
                }
                else if (parent.getItemAtPosition(position).equals("Group Image"))
                {
                    myBitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.group);
                    imageView.setImageBitmap(myBitmap);
                    tempBitmap=Bitmap.createBitmap(myBitmap.getWidth(),myBitmap.getHeight(), Bitmap.Config.RGB_565);
                    canvas=new Canvas(tempBitmap);
                    canvas.drawBitmap(myBitmap,0,0,null);
                }
                else if (parent.getItemAtPosition(position).equals("Fake People"))
                {
                    myBitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.fake_people);
                    imageView.setImageBitmap(myBitmap);
                    tempBitmap=Bitmap.createBitmap(myBitmap.getWidth(),myBitmap.getHeight(), Bitmap.Config.RGB_565);
                    canvas=new Canvas(tempBitmap);
                    canvas.drawBitmap(myBitmap,0,0,null);
                }
                else if (parent.getItemAtPosition(position).equals("FPWE"))
                {
                    myBitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.fake_people_with_eyes);
                    imageView.setImageBitmap(myBitmap);
                    tempBitmap=Bitmap.createBitmap(myBitmap.getWidth(),myBitmap.getHeight(), Bitmap.Config.RGB_565);
                    canvas=new Canvas(tempBitmap);
                    canvas.drawBitmap(myBitmap,0,0,null);
                }
                else
                {
                    myBitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.man_or_dog);
                    imageView.setImageBitmap(myBitmap);
                    tempBitmap=Bitmap.createBitmap(myBitmap.getWidth(),myBitmap.getHeight(), Bitmap.Config.RGB_565);
                    canvas=new Canvas(tempBitmap);
                    canvas.drawBitmap(myBitmap,0,0,null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        final Paint rectPaint = new Paint();
        rectPaint.setStrokeWidth(5);
        rectPaint.setColor(Color.RED);
        rectPaint.setStyle(Paint.Style.STROKE);
        btnProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FaceDetector faceDetector=new FaceDetector.Builder(getApplicationContext())
                        .setTrackingEnabled(false)
                        .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                        .setMode(FaceDetector.FAST_MODE)
                        .build();
                if(!faceDetector.isOperational())
                {
                    Toast.makeText(MainActivity.this, "Face detector could not be set up on your device", Toast.LENGTH_SHORT).show();
                    return;
                }
                Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
                SparseArray<Face> sparseArray=faceDetector.detect(frame);
                count=sparseArray.size();
                for (int i=0;i<sparseArray.size();i++){
                    Face face = sparseArray.valueAt(i);
                    float x1=face.getPosition().x;
                    float y1=face.getPosition().y;
                    float x2=x1+face.getWidth();
                    float y2=y1+face.getHeight();
                    RectF rectF=new RectF(x1,y1,x2,y2);
                    canvas.drawRoundRect(rectF,2,2,rectPaint);
                }
                imageView.setImageDrawable(new BitmapDrawable(getResources(),tempBitmap));
                countDialog(count);
            }
        });
    }

    @Override
    public void onBackPressed() {
        dialog();
    }

    public void dialog()
    {
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Do you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No",null)
                .create().show();
    }

    public void countDialog(int count)
    {
        if(count>=1)
        {
            new AlertDialog.Builder(this)
                    .setTitle("Face Count")
                    .setMessage("Number of face Detected: "+(count))
                    .setPositiveButton("OK",null)
                    .create().show();
        }
        else
        {
            new AlertDialog.Builder(this)
                    .setTitle("Face Count")
                    .setMessage("No face detected !!!")
                    .setPositiveButton("OK",null)
                    .create().show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == CAMERA_CODE)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this,"Permission denied",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
