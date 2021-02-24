package com.github.jobsearch;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public interface CommanderFunctions {
    /**
     * JCommander allows to generate command-line options of any class, that's why the first parameter
     * is Object type.
     *
     * @param object Class from the JCommander arguments are generated
     * @return a JCommander instance. Preferably with CLIArguments as a object passed.
     */
    static JCommander buildCommander(Object object) {
        return JCommander
                .newBuilder()
                .addObject(object)
                .build();
    }

    /**
     * With this function, we make easier to create an intial configuration of JCommander, asking the
     * program name and a T Supplier type for the arguments. That way we will be able to use some function
     * which return an object which works as JCommander arguments.
     *
     * @param name              name to be shown on the CLI
     * @param argumentsSupplier a function which return an object with JCommander arguments
     * @param <T>               Type to be used for the arguments
     * @return an instance of {@link JCommander} already configured with name and arguments.
     */
    static <T> JCommander buildCommanderWithName(String name, Supplier<T> argumentsSupplier) {
        JCommander jCommander = buildCommander(argumentsSupplier.get());
        jCommander.setProgramName(name);
        return jCommander;
    }

    /**
     * Function used to take the JCommander data, the expected arguments and in case of something goes wrong,
     * a function with the JCommander which generated the error.
     */
    static Optional<List<Object>> parseArguments(
            JCommander jCommander,
            String[] arguments,
            OnCommandError onCommandError
    ) {
        List<Object> result;
        try {
            jCommander.parse(arguments);

            return Optional.of(jCommander.getObjects());
        } catch (ParameterException exception) {
            onCommandError.onError(jCommander);
        }

        return Optional.empty();
    }

    @FunctionalInterface
    interface OnCommandError {
        void onError(JCommander jCommander);
    }
}
