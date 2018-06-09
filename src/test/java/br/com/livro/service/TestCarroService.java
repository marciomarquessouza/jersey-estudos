package br.com.livro.service;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import br.com.livro.models.Carro;

public class TestCarroService{

    private CarroService carroService = new CarroService();
    private Carro carro = new Carro();

    //Test variables
    String name = "car name";
    String description = "car_description";
    String url_foto = "url_foto";
    String url_video = "url_video";
    String latitude = "latitude";
    String longitude = "longitude";
    String tipo = "tipo";

    @Before
    public void getUp() {

        carro.setNome(name);
        carro.setDescricao(description);
        carro.setUrl_foto(url_foto);
        carro.setUrl_video(url_video);
        carro.setLatitude(latitude);
        carro.setLongitude(longitude);
        carro.setTipo(tipo);

    }

    @Test
    public void testGetCarro() {

        assertTrue(carroService.save(carro));
        Carro novoCarro = carroService.getCarro(carro.getId());
        //Valida a busca por id
        assertEquals(carro.toString(), novoCarro.toString());
    }

    @Test
    public void testGetCarros() {
        List<Carro> carros = new ArrayList<>();
        carros = carroService.getCarros();
        //Valida a lista se é nula ou não
        assertNotNull(carros);
        //Valida se existe algo na lista ou não
        assertTrue(carros.size() > 0);
    }

    @Test
    public void testFindByName() {
        Carro carro = carroService.findByName("Renault Megane RS Trophy").get(0);
        assertEquals("Renault Megane RS Trophy", carro.getNome());

    }

    @Test
    public void testFindByTipo() {
        Carro carro = carroService.findByTipo("esportivos").get(0);
        assertEquals("esportivos", carro.getTipo());
    }

    @Test
    public void testSaveAndUpdate() {

        //Checking success to save a new car
        assertTrue(carroService.save(carro));

        //Checking save data
        assertEquals(name, carro.getNome());
        assertEquals(description,carro.getDescricao());
        assertEquals(url_foto, carro.getUrl_foto());
        assertEquals(url_video, carro.getUrl_video());
        assertEquals(latitude, carro.getLatitude());
        assertEquals(longitude, carro.getLongitude());
        assertEquals(tipo, carro.getTipo());

        //Checking data update
        name = "Other car name";
        description = "Other description";
        url_foto = "other url_foto";
        url_video = "other url_video";
        latitude = "other latitude";
        longitude = "other longitude";
        tipo = "other tipo";

        carro.setNome(name);
        carro.setDescricao(description);
        carro.setUrl_foto(url_foto);
        carro.setUrl_video(url_video);
        carro.setLatitude(latitude);
        carro.setLongitude(longitude);
        carro.setTipo(tipo);

        assertTrue(carroService.save(carro));

        assertEquals(name, carro.getNome());
        assertEquals(description,carro.getDescricao());
        assertEquals(url_foto, carro.getUrl_foto());
        assertEquals(url_video, carro.getUrl_video());
        assertEquals(latitude, carro.getLatitude());
        assertEquals(longitude, carro.getLongitude());
        assertEquals(tipo, carro.getTipo());

    }

    @Test
    public void testDelete() {
        // Check if the data is correctly saving
        assertTrue(carroService.save(carro));
        // Check if the data is being deleted
        assertTrue(carroService.delete(carro.getId()));
        // Check if the data is returning nothing from deleted Id
        assertNull(carroService.getCarro(carro.getId()));

    }
}
