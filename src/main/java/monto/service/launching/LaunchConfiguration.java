package monto.service.launching;

import monto.service.command.CommandMessage;
import monto.service.gson.GsonMonto;
import monto.service.types.ServiceId;
import monto.service.types.Source;

public class LaunchConfiguration {
  public static final String TAG = "launchConfig";

  private final String mode;
  private final Source mainClassSource;
  // TODO: future fields: arguments, env vars, working dir, language specific configs as JsonElement

  public LaunchConfiguration(String mode, Source mainClassSource) {
    this.mode = mode;
    this.mainClassSource = mainClassSource;
  }

  public Source getMainClassSource() {
    return mainClassSource;
  }

  public String getMode() {
    return mode;
  }

  public static CommandMessage createCommandMessage(
      int session, int id, ServiceId serviceId, String mode, Source mainClassSource) {
    return new CommandMessage(
        session,
        id,
        serviceId,
        LaunchConfiguration.TAG,
        GsonMonto.toJsonTree(new LaunchConfiguration(mode, mainClassSource)));
  }

  public static LaunchConfiguration fromCommandMessage(CommandMessage commandMessage) {
    return GsonMonto.fromJson(commandMessage.getContents(), LaunchConfiguration.class);
  }
}
