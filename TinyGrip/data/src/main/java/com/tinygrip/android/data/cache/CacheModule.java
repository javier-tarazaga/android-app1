package com.tinygrip.android.data.cache;

import android.content.Context;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinygrip.android.data.cache.auth.DiskOAuthCacheImpl;
import com.tinygrip.android.data.cache.auth.MemoryOAuthCacheImpl;
import com.tinygrip.android.data.cache.auth.OAuthCache;
import com.tinygrip.android.data.cache.serializer.JacksonJsonSerializer;
import com.tinygrip.android.data.cache.serializer.JsonSerializer;
import com.tinygrip.android.data.cache.user.DiskUserCacheImpl;
import com.tinygrip.android.data.cache.user.MemoryUserCacheImpl;
import com.tinygrip.android.data.cache.user.UserCache;
import com.tinygrip.android.domain.executor.ThreadExecutor;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class CacheModule {

    @Provides
    @Singleton
    FileManager providesFileManager() {
        return new FileManager();
    }

    @Provides
    @Singleton
    JsonSerializer providesJsonSerializer(ObjectMapper objectMapper) {
        return new JacksonJsonSerializer(objectMapper);
    }

    @Provides
    @Singleton
    @Named("memoryOAuthCache")
    OAuthCache providesMemoryOAuthCache() {
        return new MemoryOAuthCacheImpl();
    }

    @Provides
    @Singleton
    @Named("diskOAuthCache")
    OAuthCache providesDiskOAuthCache(Context context,
                                      JsonSerializer jsonSerializer,
                                      FileManager fileManager,
                                      ThreadExecutor threadExecutor) {
        return new DiskOAuthCacheImpl(context, jsonSerializer, fileManager, threadExecutor);
    }

    @Provides
    @Singleton
    @Named("memoryUserCache")
    UserCache providesMemoryUserCache() {
        return new MemoryUserCacheImpl();
    }

    @Provides
    @Singleton
    @Named("diskUserCache")
    UserCache providesDiskUserCache(Context context,
                                    JsonSerializer jsonSerializer,
                                    FileManager fileManager,
                                    ThreadExecutor threadExecutor) {
        return new DiskUserCacheImpl(context, jsonSerializer, fileManager, threadExecutor);
    }
}