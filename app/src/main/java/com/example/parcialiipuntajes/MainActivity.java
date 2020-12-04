package com.example.parcialiipuntajes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseDatabase db;
    private EditText puntajeId;
    private Button okBtn;
    private TextView lecturaId;

    //Guardar Pregunta
    private String guardarPregunta;
    private String promedio;
    private String listaPromedio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Database
        db = FirebaseDatabase.getInstance();

        //Atributos Inputs y boton
        puntajeId = findViewById(R.id.puntajeId);
        okBtn = findViewById(R.id.okBtn);
        lecturaId = findViewById(R.id.lecturaId);

        okBtn.setOnClickListener(this);

        if(db.getReference().child("Preguntas").child("id") != null) { //Intente este metodo para que cuando el firebase este vacio se pueda abrir el celular pero no se pudo.
            db.getReference().child("Preguntas").child("id").addValueEventListener(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot data) {
                            Pregunta pregunta = data.getValue(Pregunta.class);
                            lecturaId.setText("");
                            lecturaId.append(pregunta.getPregunta());
                            guardarPregunta = pregunta.getPregunta();
                            //Formula de promedio es Las sumas de los numeros/totalnumeros, ej: (n1 + n2)/ 2
                            listaPromedio = pregunta.getPromedio() + ":" + pregunta.getPuntaje();
                            //promedio = (Integer.parseInt(pregunta.getPuntaje()) + Integer.parseInt(puntajeId.getText().toString()));
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            lecturaId.setText("No hay info");
                        }
                    }
            );
        }
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.okBtn:
                //Aqui se pone literalmente el database del firebase.
                //DatabaseReference reference = db.getReference().child("Preguntas").child("id");

                //Lo separe para que se pueda ver mejor las letras y no lo actualizado raro que me salia antes...
                DatabaseReference reference = db.getReference().child("Preguntas").child("id");

                //Aqui intente que funcionara de manera eficiente, pues que se actualice mi firebase sin tener que borrar nada, el problema es que cada vez que se guarda, se borra, entonces sale como si no estuviera nada.
                //Ah menos que use localStorage... lo voy a intentar con localStorage.
                //Tampoco funciono entonces lo borre de inmediato.

                //String preguntasString = db.getReference().child("Preguntas").child("id").child("pregunta").getKey();

                //Leer literalmente firebase para que porfin funcione esto porfavor
                //Si funciono pero me toco ponerlo arriba.

                //PreguntasPrueba
                Pregunta preguntas = new Pregunta(
                        guardarPregunta,
                        puntajeId.getText().toString(),
                        listaPromedio,
                        promedio
                );

                reference.setValue(preguntas);

                break;
        }
    }
}