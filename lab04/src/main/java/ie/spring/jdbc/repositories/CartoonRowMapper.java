package ie.spring.jdbc.repositories;

import ie.spring.jdbc.entities.Cartoon;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartoonRowMapper implements RowMapper<Cartoon> {
    @Override
    public Cartoon mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Cartoon(
                rs.getInt("cartoon_id"),
                rs.getString("cartoon_name"),
                rs.getInt("first_appearance_year")
        );
    }
}