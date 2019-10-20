var myData = [
           {
                nameF: "c:\windows",
                sizeF: "1"
           },
           {
                nameF: "c:\windows\driver",
                sizeF: "2"
           },

           {
                nameF: "c:\windows\conf",
                sizeF: "3"
           }
]

define(function(){
    return {
        rows: [
            { view: "button",
              label: "Main",
               click: function() {routie('')}
            },
            { view: "list",
              id: "journal1",
              template:"#nameF# - #sizeF#",
              select:true,
              data: myData
            },
            {
                view: "text",
                id: "txt_dir",
                placeholder:"Enter folder"
            },
            { view: "button",
                label: "Add",
                click: function() {
                     $$("journal1").add({
                            nameF: $$('txt_dir').getValue(),
                            sizeF: '1'
                        },0);
                    //myData.push($$('txt_dir').getValue());
                    alert($$('txt_dir').getValue());
                }
            },
        ]

    }
})