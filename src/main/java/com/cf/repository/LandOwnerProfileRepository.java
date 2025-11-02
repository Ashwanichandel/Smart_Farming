package com.cf.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cf.entity.LandOwnerProfile;
import com.cf.entity.User;

@Repository
public interface LandOwnerProfileRepository extends JpaRepository<LandOwnerProfile, Long> {
	Optional<LandOwnerProfile> findByUser(User user);

	Optional<LandOwnerProfile> findByUserId(Long userId);

	@Query("SELECT l FROM LandOwnerProfile l WHERE l.availableLandArea >= :minArea")
	List<LandOwnerProfile> findByAvailableLandAreaGreaterThanEqual(Double minArea);

	@Query("SELECT l FROM LandOwnerProfile l WHERE l.lookingForPartnership = true")
	List<LandOwnerProfile> findLandOwnersLookingForPartnership();

	List<LandOwnerProfile> findByLandVerifiedTrue();

}
