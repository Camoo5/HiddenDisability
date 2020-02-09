package com.tenacity.hiddendisabilities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class AddEditHiddenDisabilityActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "com.tenacity.hiddendisabilities.EXTRA_ID";
    public static final String EXTRA_DISABILITY =
            "com.tenacity.hiddendisabilities.EXTRA_DISABILITY";
    public static final String EXTRA_SPECIALIST =
            "com.tenacity.hiddendisabilities.EXTRA_SPECIALIST";
    public static final String EXTRA_TREATMENT =
            "com.tenacity.hiddendisabilities.EXTRA_TREATMENT";
    public static final String EXTRA_NOTE_TITLE =
            "com.tenacity.hiddendisabilities.EXTRA_NOTE_TITLE";
    public static final String EXTRA_NOTE_DESCRIPTION =
            "com.tenacity.hiddendisabilities.EXTRA_NOTE_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "com.tenacity.hiddendisabilities.EXTRA_PRIORITY";

    private EditText editTextDisability;
    private EditText editTextSpecialist;
    private EditText editTextTreatment;
    private EditText editTextNote_Title;
    private EditText editTextNote_Description;
    private NumberPicker numberPickerPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_add_hidden_disability );

        editTextDisability = findViewById ( R.id.edit_text_disability );
        editTextSpecialist = findViewById ( R.id.edit_text_specialist );
        editTextTreatment = findViewById ( R.id.edit_text_treatment );
        editTextNote_Title = findViewById ( R.id.edit_text_note_title );
        editTextNote_Description = findViewById ( R.id.edit_text_note_description );
        numberPickerPriority = findViewById ( R.id.number_picker_priority );

        numberPickerPriority.setMinValue ( 1 );
        numberPickerPriority.setMaxValue ( 10 );

        Objects.requireNonNull ( getSupportActionBar () ).setHomeAsUpIndicator ( R.drawable.ic_close );

        Intent intent = getIntent ();

        if (intent.hasExtra ( EXTRA_ID )) {
            setTitle ( "Edit HiddenDisability" );
            editTextDisability.setText ( intent.getStringExtra ( EXTRA_DISABILITY ) );
            editTextSpecialist.setText ( intent.getStringExtra ( EXTRA_SPECIALIST ) );
            editTextTreatment.setText ( intent.getStringExtra ( EXTRA_TREATMENT ) );
            editTextNote_Title.setText ( intent.getStringExtra ( EXTRA_NOTE_TITLE ) );
            editTextNote_Description.setText ( intent.getStringExtra ( EXTRA_NOTE_DESCRIPTION ) );
            numberPickerPriority.setValue ( intent.getIntExtra ( EXTRA_PRIORITY, 1 ) );
        } else {
            setTitle ( "Add HiddenDisability" );
        }
    }

    private void saveHiddenDisability() {
        String disability = editTextDisability.getText ().toString ();
        String specialist = editTextSpecialist.getText ().toString ();
        String treatment = editTextTreatment.getText ().toString ();
        String note_title = editTextNote_Title.getText ().toString ();
        String note_description = editTextNote_Description.getText ().toString ();
        int priority = numberPickerPriority.getValue ();

        if (note_title.trim ().isEmpty () || note_description.trim ().isEmpty ()) {
            Toast.makeText ( this, "Please insert a note_title and note_description", Toast.LENGTH_SHORT ).show ();
            return;
        }

        Intent data = new Intent ();
        data.putExtra ( EXTRA_DISABILITY, disability );
        data.putExtra ( EXTRA_SPECIALIST, specialist );
        data.putExtra ( EXTRA_TREATMENT, treatment );
        data.putExtra ( EXTRA_NOTE_TITLE, note_title );
        data.putExtra ( EXTRA_NOTE_DESCRIPTION, note_description );
        data.putExtra ( EXTRA_PRIORITY, priority );

        int id = getIntent ().getIntExtra ( EXTRA_ID, -1 );
        if (id != -1) {
            data.putExtra ( EXTRA_ID, id );
        }


        setResult ( RESULT_OK, data );
        finish ();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater ();
        menuInflater.inflate ( R.menu.add_hiddendisability_menu, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId () == R.id.save_hiddendisability) {
            saveHiddenDisability ();
            return true;
        }
        return super.onOptionsItemSelected ( item );
    }
}
