package com.dani.prueba.sdos.data;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.dani.prueba.sdos.data.objects.Task;
import com.dani.prueba.sdos.data.objects.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DBHelper extends OrmLiteSqliteOpenHelper {
	
	private static final String DATABASE_NAME = "sdos.db";
    private static final int DATABASE_VERSION = 1;
    
    private Dao<User, Integer> UserDao;
    private Dao<Task, Integer> TaskDao;
    
    
    public DBHelper(Context context){
    	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Task.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion,
			int newVersion) {
	}
	
	public Dao<User, Integer> getUserDao() throws SQLException {
        if (UserDao == null) {
            UserDao = getDao(User.class);
        }
        return UserDao;
    }
	
	public Dao<Task, Integer> getTaskDao() throws SQLException {
        if (TaskDao == null) {
        	TaskDao = getDao(Task.class);
        }
        return TaskDao;
    }
	
	@Override
    public void close() {
        super.close();
        UserDao = null;
        TaskDao = null;
    }

}
