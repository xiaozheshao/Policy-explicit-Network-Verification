parser grammar Cisco_interface;

import Cisco_common;

options {
   tokenVocab = CiscoLexer;
}

if_default_gw
:
   DEFAULT_GW IP_ADDRESS NEWLINE
;

if_description
:
   description_line
;

if_hsrp
:
   HSRP group = DEC NEWLINE
   (
      if_hsrp_ip_address
      | if_hsrp_preempt
      | if_hsrp_priority
      | if_hsrp_track
   )*
;

if_hsrp_ip_address
:
   IP ip = IP_ADDRESS NEWLINE
;

if_hsrp_preempt
:
   PREEMPT NEWLINE
;

if_hsrp_priority
:
   PRIORITY value = DEC NEWLINE
;

if_hsrp_track
:
   TRACK ~NEWLINE* NEWLINE
;

if_ip_access_group
:
   (
      (
         (
            IP
            | IPV4
         ) PORT? ACCESS_GROUP
      )
      |
      (
         ACCESS_LIST NAME
      )
   ) name = variable
   (
      EGRESS
      | IN
      | INGRESS
      | OUT
   )
   (
      HARDWARE_COUNT
      | OPTIMIZED
   )* NEWLINE
;

if_ip_address
:
   (
      IP
      | IPV4
   ) ADDRESS
   (
      (
         ip = IP_ADDRESS subnet = IP_ADDRESS
      )
      | prefix = IP_PREFIX
   )
   (
      STANDBY standby_address = IP_ADDRESS
   )? NEWLINE
;

if_ip_address_dhcp
:
   IP ADDRESS DHCP NEWLINE
;

if_ip_address_secondary
:
   (
      IP
      | IPV4
   ) ADDRESS
   (
      (
         ip = IP_ADDRESS subnet = IP_ADDRESS
      )
      | prefix = IP_PREFIX
   ) SECONDARY NEWLINE
;

if_ip_ospf_area_null
:
   IP OSPF DEC AREA DEC NEWLINE
;

if_ip_ospf_cost
:
   IP OSPF COST cost = DEC NEWLINE
;

if_ip_ospf_dead_interval
:
   IP OSPF DEAD_INTERVAL seconds = DEC NEWLINE
;

if_ip_ospf_dead_interval_minimal
:
   IP OSPF DEAD_INTERVAL MINIMAL HELLO_MULTIPLIER mult = DEC NEWLINE
;

if_ip_ospf_hello_interval
:
   IP OSPF HELLO_INTERVAL seconds = DEC NEWLINE
;

if_ip_ospf_passive_interface
:
   NO? IP OSPF PASSIVE_INTERFACE NEWLINE
;

if_ip_policy
:
   IP POLICY ROUTE_MAP name = ~NEWLINE NEWLINE
;

if_ip_proxy_arp
:
   NO? IP PROXY_ARP NEWLINE
;

if_ip_router_isis
:
   IP ROUTER ISIS NEWLINE
;

if_ip_verify
:
   IP VERIFY UNICAST
   (
      (
         NOTIFICATION THRESHOLD DEC
      )
      |
      (
         REVERSE_PATH ALLOW_SELF_PING? acl = DEC?
      )
      |
      (
         SOURCE REACHABLE_VIA
         (
            ANY
            | RX
         )
         (
            ALLOW_DEFAULT
            | ALLOW_SELF_PING
            | L2_SRC
         )* acl = DEC?
      )
   ) NEWLINE
;

if_isis_circuit_type
:
   ISIS CIRCUIT_TYPE
   (
      LEVEL_2_ONLY
      | LEVEL_2
   ) NEWLINE
;

if_isis_enable
:
   ISIS ENABLE num = DEC NEWLINE
;

if_isis_hello_interval
:
   ISIS HELLO_INTERVAL DEC
   (
      LEVEL_1
      | LEVEL_2
   )? NEWLINE
;

