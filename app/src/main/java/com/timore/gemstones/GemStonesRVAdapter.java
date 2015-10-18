package com.timore.gemstones;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Abuzeid on 10/18/2015.
 */
public class GemStonesRVAdapter extends RecyclerView.Adapter<GemStonesRVAdapter.ViewHolder> {


    List<String> gemElements;

    GemStonesRVAdapter(List<String> data) {
        gemElements = data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_gem_element, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.elementTv.setText(gemElements.get(position));
    }

    @Override
    public int getItemCount() {
        return gemElements.size();
    }
    public void add(int position, String item) {
        gemElements.add(position, item);
        notifyItemInserted(position);
    }
    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView elementTv;

        public ViewHolder(View itemView) {
            super(itemView);
            elementTv = (TextView) itemView.findViewById(R.id.gemstones_element_textview);
        }
    }
}
