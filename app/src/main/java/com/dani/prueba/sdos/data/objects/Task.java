package com.dani.prueba.sdos.data.objects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

@DatabaseTable
public class Task {

	public static final String ID = "_id";
	public static final String DESCRIPTION = "description";
	public static final String DATE = "date";
	public static final String TYPE = "type";
    public static final String POSITION = "position";
	public static final String DONE = "isDone";

	@DatabaseField(generatedId = true, columnName = ID)
	private int id;
	@DatabaseField(columnName = DESCRIPTION)
	private String description;
	@DatabaseField(columnName = DATE)
	private String date;
	@DatabaseField(columnName = TYPE)
	private String type;
    @DatabaseField(columnName = POSITION)
    private int position;
	@DatabaseField(columnName = DONE)
	private boolean done;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

    public int getPosition() { return position; }

    public void setPosition(int position) { this.position = position; }

	public boolean getDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
}
