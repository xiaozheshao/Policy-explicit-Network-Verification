package org.batfish.representation.cisco_nxos;

import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Configuration for an NTP server. */
public final class NtpServer implements Serializable {

  public NtpServer(String host) {
    _host = host;
  }

  public @Nonnull String getHost() {
    return _host;
  }

  public String getUseVrf() {
    return _useVrf;
  }

  public void setUseVrf(String useVrf) {
    _useVrf = useVrf;
  }

  private final @Nonnull String _host;
  private @Nullable String _useVrf;
}
