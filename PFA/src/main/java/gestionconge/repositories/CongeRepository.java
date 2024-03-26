package gestionconge.repositories;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import gestionconge.dao.Conge;
public interface CongeRepository extends JpaRepository<Conge, Integer> {
	
	void delete (Conge conge);
	
	List<Conge> findAll();
	
	Conge findOne(int id);
	
	Conge save(Conge conge);
	
	@Query(nativeQuery = true, value = "SELECT * FROM Conge WHERE id = :id")
	List<Conge> findByUtilisateur(@Param("id") int id);
	
	@Query(nativeQuery = true, value = "SELECT * FROM Conge WHERE status = :status order by dateDemande DESC")
	List<Conge> findByStatus(@Param("status") String status);
	
	@Query(nativeQuery = true, value = "SELECT * FROM Conge WHERE status = :status AND id = :id order by dateDemande DESC")
	List<Conge> findByStatusAndUtilisateur (@Param("status") String status, @Param("id") int id);
	
    @Query(nativeQuery = true, value ="SELECT * FROM Conge c WHERE c.status = :status and CAST(:date as date) between c.dateDebut and c.dateFin order by c.dateDebut DESC")
    List<Conge> findAllByDayAndStatus(@Param("status") String status, @Param("date") String date);
    
    @Query(nativeQuery = true, value = "SELECT * FROM Conge c WHERE CAST(:date as date) BETWEEN c.dateDebut AND c.dateFin AND c.status LIKE 'approved'")
    List<Conge> findApprovedCongesByDate(@Param("date") Date date);
    
    @Query(nativeQuery = true, value = "SELECT * FROM Conge c WHERE c.status NOT LIKE 'pending' and id = :id ")
    List<Conge> findHistoriqueConges(@Param("id") int id);
    
    @Query(nativeQuery = true, value ="SELECT * FROM Conge c where c.id = :id and (CAST(:dateDebut as date) BETWEEN c.dateDebut AND c.dateFin OR CAST(:dateFin as date) BETWEEN c.dateDebut AND c.dateFin OR CAST(:dateDebut as date) >= c.dateDebut AND CAST(:dateFin as date) <= c.dateFin) AND c.status NOT LIKE 'declined'")  
    List<Conge> overlapDateRange(@Param("id") int id, @Param("dateDebut") String dateDebut, @Param("dateFin") String dateFin);
}
