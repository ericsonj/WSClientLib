package net.ericsonj.wsclient;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import net.ericsonj.wsclient.exception.WSClientJSONException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author ejoseph
 * @param <S>
 * @param <U>
 */
public abstract class WSClientJSON<S, U> implements WSClient<S, U> {

    public abstract Class<S> getResponseElementClass();

    public abstract Class<U> getRequestElementClass();

    @Override
    public S getObjectByGET(String url) throws WSClientJSONException {

        try {
            ObjectMapper mapper = new ObjectMapper();

            RequestConfig config = RequestConfig.custom().setConnectTimeout(30000).setSocketTimeout(30000).build();
            HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();

            HttpGet get = new HttpGet(url);
            get.setHeader("Content-Type", "application/json");
            get.setHeader("Accept", "application/json");

            HttpResponse resp = httpClient.execute(get);
            InputStream is = resp.getEntity().getContent();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte array[] = new byte[1024];
            int readId;
            while ((readId = is.read(array)) > 0) {
                bos.write(array, 0, readId);
            }
            String strResponse = new String(bos.toByteArray());

            S respValue = mapper.readValue(strResponse, getResponseElementClass());
            return respValue;

        } catch (IOException ex) {
            throw new WSClientJSONException(ex.getMessage(), ex);
        }

    }

    @Override
    public S getObjectByPOST(String url) throws WSClientJSONException {
        try {
            ObjectMapper mapper = new ObjectMapper();

            RequestConfig config = RequestConfig.custom().setConnectTimeout(30000).setSocketTimeout(30000).build();
            HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();

            HttpPost post = new HttpPost(url);
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Accept", "application/json");

            HttpResponse resp = httpClient.execute(post);
            InputStream is = resp.getEntity().getContent();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte array[] = new byte[1024];
            int readId;
            while ((readId = is.read(array)) > 0) {
                bos.write(array, 0, readId);
            }
            String strResponse = new String(bos.toByteArray());

            S respValue = mapper.readValue(strResponse, getResponseElementClass());
            return respValue;

        } catch (IOException ex) {
            throw new WSClientJSONException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<S> getListByGET(String url) throws WSClientJSONException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

            RequestConfig config = RequestConfig.custom().setConnectTimeout(30000).setSocketTimeout(30000).build();
            HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();

            HttpGet get = new HttpGet(url);
            get.setHeader("Content-Type", "application/json");
            get.setHeader("Accept", "application/json");

            HttpResponse resp = httpClient.execute(get);
            InputStream is = resp.getEntity().getContent();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte array[] = new byte[1024];
            int readId;
            while ((readId = is.read(array)) > 0) {
                bos.write(array, 0, readId);
            }
            String strResponse = new String(bos.toByteArray());
            JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, getResponseElementClass());

            List<S> respValue = mapper.readValue(strResponse, type);

            return respValue;

        } catch (IOException ex) {
            throw new WSClientJSONException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<S> getListByPOST(String url) throws WSClientJSONException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

            RequestConfig config = RequestConfig.custom().setConnectTimeout(30000).setSocketTimeout(30000).build();
            HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();

            HttpPost post = new HttpPost(url);
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Accept", "application/json");

            HttpResponse resp = httpClient.execute(post);
            InputStream is = resp.getEntity().getContent();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte array[] = new byte[1024];
            int readId;
            while ((readId = is.read(array)) > 0) {
                bos.write(array, 0, readId);
            }
            String strResponse = new String(bos.toByteArray());
            JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, getResponseElementClass());

            List<S> respValue = mapper.readValue(strResponse, type);

            return respValue;

        } catch (IOException ex) {
            throw new WSClientJSONException(ex.getMessage(), ex);
        }
    }

    @Override
    public S getObjectByQuery(String url, U u) throws WSClientJSONException {

        try {
            ObjectMapper mapper = new ObjectMapper();
            String requestJson = mapper.writeValueAsString(u);

            RequestConfig config = RequestConfig.custom().setConnectTimeout(30000).setSocketTimeout(30000).build();
            HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();

            HttpPost post;
            post = new HttpPost(url);
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Accept", "application/json");
            post.setEntity(new StringEntity(requestJson));

            HttpResponse resp = httpClient.execute(post);
            InputStream is = resp.getEntity().getContent();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte array[] = new byte[1024];
            int readId;
            while ((readId = is.read(array)) > 0) {
                bos.write(array, 0, readId);
            }
            String strResponse = new String(bos.toByteArray());
            S respValue = mapper.readValue(strResponse, getResponseElementClass());
            return respValue;
        } catch (IOException ex) {
            throw new WSClientJSONException(ex.getMessage(), ex);
        }

    }

    @Override
    public S sendList(String url, LinkedList<U> ul) throws WSClientJSONException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String requestJson = mapper.writeValueAsString(ul);
            
            RequestConfig config = RequestConfig.custom().setConnectTimeout(30000).setSocketTimeout(30000).build();
            HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();

            HttpPost post;
            post = new HttpPost(url);
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Accept", "application/json");
            post.setEntity(new StringEntity(requestJson));

            HttpResponse resp = httpClient.execute(post);
            InputStream is = resp.getEntity().getContent();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte array[] = new byte[1024];
            int readId;
            while ((readId = is.read(array)) > 0) {
                bos.write(array, 0, readId);
            }
            String strResponse = new String(bos.toByteArray());
            S respValue = mapper.readValue(strResponse, getResponseElementClass());
            return respValue;
        } catch (IOException ex) {
            throw new WSClientJSONException(ex.getMessage(), ex);
        }
    }
    
}
