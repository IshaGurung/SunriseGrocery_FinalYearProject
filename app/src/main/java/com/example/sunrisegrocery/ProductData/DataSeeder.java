package com.example.sunrisegrocery.ProductData;

import java.util.ArrayList;
import java.util.List;

public class DataSeeder {
    public static List<ProductData> getProductList() {
        List<ProductData> productDataList = new ArrayList<>();
        productDataList.add(new ProductData(1, "Fanta", "", "", 3.7, 160, 150));
        productDataList.add(new ProductData(2, "Coke", "", "", 3.7, 40, 50));

        return productDataList;

    }
}
