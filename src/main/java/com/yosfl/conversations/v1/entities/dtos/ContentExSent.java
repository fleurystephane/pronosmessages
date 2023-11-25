package com.yosfl.conversations.v1.entities.dtos;

public class ContentExSent {
    private long nbSent;

    public ContentExSent(long res) {
        this.nbSent = res;
    }

    public long getNbSent() {
        return nbSent;
    }
}
