define(function() {
   var ajax = webix.ajax().hedaders(values:{
        "Content-type":"application/json"
   })
   webix.proxy.resource = {
       init:function(){
           webix.extend(this, webix.proxy.rest);
       },
       load:function(view, params){
            return ajax.get("api/listfiles");
       },
       save:function(view, params){
           params.data.meta = this.meta;
           return webix.proxy.rest.save.call(this, view, params);
       }
   }
})