if_isis_metric
:
   ISIS IPV6? METRIC metric = DEC
   (
      LEVEL_1
      | LEVEL_2
   )? NEWLINE
;

if_isis_network
:
   ISIS NETWORK POINT_TO_POINT NEWLINE
;

if_isis_passive
:
   ISIS PASSIVE NEWLINE
;

if_isis_tag
:
   ISIS TAG tag = DEC NEWLINE
;

if_load_interval
:
   LOAD_INTERVAL li = DEC NEWLINE
;

if_mtu
:
   MTU mtu_size = DEC NEWLINE
;

if_no_ip_address
:
   NO IP ADDRESS NEWLINE
;

if_null_block
:
   NO?
   (
      AFFINITY
      | ANTENNA
      | ARP
      | ASYNC
      | ATM
      | AUTHENTICATION
      | AUTO
      | AUTOROUTE
      | AUTOSTATE
      | BANDWIDTH
      | BEACON
      | BFD
      | BGP_POLICY
      | BRIDGE_GROUP
      | BUNDLE
      | CABLELENGTH
      | CARRIER_DELAY
      | CDP
      | CHANNEL
      | CHANNEL_GROUP
      | CHANNEL_PROTOCOL
      | CLASS
      | CLNS
      | CLOCK
      | COUNTER
      | CRC
      | CRYPTO
      | DAMPENING
      | DCB
      | DCBX
      | DCB_POLICY
      | DELAY
      | DESTINATION
      | DIALER
      | DFS
      |
      (
         DSU BANDWIDTH
      )
      | DUPLEX
      | ENABLE
      | ENCAPSULATION
      | ENCRYPTION
      | EXIT
      | FAIR_QUEUE
      | FAST_REROUTE
      | FLOW
      | FLOW_CONTROL
      | FLOWCONTROL
      | FORWARDER
      | FRAME_RELAY
      | FRAMING
      | FULL_DUPLEX
      | GIG_DEFAULT
      | GLBP
      | GROUP_RANGE
      | H323_GATEWAY
      | HALF_DUPLEX
      | HARDWARE
      | HISTORY
      | HOLD_QUEUE
      |
      (
         HSRP VERSION
      )
      | IGNORE
      |
      (
         INTERFACE BREAKOUT
      )
      | INGRESS
      |
      (
         IP
         (
            ACCOUNTING
            | ARP
            | BROADCAST_ADDRESS
            | CGMP
            | CONTROL_APPS_USE_MGMT_PORT
            | DHCP
            | DVMRP
            |
            (
               DIRECTED_BROADCAST
            )
            | FLOW
            | HELPER_ADDRESS
            | IGMP
            | IP_ADDRESS
            | IRDP
            | LOAD_SHARING
            | MROUTE_CACHE
            | MTU
            | MULTICAST
            | MULTICAST_BOUNDARY
            |
            (
               OSPF
               (
                  AUTHENTICATION
                  | AUTHENTICATION_KEY
                  | BFD
                  | MESSAGE_DIGEST_KEY
                  | MTU_IGNORE
                  | NETWORK
                  | PRIORITY
               )
            )
            | NAT
            | PIM
            | PIM_SPARSE
            | PORT_UNREACHABLE
            | REDIRECT
            | REDIRECTS
            | RIP
            | ROUTE_CACHE
            | ROUTER
            | RSVP
            | SDR
            | TCP
            | UNNUMBERED
            | UNREACHABLES
            | VERIFY
            | VIRTUAL_REASSEMBLY
            | VIRTUAL_ROUTER
            | VRF
         )
      )
      |
      (
         IPV4
         (
            ICMP 
            | MTU
            | POINT_TO_POINT
            | UNNUMBERED
            | UNREACHABLES
            | VERIFY
         )
      )
      | IPV6
      | ISDN
      |
      (
         ISIS
         (
            AUTHENTICATION
            | CSNP_INTERVAL
            | HELLO
            | HELLO_MULTIPLIER
            | LSP_INTERVAL
            | POINT_TO_POINT
         )
      )
      | KEEPALIVE
      | L2_FILTER
      | L2PROTOCOL_TUNNEL
      | L2TRANSPORT
      | LANE
      | LAPB
      | LACP
      | LINK
      | LINK_FAULT_SIGNALING
      | LLDP
      | LOAD_BALANCING
      | LOAD_INTERVAL
      | LOGGING
      | LOOPBACK
      | LRE
      | MAC
      | MAC_ADDRESS
      | MACRO
      | MANAGEMENT
      | MANAGEMENT_ONLY
      | MAP_GROUP
      | MDIX
      | MEDIA_TYPE
      | MEDIUM
      | MEMBER
      | MINIMUM_LINKS
      | MLAG
      | MLS
      | MOBILITY
      | MOP
      | MPLS
      | NAME
      | NAMEIF
      | NEGOTIATE
      | NEGOTIATION
      | NMSP
      |
      (
         NO
         (
            DESCRIPTION
            |
            (
               SWITCHPORT
               (
                  BLOCK
                  | DOT1Q
                  | PRIORITY
               )
            )
         )
      )
      |
      (
         NTP
         (
            BROADCAST
            | DISABLE
         )
      )
      | NV
      | OPENFLOW
      | OPTICAL_MONITOR
      | OSPFV3
      | PACKET
      | PATH_OPTION
      | PEAKDETECT
      | PEER
      | PFC PRIORITY
      | PHYSICAL_LAYER
      | PORT_CHANNEL
      | PORT_CHANNEL_PROTOCOL
      | PORT_NAME
      | PORTMODE
      | POS
      | POWER
      | PPP
      | PREEMPT
      | PRIORITY
      | PRIORITY_FLOW_CONTROL
      | PRIORITY_QUEUE
      | QOS
      | QUEUE_SET
      | RANDOM_DETECT
      | RATE_LIMIT
      | RATE_MODE
      | RCV_QUEUE
      | REMOTE
      | ROUTE_CACHE
      | ROUTE_ONLY
      | SCRAMBLE
      | SECURITY_LEVEL
      | SERIAL
      | SERVICE_MODULE
      | SERVICE_POLICY
      | SFLOW
      | SIGNALLED_BANDWIDTH
      | SIGNALLED_NAME
      | SONET
      | SPANNING_TREE
      | SPEED
      | SPEED_DUPLEX
      | SNMP
      | SRR_QUEUE
      | SSID
      | STACK_MIB
      | STANDBY
      | STATION_ROLE
      | STBC
      | STORM_CONTROL
      |
      (
         SWITCHPORT
         (
            EMPTY
            |
            (
               MODE PRIVATE_VLAN
            )
            | MONITOR
            | NONEGOTIATE
            | PORT_SECURITY
            | TAP
            | TOOL
            |
            (
               TRUNK GROUP
            )
            | VOICE
            | VLAN
         )
      )
      | TAG_SWITCHING
      | TAGGED
      | TAP
      | TCAM
      | TRANSCEIVER
      | TRANSPORT_MODE
      | TRUST
      | TUNABLE_OPTIC
      | TUNNEL
      | TX_QUEUE
      | UC_TX_QUEUE
      | UDLD
      | UNTAGGED
      | VLT_PEER_LAG
      | VPC
      | VRRP
      | VRRP_GROUP
      | WEIGHTING
      | WRR_QUEUE
      | X25
      | XCONNECT
   ) ~NEWLINE* NEWLINE if_null_inner*
