import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import dparish.model.Category;
import dparish.model.MenuItem;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


/**
 * @author dparish
 * This is really unrelated to the project but used to just show how to use guava features....
 *
 */
public class GuavaExamples {


    @Test
    public void testOptional() {
        Optional<String> test = getString(null);
        assertFalse(test.isPresent());

        test = getString("foobar");
        if (test.isPresent()) {
            assertEquals(test.get(), "foobar");
        } else {
            fail("this should not happen");
        }
    }

    @Test
    public void testMaps() {
        List<Category> categoryList = Lists.newArrayList(
                getCategory(0, "zero"),
                getCategory(1, "one"));
        Map<Long, Category> categoryMap = Maps.uniqueIndex(categoryList, new Function<Category, Long>() {
            @Nullable
            @Override
            public Long apply(@Nullable Category category) {
                return category.getId();
            }
        });
        assertTrue(categoryMap.containsKey(0l));
        assertEquals(categoryMap.get(0l).getName(), "zero");
    }

    @Test
    public void testLists() {
        List<Category> categoryList = Lists.newArrayList(
                getCategory(0, "zero"),
                getCategory(1, "one"));
        List<MenuItem> newList = Lists.transform(categoryList, new Function<Category, MenuItem>() {
            @Nullable
            @Override
            public MenuItem apply(@Nullable Category category) {
                return new MenuItem().setId(category.getId()).setName(category.getName());
            }
        });
        assertEquals(newList.size(), 2);
        Assert.assertEquals(newList.get(1).getId(), 1l);
    }

    @Test
    public void testPredicates() {
        Predicate<Integer> isEven = new Predicate<Integer>() {
            @Override public boolean apply(Integer number) {
                return (number % 2) == 0;
            }
        };
        List<Integer> numbers = Lists.newArrayList(0,1,2,3,4,5,6,7,8,9);

        Iterable<Integer> evenNumbers = Iterables.filter(numbers, isEven);

        for (Integer i : evenNumbers) {
            assertTrue("the number:" + i + " is not even", i % 2 == 0);
        }
    }
    private Optional<String> getString(String foo) {
        return Optional.fromNullable(foo);
    }

    private Category getCategory(long id, String name) {
        return new Category().setId(id).setName(name);
    }

}
