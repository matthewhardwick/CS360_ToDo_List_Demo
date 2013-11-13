package io.hardwick.todolist;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class TodoListFragment extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{
    Context context;
    ArrayAdapter<TodoItem> adapter;
    ListView listView;
    List<TodoItem> items;

    public TodoListFragment(List<TodoItem> items) {
        this.items = items;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();

        View view;
        view = inflater.inflate(R.layout.todo_list_view, container, false);

        listView = (ListView) view.findViewById(R.id.todo_listview);
        adapter = new TodoAdapter(context, R.layout.todo_list_view_item, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

        return view;
    }

    public void addItem(TodoItem item) {
        adapter.add(item);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        CheckedTextView checkedTextView = (CheckedTextView) view;
        if (checkedTextView.isChecked()) {
            adapter.getItem(i).setTodo_complete(true);
            Toast.makeText(getActivity(),adapter.getItem(i).toString() + " completed",Toast.LENGTH_SHORT).show();
            checkedTextView.setPaintFlags(checkedTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            checkedTextView.setChecked(false);
        } else {
            adapter.getItem(i).setTodo_complete(false);
            Toast.makeText(getActivity(),adapter.getItem(i).toString() + " removed from completion",Toast.LENGTH_SHORT).show();
            checkedTextView.setPaintFlags(checkedTextView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            checkedTextView.setChecked(true);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        adapter.remove(adapter.getItem(i));
        adapter.notifyDataSetChanged();
        return true;
    }

    public class TodoAdapter extends ArrayAdapter<TodoItem> {
        Context context;
        int layoutResourceId;
        List<TodoItem> data = null;

        public TodoAdapter(Context context, int layoutResourceId, List<TodoItem> data) {
            super(context, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.context = context;
            this.data = data;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            TodoHolder holder = null;

            if (row == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                row = inflater.inflate(layoutResourceId, parent, false);

                holder = new TodoHolder();
                holder.checkedTextView = (CheckedTextView) row.findViewById(R.id.checkedTextView);

                row.setTag(holder);
            } else {
                holder = (TodoHolder) row.getTag();
            }

            TodoItem item = data.get(position);
            holder.checkedTextView.setText(item.getTodo_string());
            holder.checkedTextView.setChecked(item.isTodo_complete());

            return row;
        }

        class TodoHolder {
            CheckedTextView checkedTextView;
        }
    }
}