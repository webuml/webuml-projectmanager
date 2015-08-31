package com.webuml.projectmanager.domain.metamodel.helper;

import com.webuml.projectmanager.domain.primitives.PropertyId;

import java.util.Set;

public interface MemberEndHolder {

  Set<PropertyId> getMemberEnd();

  void setMemberEnd(Set<PropertyId> ownedEnds);
}
