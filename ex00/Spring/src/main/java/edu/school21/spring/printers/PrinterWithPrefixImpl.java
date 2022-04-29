package edu.school21.spring.printers;

import edu.school21.spring.renderers.Renderer;

public class PrinterWithPrefixImpl implements Printer {
    String prefix;
    Renderer renderer;

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    public PrinterWithPrefixImpl(String prefix, Renderer renderer) {
        this.prefix = prefix;
        this.renderer = renderer;
    }

    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void print(String str) {
        renderer.print(prefix + " " + str);

    }
}
