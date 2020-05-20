package com.andevs.taller.mvc.controller;

import com.andevs.taller.mvc.view.log.LogView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LogController implements ActionListener {
    private LogView logView;

    public LogController(LogView logView) {
        this.logView = logView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(logView.getCleanButton())) {
            logView.getLogtextArea().setText("No hay registros para mostrar");
        }
        if (e.getSource().equals(logView.getExitButton())) {
            logView.dispose();
        }
    }
}
