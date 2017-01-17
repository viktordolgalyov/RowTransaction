package com.rowtransactiondemo;

import java.io.Serializable;

class Movie implements Serializable {
    static final long serialVersionUID = 727566175075960653L;
    private static long count = 0;
    private long id;
    private String title;
    private String cardImageUrl;
    private String studio;

    static long getCount() {
        return count;
    }

    static void incCount() {
        count++;
    }

    long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getStudio() {
        return studio;
    }

    void setStudio(String studio) {
        this.studio = studio;
    }

    String getCardImageUrl() {
        return cardImageUrl;
    }

    void setCardImageUrl(String cardImageUrl) {
        this.cardImageUrl = cardImageUrl;
    }
}
