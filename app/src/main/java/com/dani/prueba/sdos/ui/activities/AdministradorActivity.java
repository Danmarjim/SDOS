package com.dani.prueba.sdos.ui.activities;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.dani.prueba.sdos.R;
import com.dani.prueba.sdos.data.DBHelper;
import com.dani.prueba.sdos.data.objects.Task;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AdministradorActivity extends ActionBarActivity {

	protected static final String TAG = null;
	protected int mAnyo;
	protected int mMes;
	protected int mDia;
	
	private EditText mDescView;
	private EditText mDateView;
    private Spinner mySpinner;
	
	private String mDesc;
	private String mDate;
    private String mSpinner;
    private int mItemPosition;
	
	private SimpleDateFormat fechaFormato;
	private DBHelper mDBHelper;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_administrador);


		mDescView = (EditText) findViewById(R.id.textoDesc);
        mDateView = (EditText) findViewById(R.id.textoFecha);
        mySpinner = (Spinner) findViewById(R.id.spinner1);
		
		Button inserTarea = (Button) findViewById(R.id.botonInserTarea);
		inserTarea.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
                insertTask();
			}
		});
		
		fechaFormato = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

		final EditText finFecha = (EditText) findViewById(R.id.textoFecha);
		finFecha.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Calendar mcurrentDate = Calendar.getInstance();
				mAnyo = mcurrentDate.get(Calendar.YEAR);
				mMes = mcurrentDate.get(Calendar.MONTH);
				mDia = mcurrentDate.get(Calendar.DAY_OF_MONTH);

				DatePickerDialog mDatePicker = new DatePickerDialog(
						AdministradorActivity.this, new OnDateSetListener() {
							public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth,
									int selectedday) {
								Calendar newDate = Calendar.getInstance();
								newDate.set(selectedyear, selectedmonth, selectedday);
								finFecha.setText(fechaFormato.format(newDate.getTime()));
								
							}
						}, mAnyo, mMes, mDia);
				mDatePicker.setTitle("Seleccionar Fecha");
				mDatePicker.show();
			}
		});

	}

    public void insertTask(){

        mDesc = mDescView.getText().toString();
        mDate = mDateView.getText().toString();
        mSpinner = mySpinner.getSelectedItem().toString();
        mItemPosition = mySpinner.getSelectedItemPosition();

        Dao dao;
        try {


            dao = getHelper().getTaskDao();
            Task task = new Task();
            task.setDescription(mDesc);
            task.setType(mSpinner);
            task.setDate(mDate);
            task.setPosition(mItemPosition);
            dao.create(task);


        } catch (SQLException e) {
            Log.e(TAG, "Error creando tarea");
        }

        Toast.makeText(this, "Tarea añadida", Toast.LENGTH_LONG).show();
        refreshActivity();
    }

    public void refreshActivity(){
        Intent intent = new Intent(AdministradorActivity.this, AdministradorActivity.class);
        startActivity(intent);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.administrador, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_cerrarSesion) {
			Intent intent = new Intent(AdministradorActivity.this,
					LoginActivity.class);
			startActivity(intent);
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
	
	private DBHelper getHelper() {
		if (mDBHelper == null) {
			mDBHelper = OpenHelperManager.getHelper(this, DBHelper.class);
		}
		return mDBHelper;
	}
}
