package by.boiko.crm.service;

import by.boiko.crm.model.Products;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface Api {
    @GET("https://catalog.api.onliner.by/search/products?query={req}")
    Call<List<Products>> getData(@Path("req") String name);
}
