package io.hardwick.todolist;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TodoDetailFragment extends Fragment {
    private TodoItem item = null;
    TextView detailTextView = null;
    TextView detailIsCompleteTextView = null;

    public TodoDetailFragment(TodoItem item) {
        this.item = item;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todo_detail_view, container, false);

        detailTextView = (TextView) view.findViewById(R.id.detailTextView);
        detailTextView.setText(item.getTodo_string());

        detailIsCompleteTextView = (TextView) view.findViewById(R.id.detailIsCompleteTextView);

        if (item.isTodo_complete()) {
            detailIsCompleteTextView.setTextColor(Color.GREEN);
            detailIsCompleteTextView.setText(getString(R.string.complete));
        } else {
            detailIsCompleteTextView.setTextColor(Color.RED);
            detailIsCompleteTextView.setText(getString(R.string.not_complete));
        }

        return view;
    }



}