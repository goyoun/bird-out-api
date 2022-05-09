package api.birdout.repository.utils;

import org.springframework.data.jpa.repository.JpaRepository;

import api.birdout.entity.utils.File;

public interface FileRepository extends JpaRepository<File, Integer> {
  
}
