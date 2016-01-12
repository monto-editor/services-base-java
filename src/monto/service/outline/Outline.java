package monto.service.outline;

import monto.service.region.IRegion;
import monto.service.region.Region;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Outline extends Region {

    private String description;
    private Optional<URL> icon;
    private List<Outline> children;

    public Outline(String description, IRegion region, URL icon, List<Outline> childs) {
        super(region.getStartOffset(), region.getLength());
        this.description = description;
        this.icon = Optional.ofNullable(icon);
        this.children = childs;
    }

    public Outline(String description, IRegion region, URL url) {
        this(description, region, url, new ArrayList<>());
    }

    public void addChild(Outline outline) {
        children.add(outline);
    }

    public List<Outline> getChildren() {
        return children;
    }

    public Optional<URL> getIcon() {
        return icon;
    }

    public boolean isLeaf() {
        return getChildren().size() == 0;
    }

    public String getDescription() {
        return description;
    }

    public IRegion getIdentifier() {
        return this;
    }

    @Override
    public String toString() {
        return description;
    }
}
