package com.example.appmultimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private SoundPool sp;
    private int idSonido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Clase SoundPool para audios cortos
        sp = new SoundPool.Builder().build();
        idSonido = sp.load(this, R.raw.golpe, 1);
    }

    public void reproducirSonido(View vista) {
        sp.play(idSonido, 1, 1, 1, 0, 0);
    }

    // Metodo para entrar en el reproductor de audio
    public void entrarReproducirAudio(View vista) {
        Intent i = new Intent(this, ReproductorAudioActivity.class);
        startActivity(i);
    }

    // Metodo para entrar en el reproductor de video
    public void entrarReproducirVideo(View vista) {
        Intent i = new Intent(this, ReproductorVideoActivity.class);
        startActivity(i);
    }

    // Metodo para salir de la aplicacion con dialogo de confirmacion de salida
    public void salir(View vista) {
        new AlertDialog.Builder(this)
                // .setTitle("Salir")
                .setTitle("Salir")
                // .setMessage("¿Desea salir de la App?")
                .setMessage("¿Desea salir de la App?")
                // .setPositiveButton("Sí", new DialogInterface.OnClickListener(){
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                // .setNegativeButton("No", null)
                .setNegativeButton("No", null)
                .show();
    }

}