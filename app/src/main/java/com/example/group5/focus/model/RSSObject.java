package com.example.group5.focus.model;

import java.util.List;

/**
 * Created by reale on 5/5/2017.
 */

public class RSSObject
{
    public String status;
    public com.example.group5.focus.model.Feed feed;
    public List<Item> items;

    public RSSObject(String status, com.example.group5.focus.model.Feed feed, List<Item> items) {
        this.status = status;
        this.feed = feed;
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public com.example.group5.focus.model.Feed getFeed() {
        return feed;
    }

    public void setFeed(com.example.group5.focus.model.Feed feed) {
        this.feed = feed;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}