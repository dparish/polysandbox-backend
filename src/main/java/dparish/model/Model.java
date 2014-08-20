package dparish.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author dparish
 */
public abstract class Model <B extends Model<B>>{
    private long id;
    private String name;

    @JsonProperty
    public long getId() {
        return id;
    }

    public B setId(long id) {
        this.id = id;
        return (B)this;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    public B setName(String name) {
        this.name = name;
        return (B)this;
    }
}
