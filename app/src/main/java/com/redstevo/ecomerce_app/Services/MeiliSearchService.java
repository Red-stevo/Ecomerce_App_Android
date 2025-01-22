package com.redstevo.ecomerce_app.Services;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.gson.Gson;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.model.SearchResult;
import com.meilisearch.sdk.model.TaskInfo;
import com.redstevo.ecomerce_app.Adapters.SearchProductAdapter;
import com.redstevo.ecomerce_app.Models.ProductModel;
import com.redstevo.ecomerce_app.Models.SearchProductModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MeiliSearchService {
    private static final String MEILISEARCH_URL = "https://edge.meilisearch.com";
    private static final String API_KEY = "c91926c261af18e903a662d47bdf316dc468797c";

    private final Client client;

    public MeiliSearchService() {
        this.client = new Client(new Config(MEILISEARCH_URL, API_KEY));
    }

    public Index getIndex(String indexName) {
        return client.index(indexName);
    }

    public void addProducts(ProductModel product, Context context) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            try {
                Index index = getIndex("products");
                Gson gson = new Gson();
                String productJson = gson.toJson(product);
                TaskInfo response = index.addDocuments(productJson);

            } catch (Exception e) {
                Toast.makeText(context, "Error Posting product", Toast.LENGTH_SHORT).show();
            }
        });

        executor.shutdown();
    }



    public void searchProducts(String query, Context context, RecyclerView recyclerView) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        List<ProductModel> searchProductModelList = new ArrayList<>();

        executor.execute(() -> {
            try {
                Index index = getIndex("products");
                SearchResult searchResult = index.search(query);

                searchResult.getHits().forEach(data -> {
                    ProductModel productModel = new ProductModel();

                    if (String.valueOf(data.get("productCount")).equals("0.0")){
                        productModel.setProductCount(0);
                    }else
                        productModel.setProductCount(Integer.parseInt(String.valueOf(data.get("productCount"))));

                    productModel.setProductId(String.valueOf(data.get("productId")));
                    productModel.setProductPrice(Float.parseFloat(String.valueOf(data.get("productPrice"))));
                    productModel.setProductDiscount(Float.parseFloat(String.valueOf(data.get("productDiscount"))));
                    productModel.setProductName(String.valueOf(data.get("productName")));
                    productModel.setProductUrls(List.of(String.valueOf(data.get("productUrls"))));

                    searchProductModelList.add(productModel);
                });

                ((android.app.Activity) context).runOnUiThread(() -> {
                    SearchProductAdapter searchProductAdapter = new SearchProductAdapter(searchProductModelList);

                    recyclerView.setLayoutManager(new FlexboxLayoutManager(context));
                    recyclerView.setHasFixedSize(false);
                    recyclerView.setAdapter(searchProductAdapter);
                });

            } catch (Exception e) {
                ((android.app.Activity) context).runOnUiThread(() ->
                        Toast.makeText(context, "Search error: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
            }
        });

        executor.shutdown();
    }

}

