package com.example.myapplicationwer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplicationwer.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    private EditText txt_number1,txt_number2,txt_res;

    private RadioButton radio_sum,radio_rest,radio_div,radio_mult;

    private RadioGroup radio_group;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        txt_number1 = (EditText) binding.txtNumber1;
        txt_number2 = (EditText) binding.txtNumber2;
        txt_res = (EditText) binding.txtResultado;
        radio_sum = (RadioButton) binding.radioSum;
        radio_rest = (RadioButton) binding.radioSubstract;
        radio_div = (RadioButton) binding.radioDivide;
        radio_mult = (RadioButton) binding.radioMultiply;
        radio_group = (RadioGroup) binding.radioGroup;

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

        binding.btmCalcular.setOnClickListener((View v)->{
            if(radio_sum.isChecked()){
                sum();
            } else if(radio_rest.isChecked()){
                substract();
            } else if(radio_div.isChecked()){
                div();
            } else if(radio_mult.isChecked()){
                multiply();
            } else{
                showMessage();
            }

        });
    }

    public void showMessage(){
        Toast.makeText(this.getContext(), "No se ha seleccionado ninguna operacion", Toast.LENGTH_SHORT).show();
    }

    public String sum(){
        double val1 = Integer.parseInt(txt_number1.getText().toString());
        double val2 = Integer.parseInt(txt_number2.getText().toString());
        double sum = val1+val2;
        String respuesta = String.valueOf(sum);
        txt_res.setText(respuesta);
        return respuesta;
    }

    public String substract(){
        double val1 = Integer.parseInt(txt_number1.getText().toString());
        double val2 = Integer.parseInt(txt_number2.getText().toString());
        double res = val1-val2;
        String respuesta = String.valueOf(res);
        txt_res.setText(respuesta);
        return respuesta;
    }

    public String div(){
        double val1 = Integer.parseInt(txt_number1.getText().toString());
        double val2 = Integer.parseInt(txt_number2.getText().toString());
        String respuesta = "";
        if(val2!=0){
            double sum = val1/val2;
            respuesta = String.valueOf(sum);
            txt_res.setText(respuesta);
        }else {
            Toast.makeText(this.getContext(), "No se puede dividir por 0", Toast.LENGTH_SHORT).show();
        }

        return respuesta;
    }

    public String multiply(){
        double val1 = Integer.parseInt(txt_number1.getText().toString());
        double val2 = Integer.parseInt(txt_number2.getText().toString());
        double mult = val1*val2;
        String respuesta = String.valueOf(mult);
        txt_res.setText(respuesta);
        return respuesta;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}