;

if_null_inner
:
   NO?
   (
      ADDRESS
      | PRIORITY
      | PROPAGATE
      | RECEIVE
      | REMOTE_PORTS
      | SATELLITE_FABRIC_LINK
      | TRANSMIT
      | VIRTUAL_ADDRESS
   ) ~NEWLINE* NEWLINE
;

if_port_security
:
   PORT SECURITY NEWLINE
   (
      if_port_security_null
   )*
;

if_port_security_null
:
   NO?
   (
      AGE
      | ENABLE
      | MAXIMUM
      | SECURE_MAC_ADDRESS
      | VIOLATION
   ) ~NEWLINE* NEWLINE
;

if_shutdown
:
   NO?
   (
      DISABLE
      | SHUTDOWN
   ) FORCE? LAN? NEWLINE
;

if_switchport
:
   NO? SWITCHPORT NEWLINE
;

if_switchport_access
:
   SWITCHPORT ACCESS VLAN
   (
      vlan = DEC
      | DYNAMIC
   ) NEWLINE
;

if_switchport_mode
:
   SWITCHPORT MODE
   (
      ACCESS
      | DOT1Q_TUNNEL
      |
      (
         DYNAMIC
         (
            AUTO
            | DESIRABLE
         )
      )
      | FEX_FABRIC
      | TAP
      | TOOL
      | TRUNK
   ) NEWLINE
