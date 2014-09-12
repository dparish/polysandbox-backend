package dparish.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Strings;
import com.sun.jersey.api.Responses;
import dparish.dao.CategoryDAO;
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
    CategoryDAO categoryDAO;

    public MenuResource(MenuItemDAO menuItemDAO, CategoryDAO categoryDAO) {

        this.menuItemDAO = menuItemDAO;
        this.categoryDAO = categoryDAO;
    }

    @GET
    @Timed
    @Path("/item/{id}")
    public MenuItem getItem(@PathParam("id") String idParam) {
        final Long id = Long.parseLong(idParam);
        return new MenuItem().setId(id).setName("hot dog").setCategory(getCategory()).setSize(getSize());

    }

    @GET
    @Timed
    @Path("/item/list/{categoryId}")
    public List<MenuItem> getItems(@PathParam("categoryId") String idParam) {
        final Long id = Long.parseLong(idParam);
        return menuItemDAO.findByCategoryId(id);
    }

    @GET
    @Timed
    @Path("/category/list")
    public List<Category> getCategories() {
        return categoryDAO.getAll();
    }

    @POST
    @Timed
    @Path("/category")
    public Response insertCategory(@FormParam("name")String name) {
        if (Strings.isNullOrEmpty(name)) {
            Responses.noContent().build();
        }
        categoryDAO.insertCategory(name);
        return Response.ok().build();
    }

    @DELETE
    @Timed
    @Path("/category/{categoryId}")
    public Response deleteCategory(@PathParam("categoryId")String categoryId) {
        categoryDAO.deleteCategory(Long.parseLong(categoryId));
        return Response.ok().build();
    }


    private Size getSize() {
        return new Size().setId(1l).setName("Original");
    }

    private Category getCategory() {
        return new Category().setId(1l).setName("Lunch");
    }
}
