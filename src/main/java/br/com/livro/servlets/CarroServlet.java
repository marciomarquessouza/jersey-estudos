package br.com.livro.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.livro.domain.ListaCarros;
import br.com.livro.domain.Response;
import br.com.livro.util.JAXBUtil;
import br.com.livro.util.ServletUtil;
import br.com.livro.util.RegexUtil;
import br.com.livro.service.CarroService;
import br.com.livro.models.Carro;
import java.util.List;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;


@WebServlet("/carros/*")
public class CarroServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CarroService carroService = new CarroService();

    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String requestUri = request.getRequestURI();
        Long id = RegexUtil.matchId(requestUri);

        if (id != null) {
            Carro carro = carroService.getCarro(id);
            if (carro != null ) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(carro);
                ServletUtil.writeJson(response, json);
            } else {
                response.sendError(404, "Carro não encontrado");
            }
        } else {

            List<Carro> carros = carroService.getCarros();
            ListaCarros listaCarros = new ListaCarros();
            listaCarros.setCarros(carros);

            /*
            String xml = JAXBUtil.toXML(listaCarros);
            ServletUtil.writeXML(response, xml);

            String json = JAXBUtil.toJSON(listaCarros);
            ServletUtil.writeJson(response, json);
            */

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(listaCarros);
            ServletUtil.writeJson(response, json);
        }
    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        Carro carro = getCarroFromRequest(request);

        carroService.save(carro);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(carro);
        ServletUtil.writeJson(response, json);

    }

    private Carro getCarroFromRequest (HttpServletRequest request) {

        Carro carro = new Carro();

        String id = request.getParameter("id");

        if (id != null) {
            carro = carroService.getCarro(Long.parseLong(id));
        }

        carro.setNome(request.getParameter("nome"));
        carro.setDescricao(request.getParameter("descricao"));
        carro.setUrl_foto(request.getParameter("url_foto"));
        carro.setUrl_video(request.getParameter("url_video"));
        carro.setLatitude(request.getParameter("latitude"));
        carro.setLongitude(request.getParameter("longitude"));
        carro.setTipo(request.getParameter("tipo"));

        return carro;
    }

    public void doDelete (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        Long id = RegexUtil.matchId(requestURI);

        if (id != null) {
            if (carroService.delete(id)) {
                Response r = Response.Ok("Carro excluído com sucesso");
                Gson gson = new GsonBuilder().create();
                String json = gson.toJson(r);
                ServletUtil.writeJson(response, json);

            } else {
                Response r = Response.Error("URL não encontrada");
                Gson gson = new GsonBuilder().create();
                String json = gson.toJson(r);
                ServletUtil.writeJson(response, json);
            }
        }
    }
}
