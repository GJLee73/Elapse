package com.chainsmokers.gjlee.elapse.list;

/**
 * Created by GILJAE on 2018-03-04.
 */

public class RoomsData {

   private int thumbnail;
   private String streamName;
   private String streamerName;
   private String streamWatcher;

   public RoomsData (int thumbnail, String streamName, String streamerName, String streamWatcher) {
       this.thumbnail = thumbnail;
       this.streamName = streamName;
       this.streamerName = streamerName;
       this.streamWatcher = streamWatcher;
   }

   public int getThumbnail () {
       return this.thumbnail;
   }

    public String getStreamName () {
        return this.streamName;
    }

    public String getStreamerName () {
        return this.streamerName;
    }

    public String getStreamWatcher () {
        return this.streamWatcher;
    }

}
