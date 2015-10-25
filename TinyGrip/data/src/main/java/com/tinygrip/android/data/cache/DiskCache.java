package com.tinygrip.android.data.cache;

import java.lang.annotation.Retention;
import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation which identifies the type of cache we are dealing with as a disk (or file persistance) type of cache
 */
@Qualifier
@Retention(RUNTIME)
public @interface DiskCache {}
