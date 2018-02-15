package com.Telstra.sample.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Telstra.sample.R;
import com.Telstra.sample.model.ImageData;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ImageDetailsHolder> {
    private List<ImageData> mDataset; //List of Data from server
    private Context context; //Context from activity

    public static class ImageDetailsHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView desc;
        private SimpleDraweeView dataImage;

        public ImageDetailsHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            desc = (TextView) itemView.findViewById(R.id.desc);
            dataImage = (SimpleDraweeView) itemView.findViewById(R.id.icon);
        }
    }

    public ContentAdapter() {
        mDataset = new ArrayList<>();
    }


    public void updateList(List<ImageData> data) {
        mDataset = data;
        notifyDataSetChanged();
    }


    @Override

    public ImageDetailsHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        context = parent.getContext();
        Fresco.initialize(context);
        View view = LayoutInflater.from(context)
                .inflate(R.layout.list_item, parent, false);
        return new ImageDetailsHolder(view);
    }

    @Override

    public void onBindViewHolder(ImageDetailsHolder holder, int position) {
        holder.title.setText(mDataset.get(position).title);
        holder.desc.setText(mDataset.get(position).description);
        Uri uri = Uri.parse("");
        if(!TextUtils.isEmpty(mDataset.get(position).imageHref))
         uri = Uri.parse(mDataset.get(position).imageHref);
        holder.dataImage.setImageURI(uri);

    }


    @Override

    public int getItemCount() {
        return mDataset.size();
    }


}