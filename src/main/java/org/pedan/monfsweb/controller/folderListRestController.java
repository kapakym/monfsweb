package org.pedan.monfsweb.controller;
/*
Данный контроллер предназначен для получения списка файлов на backend и передачи их
на frontend. Из этого списка пользователь выбирает папки, которые подлежат контрлю.
Говоря простым языком это реализация простого файлого менеджера реализованного с помощью
list из webix. В ход в папку осуществляет двойным кликом мыши по названию папки.
*/

import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/listfiles")
public class folderListRestController {


    private String GPath;
    public List<Map<String, String>> folders_list = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{put("id", "1"); put("folderName", "/home");}});
        add(new HashMap<String, String>() {{put("id", "2"); put("folderName", "/var");}});
    }};

    public void getFolders(String path) {
        folders_list.clear();
        try {

            File[] folders = new File(path).listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    return file.isDirectory();
                }
            });
            folders_list.add(new HashMap<String, String>() {{
                put("id", Integer.toString(0));
                put("folderName", "..");
            }});
            for (File dir : folders) {

                folders_list.add(new HashMap<String, String>() {{
                    put("id", Integer.toString(folders_list.size() + 1));
                    put("folderName", dir.getAbsolutePath());
                }});
            }
        }
        catch (Throwable e) {
            folders_list.add(new HashMap<String, String>() {{
                put("id", Integer.toString(0));
                put("folderName", "..");
            }});
        }
    }



    @GetMapping
    public List<Map<String, String>> list() {

        getFolders("/");
        return folders_list;
    }

    @GetMapping("{folderName}")
    public List<Map<String, String>> listby(@RequestParam String folder) {
        System.out.println(folder);
        getFolders(folder);
        return folders_list;
    }



    @PutMapping("{folderName}")
    public List<Map<String, String>> update_put(@RequestBody String folder) {
        //getFolders(folder);
        return folders_list;
    }
}
