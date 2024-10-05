package com.aluminium.linkShortener.repository;

import com.aluminium.linkShortener.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, String> {
}
