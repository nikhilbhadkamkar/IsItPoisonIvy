package com.example.isitpoisonivy;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.isitpoisonivy.Plant;
import com.example.isitpoisonivy.R;

import java.util.ArrayList;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.PlantNote> {
    ArrayList<Plant> mPlant;
    private onItemClickListener mListener;

    PlantAdapter(ArrayList<Plant> mPlant) {
        this.mPlant = mPlant;
    }

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        mListener = listener;
    }



    public static class PlantNote extends RecyclerView.ViewHolder {
        private TextView plantName;
        private TextView botanicalName;


        public PlantNote(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);
            plantName = itemView.findViewById(R.id.plantName);
            botanicalName = itemView.findViewById(R.id.botanicalName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public PlantAdapter.PlantNote onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.plantdb_view, parent, false);
        return new PlantNote(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantAdapter.PlantNote holder, int position) {

        Plant holderA = mPlant.get(position);

        holder.plantName.setText(holderA.getPlant_name());
        holder.botanicalName.setText(holderA.getBotanical_name());

    }

    @Override
    public int getItemCount() {
        if(mPlant == null){
            return 0;
        }
        else{
            return mPlant.size();
        }
    }
}
