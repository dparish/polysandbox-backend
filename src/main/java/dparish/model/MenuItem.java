package dparish.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author dparish
 */
public class MenuItem extends Model<MenuItem> {

    private Category category;

    private Size size;

    private double price;

    @JsonProperty
    public Category getCategory() {
        return category;
    }

    public MenuItem setCategory(Category category) {
        this.category = category;
        return this;
    }

    @JsonProperty
    public Size getSize() {
        return size;
    }

    public MenuItem setSize(Size size) {
        this.size = size;
        return this;
    }

    @JsonProperty
    public double getPrice() {
        return price;
    }

    public MenuItem setPrice(double price) {
        this.price = price;
        return this;
    }
}
