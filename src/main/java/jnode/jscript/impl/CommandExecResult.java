package jnode.jscript.impl;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public final class CommandExecResult {
    private int status;
    private String output = "";
    private String errOutput = "";

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getErrOutput() {
        return errOutput;
    }

    public void setErrOutput(String errOutput) {
        this.errOutput = errOutput;
    }

    @Override
    public String toString() {
        return "CommandExecResult{" + "status=" + status + ", output='" + output + '\'' + ", errOutput='" + errOutput + '\'' + '}';
    }
}
