package edu.school21.spring.preprocessors;

public class PreProcessorToUpperImpl implements PreProcessor {
    @Override
    public String change(String str) {
        return str.toUpperCase();
    }
}