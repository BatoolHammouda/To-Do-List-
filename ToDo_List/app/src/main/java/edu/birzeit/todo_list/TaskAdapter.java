package edu.birzeit.todo_list;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private ArrayList<Task> taskList;
    private Context context;
    public TaskAdapter(Context context, ArrayList<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTaskDescription;
        CheckBox checkBoxTaskCompleted;
        Button editTask;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTaskDescription = itemView.findViewById(R.id.text_view_task_description);
            checkBoxTaskCompleted = itemView.findViewById(R.id.checkbox_task_completed);
            editTask = itemView.findViewById(R.id.editTask);
        }
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);

        holder.textViewTaskDescription.setText(task.getDescription());
        holder.checkBoxTaskCompleted.setChecked(task.isCompleted());
        holder.editTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDescriptionDialog(task, v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
    public void deleteTask(int position) {
        if (position < 0 || position >= taskList.size()) {
            return;
        }
        taskList.remove(position);
        notifyItemRemoved(position);
    }

    private void showEditDescriptionDialog(Task task, View itemView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
        builder.setTitle("Edit Task Description");

        final EditText input = new EditText(itemView.getContext());
        input.setText(task.getDescription());
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newDescription = input.getText().toString();
                // Update task description
                task.setDescription(newDescription);

                // Notify adapter of data change
                notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

}
