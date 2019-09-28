package com.example.pye.Tema4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pye.R;

import java.util.ArrayList;
import java.util.Arrays;

public class Tema_4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tema_4);
        ((Button) findViewById(R.id.btn_T4_Calcular)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Datos[] = ((EditText) findViewById(R.id.txtV_T4_Entrada)).getText().toString().split(",");
                double CantidadClases = Double.parseDouble(((EditText) findViewById(R.id.txtV_T4_CantidadIntervalos)).getText().toString());
                int CantidadDatos = Datos.length, Amplitud = 0;
                ArrayList<int[]> Clases = new ArrayList<>();

                int[] DatosINT = new int[Datos.length];

                for (int i = 0; i < Datos.length; i++) {
                    DatosINT[i] = Integer.parseInt(Datos[i]);
                }
                Amplitud = (int) Math.ceil((DatosINT[DatosINT.length - 1] - DatosINT[0]) / CantidadClases);
                Arrays.sort(DatosINT);
                for (int i = 0; i < CantidadClases; i++) {
                    int Li = (Amplitud * i) + DatosINT[0], Ls = (Amplitud * (i + 1)) + DatosINT[0];
                    Clases.add(new int[]{Li, Ls, ((Amplitud * i) + (Amplitud * (i + 1))) / 2, 0, 0});
                }

                for (int D : DatosINT) {
                    for (int i = 0; i < CantidadClases; i++) {
                        int Li = Clases.get(i)[0], Ls = Clases.get(i)[1];
                        //System.out.println(Li + " : " + Ls);
                        //System.out.println(D+" : "+Li+" : "+Ls);
                        if (Li <= D && Ls > D) {
                            Clases.get(i)[3]++;
                        }
                    }
                }

                double Promedio = 0.0, Mediana = 0.0, Moda = 0;
                int F = 0;
                int ClaseMax = -1, Max = -1;
                for (int i = 0; i < CantidadClases; i++) {
                    Promedio += Clases.get(i)[2] * Clases.get(i)[3];
                    if (Max < Clases.get(i)[3]) {
                        Max = Clases.get(i)[3];
                        ClaseMax = i;
                    }
                    F += Clases.get(i)[3];
                    Clases.get(i)[4] = F;
                }
                Promedio /= (double) Datos.length;

                //System.out.println(Clases.get(ClaseMax)[0] + " : " + Clases.get(ClaseMax - 1)[4] + " : " + Clases.get(ClaseMax)[3] + " : " + Amplitud);
                Mediana = Clases.get(ClaseMax)[0] +
                        (((Datos.length / 2.0) - Clases.get(ClaseMax - 1)[4]) /
                                Clases.get(ClaseMax)[3]) *
                                (double) Amplitud;
                double d1 = Clases.get(ClaseMax)[3] - Clases.get(ClaseMax - 1)[3], d2 = Clases.get(ClaseMax)[3] - Clases.get(ClaseMax + 1)[3];
                System.out.println(Clases.get(ClaseMax)[0] + " : " + d1 + " : " + " : " + d2 + " : " + Amplitud);
                Moda = Clases.get(ClaseMax)[0] + ((d1 / (d1 + d2)) * (double) Amplitud);
                ((TextView) findViewById(R.id.txtV_T4_Promedio)).setText("Promedio " + Promedio);
                ((TextView) findViewById(R.id.txtV_T4_Moda)).setText("Moda " + Moda);
                ((TextView) findViewById(R.id.txtV_T4_Media)).setText("Media " + Mediana);

            }
        });
    }
}