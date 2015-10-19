package com.tinygrip.android.data.api;

import java.lang.annotation.Retention;
import javax.inject.Qualifier;
import retrofit.Endpoint;
import retrofit.RestAdapter;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation representing an {@link RestAdapter} which contains an fixed {@link Endpoint} in order to function with the
 * full url given by the backend. In this case will be always https
 */
@Qualifier
@Retention(RUNTIME)
public @interface HttpsRestAdapter {
}

