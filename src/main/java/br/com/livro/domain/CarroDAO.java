package br.com.livro.domain;

import br.com.livro.models.Carro;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO extends BaseDAO {

    public Carro getCarroById (Long id) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("select * from carro where id= ? ");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                Carro carro = createCarro(rs);
                rs.close();
                return carro;
            }

        } finally {

            if(stmt != null) {
                stmt.close();
            }

            if(conn != null) {
                conn.close();
            }
        }

        return null;
    }

    public List<Carro> findByName(String name) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        List<Carro> carros = new ArrayList<>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("select * from carro where nome=?;");
            stmt.setString(1, name);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                carros.add(createCarro(resultSet));
            }
            resultSet.close();

        } finally {

            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
        return carros;
    }

    public List<Carro> findByTipo(String tipo) throws SQLException {
        List<Carro> carros = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("select * from carro where tipo=?;");
            preparedStatement.setString(1, tipo);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                carros.add(createCarro(resultSet));
            }

            resultSet.close();

        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }

        return carros;
    }

    public List<Carro> getCarros() throws SQLException {
        List<Carro> carros = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("select * from carro;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                carros.add(createCarro(resultSet));
            }
            resultSet.close();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return carros;
    }

    public Carro createCarro (ResultSet rs) throws SQLException {
        Carro carro = new Carro();

        carro.setId(rs.getLong("id"));
        carro.setNome(rs.getString("nome"));
        carro.setDescricao(rs.getString("descricao"));
        carro.setUrl_foto(rs.getString("url_foto"));
        carro.setUrl_video(rs.getString("url_video"));
        carro.setLatitude(rs.getString("latitude"));
        carro.setLongitude(rs.getString("longitude"));
        carro.setTipo(rs.getString("tipo"));
        return carro;
    }

    public void save (Carro carro) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            if(carro.getId() == null) {
                preparedStatement = connection.prepareStatement("insert into carro (nome, descricao, url_foto, url_video, latitude, longitude, tipo)" +
                        " values(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            } else {
                preparedStatement = connection.prepareStatement("update carro set nome=?, descricao=?, url_foto=?, url_video=?, latitude=?, longitude=?, tipo=? where id=?");
            }

            preparedStatement.setString(1, carro.getNome());
            preparedStatement.setString(2, carro.getDescricao());
            preparedStatement.setString(3, carro.getUrl_foto());
            preparedStatement.setString(4, carro.getUrl_video());
            preparedStatement.setString(5, carro.getLatitude());
            preparedStatement.setString(6, carro.getLongitude());
            preparedStatement.setString(7, carro.getTipo());
            if (carro.getId() != null) preparedStatement.setLong(8, carro.getId());

            int count = preparedStatement.executeUpdate();
            if (count == 0) throw new SQLException("Erro ao inserir o carro");
            if (carro.getId() == null) carro.setId(getGenerateId(preparedStatement));

        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
    }

    public static Long getGenerateId(Statement statement) throws SQLException {

        ResultSet resultSet = statement.getGeneratedKeys();

        if (resultSet.next()) {
            return resultSet.getLong(1);
        }

        return 0L;
    }

    public boolean delete (Long id) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("delete from carro where id=?;");
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
    }
}
