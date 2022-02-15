package com.example.appghichu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoDungAdapter extends RecyclerView.Adapter<NoDungAdapter.NoidungViewHolder>{
    private List<Noidung> mListNd;
    private Iclick iclick;


    public interface Iclick{
        void updateNoiDung(Noidung noidung);
        void DeleteNoiDung(Noidung noidung);
    }

    public NoDungAdapter(Iclick iclick) {
        this.iclick = iclick;
    }

    public void setdata(List<Noidung> lNoidung){
        this.mListNd = lNoidung;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoidungViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_noidung,parent,false);

        return new NoidungViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoidungViewHolder holder, int position) {
        Noidung nd = mListNd.get(position);
        if(nd == null){
            return;
        }

        holder.txtTD.setText(nd.getTieude());
        holder.txtND.setText(nd.getNd());
        holder.layoutview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iclick.updateNoiDung(nd);
            }
        });
        holder.layoutview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                iclick.DeleteNoiDung(nd);
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        if(mListNd != null){
            return mListNd.size();
        }
        return 0;
    }

    public class NoidungViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTD, txtND;
        private LinearLayout layoutview;

        public NoidungViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTD = itemView.findViewById(R.id.textTD);
            txtND = itemView.findViewById(R.id.textND);
            layoutview = itemView.findViewById(R.id.layoutView);
        }
    }
}
