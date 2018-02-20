package com.chainsmokers.gjlee.elapse.list;

/**
 * Created by GILJAE on 2018-02-15.
 */

public class GamesData {

    private String gameName;
    private int gameImage;
    private String gameCompany;

    public GamesData (String gameName, int gameImage, String gameCompany) {
        this.gameName = gameName;
        this.gameImage = gameImage;
        this.gameCompany = gameCompany;
    }

    public String getName () {
        return this.gameName;
    }

    public int getImage () {
        return this.gameImage;
    }

    public String getGameCompany () {
        return this.gameCompany;
    }

}
