package com.example.yaphet.a08;

/**
 * Created by WYF on 2017/10/11.
 */

class Content {
    public Content(String content) {
        this.content = content;
    }

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Content{" +
                "content='" + content + '\'' +
                '}';
    }
}
