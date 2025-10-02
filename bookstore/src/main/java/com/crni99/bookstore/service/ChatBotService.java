package com.crni99.bookstore.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatBotService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> findMenu(String tableName) {
        String sql = "SELECT * FROM " + tableName;
        return jdbcTemplate.queryForList(sql);
    }

    public Map<String, Object> findById(int id) {
        String sql = "SELECT * FROM  customers WHERE id = ?";

        try {
            return jdbcTemplate.queryForMap(sql, id);
        } catch (Exception e) {

            return new LinkedHashMap<>();
        }
    }
}
