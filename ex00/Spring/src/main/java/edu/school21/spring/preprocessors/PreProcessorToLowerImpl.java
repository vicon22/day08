package edu.school21.spring.preprocessors;

public class PreProcessorToLowerImpl implements PreProcessor {
    @Override
    public String change(String str) {
        return str.toLowerCase();
    }
}