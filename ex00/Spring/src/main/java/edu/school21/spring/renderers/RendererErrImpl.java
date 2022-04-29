package edu.school21.spring.renderers;

import edu.school21.spring.preprocessors.PreProcessor;

public class RendererErrImpl implements Renderer {
    PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }


    @Override
    public void print(String str) {
        System.err.println(preProcessor.change(str));
    }
}