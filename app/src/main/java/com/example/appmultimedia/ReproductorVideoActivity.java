package com.example.appmultimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

public class ReproductorVideoActivity extends AppCompatActivity {
    private ImageView ivStopVideo;
    private ImageView ivPlayVideo;
    private ImageView ivPauseVideo;
    //private Button bStopVideo;
    //private Button bPlayVideo;
    //private Button bPauseVideo;
    private VideoView vv_video;
    private Spinner sVideos;
    private int posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor_video);
        // Inicializar las vistas
        ivStopVideo = findViewById(R.id.ivStopVideo);
        ivPlayVideo = findViewById(R.id.ivPlayVideo);
        ivPauseVideo = findViewById(R.id.ivPauseVideo);
        //bStopVideo = findViewById(R.id.bStopVideo);
        //bPlayVideo = findViewById(R.id.bPlayVideo);
        //bPauseVideo = findViewById(R.id.bPauseVideo);
        vv_video = findViewById(R.id.vv_video);
        sVideos = findViewById(R.id.sVideos);
    }

    // Metodo que selecciona video
    public void escogerVideo() {
        if (sVideos.getSelectedItemPosition() == 1) { // Video de Edimburgo
            String ruta = "android.resource://" + getPackageName() + "/" + R.raw.edimburgo;
            vv_video.setVideoURI(Uri.parse(ruta));
        } else if (sVideos.getSelectedItemPosition() == 2) { // Video de Londres
            String ruta = "android.resource://" + getPackageName() + "/" + R.raw.londres;
            vv_video.setVideoURI(Uri.parse(ruta));
        } else if (sVideos.getSelectedItemPosition() == 3) { // Video de Nueva York
            String ruta = "android.resource://" + getPackageName() + "/" + R.raw.nuevayork;
            vv_video.setVideoURI(Uri.parse(ruta));
        } else { // Si no se selecciona un video, aviso de error
            Toast.makeText(this, "Escoje un vídeo.", Toast.LENGTH_SHORT).show();
        }
    }

    // Metodo para reproducir video
    public void iniciar(View vista) {
        if (sVideos.getSelectedItemPosition() == 0) { // Si no se selecciona un video, aviso de error
            Toast.makeText(this, "¡ERROR! Selecciona un vídeo.", Toast.LENGTH_SHORT).show();
        } else if (posicion != 0) {
            vv_video.seekTo(posicion); // Ir a una posicion concreta del video
            vv_video.start(); // Continuar video
        } else {
            escogerVideo(); // Seleccionar video
            vv_video.start(); // Reproducir video
        }
    }

    // Metodo para parar video
    public void pausar(View vista) {
        if (sVideos.getSelectedItemPosition() == 0) { // Si no se selecciona un video, aviso de error
            Toast.makeText(this, "¡ERROR! Selecciona un vídeo.", Toast.LENGTH_SHORT).show();
        } else {
            if (!vv_video.isPlaying()) { // Si VideoView no esta reproduciendo, aviso de error
                Toast.makeText(this, "¡Error! Inicia un vídeo.", Toast.LENGTH_LONG).show();
            }
            if (vv_video.isPlaying()) // Si esta reproduciendo
                posicion = vv_video.getCurrentPosition(); // Obtener la posicion actual del video
            vv_video.pause(); // Parar video
        }
    }

    // Metodo para detener video
    public void detener(View vista) {
        if (sVideos.getSelectedItemPosition() == 0) { // Si no se selecciona un video, aviso de error
            Toast.makeText(this, "¡ERROR! Selecciona un vídeo.", Toast.LENGTH_SHORT).show();
        } else {
            if (!vv_video.isPlaying() && posicion == 0) { // Si VideoView no esta reproduciendo, aviso de error
                Toast.makeText(this, "¡Error! Inicia un vídeo.", Toast.LENGTH_LONG).show();
            } else {
                vv_video.stopPlayback(); // Detener vídeo
                posicion = 0;
            }
        }
    }

    // Metodo para volver a la bienvenida
    public void volver(View vista) {
        // Método finish
        finish();
    }

}