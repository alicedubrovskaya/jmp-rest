package com.dubrouskaya.cloud.repository;

import com.dubrouskaya.jmpdto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
