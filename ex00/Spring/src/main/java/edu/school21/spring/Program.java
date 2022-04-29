package edu.school21.spring;

import edu.school21.spring.printers.Printer;
import edu.school21.spring.renderers.Renderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {

    public static void main(String[] args) {
            ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
            Printer printer = context.getBean("PrinterWithPrefixRendererErrWithUpper", Printer.class);
            printer.print("Hello!");
        }
}
