package eda.practica1;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class Idioma {

    private static Idioma instance;
    private final ResourceBundle bundle;

    private Idioma() {
        bundle = ResourceBundle.getBundle("literales");
    }

    public static Idioma getInstance() {
        if (instance == null) {
            instance = new Idioma();
        }
        return instance;
    }

    public String get(String key) {
        return bundle.getString(key);
    }

    public String get(String key, Object... args) {
        return MessageFormat.format(bundle.getString(key), args);
    }
}