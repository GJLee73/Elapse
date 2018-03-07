package com.chainsmokers.gjlee.elapse.list;

/**
 * Created by GILJAE on 2018-03-06.
 */

public class ChattingData {

    private String userId;
    private String userMessage;
    private boolean isServerMessage;

    public ChattingData (String userId, String userMessage, boolean isServerMessage) {
        this.userId = userId;
        this.userMessage = userMessage;
        this.isServerMessage = isServerMessage;
    }

    public String getUserId () {
        return userId;
    }

    public String getUserMessage () {
        return userMessage;
    }

    public boolean getIsServerMessage () {
        return isServerMessage;
    }

}
