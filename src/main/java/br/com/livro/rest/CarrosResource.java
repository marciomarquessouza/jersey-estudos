package br.com.livro.rest;

import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import br.com.livro.service.CarroService;
import br.com.livro.models.Carro;
import br.com.livro.domain.Response;


@Path("/carros")
@Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
@Produces(MediaType.APPLICATION_JSON+ "; charset=UTF-8")
public class CarrosResource {

    private CarroService carroService = new CarroService();

    @GET
    public List<Carro> get() {
        return carroService.getCarros();
    }

    @GET
    @Path("{id}")
    public Carro get(@PathParam("id") long id) {
        return carroService.getCarro(id);
    }

    @GET
    @Path("/nome/{nome}")
    public List<Carro> getByNome (@PathParam("nome") String nome) {
        return carroService.findByName(nome);
    }

    @GET
    @Path("/tipo/{tipo}")
    public List<Carro> getByTipo (@PathParam("tipo") String tipo) {
        return carroService.findByTipo(tipo);
    }

    @POST
    public Response post(Carro carro) {
        carroService.save(carro);
        return Response.Ok("Carro salvo com sucesso");
    }

    @PUT
    public Response put(Carro carro) {
        carroService.save((carro));
        return Response.Ok("Carro atualizado com sucesso");
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") long id) {
        carroService.delete(id);
        return Response.Ok("Carro excluído com sucesso");

    }
}
