define(['ojs/ojcore', 'knockout', 'jquery', 'utils', 'ojs/ojtabs',
        'ojs/ojarraytabledatasource', 'ojs/ojinputtext'],
 function(oj, ko, $) {
  
    function OrderMgViewModel() {
        var self = this;
        
        var data = [{name: 'Order-1000', version: '10.3.6', nodes: 2, cpu: 2, type: 'Pending', balancer: 1, memory: 8},
                  {name: 'Order-2000', version: '10.3.6', nodes: 2, cpu: 2, type: 'Accepted', balancer: 1, memory: 8},
                  {name: 'Order-3000', version: '10.3.6', nodes: 2, cpu: 2, type: 'Reject', balancer: 1, memory: 8},
                  {name: 'Order-4000', version: '10.3.6', nodes: 2, cpu: 2, type: 'Pending', balancer: 1, memory: 8},
                  {name: 'Order-5000', version: '10.3.6', nodes: 2, cpu: 2, type: 'Pending', balancer: 1, memory: 8}
                 ];
      
        self.dataSourceOpen = new oj.ArrayTableDataSource(data, {idAttribute: "name"});

        self.dataSourceClose = new oj.ArrayTableDataSource(data, {idAttribute: "name"});
        
        self.acceptAll = function(data, event) {
//        	console.log("Accept " + id);
        }        

        self.rejectAll = function(data, event) {
//        	console.log("Reject " + id);
        }
    }

    return new OrderMgViewModel();
  }
);