;

if_switchport_private_vlan_association
:
   SWITCHPORT PRIVATE_VLAN ASSOCIATION TRUNK primary_vlan_id = DEC
   secondary_vlan_id = DEC NEWLINE
;

if_switchport_private_vlan_host_association
:
   SWITCHPORT PRIVATE_VLAN HOST_ASSOCIATION primary_vlan_id = DEC
   secondary_vlan_id = DEC NEWLINE
;

if_switchport_private_vlan_mapping
:
   SWITCHPORT PRIVATE_VLAN MAPPING TRUNK? primary_vlan_id = DEC
   secondary_vlan_list = range NEWLINE
;

if_switchport_trunk_allowed
:
   SWITCHPORT TRUNK ALLOWED VLAN ADD? r = range NEWLINE
;

if_switchport_trunk_encapsulation
:
   SWITCHPORT TRUNK ENCAPSULATION e = switchport_trunk_encapsulation NEWLINE
;

if_switchport_trunk_native
:
   SWITCHPORT TRUNK NATIVE VLAN vlan = DEC NEWLINE
;

if_vrf
:
   VRF name = variable NEWLINE
;

if_vrf_forwarding
:
   VRF FORWARDING name = variable NEWLINE
;

if_vrf_member
:
   VRF MEMBER name = variable NEWLINE
;

s_interface
:
   INTERFACE PRECONFIGURE? iname = interface_name
   (
      L2TRANSPORT
      | MULTIPOINT
      | POINT_TO_POINT
   )? NEWLINE
   (
      if_default_gw
      | if_description
      | if_hsrp
      | if_ip_proxy_arp
      | if_ip_verify
      | if_ip_access_group
      | if_ip_address
      | if_ip_address_dhcp
      | if_ip_address_secondary
      | if_ip_ospf_area_null
      | if_ip_ospf_cost
      | if_ip_ospf_dead_interval
      | if_ip_ospf_dead_interval_minimal
      | if_ip_ospf_hello_interval
      | if_ip_ospf_passive_interface
      | if_ip_policy
      | if_ip_router_isis
      | if_isis_circuit_type
      | if_isis_enable
      | if_isis_hello_interval
      | if_isis_metric
      | if_isis_network
      | if_isis_passive
      | if_isis_tag
      | if_load_interval
      | if_mtu
      | if_no_ip_address
      | if_null_block
      | if_port_security
      | if_shutdown
      | if_switchport
      | if_switchport_access
      | if_switchport_mode
      | if_switchport_private_vlan_association
      | if_switchport_private_vlan_host_association
      | if_switchport_private_vlan_mapping
      | if_switchport_trunk_allowed
      | if_switchport_trunk_encapsulation
      | if_switchport_trunk_native
      | if_vrf
      | if_vrf_forwarding
      | if_vrf_member
      | unrecognized_line
   )*
;
