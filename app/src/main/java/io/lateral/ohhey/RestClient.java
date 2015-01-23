package io.lateral.ohhey;

import org.androidannotations.annotations.rest.Accept;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.api.rest.MediaType;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

/**
 * Created by Ted Eriksson on 23/01/15.
 */
@Rest(rootUrl = "oh-hey.elasticbeanstalk.com", converters = {MappingJacksonHttpMessageConverter.class})
@Accept(MediaType.APPLICATION_JSON)
public interface RestClient {
    @Post("/location")
    void postToLocation(User user);
}
