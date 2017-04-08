/**
 * Copyright (c) 2014, 2017, Oracle and/or its affiliates.
 * The Universal Permissive License (UPL), Version 1.0
 */
/*
 * Your application specific code will go here
 */
define(['ojs/ojcore', 'knockout', 'ojs/ojrouter', 'ojs/ojknockout', 'ojs/ojarraytabledatasource',
  'ojs/ojoffcanvas'],
  function(oj, ko) {
     function ControllerViewModel() {
       var self = this;

      // Media queries for repsonsive layouts
      var smQuery = oj.ResponsiveUtils.getFrameworkQuery(oj.ResponsiveUtils.FRAMEWORK_QUERY_KEY.SM_ONLY);
      self.smScreen = oj.ResponsiveKnockoutUtils.createMediaQueryObservable(smQuery);
      var mdQuery = oj.ResponsiveUtils.getFrameworkQuery(oj.ResponsiveUtils.FRAMEWORK_QUERY_KEY.MD_UP);
      self.mdScreen = oj.ResponsiveKnockoutUtils.createMediaQueryObservable(mdQuery);

       // Router setup
       self.router = oj.Router.rootInstance;
       self.router.configure({
         'createOrder': {label: 'Create Order', isDefault: true},
         'myOrder': {label: 'My Order'},
         'dashboard': {label: 'Dashboard'}
       });
       if(sessionStorage["role"] == "administrator") {
    	   self.router.configure({
    	         'orderManagement': {label: 'Order Management', isDefault: true},
    	         'addGoods': {label: 'Add Goods'},
    	         'dashboard': {label: 'Dashboard'}
    	       });
       }
    	   
      oj.Router.defaults['urlAdapter'] = new oj.Router.urlParamAdapter();

      // Navigation setup
      var navData = [
      {name: 'Create Order', id: 'createOrder',
       iconClass: 'oj-navigationlist-item-icon demo-icon-font-24 demo-chart-icon-24'},
      {name: 'My Order', id: 'myOrder',
       iconClass: 'oj-navigationlist-item-icon demo-icon-font-24 demo-fire-icon-24'},
      {name: 'Dashboard', id: 'dashboard',
       iconClass: 'oj-navigationlist-item-icon demo-icon-font-24 demo-people-icon-24'}
      ];
      if(sessionStorage["role"] == "administrator") {
    	  navData = [
    	             {name: 'Order Management', id: 'orderManagement',
    	              iconClass: 'oj-navigationlist-item-icon demo-icon-font-24 demo-chart-icon-24'},
    	             {name: 'Add Goods', id: 'addGoods',
    	              iconClass: 'oj-navigationlist-item-icon demo-icon-font-24 demo-fire-icon-24'},
    	             {name: 'Dashboard', id: 'dashboard',
    	              iconClass: 'oj-navigationlist-item-icon demo-icon-font-24 demo-people-icon-24'}
    	             ];
      }
      self.navDataSource = new oj.ArrayTableDataSource(navData, {idAttribute: 'id'});

      // Drawer
      // Called by nav drawer option change events so we can close drawer after selection
      self.navChangeHandler = function (event, data) {
       if (data.option === 'selection' && data.value !== self.router.stateId()) {
         self.toggleDrawer();
       }
      }
      // Close offcanvas on medium and larger screens
      self.mdScreen.subscribe(function() {oj.OffcanvasUtils.close(self.drawerParams);});
      self.drawerParams = {
        displayMode: 'push',
        selector: '#navDrawer',
        content: '#pageContent'
      };
      // Called by navigation drawer toggle button and after selection of nav drawer item
      self.toggleDrawer = function() {
        return oj.OffcanvasUtils.toggle(self.drawerParams);
      }

      // Header
      // Application Name used in Branding Area
      self.appName = ko.observable("Office Depot Procurement");
      // User Info used in Global Navigation area
      self.userLogin = ko.observable(sessionStorage["username"]);

      // Footer
      function footerLink(name, id, linkTarget) {
        this.name = name;
        this.linkId = id;
        this.linkTarget = linkTarget;
      }
      self.footerLinks = ko.observableArray([
        new footerLink('About Oracle', 'aboutOracle', 'http://www.oracle.com/us/corporate/index.html#menu-about'),
        new footerLink('Contact Us', 'contactUs', 'http://www.oracle.com/us/corporate/contact/index.html'),
        new footerLink('Legal Notices', 'legalNotices', 'http://www.oracle.com/us/legal/index.html'),
        new footerLink('Terms Of Use', 'termsOfUse', 'http://www.oracle.com/us/legal/terms/index.html'),
        new footerLink('Your Privacy Rights', 'yourPrivacyRights', 'http://www.oracle.com/us/legal/privacy/index.html')
      ]);
      
      function logout() {
    	  sessionStorage.clear();
    	  window.location.href = "login.html";
      }
      
      self.menuItemSelect = function (event, ui) {
          switch (ui.item.attr("id")) {
              case "out":
            	  logout();
                  break;
              default:
          }    	  
      }
      
      self.acceptOrder = function(id) {
      	console.log("Accept " + id);
      }        

      self.rejectOrder = function(id) {
      	console.log("Reject " + id);
      }
     }

     return new ControllerViewModel();
  }
);
