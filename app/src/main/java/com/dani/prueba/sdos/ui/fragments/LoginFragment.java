package com.dani.prueba.sdos.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dani.prueba.sdos.R;
import com.dani.prueba.sdos.data.DBHelper;
import com.dani.prueba.sdos.data.objects.User;
import com.dani.prueba.sdos.ui.activities.AdministradorActivity;
import com.dani.prueba.sdos.ui.activities.LoginActivity;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;


public class LoginFragment extends Fragment {

    private DBHelper mDBHelper = null;
    private EditText userNameEditText;
    private EditText passwordEditText;
    String userView;
    String passView;
    private Button loginButton;
    private static final String TAG = null;

    private FragmentActivity myContext;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_login, container,
                false);

        loginButton = (Button) rootView.findViewById(R.id.botonLog);
        userNameEditText = (EditText) rootView.findViewById(R.id.textoID);
        passwordEditText = (EditText) rootView.findViewById(R.id.textoPass);

        initializeListeners();

        return rootView;
    }

    public void initializeListeners() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    checkUser();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void checkUser() throws SQLException{
        userView = userNameEditText.getText().toString().trim();
        passView = passwordEditText.getText().toString().trim();
        Dao userDao;
        userDao = getHelper().getUserDao();
        List<User> users = userDao.queryBuilder().where().eq(User.USER, userView).query();
        if(users.size()>0){
            User user = users.get(0);
            if(isAdministrator(user)){
                Intent intent = new Intent(getActivity(), AdministradorActivity.class);
                startActivity(intent);
            }else if(isTecnichal(user)){

                FullListFragment fullList = new FullListFragment();
                Bundle arguments = new Bundle();
                arguments.putInt("taskUser", user.getTask());
                arguments.putInt("otherTaskUser", user.getOtherTask());
                fullList.setArguments(arguments);

                FragmentTransaction ft = myContext.getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.main_content, fullList);
                ft.commit();

            }
        }

    }

    private boolean isAdministrator(User user){
        if(user!=null && user.getGroup().equals(User.ADMINISTRATOR)){
            return true;
        }
        return false;
    }

    private boolean isTecnichal(User user){
        if(user!=null && user.getGroup().equals(User.TECHNICAL)){
            return true;
        }
        return false;
    }

    protected DBHelper getHelper() {
        if (mDBHelper == null) {
            mDBHelper = OpenHelperManager.getHelper(getActivity(), DBHelper.class);
        }
        return mDBHelper;
    }

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

}
