package com.askjeffreyliu.walmartlist;

import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.askjeffreyliu.walmartlist.model.Product;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    private static final String EXTRA_IMAGE = "com.askjeff.sharedElementName";
    private static final String EXTRA_PRODUCT = "com.askjeff.product";

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.idTextView)
    TextView idTextView;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.price)
    TextView price;

    public static void navigate(AppCompatActivity activity, View transitionImage, Product product) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(EXTRA_PRODUCT, product);
        intent.putExtra(EXTRA_IMAGE, product.getProductId());

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, EXTRA_IMAGE);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        ViewCompat.setTransitionName(imageView, EXTRA_IMAGE);

        Product product = getIntent().getParcelableExtra(EXTRA_PRODUCT);


        Picasso.with(this).load(product.getProductImage()).into(imageView);
        idTextView.setText(product.getProductId());
        name.setText(product.getProductName());
        price.setText(product.getPrice());
    }
}
