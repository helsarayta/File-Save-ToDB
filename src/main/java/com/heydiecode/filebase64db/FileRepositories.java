package com.heydiecode.filebase64db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepositories extends JpaRepository<File, Integer> {


}
