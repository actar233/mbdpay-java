package com.github.actar233.mbdpay;

import java.util.*;

public class QueryStringBuilder {

    private final Map<String, List<String>> builder;

    public static QueryStringBuilder create() {
        return new QueryStringBuilder();
    }

    private QueryStringBuilder() {
        this.builder = new HashMap<>();
    }

    public QueryStringBuilder put(String key, String... values) {
        List<String> list = this.builder.get(key);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.addAll(Arrays.asList(values));
        this.builder.put(key, list);
        return this;
    }

    public QueryStringBuilder put(String key, String value) {
        List<String> list = this.builder.get(key);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(value);
        this.builder.put(key, list);
        return this;
    }

    public String build() {
        StringBuilder builder = new StringBuilder();
        String[] keys = this.builder.keySet()
                .stream()
                .sorted()
                .toArray(String[]::new);
        for (String key : keys) {
            List<String> values = this.builder.get(key);
            for (String value : values) {
                if (builder.length() > 0) {
                    builder.append('&');
                }
                builder.append(key);
                builder.append('=');
                builder.append(value);
            }
        }
        return builder.toString();
    }

}
