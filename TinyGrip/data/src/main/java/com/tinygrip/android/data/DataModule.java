package com.tinygrip.android.data;

import android.app.Application;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.tinygrip.android.data.api.ApiConfig;
import com.tinygrip.android.data.api.ApiModule;
import com.tinygrip.android.data.cache.CacheModule;
import dagger.Module;
import dagger.Provides;
import java.io.File;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * A module to wrap the Data related items it to the graph.
 */
@Module(
    includes = {
        ApiModule.class,
        CacheModule.class
    })
public class DataModule {

    static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Application app) {
        OkHttpClient client = createOkHttpClient(app);

        // TODO - MAKE SURE TO REMOVE THIS FROM HERE!!!
        client.setSslSocketFactory(createBadSslSocketFactory());

        // Ignore invalid SSL endpoints.
        client.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });

        return client;
    }

    @Provides
    @Singleton
    SessionData provideSessionData() {
        return new SessionData();
    }

    static OkHttpClient createOkHttpClient(Application app) {
        OkHttpClient client = new OkHttpClient();

        client.setConnectTimeout(ApiConfig.CONNECT_TIMEOUT, TimeUnit.SECONDS); // connect timeout
        client.setReadTimeout(ApiConfig.READ_TIMEOUT, TimeUnit.SECONDS);    // socket timeout

        // Install an HTTP cache in the application cache directory.
        File cacheDir = new File(app.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
        client.setCache(cache);

        return client;
    }

    private static SSLSocketFactory createBadSslSocketFactory() {
        try {
            // Construct SSLSocketFactory that accepts any cert.
            SSLContext context = SSLContext.getInstance("TLS");
            TrustManager permissive = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            context.init(null, new TrustManager[] { permissive }, null);
            return context.getSocketFactory();
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }
}
