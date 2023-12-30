package com.pet.pet_shelter.DAOs;

import com.pet.pet_shelter.DTOs.Document;
import com.pet.pet_shelter.ENUMS.DocType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class DocumentDao {

    @Autowired
    JdbcTemplate jdbc;

    public int addDoc(Document doc){
        return jdbc.update("insert into documents (path,type,pet_id) values (?,?,?)",
                doc.getPath(),
                doc.getType().toString(),
                doc.getPetID()
            );
    }

    public List<Document> getDocumentsForPet(Long petId){
        return jdbc.query("select path , type from documents where documents.pet_id = ?", new DocRowMapper(), petId);
    }

    static class DocRowMapper implements RowMapper<Document>{

        @Override
        public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Document.builder()
            .path(rs.getString("path"))
            .type(DocType.valueOf(rs.getString("type")))
            .petID(rs.getLong("pet_id")).build();
        }

    }

}
