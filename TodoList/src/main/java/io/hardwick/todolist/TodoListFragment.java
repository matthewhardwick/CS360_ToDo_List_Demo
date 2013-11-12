package io.hardwick.todolist;

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
    ArrayAdapter<String> adapter;
    ListView listView;
    List<String> items;

    public TodoListFragment(List<String> items) {
        this.items = items;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();

        View view;
        view = inflater.inflate(R.layout.todo_list_view, container, false);

        listView = (ListView) view.findViewById(R.id.todo_listview);
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_multiple_choice, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

        return view;
    }

    public void addItem(String item) {
        adapter.add(item);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        CheckedTextView checkedTextView = (CheckedTextView) view;
        if (checkedTextView.isChecked()) {
            Toast.makeText(getActivity(),adapter.getItem(i).toString() + " completed",Toast.LENGTH_SHORT).show();
            checkedTextView.setPaintFlags(checkedTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            checkedTextView.setChecked(false);
        } else {
            Toast.makeText(getActivity(),adapter.getItem(i).toString() + " removed from completion",Toast.LENGTH_SHORT).show();
            checkedTextView.setPaintFlags(checkedTextView.getPaintFlags() & ( ~ Paint.STRIKE_THRU_TEXT_FLAG));
            checkedTextView.setChecked(true);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        adapter.remove(adapter.getItem(i));
        adapter.notifyDataSetChanged();
        return true;
    }
}