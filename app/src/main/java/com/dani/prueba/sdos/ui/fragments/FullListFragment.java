package com.dani.prueba.sdos.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dani.prueba.sdos.R;
import com.dani.prueba.sdos.adapter.TareaAdapter;
import com.dani.prueba.sdos.data.DBHelper;
import com.dani.prueba.sdos.data.objects.Task;
import com.dani.prueba.sdos.data.objects.User;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;

public class FullListFragment extends Fragment {

	private ListView mListView;
	private TareaAdapter taskAdapter;
    private int taskUser, otherTaskUser;
	private DBHelper databaseHelper = null;

	public FullListFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);

        Bundle arguments = getArguments();
        if(arguments != null){
            taskUser = getArguments().getInt("taskUser");
            otherTaskUser = getArguments().getInt("otherTaskUser");
        }

        mListView = (ListView) rootView.findViewById(R.id.listView2);


        try {
            loadTask();
        } catch (SQLException e) {
            e.printStackTrace();
        }

		return rootView;
	}

    public void loadTask() throws SQLException {

        Dao taskDao;
        taskDao = getHelper().getTaskDao();

        List<Task> tasks = taskDao.queryBuilder().where().eq(Task.POSITION, taskUser).query();
        List<Task> tasks2 = taskDao.queryBuilder().where().eq(Task.POSITION, otherTaskUser).query();

        for(int i = 0; i < tasks2.size() ; i++){
            tasks.add(tasks2.get(i));
        }

        pintListView(tasks);
    }

    public void pintListView(List<Task> tasks){

        taskAdapter = new TareaAdapter(getActivity(), tasks);
        mListView.setAdapter(taskAdapter);
    }
	
	protected DBHelper getHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(getActivity(),
					DBHelper.class);
		}
		return databaseHelper;
	}
}
