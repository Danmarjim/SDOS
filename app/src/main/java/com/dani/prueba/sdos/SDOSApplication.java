package com.dani.prueba.sdos;

import java.sql.SQLException;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.dani.prueba.sdos.data.DBHelper;
import com.dani.prueba.sdos.data.objects.Task;
import com.dani.prueba.sdos.data.objects.User;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

public class SDOSApplication extends Application {

	private static final String TAG = "error";
    private static final String PREFERENCES = "preferences";
    private static final String IS_LODADED = "isLoaded";
	private DBHelper mDBHelper = null;
    SharedPreferences areDatasLoaded;


	public void onCreate() {
        areDatasLoaded = this.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        if(areDatasLoaded.getBoolean(IS_LODADED,false) == false){
            createUsers();
            //createTasks();
            areDatasLoaded.edit().putBoolean(IS_LODADED,true).commit();
        }
	}
	
	public void createUsers(){
		Dao dao;
		try {
		    dao = getHelper().getUserDao();
		    
		    User user = new User();
		    user.setUserName("rugar");
		    user.setPassword("rugar");
		    user.setGroup(User.ADMINISTRATOR);

            User user2 = new User();
		    user2.setUserName("ramar");
		    user2.setPassword("ramar");
            user2.setTask(2);
		    user2.setGroup(User.TECHNICAL);
		    
		    User user3 = new User();
		    user3.setUserName("salop");
            user3.setPassword("salop");
            user3.setTask(1);
            user3.setOtherTask(3);
            user3.setGroup(User.TECHNICAL);
		    
		    dao.create(user);
		    dao.create(user2);
		    dao.create(user3);

		} catch (SQLException e) {
		    Log.e(TAG, "Error creando usuario");
		}
	}
	
	/*public void createTasks(){
		Dao dao;
		try {
		    dao = getHelper().getTaskDao();
		    
		    Task tarea = new Task();
		    tarea.setDescription("Reponedor de productos");
		    tarea.setType("Reponer");
		    
		    Task tarea2 = new Task();
		    tarea.setDescription("Realizar un cobro");
		    tarea.setType("Cobrar");
		    
		    Task tarea3 = new Task();
		    tarea.setDescription("Realizar un cobro");
		    tarea.setType("Envolver");
		    
		    dao.create(tarea);
		    dao.create(tarea2);
		    dao.create(tarea3);

		} catch (SQLException e) {
		    Log.e(TAG, "Error creando tarea");
		}
	}*/

	protected DBHelper getHelper() {
		if (mDBHelper == null) {
			mDBHelper = OpenHelperManager.getHelper(this, DBHelper.class);
		}
		return mDBHelper;
	}
}
