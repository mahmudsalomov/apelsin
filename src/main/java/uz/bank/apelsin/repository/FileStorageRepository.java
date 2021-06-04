package uz.bank.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bank.apelsin.model.FileStorage;

@Repository
public interface FileStorageRepository extends JpaRepository<FileStorage,Integer> {

    FileStorage findByHashId(String hashId);
}
