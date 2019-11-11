package org.pedan.monfsweb;

import org.pedan.monfsweb.domain.FolderControl;
import org.pedan.monfsweb.repos.actionFolderRepo;
import org.pedan.monfsweb.repos.folderControlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication

public class MonfswebApplication {
    @Autowired
    private folderControlRepo myFolderRepo;
    public static List<MyThread> myThreadList;
	public static void main(String[] args) {

        SpringApplication.run(MonfswebApplication.class, args);
	}

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {

/*
Итак, этот метод отслеживает что приложение Spring запустилось. После, чего он
обновляет статус всех контролируемых папок как Stop. Сделанно для того, что бы
при запуске не отображались фейковые состояния потоков, которые не существуют.
* */
        myThreadList = new ArrayList<MyThread>();
        System.out.println("hello world, I have just started up");
        List<FolderControl> myList = myFolderRepo.findAll();
        for (FolderControl myCtr : myList) {
            myCtr.setStatus("Stop");
        }
        myFolderRepo.saveAll(myList);
    }
}
