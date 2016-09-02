package org.batfish.nls;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class NlsConstants {

   public static final Set<String> NLS_DATA_PLANE_COMPUTATION_FACTS = initNlsDataPlaneComputationFacts();

   public static final Set<String> NLS_DATA_PLANE_ENTITY_SYMBOLS = initNlsDataPlaneEntitySymbols();

   public static final Set<String> NLS_DATA_PLANE_OUTPUT_DEBUG_SYMBOLS = initNlsDataPlaneOutputDebugSymbols();

   public static final Set<String> NLS_DATA_PLANE_OUTPUT_SYMBOLS = initNlsDataPlaneOutputSymbols();

   public static final Set<String> NLS_TRAFFIC_COMPUTATION_CONTROL_PLANE_FACTS = initNlsTrafficComputationControlPlaneFacts();

   public static final Set<String> NLS_TRAFFIC_COMPUTATION_FLOW_FACTS = initNlsTrafficComputationFlowFacts();

   public static final Set<String> NLS_TRAFFIC_ENTITY_SYMBOLS = initNlsTrafficEntitySymbols();

   public static final Set<String> NLS_TRAFFIC_OUTPUT_DEBUG_SYMBOLS = initNlsTrafficOutputDebugSymbols();

   public static final Set<String> NLS_TRAFFIC_OUTPUT_SYMBOLS = initNlsTrafficOutputSymbols();

   private static Set<String> initNlsDataPlaneComputationFacts() {
      Set<String> factSet = new HashSet<>();
      String[] facts = new String[] { "SamePhysicalSegment", "SetActiveInt",
            "SetAsPathLineDeny", "SetAsPathLineMatchAs",
            "SetAsPathLineMatchAsAtBeginning", "SetAsPathLineMatchAsPair",
            "SetAsPathLineMatchAsPairAtBeginning", "SetAsPathLineMatchEmpty",
            "SetAsPathLinePermit", "SetBgpAdvertisementCommunity",
            "SetBgpAdvertisement_flat", "SetBgpAdvertisementPath",
            "SetBgpAdvertisementPathSize", "SetBgpDefaultLocalPref_flat",
            "SetBgpExportPolicy_flat", "SetBgpGeneratedRoute_flat",
            "SetBgpGeneratedRouteAttributePolicy_flat",
            "SetBgpGeneratedRoutePolicy_flat", "SetBgpImportPolicy_flat",
            "SetBgpMultihopNeighborNetwork_flat",
            "SetBgpNeighborAdvertiseInactive_flat",
            "SetBgpNeighborAllowLocalAsIn_flat",
            "SetBgpNeighborAllowRemoteAsOut_flat",
            "SetBgpNeighborDefaultMetric_flat",
            "SetBgpNeighborGeneratedRoute_flat",
            "SetBgpNeighborGeneratedRouteAttributePolicy_flat",
            "SetBgpNeighborGeneratedRoutePolicy_flat",
            "SetBgpNeighborLocalIp_flat", "SetBgpNeighborNetwork_flat",
            "SetBgpNeighborSendCommunity_flat", "SetBgpOriginationPolicy_flat",
            "SetCommunityListLine", "SetCommunityListLinePermit",
            "SetCrossZoneFilter", "SetDefaultCrossZoneAccept",
            "SetDefaultInboundAccept", "SetExternalBgpRemoteIp",
            "SetFakeInterface", "SetFlowSinkInterface",
            "SetGeneratedRouteDiscard_flat", "SetGeneratedRoute_flat",
            "SetGeneratedRouteMetric_flat",
            "SetGeneratedRouteAttributePolicy_flat",
            "SetGeneratedRoutePolicy_flat", "SetIbgpNeighbors",
            "SetInboundInterfaceFilter", "SetInterfaceFilterIn",
            "SetInterfaceFilterOut", "SetInterfaceRoutingPolicy",
            "SetInterfaceZone", "SetIpAccessListLine_deny",
            "SetIpAccessListLine_dscp", "SetIpAccessListLine_dstIps",
            "SetIpAccessListLine_dstPorts", "SetIpAccessListLine_ecn",
            "SetIpAccessListLine_icmpCodes", "SetIpAccessListLine_icmpTypes",
            "SetIpAccessListLine_notDscp", "SetIpAccessListLine_notDstIps",
            "SetIpAccessListLine_notDstPorts", "SetIpAccessListLine_notEcn",
            "SetIpAccessListLine_notIcmpCodes",
            "SetIpAccessListLine_notIcmpTypes",
            "SetIpAccessListLine_notProtocol", "SetIpAccessListLine_notSrcIps",
            "SetIpAccessListLine_notSrcPorts", "SetIpAccessListLine_permit",
            "SetIpAccessListLine_protocol", "SetIpAccessListLine_srcIps",
            "SetIpAccessListLine_srcOrDstIps",
            "SetIpAccessListLine_srcOrDstPorts", "SetIpAccessListLine_srcPorts",
            "SetIpAccessListLine_state", "SetIpAccessListLine_tcpFlags",
            "SetIpAccessListLine_tcpFlagsCWR",
            "SetIpAccessListLine_tcpFlagsECE",
            "SetIpAccessListLine_tcpFlagsURG",
            "SetIpAccessListLine_tcpFlagsACK",
            "SetIpAccessListLine_tcpFlagsPSH",
            "SetIpAccessListLine_tcpFlagsRST",
            "SetIpAccessListLine_tcpFlagsSYN",
            "SetIpAccessListLine_tcpFlagsFIN", "SetIpInt", "SetIsisArea",
            "SetIsisGeneratedRoute_flat", "SetIsisGeneratedRoutePolicy_flat",
            "SetIsisInterfaceCost", "SetIsisL1ActiveInterface", "SetIsisL1Node",
            "SetIsisL1PassiveInterface", "SetIsisL2ActiveInterface",
            "SetIsisL2Node", "SetIsisL2PassiveInterface",
            "SetIsisOutboundPolicyMap", "SetLinkLoadLimitIn",
            "SetLinkLoadLimitOut", "SetLocalAs_flat", "SetNetwork",
            "SetNodeRole", "SetNodeVendor", "SetOspfGeneratedRoute_flat",
            "SetOspfGeneratedRoutePolicy_flat", "SetOspfInterface",
            "SetOspfInterfaceCost", "SetOspfOutboundPolicyMap",
            "SetOspfRouterId", "SetPolicyMapClauseAddCommunity",
            "SetPolicyMapClauseDeleteCommunity", "SetPolicyMapClauseDeny",
            "SetPolicyMapClauseMatchAcl", "SetPolicyMapClauseMatchAsPath",
            "SetPolicyMapClauseMatchColor",
            "SetPolicyMapClauseMatchCommunityList",
            "SetPolicyMapClauseMatchInterface",
            "SetPolicyMapClauseMatchNeighbor", "SetPolicyMapClauseMatchPolicy",
            "SetPolicyMapClauseMatchPolicyConjunction",
            "SetPolicyMapClauseMatchProtocol",
            "SetPolicyMapClauseMatchRouteFilter", "SetPolicyMapClauseMatchTag",
            "SetPolicyMapClausePermit", "SetPolicyMapClauseSetCommunity",
            "SetPolicyMapClauseSetCommunityNone",
            "SetPolicyMapClauseSetLocalPreference",
            "SetPolicyMapClauseSetMetric", "SetPolicyMapClauseSetNextHopIp",
            "SetPolicyMapClauseSetOriginType", "SetPolicyMapClauseSetProtocol",
            "SetPolicyMapIsisExternalRouteType",
            "SetPolicyMapOspfExternalRouteType", "SetPrecomputedRoute_flat",
            "SetRemoteAs_flat", "SetRouteFilterLine",
            "SetRouteFilterPermitLine", "SetRouteReflectorClient_flat",
            "SetStaticIntRoute_flat", "SetStaticRoute_flat",
            "SetSwitchportAccess", "SetSwitchportTrunkAllows",
            "SetSwitchportTrunkEncapsulation", "SetSwitchportTrunkNative",
            "SetVlanInterface", "SetZoneFromHostFilter", "SetZoneToHostFilter"

      };
      factSet.addAll(Arrays.asList(facts));
      return Collections.unmodifiableSet(factSet);
   }

   private static Set<String> initNlsDataPlaneEntitySymbols() {
      Set<String> factSet = new HashSet<>();
      String[] facts = new String[] { "AdvertisementCommunity",
            "AdvertisementPath", "AdvertisementPathSize",
            "BgpAdvertisement_dstIp", "BgpAdvertisement_dstNode",
            "BgpAdvertisement_localPref", "BgpAdvertisement_med",
            "BgpAdvertisement_network", "BgpAdvertisement_nextHopIp",
            "BgpAdvertisement_originatorIp", "BgpAdvertisement_originType",
            "BgpAdvertisement_srcIp", "BgpAdvertisement_srcNode",
            "BgpAdvertisement_srcProtocol", "BgpAdvertisement_type",
            "Network_index", "Route", "RouteDetails_admin", "RouteDetails_cost",
            "RouteDetails_nextHop", "RouteDetails_nextHopInt",
            "RouteDetails_nextHopIp", "RouteDetails_tag", "Route_network",
            "Route_node", "Route_protocol"

      };
      factSet.addAll(Arrays.asList(facts));
      return Collections.unmodifiableSet(factSet);
   }

   private static final Set<String> initNlsDataPlaneOutputDebugSymbols() {
      Set<String> factSet = new HashSet<>();
      String[] facts = new String[] { "BgpExportPolicy", "BgpImportPolicy",
            "BgpMultihopNeighborIp", "BgpMultihopNeighborTo",
            "BgpNeighborAdvertiseInactive", "BgpNeighborAllowLocalAsIn",
            "BgpNeighborAllowRemoteAsOut",
            "BgpNeighborGeneratedRouteAttributePolicy",
            "BgpNeighborGeneratedRoutePolicy", "BgpNeighborIp",
            "BgpNeighborLocalIp", "BgpNeighbors", "BgpNeighborSendCommunity",
            "ConnectedRoute", "Fib", "GeneratedRouteAttributePolicy",
            "IbgpNeighbors", "Ip", "IpAccessListMatchDscp",
            "IpAccessListMatchEcn", "IpAccessListMatchState",
            "IpAccessListMatchTcpFlags", "IpAccessListMatchTcpFlagsCWR",
            "IpAccessListMatchTcpFlagsECE", "IpAccessListMatchTcpFlagsURG",
            "IpAccessListMatchTcpFlagsACK", "IpAccessListMatchTcpFlagsPSH",
            "IpAccessListMatchTcpFlagsRST", "IpAccessListMatchTcpFlagsSYN",
            "IpAccessListMatchTcpFlagsFIN", "IpReadyInt", "IsisExport",
            "IsisL2EnabledInterface", "LocalAs", "NetworkOf",
            "need_PolicyMapMatchAdvert", "OriginalBgpAdvertisementRoute",
            "OspfExport", "PolicyMapClauseMatchAdvert",
            "PolicyMapClauseMatchRoute", "PolicyMapClauseTransformAdvert",
            "PolicyMapConjunctionDenyAdvert", "PolicyMapConjunctionDenyRoute",
            "PolicyMapDenyAdvert", "PolicyMapDenyRoute",
            "PolicyMapPermitAdvert", "PolicyMapPermitRoute",
            "SetBgpAdvertisement_flat", "SetBgpGeneratedRouteAttributePolicy",
            "SetBgpGeneratedRoutePolicy", "SetBgpMultihopNeighborNetwork",
            "SetBgpMultihopNeighborNetwork_flat",
            "SetBgpNeighborAdvertiseInactive",
            "SetBgpNeighborAdvertiseInactive_flat",
            "SetBgpNeighborAllowLocalAsIn", "SetBgpNeighborAllowLocalAsIn_flat",
            "SetBgpNeighborAllowRemoteAsOut",
            "SetBgpNeighborAllowRemoteAsOut_flat", "SetBgpNeighborLocalIp",
            "SetBgpNeighborLocalIp_flat", "SetBgpNeighborNetwork",
            "SetBgpNeighborNetwork_flat", "SetExternalBgpRemoteIp",
            "SetGeneratedRouteAttributePolicy",
            "SetGeneratedRouteAttributePolicy_flat",

            "SetIpAccessListLine_deny", "SetIpAccessListLine_dscp",
            "SetIpAccessListLine_dstIps", "SetIpAccessListLine_dstPorts",
            "SetIpAccessListLine_ecn", "SetIpAccessListLine_icmpCodes",
            "SetIpAccessListLine_icmpTypes", "SetIpAccessListLine_notDscp",
            "SetIpAccessListLine_notDstIps", "SetIpAccessListLine_notDstPorts",
            "SetIpAccessListLine_notEcn", "SetIpAccessListLine_notIcmpCodes",
            "SetIpAccessListLine_notIcmpTypes",
            "SetIpAccessListLine_notProtocol", "SetIpAccessListLine_notSrcIps",
            "SetIpAccessListLine_notSrcPorts", "SetIpAccessListLine_permit",
            "SetIpAccessListLine_protocol", "SetIpAccessListLine_srcIps",
            "SetIpAccessListLine_srcOrDstIps",
            "SetIpAccessListLine_srcOrDstPorts", "SetIpAccessListLine_srcPorts",
            "SetIpAccessListLine_state", "SetIpAccessListLine_tcpFlags",
            "SetIpAccessListLine_tcpFlagsCWR",
            "SetIpAccessListLine_tcpFlagsECE",
            "SetIpAccessListLine_tcpFlagsURG",
            "SetIpAccessListLine_tcpFlagsACK",
            "SetIpAccessListLine_tcpFlagsPSH",
            "SetIpAccessListLine_tcpFlagsRST",
            "SetIpAccessListLine_tcpFlagsSYN",
            "SetIpAccessListLine_tcpFlagsFIN", "SetIpInt",
            "SetIsisOutboundPolicyMap", "SetLocalAs", "SetLocalAs_flat",
            "SetNetwork", "SetOspfInterface", "SetOspfOutboundPolicyMap",
            "SetPolicyMapClauseMatchInterface", "SetPolicyMapClauseMatchPolicy",
            "SetPolicyMapClauseMatchPolicyConjunction",
            "SetPolicyMapClauseMatchProtocol", "SetPolicyMapClauseAddCommunity",
            "SetPolicyMapClauseSetCommunity",
            "SetPolicyMapClauseSetLocalPreference",
            "SetPolicyMapClauseSetMetric", "SetPolicyMapOspfExternalRouteType"

      };
      factSet.addAll(Arrays.asList(facts));
      return Collections.unmodifiableSet(factSet);
   }

   private static final Set<String> initNlsDataPlaneOutputSymbols() {
      Set<String> factSet = new HashSet<>();
      String[] facts = new String[] { "AdvertisementCommunity",
            "AdvertisementPath", "AdvertisementPathSize", "BgpAdvertisement",
            "BgpAdvertisement_dstIp", "BgpAdvertisement_dstNode",
            "BgpAdvertisement_localPref", "BgpAdvertisement_med",
            "BgpAdvertisement_network", "BgpAdvertisement_nextHopIp",
            "BgpAdvertisement_originatorIp", "BgpAdvertisement_originType",
            "BgpAdvertisement_srcIp", "BgpAdvertisement_srcNode",
            "BgpAdvertisement_srcProtocol", "BgpAdvertisement_type",
            "FibForwardPolicyRouteNextHopIp", "FibNetwork", "InstalledRoute",
            "InterfaceRoute_nextHopInt", "Network_index",
            "ReceivedBgpAdvertisement", "Route", "RouteDetails_admin",
            "RouteDetails_cost", "RouteDetails_nextHop",
            "RouteDetails_nextHopInt", "RouteDetails_nextHopIp",
            "RouteDetails_tag", "Route_network", "Route_node", "Route_protocol",
            "SetFlowSinkInterface"

      };
      factSet.addAll(Arrays.asList(facts));
      return Collections.unmodifiableSet(factSet);
   }

   private static final Set<String> initNlsTrafficComputationControlPlaneFacts() {
      Set<String> factSet = new HashSet<>();
      String[] facts = new String[] { "SamePhysicalSegment", "SetActiveInt",
            "SetAsPathLineDeny", "SetAsPathLineMatchAs",
            "SetAsPathLineMatchAsAtBeginning", "SetAsPathLineMatchAsPair",
            "SetAsPathLineMatchAsPairAtBeginning", "SetAsPathLineMatchEmpty",
            "SetAsPathLinePermit", "SetCommunityListLine",
            "SetCommunityListLinePermit", "SetCrossZoneFilter",
            "SetDefaultCrossZoneAccept", "SetDefaultInboundAccept",
            "SetFakeInterface", "SetFlowSinkInterface",
            "SetInboundInterfaceFilter", "SetInterfaceFilterIn",
            "SetInterfaceFilterOut", "SetInterfaceZone",
            "SetInterfaceRoutingPolicy", "SetIpAccessListLine_deny",
            "SetIpAccessListLine_dscp", "SetIpAccessListLine_dstIps",
            "SetIpAccessListLine_dstPorts", "SetIpAccessListLine_ecn",
            "SetIpAccessListLine_icmpCodes", "SetIpAccessListLine_icmpTypes",
            "SetIpAccessListLine_notDscp", "SetIpAccessListLine_notDstIps",
            "SetIpAccessListLine_notDstPorts", "SetIpAccessListLine_notEcn",
            "SetIpAccessListLine_notIcmpCodes",
            "SetIpAccessListLine_notIcmpTypes",
            "SetIpAccessListLine_notProtocol", "SetIpAccessListLine_notSrcIps",
            "SetIpAccessListLine_notSrcPorts", "SetIpAccessListLine_permit",
            "SetIpAccessListLine_protocol", "SetIpAccessListLine_srcIps",
            "SetIpAccessListLine_srcOrDstIps",
            "SetIpAccessListLine_srcOrDstPorts", "SetIpAccessListLine_srcPorts",
            "SetIpAccessListLine_state", "SetIpAccessListLine_tcpFlags",
            "SetIpAccessListLine_tcpFlagsCWR",
            "SetIpAccessListLine_tcpFlagsECE",
            "SetIpAccessListLine_tcpFlagsURG",
            "SetIpAccessListLine_tcpFlagsACK",
            "SetIpAccessListLine_tcpFlagsPSH",
            "SetIpAccessListLine_tcpFlagsRST",
            "SetIpAccessListLine_tcpFlagsSYN",
            "SetIpAccessListLine_tcpFlagsFIN", "SetIpInt", "SetLinkLoadLimitIn",
            "SetLinkLoadLimitOut", "SetLocalAs_flat", "SetNetwork",
            "SetNodeRole", "SetNodeVendor", "SetPolicyMapClauseAddCommunity",
            "SetPolicyMapClauseDeleteCommunity", "SetPolicyMapClauseDeny",
            "SetPolicyMapClauseMatchAcl", "SetPolicyMapClauseMatchAsPath",
            "SetPolicyMapClauseMatchColor",
            "SetPolicyMapClauseMatchCommunityList",
            "SetPolicyMapClauseMatchInterface",
            "SetPolicyMapClauseMatchNeighbor", "SetPolicyMapClauseMatchPolicy",
            "SetPolicyMapClauseMatchPolicyConjunction",
            "SetPolicyMapClauseMatchProtocol",
            "SetPolicyMapClauseMatchRouteFilter", "SetPolicyMapClauseMatchTag",
            "SetPolicyMapClausePermit", "SetPolicyMapClauseSetCommunity",
            "SetPolicyMapClauseSetCommunityNone",
            "SetPolicyMapClauseSetLocalPreference",
            "SetPolicyMapClauseSetMetric", "SetPolicyMapClauseSetNextHopIp",
            "SetPolicyMapClauseSetOriginType", "SetPolicyMapClauseSetProtocol",
            "SetPolicyMapIsisExternalRouteType",
            "SetPolicyMapOspfExternalRouteType", "SetPrecomputedRoute_flat",
            "SetRouteFilterLine", "SetRouteFilterPermitLine",
            "SetStaticIntRoute_flat", "SetSwitchportAccess",
            "SetSwitchportTrunkAllows", "SetSwitchportTrunkEncapsulation",
            "SetSwitchportTrunkNative", "SetVlanInterface",
            "SetZoneFromHostFilter", "SetZoneToHostFilter"

      };
      factSet.addAll(Arrays.asList(facts));
      return Collections.unmodifiableSet(factSet);
   }

   private static final Set<String> initNlsTrafficComputationFlowFacts() {
      Set<String> factSet = new HashSet<>();
      String[] facts = new String[] { "DuplicateRoleFlows", "SetFlowOriginate",
            "SetPrecomputedRoute_flat" };
      factSet.addAll(Arrays.asList(facts));
      return Collections.unmodifiableSet(factSet);
   }

   private static final Set<String> initNlsTrafficEntitySymbols() {
      Set<String> factSet = new HashSet<>();
      String[] facts = new String[] { "Flow_dscp", "Flow_dstIp", "Flow_dstPort",
            "Flow_ecn", "Flow_icmpCode", "Flow_icmpType", "Flow_ipProtocol",
            "Flow_node", "Flow_srcIp", "Flow_srcPort", "Flow_state",
            "Flow_tcpFlagsCWR", "Flow_tcpFlagsECE", "Flow_tcpFlagsURG",
            "Flow_tcpFlagsACK", "Flow_tcpFlagsPSH", "Flow_tcpFlagsRST",
            "Flow_tcpFlagsSYN", "Flow_tcpFlagsFIN", "Flow_tag"

      };
      factSet.addAll(Arrays.asList(facts));
      return Collections.unmodifiableSet(factSet);
   }

   private static final Set<String> initNlsTrafficOutputDebugSymbols() {
      Set<String> factSet = new HashSet<>();
      String[] facts = new String[] { "FlowAccepted", "FlowDeniedIn",
            "FlowDeniedInCrossZoneFilter", "FlowDeniedInInboundFilter",
            "FlowDeniedInInterfaceAcl", "FlowDeniedInToHostFilter",
            "FlowDeniedOut", "FlowDeniedOutCrossZone", "FlowDeniedOutHostOut",
            "FlowDeniedOutInterfaceAcl", "FlowInboundInterface",
            "FlowMatchRoute", "FlowNonInboundNullSrcZone",
            "FlowNonInboundSrcInterface", "FlowNonInboundSrcZone",
            "FlowReachPostIn", "FlowReachPostInboundCrossZoneAcl",
            "FlowReachPostInboundFilter", "FlowReachPostIncomingInterfaceAcl",
            "FlowReachPostInInterface", "FlowReachPreInInterface",
            "FlowReachPreOutEdge", "FlowReachPreOutInterface",
            "FlowReachPostHostInFilter", "FlowReachPostOutboundCrossZoneAcl",
            "FlowReachPostOutgoingInterfaceAcl", "FlowReachPostOutInterface",
            "FlowUnknown", "IpAccessListDeny", "IpAccessListMatch",
            "IpAccessListMatchDscp", "IpAccessListMatchNotDscp",
            "IpAccessListMatchDstIp", "IpAccessListMatchNotDstIp",
            "IpAccessListMatchDstPort", "IpAccessListMatchNotDstPort",
            "IpAccessListMatchEcn", "IpAccessListMatchNotEcn",
            "IpAccessListMatchIcmpCode", "IpAccessListMatchNotIcmpCode",
            "IpAccessListMatchIcmpType", "IpAccessListMatchNotIcmpType",
            "IpAccessListMatchProtocol", "IpAccessListMatchNotProtocol",
            "IpAccessListMatchSrcIp", "IpAccessListMatchNotSrcIp",
            "IpAccessListMatchSrcOrDstIp", "IpAccessListMatchSrcOrDstPort",
            "IpAccessListMatchSrcPort", "IpAccessListMatchSrcPort",
            "IpAccessListMatchState", "IpAccessListMatchTcpFlags"

      };
      factSet.addAll(Arrays.asList(facts));
      return Collections.unmodifiableSet(factSet);
   }

   private static final Set<String> initNlsTrafficOutputSymbols() {
      Set<String> factSet = new HashSet<>();
      String[] facts = new String[] { "Flow_dscp", "Flow_dstIp", "Flow_dstPort",
            "Flow_ecn", "Flow_icmpCode", "Flow_icmpType", "Flow_ipProtocol",
            "Flow_node", "Flow_srcIp", "Flow_srcPort", "Flow_state",
            "Flow_tcpFlagsCWR", "Flow_tcpFlagsECE", "Flow_tcpFlagsURG",
            "Flow_tcpFlagsACK", "Flow_tcpFlagsPSH", "Flow_tcpFlagsRST",
            "Flow_tcpFlagsSYN", "Flow_tcpFlagsFIN", "Flow_tag",
            "FlowPathAcceptedEdge", "FlowPathDeniedInEdge",
            "FlowPathDeniedOutEdge", "FlowPathHistory",
            "FlowPathIntermediateEdge", "FlowPathNeighborUnreachableEdge",
            "FlowPathNoRouteEdge", "FlowPathNullRoutedEdge"

      };
      factSet.addAll(Arrays.asList(facts));
      return Collections.unmodifiableSet(factSet);
   }

}
