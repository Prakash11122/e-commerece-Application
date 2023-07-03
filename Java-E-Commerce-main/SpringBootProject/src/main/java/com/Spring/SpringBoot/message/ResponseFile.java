//controller will use both class of message package for sending
//message via HTTP responses
package com.Spring.SpringBoot.message;

public class ResponseFile {
    private String name;
    private  String url;
    private  String type;
//    private double size;

    public ResponseFile() {
    }

    public ResponseFile(String name, String url, String type) {
        this.name = name;
        this.url = url;
        this.type = type;
  //      this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
