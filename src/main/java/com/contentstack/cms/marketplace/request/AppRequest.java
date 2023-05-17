package com.contentstack.cms.marketplace.request;

import com.contentstack.cms.Parametron;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import retrofit2.Call;
import retrofit2.Retrofit;

import java.util.HashMap;
import java.util.Map;


public class AppRequest implements Parametron {


    private final RequestService service;
    private final String organizationUid;
    private Map<String, String> headers;
    private Map<String, Object> queryParams;


    public AppRequest(@NotNull Retrofit client, @NotNull String orgId) {
        this.service = client.create(RequestService.class);
        this.organizationUid = orgId;
        this.headers = new HashMap<>();
        this.headers.put("organization_uid", this.organizationUid);
        this.queryParams = new HashMap<>();
    }


    Call<ResponseBody> create(JSONObject data) {
        return this.service.create(this.headers, data);
    }

    Call<ResponseBody> find() {
        return this.service.listRequests(this.headers, this.queryParams);
    }


    Call<ResponseBody> findRequestedStacks() {
        return this.service.listRequestedStacks(this.headers, this.queryParams);
    }

    Call<ResponseBody> delete(String requestId) {
        return this.service.deleteRequest(this.headers, requestId);
    }

    @Override
    public AppRequest addParam(@NotNull String key, @NotNull String value) {
        this.queryParams.put(key, value);
        return this;
    }

    @Override
    public AppRequest addHeader(@NotNull String key, @NotNull String value) {
        this.headers.put(key, value);
        return this;
    }

    @Override
    public AppRequest addParams(@NotNull HashMap params) {
        this.queryParams.putAll(params);
        return this;
    }


    @Override
    public AppRequest addHeaders(@NotNull HashMap headers) {
        this.headers.putAll(headers);
        return this;
    }
}
