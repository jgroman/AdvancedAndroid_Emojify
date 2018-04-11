package com.example.android.emojify;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

public class Emojifier {

    @SuppressWarnings("unused")
    private static final String TAG = Emojifier.class.getSimpleName();

    static void detectFaces(Context context, Bitmap bitmap) {

        FaceDetector faceDetector = new FaceDetector.Builder(context)
                .setTrackingEnabled(false)
                .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                .build();

        if (!faceDetector.isOperational()) {
            new AlertDialog.Builder(context).setMessage("Could not set up the face detector").show();
            return;
        }

        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
        SparseArray<Face> faces = faceDetector.detect(frame);
        
        int facesDetected = faces.size();

        Log.d(TAG, "detectFaces: " + facesDetected);
        
        if (facesDetected == 0) {
            Toast.makeText(context, "No faces detected", Toast.LENGTH_SHORT).show();
        }

        faceDetector.release();
    }
}
