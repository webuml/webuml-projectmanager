package com.webuml.projectmanager.controller.views.thumbnails;

import com.webuml.projectmanager.controller.infrastructure.HalLink;
import com.webuml.projectmanager.controller.views.lighttables.LightTableViewController;
import com.webuml.projectmanager.domain.viewmodel.Thumbnail;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static com.webuml.projectmanager.controller.LinkRelations.LIGHT_TABLE;
import static com.webuml.projectmanager.controller.LinkRelations.shortRel;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
class ThumbnailResourceAssembler extends ResourceAssemblerSupport<Thumbnail, ThumbnailResource> {

  public ThumbnailResourceAssembler() {
    super(ThumbnailController.class, ThumbnailResource.class);
  }

  @Override
  public ThumbnailResource toResource(Thumbnail thumbnail) {
    ThumbnailResource resource = createResourceWithId(thumbnail.getId(), thumbnail, thumbnail.getId());

    Link lightTableLink = linkTo(methodOn(LightTableViewController.class).getJson(thumbnail.getLightTableViewId())).withRel(LIGHT_TABLE);
    resource.add(new HalLink(lightTableLink, shortRel(LIGHT_TABLE)));

    return resource;
  }

  @Override
  protected ThumbnailResource instantiateResource(Thumbnail entity) {
    return new ThumbnailResource(entity);
  }
}
