package com.webuml.projectmanager.controller.infrastructure;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.webuml.projectmanager.domain.primitives.ElementId;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Configuration
@EnableEntityLinks
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

  public static final String[] PUBLIC_RESOURCES = new String[] {"classpath:/static/"};

  private ObjectMapper objectMapper;

  @Inject
  public void setObjectMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
    objectMapper.registerModule(customJacksonModule());
    this.objectMapper.setSerializationInclusion(NON_EMPTY);
    this.objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    this.objectMapper.setDateFormat(new ISO8601DateFormat());
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    converter.setObjectMapper(objectMapper);
    converters.add(converter);
  }

  Module customJacksonModule() {
    final SimpleModule module = new SimpleModule("webuml-projectmanager");
    module.addDeserializer(ElementId.class, new FromStringDeserializer<ElementId>(ElementId.class) {
      @Override
      protected ElementId _deserialize(String value, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return new ElementId(value);
      }
    });
    module.addSerializer(ElementId.class, new JsonSerializer<ElementId>() {
      @Override
      public void serialize(ElementId id, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        if (id != null) {
          jgen.writeString(id.getValue());
        }
      }
    });
    return module;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**")
        .addResourceLocations("classpath:/static/");
  }
}
