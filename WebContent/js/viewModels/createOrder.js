/**
 * Copyright (c) 2014, 2017, Oracle and/or its affiliates.
 * The Universal Permissive License (UPL), Version 1.0
 */
/*
 * Your about ViewModel code goes here
 */
define(['ojs/ojcore', 'knockout', 'jquery', 'utils', 'ojs/ojinputtext', 
        'ojs/ojtable', 'ojs/ojarraytabledatasource', 'ojs/ojinputnumber'],
 function(oj, ko, $) {
  
    function CreateOrderViewModel() {
        var self = this;
      
        self.firstNameValue = ko.observable();
        self.lastNameValue = ko.observable();
        self.phoneValue = ko.observable();
        self.emailValue = ko.observable();
        self.addressValue = ko.observable();
        self.justificationValue = ko.observable();
        
        var array = [{"name" : "Pen", "supplier" : "MG", "color" : "red", "price" : 2, "id" : 1},
                     {"name" : "Max Pen", "supplier" : "Sugar", "color" : "black", "price" : 5, "id" : 2},
                     {"name" : "Pencil", "supplier" : "True Color", "color" : "blue", "price" : 1, "id" : 3}];
        var koArray = ko.observableArray(array);
        self.dataSource = new oj.ArrayTableDataSource(koArray, {idAttribute: 'id'});

        function getGoods() {
        	$.ajax({
        		type : "GET",
        		url : baseUrl + "item/items",
//        		dataType : "jsonp",
//        		jsonp : "callback",
//        		jsonCallback : "abc",
        		success : function(response) {
        			koArray(response);
        		},
        		error : function(response) {
        			errorMsg();
        		}
        	});
        }
        
//        getGoods();
        
        self.colorRenderer = function(context) {
        	var color = context.row.color;
        	var div = $("<div>");
            div.css("background-color", color);
            div.addClass("color-circle");
            $(context.cellContext.parentElement).append(div);
        }
        
        self.idRenderer = function(context) {
        	var id = context.row.id;
        	var span = $("<span>");
            span.addClass("goods-id");
            span.html(id);
            $(context.cellContext.parentElement).append(span);
        }
        
        self.countRenderer = function(context) {
        	var input = $("<input>");
        	input.attr("type", "text");
        	$(context.cellContext.parentElement).append(input);
        	input.ojInputNumber({"value": 0, "max": 10, "min": 0, "step": 1, "optionChange": self.countChange});
        	input.ojInputNumber("widget").css("max-width", "20px");
        }
        
        self.countChange = function(event, data) {
        	var rawValue, elem;
            elem = $(event.target);
            if(data["option"] == "rawValue") {
            	rawValue = elem.ojInputNumber("option", "rawValue");
            	if(rawValue >= 0) {
                	updateSelfTotal(elem, rawValue);
            		updateTotal();
            	}
            }
        }
        
        function updateSelfTotal(elem, rawValue) {
        	var tr = elem.closest("tr");
        	var totalTd = tr.find(".self-total");
        	var priceTd = tr.find(".self-price");
        	totalTd.html(priceTd.text() * rawValue);
        }

        function updateTotal() {
        	var totalPrice = 0;
        	var rows = $("#createOrder-table").find("tbody > tr");
        	$.each(rows, function(i, tr) {
        		var totalTd = $(tr).find(".self-total");
        		totalPrice += Number(totalTd.text());
        	});
        	$("#createOrder-totalPrice").html(totalPrice);
        }
        
        self.totalRenderer = function() {
        	
        }
        
        self.submit = function() {
        	
        }
    }

    /*
     * Returns a constructor for the ViewModel so that the ViewModel is constrcuted
     * each time the view is displayed.  Return an instance of the ViewModel if
     * only one instance of the ViewModel is needed.
     */
    return new CreateOrderViewModel();
  }
);
