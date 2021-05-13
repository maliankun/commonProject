package com.inspur.osp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inspur.osp.data.bean.CellDetailInfo;
import com.inspur.topo.R;

import java.util.List;

public class NewCellDetailAdapter extends RecyclerView.Adapter<NewCellDetailAdapter.Holder> {
    private List<CellDetailInfo> addata;
    public NewCellDetailAdapter(List<CellDetailInfo> data){
        this.addata = data;
    }

    @Override
    public NewCellDetailAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_celldetail, null);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NewCellDetailAdapter.Holder holder, int position) {
        holder.tv_itemname.setText(addata.get(position).getCnName());
        holder.tv_itemvalue.setText(addata.get(position).getValue());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return addata.size();
    }


    public class Holder extends RecyclerView.ViewHolder{

        private final TextView tv_itemname;
        private final TextView tv_itemvalue;

        public Holder(View itemView) {
            super(itemView);
            tv_itemname = itemView.findViewById(R.id.tv_itemname);
            tv_itemvalue = itemView.findViewById(R.id.tv_itemvalue);
        }
    }
}
