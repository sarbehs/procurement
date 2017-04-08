/**
 * Copyright (c) 2014, 2017, Oracle and/or its affiliates.
 * The Universal Permissive License (UPL), Version 1.0
 */
'use strict';

/**
 * Example of Require.js boostrap javascript
 */

requirejs.config(
{
  baseUrl: 'js',

  // Path mappings for the logical module names
  paths:
  //injector:mainReleasePaths
  {
    'knockout': 'libs/knockout/knockout-3.4.0.debug',
    'jquery': 'libs/jquery/jquery-3.1.0',
    'jqueryui-amd': 'libs/jquery/jqueryui-amd-1.12.0',
    'promise': 'libs/es6-promise/es6-promise',
    'hammerjs': 'libs/hammer/hammer-2.0.8',
    'ojdnd': 'libs/dnd-polyfill/dnd-polyfill-1.0.0',
    'ojs': 'libs/oj/v2.3.0/debug',
    'ojL10n': 'libs/oj/v2.3.0/ojL10n',
    'ojtranslations': 'libs/oj/v2.3.0/resources',
    'text': 'libs/require/text',
    'signals': 'libs/js-signals/signals',
    'customElements': 'libs/webcomponents/CustomElements',
    'proj4': 'libs/proj4js/dist/proj4-src',
    'css': 'libs/require-css/css',
  }
  //endinjector
  ,
  // Shim configurations for modules that do not expose AMD
  shim:
  {
    'jquery':
    {
      exports: ['jQuery', '$']
    }
  }
}
);

/**
 * A top-level require call executed by the Application.
 * Although 'ojcore' and 'knockout' would be loaded in any case (they are specified as dependencies
 * by the modules themselves), we are listing them explicitly to get the references to the 'oj' and 'ko'
 * objects in the callback
 */
require(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'ojs/ojinputtext','ojs/ojbutton'],
  function (oj, ko, $) { // this callback gets executed when all required modules are loaded
	
	function LoginViewModel() {
		var self = this;
		
		self.login = function() {
			sessionStorage["username"] = $("#login-name").val();
			if(sessionStorage["username"] == "admin")
				sessionStorage["role"] = "administrator";
			else
				sessionStorage["role"] = "regular";
	        window.location.href = "index.html";
		}
		
		submitByEnterKey = function(event) {
		   if(event && event.keyCode == 13) {  // 13 is Enter key
			   self.login();
		   }
		}

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
	}
	
	var vm = new LoginViewModel();
	
	$(
            function()
            {
            	ko.applyBindings(vm, document.getElementById('login-pageContent'));
            }
    );
}
);

var submitByEnterKey;

