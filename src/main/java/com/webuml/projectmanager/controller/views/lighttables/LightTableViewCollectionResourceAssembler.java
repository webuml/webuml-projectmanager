package com.webuml.projectmanager.controller.views.lighttables;

import com.webuml.projectmanager.controller.infrastructure.HalLink;
import com.webuml.projectmanager.domain.viewmodel.LightTableView;
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
class LightTableViewCollectionResourceAssembler extends ResourceAssemblerSupport<Iterable<LightTableView>, LightTableViewCollectionResource> {

  @Inject
  LightTableViewResourceAssembler resourceAssembler;

  public LightTableViewCollectionResourceAssembler() {
    super(LightTableViewCollectionController.class, LightTableViewCollectionResource.class);
  }

  @Override
  public LightTableViewCollectionResource toResource(Iterable<LightTableView> lightTableViewIterable) {

    ArrayList<LightTableViewResource> resourceList = new ArrayList<>();
    for (LightTableView lightTableView : lightTableViewIterable) {
      LightTableViewResource resource = resourceAssembler.toResource(lightTableView);
      resourceList.add(resource);
    }
    LightTableViewCollectionResource collectionResource = new LightTableViewCollectionResource(resourceList);
    Link self = linkTo(methodOn(LightTableViewCollectionController.class).get(null)).withRel(REL_SELF);
    collectionResource.add(new HalLink(self, shortRel(REL_SELF)));
    return collectionResource;
  }
}
