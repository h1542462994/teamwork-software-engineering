package org.software.learning.model.common;

public class ResponseToken {
    private int code;
    private String summary;
    private String defaultMsg = "";

    public ResponseToken(int code, String summary) {
        this.code = code;
        this.summary = summary;
    }

    public ResponseToken(int code, String summary, String defaultMsg) {
        this.code = code;
        this.summary = summary;
        this.defaultMsg = defaultMsg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDefaultMsg() {
        return defaultMsg;
    }

    public void setDefaultMsg(String defaultMsg) {
        this.defaultMsg = defaultMsg;
    }
}
