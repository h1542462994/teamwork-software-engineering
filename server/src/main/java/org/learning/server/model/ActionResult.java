package org.learning.server.model;

public class ActionResult<T> {
    String result;
    boolean failed = false;
    T value;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isFailed() {
        return failed;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public ActionResult<T> value(T value){
        this.value = value;
        return this;
    }

    public ActionResult<T> error(String actionResult) {
        this.failed = true;
        this.result = actionResult;
        return this;
    }

    public ActionResult<T> success() {
        this.failed = false;
        return this;
    }

    public ActionResult<T> success(String actionResult) {
        this.failed = false;
        this.result = actionResult;
        return this;
    }
}
