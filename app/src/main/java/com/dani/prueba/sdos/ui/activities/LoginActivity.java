package com.dani.prueba.sdos.ui.activities;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dani.prueba.sdos.R;
import com.dani.prueba.sdos.data.DBHelper;
import com.dani.prueba.sdos.data.objects.User;
import com.dani.prueba.sdos.ui.fragments.FullListFragment;
import com.dani.prueba.sdos.ui.fragments.LoginFragment;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

public class LoginActivity extends FragmentActivity {

    FragmentManager fragmentManager;
    Fragment fragment = new LoginFragment();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_content, fragment).commit();
	}

}
