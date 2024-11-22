package ie.spring.jdbc.repositories;

import ie.spring.jdbc.entities.Cartoon;
import ie.spring.jdbc.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CartoonRepositoryImpl implements CartoonRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CartoonRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int count() {
        String sql = "select count(*) from cartoons";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        if (count == null) {
            return 0;
        }
        return count;
    }

    @Override
    public Optional<Cartoon> findByCartoonId(int cartoonId) throws NotFoundException {
        try {
            String sql = "SELECT * FROM cartoons WHERE cartoons.cartoon_id = ?";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new CartoonRowMapper(), cartoonId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Cartoon> findAll() {
        String sql = "SELECT * FROM cartoons";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Cartoon.class));
        }

    @Override
    @Transactional
    public void delete(int cartoonId) {
        String sql = "DELETE FROM cartoons WHERE cartoons.cartoon_id = ?";
        jdbcTemplate.update(sql, cartoonId);
    }

    @Override
    public void save(Cartoon cartoon) {
        String sql = "INSERT INTO cartoons (cartoon_id, cartoon_name, first_appearance_year) VALUES (:id, :name, :year)";
        Map<String, Object> params = new HashMap<>();
        params.put("id", cartoon.getCartoonId());
        params.put("name", cartoon.getCartoonName());
        params.put("year", cartoon.getFirstAppearanceYear());
        namedParameterJdbcTemplate.update(sql, params);
    }
}