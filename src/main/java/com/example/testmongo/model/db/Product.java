package com.example.testmongo.model.db;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Setter
@Getter
@ToString
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "product")
public class Product {

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("productId")
    private String productId;

    @JsonProperty("productName")
    private String productName;

    @JsonProperty("desc")
    private String desc;

    @JsonProperty("qty")
    private int qty;

    @JsonProperty("data")
    private Data data;

    @JsonProperty("specs")
    private Map<String, String> specs;
}
