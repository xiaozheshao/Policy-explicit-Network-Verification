package org.batfish.vendor.check_point_gateway.grammar;

import static org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayLexer.WORD;
import static org.batfish.vendor.check_point_gateway.representation.CheckPointGatewayConfiguration.getBondInterfaceName;

import com.google.common.collect.Range;
import com.google.common.primitives.Ints;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.batfish.common.Warnings;
import org.batfish.common.Warnings.ParseWarning;
import org.batfish.datamodel.ConcreteInterfaceAddress;
import org.batfish.datamodel.IntegerSpace;
import org.batfish.datamodel.Ip;
import org.batfish.datamodel.Prefix;
import org.batfish.grammar.BatfishCombinedParser;
import org.batfish.grammar.SilentSyntaxListener;
import org.batfish.grammar.UnrecognizedLineToken;
import org.batfish.grammar.silent_syntax.SilentSyntaxCollection;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.A_bonding_groupContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Abg_interfaceContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Bonding_group_member_interface_nameContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Bonding_group_modeContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Bonding_group_numberContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Check_point_gateway_configurationContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Double_quoted_stringContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.HostnameContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Interface_nameContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Ip_addressContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Ip_mask_lengthContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Ip_prefixContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Lacp_rateContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Link_speedContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.MtuContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.On_or_offContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Quoted_textContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.S_bonding_groupContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.S_hostnameContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.S_interfaceContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.S_static_routeContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Sbg_lacp_rateContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Sbg_modeContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Sbg_xmit_hash_policyContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Si_auto_negotiationContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Si_commentsContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Si_ipv4_addressContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Si_link_speedContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Si_mtuContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Si_stateContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Siia_maskContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Single_quoted_stringContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Ssr_commentContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Ssr_nexthopContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.SsrnContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.SsrngContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Ssrng_priorityContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Static_route_commentContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Static_route_prefixContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Static_route_priorityContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Uint16Context;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Uint32Context;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Uint8Context;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.WordContext;
import org.batfish.vendor.check_point_gateway.grammar.CheckPointGatewayParser.Xmit_hash_policyContext;
import org.batfish.vendor.check_point_gateway.representation.BondingGroup;
import org.batfish.vendor.check_point_gateway.representation.BondingGroup.LacpRate;
import org.batfish.vendor.check_point_gateway.representation.BondingGroup.Mode;
import org.batfish.vendor.check_point_gateway.representation.BondingGroup.XmitHashPolicy;
import org.batfish.vendor.check_point_gateway.representation.CheckPointGatewayConfiguration;
import org.batfish.vendor.check_point_gateway.representation.Interface;
import org.batfish.vendor.check_point_gateway.representation.Interface.LinkSpeed;
import org.batfish.vendor.check_point_gateway.representation.Nexthop;
import org.batfish.vendor.check_point_gateway.representation.NexthopAddress;
import org.batfish.vendor.check_point_gateway.representation.NexthopBlackhole;
import org.batfish.vendor.check_point_gateway.representation.NexthopLogical;
import org.batfish.vendor.check_point_gateway.representation.NexthopReject;
import org.batfish.vendor.check_point_gateway.representation.NexthopTarget;
import org.batfish.vendor.check_point_gateway.representation.StaticRoute;

