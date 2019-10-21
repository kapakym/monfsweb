package org.pedan.monfsweb;

import lombok.Data;
import org.pedan.monfsweb.domain.FolderControl;
import org.springframework.data.repository.CrudRepository;

import javax.naming.Name;

public interface FolderListRepo extends CrudRepository<FolderControl, Long> {
}
