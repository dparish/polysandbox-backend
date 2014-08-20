package dparish;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

import dparish.dao.MenuItemDAO;
import dparish.resources.MenuResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.skife.jdbi.v2.DBI;

/**
 * Created by david.parish on 7/17/14.
 */
public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {

        bootstrap.addBundle(new MigrationsBundle<HelloWorldConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(HelloWorldConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) throws Exception {
        final HelloWorldResource helloWorldResource =
                new HelloWorldResource(
                        configuration.getTemplate(),
                        configuration.getDefaultName()
                );
        environment.jersey().register(helloWorldResource);


        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("crossOriginRequsts", CrossOriginFilter.class);
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(helloWorldResource);

        // jdbi
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "derby");
        MenuItemDAO menuItemDAO = jdbi.onDemand(MenuItemDAO.class);

        environment.jersey().register(new MenuResource(menuItemDAO));
    }

    @Override
    public String getName() {
        return "hello-world";
    }
}
