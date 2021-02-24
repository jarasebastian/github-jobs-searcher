package com.github.jobsearch.cli;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

/**
 * Class used by JCommander for validating arguments.
 * <p>
 * In our case we use it to validate the skill asked (keyword) is composed by only characters and numbers.
 */
public class CLIKeywordValidator implements IParameterValidator {
    @Override
    public void validate(String name, String value) throws ParameterException {
        if (!value.matches("^[a-zA-Z]+[0-9]*$")) {
            System.err.println("Keyword: " + value + " is not a valid Keyword, keywords should be alphanumerics.\n");
            throw new ParameterException("Only alphanumerics are supported");
        }
    }
}
