Jobs Search Reporter

A little CLI to search jobs with Github

---

## Description

Our CLI will use [la API de Github](https://jobs.github.com/) to search jobs
For our CLI can work we should send some search options/parameters.

The way to invocate our CLI will be:


```
jobs-search [OPTIONS] <SKILL>
```

Donde `[OPTIONS]` son:
 
```
--location <LOCATION>   # <LOCATION>: A world place, also available as: -l
--page <PAGE>         	# <PAGE>: The results are shown every 50 results, so 50 results mean a page starting from 0. Default: 0. Also available as: -p
--full-time             # If we want to see only full time jobs. default: false
--markdown              # Show the Markdown results
```

and `<SKILL>` is the skill type we want to find jobs from.

### Example

For finding `Java` jobs in Tokyo, we should use the following command:

`jobs-search --location tokyo java` 