package com.example.basiccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView result,solution;
    MaterialButton button_c,open_bracket,close_bracket,button_AC,button_point;
    MaterialButton button_divide,button_multiply,button_sub,button_add,button_eval;
    MaterialButton button_0,button_1,button_2,button_3,button_4,button_5,button_6,button_7,button_8,button_9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);
        solution =findViewById(R.id.solution);

        assignId(button_c,R.id.button_c);
        assignId(open_bracket,R.id.open_bracket);
        assignId(close_bracket,R.id.close_bracket);
        assignId(button_divide,R.id.button_divide);
        assignId(button_multiply,R.id.button_multiply);
        assignId(button_add,R.id.button_add);
        assignId(button_sub,R.id.button_sub);
        assignId(button_eval,R.id.button_eval);
        assignId(button_0,R.id.button_0);
        assignId(button_1,R.id.button_1);
        assignId(button_2,R.id.button_2);
        assignId(button_3,R.id.button_3);
        assignId(button_4,R.id.button_4);
        assignId(button_5,R.id.button_5);
        assignId(button_6,R.id.button_6);
        assignId(button_7,R.id.button_7);
        assignId(button_8,R.id.button_8);
        assignId(button_9,R.id.button_9);
        assignId(button_point,R.id.button_point);
        assignId(button_AC,R.id.button_AC);
    }

    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttontext =button.getText().toString();
        String DataToCalculate= solution.getText().toString();

        if(buttontext.equals("AC")){
            solution.setText("");
            result.setText("0");

            return;
        }
        if(buttontext.equals("=")){
            solution.setText(result.getText());
            return;
        }
        if(buttontext.equals("C")){
            if(DataToCalculate.equals("0") || DataToCalculate.equals("")){
                DataToCalculate="0";
            }
            else{
                DataToCalculate =DataToCalculate.substring(0,DataToCalculate.length()-1);
            }
        }else{
            DataToCalculate = DataToCalculate+buttontext;
        }
        solution.setText(DataToCalculate);
        String finalResult = getresult(DataToCalculate);
        if(!finalResult.equals("Err") ){
            result.setText(finalResult);
        }
    }
    String getresult(String data){
        try {
            Context context =Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable =context.initStandardObjects();
            String Finalresult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (Finalresult.endsWith(".0")){
                Finalresult = Finalresult.replace(".0","");
            }
            return Finalresult;
        }catch(Exception e){
            return "err";
        }
    }
}