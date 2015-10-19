package com.tinygrip.android.data.api;

import java.lang.annotation.Retention;
import javax.inject.Qualifier;
import retrofit.Endpoint;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation representing an Rest Adapter which contains an fixed {@link Endpoint} in order to function with the
 * full url given by the backend
 */
@Qualifier
@Retention(RUNTIME)
public @interface HttpRestAdapter {
}

