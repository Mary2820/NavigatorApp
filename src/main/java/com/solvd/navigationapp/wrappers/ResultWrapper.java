package com.solvd.navigationapp.wrappers;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.solvd.navigationapp.models.Result;
import jakarta.xml.bind.annotation.*;

import java.util.List;

@JsonRootName("path")
@XmlRootElement(name = "path")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResultWrapper {

    @XmlElementWrapper(name = "steps")
    @XmlElement(name = "step")
    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
