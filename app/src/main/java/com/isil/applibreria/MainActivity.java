package com.isil.applibreria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    //declaracion
    RadioGroup radioLibros;
    RadioGroup radioPagos;
    EditText cantidad;
    TextView resultado;
    DecimalFormat df=new DecimalFormat("0.00");
    double precio=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioLibros=findViewById(R.id.rgLibros);
        radioPagos=findViewById(R.id.rgPagos);
        cantidad=findViewById(R.id.txtcantidad);
        resultado=findViewById(R.id.txtresultado);
    }
    public void cantidad(View view){
        boolean es_tarjeta=false;
        String mensaje="";

        switch(radioPagos.getCheckedRadioButtonId()){
            case R.id.tarjeta:
                es_tarjeta=true;
                break;
            case R.id.efectivo:
                es_tarjeta=false;
                break;
            default:
                mensaje="Elija un tipo de pago";
        }

        switch (radioLibros.getCheckedRadioButtonId()){
            case R.id.rbA:
                precio=40;
                break;
            case R.id.rbB:
                precio=30;
                if(es_tarjeta)precio=precio+(precio*0.185);
                break;
            case R.id.rbC:
                precio=25;
                if(!es_tarjeta)precio=precio-(precio*0.40);
                break;
            default:
                mensaje=mensaje+" \n"+"Elija un tipo de libro";
        }

        int cant;
        if(!cantidad.getText().toString().isEmpty()){
            cant=Integer.parseInt(cantidad.getText().toString());
            double total=cant*precio;
            resultado.setText(df.format(total));
        }else{
            mensaje=mensaje+" \n"+"Elija una cantidad";
            resultado.setText(mensaje);
        }
    }
}
