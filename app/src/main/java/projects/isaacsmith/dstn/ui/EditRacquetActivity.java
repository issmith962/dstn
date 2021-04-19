package projects.isaacsmith.dstn.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.apache.commons.lang3.text.WordUtils;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.UUID;

import projects.isaacsmith.dstn.R;
import projects.isaacsmith.dstn.model.Racquet;
import projects.isaacsmith.dstn.presenter.AddRacquetPresenter;
import projects.isaacsmith.dstn.presenter.EditRacquetPresenter;

public class EditRacquetActivity extends AppCompatActivity {
    EditRacquetPresenter mEditRacquetPresenter;

    EditText clientNameEditText;
    DatePickerDialog datePicker;
    EditText stringBrandEditText;
    EditText stringTypeEditText;
    EditText tensionEditText;
    EditText quantityEditText;
    EditText notesEditText;

    RadioButton selectedUnit;
    RadioGroup units;
    TextView dateDisplay;

    Button saveButton;
    Button discardButton;

    String clientName;
    LocalDate date;
    String stringBrand;
    String stringType;
    String tension;
    String tensionUnit;
    String quantity;
    String notes;
    UUID id;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_racquet);
        mEditRacquetPresenter = new EditRacquetPresenter();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView titleView = toolbar.findViewById(R.id.toolbar_title);
        titleView.setText(R.string.edit_racquet);

        clientNameEditText = (EditText)findViewById(R.id.client_name_edit_text);
        stringBrandEditText = (EditText)findViewById(R.id.string_brand_edit_text);
        stringTypeEditText = (EditText)findViewById(R.id.string_type_edit_text);
        tensionEditText = (EditText)findViewById(R.id.tension_edit_text);
        quantityEditText = (EditText)findViewById(R.id.quantity_edit_text);
        notesEditText = (EditText)findViewById(R.id.notes_edit_text);

        units = findViewById(R.id.units);
        dateDisplay = findViewById(R.id.date_text);
        saveButton = (Button)findViewById(R.id.save_button);
        saveButton.setText("Save changes");
        discardButton = (Button)findViewById(R.id.discard_button);
        discardButton.setText("Discard changes");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String origClientName = bundle.getString("clientName");
            if (origClientName != null) {
                clientNameEditText.setText(origClientName);
            }

            String origStringBrand = bundle.getString("stringBrand");
            if (origStringBrand != null) {
                stringBrandEditText.setText(origStringBrand);
            }

            String origStringType = bundle.getString("stringType");
            if (origStringType != null) {
                stringTypeEditText.setText(origStringType);
            }

            String origTension = bundle.getString("tension");
            if (origTension != null) {
                tensionEditText.setText(origTension);
            }

            String origQuantity = bundle.getString("quantity");
            if (origQuantity != null) {
                quantityEditText.setText(origQuantity);
            }

            String origNotes = bundle.getString("notes");
            if (origNotes != null) {
                notesEditText.setText(origNotes);
            }

            String origUnit = bundle.getString("unit");
            if (origUnit != null) {
                if (origUnit.equals("lb")) {
                    ((RadioButton)findViewById(R.id.lb_unit)).setChecked(true);
                } else if (origUnit.equals("kg")) {
                    ((RadioButton)findViewById(R.id.kg_unit)).setChecked(true);
                }
            }

            LocalDate origDate = (LocalDate) getIntent().getSerializableExtra("date");
            if (origDate != null) {
                date = origDate;
                String displayDate = WordUtils.capitalize(date.getDayOfWeek().toString().substring(0, 3).toLowerCase()) + ", " +
                        WordUtils.capitalize(date.getMonth().toString().substring(0, 3).toLowerCase()) +
                        " " + date.getDayOfMonth() + ", " + date.getYear();
                dateDisplay.setText(displayDate);
            }
            id = UUID.fromString(bundle.getString("id"));
        }
        saveButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clientName = clientNameEditText.getText().toString();
                        stringBrand = stringBrandEditText.getText().toString();
                        stringType = stringTypeEditText.getText().toString();
                        tension = tensionEditText.getText().toString();
                        selectedUnit = (RadioButton)findViewById(units.getCheckedRadioButtonId());
                        tensionUnit = selectedUnit.getText().toString();
                        quantity = quantityEditText.getText().toString();
                        notes = notesEditText.getText().toString();
                        mEditRacquetPresenter.editRacquet(id, date, clientName, stringBrand, stringType, tension, tensionUnit, quantity, notes);

                        finish();
                    }
                }
        );
        discardButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                }
        );
        findViewById(android.R.id.content).setFocusableInTouchMode(true);
        findViewById(android.R.id.content).clearFocus();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int[] sourceCoordinates = new int[2];
            v.getLocationOnScreen(sourceCoordinates);
            float x = ev.getRawX() + v.getLeft() - sourceCoordinates[0];
            float y = ev.getRawY() + v.getTop() - sourceCoordinates[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom()) {
                hideKeyboard(this);
            }

        }
        return super.dispatchTouchEvent(ev);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null) {
            activity.getWindow().getDecorView();
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
                findViewById(android.R.id.content).clearFocus();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void showDatePickerDialog(View v) {
//        final Calendar cldr = Calendar.getInstance();
//        int day = cldr.get(Calendar.DAY_OF_MONTH);
//        int month = cldr.get(Calendar.MONTH);
//        int year = cldr.get(Calendar.YEAR);

        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();
        // date picker dialog
        datePicker = new DatePickerDialog(EditRacquetActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        LocalDate newDate = LocalDate.of(year, monthOfYear + 1, dayOfMonth);
                        String dayOfWeek = WordUtils.capitalize(newDate.getDayOfWeek().toString().toLowerCase());
                        String displayDate = dayOfWeek.substring(0, 3) + ", " +
                                WordUtils.capitalize(Month.of(monthOfYear + 1).name().substring(0, 3).toLowerCase()) +
                                " " + dayOfMonth + ", " + year;
                        dateDisplay.setText(displayDate);
                        date = newDate;
                    }
                }, year, month, day);
        datePicker.updateDate(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
        datePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePicker.show();
    }
}