package com.redstevo.ecomerce_app.Activities.ProductsView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.redstevo.ecomerce_app.Activities.GeneralView.GeneralActivity;
import com.redstevo.ecomerce_app.Adapters.SearchProductAdapter;
import com.redstevo.ecomerce_app.Models.SearchProductModel;
import com.redstevo.ecomerce_app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchProduct extends GeneralActivity {


    /*SearchIndex<SearchProductModel> searchIndex;
    SearchClient client;
    public SearchProduct(){
        this.client = DefaultSearchClient.create("R9W4M96A8S", "6dbcc8015a29941136670834d6bc7299");
        this.searchIndex = client.initIndex("products", SearchProductModel.class);
    }*/
    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://myapplication-fce0cb20-default-rtdb.firebaseio.com/")
            .getReference("products");


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
        Log.d("CHECKING", "CHECK");
        if ("Related Products".equals(query) || query == null){
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Log.d("CHECKED", "data found : "+snapshot.getChildrenCount());
                    productModels.clear();
                    for (DataSnapshot snapshot1: snapshot.getChildren()){
                        SearchProductModel model = snapshot1.getValue(SearchProductModel.class);
                        assert model != null;
                        String name = model.getProductName();
                        Log.d("EXAMPLE NAME+++=>", "name is::"+name);

                        productModels.add(model);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(SearchProduct.this,"failed to load product",Toast.LENGTH_SHORT).show();
                    Log.d("FAILURE","could not get");
                }
            });
        } else {
             com.google.firebase.database.Query nameQuery = databaseReference.orderByChild("productName")
                    .startAt(query)
                    .endAt(query + "\uf8ff");

            /*Query categoryQuery = databaseReference.orderByChild("category")
                    .startAt(query)
                    .endAt(query + "\uf8ff");*/

            // Combine results from both queries
            nameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot productSnapshot : snapshot.getChildren()) {
                        SearchProductModel product = productSnapshot.getValue(SearchProductModel.class);
                        if (product != null) {
                            productModels.add(product);
                        }
                    }

                    // Run category query
                    /*categoryQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot categorySnapshot) {
                            for (DataSnapshot productSnapshot : categorySnapshot.getChildren()) {
                                SearchProductModel product = productSnapshot.getValue(SearchProductModel.class);
                                if (product != null && !productModels.contains(product)) {
                                    productModels.add(product); // Avoid duplicates
                                }
                            }

                            // Handle the combined product list
                            Log.d("ProductList", "Filtered Products: " + productModels.toString());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.e("FetchError", error.getMessage());
                        }
                    });*/
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(SearchProduct.this,"could not load product", Toast.LENGTH_LONG).show();
                    Log.e("FetchError", error.getMessage());
                }
            });
        }

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
