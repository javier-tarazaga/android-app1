
package com.tinygrip.android.presentation.internal.di.modules;

import android.app.Application;
import android.content.Context;
import com.tinygrip.android.data.cache.root.MemoryRootCacheImpl;
import com.tinygrip.android.data.cache.root.RootCache;
import com.tinygrip.android.data.cache.user.MemoryUserCacheImpl;
import com.tinygrip.android.data.cache.user.UserCache;
import com.tinygrip.android.data.executor.JobExecutor;
import com.tinygrip.android.data.repository.AreaDataRepository;
import com.tinygrip.android.data.repository.RootDataRepository;
import com.tinygrip.android.data.repository.UserDataRepository;
import com.tinygrip.android.domain.executor.PostExecutionThread;
import com.tinygrip.android.domain.executor.ThreadExecutor;
import com.tinygrip.android.domain.repository.AreaRepository;
import com.tinygrip.android.domain.repository.RootRepository;
import com.tinygrip.android.domain.repository.UserRepository;
import com.tinygrip.android.presentation.AndroidApplication;
import com.tinygrip.android.presentation.ApplicationRouter;
import com.tinygrip.android.presentation.UIThread;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {

    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return this.application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ApplicationRouter provideNavigator() {
        return new ApplicationRouter();
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    UserCache provideUserCache(MemoryUserCacheImpl userCache) {
        return userCache;
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository(UserDataRepository userDataRepository) {
        return userDataRepository;
    }

    @Provides
    @Singleton
    RootCache providesRootCache(MemoryRootCacheImpl rootCache) {
        return rootCache;
    }

    @Provides
    @Singleton
    RootRepository provideRootRepository(RootDataRepository rootDataRepository) {
        return rootDataRepository;
    }

    @Provides
    @Singleton
    AreaRepository providesAreaRepository(AreaDataRepository areaRepository) {
        return areaRepository;
    }
}
