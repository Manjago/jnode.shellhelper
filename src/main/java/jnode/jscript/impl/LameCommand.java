package jnode.jscript.impl;

import jnode.logger.Logger;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.StreamConsumer;

import static org.codehaus.plexus.util.cli.CommandLineUtils.StringStreamConsumer;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class LameCommand
{

    private final Logger logger = Logger.getLogger(getClass());
    private final Commandline command;

    public LameCommand(String exec, String cmd, String workDir) {
        command = new Commandline();
        command.setExecutable(exec);
        command.setWorkingDirectory(workDir);
        command.createArg().setValue(cmd);
    }

    private CommandExecResult execute(StreamConsumer out, StringStreamConsumer err)
    {
        CommandExecResult result = new CommandExecResult();
        logger.l5("Command line - " + getCommandString());
        logger.l5("Working directory - " + getWorkDir());
        int status;
        try {
            status = CommandLineUtils.executeCommandLine(command, out, err);
        } catch (CommandLineException e) {
            logger.l3("fail execute command " + this, e);
            result.setStatus(-1);
            return result;
        }
        result.setErrOutput(err.getOutput());
        if (out instanceof StringStreamConsumer) {
            StringStreamConsumer sc = (StringStreamConsumer) out;
            result.setOutput(sc.getOutput());
        }

        result.setStatus(status);
        logger.l5("execution result " + result);
        return result;
    }

    public CommandExecResult execute()  {
        StringStreamConsumer out = new StringStreamConsumer();
        StringStreamConsumer err = new StringStreamConsumer();
        return execute(out, err);
    }

    String getCommandString() {
        return command.toString();
    }

    Commandline getCommandline() {
        return command;
    }

    String getWorkDir() {
        return command.getWorkingDirectory().toString();
    }

    @Override
    public String toString() {
        return "LameCommand{" + "command=" + getCommandline() + '}';
    }
}
