package br.com.livro.service;

import br.com.livro.models.Carro;
import br.com.livro.domain.CarroDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarroService {
    private CarroDAO carroDAO = new CarroDAO();

    public List<Carro> getCarros(){
        try {
            return carroDAO.getCarros();
        }catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Carro>();
        }
    }

    public Carro getCarro(long id) {
        try {
            return carroDAO.getCarroById(id);
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Carro> findByName(String nome) {
        try {
            return carroDAO.findByName(nome);
        }catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Carro>();
        }
    }

    public List<Carro> findByTipo(String tipo) {
        try {
            return carroDAO.findByTipo(tipo);
        }catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Carro>();
        }
    }

    public boolean delete(Long id) {
        try{
            return carroDAO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean save(Carro carro) {
        try {
            carroDAO.save(carro);
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
