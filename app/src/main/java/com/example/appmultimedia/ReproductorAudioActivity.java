package com.example.appmultimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class ReproductorAudioActivity extends AppCompatActivity {
    private ImageView ivStopAudio;
    private ImageView ivPlayAudio;
    private ImageView ivPauseAudio;
    private MediaPlayer mp = null;
    private int position;
    private Spinner sCanciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor_audio);
        // Inicializar las vistas
        ivStopAudio = findViewById(R.id.ivStopAudio);
        ivPlayAudio = findViewById(R.id.ivPlayAudio);
        ivPauseAudio = findViewById(R.id.ivPauseAudio);
        sCanciones = findViewById(R.id.sCanciones);

        // Listener que se activa al seleccionar un elemento de la lista (spinner)
        sCanciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (mp != null) {
                    mp.release(); // liberar objeto
                }
                if (position == 1) { // Cancion 1
                    mp = MediaPlayer.create(view.getContext(), R.raw.cancion1);
                } else if (position == 2) { // Cancion 2
                    mp = MediaPlayer.create(view.getContext(), R.raw.cancion2);
                } else if (position == 3) { // Cancion 3
                    mp = MediaPlayer.create(view.getContext(), R.raw.cancion3);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { // Si no se selecciona una cancion, aviso de error
                Toast.makeText(parent.getContext(), "Escoje una canción.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Metodo para reproducir audio
    public void iniciar(View vista) {
        if (sCanciones.getSelectedItemPosition() == 0) { // Si no se selecciona una cancion, aviso de error
            Toast.makeText(this, "¡ERROR! Selecciona una canción.", Toast.LENGTH_SHORT).show();
        } else {
            mp.start(); // Reproducir audio
        }
    }

    // Metodo para parar audio
    public void pausar(View vista) {
        if (sCanciones.getSelectedItemPosition() == 0) { // Si no se selecciona una cancion, aviso de error
            Toast.makeText(this, "¡ERROR! Selecciona una canción.", Toast.LENGTH_SHORT).show();
        } else {
            if (mp.isPlaying() == false) { // Si MediaPlayer no esta reproduciendo, aviso de error
                Toast.makeText(this, "¡Error! Inicia una canción.", Toast.LENGTH_LONG).show();
            }
            mp.pause(); // Parar audio
        }
    }

    // Metodo para detener audio
    public void detener(View vista) {
        if (sCanciones.getSelectedItemPosition() == 0) { // Si no se selecciona una cancion, aviso de error
            Toast.makeText(this, "¡ERROR! Selecciona una canción.", Toast.LENGTH_SHORT).show();
        } else {
            // Si esta en pausa
            if (mp != null && !mp.isPlaying()) {
                //mp.stop(); // Detener audio
                mp.pause();
                mp.seekTo(0); // Ir a un punto concreto del audio (0 > comienzo)
            }
            // Si esta reproduciendo
            if (mp != null && mp.isPlaying()) {
                //mp.stop(); // Detener audio
                mp.pause();
                mp.seekTo(0); // Ir a un punto concreto del audio (0 > comienzo)
            }
        }
    }

    // Metodo para volver a la bienvenida
    public void volver(View vista) {
        // Metodo finish
        finish();
    }

}
