package com.integrate.e9plugin.service.impl.utils;


import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;


public class OkHttpUtils {

    private static final Logger log = LoggerFactory.getLogger(OkHttpUtils.class);

    private static final String HTTP_JSON = "application/json; charset=utf-8";
    private static final String HTTP_FORM = "application/x-www-form-urlencoded; charset=utf-8";

    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build();


    /**
     * get请求
     * 对于小文档，响应体上的string()方法非常方便和高效。
     * 但是，如果响应主体很大(大于1 MB)，则应避免string()，
     * 因为它会将整个文档加载到内存中。在这种情况下，将主体处理为流。
     *
     * @param url
     * @return
     */
    public static String httpGet(String url) {
        if (url == null || "".equals(url)) {
            log.error("url为null!");
            return "";
        }

        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(url).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 200) {
                log.info("http GET 请求成功; [url={}]", url);
                return response.body().string();
            } else {
                log.warn("Http GET 请求失败; [errorCode = {} , url={}]", response.code(), url);
            }
        } catch (IOException e) {
            throw new RuntimeException("同步http GET 请求失败,url:" + url, e);
        }
        return null;
    }

    public static String httpGet(String url, Map<String, String> headers) {
        if (CollectionUtils.isEmpty(headers)) {
            return httpGet(url);
        }

        Request.Builder builder = new Request.Builder();
        headers.forEach((String key, String value) -> builder.header(key, value));
        Request request = builder.get().url(url).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 200) {
                log.info("http GET 请求成功; [url={}]", url);
                return response.body().string();
            } else {
                log.warn("Http GET 请求失败; [errorxxCode = {} , url={}]", response.code(), url);
            }
        } catch (IOException e) {
            throw new RuntimeException("同步http GET 请求失败,url:" + url, e);
        }
        return null;
    }

    /**
     * 同步 POST调用 无Header
     *
     * @param url
     * @param json
     * @return
     */
    public static String httpPostJson(String url, String json) {
        if (url == null || "".equals(url)) {
            log.error("url为null!");
            return "";
        }

        MediaType JSON = MediaType.parse(HTTP_JSON);
        RequestBody body = RequestBody.create(JSON, json);
        Request.Builder requestBuilder = new Request.Builder().url(url);
        Request request = requestBuilder.post(body).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 200) {
                log.info("http Post 请求成功; [url={}, requestContent={}]", url, json);
                return response.body().string();
            } else {
                log.warn("Http POST 请求失败; [ errorCode = {}, url={}, param={}]", response.code(), url, json);
            }
        } catch (IOException e) {
            throw new RuntimeException("同步http请求失败,url:" + url, e);
        }
        return null;
    }

    /**
     * 同步 POST调用 有Header
     *
     * @param url
     * @param headers
     * @param json
     * @return
     */
    public static String httpPostJson(String url, Map<String, String> headers, String json) {
        if (CollectionUtils.isEmpty(headers)) {
            httpPostJson(url, json);
        }

        MediaType JSON = MediaType.parse(HTTP_JSON);
        RequestBody body = RequestBody.create(JSON, json);
        Request.Builder requestBuilder = new Request.Builder().url(url);
        headers.forEach((k, v) -> requestBuilder.addHeader(k, v));
        Request request = requestBuilder.post(body).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 200) {
                log.info("http Post 请求成功; [url={}, requestContent={}]", url, json);
                return response.body().string();
            } else {
                log.warn("Http POST 请求失败; [ errorCode = {}, url={}, param={}]", response.code(), url, json);
            }
        } catch (IOException e) {
            throw new RuntimeException("同步http请求失败,url:" + url, e);
        }
        return null;
    }

    /**
     * 提交表单
     * @param url
     * @param content
     * @param headers
     * @return
     */
    public static String postDataByForm(String url, String content, Map<String, String> headers) {
        MediaType JSON = MediaType.parse(HTTP_FORM);
        RequestBody body = RequestBody.create(JSON, content);

        Request.Builder requestBuilder = new Request.Builder().url(url);
        if (headers != null && headers.size() > 0) {
            headers.forEach((k, v) -> requestBuilder.addHeader(k, v));
        }
        Request request = requestBuilder
                .post(body)
                .build();

        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.code() == 200) {
                log.info("postDataByForm; [postUrl={}, requestContent={}, responseCode={}]", url, content, response.code());
                return response.body().string();
            } else {
                log.warn("Http Post Form请求失败,[url={}, param={}]", url, content);
            }
        } catch (IOException e) {
            log.error("Http Post Form请求失败,[url={}, param={}]", url, content, e);
            throw new RuntimeException("Http Post Form请求失败,url:" + url);
        }
        return null;
    }

    /**
     * 异步Http调用参考模板：Get、Post、Put
     * 需要异步调用的接口一般情况下你需要定制一个专门的Http方法
     *
     * @param httpMethod
     * @param url
     * @param content
     * @return
     */
    @Deprecated
    public static CompletableFuture<Boolean> asyncHttpByJson(String httpMethod, String url, Map<String, String> headers, String content) {
        MediaType JSON = MediaType.parse(HTTP_JSON);
        RequestBody body = RequestBody.create(JSON, content);

        Request.Builder requestBuilder = new Request.Builder()
                .url(url);

        if (!CollectionUtils.isEmpty(headers)) {
            headers.forEach((key, value) -> requestBuilder.header(key, value));
        }

        switch (httpMethod) {
            case "GET":
                requestBuilder.get();
                break;
            case "POST":
                requestBuilder.post(body);
                break;
            default:
        }

        Request request = requestBuilder.build();
        Call call = okHttpClient.newCall(request);
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log.error("异步http {} 请求失败,[url={}, param={}]", httpMethod, url, content);
                throw new RuntimeException("异步http请求失败,url:" + url);
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response.code() == 200) {
                    future.complete(true);
                } else {
                    log.error("异步http {} 请求失败,错误码为{},请求参数为[url={}, param={}]", httpMethod, response.code(), url, content);
                    future.complete(false);
                }
            }
        });
        return future;
    }



    /**
     * lambda表达式异步调用http模板,不建议使用
     *
     * @param request
     * @param failure
     * @param respConsumer
     */
    public static void asyncCall(Request request, Consumer<Exception> failure, Consumer<Response> respConsumer) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                failure.accept(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                respConsumer.accept(response);
            }
        });
    }

    //test
    public static void main(String[] args) {
        String url = "http://www.httpbin.org/ip";
        System.out.println(httpGet(url));
    }

}