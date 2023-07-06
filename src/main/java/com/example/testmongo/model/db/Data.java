package com.example.testmongo.model.db;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Setter
@Getter
@ToString
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {

    @JsonProperty("createdBy")
    private String createdBy;

    @JsonProperty("creationDate")
    private String creationDate;

    @JsonProperty("updateDate")
    private String updateDate;
}
