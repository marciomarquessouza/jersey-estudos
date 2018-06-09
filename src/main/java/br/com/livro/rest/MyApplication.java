package br.com.livro.rest;

import org.glassfish.jersey.jettison.JettisonFeature;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;


public class MyApplication extends Application {

    /* The jettison was removed to use the GSON
    @Override
    public Set<Object> getSingletons() {
        Set<Object> singletons = new HashSet<>();
        // Driver do Jettison para gera o JSON
        singletons.add(new JettisonFeature());
        return singletons;
    }*/


    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();
        // Configura o pacote para fazer scan das classes com anotações REST
        properties.put("jersey.config.server.provider.packages", "br.com.livro");
        return  properties;
    }
}
