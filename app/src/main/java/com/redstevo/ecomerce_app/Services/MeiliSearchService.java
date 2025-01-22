package com.redstevo.ecomerce_app.Services;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.model.SearchResult;
import com.meilisearch.sdk.model.TaskInfo;
import com.redstevo.ecomerce_app.Models.ProductModel;
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
                Log.e("Add Products", "Error adding product: " + e.getClass());
                Toast.makeText(context, "Error Posting product", Toast.LENGTH_SHORT).show();
            }
        });

        executor.shutdown();
    }



    public void searchProducts(String query, Context context) {
        try {
            Index index = getIndex("products");
            SearchResult searchResult = index.search(query);

            searchResult.getHits().forEach(System.out::println);
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}

