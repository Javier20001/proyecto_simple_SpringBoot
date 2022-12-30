package mx.com.gm.validation.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import mx.com.gm.validation.ValidarCaracterEspeciales;

public class ValidarCaracterEspecialesImpl implements ConstraintValidator<ValidarCaracterEspeciales, String> {

    private List<String> ltsCaracteres;

    @Override
    public void initialize(ValidarCaracterEspeciales validarNombre) {
        this.ltsCaracteres = new ArrayList<>();
        ltsCaracteres.add("_");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean correcto = false;
        String strSource = "[~!@#$%^&*()_+{}\\[\\]:;,.<>/?-]";
        if (value != null) {
            Pattern p = Pattern.compile(strSource);
            Matcher m = p.matcher(value);
            correcto = m.find();
        }
        return correcto;
    }
}
