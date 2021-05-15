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

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(!((tp.getCurrentHour() >= 8 && tp.getCurrentMinute() >= 0) && (tp.getCurrentHour() <= 20 && tp.getCurrentMinute() <= 59))) {
                    tp.setCurrentHour(19);
                    tp.setCurrentMinute(30);
                    Toast.makeText(MainActivity.this, "Restaurant reservations are only valid between 8:00AM to 8:59PM", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etFirst.getText().toString().trim().equalsIgnoreCase("") && !etLast.getText().toString().trim().equalsIgnoreCase("")) {
                    if(!etPhone.getText().toString().trim().equalsIgnoreCase("")) {
                        if(etPhone.getText().toString().length() == 8 && (etPhone.getText().toString().startsWith("8") || etPhone.getText().toString().startsWith("9"))) {
                            if(!etPax.getText().toString().trim().equalsIgnoreCase("")) {
                                int pax = Integer.parseInt(etPax.getText().toString());
                                if(pax >= 1 && pax <= 8) {
                                    String firstName = etFirst.getText().toString();
                                    String lastName = etLast.getText().toString();
                                    String phone = etPhone.getText().toString();
                                    String table;
                                    String date = dp.getDayOfMonth() + "/" + (dp.getMonth()+1) + "/" + dp.getYear();
                                    String time = tp.getCurrentHour() + ":" + tp.getCurrentMinute();
                                    if(cbSmoker.isChecked()) {
                                        table = "smoking";
                                    } else {
                                        table = "non-smoking";
                                    }
                                    Toast.makeText(MainActivity.this, String.format("Thank you for making a reservation %s. \nReservation Date: %s \nReservation Time: %s \nYou have requested for a %s table for %d people", firstName, date, time, table, pax), Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "Invalid reservation size.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Please fill in the reservation size field.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Invalid phone number.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Please fill in the phone number field", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please fill in the name fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}