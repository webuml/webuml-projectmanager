package com.webuml.projectmanager.domain.projectmodel;

import com.webuml.projectmanager.domain.primitives.ProjectId;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, ProjectId> {

}
