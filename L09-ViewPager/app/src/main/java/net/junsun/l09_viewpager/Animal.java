package net.junsun.l09_viewpager;

/**
 * Created by jsun on 4/18/2016.
 */
public class Animal {
    private String name;
    private int imageResrouceId;

    public Animal(String name, int resourceId) {
        this.name = name;
        this.imageResrouceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageResrouceId;
    }
}
