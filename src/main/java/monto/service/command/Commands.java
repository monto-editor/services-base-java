package monto.service.command;

import monto.service.types.Command;

public final class Commands {
  private Commands() {}

  public static final Command COMPLETE_CODE = new Command("completeCode");

  public static final Command RUN = new Command("run");
  public static final Command DEBUG = new Command("debug");
  public static final Command TERMINATE = new Command("terminate");
  public static final Command ADD_BREAKPOINT = new Command("addBreakpoint");
  public static final Command REMOVE_BREAKPOINT = new Command("removeBreakpoint");
  public static final Command DEBUG_RESUME = new Command("debugResume");
  public static final Command DEBUG_STEP = new Command("debugStep");
}
