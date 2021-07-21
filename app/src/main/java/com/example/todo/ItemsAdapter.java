package com.example.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//Displays data from the model into row in recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
    public interface onLongClickListener{
        void onItemLongClicked(int position);
    }
    List<String> items;
    onLongClickListener longClickListener;
    public ItemsAdapter(List<String> items, onLongClickListener longClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use layout inflator to inflate a view, wrap it inside a View Holder and return it.
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(todoView);
//        return null;
    }

    @Override
    // Binds data to a particular view holder.
    public void onBindViewHolder(@NonNull ItemsAdapter.ViewHolder holder, int position) {
        // Grab the item at the position. Then bind the item into the specified view holder.
        String item = items.get(position);
        holder.bind(item);
    }

    @Override // Tells how many items are in the list.
    public int getItemCount() {
        return items.size();
    }

    //Container for easy access to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }
        // Update the view inside of the view holder with this data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // Notify listener which position was long pressed.
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}

