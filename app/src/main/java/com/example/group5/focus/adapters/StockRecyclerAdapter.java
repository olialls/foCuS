package com.example.group5.focus.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.group5.focus.R;
import com.example.group5.focus.model.Stock;

import java.util.List;

public class StockRecyclerAdapter extends RecyclerView.Adapter<StockRecyclerAdapter.UserViewHolder> {

    private List<Stock> listStocks;

    public StockRecyclerAdapter(List<Stock> listStocks) {
        this.listStocks = listStocks;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_stock_recycler, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.textViewStock.setText(listStocks.get(position).getSymbol());
        holder.textViewPrice.setText(listStocks.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        Log.v(StockRecyclerAdapter.class.getSimpleName(),""+listStocks.size());
        return listStocks.size();
    }


    /**
     * ViewHolder class
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewStock;
        public AppCompatTextView textViewPrice;

        public UserViewHolder(View view) {
            super(view);
            textViewStock = (AppCompatTextView) view.findViewById(R.id.textViewStock);
            textViewPrice = (AppCompatTextView) view.findViewById(R.id.textViewPrice);
        }
    }


}