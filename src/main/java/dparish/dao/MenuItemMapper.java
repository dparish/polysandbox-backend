package dparish.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import dparish.model.Category;
import dparish.model.MenuItem;
import dparish.model.Size;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author dparish
 */
public class MenuItemMapper implements ResultSetMapper<MenuItem> {

    @Override
    public MenuItem map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        Size size = new Size()
                .setId(resultSet.getLong("sizeid"))
                .setName(resultSet.getString("sizename"));
        Category category = new Category()
                .setId(resultSet.getLong("categoryid"))
                .setName(resultSet.getString("categoryname"));
        return new MenuItem().setId(resultSet.getLong("itemid"))
                .setSize(size)
                .setCategory(category)
                .setName(resultSet.getString("itemname"))
                .setPrice(resultSet.getDouble("price"));
    }
}
