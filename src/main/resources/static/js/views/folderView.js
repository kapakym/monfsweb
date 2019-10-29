

define(function(){
    return {
                rows: [
            { view: "toolbar",
                 cols:[
                     { view:"label", name:"headp", label:"Path: ",  align:"right", width:50},
                     { view:"label", name:"path", id:"path", label:"/",  align:"left", fillspace:true},
                      { view:"button", value:"Enter", width:100, align:"right" }
                    // { view:"button", value:"Go", width:100, align:"right" },
                 ]
            },

            { view: "list",
              id: "journal2",
              template:"<span class='info'>[    #folderName#    ]</span>",
              fillspace:true,
              select:true,
              url: "/api/listfiles",


              onDblClick:{
                    info:function(e, id) {
                    webix.message("Selected: "+this.getItem(id).folderName);
                    var myPath = this.getItem(id).folderName;
                    if (this.getItem(id).folderName=="..") {
                        var myPath = $$("path").getValue();
                        var indOf = myPath.lastIndexOf("/");
                        myPath = myPath.slice(0,indOf);
                        if (myPath=="") myPath="/";
                        webix.message("Selected: "+myPath);
                    }
                    $$("path").setValue(myPath);
                    var myData =  webix.ajax().get("/api/listfiles/folderName", { folder:myPath});
                    this.clearAll();
                    this.parse(myData);
              }}
            },
            { view: "toolbar",
                 cols:[
                     { view:"button", id:"LoadBut", value:"Add folder", width:100, align:"left",
                       click:function(){
                           if ($$("journal2").getSelectedId()!=0) {
                                var myData = webix.ajax().post('api/controltable', {folder: $$("journal2").getItem($$("journal2").getSelectedId()).folderName});
                                $$('w_foldercontrol').clearAll();
                                $$('w_foldercontrol').parse(myData);
                                webix.message("Add: "+$$("journal2").getItem($$("journal2").getSelectedId()).folderName);
                           }
                       }
                     },
                     { view:"button", value:"Remove folder(s)", width:200, align:"right",
                       click:function(){
                            var myData = webix.ajax().del('api/controltable', {id:$$('w_foldercontrol').getSelectedId().id});
                            $$('w_foldercontrol').clearAll();
                            $$('w_foldercontrol').parse(myData);
                       }
                     },
                     { view:"button", value:"Run", width:100, align:"right",
                       click: function(){
                            var myData = webix.ajax().post('api/controltable/run', {folder:$$('w_foldercontrol').getItem($$('w_foldercontrol').getSelectedId()).folderPath});
                            $$('w_foldercontrol').clearAll();
                            $$('w_foldercontrol').parse(myData);
                       }
                     },
                     { view:"button", value:"Stop", width:100, align:"right" },

                 ]
            },
                        { view:"datatable", id:"w_foldercontrol", select:true,
                             columns:[
                                 { id:"id",    header:"id folder",              width:50},
                                 { id:"folderPath",   header:"Folder path",    fillspace:true},
                                 { id:"status", header:"Status"}
                             ],

                           url: 'api/controltable',

                        },


                ]
    }

})