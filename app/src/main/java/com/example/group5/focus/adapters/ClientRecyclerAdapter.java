package com.example.group5.focus.adapters;

import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.group5.focus.R;
import com.example.group5.focus.activities.ProfileActivity;
import com.example.group5.focus.common.Common;
import com.example.group5.focus.interfaces.ItemClickListener;
import com.example.group5.focus.model.Client;

import java.util.List;


class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public AppCompatTextView textViewName;
    public AppCompatTextView textViewUsername;
    public AppCompatTextView textViewPassword;

    ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        textViewName = (AppCompatTextView) itemView.findViewById(R.id.textViewName);
        textViewUsername = (AppCompatTextView) itemView.findViewById(R.id.textViewUsername);
        textViewPassword = (AppCompatTextView) itemView.findViewById(R.id.textViewPassword);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition());

    }

}

public class ClientRecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<Client> listUsers;
    int row_index = -1;

    public ClientRecyclerAdapter(List<Client> listUsers) {
        this.listUsers = listUsers;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_recycler, parent, false);

        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.textViewName.setText(listUsers.get(position).getName());
        holder.textViewUsername.setText(listUsers.get(position).getDOB());
        holder.textViewPassword.setText(listUsers.get(position).getCountry());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                row_index = position;
                Common.currentItem = listUsers.get(position);
                notifyDataSetChanged();
                int id = listUsers.get(position).getManager();
                String managerID = Integer.toString(id);
                Intent profileIntent = new Intent(view.getContext(), ProfileActivity.class);
                profileIntent.putExtra("POSITION", row_index);
                profileIntent.putExtra("ID", managerID);
                view.getContext().startActivity(profileIntent);

            }
        });



    }

    @Override
    public int getItemCount() {
        Log.v(ClientRecyclerAdapter.class.getSimpleName(),""+listUsers.size());
        return listUsers.size();
    }


    /**
     * ViewHolder class
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewName;
        public AppCompatTextView textViewUsername;
        public AppCompatTextView textViewPassword;

        public UserViewHolder(View view) {
            super(view);
            textViewName = (AppCompatTextView) view.findViewById(R.id.textViewName);
            textViewUsername = (AppCompatTextView) view.findViewById(R.id.textViewUsername);
            textViewPassword = (AppCompatTextView) view.findViewById(R.id.textViewPassword);
        }
    }


}