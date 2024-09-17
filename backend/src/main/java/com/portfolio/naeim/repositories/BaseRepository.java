package com.portfolio.naeim.repositories;

import com.portfolio.naeim.entities.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository <T extends BaseEntity> extends JpaRepository<T, Long> {
}
