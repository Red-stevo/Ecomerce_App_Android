package com.redstevo.ecomerce_app.Activities.UserCart;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.redstevo.ecomerce_app.Activities.GeneralView.GeneralActivity;
import com.redstevo.ecomerce_app.Adapters.CartItemAdapter;
import com.redstevo.ecomerce_app.Models.CartItemModel;
import com.redstevo.ecomerce_app.R;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends GeneralActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        super.handleSearching(findViewById(R.id.search_product));
        super.handleUserCartClick(findViewById(R.id.user_cart));
        super.handleTrackOrderClick(findViewById(R.id.track_order));
        super.handleUserProfileClick(findViewById(R.id.user_profile));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        List<String> images = new ArrayList<>(List.of(
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.g9ziyrYoAGUtig18K-k3pgHaHa%26pid%3DApi&f=1&ipt=88270b045421afa7f47ac303203c985e07d488400172626d0fec18565d24e8eb&ipo=images",
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.elPqPSN0p8F7zIerDBRSUQHaHa%26pid%3DApi&f=1&ipt=87b353ef470684e9fb4d24dedc5acba849256818cbcd7003882359396547239b&ipo=images",
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.K8qhS0TCefDe6l6Ke7HZeAHaHa%26pid%3DApi&f=1&ipt=e93d742f8d486f72a61586c55b8ed2c1b20974d84b78837465d13541af092ce0&ipo=images",
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.tRyXg87M_e6VAgogaPfBwgHaHa%26pid%3DApi&f=1&ipt=e3e80d6b8e19bcc52007fd03d02efa574f0c54b4abb19cebe7db824eab60cdbb&ipo=images",
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.nz2LhHdjpEVNMAAA6oGh6QHaHa%26pid%3DApi&f=1&ipt=a7c13f2faa1b70d25fe9139c7710f158ea427d07bdf770eab8afe151538284f8&ipo=images",
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.3uaK00PL19Pym8cxWX4oSQHaHa%26pid%3DApi&f=1&ipt=c96689ef47cf494b8de5844238af540b883ecf37093b68f5eec86f2e023a076d&ipo=images",
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse4.mm.bing.net%2Fth%3Fid%3DOIP.r8HA9M6DubccuiR1IoC-9wHaIP%26pid%3DApi&f=1&ipt=18cfd3121d54b3307718b4b829d081fd18313c07d9ad2cdf41de0df806da947a&ipo=images",
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse3.mm.bing.net%2Fth%3Fid%3DOIP.73Yndv8io3A-JSgj48sFhgHaHa%26pid%3DApi&f=1&ipt=b38fbfc64716d2a8ae9f7763b69eff0c05d7d69ed5c2bec90339d2a2c2c72753&ipo=images",
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.p8ybcMhBkrHbJVUgsIVqyQHaHa%26pid%3DApi&f=1&ipt=fb8c7fa333b961a4aa3287ed485091604c2cbe0a8ab5fb75071bc90164857680&ipo=images",
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP._tpWbnSsHb5CO-ZmX00exAHaE8%26pid%3DApi&f=1&ipt=54c5945f8880f95501fa53542dac649d2fb133e56a45cb76b0f23a4bdbd12b47&ipo=images",
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

        List<CartItemModel> cartItemModelList = new ArrayList<>();

        for (int i = 0; i < images.size(); i++) {
            CartItemModel cartItemModel = new CartItemModel(
                    images.get(i),
                    "Children Blue Dresses", 3 + i,
                    2300.00F * i - 1000);

            cartItemModelList.add(cartItemModel);
        }

        populateCartView(recyclerView, cartItemModelList);
    }

    private void populateCartView(RecyclerView recyclerView, List<CartItemModel> cartItemModelList) {
        CartItemAdapter cartItemAdapter = new CartItemAdapter(cartItemModelList, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                        false));
        recyclerView.setAdapter(cartItemAdapter);

    }
}
