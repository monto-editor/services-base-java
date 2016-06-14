package monto.service.outline;

import monto.service.region.IRegion;
import monto.service.region.Region;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Outline extends Region {

  private String label;
  private URL icon;
  private List<Outline> children;

  public Outline(String label, IRegion link, URL icon, List<Outline> children) {
    super(link.getStartOffset(), link.getLength());
    this.label = label;
    this.icon = icon;
    this.children = children;
  }

  public Outline(String label, IRegion link, URL icon, Outline... children) {
    this(label, link, icon, new ArrayList<>(Arrays.asList(children)));
  }

  public void addChild(Outline outline) {
    children.add(outline);
  }

  public List<Outline> getChildren() {
    return children;
  }

  public Optional<URL> getIcon() {
    return Optional.ofNullable(icon);
  }

  public boolean isLeaf() {
    return getChildren().size() == 0;
  }

  public String getLabel() {
    return label;
  }

  public IRegion getLink() {
    return this;
  }

  @Override
  public String toString() {
    return label;
  }
}
