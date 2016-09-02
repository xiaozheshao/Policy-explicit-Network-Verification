package org.batfish.datamodel;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.batfish.common.util.CommonUtil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CommunityListLine implements Serializable {

   private static final String ACTION_VAR = "action";

   private static final String REGEX_VAR = "regex";

   private static final long serialVersionUID = 1L;

   private final LineAction _action;

   private final String _regex;

   @JsonCreator
   public CommunityListLine(@JsonProperty(ACTION_VAR) LineAction action,
         @JsonProperty(REGEX_VAR) String regex) {
      _action = action;
      _regex = regex;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      CommunityListLine other = (CommunityListLine) obj;
      if (_action != other._action) {
         return false;
      }
      if (!_regex.equals(other._regex)) {
         return false;
      }
      return true;
   }

   @JsonProperty(ACTION_VAR)
   public LineAction getAction() {
      return _action;
   }

   public Set<Long> getExactMatchingCommunities(Set<Long> allCommunities) {
      Pattern p = Pattern.compile(_regex);
      Set<Long> matchingCommunitites = new LinkedHashSet<>();
      for (long candidateCommunity : allCommunities) {
         String candidateCommunityStr = CommonUtil
               .longToCommunity(candidateCommunity);
         Matcher matcher = p.matcher(candidateCommunityStr);
         if (matcher.matches()) {
            matchingCommunitites.add(candidateCommunity);
         }
      }
      return matchingCommunitites;
   }

   public Set<Long> getMatchingCommunities(Set<Long> allCommunities) {
      Pattern p = Pattern.compile(_regex);
      Set<Long> matchingCommunitites = new LinkedHashSet<>();
      for (long candidateCommunity : allCommunities) {
         String candidateCommunityStr = CommonUtil
               .longToCommunity(candidateCommunity);
         Matcher matcher = p.matcher(candidateCommunityStr);
         if (matcher.find()) {
            matchingCommunitites.add(candidateCommunity);
         }
      }
      return matchingCommunitites;
   }

   @JsonProperty(REGEX_VAR)
   public String getRegex() {
      return _regex;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + _action.hashCode();
      result = prime * result + _regex.hashCode();
      return result;
   }

}
