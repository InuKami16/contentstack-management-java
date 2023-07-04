package com.contentstack.cms.marketplace.apps.oauth;

import com.contentstack.cms.Parametron;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import retrofit2.Call;
import retrofit2.Retrofit;

import java.util.HashMap;

public class Oauth implements Parametron {

    private final OauthService service;
    protected HashMap<String, String> headers;
    protected HashMap<String, Object> params;
    private String appId;

    public Oauth(Retrofit client, String organizationId) {
        this.headers = new HashMap<>();
        this.params = new HashMap<>();
        this.headers.put("organization_uid", organizationId);
        this.service = client.create(OauthService.class);
    }

    public Oauth(Retrofit client, String organizationId, @NotNull String appId) {
        this.headers = new HashMap<>();
        this.params = new HashMap<>();
        if (appId.isEmpty()) {
            throw new NullPointerException("App Id is required");
        }
        this.appId = appId;
        if (organizationId.isEmpty()) {
            throw new IllegalArgumentException("Organization uid could not be empty");
        }
        this.headers.put("organization_uid", organizationId);
        this.service = client.create(OauthService.class);
    }

    public Call<ResponseBody> fetchOauthConfiguration(@NotNull String appId) {
        return service.getOauthConfiguration(this.headers, appId);
    }

    Call<ResponseBody> updateOauthConfiguration(JSONObject body) {
        return service.updateOauthConfiguration(this.headers, this.appId, body);
    }

    Call<ResponseBody> findScopes() {
        return service.findScopes(this.headers);
    }

    /**
     * Adds a header with the specified key and value to this location and returns the updated location.
     *
     * @param key
     *         the key of the header to be added
     * @param value
     *         the value of the header to be added
     * @return a new {@link Oauth} object with the specified header added
     * @throws NullPointerException
     *         if the key or value argument is null
     */
    @SuppressWarnings("unknown")
    @Override
    public Oauth addParam(@NotNull String key, @NotNull Object value) {
        this.params.put(key, value);
        return this;
    }

    /**
     * Adds a header with the specified key and value to this location and returns the updated location.
     *
     * @param key
     *         the key of the header to be added
     * @param value
     *         the value of the header to be added
     * @return a new {@link Oauth} object with the specified header added
     * @throws NullPointerException
     *         if the key or value argument is null
     */
    @SuppressWarnings("unknown")
    @Override
    public Oauth addHeader(@NotNull String key, @NotNull String value) {
        this.headers.put(key, value);
        return this;
    }

    /**
     * Adds the specified parameters to this location and returns the updated location.
     *
     * @param params
     *         a {@link HashMap} containing the parameters to be added
     * @return a new {@link Oauth} object with the specified parameters added
     * @throws NullPointerException
     *         if the params argument is null
     */
    @SuppressWarnings("unknown")
    @Override
    public Oauth addParams(@NotNull HashMap params) {
        this.params.putAll(params);
        return this;
    }

    /**
     * Adds the specified parameters to this location and returns the updated location.
     *
     * @param headers
     *         a {@link HashMap} containing the parameters to be added
     * @return a new {@link Oauth} object with the specified parameters added
     * @throws NullPointerException
     *         if the params argument is null
     */
    @SuppressWarnings("unknown")
    @Override
    public Oauth addHeaders(@NotNull HashMap headers) {
        this.headers.putAll(headers);
        return this;
    }


}
