package io.hardwick.todolist;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class TodoListFragment extends Fragment {
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
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_multiple_choice, items);
        listView.setAdapter(adapter);

        return view;
    }

    public void addItem(String item) {
        adapter.add(item);
        adapter.notifyDataSetInvalidated();
    }
}