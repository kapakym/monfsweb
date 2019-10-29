package org.pedan.monfsweb.repos;

import org.pedan.monfsweb.domain.FolderControl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface folderControlRepo extends JpaRepository<FolderControl, Long> {
    // Метод позволяющий из базы выдергивать инфу отфильтрованную по полю folderPath
    List<FolderControl> findByfolderPath(String folder);
}
