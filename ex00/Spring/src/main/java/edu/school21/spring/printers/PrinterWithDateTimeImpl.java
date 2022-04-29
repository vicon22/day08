package edu.school21.spring.printers;

import edu.school21.spring.renderers.Renderer;

import java.time.LocalTime;

public class PrinterWithDateTimeImpl implements Printer {
    String prefix;
    Renderer renderer;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }


    public PrinterWithDateTimeImpl(String prefix, Renderer renderer) {
        this.prefix = prefix;
        this.renderer = renderer;
    }

    @Override
    public void setPrefix(String prefix) {
//        LocalTime time = LocalTime.now();
//        this.prefix = time.getHour() + ":" + time.getMinute() + ":" + time.getSecond() + " ";
    }

    @Override
    public void print(String str) {
        LocalTime time = LocalTime.now();
        this.prefix = time.getHour() + ":" + time.getMinute() + ":" + time.getSecond() + " ";
        renderer.print(prefix + " " + str);
    }
}
