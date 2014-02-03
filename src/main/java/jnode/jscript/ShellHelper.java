package jnode.jscript;

import jnode.dto.Echoarea;
import jnode.ftn.FtnTools;
import jnode.jscript.impl.CommandExecResult;
import jnode.jscript.impl.LameCommand;
import jnode.logger.Logger;

import java.text.MessageFormat;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class ShellHelper extends IJscriptHelper {


    private final Logger logger = Logger.getLogger(getClass());

    @Override
    public Version getVersion() {
        return new Version() {
            @Override
            public int getMajor() {
                return 1;
            }

            @Override
            public int getMinor() {
                return 0;
            }
        };
    }

    /**
     * Выполнить команду и вывести результат в эхоарию
     * @param echoArea эхоария
     * @param subject сабжект
     * @param exec выполняема команда
     * @param cmd список аргументов для команды
     * @param workDir рабочая директория
     */
    public void execCommand(String echoArea, String subject, String exec, String cmd, String workDir){
        LameCommand command = new LameCommand(exec, cmd, workDir);
        CommandExecResult execResult = command.execute();

        if (execResult.getOutput() != null && execResult.getOutput().length() > 0){
            Echoarea area = FtnTools.getAreaByName(echoArea, null);

            if (execResult.getErrOutput().length() != 0){
                StringBuilder sb = new StringBuilder();
                sb.append(execResult.getOutput());
                sb.append("\nerror output:\n");
                sb.append(execResult.getErrOutput());
                FtnTools.writeEchomail(area, subject, execResult.getOutput());
            } else {
                FtnTools.writeEchomail(area, subject, execResult.getOutput());
            }

            logger.l4(MessageFormat.format("write message to {0} with subject {1}", area, subject));
        }

    }


}
