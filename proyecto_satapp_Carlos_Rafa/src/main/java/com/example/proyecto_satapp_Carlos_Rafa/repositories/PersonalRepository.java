package com.example.proyecto_satapp_Carlos_Rafa.repositories;

import com.example.proyecto_satapp_Carlos_Rafa.models.Personal;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalRepository extends JpaRepository<Personal, Long> {
}
