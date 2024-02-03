package com.example.gemini;

public interface ResponseCallback {
    void onResponse(String response);
    void  onError(Throwable t);
}
