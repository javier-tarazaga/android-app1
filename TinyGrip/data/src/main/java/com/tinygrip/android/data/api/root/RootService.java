package com.tinygrip.android.data.api.root;

import com.tinygrip.android.data.api.ApiConfig;
import com.tinygrip.android.data.entity.RootEntity;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Root service for retrieving the basic application data from the network
 */
public interface RootService {

    @GET(ApiConfig.BASE + "/Root")
    RootEntity rootEntitySync(@Query("apiKey") String apiKey);
}
