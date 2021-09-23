package it1901.util;

import java.util.function.UnaryOperator;

import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;

public class TextFieldFormatter {

    /**
     * returns a TextFormatter that only allows numbers in text fields
     */    
    public static TextFormatter<String> getNumberFormatter() {
        UnaryOperator<Change> filter = change -> {
            String text = change.getText();
        
            if (text.matches("[0-9]*")) {
                return change;
            }
        
            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter); 
        return textFormatter;
    }

}