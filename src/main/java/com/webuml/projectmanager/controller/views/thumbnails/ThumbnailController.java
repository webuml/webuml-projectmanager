package com.webuml.projectmanager.controller.views.thumbnails;

import com.webuml.projectmanager.controller.infrastructure.CommonErrorController;
import com.webuml.projectmanager.domain.viewmodel.ElementViewId;
import com.webuml.projectmanager.domain.viewmodel.Thumbnail;
import com.webuml.projectmanager.domain.viewmodel.ThumbnailRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.core.Relation;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Collection;

import static com.webuml.projectmanager.controller.LinkRelations.THUMBNAIL;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/thumbnails/{lightTableViewId}")
@Relation(collectionRelation = THUMBNAIL)
@ExposesResourceFor(Thumbnail.class)
public class ThumbnailController extends CommonErrorController {

  @Inject
  ThumbnailRepository repository;

  @Inject
  ThumbnailResourceAssembler resourceAssembler;

  @Value("${webuml-ui.baseUri}")
  String webumlUiBaseUri;

  @RequestMapping(method = PUT)
  @ResponseStatus(NO_CONTENT)
  public void put(@PathVariable("lightTableViewId") ElementViewId lightTableViewId, @RequestBody Thumbnail thumbnail) throws IOException {
    Collection<Thumbnail> thumbnails = repository.findByLightTableViewId(lightTableViewId);
    if (thumbnails == null || thumbnails.isEmpty()) {
      post(thumbnail);
    } else {
      Thumbnail loadedThumbnail = thumbnails.iterator().next();
      loadedThumbnail.setImageData(thumbnail.getImageData());
      loadedThumbnail.setTitle(thumbnail.getTitle());
      repository.save(loadedThumbnail);
    }
  }

  @RequestMapping(method = POST)
  @ResponseStatus(CREATED)
  public void post(@RequestBody Thumbnail thumbnail) throws IOException {
    thumbnail.setId(new ElementViewId());
    repository.save(thumbnail);
  }

  @RequestMapping(method = GET, produces = "application/hal+json")
  public ThumbnailResource getJson(@PathVariable("lightTableViewId") ElementViewId lightTableId) {
    Collection<Thumbnail> thumbnails = repository.findByLightTableViewId(lightTableId);
    Thumbnail thumbnail = null;
    if (thumbnails == null || thumbnails.isEmpty()) {
      thumbnail = createDefaultThumbnail(lightTableId);
    } else {
      thumbnail = thumbnails.iterator().next();
    }
    return resourceAssembler.toResource(thumbnail);
  }

  private Thumbnail createDefaultThumbnail(ElementViewId lightTableId) {
    Thumbnail thumbnail = new Thumbnail(new ElementViewId());
    thumbnail.setImageData("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAABhCAMAAAAp+FneAAADAFBMVEUnlx2dnZ2tz6vX19fn7ee3t7e5ubn39/fj4+PFxcWrq6vPz89zs2/9/f3d3d3v7+/j6+OxsbHD3cGjy6G/v7/Ly8uJvYf7+/vT09Pz8/Otx62pqanb29v3+ffn5+fJycmvr69tyV/9//3h4eHp6em1tbXDw8MrmSOjo6Ox07HZ2dnt7e27u7vl5eXHx8etra3R0dHf39/x8fGzs7PT29OnzaXBwcHNzc2NwYnV1dX19fWty635+fl9yXP////r6+sAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAKltBPAAAACXBIWXMAAAsTAAALEwEAmpwYAAABR0lEQVR42u3YS1ODMBQFYDRKWjXGRyuiVbSAjxZsS7QIxtv//6/sdIpD3Doki56zu2z4SBhyBo8EdxhBXiALd4lj4YU1uYucawAAAAAAAP4JEPPIyCKwDOjlHxftOUssA+Kb7+c7VlbNfdXCNuD982S8iiehM8DKnAEAYNcAcvDiEMCH6ql2tgKCpZuvriPAvZ+OtsuQPczaUdPuAcVSlfp30qGRWnQMCEq1LDrvA4V5xkfX2wcWo0M/t1FI2NnVJbWuq81hm2QpE0Q2ANXXozdmp8fNGz3rUdhXkbZWyaqD/VfiXDabfeurgbTZCStuzntDy6X0L2C9BQAAAIBdAEtqI0e5ZYDuq/N23mrLABLaSEC2AfhBAQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADsGEA6BCRrgK5Kh5kIj/jUYTT9AE5fThcc+hguAAAAAElFTkSuQmCC");
    thumbnail.setLightTableViewId(lightTableId);
    thumbnail.setTitle("Golden Test Data");
    return thumbnail;
  }

}
