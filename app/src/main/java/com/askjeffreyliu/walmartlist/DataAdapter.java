package com.askjeffreyliu.walmartlist;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.askjeffreyliu.walmartlist.listener.OnLoadMoreListener;
import com.askjeffreyliu.walmartlist.model.Product;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jeff on 1/14/18.
 */

public class DataAdapter extends RecyclerView.Adapter {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    private List<Product> products;
    private Context mContext;

    // The minimum amount of items to have below your current scroll position
// before loading more.
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;
    private RecyclerViewClickListener mListener;


    public DataAdapter(List<Product> products, RecyclerView recyclerView, Context context, RecyclerViewClickListener listener) {
        this.products = products;
        this.mContext = context;
        this.mListener = listener;

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
                    .getLayoutManager();


            recyclerView
                    .addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(RecyclerView recyclerView,
                                               int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);

                            totalItemCount = linearLayoutManager.getItemCount();
                            lastVisibleItem = linearLayoutManager
                                    .findLastVisibleItemPosition();
                            if (!loading
                                    && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                                // End has been reached
                                // Do something
                                if (onLoadMoreListener != null) {
//                                    onLoadMoreListener.onLoadMore(imageLikeList.get(imageLikeList.size() - 1).getMediaID());
                                }
                                loading = true;
                            }
                        }
                    });
        }
    }

    public void addProducts(List<Product> toBeAddedProducts) {
        products.addAll(toBeAddedProducts);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return products.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item, parent, false);

            vh = new ProductViewHolder(v, mListener);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_loading, parent, false);

            vh = new ProgressViewHolder(v);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ProductViewHolder) {
            Product product = products.get(position);
            Picasso.with(mContext).load(product.getProductImage()).into(((ProductViewHolder) holder).imageView);
            ((ProductViewHolder) holder).textView.setText(product.getProductId());
        }
    }


    public void setLoaded() {
        loading = false;
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }


    //
    public static class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView textView;
        private Product product;
        private RecyclerViewClickListener mListener;

        public ProductViewHolder(View v, RecyclerViewClickListener listener) {
            super(v);
            mListener = listener;
            v.setOnClickListener(this);
            imageView = v.findViewById(R.id.imageView);

            textView = v.findViewById(R.id.textView);
        }

        @Override
        public void onClick(View view) {
            Logger.d("clicked");
            mListener.onClick(view, getAdapterPosition(), product);
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        ProgressViewHolder(View v) {
            super(v);

        }
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position, Product product);
    }
}