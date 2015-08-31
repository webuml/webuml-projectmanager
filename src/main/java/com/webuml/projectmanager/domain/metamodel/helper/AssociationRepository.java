package com.webuml.projectmanager.domain.metamodel.helper;

import com.webuml.projectmanager.domain.metamodel.Association;
import com.webuml.projectmanager.domain.primitives.ElementId;
import com.webuml.projectmanager.domain.primitives.ProjectId;
import com.webuml.projectmanager.domain.primitives.PropertyId;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface AssociationRepository extends CrudRepository<Association, ElementId> {

  Set<Association> findByProjectId(ProjectId projectId);

  Set<Association> findByOwnedEnd(PropertyId typeId);

  Set<Association> findByMemberEnd(PropertyId typeId);

  Set<Association> findByMemberEndIn(Set<PropertyId> typeId);
}
