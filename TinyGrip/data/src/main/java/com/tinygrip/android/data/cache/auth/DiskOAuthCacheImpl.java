
package com.tinygrip.android.data.cache.auth;

import android.content.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.tinygrip.android.data.cache.DiskCache;
import com.tinygrip.android.data.cache.FileManager;
import com.tinygrip.android.data.cache.serializer.JacksonJsonSerializer;
import com.tinygrip.android.data.cache.serializer.JsonSerializer;
import com.tinygrip.android.data.cache.user.UserCache;
import com.tinygrip.android.data.entity.user.OAuthEntity;
import com.tinygrip.android.data.exception.user.UserNotFoundException;
import com.tinygrip.android.domain.executor.ThreadExecutor;
import java.io.File;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import rx.Observable;
import rx.Subscriber;

/**
 * {@link UserCache} implementation.
 */
@Singleton
@Named("diskOAuthCache")
public class DiskOAuthCacheImpl implements OAuthCache {

    private static final String OATH_KEY_ID = "com.tinygrip.android.OATH_KEY_ID";

    private static final String DEFAULT_FILE_NAME = "auth_";

    private final Context context;
    private final File cacheDir;
    private final JsonSerializer serializer;
    private final FileManager fileManager;
    private final ThreadExecutor threadExecutor;

    /**
     * Constructor of the class {@link DiskOAuthCacheImpl}.
     *
     * @param context A
     * @param jsonSerializer {@link JsonSerializer} for object serialization.
     * @param fileManager {@link FileManager} for saving serialized objects to the file system.
     */
    @Inject
    public DiskOAuthCacheImpl(Context context, JsonSerializer jsonSerializer,
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
    public Observable<OAuthEntity> get() {
        return Observable.create(new Observable.OnSubscribe<OAuthEntity>() {
            @Override
            public void call(Subscriber<? super OAuthEntity> subscriber) {
                File oAuthEntityFile = DiskOAuthCacheImpl.this.buildFile();
                String fileContent = DiskOAuthCacheImpl.this.fileManager.readFileContent(oAuthEntityFile);
                try {
                    OAuthEntity oAuthEntity = DiskOAuthCacheImpl.this.serializer.deserialize(fileContent, OAuthEntity.class);
                    if (oAuthEntity != null) {
                        subscriber.onNext(oAuthEntity);
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
    public synchronized void put(OAuthEntity oAuthEntity) {
        if (oAuthEntity != null) {
            File oAuthEntityFile = this.buildFile();

            // Lets cache every time even if we have it already.
            try {
                String jsonString = this.serializer.serialize(oAuthEntity);
                this.executeAsynchronously(new CacheWriter(this.fileManager, oAuthEntityFile,
                                                           jsonString));
            } catch (JsonProcessingException e) {
                // Fail silently
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
        boolean expired = false;
        // TODO - Determine if the token has expired
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
        fileNameBuilder.append(OATH_KEY_ID);

        return new File(fileNameBuilder.toString());
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
