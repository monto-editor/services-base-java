package monto.service.launching;

import monto.service.types.Source;

public class LaunchConfiguration {
  private final Source mainClassSource;
  // TODO: future fields: arguments, env vars, working dir, language specific configs as JsonElement

  public LaunchConfiguration(Source mainClassSource) {
    this.mainClassSource = mainClassSource;
  }

  public Source getMainClassSource() {
    return mainClassSource;
  }
}
