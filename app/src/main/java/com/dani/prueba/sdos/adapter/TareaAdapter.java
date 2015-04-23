package com.dani.prueba.sdos.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dani.prueba.sdos.R;
import com.dani.prueba.sdos.data.objects.Task;

public class TareaAdapter extends BaseAdapter {

	private Context context;
	private List<Task> items;

	public TareaAdapter(Context context, List<Task> items) {
		super();
		this.context = context;
		this.items = items;
	}

	@Override
	public int getCount() {
		return this.items.size();
	}

	@Override
	public Object getItem(int position) {
		return this.items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.fragment_list_item, parent, false);
		}

		TextView desc = (TextView) rowView.findViewById(R.id.descTask);
		TextView type = (TextView) rowView.findViewById(R.id.typeTask);
        TextView date = (TextView) rowView.findViewById(R.id.dateTask);

		Task task = this.items.get(position);
        desc.setText(task.getDescription());
        type.setText(task.getType());
        date.setText(task.getDate());

		return rowView;
	}

}
