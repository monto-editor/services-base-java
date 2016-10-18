package monto.service.product;

import monto.service.types.Product;

public class Products {
  public static final Product OUTLINE = new Product("outline");
  public static final Product TOKENS = new Product("tokens");
  public static final Product AST = new Product("ast");
  public static final Product COMPLETIONS = new Product("completions");
  public static final Product ERRORS = new Product("errors");
  public static final Product FILE_DEPENDENCIES = new Product("filedependencies");
  public static final Product FILE_GRAPH = new Product("filegraph");
  public static final Product IDENTIFIER = new Product("identifier");
  public static final Product STREAM_OUTPUT = new Product("streamOutput");
  public static final Product PROCESS_TERMINATED = new Product("processTerminated");
  public static final Product LOGICAL_SOURCE_NAME = new Product("logicalSourceName");
  public static final Product HIT_BREAKPOINT = new Product("hitBreakpoint");
  public static final Product THREADS_RESUMED = new Product("threadsResumed");
}
