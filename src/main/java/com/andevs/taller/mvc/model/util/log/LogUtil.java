package com.andevs.taller.mvc.model.util.log;

import java.util.Date;

public class LogUtil {
    private static String log = "";

    public static void writeInLog(String record) {
        log += "\n".concat(new Date().toString().concat(" ".concat(record)));
    }

    public static String getLog() {
        if (log.equals("")) {
            return "No hay registros para mostrar";
        }
        return log;
    }
}
