
package com.tinygrip.android.data.cache.user;

import android.content.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.tinygrip.android.data.cache.DiskCache;
import com.tinygrip.android.data.cache.FileManager;
import com.tinygrip.android.data.cache.serializer.JacksonJsonSerializer;
import com.tinygrip.android.data.cache.serializer.JsonSerializer;
import com.tinygrip.android.data.entity.user.UserEntity;
import com.tinygrip.android.data.exception.user.UserNotFoundException;
import com.tinygrip.android.domain.executor.ThreadExecutor;
import java.io.File;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.Subscriber;

/**
 * {@link UserCache} implementation.
 */
@DiskCache
@Singleton
public class DiskUserCacheImpl implements UserCache {

    private static final String USER_KEY_IDE = "com.tinygrip.android.USER_KEY_ID";

    private static final String SETTINGS_FILE_NAME = "com.tinygrip.android.SETTINGS";
    private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";

    private static final String DEFAULT_FILE_NAME = "user_";
    private static final long EXPIRATION_TIME = 60 * 10 * 1000;

    private final Context context;
    private final File cacheDir;
    private final JsonSerializer serializer;
    private final FileManager fileManager;
    private final ThreadExecutor threadExecutor;

    /**
     * Constructor of the class {@link DiskUserCacheImpl}.
     *
     * @param context A
     * @param jsonSerializer {@link JacksonJsonSerializer} for object serialization.
     * @param fileManager {@link FileManager} for saving serialized objects to the file system.
     */
    @Inject
    public DiskUserCacheImpl(Context context, JsonSerializer jsonSerializer,
                             FileManager fileManager, ThreadExecutor executor) {
        if (context == null || jsonSerializer == null || fileManager == null || executor == null) {
            throw new IllegalArgumentException("Invalid null parameter");
        }
        this.context = context.getApplicationContext();
        this.cacheDir = this.context.getCacheDir();
        this.serializer = jsonSerializer;
        this.fileManager = fileManager;
        this.threadExecutor = executor;
    }

    @Override
    public Observable<UserEntity> get() {
        return Observable.create(new Observable.OnSubscribe<UserEntity>() {
            @Override
            public void call(Subscriber<? super UserEntity> subscriber) {
                File userEntityFile = DiskUserCacheImpl.this.buildFile();
                String fileContent = DiskUserCacheImpl.this.fileManager.readFileContent(userEntityFile);
                try {
                    UserEntity userEntity = DiskUserCacheImpl.this.serializer.deserialize(fileContent, UserEntity.class);
                    if (userEntity != null) {
                        subscriber.onNext(userEntity);
                        subscriber.onCompleted();
                    } else {
                        subscriber.onError(new UserNotFoundException());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(new UserNotFoundException());
                }


            }
        });
    }

    @Override
    public synchronized void put(UserEntity userEntity) {
        if (userEntity != null) {
            File userEntityFile = this.buildFile();

            // Lets cache every time even if we have it already.
            try {
                String jsonString = this.serializer.serialize(userEntity);
                this.executeAsynchronously(new CacheWriter(this.fileManager, userEntityFile,
                                                           jsonString));
                setLastCacheUpdateTimeMillis();
            } catch (JsonProcessingException e) {
                // Silently fail
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isCached() {
        File userEntityFile = this.buildFile();
        return this.fileManager.exists(userEntityFile);
    }

    @Override
    public boolean isExpired() {
        long currentTime = System.currentTimeMillis();
        long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

        boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME);

        if (expired) {
            this.evictAll();
        }

        return expired;
    }

    @Override
    public synchronized void evictAll() {
        this.executeAsynchronously(new CacheEvictor(this.fileManager, this.cacheDir));
    }

    /**
     * Build a file, used to be inserted in the disk cache.
     *
     * @return A valid file.
     */
    private File buildFile() {
        StringBuilder fileNameBuilder = new StringBuilder();
        fileNameBuilder.append(this.cacheDir.getPath());
        fileNameBuilder.append(File.separator);
        fileNameBuilder.append(DEFAULT_FILE_NAME);
        fileNameBuilder.append(USER_KEY_IDE);

        return new File(fileNameBuilder.toString());
    }

    /**
     * Set in millis, the last time the cache was accessed.
     */
    private void setLastCacheUpdateTimeMillis() {
        long currentMillis = System.currentTimeMillis();
        this.fileManager.writeToPreferences(this.context, SETTINGS_FILE_NAME,
                                            SETTINGS_KEY_LAST_CACHE_UPDATE, currentMillis);
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private long getLastCacheUpdateTimeMillis() {
        return this.fileManager.getFromPreferences(this.context, SETTINGS_FILE_NAME,
                                                   SETTINGS_KEY_LAST_CACHE_UPDATE);
    }

    /**
     * Executes a {@link Runnable} in another Thread.
     *
     * @param runnable {@link Runnable} to execute
     */
    private void executeAsynchronously(Runnable runnable) {
        this.threadExecutor.execute(runnable);
    }

    /**
     * {@link Runnable} class for writing to disk.
     */
    private static class CacheWriter implements Runnable {

        private final FileManager fileManager;
        private final File fileToWrite;
        private final String fileContent;

        CacheWriter(FileManager fileManager, File fileToWrite, String fileContent) {
            this.fileManager = fileManager;
            this.fileToWrite = fileToWrite;
            this.fileContent = fileContent;
        }

        @Override
        public void run() {
            this.fileManager.writeToFile(fileToWrite, fileContent);
        }
    }

    /**
     * {@link Runnable} class for evicting all the cached files
     */
    private static class CacheEvictor implements Runnable {

        private final FileManager fileManager;
        private final File cacheDir;

        CacheEvictor(FileManager fileManager, File cacheDir) {
            this.fileManager = fileManager;
            this.cacheDir = cacheDir;
        }

        @Override
        public void run() {
            this.fileManager.clearDirectory(this.cacheDir);
        }
    }
}
