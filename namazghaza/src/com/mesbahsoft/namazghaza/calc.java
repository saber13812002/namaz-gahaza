package com.mesbahsoft.namazghaza;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: fateme
 * Date: 11/10/13
 * Time: 2:40 AM
 * To change this template use File | Settings | File Templates.
 */




public class calc extends Activity implements View.OnClickListener{



    private db_helper RokatDBHelper;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc);

        Button OkButton = (Button) findViewById(R.id.btnOK);
        OkButton.setOnClickListener(this);


        RokatDBHelper = new db_helper(this);
    }

    @Override
    public void onClick(View view) {

        TextView txtYear = (TextView) findViewById(R.id.txtYears);
        String value= txtYear.getText().toString();
        TextView txtMonth = (TextView) findViewById(R.id.txtMonths);
        String value2= txtYear.getText().toString();
        TextView txtDay = (TextView) findViewById(R.id.txtDays);
        String value3= txtYear.getText().toString();
        TextView txtRokatRoo = (TextView) findViewById(R.id.txtRokatRooz);
        String value4= txtYear.getText().toString();

        int total=0;
        try
        {
            int finalValue=Integer.parseInt(value);
            int finalValue2=Integer.parseInt(value2);
            int finalValue3=Integer.parseInt(value3);
            int finalValue4=Integer.parseInt(value4);
            total= finalValue4*((finalValue*365)+(finalValue2*31)+(finalValue3));

        }
        catch (NumberFormatException e)
        {
            // handle the exception
        }

        RokatDBHelper.addGhazaRokatRow(total);

        finish();
    }
}