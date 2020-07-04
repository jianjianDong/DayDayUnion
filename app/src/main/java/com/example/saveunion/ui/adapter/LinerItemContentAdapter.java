package com.example.saveunion.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.saveunion.R;
import com.example.saveunion.model.HomePageContent;
import com.example.saveunion.utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.Url;

public class LinerItemContentAdapter extends RecyclerView.Adapter<LinerItemContentAdapter.InnerViewHolder> {

    private List<HomePageContent.DataBean> mData = new ArrayList<>();

    @NonNull
    @Override
    public InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pager_content, parent, false);
        return new InnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerViewHolder holder, int position) {
        holder.setData(mData.get(position));
    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<HomePageContent.DataBean> contents) {
        mData.clear();
        mData.addAll(contents);
        Log.d("LinerItemContentAdapter", "setData: "+contents.get(0));
        notifyDataSetChanged();
    }

    public class InnerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.goods_cover_ig)
        ImageView mImageView;



        public InnerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void setData(HomePageContent.DataBean dataBean) {

            String cover = dataBean.getPict_url();

            String coverUrl = UrlUtils.createCoverUrl(cover);

            Glide.with(itemView.getContext()).load(coverUrl).into(mImageView);
        }
    }
}
