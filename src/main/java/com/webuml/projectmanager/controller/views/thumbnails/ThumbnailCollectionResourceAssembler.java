package com.webuml.projectmanager.controller.views.thumbnails;

import com.webuml.projectmanager.controller.infrastructure.HalLink;
import com.webuml.projectmanager.domain.viewmodel.Thumbnail;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;

import static com.webuml.projectmanager.controller.LinkRelations.shortRel;
import static org.springframework.hateoas.Link.REL_SELF;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
class ThumbnailCollectionResourceAssembler extends ResourceAssemblerSupport<Iterable<Thumbnail>, ThumbnailCollectionResource> {

  @Inject
  ThumbnailResourceAssembler resourceAssembler;

  public ThumbnailCollectionResourceAssembler() {
    super(ThumbnailCollectionController.class, ThumbnailCollectionResource.class);
  }

  @Override
  public ThumbnailCollectionResource toResource(Iterable<Thumbnail> thumbnails) {

    ArrayList<ThumbnailResource> resourceList = new ArrayList<>();
    for (Thumbnail thumbnail : thumbnails) {
      ThumbnailResource resource = resourceAssembler.toResource(thumbnail);
      resourceList.add(resource);
    }

    ThumbnailCollectionResource collectionResource = new ThumbnailCollectionResource(resourceList);
    Link self = linkTo(methodOn(ThumbnailCollectionController.class).get(null)).withRel(REL_SELF);
    collectionResource.add(new HalLink(self, shortRel(REL_SELF)));
    return collectionResource;
  }
}
