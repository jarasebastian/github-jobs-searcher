package com.github.jobsearch.cli;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

/**
 * Class internally used by JCommande in order to validate certain arguments.
 * In our case we will use it to stop the parse of arguments in case we detect the help option
 */
public class CLIHelpValidator implements IParameterValidator {

    @Override
    public void validate(String name, String value) throws ParameterException {
        boolean actualValue = Boolean.parseBoolean(value);
        if (actualValue) {
            throw new ParameterException("Help passed");
        }
    }
}
