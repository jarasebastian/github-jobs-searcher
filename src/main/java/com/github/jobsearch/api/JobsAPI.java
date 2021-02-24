package com.github.jobsearch.api;

import com.github.jobsearch.JobPosition;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

import java.util.List;
import java.util.Map;

/**
 * This interface will be used by Feign to make the requests to the github API.
 */
@Headers("Accept: application/json")
public interface JobsAPI {
    @RequestLine("GET /positions.json")
    List<JobPosition> jobs(@QueryMap Map<String, Object> queryMap);

    @RequestLine("GET /positions/{id}.json")
    JobPosition job(@Param("id") String id);
}
