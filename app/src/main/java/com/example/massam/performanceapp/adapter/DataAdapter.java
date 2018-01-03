package com.example.massam.performanceapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.massam.performanceapp.model.Model;
import com.example.massam.performanceapp.R;

import java.util.List;

/**
 * Created by massam on 03/01/18.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.CardViewViewHolder>{
    private List<Model> ListData;
    private Context context;

    private String TAG = "adapter";

    public DataAdapter(Context context) {
        this.context = context;
    }

    public List<Model> getListData() {
        return ListData;
    }

    public void setListData(List<Model> ListData) {
        this.ListData = ListData;
    }
    @Override
    public CardViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: " + getListData().size());
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_data, parent, false);
        CardViewViewHolder viewHolder = new CardViewViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CardViewViewHolder holder, int position) {
        final CardViewViewHolder cardVH = (CardViewViewHolder) holder;

        Model p = getListData().get(position);

        Glide.with(context)
                .load(p.getUrl())
                .override(350, 550)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        cardVH.progressBar.setVisibility(View.GONE);
                        Log.d("aha", "onException: ");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        cardVH.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .crossFade()
                .into(holder.imgPhoto);

        holder.tv_title.setText(p.getTitle());
        holder.tv_thumbnail.setText(p.getThumbnailUrl());
        holder.tv_id.setText(Integer.toString(p.getId()));
        holder.tv_albumid.setText(Integer.toString(p.getAlbumId()));
    }

    @Override
    public int getItemCount() {
        return getListData().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder{
        ImageView imgPhoto;
        TextView tv_title, tv_thumbnail, tv_id,tv_albumid;
        ProgressBar progressBar;

        public CardViewViewHolder(View itemView) {
            super(itemView);
            imgPhoto = (ImageView)itemView.findViewById(R.id.img_item_photo);
            tv_title = (TextView)itemView.findViewById(R.id.tv_title);
            tv_thumbnail = (TextView)itemView.findViewById(R.id.tv_thumbnail);
            tv_id = (TextView)itemView.findViewById(R.id.tv_id);
            tv_albumid = (TextView)itemView.findViewById(R.id.tv_album_id);
            progressBar = (ProgressBar) itemView.findViewById(R.id.image_progress);
        }
    }
}
