package com.redstevo.ecomerce_app.Activities.ProductsView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

import com.algolia.search.DefaultSearchClient;
import com.algolia.search.SearchClient;
import com.algolia.search.SearchIndex;
import com.algolia.search.models.indexing.Query;
import com.algolia.search.models.indexing.SearchResult;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.redstevo.ecomerce_app.Activities.GeneralView.GeneralActivity;
import com.redstevo.ecomerce_app.Adapters.SearchProductAdapter;
import com.redstevo.ecomerce_app.Models.SearchProductModel;
import com.redstevo.ecomerce_app.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SearchProduct extends GeneralActivity {


    SearchIndex<SearchProductModel> searchIndex;
    SearchClient client;
    public SearchProduct(){
        this.client = DefaultSearchClient.create("R9W4M96A8S", "6dbcc8015a29941136670834d6bc7299");
        this.searchIndex = client.initIndex("products", SearchProductModel.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        SharedPreferences sharedPreferences = super.getSharedPreferences();




        //populate the recycle view
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        populateProductsView(
                getSearchProducts(
                        sharedPreferences.getString("query", "Related Products")),
                recyclerView); //call service to get data.

        //add event listener to the search bar.
        EditText searchBar = findViewById(R.id.search_product);

        searchBar.setOnKeyListener((v, keyCode, event) -> {
            if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                populateProductsView(getSearchProducts(String.valueOf(searchBar.getText())),
                        recyclerView);

                searchBar.setText("");
            }
            return true;
        });
    }

    private List<SearchProductModel> getSearchProducts(String query) {


        List<SearchProductModel> productModels = new ArrayList<>();
        SearchResult<SearchProductModel> result;
        if ("Related Products".equals(query) || query == null){
            result = searchIndex.search(new Query()).setHitsPerPage(50);
        } else {

            result = searchIndex.search(new Query(query)).setHitsPerPage(50);
        }
        if (!result.getHits().isEmpty())
             productModels = result.getHits();

        Collections.shuffle(productModels);
        return productModels.stream().map(model->new SearchProductModel(model.getProductId(),model.getProductName(),
                model.getProductUrl(),model.getProductPrice(),model.getProductDiscount())).collect(Collectors.toList());



    }

    private void populateProductsView(
            List<SearchProductModel> searchProductModelList, RecyclerView recyclerView) {
        SearchProductAdapter searchProductAdapter = new SearchProductAdapter(searchProductModelList);

       recyclerView.setLayoutManager(new FlexboxLayoutManager(this));
       recyclerView.setHasFixedSize(false);
       recyclerView.setAdapter(searchProductAdapter);
    }

}
