package com.github.jobsearch;

import com.beust.jcommander.JCommander;
import com.github.jobsearch.api.JobsAPI;
import com.github.jobsearch.cli.CLIArguments;
import com.github.jobsearch.cli.CLIFunctions;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static com.github.jobsearch.api.APIFunctions.buildAPI;

public class JobSearch {
    public static void main(String[] args) {
        //Creation of our CLI with JCommander
        JCommander jCommander = CommanderFunctions.buildCommanderWithName("job-search", CLIArguments::newInstance);

            //We get the options sent into a JCommander
        Stream<CLIArguments> streamOfCLI =
                //it returns a Optional<List<Object>>
                CommanderFunctions.parseArguments(jCommander, args, JCommander::usage)
                        //En caso de un Optional.empty()
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(obj -> (CLIArguments) obj);

        //We take our Stream and then we get the options given into the CLI
        Optional<CLIArguments> cliOptional = streamOfCLI
                //We are only interested in the cases where are not being a help request
                .filter(cli -> !cli.isHelp())
                //and they contain a keyword, in other cases we don't have to search
                .filter(cli -> cli.getKeyword() != null)
                .findFirst();

        //If Optional contains a data, we convert it in a Map<String,Object>
        cliOptional.map(CLIFunctions::toMap)
                //We convert the Map to a request
                .map(JobSearch::executeRequest)
                //We still keep operation on an Optional… in case there had no more data
                //We generate an empty stream
                .orElse(Stream.empty())
                //Finally, we print the data on the screen.
                .forEach(System.out::println);
    }

    private static Stream<JobPosition> executeRequest(Map<String, Object> options) {
        JobsAPI api = buildAPI(JobsAPI.class, "https://jobs.github.com");

        return Stream.of(options)
                .map(api::jobs)
                .flatMap(Collection::stream);
    }
}