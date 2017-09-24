package com.tmobile.eit.ce.authz.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "ABFs",
    "subResources"
})
public class Resource {

    @JsonProperty("id")
    private String id;
    @JsonProperty("ABFs")
    private List<String> ABFs = null;
    @JsonProperty("subResources")
    private List<Resource> subResources = null;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("ABFs")
    public List<String> getABFs() {
        return ABFs;
    }

    @JsonProperty("ABFs")
    public void setABFs(List<String> ABFs) {
        this.ABFs = ABFs;
    }

    @JsonProperty("subResources")
    public List<Resource> getSubResources() {
        return subResources;
    }

    @JsonProperty("subResources")
    public void setSubResources(List<Resource> subResources) {
        this.subResources = subResources;
    }

}
