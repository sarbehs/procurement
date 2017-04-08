define(['ojs/ojcore', 'knockout', 'jquery', 'utils', 'ojs/ojlistview', 'ojs/ojarraytabledatasource',
        'ojs/ojinputtext'],
 function(oj, ko, $) {
  
    function MyOrderViewModel() {
        var self = this;
      
        var data = [{name: 'Order-1000', version: '10.3.6', nodes: 2, cpu: 2, type: 'Pending', balancer: 1, memory: 8},
                  {name: 'Order-2000', version: '10.3.6', nodes: 2, cpu: 2, type: 'Accepted', balancer: 1, memory: 8},
                  {name: 'Order-3000', version: '10.3.6', nodes: 2, cpu: 2, type: 'Reject', balancer: 1, memory: 8},
                  {name: 'Order-4000', version: '10.3.6', nodes: 2, cpu: 2, type: 'Pending', balancer: 1, memory: 8},
                  {name: 'Order-5000', version: '10.3.6', nodes: 2, cpu: 2, type: 'Pending', balancer: 1, memory: 8}
                 ];
      
        self.dataSource = new oj.ArrayTableDataSource(data, {idAttribute: "name"});        
    }

    /*
     * Returns a constructor for the ViewModel so that the ViewModel is constrcuted
     * each time the view is displayed.  Return an instance of the ViewModel if
     * only one instance of the ViewModel is needed.
     */
    return new MyOrderViewModel();
  }
);
