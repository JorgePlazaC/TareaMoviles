package com.example.myapplicationwer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplicationwer.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    private EditText txt_number1,txt_number2;

    private TextView txt_res;

    private RadioButton radio_sum,radio_rest,radio_div,radio_mult;

    private CheckBox check_sum,check_rest,check_div,check_mult;

    private RadioGroup radio_group;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        txt_number1 = (EditText) binding.txtNumber1;
        txt_number2 = (EditText) binding.txtNumber2;
        txt_res = (TextView) binding.txtResultado;
        radio_sum = (RadioButton) binding.radioSum;
        radio_rest = (RadioButton) binding.radioSubstract;
        radio_div = (RadioButton) binding.radioDivide;
        radio_mult = (RadioButton) binding.radioMultiply;
        radio_group = (RadioGroup) binding.radioGroup;

        check_sum = (CheckBox) binding.checkBoxSum;
        check_rest = (CheckBox) binding.checkBoxRest;
        check_div = (CheckBox) binding.checkBoDiv;
        check_mult = (CheckBox) binding.checkBoxMult;

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        //Desmarcar radio

        check_sum.setOnClickListener((View v)->{
            desmarcarRadio();
        });

        check_rest.setOnClickListener((View v)->{
            desmarcarRadio();
        });

        check_div.setOnClickListener((View v)->{
            desmarcarRadio();
        });

        check_mult.setOnClickListener((View v)->{
            desmarcarRadio();
        });

        //Desmarcar CheckBox

        radio_sum.setOnClickListener((View v) ->{
            desmarcarCheckBox();
        });

        radio_rest.setOnClickListener((View v) ->{
            desmarcarCheckBox();
        });

        radio_div.setOnClickListener((View v) ->{
            desmarcarCheckBox();
        });

        radio_mult.setOnClickListener((View v) ->{
            desmarcarCheckBox();
        });

        binding.btmCalcular.setOnClickListener((View v)->{

            if(check_sum.isChecked() || check_rest.isChecked() || check_div.isChecked() || check_mult.isChecked()){
                if(txt_number1.getText().toString().equals("") || txt_number2.getText().toString().equals("")){
                    Toast.makeText(this.getContext(), "Ingrese valores", Toast.LENGTH_SHORT).show();
                    return;
                }
                operacionesCheckBox();
            } else{
                if(radio_sum.isChecked()){
                    sum(false);
                } else if(radio_rest.isChecked()){
                    substract(false);
                } else if(radio_div.isChecked()){
                    div(false);
                } else if(radio_mult.isChecked()){
                    multiply(false);
                } else{
                    showMessage();
                }
            }
        });
    }

    public void showMessage(){
        Toast.makeText(this.getContext(), "No se ha seleccionado ninguna operacion", Toast.LENGTH_SHORT).show();
    }

    public String sum(boolean checkBox){
        if(txt_number1.getText().toString().equals("") || txt_number2.getText().toString().equals("")){
            Toast.makeText(this.getContext(), "Ingrese valores", Toast.LENGTH_SHORT).show();
            return "";
        }
        double val1 = Integer.parseInt(txt_number1.getText().toString());
        double val2 = Integer.parseInt(txt_number2.getText().toString());
        double sum = val1+val2;
        String respuesta = String.valueOf(sum);
        if(!checkBox){
            txt_res.setText(respuesta);
        }
        return respuesta;
    }

    public String substract(boolean checkBox){
        if(txt_number1.getText().toString().equals("") || txt_number2.getText().toString().equals("")){
            Toast.makeText(this.getContext(), "Ingrese valores", Toast.LENGTH_SHORT).show();
            return "";
        }
        double val1 = Integer.parseInt(txt_number1.getText().toString());
        double val2 = Integer.parseInt(txt_number2.getText().toString());
        double res = val1-val2;
        String respuesta = String.valueOf(res);
        if(!checkBox){
            txt_res.setText(respuesta);
        }
        return respuesta;
    }

    public String div(boolean checkBox){
        if(txt_number1.getText().toString().equals("") || txt_number2.getText().toString().equals("")){
            Toast.makeText(this.getContext(), "Ingrese valores", Toast.LENGTH_SHORT).show();
            return "";
        }
        double val1 = Integer.parseInt(txt_number1.getText().toString());
        double val2 = Integer.parseInt(txt_number2.getText().toString());
        String respuesta = "";
        if(val2!=0){
            double sum = val1/val2;
            respuesta = String.valueOf(sum);
            if(!checkBox){
                txt_res.setText(respuesta);
            }
        }else {
            Toast.makeText(this.getContext(), "No se puede dividir por 0", Toast.LENGTH_SHORT).show();
        }

        return respuesta;
    }

    public String multiply(boolean checkBox){
        if(txt_number1.getText().toString().equals("") || txt_number2.getText().toString().equals("")){
            Toast.makeText(this.getContext(), "Ingrese valores", Toast.LENGTH_SHORT).show();
            return "";
        }
        double val1 = Integer.parseInt(txt_number1.getText().toString());
        double val2 = Integer.parseInt(txt_number2.getText().toString());
        double mult = val1*val2;
        String respuesta = String.valueOf(mult);
        if(!checkBox){
            txt_res.setText(respuesta);
        }
        return respuesta;
    }

    public void desmarcarRadio(){
        radio_sum.setChecked(false);
        radio_rest.setChecked(false);
        radio_div.setChecked(false);
        radio_mult.setChecked(false);
    }

    public void desmarcarCheckBox(){
        check_sum.setChecked(false);
        check_rest.setChecked(false);
        check_div.setChecked(false);
        check_mult.setChecked(false);
    }

    public void operacionesCheckBox(){
        String sum = "";
        String rest = "";
        String div = "";
        String mult = "";
        String resultado = "";
        if(check_sum.isChecked()){
            sum = "Suma = "+sum(true)+" "+"\n";
        }
        if(check_rest.isChecked()){
            rest = "Resta = "+substract(true)+" "+"\n";
        }
        if(check_div.isChecked()){
            div = "División = "+div(true)+" "+"\n";
        }
        if(check_mult.isChecked()) {
            mult = "Multiplicación = "+multiply(true)+" "+"\n";
        }
        resultado = sum+rest+div+mult;
        txt_res.setText(resultado);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}