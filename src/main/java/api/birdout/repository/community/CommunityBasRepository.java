package api.birdout.repository.community;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import api.birdout.entity.community.CommunityBas;

public interface CommunityBasRepository extends JpaRepository<CommunityBas, Integer> {
  
  public Page<CommunityBas> findByType(String type, Pageable pageable);

}
