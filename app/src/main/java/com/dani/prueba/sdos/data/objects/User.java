package com.dani.prueba.sdos.data.objects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class User {
    public static final String ADMINISTRATOR = "administrator";
    public static final String TECHNICAL = "technical";

	public static final String ID = "_id";
	public static final String USER = "user";
	public static final String PASSWORD = "password";
	public static final String GROUP = "group";
	public static final String TASK = "task";
    public static final String OTHERTASK = "other";

	@DatabaseField(generatedId = true, columnName = ID)
	private int id;
	@DatabaseField(columnName = USER)
	private String userName;
	@DatabaseField(columnName = PASSWORD)
	private String password;
	@DatabaseField(columnName = GROUP)
	private String group;
	@DatabaseField(columnName = TASK)
	private int task;
    @DatabaseField(columnName = OTHERTASK)
    private int other;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTask() {
        return task;
    }

    public void setTask(int task) {
        this.task = task;
    }

    public int getOtherTask() {
        return other;
    }

    public void setOtherTask(int other) {
        this.other = other;
    }
}
