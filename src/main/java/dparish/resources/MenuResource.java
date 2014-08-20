package dparish.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

import com.codahale.metrics.annotation.Timed;
import dparish.dao.MenuItemDAO;
import dparish.model.Category;
import dparish.model.MenuItem;
import dparish.model.Size;

/**
 * Created by david.parish on 7/31/14.
 */
@Path("/menu")
@Produces(MediaType.APPLICATION_JSON)
public class MenuResource {

    MenuItemDAO menuItemDAO;

    public MenuResource(MenuItemDAO menuItemDAO) {
        this.menuItemDAO = menuItemDAO;
    }

    @GET
    @Timed
    @Path("/item/{id}")
    public MenuItem getItem(@PathParam("id") String idParam) {
        final Long id = Long.parseLong(idParam);
//        if (idParam.isPresent()) {
//            id = Long.parseLong(idParam.get());
//        } else {
//            id = -1l;
//        }
        return new MenuItem().setId(id).setName("hot dog").setCategory(getCategory()).setSize(getSize());

    }

    @GET
    @Timed
    @Path("/item/list/{categoryId}")
    public List<MenuItem> getItems(@PathParam("categoryId") String idParam) {
        final Long id = Long.parseLong(idParam);
        return menuItemDAO.findByCategoryId(id);
    }


    private Size getSize() {
        return new Size().setId(1l).setName("Original");
    }

    private Category getCategory() {
        return new Category().setId(1l).setName("Lunch");
    }
}
