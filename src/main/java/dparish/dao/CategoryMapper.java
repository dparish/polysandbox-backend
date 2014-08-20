package dparish.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import dparish.model.Category;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author dparish
 */
public class CategoryMapper implements ResultSetMapper<Category> {
    @Override
    public Category map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Category().setId(resultSet.getLong("id")).setName(resultSet.getString("name"));
    }
}
