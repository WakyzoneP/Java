package com.example;

import java.io.Serializable;
import java.net.URL;

public class ObjectsGraph implements Serializable {
    private static final long serialVersionUID = 1L;

    private URL url1;
    private URL url2;

    public ObjectsGraph(URL url1, URL url2) {
        this.url1 = url1;
        this.url2 = url2;
    }

    public URL getUrl1() {
        return url1;
    }

    public URL getUrl2() {
        return url2;
    }

    @Override
    public String toString() {
        return new String("ObjectsGraph: url1=" + url1 + ", url2=" + url2);
    }
}
