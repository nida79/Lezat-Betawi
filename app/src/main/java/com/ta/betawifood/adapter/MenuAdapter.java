package com.ta.betawifood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ta.betawifood.R;
import com.ta.betawifood.models.ResepModel;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder>  implements Filterable {
    RequestOptions options ;
    private Context mCtx;
    private ArrayList<ResepModel> resepList;
    private ArrayList<ResepModel> searchResep;

    public MenuAdapter(Context mCtx, ArrayList<ResepModel> resepList) {
        this.mCtx = mCtx;
        this.resepList = resepList;
        searchResep = new ArrayList<>();
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_loading);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mCtx).inflate(R.layout.resep_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ResepModel data = resepList.get(position);
        String deskripsi = data.getDeskripsi();
        String video = data.getVideoID();
        holder.textView.setText(data.getJudul());
        Glide.with(mCtx).load(data.getGambar()).apply(options).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return resepList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<ResepModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(searchResep);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ResepModel item : searchResep) {
                    if (item.getJudul().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            resepList.clear();
            resepList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.judul);
            imageView = itemView.findViewById(R.id.cover);
        }
    }
}
