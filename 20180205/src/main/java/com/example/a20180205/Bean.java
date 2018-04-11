package com.example.a20180205;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2018/2/6.
 */

public class Bean {
    private int status;
    private String info;
    private List<databean> data;

    public int getStatus() {

        return status;
    }

    public String getInfo() {
        return info;
    }

    public List<databean> getData() {
        return data;
    }

    public void setData(List<databean> data) {
        this.data = data;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public  class databean{
        private String news_id;
        private String news_title;
        private String news_summary;
        private String pic_url;

        public String getNews_id() {
            return news_id;
        }

        public void setNews_id(String news_id) {
            this.news_id = news_id;
        }

        public String getNews_title() {
            return news_title;
        }

        public void setNews_title(String news_title) {
            this.news_title = news_title;
        }

        public String getNews_summary() {
            return news_summary;
        }

        public void setNews_summary(String news_summary) {
            this.news_summary = news_summary;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }
    }
}
