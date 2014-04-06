package com.guikuki.viewresolvers;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Locale;

/**
 * ViewResolver for JSON.
 * Created by antoniosilvadelpozo on 05/04/14.
 */
public class JsonViewResolver implements ViewResolver {

    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {
        MappingJackson2JsonView mappingJackson2JsonView = new MappingJackson2JsonView();
        mappingJackson2JsonView.setPrettyPrint(true);
        return mappingJackson2JsonView;
    }
}
