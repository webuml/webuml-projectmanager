package com.webuml.projectmanager.controller.views.lighttables;

import com.webuml.projectmanager.controller.LinkRelations;
import com.webuml.projectmanager.controller.infrastructure.HalLink;
import com.webuml.projectmanager.controller.views.associations.AssociationViewCollectionController;
import com.webuml.projectmanager.controller.views.classes.ClassViewCollectionController;
import com.webuml.projectmanager.controller.views.thumbnails.ThumbnailController;
import com.webuml.projectmanager.domain.viewmodel.LightTableView;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static com.webuml.projectmanager.controller.LinkRelations.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
class LightTableViewResourceAssembler extends ResourceAssemblerSupport<LightTableView, LightTableViewResource> {

  public LightTableViewResourceAssembler() {
    super(LightTableViewCollectionController.class, LightTableViewResource.class);
  }

  @Override
  public LightTableViewResource toResource(LightTableView lightTableView) {
    LightTableViewResource resource = createResourceWithId(lightTableView.getId(), lightTableView);

    Link classViewsLink = linkTo(methodOn(ClassViewCollectionController.class).get(lightTableView.getId())).withRel(CLASS_VIEWS);
    resource.add(new HalLink(classViewsLink, shortRel(CLASS_VIEWS)));

    Link associationViewsLink = linkTo(methodOn(AssociationViewCollectionController.class).get(lightTableView.getId())).withRel(ASSOCIATION_VIEWS);
    resource.add(new HalLink(associationViewsLink, shortRel(ASSOCIATION_VIEWS)));

    Link thumbnailLink = linkTo(methodOn(ThumbnailController.class).getJson(lightTableView.getId())).withRel(THUMBNAIL);
    resource.add(new HalLink(thumbnailLink, shortRel(THUMBNAIL)));

    return resource;
  }

  @Override
  protected LightTableViewResource instantiateResource(LightTableView entity) {
    return new LightTableViewResource(entity);
  }
}
