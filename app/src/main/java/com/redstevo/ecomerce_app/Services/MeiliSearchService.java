package com.redstevo.ecomerce_app.Services;

import android.content.Context;
import android.widget.Toast;

import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.model.SearchResult;
import com.meilisearch.sdk.model.TaskInfo;
import com.redstevo.ecomerce_app.Models.ProductModel;

public class MeiliSearchService {
    private static final String MEILISEARCH_URL = "https://ms-54cba11a295c-17833.fra.meilisearch.io";
    private static final String API_KEY = "c91926c261af18e903a662d47bdf316dc468797c";

    private final Client client;

    public MeiliSearchService() {
        this.client = new Client(new Config(MEILISEARCH_URL, API_KEY));
    }

    public Index getIndex(String indexName) {
        return client.index(indexName);
    }


    public void addProducts(ProductModel products, Context context) {
        try {
            Index index = getIndex("products");
            TaskInfo response = index.addDocuments(String.valueOf(products));
            System.out.println("Products added: " + response.toString());
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
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

