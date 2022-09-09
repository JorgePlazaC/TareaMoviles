package com.example.myapplicationwer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplicationwer.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    //Declaración de variables

    private FragmentSecondBinding binding;

    private EditText txt_num1, txt_num2,txt_resp2;

    private Spinner spiOptions;

    private OperacionAdapter operacionAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);

        //Asignación de valores a las variables
        //Texto
        txt_num1 = (EditText) binding.txtNumber1;
        txt_num2 = (EditText) binding.txtNumber2;
        txt_resp2 = (EditText) binding.txtResp;
        //Spinner
        spiOptions = (Spinner) binding.spinner;


        //Configuración de spinner

        String[] operations = {
                "Sumar","Restar","Multiplicar","Dividir",
        };
        int imgOperaciones[] = {R.drawable.suma, R.drawable.resta, R.drawable.multiplicacion, R.drawable.division};

        //Adapter

        operacionAdapter = new OperacionAdapter(getActivity().getApplicationContext(),imgOperaciones,operations);


        //ArrayAdapter<String>adapter= new ArrayAdapter<>(this.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,operations);


        spiOptions.setAdapter(operacionAdapter);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Botón que lleva al primer fragment
        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        //Botón que lleva al tercer fragment
        binding.buttonThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_thirdFragment);
            }
        });

        //Botón que da la orden para realizar la operación matematica
        binding.bttCalculate.setOnClickListener((View v) -> {
            String select = operacionAdapter.nombreOperaciones[Integer.parseInt(spiOptions.getSelectedItem().toString())];
            switch (select){
                case "Sumar":
                    sum();
                    break;
                case "Restar":
                    substract();
                    break;
                case "Multiplicar":
                    multiply();
                    break;
                case "Dividir":
                    div();
                    break;
                default:
                    showMessage();
                    break;
            }
        });
    }

    //Metodos usados tras presionar el botón calcular

    public void showMessage(){
        Toast.makeText(this.getContext(), "No se ha seleccionado ninguna operacion", Toast.LENGTH_SHORT).show();
    }

    public String sum(){
        if(txt_num1.getText().toString().equals("") || txt_num2.getText().toString().equals("")){
            Toast.makeText(this.getContext(), "Ingrese valores", Toast.LENGTH_SHORT).show();
            return "";
        }
        double val1 = Integer.parseInt(txt_num1.getText().toString());
        double val2 = Integer.parseInt(txt_num2.getText().toString());
        double sum = val1+val2;
        String respuesta = String.valueOf(sum);
        txt_resp2.setText(respuesta);
        return respuesta;
    }

    public String substract(){
        if(txt_num1.getText().toString().equals("") || txt_num2.getText().toString().equals("")){
            Toast.makeText(this.getContext(), "Ingrese valores", Toast.LENGTH_SHORT).show();
            return "";
        }
        double val1 = Integer.parseInt(txt_num1.getText().toString());
        double val2 = Integer.parseInt(txt_num2.getText().toString());
        double res = val1-val2;
        String respuesta = String.valueOf(res);
        txt_resp2.setText(respuesta);
        return respuesta;
    }

    public String div(){
        if(txt_num1.getText().toString().equals("") || txt_num2.getText().toString().equals("")){
            Toast.makeText(this.getContext(), "Ingrese valores", Toast.LENGTH_SHORT).show();
            return "";
        }
        double val1 = Integer.parseInt(txt_num1.getText().toString());
        double val2 = Integer.parseInt(txt_num2.getText().toString());
        String respuesta = "";
        if(val2!=0){
            double sum = val1/val2;
            respuesta = String.valueOf(sum);
            txt_resp2.setText(respuesta);
        }else {
            Toast.makeText(this.getContext(), "No se puede dividir por 0", Toast.LENGTH_SHORT).show();
        }

        return respuesta;
    }

    public String multiply(){
        if(txt_num1.getText().toString().equals("") || txt_num2.getText().toString().equals("")){
            Toast.makeText(this.getContext(), "Ingrese valores", Toast.LENGTH_SHORT).show();
            return "";
        }
        double val1 = Integer.parseInt(txt_num1.getText().toString());
        double val2 = Integer.parseInt(txt_num2.getText().toString());
        double mult = val1*val2;
        String respuesta = String.valueOf(mult);
        txt_resp2.setText(respuesta);
        return respuesta;
    }

    //Metodo del sistema
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}