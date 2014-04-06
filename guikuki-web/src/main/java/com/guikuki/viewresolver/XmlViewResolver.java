package com.guikuki.viewresolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;

import java.util.Locale;

/**
 * ViewResolver for XML.
 * Created by antoniosilvadelpozo on 06/04/14.
 */
public class XmlViewResolver implements ViewResolver {

    @Autowired
    private Jaxb2Marshaller jaxb2Marshaller;

    public Jaxb2Marshaller getJaxb2Marshaller() {
        return jaxb2Marshaller;
    }

    public void setJaxb2Marshaller(Jaxb2Marshaller jaxb2Marshaller) {
        this.jaxb2Marshaller = jaxb2Marshaller;
    }

    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {
        MarshallingView marshallingView = new MarshallingView();
        marshallingView.setMarshaller(jaxb2Marshaller);
        return marshallingView;
    }
}
