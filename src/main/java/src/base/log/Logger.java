package src.base.log;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Logger {
    private static List<LogType> allowedLogs = new ArrayList<>();
    
    public static void setAllowedLogs(LogType... allowedLogs){
        Logger.allowedLogs = Arrays.stream(allowedLogs).toList();
    }
    
    public static void log(String msg, LogType logType){
        if (!allowedLogs.contains(logType)) return;
        var output = MessageFormat.format("[{0}] {1}", logType.toString(), msg);
        System.out.println(output);
    }
}
