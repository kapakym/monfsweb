package org.pedan.monfsweb.controller;
/*
 Данный контроллер предназначен для получения списка дисков.
 Данный контроллер был создан после запуска данной программы на Windows.
 Как вы понимаете в Windows нужно выводить еще и названия дисков.
*/

import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/disklist")
public class diskListRestController {


    private String GPath;
    public List<Map<String, String>> disks_list = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("folderName", "/home");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("folderName", "/var");
        }});
    }};

    public void getDisks() {
        disks_list.clear();
        try {

            File[] folders = File.listRoots();
            for (File dir : folders) {

                disks_list.add(new HashMap<String, String>() {{
                    put("id", Integer.toString(disks_list.size() + 1));
                    put("diskName", dir.getAbsolutePath());
                }});
            }
        } catch (Throwable e) {
            disks_list.add(new HashMap<String, String>() {{
                put("id", Integer.toString(0));
                put("folderName", "/");
            }});
        }

    }



    @GetMapping
    public List<Map<String, String>> list() {
        getDisks();
        return disks_list;
    }


}
