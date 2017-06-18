package net.ericsonj.wsclient;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ejoseph
 * @param <S>
 * @param <U>
 */
public interface WSClient<S, U> {

    /**
     *
     * @param url
     * @return
     * @throws java.lang.Exception
     */
    public S getObjectByGET(String url) throws Exception;

    /**
     *
     * @param url
     * @return
     * @throws java.lang.Exception
     */
    public S getObjectByPOST(String url) throws Exception;

    /**
     *
     * @param url
     * @return
     * @throws java.lang.Exception
     */
    public List<S> getListByGET(String url) throws Exception;

    /**
     *
     * @param url
     * @return
     * @throws java.lang.Exception
     */
    public List<S> getListByPOST(String url) throws Exception;

    /**
     * Get object S by query object U. Use POST method in web service;
     *
     * @param url
     * @param u
     * @return
     * @throws java.lang.Exception
     */
    public S getObjectByQuery(String url, U u) throws Exception;

    /**
     * Send list of U. Use POST method
     *
     * @param url
     * @param ul
     * @return
     * @throws java.lang.Exception
     */
    public S sendList(String url, LinkedList<U> ul) throws Exception;

}
