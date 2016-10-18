package monto.service.command;

import monto.service.types.Command;

public final class Commands {
  private Commands() {}

  public static final Command CODE_COMPLETION_REQUEST = new Command("codeCompletionRequest");

  public static final Command RUN_LAUNCH_CONFIGURATION = new Command("runLaunchConfiguration");
  public static final Command DEBUG_LAUNCH_CONFIGURATION = new Command("debugLaunchConfiguration");
  public static final Command TERMINATE_PROCESS = new Command("terminateProcess");
  public static final Command ADD_BREAKPOINT = new Command("addBreakpoint");
  public static final Command REMOVE_BREAKPOINT = new Command("removeBreakpoint");
  public static final Command RESUME_DEBUGGING = new Command("resumeDebugging");
  public static final Command DEBUG_STEP = new Command("debugStep");
}