@ParametersAreNonnullByDefault
public class CheckPointGatewayConfigurationBuilder extends CheckPointGatewayParserBaseListener
    implements SilentSyntaxListener {

  public CheckPointGatewayConfigurationBuilder(
      CheckPointGatewayCombinedParser parser,
      String text,
      Warnings warnings,
      CheckPointGatewayConfiguration configuration,
      SilentSyntaxCollection silentSyntax) {
    _parser = parser;
    _text = text;
    _w = warnings;
    _configuration = configuration;
    _silentSyntax = silentSyntax;
  }

  @Override
  public void visitErrorNode(ErrorNode errorNode) {
    Token token = errorNode.getSymbol();
    int line = token.getLine();
    String lineText = errorNode.getText().replace("\n", "").replace("\r", "").trim();
    _configuration.setUnrecognized(true);

    if (token instanceof UnrecognizedLineToken) {
      UnrecognizedLineToken unrecToken = (UnrecognizedLineToken) token;
      _w.getParseWarnings()
          .add(
              new ParseWarning(
                  line, lineText, unrecToken.getParserContext(), "This syntax is unrecognized"));
    } else {
      _w.redFlag(
          String.format(
              "Unrecognized Line: %d: %s SUBSEQUENT LINES MAY NOT BE PROCESSED CORRECTLY",
              line, lineText));
    }
  }

  @Nonnull
  public CheckPointGatewayConfiguration getConfiguration() {
    return _configuration;
  }

  @Nonnull
  @Override
  public String getInputText() {
    return _text;
  }

  @Nonnull
  @Override
  public BatfishCombinedParser<?, ?> getParser() {
    return _parser;
  }

  @Nonnull
  @Override
  public SilentSyntaxCollection getSilentSyntax() {
    return _silentSyntax;
  }

  @Nonnull
  @Override
  public Warnings getWarnings() {
    return _w;
  }

  @Override
  public void enterCheck_point_gateway_configuration(
      Check_point_gateway_configurationContext ctx) {}

  @Override
  public void enterA_bonding_group(A_bonding_groupContext ctx) {
    Optional<Integer> numOpt = toInteger(ctx, ctx.bonding_group_number());
    _currentBondingGroupLineIsValid = numOpt.isPresent();
    if (!_currentBondingGroupLineIsValid) {
      _currentBondingGroup = new BondingGroup(0); // dummy
      return;
    }
    _currentBondingGroup =
        _configuration
            .getBondingGroups()
            .getOrDefault(numOpt.get(), new BondingGroup(numOpt.get()));
  }

  @Override
  public void exitA_bonding_group(A_bonding_groupContext ctx) {
    // Create the bonding group and its interface if needed
    if (_currentBondingGroupLineIsValid) {
      int num = _currentBondingGroup.getNumber();
      _configuration.getInterfaces().computeIfAbsent(getBondInterfaceName(num), Interface::new);
      _configuration.getBondingGroups().putIfAbsent(num, _currentBondingGroup);
    }
    _currentBondingGroup = null;
  }

  @Override
  public void exitAbg_interface(Abg_interfaceContext ctx) {
    Optional<String> ifaceNameOpt = toString(ctx, ctx.bonding_group_member_interface_name());
    if (!ifaceNameOpt.isPresent() || !isValidBondGroupMember(ctx, ifaceNameOpt.get())) {
      _currentBondingGroupLineIsValid = false;
      return;
    }

    _currentBondingGroup.getInterfaces().add(ifaceNameOpt.get());
  }

  /**
   * Indicates if the specified interface can be added as a bonding group member. Adds a warning if
   * not.
   */
  private boolean isValidBondGroupMember(ParserRuleContext ctx, String ifaceName) {
    if (_configuration.getBondingGroups().values().stream()
        .anyMatch(bg -> bg.getInterfaces().contains(ifaceName))) {
      warn(ctx, "Interface can only be added to one bonding group.");
      return false;
    }

    // Trust interface references are valid if interfaces haven't been explicitly configured yet
    // i.e. looks like we're parsing `show configuration` data which has interface refs before defs
    if (!_firstInterfaceHasBeenConfigured) {
      return true;
    }

    Interface candidate = _configuration.getInterfaces().get(ifaceName);
    if (candidate == null) {
      warn(ctx, "Cannot add non-existent interface to a bonding group.");
      return false;
    }

    if (candidate.getAddress() != null) {
      warn(ctx, "Cannot add an interface with a configured address to a bonding group.");
      return false;
    }

    return true;
  }

  @Override
  public void enterS_bonding_group(S_bonding_groupContext ctx) {
    _currentBondingGroup = new BondingGroup(0); // dummy
    Optional<Integer> numOpt = toInteger(ctx, ctx.bonding_group_number());
    if (!numOpt.isPresent()) {
      return;
    }
    int num = numOpt.get();
    if (!_configuration.getBondingGroups().containsKey(num)) {
      warn(ctx, "Cannot configure non-existent bonding group, add it first.");
      return;
    }
    _currentBondingGroup = _configuration.getBondingGroups().get(num);
  }

  @Override
  public void exitS_bonding_group(S_bonding_groupContext ctx) {
    _currentBondingGroup = null;
  }

  @Override
  public void exitSbg_lacp_rate(Sbg_lacp_rateContext ctx) {
    _currentBondingGroup.setLacpRate(toLacpRate(ctx.lacp_rate()));
  }

  @Override
  public void exitSbg_mode(Sbg_modeContext ctx) {
    _currentBondingGroup.setMode(toMode(ctx.bonding_group_mode()));
  }

  @Override
  public void exitSbg_xmit_hash_policy(Sbg_xmit_hash_policyContext ctx) {
    _currentBondingGroup.setXmitHashPolicy(toXmitHashPolicy(ctx.xmit_hash_policy()));
  }

  @Override
  public void exitS_hostname(S_hostnameContext ctx) {
    toString(ctx, ctx.hostname()).ifPresent(_configuration::setHostname);
  }

  @Override
  public void enterS_interface(S_interfaceContext ctx) {
    _currentInterface =
        toString(ctx, ctx.interface_name())
            .map(n -> _configuration.getInterfaces().computeIfAbsent(n, Interface::new))
            .orElse(new Interface(ctx.interface_name().getText()));
    _currentInterfaceInBondingGroup = isInterfaceInBondingGroup(_currentInterface);
    _firstInterfaceHasBeenConfigured = true;
  }

  /** Indicates if the specified interface is already a member of a bonding group. */
  private boolean isInterfaceInBondingGroup(Interface iface) {
    return _configuration.getBondingGroups().values().stream()
        .anyMatch(bg -> bg.getInterfaces().contains(iface.getName()));
  }

  @Override
  public void exitS_interface(S_interfaceContext ctx) {
    _currentInterface = null;
    _currentInterfaceInBondingGroup = null;
  }

  @Override
  public void exitSi_auto_negotiation(Si_auto_negotiationContext ctx) {
    if (_currentInterfaceInBondingGroup) {
      warn(ctx, "Interface is a member of a bonding group and cannot be configured directly.");
      return;
    }
    _currentInterface.setAutoNegotiate(toBoolean(ctx.on_or_off()));
  }

  @Override
  public void exitSi_comments(Si_commentsContext ctx) {
    _currentInterface.setComments(toString(ctx.word()));
  }

  @Override
  public void exitSi_ipv4_address(Si_ipv4_addressContext ctx) {
    if (_currentInterfaceInBondingGroup) {
      warn(ctx, "Interface is a member of a bonding group and cannot be configured directly.");
      return;
    }
    Ip ip = toIp(ctx.ip_address());
    Optional<Integer> subnetBits = toSubnetBits(ctx, ctx.siia_mask());
    subnetBits.ifPresent(
        sb -> _currentInterface.setAddress(ConcreteInterfaceAddress.create(ip, sb)));
  }

  @Override
  public void exitSi_link_speed(Si_link_speedContext ctx) {
    if (_currentInterfaceInBondingGroup) {
      warn(ctx, "Interface is a member of a bonding group and cannot be configured directly.");
      return;
    }
    _currentInterface.setLinkSpeed(toLinkSpeed(ctx.link_speed()));
  }

  @Override
  public void exitSi_mtu(Si_mtuContext ctx) {
    if (_currentInterfaceInBondingGroup) {
      warn(ctx, "Interface is a member of a bonding group and cannot be configured directly.");
      return;
    }
    toInteger(ctx, ctx.mtu()).ifPresent(m -> _currentInterface.setMtu(m));
  }

  @Override
  public void exitSi_state(Si_stateContext ctx) {
    boolean state = toBoolean(ctx.on_or_off());
    // Setting state to `off` is not permitted for an interface in a bonding group
    if (_currentInterfaceInBondingGroup && !state) {
      warn(ctx, "Interface is a member of a bonding group and cannot be configured directly.");
      return;
    }
    _currentInterface.setState(state);
  }

  @Override
  public void enterS_static_route(S_static_routeContext ctx) {
    Optional<Prefix> prefix = toPrefix(ctx, ctx.static_route_prefix());
    if (!prefix.isPresent()) {
      // Dummy
      _currentStaticRoute = new StaticRoute(Prefix.ZERO);
      return;
    }

    _currentStaticRoute =
        _configuration.getStaticRoutes().computeIfAbsent(prefix.get(), StaticRoute::new);
  }

  @Override
  public void exitS_static_route(S_static_routeContext ctx) {
    _currentStaticRoute = null;
  }

  @Override
  public void exitSsr_comment(Ssr_commentContext ctx) {
    toString(ctx, ctx.static_route_comment()).ifPresent(c -> _currentStaticRoute.setComment(c));
  }

  @Override
  public void enterSsr_nexthop(Ssr_nexthopContext ctx) {
    Optional<NexthopTarget> nexthopTarget = toNexthopTarget(ctx.ssrn());
    if (!nexthopTarget.isPresent()) {
      // Dummy
      _currentStaticRouteNextHop = new Nexthop(NexthopBlackhole.INSTANCE);
      return;
    }

    _currentStaticRouteNextHop =
        _currentStaticRoute.getNexthops().computeIfAbsent(nexthopTarget.get(), Nexthop::new);
  }

  @Override
  public void exitSsrng_priority(Ssrng_priorityContext ctx) {
    toInteger(ctx, ctx.static_route_priority())
        .ifPresent(p -> _currentStaticRouteNextHop.setPriority(p));
  }

  @Override
  public void exitSsr_nexthop(Ssr_nexthopContext ctx) {
    _currentStaticRouteNextHop = null;
  }

  private @Nonnull Optional<NexthopTarget> toNexthopTarget(SsrnContext ctx) {
    if (ctx.ssrn_blackhole() != null) {
      return Optional.of(NexthopBlackhole.INSTANCE);
    } else if (ctx.ssrn_reject() != null) {
      return Optional.of(NexthopReject.INSTANCE);
    }
    assert ctx.ssrn_gateway() != null;
    return toNexthopTarget(ctx.ssrn_gateway().ssrng());
  }

  private @Nonnull Optional<NexthopTarget> toNexthopTarget(SsrngContext ctx) {
    if (ctx.ssrng_address() != null) {
      return Optional.of(new NexthopAddress(toIp(ctx.ssrng_address().ip_address())));
    }
    assert ctx.ssrng_logical() != null;
    String iface = toString(ctx.ssrng_logical().iface);
    if (!_configuration.getInterfaces().containsKey(iface)) {
      warn(ctx, "Cannot set nexthop gateway to non-existent interface");
      return Optional.empty();
    }
    return Optional.of(new NexthopLogical(iface));
  }

  private @Nonnull Optional<Prefix> toPrefix(
      ParserRuleContext messageCtx, Static_route_prefixContext ctx) {
    if (ctx.ip_prefix() != null) {
      Prefix prefix = toPrefix(ctx.ip_prefix());
      if (prefix.equals(Prefix.ZERO)) {
        warn(
            messageCtx,
            String.format(
                "Static-route prefix %s is not valid, use the 'default' keyword instead.", prefix));
        return Optional.empty();
      }
      return Optional.of(prefix);
    }
    assert ctx.DEFAULT() != null;
    return Optional.of(Prefix.ZERO);
  }

  private @Nonnull Prefix toPrefix(Ip_prefixContext ctx) {
    return Prefix.parse(ctx.getText());
  }

  private LinkSpeed toLinkSpeed(Link_speedContext ctx) {
    if (ctx.HUNDRED_M_FULL() != null) {
      return LinkSpeed.HUNDRED_M_FULL;
    } else if (ctx.HUNDRED_M_HALF() != null) {
      return LinkSpeed.HUNDRED_M_HALF;
    } else if (ctx.TEN_M_FULL() != null) {
      return LinkSpeed.TEN_M_FULL;
    } else if (ctx.TEN_M_HALF() != null) {
      return LinkSpeed.TEN_M_HALF;
    }
    assert ctx.THOUSAND_M_FULL() != null;
    return LinkSpeed.THOUSAND_M_FULL;
  }

  private BondingGroup.LacpRate toLacpRate(Lacp_rateContext ctx) {
    if (ctx.FAST() != null) {
      return LacpRate.FAST;
    }
    assert ctx.SLOW() != null;
    return LacpRate.SLOW;
  }

  private BondingGroup.Mode toMode(Bonding_group_modeContext ctx) {
    if (ctx.ACTIVE_BACKUP() != null) {
      return Mode.ACTIVE_BACKUP;
    } else if (ctx.EIGHT_ZERO_TWO_THREE_AD() != null) {
      return Mode.EIGHT_ZERO_TWO_THREE_AD;
    } else if (ctx.ROUND_ROBIN() != null) {
      return Mode.ROUND_ROBIN;
    }
    assert ctx.XOR() != null;
    return Mode.XOR;
  }

  private XmitHashPolicy toXmitHashPolicy(Xmit_hash_policyContext ctx) {
    if (ctx.LAYER2() != null) {
      return XmitHashPolicy.LAYER2;
    }
    assert ctx.LAYER3_4() != null;
    return XmitHashPolicy.LAYER3_4;
  }

  private @Nonnull Optional<Integer> toInteger(
      ParserRuleContext messageCtx, Bonding_group_numberContext ctx) {
    return toIntegerInSpace(
        messageCtx, ctx.uint16(), BONDING_GROUP_NUMBER_SPACE, "bonding group number");
  }

  private @Nonnull Optional<Integer> toInteger(ParserRuleContext messageCtx, MtuContext ctx) {
    return toIntegerInSpace(messageCtx, ctx.uint16(), MTU_SPACE, "mtu");
  }

  private @Nonnull Optional<Integer> toInteger(
      ParserRuleContext messageCtx, Static_route_priorityContext ctx) {
    return toIntegerInSpace(
        messageCtx,
        ctx.uint8(),
        STATIC_ROUTE_NEXTHOP_PRIORITY_SPACE,
        "static-route nexthop priority");
  }

  private @Nonnull Optional<Integer> toSubnetBits(
      ParserRuleContext messageCtx, Siia_maskContext ctx) {
    if (ctx.siia_mask_length() != null) {
      return toInteger(ctx, ctx.siia_mask_length().ip_mask_length());
    }
    assert ctx.siia_subnet_mask() != null;
    Ip mask = toIp(ctx.siia_subnet_mask().ip_address());
    if (!mask.isValidNetmask1sLeading()) {
      warn(messageCtx, String.format("Subnet-mask %s is not valid.", mask));
      return Optional.empty();
    }
    return Optional.of(mask.numSubnetBits());
  }

  private @Nonnull Optional<Integer> toInteger(
      ParserRuleContext messageCtx, Ip_mask_lengthContext ctx) {
    return toIntegerInSpace(messageCtx, ctx.uint8(), MASK_LENGTH_SPACE, "mask-length");
  }

  private @Nonnull Optional<Integer> toIntegerInSpace(
      ParserRuleContext messageCtx, Uint8Context ctx, IntegerSpace space, String name) {
    return toIntegerInSpace(messageCtx, ctx.getText(), space, name);
  }

  private @Nonnull Optional<Integer> toIntegerInSpace(
      ParserRuleContext messageCtx, Uint16Context ctx, IntegerSpace space, String name) {
    return toIntegerInSpace(messageCtx, ctx.getText(), space, name);
  }

  private @Nonnull Optional<Integer> toIntegerInSpace(
      ParserRuleContext messageCtx, Uint32Context ctx, IntegerSpace space, String name) {
    return toIntegerInSpace(messageCtx, ctx.getText(), space, name);
  }

  /**
   * Convert a {@link String} to an {@link Integer} if it represents a number that is contained in
   * the provided {@code space}, or else {@link Optional#empty}.
   */
  private @Nonnull Optional<Integer> toIntegerInSpace(
      ParserRuleContext messageCtx, String str, IntegerSpace space, String name) {
    Integer num = Ints.tryParse(str);
    if (num == null || !space.contains(num)) {
      warn(messageCtx, String.format("Expected %s in range %s, but got '%d'", name, space, num));
      return Optional.empty();
    }
    return Optional.of(num);
  }

  private static @Nonnull Ip toIp(Ip_addressContext ctx) {
    return Ip.parse(ctx.getText());
  }

  private boolean toBoolean(On_or_offContext ctx) {
    if (ctx.ON() != null) {
      return true;
    }
    assert ctx.OFF() != null;
    return false;
  }

  /** Handle hostname special string replacements, like {@code %m} for chassis identifier. */
  private @Nonnull String preprocessHostname(String original) {
    // Assume this is the first chassis and (?)blade
    return original.replace("%m", "ch01-01");
  }

  private @Nonnull Optional<String> toString(ParserRuleContext messageCtx, HostnameContext ctx) {
    return toString(
        messageCtx,
        ctx.word(),
        "device hostname",
        this::preprocessHostname,
        DEVICE_HOSTNAME_PATTERN);
  }

  private @Nonnull Optional<String> toString(
      ParserRuleContext messageCtx, Bonding_group_member_interface_nameContext ctx) {
    return toString(
        messageCtx,
        ctx.interface_name().word(),
        "bonding group member interface name (must be eth interface)",
        BONDING_GROUP_MEMBER_INTERFACE_NAME_PATTERN);
  }

  private @Nonnull Optional<String> toString(
      ParserRuleContext messageCtx, Interface_nameContext ctx) {
    return toString(messageCtx, ctx.word(), "interface name", INTERFACE_NAME_PATTERN);
  }

  private @Nonnull Optional<String> toString(
      ParserRuleContext messageCtx, Static_route_commentContext ctx) {
    return toString(messageCtx, ctx.word(), "static-route comment", STATIC_ROUTE_COMMENT_PATTERN);
  }

  private @Nonnull Optional<String> toString(
      ParserRuleContext messageCtx, WordContext ctx, String type, Pattern pattern) {
    return toString(messageCtx, ctx, type, s -> pattern.matcher(s).matches());
  }

  private @Nonnull Optional<String> toString(
      ParserRuleContext messageCtx,
      WordContext ctx,
      String type,
      Function<String, String> preprocessFunction,
      Pattern pattern) {
    return toString(messageCtx, ctx, type, preprocessFunction, s -> pattern.matcher(s).matches());
  }

  private @Nonnull Optional<String> toString(
      ParserRuleContext messageCtx, WordContext ctx, String type, Predicate<String> predicate) {
    String text = toString(ctx);
    return toString(messageCtx, text, type, predicate);
  }

  private @Nonnull Optional<String> toString(
      ParserRuleContext messageCtx,
      WordContext ctx,
      String type,
      Function<String, String> preprocessFunction,
      Predicate<String> predicate) {
    String text = preprocessFunction.apply(toString(ctx));
    return toString(messageCtx, text, type, predicate);
  }

  private @Nonnull Optional<String> toString(
      ParserRuleContext messageCtx, String text, String type, Predicate<String> predicate) {
    if (!predicate.test(text)) {
      warn(messageCtx, String.format("Illegal value for %s", type));
      return Optional.empty();
    }
    return Optional.of(text);
  }

  private static @Nonnull String toString(WordContext ctx) {
    return ctx.word_content().children.stream()
        .map(
            child -> {
              if (child instanceof Double_quoted_stringContext) {
                return toString(((Double_quoted_stringContext) child).text);
              } else if (child instanceof Single_quoted_stringContext) {
                return toString(((Single_quoted_stringContext) child).text);
              } else {
                assert child instanceof TerminalNode;
                int type = ((TerminalNode) child).getSymbol().getType();
                assert type == WORD;
                return child.getText();
              }
            })
        .collect(Collectors.joining(""));
  }

  private static @Nonnull String toString(@Nullable Quoted_textContext text) {
    if (text == null) {
      return "";
    }
    // Device appears to just remove backslashes from quoted strings
    return text.getText().replaceAll("\\\\", "");
  }

  private static final Pattern BONDING_GROUP_MEMBER_INTERFACE_NAME_PATTERN =
      Pattern.compile("^eth[0-9-]+$");
  private static final Pattern DEVICE_HOSTNAME_PATTERN = Pattern.compile("^[A-Za-z0-9_-]+$");
  // Only certain prefixes are allowed, so this is more broad than what the device accepts
  private static final Pattern INTERFACE_NAME_PATTERN = Pattern.compile("^[A-Za-z0-9.-]+$");
  private static final Pattern STATIC_ROUTE_COMMENT_PATTERN = Pattern.compile("^[A-Za-z0-9,. ]+$");

  private static final IntegerSpace BONDING_GROUP_NUMBER_SPACE =
      IntegerSpace.of(Range.closed(0, 1024));
  private static final IntegerSpace MASK_LENGTH_SPACE = IntegerSpace.of(Range.closed(1, 32));
  private static final IntegerSpace MTU_SPACE = IntegerSpace.of(Range.closed(68, 16000));
  private static final IntegerSpace STATIC_ROUTE_NEXTHOP_PRIORITY_SPACE =
      IntegerSpace.of(Range.closed(1, 8));

  private BondingGroup _currentBondingGroup;

  /** If the current bonding group configuration line is valid. */
  private boolean _currentBondingGroupLineIsValid;

  private Interface _currentInterface;

  /**
   * Indicates if {@code _currentInterface} is in a bonding group. This determines if certain
   * properties can be (re)configured.
   */
  private Boolean _currentInterfaceInBondingGroup;

  private StaticRoute _currentStaticRoute;

  private Nexthop _currentStaticRouteNextHop;

  /**
   * This indicates if any interfaces have been explicitly configured up to this point in parsing.
   *
   * <p>This is used in heuristics to determine when Batfish should be strict about bonding group
   * member interfaces, i.e. disallow adding undefined interfaces as bonding group members.
   *
   * <p>This is needed because Check Point gateways will print output for {@code show configuration}
   * where bonding group members are referenced before their definition. This is in contrast to
   * "reconfiguration" lines, which cannot reference undefined ifaces/come after their definitions.
   */
  private boolean _firstInterfaceHasBeenConfigured;

  @Nonnull private CheckPointGatewayConfiguration _configuration;

  @Nonnull private CheckPointGatewayCombinedParser _parser;

  @Nonnull private final String _text;

  @Nonnull private final Warnings _w;

  @Nonnull private final SilentSyntaxCollection _silentSyntax;
}
