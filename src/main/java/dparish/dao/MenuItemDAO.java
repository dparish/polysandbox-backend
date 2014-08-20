package dparish.dao;

import java.util.List;

import dparish.model.MenuItem;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 * @author dparish
 */
@RegisterMapper(MenuItemMapper.class)
public interface MenuItemDAO {

    @SqlQuery("select menuitem.id as itemid, menuitem.name as itemname, category.id as categoryid, size.id as sizeid, size.name as sizename, category.name as categoryname, price " +
            "from menuitem " +
            "inner join CATEGORY on menuitem.categoryid = category.id " +
            "inner join size on menuitem.sizeid = size.id " +
            "where menuitem.categoryid = :categoryId")
    List<MenuItem> findByCategoryId(@Bind("categoryId") long categoryId);
}
