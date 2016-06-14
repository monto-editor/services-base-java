package monto.service.dependency;

import monto.service.types.Source;

import java.util.Set;

public class FileDependency {

  private Source fileName;
  private Set<Source> dependencies;

  public FileDependency(Source fileName, Set<Source> dependencies) {
    this.fileName = fileName;
    this.dependencies = dependencies;
  }

  public Source getFileName() {
    return fileName;
  }

  public Set<Source> getDependencies() {
    return dependencies;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    FileDependency that = (FileDependency) o;

    if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
    return dependencies != null
        ? dependencies.equals(that.dependencies)
        : that.dependencies == null;
  }

  @Override
  public int hashCode() {
    int result = fileName != null ? fileName.hashCode() : 0;
    result = 31 * result + (dependencies != null ? dependencies.hashCode() : 0);
    return result;
  }
}
