package com.tinygrip.android.data.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.squareup.okhttp.OkHttpClient;
import com.tinygrip.android.data.api.util.StringConverterModule;
import com.tinygrip.android.data.service.RootService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.converter.JacksonConverter;

/**
 * A module to wrap the Data related items it to the graph.
 */
@Module public class ApiModule {

  public static final String PRODUCTION_API_URL = ApiConfig.HOST_PROD;
  public static final String MOCK_API_URL = ApiConfig.HOST_MOCK;

  @Provides @Singleton Endpoint provideDeviceEndpoint() {
    return Endpoints.newFixedEndpoint(PRODUCTION_API_URL);
  }

  @Provides @Singleton Client provideClient(OkHttpClient client) {
    return new OkClient(client);
  }

  @Provides @Singleton ObjectMapper provideObjectMapper() {
    ObjectMapper jacksonObjectMapper = new ObjectMapper();
    jacksonObjectMapper.setPropertyNamingStrategy(new PropertyNamingStrategy.PascalCaseStrategy());
    jacksonObjectMapper.registerModule(new StringConverterModule());

    // This way it won't complain if we don't have every part of the json object defined
    jacksonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    return jacksonObjectMapper;
  }

  @Provides @Singleton RestAdapter providesRestAdapter(Endpoint endpoint,
      ObjectMapper jacksonObjectMapper, Client client, ApiRequestInterceptor headers) {

    return new RestAdapter.Builder() //
        .setClient(client) //
        .setEndpoint(endpoint) //
        .setConverter(new JacksonConverter(jacksonObjectMapper))
        //.setErrorHandler(errorHandler).setRequestInterceptor(headers) //
        .setLogLevel(RestAdapter.LogLevel.FULL)
        .build();
  }

  @Provides @Singleton RootService providesRootService(RestAdapter restAdapter) {
    return restAdapter.create(RootService.class);
  }
}
