package com.github.jobsearch.cli;

import com.beust.jcommander.Parameter;

public final class CLIArguments {
    /**
     * Default constructor which allows that only the package classes are able to create objects
     * from this class.
     * This way we force the construction is performed using public functions or builders.
     * <p>
     * The instances should be created using: CLIArguments#newInstance
     */
    CLIArguments() {
    }

    @Parameter(
            required = true,
            descriptionKey = "KEYWORD",
            description = "KEYWORD",
            validateWith = CLIKeywordValidator.class)
    private String keyword;

    @Parameter(
            names = {"--location", "-l"},
            description = "City, postal code or another parameter to search a location")
    private String location;

    @Parameter(
            names = {"--page", "-p"},
            description = "Every search contains 50 positions, you can page more results changing the number, the pagination starts at 0")
    private int page = 0;

    @Parameter(
            names = "--full-time",
            description = "Add this flag if you want to list the 'full time' jobs"
    )
    private boolean isFullTime = false;

    @Parameter(
            names = "--markdown",
            description = "Add this flag if you want to get the markdown results"
    )
    private boolean isMarkdown = false;

    @Parameter(
            names = "--help",
            help = true,
            validateWith = CLIHelpValidator.class,
            description = "Show the help section")
    private boolean help;

    public String getKeyword() {
        return keyword;
    }

    public String getLocation() {
        return location;
    }

    public int getPage() {
        return page;
    }

    public boolean isFullTime() {
        return isFullTime;
    }

    public boolean isMarkdown() {
        return isMarkdown;
    }

    public boolean isHelp() {
        return help;
    }

    @Override
    public String toString() {
        return "CLIArguments{" +
                "keyword='" + keyword + '\'' +
                ", location='" + location + '\'' +
                ", pages=" + page +
                ", isFullTime=" + isFullTime +
                ", help=" + help +
                ", isMarkdown=" + isMarkdown +
                '}';
    }

    /**
     * This function is equivalent to CLIArguments::new, nevertheless if we want to add addtional parameters
     * in the future, we can limit the way to build objects through this function.
     */
    public static CLIArguments newInstance() {
        return new CLIArguments();
    }
}
