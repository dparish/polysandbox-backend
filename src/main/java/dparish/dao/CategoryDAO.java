package dparish.dao;

import dparish.model.Category;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * @author dparish
 */
@RegisterMapper(CategoryMapper.class)
public interface CategoryDAO {

    @SqlQuery("select * from category")
    public List<Category> getAll();

    @SqlUpdate("insert into category(name) values (:name)")
    public void insertCategory(@Bind("name") String name);

    @SqlUpdate("delete from category where id = :id")
    public void deleteCategory(@Bind("id") Long id);
}
