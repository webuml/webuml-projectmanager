package com.webuml.projectmanager.domain.metamodel;

import com.webuml.projectmanager.domain.primitives.ElementId;
import com.webuml.projectmanager.domain.primitives.Identifiable;

public interface Element<IdentifierType extends ElementId> extends Identifiable<IdentifierType> {

}
