package edu.school21.spring.renderers;

import edu.school21.spring.preprocessors.PreProcessor;

public class RendererStandardImpl implements Renderer{
    PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void print(String str) {
        System.out.println(preProcessor.change(str));
    }
}