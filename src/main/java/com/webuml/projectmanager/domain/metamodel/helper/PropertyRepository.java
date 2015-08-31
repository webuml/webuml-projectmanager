package com.webuml.projectmanager.domain.metamodel.helper;

import com.webuml.projectmanager.domain.metamodel.Property;
import com.webuml.projectmanager.domain.primitives.ElementId;
import com.webuml.projectmanager.domain.primitives.PropertyId;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PropertyRepository extends CrudRepository<Property, PropertyId> {

  Set<Property> findByTypeId(ElementId typeId);
}
