package io.hardwick.todolist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    List<TodoItem> listItems;
    TodoListFragment listFragment;
    Button addItemButton;
    EditText addItemEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listItems = new ArrayList<TodoItem>();
        listFragment = new TodoListFragment(listItems);

        addItemButton = (Button) findViewById(R.id.addToDobutton);
        addItemEditText = (EditText) findViewById(R.id.addToDoEditText);

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemToAdd = addItemEditText.getText().toString();
                if (itemToAdd != null && itemToAdd.length() > 0)
                    listFragment.addItem(new TodoItem(itemToAdd.toString(), false));
                addItemEditText.setText("");
            }
        });

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, listFragment)
                    .commit();
        }
    }
}
