package com.tinygrip.android.data.api;

/**
 * Api configuration
 *
 * @author Javier Tarazaga
 */
public class ApiConfig {

  /** Connect timeout */
  public static final int CONNECT_TIMEOUT = 120;

  /** Socket timeout */
  public static final int READ_TIMEOUT = 120;

  public static final String HOST_PROD = "http://tinygrip-001-site1.btempurl.com";
  public static final String HOST_MOCK = "http://tinygrip.mockable.io";

  public static final String BASE = "/api";
}
