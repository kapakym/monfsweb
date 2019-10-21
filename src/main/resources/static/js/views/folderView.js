var input1 = document.createElement('input');

define(function(){
    return {
                rows: [
            { view: "toolbar",
                 cols:[
                     { view:"label", name:"headp", label:"Path: ",  align:"right", width:50},
                     { view:"label", name:"path", id:"path", label:"/",  align:"left", width:200},
                    // { view:"button", value:"Go", width:100, align:"right" },
                 ]
            },

            { view: "list",
              id: "journal2",
              template:"<span class='info'>[    #folderName#    ]</span>",
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
                                var myData = webix.ajax().post('api/listfiles', {folder: $$("journal2").getItem($$("journal2").getSelectedId()).folderName});
                                $$('w_foldercontrol').clearAll();
                                $$('w_foldercontrol').parse(myData);
                                webix.message("Add: "+$$("journal2").getItem($$("journal2").getSelectedId()).folderName);
                           }
                       }
                     },
                     { view:"button", value:"Remove folder(s)", width:200, align:"right" },
                     { view:"button", value:"Run", width:100, align:"right" },
                     { view:"button", value:"Stop", width:100, align:"right" },

                 ]
            },
                        { view:"datatable", id:"w_foldercontrol",
                             columns:[
                                 { id:"id",    header:"id folder",              width:50},
                                 { id:"folderPath",   header:"Folder path",    width:200},
                             ],
                           url: 'foldercontrol->/api/folderc'
                        },


                ]
    }

})