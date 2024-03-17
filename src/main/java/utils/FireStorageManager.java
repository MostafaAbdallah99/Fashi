package utils;

import com.google.auth.ServiceAccountSigner;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class FireStorageManager {
    private static final String JSON_KEY = "/iti-e-commerce-a269d-firebase-adminsdk-aaojv-a478697772.json";
    private static final String STORAGE_BUCKET = "iti-e-commerce-a269d.appspot.com";
    private static final String CONTENT_TYPE = "image/jpg";
    FirebaseApp firebaseApp;
    Storage storage;

    private FireStorageManager(){
        try {
            readPrivateKey();
        } catch (IOException e) {
            throw new RuntimeException("Error reading private key");
        }
    }

    private static class SingletonHelper {
        private static final FireStorageManager INSTANCE = new FireStorageManager();
    }

    public static FireStorageManager getInstance() {
        return SingletonHelper.INSTANCE;
    }

    private void readPrivateKey() throws IOException {
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(Objects.requireNonNull(getClass().getResourceAsStream(JSON_KEY))))
                .setStorageBucket(STORAGE_BUCKET)
                .build();
        firebaseApp = FirebaseApp.initializeApp(options);
        storage = StorageOptions
                .newBuilder()
                .setCredentials(GoogleCredentials.fromStream(Objects.requireNonNull(this.getClass().getResourceAsStream(JSON_KEY))))
                .build()
                .getService();
    }

    public String uploadFileToStorage(byte[] fileBytes,String filename) throws IOException {
        Bucket bucket = StorageClient.getInstance(firebaseApp).bucket();
        BlobId blobId = BlobId.of(bucket.getName(),filename);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(CONTENT_TYPE).build();
        Blob blob = storage.create(blobInfo,fileBytes);
        URL url = blob.signUrl(100,TimeUnit.DAYS, Storage.SignUrlOption.signWith((ServiceAccountSigner) GoogleCredentials.fromStream(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream(JSON_KEY)))));
        return url.getProtocol() + "://" + url.getHost() + url.getFile();
    }
}

