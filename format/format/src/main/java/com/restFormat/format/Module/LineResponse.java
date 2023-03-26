package com.restFormat.format.Module;

public class LineResponse {
    private int lineValid;
    private int lineInvalid;

    public LineResponse(int lineValid, int lineInvalid) {
        this.lineValid = lineValid;
        this.lineInvalid = lineInvalid;
    }

    public LineResponse(){}

    public int getLineValid() {
        return lineValid;
    }

    public void setLineValid(int lineValid) {
        this.lineValid = lineValid;
    }

    public int getLineInvalid() {
        return lineInvalid;
    }

    public void setLineInvalid(int lineInvalid) {
        this.lineInvalid = lineInvalid;
    }
}
