define(function(){
    return {
                   type:"line",
                   id : 'main',
                   type:"head",
                  		rows: [
                  		    { view: "button",
                  		      label: "Journal",
                  		      click: function() {routie('journal')}
                  		                      },
                  			{ template:"Row 1"},
                  			{ view : "resizer"},
                  			{ template:"Row 2"},
                  			{ cols: [
                  			          { template: "Col 1"},
                  			          { view: "resizer" },
                  			          { template: "Col 2"}
                  			       ]
                  			}
                  		]
           	}
})