package com.redstevo.ecomerce_app.Activities.ProductsView;

import android.content.SharedPreferences;
import android.os.Bundle;
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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SearchProduct extends GeneralActivity {



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

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("product");
        List<SearchProductModel> productModels = new ArrayList<>();
        if ("Related Products".equals(query) || query == null){
            databaseReference.addListenerForSingleValueEvent((new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot dt : snapshot.getChildren()){
                        SearchProductModel searchProductModel = dt.getValue(SearchProductModel.class);
                        if (searchProductModel != null)
                            productModels.add(searchProductModel);
                    }
                    Collections.shuffle(productModels);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(SearchProduct.this,"could not find products",Toast.LENGTH_SHORT).show();
                }
            }));
        } else {
            Query queryByName = databaseReference.child("name")
                    .startAt(query).endAt(query+"\uf8ff");
            Query queryByCategory = databaseReference.child("category")
                            .startAt(query).endAt(query+"\uf8ff");

            queryByName.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        SearchProductModel model = dataSnapshot.getValue(SearchProductModel.class);
                        if (model != null)
                            productModels.add(model);
                    }
                    Collections.shuffle(productModels);

                    queryByCategory.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                                SearchProductModel model = dataSnapshot.getValue(SearchProductModel.class);
                                if (model != null)
                                    productModels.add(model);
                            }
                            Collections.shuffle(productModels);
                        }



                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(SearchProduct.this,"product not found",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(SearchProduct.this,"product not found",Toast.LENGTH_SHORT).show();
                }
            });
        }

        return productModels.stream().map(model->new SearchProductModel(model.getProductId(),model.getProductName(),
                model.getProductUrl(),model.getProductPrice(),model.getProductDiscount())).collect(Collectors.toList());


       /* List<String> urls = new ArrayList<>(List.of(
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.g9ziyrYoAGUtig18K-k3pgHaHa%26pid%3DApi&f=1&ipt=88270b045421afa7f47ac303203c985e07d488400172626d0fec18565d24e8eb&ipo=images",
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.elPqPSN0p8F7zIerDBRSUQHaHa%26pid%3DApi&f=1&ipt=87b353ef470684e9fb4d24dedc5acba849256818cbcd7003882359396547239b&ipo=images",
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.K8qhS0TCefDe6l6Ke7HZeAHaHa%26pid%3DApi&f=1&ipt=e93d742f8d486f72a61586c55b8ed2c1b20974d84b78837465d13541af092ce0&ipo=images",
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.tRyXg87M_e6VAgogaPfBwgHaHa%26pid%3DApi&f=1&ipt=e3e80d6b8e19bcc52007fd03d02efa574f0c54b4abb19cebe7db824eab60cdbb&ipo=images",
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.nz2LhHdjpEVNMAAA6oGh6QHaHa%26pid%3DApi&f=1&ipt=a7c13f2faa1b70d25fe9139c7710f158ea427d07bdf770eab8afe151538284f8&ipo=images",
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.3uaK00PL19Pym8cxWX4oSQHaHa%26pid%3DApi&f=1&ipt=c96689ef47cf494b8de5844238af540b883ecf37093b68f5eec86f2e023a076d&ipo=images",
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse4.mm.bing.net%2Fth%3Fid%3DOIP.r8HA9M6DubccuiR1IoC-9wHaIP%26pid%3DApi&f=1&ipt=18cfd3121d54b3307718b4b829d081fd18313c07d9ad2cdf41de0df806da947a&ipo=images",
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse3.mm.bing.net%2Fth%3Fid%3DOIP.73Yndv8io3A-JSgj48sFhgHaHa%26pid%3DApi&f=1&ipt=b38fbfc64716d2a8ae9f7763b69eff0c05d7d69ed5c2bec90339d2a2c2c72753&ipo=images",
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.p8ybcMhBkrHbJVUgsIVqyQHaHa%26pid%3DApi&f=1&ipt=fb8c7fa333b961a4aa3287ed485091604c2cbe0a8ab5fb75071bc90164857680&ipo=images",
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP._tpWbnSsHb5CO-ZmX00exAHaE8%26pid%3DApi&f=1&ipt=54c5945f8880f95501fa53542dac649d2fb133e56a45cb76b0f23a4bdbd12b47&ipo=images"
        ));


       return urls.stream().map(url -> new SearchProductModel(
               "wqr4332", "Girl's Dresses", url,
               2750.00F, 350.00F)).collect(Collectors.toList());*/
    }

    private void populateProductsView(
            List<SearchProductModel> searchProductModelList, RecyclerView recyclerView) {
        SearchProductAdapter searchProductAdapter = new SearchProductAdapter(searchProductModelList);

       recyclerView.setLayoutManager(new FlexboxLayoutManager(this));
       recyclerView.setHasFixedSize(false);
       recyclerView.setAdapter(searchProductAdapter);
    }

}
