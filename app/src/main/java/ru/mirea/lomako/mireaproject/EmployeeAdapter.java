package ru.mirea.lomako.mireaproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmployeeAdapter  extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Employee> states;

    EmployeeAdapter(Context context, List<Employee> states) {
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeAdapter.ViewHolder holder, int position) {
        Employee state = states.get(position);
        holder.history.setText(state.getHistoryResource());
        holder.nameView.setText(state.getNameHistory());
        holder.author.setText(state.getAuthor());
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView history;
        final TextView nameView, author;
        ViewHolder(View view){
            super(view);
            history = view.findViewById(R.id.historylist);
            nameView = view.findViewById(R.id.name);
            author = view.findViewById(R.id.author);
        }
    }
}

