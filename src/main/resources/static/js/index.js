requirejs.config({
    baseUrl: 'js'
})

function buildRoute(view) {
    return function(){
        webix.ui({
                id : "root",

                rows: [ view ]
        }, $$("root"))
    }
}


function navApp(value) {
    if (value=="Folder setting") routie("");
    if (value=="Journal") routie("journal");
}

require(['views/main', 'views/journal', 'views/folderView', 'util/resourceProxy'],
function(main, journal, folderView, resourceProxy) {
webix.ready(function() {
	webix.ui({
	        container: "app",
	        width: document.body.clientWidth,
	        height: document.body.clientHeight,
	        rows: [
	        	{ view: "toolbar", padding:3, elements: [
            					{ view: "button", type: "icon", icon: "mdi mdi-menu",
            						width: 37, align: "left", css: "app_button", click: function(){
            							$$("$sidebar1").toggle();
            						}
            					},
            					{ view: "label", label: "File system monitor (Java+Spring+Webix)"},
            					{},
            					{ view: "button", type: "icon", width: 45, css: "app_button", icon: "mdi mdi-comment",  badge:4},
            					{ view: "button", type: "icon", width: 45, css: "app_button", icon: "mdi mdi-bell",  badge:10}
            				]
            				},
	            { cols: [
	                { "view": "sidebar", "width": 250, 'height': 600,
	                    data: [
                            { "value": "Folder setting", "icon": "wxi-check"},
                            { "value": "Journal", "icon": "wxi-dots"},
                            { "value": "Statistics", "icon": "wxi-alert" },
                            { "value": "Settings monitor", "icon": "wxi-file" },
                        ],
                        on:{ onAfterSelect: function(id){
                                                            navApp(this.getItem(id).value);
                                                            //webix.message("Selected: "+this.getItem(id).value)
                                                        }
                           }
                    },
                    {id: "root"},
	            ]}



	        ]
	})
})
routie({
    	    "" : buildRoute(folderView),
    	    "journal" : buildRoute(journal),
    	    "folderView": buildRoute(folderView)
    	})
})