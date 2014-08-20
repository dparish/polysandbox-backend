package dparish;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by david.parish on 7/17/14.
 */
public class HelloWorldConfiguration extends Configuration {

    @NotEmpty
    String template;

    @NotEmpty
    String defaultName = "Stranger";

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    @JsonProperty
    public String getTemplate() {

        return template;
    }

    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database;

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
}
