package sg.edu.rp.c346.id20040654.l04reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatePicker dp;
    TimePicker tp;
    EditText etFirst;
    EditText etLast;
    EditText etPhone;
    EditText etPax;
    CheckBox cbSmoker;
    Button btnConfirm;
    Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        etFirst = findViewById(R.id.editTextFirstName);
        etLast = findViewById(R.id.editTextLastName);
        etPhone = findViewById(R.id.editTextPhone);
        etPax = findViewById(R.id.editTextPax);
        cbSmoker = findViewById(R.id.checkBoxSmoker);
        btnConfirm = findViewById(R.id.buttonConfirm);
        btnReset = findViewById(R.id.buttonReset);

        dp.updateDate(2020, 5, 1);
        tp.setCurrentHour(19);
        tp.setCurrentMinute(30);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // dp.setMinDate(System.currentTimeMillis() - 10000);
                if(!etFirst.getText().toString().trim().equalsIgnoreCase("") && !etLast.getText().toString().trim().equalsIgnoreCase("")) {
                    if(!etPhone.getText().toString().trim().equalsIgnoreCase("")) {
                        if(etPhone.getText().toString().length() == 8 && (etPhone.getText().toString().startsWith("8") || etPhone.getText().toString().startsWith("9"))) {
                            if(!etPax.getText().toString().trim().equalsIgnoreCase("")) {
                                int pax = Integer.parseInt(etPax.getText().toString());
                                if(pax >= 1 && pax <= 8) {
                                    
                                } else {
                                    Toast.makeText(MainActivity.this, "Invalid reservation size.", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Please fill in the reservation size field.", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Invalid phone number.", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Please fill in the phone number field", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please fill in the name fields", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}