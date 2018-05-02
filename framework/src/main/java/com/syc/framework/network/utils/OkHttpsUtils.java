package com.syc.framework.network.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.LinkedList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Created by shiyucheng on 2018/1/3.
 * Https
 */

public class OkHttpsUtils {
    public static SSLSocketFactory getSslSocketFactory(InputStream[] certificates, InputStream bksFile, String password) {
        try {
            X509TrustManager[] trustManagers = prepareTrustManager(certificates);
            KeyManager[] keyManagers = prepareKeyManager(bksFile, password);
            SSLContext sslContext = SSLContext.getInstance("TLS");

            sslContext.init(
                    keyManagers,
                    new TrustManager[]{new MyTrustManager(trustManagers)},
                    new SecureRandom()
            );

            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static HostnameVerifier getHostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
    }

    private static X509TrustManager[] prepareTrustManager(InputStream... certificates) {
        if (certificates == null || certificates.length <= 0) {
            return null;
        }

        List<X509TrustManager> trustManagers = new LinkedList<>();
        for (int index = 0; index < certificates.length; ++index) {
            InputStream certificate = certificates[index];
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                keyStore.load(null);

                String certificateAlias = Integer.toString(index);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));

                TrustManagerFactory trustManagerFactory = null;

                trustManagerFactory = TrustManagerFactory.
                        getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init(keyStore);

                X509TrustManager x509TrustManager = chooseX509TrustManager(trustManagerFactory.getTrustManagers());
                if (x509TrustManager != null) {
                    trustManagers.add(x509TrustManager);
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (CertificateException e) {
                e.printStackTrace();
            } catch (KeyStoreException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        X509TrustManager[] result = new X509TrustManager[trustManagers.size()];
        trustManagers.toArray(result);

        return result;
    }

    private static KeyManager[] prepareKeyManager(InputStream bksFile, String password) {
        try {
            if (bksFile == null || password == null) return null;

            KeyStore clientKeyStore = KeyStore.getInstance("BKS");
            clientKeyStore.load(bksFile, password.toCharArray());
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(clientKeyStore, password.toCharArray());
            return keyManagerFactory.getKeyManagers();

        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static X509TrustManager chooseX509TrustManager(TrustManager[] trustManagers) {
        for (TrustManager trustManager : trustManagers) {
            if (trustManager instanceof X509TrustManager) {
                return (X509TrustManager) trustManager;
            }
        }
        return null;
    }

    private static class MyTrustManager implements X509TrustManager {
        private X509TrustManager defaultTrustManager;
        private X509TrustManager[] localTrustManagers;

        public MyTrustManager(X509TrustManager[] localTrustManagers) throws NoSuchAlgorithmException, KeyStoreException {
            TrustManagerFactory var4 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            var4.init((KeyStore) null);
            this.defaultTrustManager = chooseX509TrustManager(var4.getTrustManagers());

            this.localTrustManagers = localTrustManagers;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            for (X509TrustManager tm : localTrustManagers) {
                try {
                    tm.checkServerTrusted(chain, authType);
                    return;
                } catch (CertificateException ce) {
                    // 尝试使用本地公钥检查,无需处理校验异常
                }
            }

            // 本地公钥都不适用,请求CA校验
            defaultTrustManager.checkServerTrusted(chain, authType);
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
}
