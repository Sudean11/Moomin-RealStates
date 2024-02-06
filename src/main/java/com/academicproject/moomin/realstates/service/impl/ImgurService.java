package com.academicproject.moomin.realstates.service.impl;

import okhttp3.*;

import java.io.IOException;

public class ImgurService {
    private static final String IMGUR_UPLOAD_URL = "https://api.imgur.com/3/image";
    private static final String CLIENT_ID = "7d0638aeccf1a23";

    private final OkHttpClient client;

    public ImgurService() {
        this.client = new OkHttpClient();
    }

    public String uploadImage(byte[] imageData) throws IOException {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", "image.png",
                        RequestBody.create(MediaType.parse("image/*"), imageData))
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + CLIENT_ID)
                .url(IMGUR_UPLOAD_URL)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            return response.body().string();
        }
    }
}
