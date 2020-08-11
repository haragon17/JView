store = {};
panels = {};
userType = "";
userDept = "";

Ext.onReady(function() {

	projid = new Ext.form.Hidden({
		name : 'projid',
		id : 'projid'
	});
	cusid = new Ext.form.Hidden({
		name : 'cusid',
		id : 'cusid'
	});
	fid = new Ext.form.Hidden({
		name : 'fid',
		id : 'fid'
	});
	
	panels.search = Ext.create('Ext.form.Panel', {
		title : 'Search Criteria',
		autoWidth : true,
		id : 'formPanel',
		width : 800,
		height : 240,
		collapsible : true,
		renderTo : document.body,
		style : {
			"margin-left" : "auto",
			"margin-right" : "auto",
			"margin-top" : "30px"
		},
		layout : 'column',
		fieldDefaults : {
			labelAlign : '',
			msgTarget : 'side'
		},
		defaults : {
			xtype : 'container',
			layout : 'form',
			columnWidth : 1,
			labelWidth : 0,
			anchor : '100%',
			hideBorders : false,
			padding : '10 10 10 10'
		},

		items : [ {
			layout : 'column',
			border : false,
			items : [ {
				columnWidth : 0.66,
				border : false,
				layout : 'anchor',
				style : {
					"margin-left" : "50px",
					"margin-right" : "auto",
					"margin-top" : "10px",
					"margin-bottom" : "10px"
				},
				defaultType : 'textfield',
				items : [ 
				    {
				    fieldLabel : 'Project Name ',
				    name : 'sproj_name',
				    id : 'sproj_name',
				    labelWidth : 100,
					margin : '0 0 10 0',
					width : 340,
					emptyText : 'Project Name'
				    
				    },{
						fieldLabel : 'Price €',
						name : 'sprice',
						combineErrors: true,
						xtype: 'fieldcontainer',
						labelWidth : 100,
						margin : '0 0 10 0',
						width : 350,
						layout: 'hbox',
		                defaults: {
		                    flex: 1,
		                },
		                items: [
		                    {
		                        xtype : 'numberfield',
		                        name : 'price_start',
		                        id	: 'price_start',
		                        labelSeparator : '',
		                        margin: '0 0 0 0',
		                        msgTarget : 'side',
//		                        minValue : 0,
		                        width: 50,
		                        listeners: {
		                        	   "blur": function () {
		                        		   			var startDate = Ext.getCmp('price_start').getRawValue();
		                        		   			Ext.getCmp('price_limit').setMinValue(startDate);
		                        	   }
		                        }
		                    },{
		                    	 xtype: 'fieldcontainer',
		     	                fieldLabel: 'To ',
		     	                combineErrors: true,
		     	                msgTarget : 'side',
		     	                margin: '0 0 0 5',
		                    	labelSeparator : '',
		                        Width : 20
		                    },
		                    {
		                        xtype     : 'numberfield',
		                        margin: '0 10 0 -80',	
		                        name      : 'price_limit',
		                        id	: 'price_limit',
		                        labelSeparator : '',
		                        msgTarget : 'side',
//		                        minValue : 0,
		                        width: 50,
		                        listeners: {
		                        	"blur": function () {
		                        		   	var endDate = Ext.getCmp('price_limit').getRawValue();
		                           			Ext.getCmp('price_start').setMaxValue(endDate);
		                        	}
		                        }
		                    }
		                ]
					},{
						fieldLabel : 'Target Time',
						name : 'stime',
						combineErrors: true,
						xtype: 'fieldcontainer',
						labelWidth : 100,
						msgTarget : 'above',
						margin : '0 0 10 0',
						width : 350,
						layout: 'hbox',
		                defaults: {
		                    flex: 1,
		                },
		                items: [
		                    {
		                        xtype     : 'numberfield',
		                        name      : 'time_start',
		                        id	: 'time_start',
		                        labelSeparator : '',
		                        margin: '0 0 0 0',
		                        msgTarget : 'side',
		                        emptyText : 'Minutes',
		                        minValue : 0,
		                        width: 50,
		                        listeners: {
		                        	   "blur": function () {
		                        		   			var startDate = Ext.getCmp('time_start').getRawValue();
		                        		   			Ext.getCmp('time_limit').setMinValue(startDate);
		                        	   }
		                        }
		                    },{
		                    	 xtype: 'fieldcontainer',
		     	                fieldLabel: 'To ',
		     	                combineErrors: true,
		     	                msgTarget : 'side',
		     	                margin: '0 0 0 5',
		                    	labelSeparator : '',
		                        Width : 20
		                    },
		                    {
		                        xtype     : 'numberfield',
		                        margin: '0 10 0 -80',	
		                        name      : 'time_limit',
		                        id	: 'time_limit',
		                        labelSeparator : '',
//		                        msgTarget : 'side',
		                        emptyText : 'Minutes',
		                        minValue : 0,
		                        width: 50,
		                        listeners: {
		                        	"blur": function () {
		                        		   	var endDate = Ext.getCmp('time_limit').getRawValue();
		                           			Ext.getCmp('time_start').setMaxValue(endDate);
		                        	}
		                        }
		                    }
		                ]
					},{
						fieldLabel : 'Update Date',
						name : 'supdate_date',
						combineErrors: true,
						xtype: 'fieldcontainer',
						labelWidth : 100,
						margin : '0 0 10 0',
						width : 350,
						layout: 'hbox',
		                defaults: {
		                    flex: 1,
		                },
		                items: [
		                    {
		                        xtype     : 'datefield',
		                        name      : 'update_start',
		                        id	: 'update_start',
		                        labelSeparator : '',
		                        margin: '0 0 0 0',
		                        msgTarget : 'side',
		                        width: 50,
		                        editable: false,
		                        format: 'Y-m-d',
		                        maxValue: new Date(),
		                        listeners: {
		                        	   "change": function () {
		                        		   			var startDate = Ext.getCmp('update_start').getRawValue();
		                        		   			Ext.getCmp('update_limit').setMinValue(startDate);
		                        	   }
		                        }
		                    },{
		                    	 xtype: 'fieldcontainer',
		     	                fieldLabel: 'To ',
		     	                combineErrors: true,
		     	                msgTarget : 'side',
		     	                margin: '0 0 0 5',
		                    	labelSeparator : '',
		                        Width : 20
		                    },
		                    {
		                        xtype     : 'datefield',
		                        margin: '0 10 0 -80',	
		                        name      : 'update_limit',
		                        id	: 'update_limit',
		                        labelSeparator : '',
		                        msgTarget : 'side',
		                        editable: false,
		                        format: 'Y-m-d',
		                        maxValue: new Date(),
		                        width: 50,
		                        listeners: {
		                        	"change": function () {
		                        		   	var endDate = Ext.getCmp('update_limit').getRawValue();
		                           			Ext.getCmp('update_start').setMaxValue(endDate);
		                        	}
		                        }
		                    }
		                ]
					} ]
			}, {
				columnWidth : 0.33,
				style : {
					"margin-left" : "-80px",
					"margin-right" : "10px",
					"margin-top" : "10px",
				},
				border : false,
				layout : 'anchor',
				defaultType : 'textfield',
				items : [ {
					xtype: 'combobox',
					fieldLabel : 'Item Name ',
					name : 'sitm_id',
					id : 'sitm_id',
					queryMode : 'local',
					labelWidth : 110,
					margin : '0 0 10 0',
					width : 280,
					emptyText : 'Item Name',
					store : {
						fields : [ 'itm_id', 'itm_name' ],
						proxy : {
							type : 'ajax',
							url : 'showItem.htm?id=0',
							reader : {
								type : 'json',
								root : 'records',
								idProperty : 'itm_id'
							}
						},
						autoLoad : true,
						sorters: [{
					         property: 'itm_name',
					         direction: 'ASC'
					     }]
					},
					valueField : 'itm_id',
					displayField : 'itm_name',
					listeners : {
						blur : function() {
							var v = this.getValue();
							var record = this.findRecord(this.valueField || this.displayField, v);
							if(record == false){
								Ext.getCmp('sitm_id').setValue("");
							}
						}
					}
				}, {
					xtype : 'combobox',
					fieldLabel : 'Customer Name ',
					name : 'scus_name',
					id: 'scus_name',
					queryMode : 'local',
					labelWidth : 110,
					margin : '0 0 10 0',
					width : 280,
					emptyText : 'Customer Name',
					store : {
						fields : [ 'cus_id', 'cus_name', 'cus_code' ],
						proxy : {
							type : 'ajax',
							url : 'showCustomer.htm',
							reader : {
								type : 'json',
								root : 'records',
								idProperty : 'cus_id'
							}
						},
						autoLoad : true,
						sorters: [{
					         property: 'cus_name',
					         direction: 'ASC'
					     }]
					},
					valueField : 'cus_name',
					displayField : 'cus_name',
					listeners : {

						select : function() {
							
							var v = this.getValue();
							var record = this.findRecord(this.valueField || this.displayField, v);
							var myIndex = this.store.indexOf(record);
							var myValue = this.store.getAt(myIndex).data.cus_code;
							var myId = this.store.getAt(myIndex).data.cus_id;
							Ext.getCmp('cusid').setValue(myId);
							Ext.getCmp('scus_code').setValue(myValue);
							
							console.log("cus_code: "+myValue);
						},
						blur : function() {
							var v = this.getValue();
							var record = this.findRecord(this.valueField || this.displayField, v);
							if(record == false){
								Ext.getCmp('cusid').setValue("");
								Ext.getCmp('scus_name').setValue("");
								Ext.getCmp('scus_code').setValue("");
							}
						}
					}
				}, {
					xtype : 'combobox',
					fieldLabel : 'Customer Code ',
					name : 'scus_code',
					id : 'scus_code',
					queryMode : 'local',
					labelWidth : 110,
					margin : '0 0 10 0',
					width : 280,
					emptyText : 'Customer Code',
					store : {
						fields : [ 'cus_id', 'cus_code', 'cus_name' ],
						proxy : {
							type : 'ajax',
							url : 'showCustomer.htm',
							reader : {
								type : 'json',
								root : 'records',
								idProperty : 'cus_id'
							}
						},
						autoLoad : true,
						sorters: [{
					         property: 'cus_code',
					         direction: 'ASC'
					     }]
					},
					valueField : 'cus_code',
					displayField : 'cus_code',
					listeners : {

						select : function() {
							
							var v = this.getValue();
							var record = this.findRecord(this.valueField || this.displayField, v);
							var myIndex = this.store.indexOf(record);
							var myValue = this.store.getAt(myIndex).data.cus_name;
							var myId = this.store.getAt(myIndex).data.cus_id;
							Ext.getCmp('cusid').setValue(myId);
							Ext.getCmp('scus_name').setValue(myValue);
							
							console.log("cus_name: "+myValue);
						},
						blur : function() {
							var v = this.getValue();
							var record = this.findRecord(this.valueField || this.displayField, v);
							if(record == false){
								Ext.getCmp('cusid').setValue("");
								Ext.getCmp('scus_name').setValue("");
								Ext.getCmp('scus_code').setValue("");
							}
						}

					}
				},{
					xtype: 'combobox',
					fieldLabel : 'Key Acc Mng ',
					name : 'skey_acc_mng',
					id : 'skey_acc_mng',
					queryMode : 'local',
					labelWidth : 110,
					margin : '0 0 10 0',
					width : 280,
					editable : false,
					emptyText : 'Key Acc Mng',
					store : {
						fields : [ 'key_acc_id', 'key_acc_name' ],
						proxy : {
							type : 'ajax',
							url : 'showKeyAccMng.htm',
							reader : {
								type : 'json',
								root : 'records',
								idProperty : 'key_acc_id'
							}
						},
						autoLoad : true
					},
					valueField : 'key_acc_id',
					displayField : 'key_acc_name'
				}  ]
			} ]
		} ],

		buttons : [ {
			text : 'Search',
			id : 'searchs',
			handler : function() {
				var form = this.up('form').getForm();
				if (form.isValid()) {
//					Ext.getCmp('ireport').setDisabled(false);
					Ext.Ajax.request({
						url : 'searchProjectsParam.htm?AUD='+store.exchangeRates.getAt(0).data.AUD+'&CHF='+store.exchangeRates.getAt(0).data.CHF+
						'&GBP='+store.exchangeRates.getAt(0).data.GBP+'&THB='+store.exchangeRates.getAt(0).data.THB+
						'&USD='+store.exchangeRates.getAt(0).data.USD+'&EUR='+store.exchangeRates.getAt(0).data.EUR+'&cus_id='+Ext.getCmp('cusid').getValue() + getParamValues(),
						success : function(response, opts) {
//							store.projectsRef.loadPage(1);
							store.projectsRef.reload();
							setTimeout(function(){store.projects.loadPage(1)},500);
//							for(var xyz=0;xyz<store.projects.count();xyz++){
//								if(Ext.fly(panels.grid.plugins[0].view.getNodes()[xyz]).hasCls(panels.grid.plugins[0].rowCollapsedCls) == true){
//									panels.grid.plugins[0].toggleRow(xyz, panels.grid.getStore().getAt(xyz));
//								}
//							}
						}
					});

				} else {
					Ext.MessageBox.show({
						title : 'Failed',
						msg : ' Please Check On Invalid Field!',
						buttons : Ext.MessageBox.OK,
						icon : Ext.MessageBox.ERROR,
						animateTarget : 'searchs',
					});
					console.debug("Projects.js Else conditions");
				}
			}
		}, {
			text : 'Reset',
			handler : function() {
				this.up('form').getForm().reset();
				Ext.getCmp('cusid').setValue("");
				Ext.getCmp('update_start').setMaxValue(new Date());
				Ext.getCmp('update_limit').setMinValue('');
			}
		} ]

	});

	panels.grid = Ext.create('Ext.grid.Panel', {
		renderTo : document.body,
		xtype: 'row-expander-grid',
		title : 'Projects',
		split : true,
		forceFit : true,
		loadMask : true,
		autoWidth : true,
		frame : true,
		store : store.projects,
		tools : [ 
//		{
//			xtype : 'button',
//			text : 'Report',
//			id : 'ireport',
//			margin : '0 5 0 0',
//			disabled : true,
//			iconCls : 'icon-print',
//			handler : function() {
//				alert("Print Report!");
//				var form = Ext.getCmp('formPanel').getForm();	   	
//			}
//		},
		{
			xtype : 'button',
			text : 'Add Project',
			id : 'icreate',
			iconCls : 'icon-add',
			handler : function() {
				addProject.showAt(panels.search.getX()+150,panels.search.getY());
			}
		} ],
		style : {
			"margin-left" : "auto",
			"margin-right" : "auto",
			"margin-top" : "15px",
			"margin-bottom" : "10px"
		},
		width : 1200,
		height : 500,
		columns : [
				{
					text : "Project Name",
					flex : 1.5,
					sortable : true,
					dataIndex : 'proj_name'
				},
				{
					text : "Customer Name",
					flex : 1.5,
					sortable : true,
					dataIndex : 'cus_name'
				},
				{
					text : "Project Description",
					flex : 2,
					sortable : true,
					dataIndex : 'proj_desc'
				},
				{
					text : "Billing To",
					flex : 1.2,
					sortable : true,
					dataIndex : 'bill_to'
				},
				{
					text : "Terms",
					flex : 0.6,
					sortable : true,
					dataIndex : 'billing_terms'
				},
				{
					text : "Currency",
					flex : 0.8,
					dataIndex : 'proj_currency',
					renderer : currencyName
				},
//				{
//					text : "Price",
//					width : 80,
//					sortable : true,
//					renderer : combinecols,
//					dataIndex : 'proj_ref_id'
//				},
//				{
//					text : "Price(Euro)",
//					width : 80,
//					sortable : true,
//					renderer : currencyToEuro,
//					dataIndex : 'proj_ref_id'
//				},
				{
					text : 'Briefing',
					flex : 0.5,
					xtype : 'actioncolumn',
					align : 'center',
					id : 'open',
					items : [{
						getClass: function(v, meta, rec) {
							
							if(rec.get('file_id') != 0){
								this.items[0].tooltip = 'd1';
								return 'icon-d1';
							} else {
								this.items[0].tooltip = 'd2';
								
								return 'icon-d2';
							}
							
						},
						handler : function(grid, rowIndex, colIndex){
							file_id = grid.getStore().getAt(rowIndex).get('file_id');
							if(file_id != 0){
								window.open('download.htm?file='+file_id,'_blank');
							}else{
								Ext.MessageBox.show({
									title : 'Infomation',
									msg : 'No Briefing Uploaded !',
									buttons : Ext.MessageBox.OK,
									animateTarget : 'open',
									icon : Ext.MessageBox.INFO
								});
							}
						}
						
//						iconCls : 'my-icon',
//						tooltip : 'Download',
//						handler : function(grid, rowIndex, colIndex){
//							file_id = grid.getStore().getAt(rowIndex).get('file_id');
//							if(file_id != 0){
//								window.open('download.htm?file='+file_id,'_blank');
//							}else{
//								Ext.MessageBox.show({
//									title : 'Infomation',
//									msg : 'No Briefing Uploaded !',
//									buttons : Ext.MessageBox.OK,
//									animateTarget : 'open',
//									icon : Ext.MessageBox.INFO
//								});
//							}
//						}
					}]
				},
				{
					text : 'Add',
					flex : 0.3,
					xtype : 'actioncolumn',
					align : 'center',
					id : 'add',
					items : [{
						iconCls : 'icon-add',
						tooltip : 'Add',
						handler : function(grid, rowIndex, colIndex){
							proj_id = grid.getStore().getAt(rowIndex).get('proj_id');
//							alert(proj_id);
							Ext.getCmp('aproj_id').setValue(proj_id);
							addItem.show();
						}
					}]
				},
				{
					text : 'Edit',
					xtype : 'actioncolumn',
					flex : 0.3,
					align : 'center',
					id : 'edit',
					items : [ {
						iconCls : 'table-edit',
						handler : function(grid, rowIndex, colIndex) {
							proj_id = grid.getStore().getAt(rowIndex).get('proj_id');
							proj_name = grid.getStore().getAt(rowIndex).get('proj_name');
							cus_id = grid.getStore().getAt(rowIndex).get('cus_id');
							file_id = grid.getStore().getAt(rowIndex).get('file_id');
							proj_desc = grid.getStore().getAt(rowIndex).get('proj_desc');
							cus_name = grid.getStore().getAt(rowIndex).get('cus_name');
							cus_code = grid.getStore().getAt(rowIndex).get('cus_code');
							proj_currency = grid.getStore().getAt(rowIndex).get('proj_currency');

							Ext.getCmp('eproj_name').setValue(proj_name);
							Ext.getCmp('ecus_id').setValue(cus_id);
							Ext.getCmp('ecus_name').setValue(cus_name);
							Ext.getCmp('ecus_code').setValue(cus_code);
							Ext.getCmp('eproj_desc').setValue(proj_desc);
							Ext.getCmp('eproj_currency').setValue(proj_currency);
							Ext.getCmp('eproj_id').setValue(proj_id);
							Ext.getCmp('efile_id').setValue(file_id);
							editProject.show();
						}
					} ]
				},
				{
					text : 'Delete',
					xtype : 'actioncolumn',
					flex : 0.35,
					align : 'center',
					id : 'del',
					items : [ {
						iconCls : 'icon-delete',
						handler : function(grid, rowIndex, colIndex) {
							proj_id = grid.getStore().getAt(rowIndex).get('proj_id');
							file_id = grid.getStore().getAt(rowIndex).get('file_id');
							Ext.getCmp('projid').setValue(proj_id);
							Ext.getCmp('fid').setValue(file_id);
							if(userDept == "Manager" || userDept == "Billing" || userDept == "IT"){
								Ext.MessageBox.show({
									title : 'Confirm',
									msg : 'Are you sure you want to delete this?',
									buttons : Ext.MessageBox.YESNO,
									animateTarget : 'del',
									fn : confirmChk,
									icon : Ext.MessageBox.QUESTION
								});
							}else{
								Ext.MessageBox.show({
									title : 'Information',
									msg : 'Please contact Billing Department for delete !',
									buttons : Ext.MessageBox.OK,
									animateTarget : 'del',
									icon : Ext.MessageBox.INFO
								});
							}
						}
					} ]
				},
				],
		columnLines : true,
		listeners: {
			viewready: function (grid) {
		        var view = grid.view;
		        this.toolTip = Ext.create('Ext.tip.ToolTip', {
		            target: view.el,
		            delegate: view.cellSelector,
//		            width: 'auto',
//		            autoWidth: true,
		            trackMouse: true,
		            renderTo: Ext.getBody(),
		            listeners: {
		                beforeshow: function(tip) {
		                    var trigger = tip.triggerElement,
		                        parent = tip.triggerElement.parentElement,
		                        columnTitle = view.getHeaderByCell(trigger).text,
		                        columnDataIndex = view.getHeaderByCell(trigger).dataIndex,
		                        columnText = view.getRecord(parent).get(columnDataIndex).toString();
		                    if (columnText){
//		                        tip.update("<b>" + columnTitle + ":</b> " + columnText);
		                        tip.update("<b>"+(columnText.replace(/\r\n|\n/gi, "<br>"))+"</b>");
		                    } else {
		                        return false;
		                    }
		                }
		            }
		        });
	        }
	    },
		plugins: [{
	        ptype: 'rowexpander',
	        rowBodyTpl : new Ext.XTemplate(
	        		'{proj_id:this.projects_reference}',
	        		{
	        			projects_reference: function(v){
		            		var myText = "";
		            		var record = store.projects.findRecord('proj_id',v);
		    				var myIndex = store.projects.indexOf(record);
		    				var proj_currency = store.projects.getAt(myIndex).data.proj_currency;
		            		 store.projectsRef.each(function(rec){
//		            			 alert(rec);
		            			 var myIndex = store.projectsRef.indexOf(rec);
		            			 var myEuro = "";
		            			 var myDesc = "";
		            			 if(rec.data.proj_ref_desc != ""){
		            				 myDesc = rec.data.proj_ref_desc;
		            			 }else{
		            				 myDesc = "-";
		            			 }
		            			if(proj_currency == "USD"){
		            				 myEuro = '€ '+(Math.round(((Math.round(rec.data.price*100)/100)*store.exchangeRates.getAt(0).data.EUR)*100)/100);
            					}else if(proj_currency == "EUR"){
            						myEuro = '€ '+(Math.round(rec.data.price*100)/100);
            					}else if(proj_currency == "THB"){
            						myEuro = '€ '+(Math.round(((Math.round(rec.data.price*100)/100)/store.exchangeRates.getAt(0).data.THB*store.exchangeRates.getAt(0).data.EUR)*100)/100);
            					}else if(proj_currency == "AUD"){
            						myEuro = '€ '+(Math.round(((Math.round(rec.data.price*100)/100)/store.exchangeRates.getAt(0).data.AUD*store.exchangeRates.getAt(0).data.EUR)*100)/100);
            					}else if(proj_currency == "GBP"){
            						myEuro = '€ '+(Math.round(((Math.round(rec.data.price*100)/100)/store.exchangeRates.getAt(0).data.GBP*store.exchangeRates.getAt(0).data.EUR)*100)/100);
            					}else if(proj_currency == "CHF"){
            						myEuro = '€ '+(Math.round(((Math.round(rec.data.price*100)/100)/store.exchangeRates.getAt(0).data.CHF*store.exchangeRates.getAt(0).data.EUR)*100)/100);
            					}else if(proj_currency == "SGD"){
            						myEuro = '€ '+(Math.round(((Math.round(rec.data.price*100)/100)/store.exchangeRates.getAt(0).data.SGD*store.exchangeRates.getAt(0).data.EUR)*100)/100);
            					}else{
            						myEuro = '-';
            					}
//		            			if(proj_currency == "EUR"){
//            						myEuro = '€ '+(Math.round(rec.data.price*100)/100);
//		            			}else if(proj_currency == "USD"){
//		            				myEuro = '€ '+(Math.round(((Math.round(rec.data.price*100)/100)/store.exchangeRates.getAt(0).data.USD)*100)/100);
//            					}else if(proj_currency == "THB"){
//            						myEuro = '€ '+(Math.round(((Math.round(rec.data.price*100)/100)/store.exchangeRates.getAt(0).data.THB)*100)/100);
//            					}else if(proj_currency == "AUD"){
//            						myEuro = '€ '+(Math.round(((Math.round(rec.data.price*100)/100)/store.exchangeRates.getAt(0).data.AUD)*100)/100);
//            					}else if(proj_currency == "GBP"){
//            						myEuro = '€ '+(Math.round(((Math.round(rec.data.price*100)/100)/store.exchangeRates.getAt(0).data.GBP)*100)/100);
//            					}else if(proj_currency == "CHF"){
//            						myEuro = '€ '+(Math.round(((Math.round(rec.data.price*100)/100)/store.exchangeRates.getAt(0).data.CHF)*100)/100);
//            					}else if(proj_currency == "SGD"){
//            						myEuro = '€ '+(Math.round(((Math.round(rec.data.price*100)/100)/store.exchangeRates.getAt(0).data.SGD)*100)/100);
//            					}else{
//            						myEuro = '-';
//            					}
		            			if(rec.data.proj_id == v){
		            				var actual_time = "";
		            				var target_time = "";
		            				var topix_id = "";
//		            				alert(rec.data.proj_id);
//		            				alert(rec.data.actual_time);
		            				
		            				if(rec.data.activated == 0){
			            				if(rec.data.actual_time == 0){
			            					actual_time = '<td bgcolor=#FE7575><strike>Actual Time: <b>'+(Math.round(rec.data.actual_time*100)/100)+'</b></strike></td>';
			            				}else if((rec.data.actual_time - rec.data.time) > 0){
			            					actual_time = '<td bgcolor=#FE7575><strike>Actual Time: <b>'+(Math.round(rec.data.actual_time*100)/100)+'</b></strike></td>';
			            				}else{
			            					actual_time = '<td bgcolor=#F0F0F0><strike>Actual Time: <b>'+(Math.round(rec.data.actual_time*100)/100)+'</b></strike></td>';
			            				}
			            				if(rec.data.time == 0){
			            					target_time = '<td bgcolor=#FE7575><strike>Target Time: <b>'+(Math.round(rec.data.time*100)/100)+'</b></strike></td>';
			            				}else{
			            					target_time = '<td bgcolor=#F0F0F0><strike>Target Time: <b>'+(Math.round(rec.data.time*100)/100)+'</b></strike></td>';
			            				}
			            				if(rec.data.topix_article_id == "" || rec.data.topix_article_id == null){
			            					topix_id = "-";
			            				}else{
			            					topix_id = rec.data.topix_article_id;
			            				}
		            					myText += '<td bgcolor=#F0F0F0><strike>Topix ID: <b>'+topix_id+'</b></strike></td>'+ 
			            				'<td bgcolor=#F0F0F0><strike>Item: <b>'+rec.data.itm_name+'</b></strike></td>'+
	//		            				 '<td bgcolor=#F0F0F0>Target Time: <b>'+rec.data.time+'</b></td>'+
			            				 target_time+
	//		            				 '<td bgcolor=#F0F0F0>Actual Time: <b>'+rec.data.actual_time+'</b></td>'+
			            				 actual_time+
			            				 '<td bgcolor=#F0F0F0><strike>Price: <b>'+(Math.round(rec.data.price*100)/100)+' '+proj_currency+'</b></strike></td>'+
			            				 '<td bgcolor=#F0F0F0><strike>Price(Euro): <b>'+myEuro+'</strike></b></td>'+
			            				 '<td bgcolor=#F0F0F0><strike>Description: <b>'+myDesc+'</strike></b></td>'+
			            				 '<td><a href="javascript:editItemFunction('+myIndex+');"><font color=#B0B0B0><u>edit</u></font></a></td>'+
		            					 '<td><a href="javascript:deleteItem('+rec.data.proj_ref_id+');"><font color=#B0B0B0><u>delete</u></font></a></td></tr>';
	//		            				 '<td><a href="javascript:contactBilling();"><font color=#B0B0B0><u>delete</u></font></a></td></tr>';
//			            				if(userDept == "Manager" || userDept == "Billing" || userDept == "IT"){
//			            					myText += '<td><a href="javascript:deleteItem('+rec.data.proj_ref_id+');"><font color=#B0B0B0><u>delete</u></font></a></td></tr>';
//			            				}else{
//			            					myText += '<td><a href="javascript:contactBilling();"><font color=#B0B0B0><u>delete</u></font></a></td></tr>';
//			            				}
		            				}else{
		            					if(rec.data.actual_time == 0){
			            					actual_time = '<td bgcolor=#FE7575>Actual Time: <b>'+(Math.round(rec.data.actual_time*100)/100)+'</b></td>';
			            				}else if((rec.data.actual_time - rec.data.time) > 0){
			            					actual_time = '<td bgcolor=#FE7575>Actual Time: <b>'+(Math.round(rec.data.actual_time*100)/100)+'</b></td>';
			            				}else{
			            					actual_time = '<td bgcolor=#F0F0F0>Actual Time: <b>'+(Math.round(rec.data.actual_time*100)/100)+'</b></td>';
			            				}
			            				if(rec.data.time == 0){
			            					target_time = '<td bgcolor=#FE7575>Target Time: <b>'+(Math.round(rec.data.time*100)/100)+'</b></td>';
			            				}else{
			            					target_time = '<td bgcolor=#F0F0F0>Target Time: <b>'+(Math.round(rec.data.time*100)/100)+'</b></td>';
			            				}
			            				if(rec.data.topix_article_id == "" || rec.data.topix_article_id == null){
			            					topix_id = "-";
			            				}else{
			            					topix_id = rec.data.topix_article_id;
			            				}
			            				myText += '<td bgcolor=#F0F0F0>Topix ID: <b>'+topix_id+'</b></td>'+ 
			            				'<td bgcolor=#F0F0F0>Item: <b>'+rec.data.itm_name+'</b></td>'+
	//		            				 '<td bgcolor=#F0F0F0>Target Time: <b>'+rec.data.time+'</b></td>'+
			            				 target_time+
	//		            				 '<td bgcolor=#F0F0F0>Actual Time: <b>'+rec.data.actual_time+'</b></td>'+
			            				 actual_time+
			            				 '<td bgcolor=#F0F0F0>Price: <b>'+(Math.round(rec.data.price*100)/100)+' '+proj_currency+'</b></td>'+
			            				 '<td bgcolor=#F0F0F0>Price(Euro): <b>'+myEuro+'</b></td>'+
			            				 '<td bgcolor=#F0F0F0>Description: <b>'+myDesc+'</b></td>'+
			            				 '<td><a href="javascript:editItemFunction('+myIndex+');"><font color=#B0B0B0><u>edit</u></font></a></td>';
	//		            				 '<td><a href="javascript:contactBilling();"><font color=#B0B0B0><u>delete</u></font></a></td></tr>';
			            				if(userDept == "Manager" || userDept == "Billing" || userDept == "IT"){
			            					myText += '<td><a href="javascript:deleteItem('+rec.data.proj_ref_id+');"><font color=#B0B0B0><u>delete</u></font></a></td></tr>';
			            				}else{
			            					myText += '<td><a href="javascript:contactBilling();"><font color=#B0B0B0><u>delete</u></font></a></td></tr>';
			            				}
		            				}
		            				 
		            			}
		            		})
		            		if(myText !== ""){
		            			return "<table cellspacing=8 class=\"myTable\">"+myText+"</table>";
		            		}else{
		            			return "<b>No Item Assign...</b>";
		            		}
		            	}
	        		}
	        )
	    }],
		bbar : Ext.create('Ext.PagingToolbar', {
			store : store.projects,
			displayInfo : true,
			displayMsg : 'Displaying Project {0} - {1} of {2}',
			emptyMsg : "No Project to display",
			plugins : Ext.create('Ext.ux.ProgressBarPager', {})
		})
	});

	Ext.Ajax.request({
		url : 'userModel.htm',
		success: function(response, opts){
			var responseOject = Ext.decode(response.responseText);
			userDept = responseOject.user[0].dept;
			userType = responseOject.user[0].usr_type;
		},
		failure: function(response, opts){
			var responseOject = Ext.util.JSON.decode(response.responseText);
			Ext.Msg.alert(responseOject.messageHeader, responseOject.message);
		}
	});
	
});

Ext.define('projRefModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'proj_ref_id',
		type : 'int'
	}, {
		name : 'proj_id',
		type : 'int'
	}, {
		name : 'itm_id',
		type : 'int'
	}, {
		name : 'cus_id',
		type : 'int'
	}, {
		name : 'file_id',
		type : 'int'
	}, {
		name : 'time',
		type : 'float'
	}, {
		name : 'actual_time',
		type : 'float'
	}, {
		name : 'price',
		type : 'float'
	}, {
		name : 'itm_name',
		type : 'string'
	}, {
		name : 'currency',
		type : 'string'
	}, {
		name : 'cus_name',
		type : 'string'
	}, {
		name : 'cus_code',
		type : 'string'
	}, {
		name : 'proj_ref_desc',
		type : 'string'
	}, {
		name : 'file_name',
		type : 'string'
	}, {
		name : 'topix_article_id',
		type : 'string'
	}, {
		name : 'activated',
		type : 'int'
	}

	]
});

store.projectsRef = Ext.create('Ext.data.JsonStore', {
	model : 'projRefModel',
	id : 'projRefStore',
//	pageSize : 9,
	autoLoad : true,
	proxy : {
		type : 'ajax',
		url : 'searchProjectsReference.htm',
		reader : {
			type : 'json',
			root : 'records',
			idProperty : 'proj_ref_id',
			totalProperty : 'total'
		}
	},
});

Ext.define('projModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'proj_id',
		type : 'int'
	}, {
		name : 'proj_name',
		type : 'string'
	}, {
		name : 'proj_desc',
		type : 'string'
	}, {
		name : 'cus_id',
		type : 'int'
	}, {
		name : 'file_id',
		type : 'int'
	}, {
		name : 'proj_count',
		type : 'int'
	}, {
		name : 'cus_name',
		type : 'string'
	}, {
		name : 'cus_code',
		type : 'string'
	}, {
		name : 'bill_to',
		type : 'string'
	}, {
		name : 'billing_terms',
		type : 'string'
	}, {
		name : 'proj_currency',
		type : 'string'
	}

	]
});

store.projects = Ext.create('Ext.data.JsonStore', {
	model : 'projModel',
	id : 'projStore',
	pageSize : 9,
//	autoLoad : true,
	proxy : {
		type : 'ajax',
		url : 'searchProjects.htm',
		reader : {
			type : 'json',
			root : 'records',
			idProperty : 'proj_id',
			totalProperty : 'total'
		}
	},
	listeners: {
		'load' : function(){
			for(var xyz=0;xyz<store.projects.count();xyz++){
				if(Ext.fly(panels.grid.plugins[0].view.getNodes()[xyz]).hasCls(panels.grid.plugins[0].rowCollapsedCls) == true){
					panels.grid.plugins[0].toggleRow(xyz, panels.grid.getStore().getAt(xyz));
				}
			}
		}
	}
});

Ext.Ajax.useDefaultXhrHeader = false;

Ext.define('exModel', {
	extend : 'Ext.data.Model',
	fields : [{
		name : 'USD',
		type : 'float'
	}, {
		name : 'EUR',
		type : 'float'
	}, {
		name : 'THB',
		type : 'float'
	}, {
		name : 'AUD',
		type : 'float'
	}, {
		name : 'GBP',
		type : 'float'
	}, {
		name : 'CHF',
		type : 'float'
	}, {
		name : 'SGD',
		type : 'float'
	}]
});

store.exchangeRates = Ext.create('Ext.data.JsonStore', {
	model: 'exModel',
	id : 'exStore',
	autoLoad : true,
	proxy : {
		type : 'ajax',
//		url : 'https://openexchangerates.org/api/latest.json?app_id=70ee2e9a9f814ea0a36bd0a00a11272c&base=EUR',
		url : 'https://openexchangerates.org/api/latest.json?app_id=973ee1daef2c4b268fe659f3e7d34b52',
		reader : {
			type : 'json',
			root : 'rates',
		}
	}
});

//store.exchangeRates = Ext.create('Ext.data.JsonPStore', {
//	model: 'exModel',
//	id : 'exStore',
//	autoLoad : true,
//	url : 'https://openexchangerates.org/api/latest.json?app_id=70ee2e9a9f814ea0a36bd0a00a11272c',
//	root : 'rates',
//	
//});

var currency = Ext.create('Ext.data.Store', {
    fields: ['currency','name'],
    data : [
        {"currency":"AUD", "name":"Australian Dollar[AUD]"},
        {"currency":"CHF", "name":"Swiss Franc[CHF]"},
        {"currency":"EUR", "name":"Euro[EUR]"},
        {"currency":"GBP", "name":"British Pound[GBP]"},
        {"currency":"THB", "name":"Thai Bath[THB]"},
        {"currency":"USD", "name":"US Dollar[USD]"},
        {"currency":"SGD", "name":"Singapore Dollar[SGD]"}
        //...
    ]
});

editProject = new Ext.create('Ext.window.Window', {
	title : 'Edit Project',
	width : 450,
	animateTarget : 'edit',
	modal : true,
	resizable : false,
	closeAction : 'hide',
	items : [ {
		xtype : 'form',
		id : 'editform',
		items:[{
        	xtype:'fieldset',
            title: 'Project Information',
            collapsible: true,
            defaultType: 'textfield',
            layout: 'anchor',
            padding: 10,
            width:400,
            style: {
                "margin-left": "auto",
                "margin-right": "auto",
                "margin-top": "10px",
                "margin-bottom": "auto"
            },
            defaults: {
                anchor: '100%'
            },
            items :[{
        	xtype:'textfield',
            allowBlank: false,
            fieldLabel: 'Project Name <font color="red">*</font> ',
            labelWidth: 120,
            name: 'eproj_name',
            id: 'eproj_name',
            msgTarget: 'under',
            emptyText: 'Project Name',
            maxLength : 30,
			maxLengthText : 'Maximum input 30 Character',
//			listeners: {
//          		 'blur': function(e){
//          			var proj_name = Ext.getCmp('eproj_name').getValue();
//          			var proj_id = Ext.getCmp('eproj_id').getValue();
//          			 Ext.Ajax.request({
//          				url : 'chkProjName.htm',
//          				params: {records : proj_name},
//          				success: function(response, opts){
//          					var responseOject = Ext.decode(response.responseText);
//          					if(responseOject.records[0].proj_id != 0){
//          						if(responseOject.records[0].proj_id != proj_id){
//	           						Ext.getCmp('eproj_name').setValue('');
//	           						Ext.getCmp('eproj_name').markInvalid('"'+proj_name+'" has been used');
//          						}
//          					}
//          				},
//          				failure: function(response, opts){
//          					var responseOject = Ext.util.JSON.decode(response.responseText);
//          					Ext.Msg.alert(responseOject.messageHeader, responseOject.message);
//          				}
//          			});
//          		 }
//          	 }
        },{
			xtype : 'combobox',
			fieldLabel : 'Customer Name <font color="red">*</font> ',
			name : 'ecus_name',
			id: 'ecus_name',
			allowBlank: false,
			queryMode : 'local',
			msgTarget: 'under',
			labelWidth : 120,
			emptyText : 'Customer Name',
			store : {
				fields : [ 'cus_id', 'cus_name', 'cus_code' ],
				proxy : {
					type : 'ajax',
					url : 'showCustomer.htm',
					reader : {
						type : 'json',
						root : 'records',
						idProperty : 'cus_id'
					}
				},
				autoLoad : true,
				sorters: [{
			         property: 'cus_name',
			         direction: 'ASC'
			     }]
			},
			valueField : 'cus_name',
			displayField : 'cus_name',
			listeners : {

				select : function() {
					
					var v = this.getValue();
					var record = this.findRecord(this.valueField || this.displayField, v);
					var myIndex = this.store.indexOf(record);
					var myValue = this.store.getAt(myIndex).data.cus_code;
					var myId = this.store.getAt(myIndex).data.cus_id;
					Ext.getCmp('ecus_id').setValue(myId);
					Ext.getCmp('ecus_code').setValue(myValue);
					
					console.log("cus_code: "+myValue);
				},
				blur : function() {
					var v = this.getValue();
					var record = this.findRecord(this.valueField || this.displayField, v);
					if(record !== false){
						var myIndex = this.store.indexOf(record);
						var myValue = this.store.getAt(myIndex).data.cus_name;
						var myId = this.store.getAt(myIndex).data.cus_id;
						Ext.getCmp('ecus_id').setValue(myId);
						Ext.getCmp('ecus_code').setValue(myValue);
					}else{
						Ext.getCmp('ecus_id').setValue("");
						Ext.getCmp('ecus_name').setValue("");
						Ext.getCmp('ecus_code').setValue("");
					}
				}

			}
		}, {
			xtype : 'combobox',
			fieldLabel : 'Customer Code <font color="red">*</font> ',
			name : 'ecus_code',
			id : 'ecus_code',
			allowBlank: false,
			queryMode : 'local',
			labelWidth : 120,
			msgTarget: 'under',
			emptyText : 'Customer Code',
			store : {
				fields : [ 'cus_id', 'cus_code', 'cus_name' ],
				proxy : {
					type : 'ajax',
					url : 'showCustomer.htm',
					reader : {
						type : 'json',
						root : 'records',
						idProperty : 'cus_id'
					}
				},
				autoLoad : true,
				sorters: [{
			         property: 'cus_code',
			         direction: 'ASC'
			     }]
			},
			valueField : 'cus_code',
			displayField : 'cus_code',
			listeners : {

				select : function() {
					
					var v = this.getValue();
					var record = this.findRecord(this.valueField || this.displayField, v);
					var myIndex = this.store.indexOf(record);
					var myValue = this.store.getAt(myIndex).data.cus_name;
					var myId = this.store.getAt(myIndex).data.cus_id;
					Ext.getCmp('ecus_id').setValue(myId);
					Ext.getCmp('ecus_name').setValue(myValue);
					
					console.log("cus_name: "+myValue);
				},
				blur : function() {
					var v = this.getValue();
					var record = this.findRecord(this.valueField || this.displayField, v);
					if(record !== false){
						var myIndex = this.store.indexOf(record);
						var myValue = this.store.getAt(myIndex).data.cus_name;
						var myId = this.store.getAt(myIndex).data.cus_id;
						Ext.getCmp('ecus_id').setValue(myId);
						Ext.getCmp('ecus_name').setValue(myValue);
					}else{
						Ext.getCmp('ecus_id').setValue("");
						Ext.getCmp('ecus_code').setValue("");
						Ext.getCmp('ecus_name').setValue("");
					}
				}

			}
		},{
			xtype:'combobox',
            fieldLabel: 'Currency <font color="red">*</font> ',
            labelWidth: 120,
            name: 'eproj_currency',
            id: 'eproj_currency',
            queryMode : 'local',
			labelWidth : 120,
			emptyText : 'Price Currency',
			editable : false,
			allowBlank: false,
			msgTarget : 'side',
			store : currency,
			valueField : 'currency',
			displayField : 'name',
		},{
	    	xtype:'filefield',
	    	labelWidth: 120,
	        fieldLabel: 'Briefing File ',
	        name: 'file',
	        id: 'efile',
	        msgTarget: 'side',
	        emptyText: 'Browse To Change...',
	        listeners: {
                change: function(fld, value) {
                    var newValue = value.replace(/C:\\fakepath\\/g, '');
                    fld.setRawValue(newValue);
                }
            }
	    },{
	    	xtype: 'textarea',
	    	labelWidth: 120,
	    	fieldLabel: 'Project Detail',
	    	name: 'eproj_desc',
	    	id: 'eproj_desc',
	    	emptyText: 'Project Details',
	    	maxLength : 500,
			maxLengthText : 'Maximum input 500 Character'
	    }]
        },{
			xtype : 'hidden',
			id : 'ecus_id',
			name : 'ecus_id'
		},{
			xtype : 'hidden',
			id : 'efile_id',
			name : 'efile_id'
		},{
			xtype : 'hidden',
			id : 'eproj_id',
			name : 'eproj_id'
		}]
	}],
	buttons : [
			{
				text : 'Update',
				width : 100,
				id : 'ejbtn',
				handler : function() {
					var form = Ext.getCmp('editform').getForm();
					if (form.isValid()) {
						form.submit({
							url : 'updateProjects.htm',
							waitTitle : 'Update Project',
							waitMsg : 'Please wait...',
							standardSubmit : false,
							failure : function(form, action) {
								Ext.MessageBox.show({
									title : 'Information',
									msg : 'Project Has Been Updated!',
									buttons : Ext.MessageBox.OK,
									icon : Ext.MessageBox.INFO,
									animateTarget : 'ejbtn',
									fn : function() {
										editProject.hide();
										store.projectsRef.reload();
										setTimeout(function(){store.projects.reload()},500);
									}
								});
							}
						});
					} else {
						Ext.MessageBox.show({
							title : 'Failed',
							msg : ' Please Insert All Required Field',
							buttons : Ext.MessageBox.OK,
							icon : Ext.MessageBox.ERROR,
							animateTarget : 'ejbtn',
						});
					}
				}
			},{
				text : 'Reset',
				width : 100,
				handler : function() {
					Ext.getCmp('editform').getForm().reset();
				}
			} ],
	listeners : {
		'beforehide' : function() {
			Ext.getCmp('editform').getForm().reset();
		}
	}
});

addItem = new Ext.create('Ext.window.Window', {
	title: 'Add Item',
    animateTarget: 'add',
    modal : true,
    resizable:false,
    width: 450,
    closeAction: 'hide',
    items :[{
    	xtype:'form',
        id:'addItemForm',
        items:[{
    		xtype:'fieldset',
            title: 'Item Information',
            defaultType: 'textfield',
            layout: 'anchor',
            padding: 10,
            width:400,
            style: {
                "margin-left": "auto",
                "margin-right": "auto",
                "margin-top": "10px",
                "margin-bottom": "10px"
            },
            defaults: {
                anchor: '100%'
            },
            items :[{
				xtype: 'combobox',
				fieldLabel : 'Item Name <font color="red">*</font> ',
				name : 'aitm_id',
				id : 'aitm_id',
				allowBlank: false,
				queryMode : 'local',
				labelWidth : 120,
				msgTarget: 'under',
				emptyText : 'Item Name',
				store : {
					fields : [ 'itm_id', 'itm_name' ],
					proxy : {
						type : 'ajax',
						url : 'showItem.htm?id=0',
						reader : {
							type : 'json',
							root : 'records',
							idProperty : 'itm_id'
						}
					},
					autoLoad : true,
					sorters: [{
				         property: 'itm_name',
				         direction: 'ASC'
				     }]
				},
				valueField : 'itm_id',
				displayField : 'itm_name',
				listeners : {
					blur : function() {
						var v = this.getValue();
						var record = this.findRecord(this.valueField || this.displayField, v);
						if(record == false){
							Ext.getCmp('aitm_id').setValue("");
						}
					}
				}
			},{
    	    	xtype:'numberfield',
    	    	labelWidth: 120,
    	    	fieldLabel: 'Target Time(mins) ',
    	    	emptyText : 'Time in minutes',
    	    	minValue : 0,
    	    	msgTarget : 'under',
    	    	name: 'atime',
    	    	id: 'atime'
    	    },{
    	    	xtype:'numberfield',
    	    	labelWidth: 120,
    	    	fieldLabel: 'Actual Time(mins) ',
    	    	emptyText : 'Time in minutes',
    	    	minValue : 0,
    	    	msgTarget : 'under',
    	    	name: 'aactual_time',
    	    	id: 'aactual_time'
    	    },{
    	    	xtype:'numberfield',
    	    	labelWidth: 120,
    	    	fieldLabel: 'Price ',
//    	    	minValue : 0,
    	    	msgTarget : 'under',
    	    	name: 'aprice',
    	    	id: 'aprice',
    	    	emptyText : 'Project Price',
//    	    	listeners: {
//    	            change: function(field, value) {
//    	                if(value == null || value == 0){
//    	                	Ext.getCmp('acurrency').clearInvalid();
//    	                	Ext.getCmp('acurrency').allowBlank = true;
//    	                }else{
//    	                	if(Ext.getCmp('acurrency').value == null){
//	    	                	Ext.getCmp('acurrency').markInvalid('Currency Required!');
//	    	                	Ext.getCmp('acurrency').allowBlank = false;
//    	                	}
//    	                }
//    	            }
//    	        }
//    	    },{
//				xtype : 'combobox',
//				fieldLabel : 'Currency ',
//				name : 'acurrency',
//				id : 'acurrency',
//				queryMode : 'local',
//				labelWidth : 120,
//				emptyText : 'Price Currency',
//				editable : false,
//				msgTarget: 'under',
//				store : currency,
//				valueField : 'currency',
//				displayField : 'name',
			},{
    	    	xtype: 'textarea',
    	    	labelWidth: 120,
    	    	fieldLabel: 'Item Detail',
    	    	name: 'aproj_ref_desc',
    	    	id: 'aproj_ref_desc',
    	    	emptyText: 'Item Details',
    	    	maxLength : 100,
				maxLengthText : 'Maximum input 100 Character',
    	    },{
    	    	labelWidth: 120,
    	    	fieldLabel: 'Article ID(Topix) ',
    	    	name: 'atopix_article_id',
    	    	id: 'atopix_article_id',
    	    	emptyText: 'Article ID(Topix)'
    	    },{
    	    	xtype:'radiogroup',
    	    	labelWidth: 120,
    	    	fieldLabel: 'Activated ',
    	    	name: 'aproj_ref_activated',
    	    	id: 'aproj_ref_activated',
    	    	items: [{
                    inputValue: '1',
                    boxLabel: 'Yes',
                    name: 'aproj_ref_activated',
                    checked: true
                }, {
                    inputValue: '0',
                    boxLabel: 'No',
                    name: 'aproj_ref_activated'
                }]
    	    }]
            },{
				xtype : 'hidden',
				id : 'aproj_id',
				name : 'aproj_id'
            }]
    		}],
            buttons:[{	
           		  text: 'Add',
          		  width:100,
          		  id: 'abtn',
                 handler: function(){
                	 var form = Ext.getCmp('addItemForm').getForm();
                	 if(form.isValid()){
        				 form.submit({
        				 url: 'createProjectsReference.htm',
        				 waitTitle: 'Adding Item',
        				 waitMsg: 'Please wait...',
        				 standardSubmit: false,
                         success: function(form, action) {
                        	 Ext.MessageBox.show({
          						title: 'Information',
          						msg: 'Item Has Been Add!',
          						buttons: Ext.MessageBox.OK,
          						icon: Ext.MessageBox.INFO,
          						animateTarget: 'abtn',
          						fn: function(){
          							addItem.hide();
          							store.projectsRef.reload();
          							setTimeout(function(){store.projects.reload()},500);
          							}
          					});
                            },
                            failure : function(form, action) {
//								Ext.Msg.alert('Failed',
//										action.result ? action.result.message
//												: 'No response');
                            	Ext.MessageBox.show({
                                    title: 'REMOTE EXCEPTION',
                                    msg: operation.getError(),
                                    icon: Ext.MessageBox.ERROR,
                                    buttons: Ext.Msg.OK,
                                    fn: function(){location.reload()}
                                });
							}
              			});
                	 }else {
        					Ext.MessageBox.show({
        						title: 'Failed',
        						msg: ' Please Insert All Required Field',
        						buttons: Ext.MessageBox.OK,
        						icon: Ext.MessageBox.ERROR,
        						animateTarget: 'abtn',
        					});
        				}
        		}
               	},{
                	text: 'Reset',
                	width:100,
                	handler: function(){
                		Ext.getCmp('addItemForm').getForm().reset();
                	}
                }],
               	listeners:{
               		'beforehide':function(){
               			Ext.getCmp('addItemForm').getForm().reset();
               		}
               	}
});

addProject = new Ext.create('Ext.window.Window', {
	title: 'Create Project',
    width: 500,
    animateTarget: 'icreate',
    modal : true,
    resizable:false,
    closeAction: 'hide',
    defaults: {
        anchor: '100%'
    },
    items :[{
            xtype:'form',
            id:'projectsform',
            items:[{
            	xtype:'fieldset',
                title: 'Project Information',
                collapsible: true,
                defaultType: 'textfield',
                layout: 'anchor',
                padding: 10,
                width:400,
                style: {
                    "margin-left": "auto",
                    "margin-right": "auto",
                    "margin-top": "10px",
                    "margin-bottom": "auto"
                },
                defaults: {
                    anchor: '100%'
                },
                items :[{
            	xtype:'textfield',
                allowBlank: false,
                fieldLabel: 'Project Name <font color="red">*</font> ',
                labelWidth: 120,
                name: 'cproj_name',
                id: 'cproj_name',
                msgTarget: 'under',
                emptyText: 'Project Name',
//                listeners: {
//             		 'blur': function(e){
//             			var proj_name = Ext.getCmp('cproj_name').getValue();
//             			 Ext.Ajax.request({
//             				url : 'chkProjName.htm',
//             				params: {records : proj_name},
//             				success: function(response, opts){
//             					var responseOject = Ext.decode(response.responseText);
//             					if(responseOject.records[0].proj_id != 0){
//   	           						Ext.getCmp('cproj_name').setValue('');
//   	           						Ext.getCmp('cproj_name').markInvalid('"'+proj_name+'" has been used');
//             					}
//             				},
//             				failure: function(response, opts){
//             					var responseOject = Ext.util.JSON.decode(response.responseText);
//             					Ext.Msg.alert(responseOject.messageHeader, responseOject.message);
//             				}
//             			});
//             		 }
//             	 }
            },{
				xtype : 'combobox',
				fieldLabel : 'Customer Name <font color="red">*</font> ',
				name : 'ccus_name',
				id: 'ccus_name',
				allowBlank: false,
				queryMode : 'local',
				msgTarget: 'under',
				labelWidth : 120,
				emptyText : 'Customer Name',
				store : {
					fields : [ 'cus_id', 'cus_name', 'cus_code' ],
					proxy : {
						type : 'ajax',
						url : 'showCustomer.htm',
						reader : {
							type : 'json',
							root : 'records',
							idProperty : 'cus_id'
						}
					},
					autoLoad : true,
					sorters: [{
				         property: 'cus_name',
				         direction: 'ASC'
				     }]
				},
				valueField : 'cus_name',
				displayField : 'cus_name',
				listeners : {

					select : function() {
						
						var v = this.getValue();
						var record = this.findRecord(this.valueField || this.displayField, v);
						var myIndex = this.store.indexOf(record);
						var myValue = this.store.getAt(myIndex).data.cus_code;
						var myId = this.store.getAt(myIndex).data.cus_id;
						Ext.getCmp('ccus_id').setValue(myId);
						Ext.getCmp('ccus_code').setValue(myValue);
						
						console.log("cus_code: "+myValue);
					},
					blur : function() {
						var v = this.getValue();
						var record = this.findRecord(this.valueField || this.displayField, v);
						if(record !== false){
							var myIndex = this.store.indexOf(record);
							var myValue = this.store.getAt(myIndex).data.cus_code;
							var myId = this.store.getAt(myIndex).data.cus_id;
							Ext.getCmp('ccus_id').setValue(myId);
							Ext.getCmp('ccus_code').setValue(myValue);
						}else{
							Ext.getCmp('ccus_id').setValue("");
							Ext.getCmp('ccus_name').setValue("");
							Ext.getCmp('ccus_code').setValue("");
						}
					}

				}
			}, {
				xtype : 'combobox',
				fieldLabel : 'Customer Code <font color="red">*</font> ',
				name : 'ccus_code',
				id : 'ccus_code',
				allowBlank: false,
				queryMode : 'local',
				labelWidth : 120,
				msgTarget: 'under',
				emptyText : 'Customer Code',
				store : {
					fields : [ 'cus_id', 'cus_code', 'cus_name' ],
					proxy : {
						type : 'ajax',
						url : 'showCustomer.htm',
						reader : {
							type : 'json',
							root : 'records',
							idProperty : 'cus_id'
						}
					},
					autoLoad : true,
					sorters: [{
				         property: 'cus_code',
				         direction: 'ASC'
				     }]
				},
				valueField : 'cus_code',
				displayField : 'cus_code',
				listeners : {

					select : function() {
						
						var v = this.getValue();
						var record = this.findRecord(this.valueField || this.displayField, v);
						var myIndex = this.store.indexOf(record);
						var myValue = this.store.getAt(myIndex).data.cus_name;
						var myId = this.store.getAt(myIndex).data.cus_id;
						Ext.getCmp('ccus_id').setValue(myId);
						Ext.getCmp('ccus_name').setValue(myValue);
						
						console.log("cus_name: "+myValue);
					},
					blur : function() {
						var v = this.getValue();
						var record = this.findRecord(this.valueField || this.displayField, v);
						if(record !== false){
							var myIndex = this.store.indexOf(record);
							var myValue = this.store.getAt(myIndex).data.cus_name;
							var myId = this.store.getAt(myIndex).data.cus_id;
							Ext.getCmp('ccus_id').setValue(myId);
							Ext.getCmp('ccus_name').setValue(myValue);
						}else{
							Ext.getCmp('ccus_id').setValue("");
							Ext.getCmp('ccus_code').setValue("");
							Ext.getCmp('ccus_name').setValue("");
						}
					}

				}
			},{
				xtype:'combobox',
                fieldLabel: 'Currency <font color="red">*</font> ',
                labelWidth: 120,
                name: 'cproj_currency',
                id: 'cproj_currency',
                queryMode : 'local',
    			labelWidth : 120,
    			emptyText : 'Price Currency',
    			editable : false,
    			allowBlank: false,
    			msgTarget : 'side',
    			store : currency,
    			valueField : 'currency',
    			displayField : 'name',
			},{
    	    	xtype:'filefield',
    	    	labelWidth: 120,
    	        fieldLabel: 'Briefing File ',
    	        name: 'file',
    	        id: 'file',
    	        msgTarget: 'side',
    	        emptyText: 'Browse File...',
    	        listeners: {
                    change: function(fld, value) {
                        var newValue = value.replace(/C:\\fakepath\\/g, '');
                        fld.setRawValue(newValue);
                    }
                }
    	    },{
    	    	xtype: 'textarea',
    	    	labelWidth: 120,
    	    	fieldLabel: 'Project Detail',
    	    	name: 'cproj_desc',
    	    	id: 'cproj_desc',
    	    	emptyText: 'Project Details',
    	    	maxLength : 500,
				maxLengthText : 'Maximum input 500 Character',
    	    }]
            },{
            id: 'itmf',
    	    xtype:'fieldset',
            checkboxToggle:true,
            title: 'Item Information',
            defaultType: 'textfield',
            collapsed: true,
            layout: 'anchor',
            padding: 10,
            width:400,
            style: {
                "margin-left": "auto",
                "margin-right": "auto",
                "margin-top": "10px",
                "margin-bottom": "10px"
            },
            defaults: {
                anchor: '100%'
            },
            items :[{
				xtype: 'combobox',
				fieldLabel : 'Item Name <font color="red">*</font> ',
				name : 'citm_id',
				id : 'citm_id',
//				allowBlank: false,
				queryMode : 'local',
				labelWidth : 120,
				msgTarget: 'under',
				emptyText : 'Item Name',
				store : {
					fields : [ 'itm_id', 'itm_name' ],
					proxy : {
						type : 'ajax',
						url : 'showItem.htm?id=0',
						reader : {
							type : 'json',
							root : 'records',
							idProperty : 'itm_id'
						}
					},
					autoLoad : true,
					sorters: [{
				         property: 'itm_name',
				         direction: 'ASC'
				     }]
				},
				valueField : 'itm_id',
				displayField : 'itm_name',
				listeners : {
					blur : function() {
						var v = this.getValue();
						var record = this.findRecord(this.valueField || this.displayField, v);
						if(record == false){
							Ext.getCmp('citm_id').setValue("");
						}
					}
				}
			},{
    	    	xtype:'numberfield',
    	    	labelWidth: 120,
    	    	fieldLabel: 'Target Time(mins) ',
    	    	emptyText : 'Time in minutes',
    	    	minValue : 0,
    	    	msgTarget : 'under',
    	    	name: 'ctime',
    	    	id: 'ctime',
    	    },{
    	    	xtype:'numberfield',
    	    	labelWidth: 120,
    	    	fieldLabel: 'Actual Time(mins) ',
    	    	emptyText : 'Time in minutes',
    	    	minValue : 0,
    	    	msgTarget : 'under',
    	    	name: 'cactual_time',
    	    	id: 'cactual_time',
    	    },{
    	    	xtype:'numberfield',
    	    	labelWidth: 120,
    	    	fieldLabel: 'Price ',
//    	    	minValue : 0,
    	    	msgTarget : 'under',
    	    	name: 'cprice',
    	    	id: 'cprice',
    	    	emptyText : 'Project Price',
    	    	listeners: {
    	            change: function(field, value) {
    	                if(value == null || value == 0){
    	                	Ext.getCmp('ccurrency').clearInvalid();
    	                	Ext.getCmp('ccurrency').allowBlank = true;
    	                }else{
    	                	if(Ext.getCmp('ccurrency').value == null){
	    	                	Ext.getCmp('ccurrency').markInvalid('Currency Required!');
	    	                	Ext.getCmp('ccurrency').allowBlank = false;
    	                	}
    	                }
    	            }
    	        }
//    	    	emptyText: 'java,art,animal,etc...'
//    	    },{
//				xtype : 'combobox',
//				fieldLabel : 'Currency ',
//				name : 'ccurrency',
//				id : 'ccurrency',
//				queryMode : 'local',
//				labelWidth : 120,
//				emptyText : 'Price Currency',
//				editable : false,
//				msgTarget : 'side',
//				store : currency,
//				valueField : 'currency',
//				displayField : 'name',
			},{
    	    	xtype: 'textarea',
    	    	labelWidth: 120,
    	    	fieldLabel: 'Item Detail',
    	    	name: 'cproj_ref_desc',
    	    	id: 'cproj_ref_desc',
    	    	emptyText: 'Item Details',
    	    	maxLength : 100,
				maxLengthText : 'Maximum input 100 Character',
    	    },{
    	    	labelWidth: 120,
    	    	fieldLabel: 'Article ID(Topix) ',
    	    	name: 'ctopix_article_id',
    	    	id: 'ctopix_article_id',
    	    	emptyText: 'Article ID(Topix)'
    	    },{
    	    	xtype:'radiogroup',
    	    	labelWidth: 120,
    	    	fieldLabel: 'Activated ',
    	    	name: 'cproj_ref_activated',
    	    	id: 'cproj_ref_activated',
    	    	items: [{
                    inputValue: '1',
                    boxLabel: 'Yes',
                    name: 'cproj_ref_activated',
                    checked: true
                }, {
                    inputValue: '0',
                    boxLabel: 'No',
                    name: 'cproj_ref_activated'
                }]
    	    }]
            },{
				xtype : 'hidden',
				id : 'ccus_id',
				name : 'ccus_id'
			}],
    }],
    buttons:[{	
   		  text: 'Add',
  		  width:100,
  		  id: 'btn',
         handler: function(){
        	 var chkBox = Ext.getCmp('itmf').checkboxCmp.getValue();
        	 var form = Ext.getCmp('projectsform').getForm();
        	 if(chkBox){
        		 if(Ext.getCmp('citm_id').getValue() == null){
        			 Ext.getCmp('citm_id').markInvalid('Item Required!');
        		 }
        	 }else{
        		 Ext.getCmp('citm_id').clearInvalid();
        		 Ext.getCmp('citm_id').setValue('');
        		 Ext.getCmp('ctime').setValue('');
        		 Ext.getCmp('cactual_time').setValue('');
        		 Ext.getCmp('cprice').setValue('');
//        		 Ext.getCmp('ccurrency').setValue('');
        		 Ext.getCmp('cproj_ref_desc').setValue('');
        	 }
        	 if(form.isValid()){
				 form.submit({
				 url: 'createProjects.htm',
				 waitTitle: 'Creating Project',
				 waitMsg: 'Please wait...',
				 standardSubmit: true,
                 failure: function(form, action) {
                	 Ext.MessageBox.show({
  						title: 'Information',
  						msg: 'Project Has Been Created!',
  						buttons: Ext.MessageBox.OK,
  						icon: Ext.MessageBox.INFO,
  						animateTarget: 'btn',
  						fn: function(){addProject.hide();}
  					});
                    }
      			});
        	 }else {
					Ext.MessageBox.show({
						title: 'Failed',
						msg: ' Please Insert All Required Field',
						buttons: Ext.MessageBox.OK,
						icon: Ext.MessageBox.ERROR,
						animateTarget: 'btn',
						fn: function(){
							if(chkBox){
								if(Ext.getCmp('citm_id').getValue() == null){
									Ext.getCmp('citm_id').markInvalid('Item Required!');
								}
							}
						}
					});
				}
		}
       	},{
        	text: 'Reset',
        	width:100,
        	handler: function(){
        		Ext.getCmp('projectsform').getForm().reset();
        	}
        }],
       	listeners:{
       		'beforehide':function(){
       			Ext.getCmp('projectsform').getForm().reset();
       		}
       	}
	});


editItem = new Ext.create('Ext.window.Window', {
	title : 'Edit Item',
	width : 450,
	animateTarget : 'edit',
	modal : true,
	resizable : false,
	closeAction : 'hide',
	items :[{
    	xtype:'form',
        id:'editItemForm',
        items:[{
    		xtype:'fieldset',
            title: 'Item Information',
            defaultType: 'textfield',
            layout: 'anchor',
            padding: 10,
            width:400,
            style: {
                "margin-left": "auto",
                "margin-right": "auto",
                "margin-top": "10px",
                "margin-bottom": "10px"
            },
            defaults: {
                anchor: '100%'
            },
            items :[{
				xtype: 'combobox',
				fieldLabel : 'Item Name <font color="red">*</font> ',
				name : 'eitm_id',
				id : 'eitm_id',
				allowBlank: false,
				queryMode : 'local',
				labelWidth : 120,
				msgTarget: 'under',
				emptyText : 'Item Name',
				store : {
					fields : [ 'itm_id', 'itm_name' ],
					proxy : {
						type : 'ajax',
						url : 'showItem.htm?id=0',
						reader : {
							type : 'json',
							root : 'records',
							idProperty : 'itm_id'
						}
					},
					autoLoad : true,
					sorters: [{
				         property: 'itm_name',
				         direction: 'ASC'
				     }]
				},
				valueField : 'itm_id',
				displayField : 'itm_name',
				listeners : {
					blur : function() {
						var v = this.getValue();
						var record = this.findRecord(this.valueField || this.displayField, v);
						if(record == false){
							Ext.getCmp('eitm_id').setValue("");
						}
					}
				}
			},{
    	    	xtype:'numberfield',
    	    	labelWidth: 120,
    	    	fieldLabel: 'Target Time(mins) ',
    	    	emptyText : 'Time in minutes',
    	    	minValue : 0,
    	    	msgTarget : 'under',
    	    	name: 'etime',
    	    	id: 'etime',
    	    },{
    	    	xtype:'numberfield',
    	    	labelWidth: 120,
    	    	fieldLabel: 'Actual Time(mins) ',
    	    	emptyText : 'Time in minutes',
    	    	minValue : 0,
    	    	msgTarget : 'under',
    	    	name: 'eactual_time',
    	    	id: 'eactual_time',
    	    },{
    	    	xtype:'numberfield',
    	    	labelWidth: 120,
    	    	fieldLabel: 'Price ',
//    	    	minValue : 0,
    	    	msgTarget : 'under',
    	    	name: 'eprice',
    	    	id: 'eprice',
    	    	emptyText : 'Project Price',
//    	    	listeners: {
//    	            change: function(field, value) {
//    	                if(value == null || value == 0){
//    	                	Ext.getCmp('ecurrency').clearInvalid();
//    	                	Ext.getCmp('ecurrency').allowBlank = true;
//    	                }else{
//    	                	if(Ext.getCmp('ecurrency').value == null){
//    	                	Ext.getCmp('ecurrency').markInvalid('Currency Required!');
//    	                	Ext.getCmp('ecurrency').allowBlank = false;
//    	                	}
//    	                }
//    	            }
//    	        }
//    	    },{
//				xtype : 'combobox',
//				fieldLabel : 'Currency ',
//				name : 'ecurrency',
//				id : 'ecurrency',
//				queryMode : 'local',
//				labelWidth : 120,
//				emptyText : 'Price Currency',
//				editable : false,
//				msgTarget : 'side',
//				store : currency,
//				valueField : 'currency',
//				displayField : 'name',
			},{
    	    	xtype: 'textarea',
    	    	labelWidth: 120,
    	    	fieldLabel: 'Item Detail',
    	    	name: 'eproj_ref_desc',
    	    	id: 'eproj_ref_desc',
    	    	emptyText: 'Item Details',
    	    	maxLength : 100,
				maxLengthText : 'Maximum input 100 Character'
    	    },{
    	    	labelWidth: 120,
    	    	fieldLabel: 'Article ID(Topix) ',
    	    	name: 'etopix_article_id',
    	    	id: 'etopix_article_id',
    	    	emptyText: 'Article ID(Topix)'
    	    },{
    	    	xtype:'radiogroup',
    	    	labelWidth: 120,
    	    	fieldLabel: 'Activated ',
    	    	name: 'eproj_ref_activated',
    	    	id: 'eproj_ref_activated',
    	    	items: [{
                    inputValue: '1',
                    boxLabel: 'Yes',
                    name: 'eproj_ref_activated',
                    checked: true
                }, {
                    inputValue: '0',
                    boxLabel: 'No',
                    name: 'eproj_ref_activated'
                }]
    	    }]
            },{
				xtype : 'hidden',
				id : 'eproj_ref_id',
				name : 'eproj_ref_id'
        }]
	}],
	buttons : [
			{
				text : 'Update',
				width : 100,
				id : 'ebtn',
				handler : function() {
					var form = Ext.getCmp('editItemForm').getForm();
					if (form.isValid()) {
						form.submit({
							url : 'updateProjectsReference.htm',
							waitTitle : 'Update Item',
							waitMsg : 'Please wait...',
							standardSubmit : false,
							success : function(form, action) {
								Ext.MessageBox.show({
									title : 'Information',
									msg : 'Item Has Been Updated!',
									buttons : Ext.MessageBox.OK,
									icon : Ext.MessageBox.INFO,
									animateTarget : 'ebtn',
									fn : function() {
										editItem.hide();
										store.projectsRef.reload();
										setTimeout(function(){store.projects.reload()},500);
									}
								});
							},
							failure : function(form, action) {
//								Ext.Msg.alert('Failed',
//										action.result ? action.result.message
//												: 'No response');
								Ext.MessageBox.show({
				                    title: 'REMOTE EXCEPTION',
				                    msg: action.result ? action.result.message
											: 'No response',
				                    icon: Ext.MessageBox.ERROR,
				                    buttons: Ext.Msg.OK,
				                    fn: function(){location.reload()}
				                });
							}
						});
					} else {
						Ext.MessageBox.show({
							title : 'Failed',
							msg : ' Please Insert All Required Field',
							buttons : Ext.MessageBox.OK,
							icon : Ext.MessageBox.ERROR,
							animateTarget : 'ebtn',
						});
					}
				}
			},{
				text : 'Reset',
				width : 100,
				handler : function() {
					Ext.getCmp('editItemForm').getForm().reset();
				}
			} ],
	listeners : {
		'beforehide' : function() {
			Ext.getCmp('editItemForm').getForm().reset();
		}
	}
});

function editItemFunction(v){
	var myData = store.projectsRef.getAt(v);
	Ext.getCmp('eitm_id').setValue(myData.data.itm_id);
	Ext.getCmp('etime').setValue(myData.data.time);
	Ext.getCmp('eactual_time').setValue(myData.data.actual_time);
	Ext.getCmp('eprice').setValue(myData.data.price);
//	Ext.getCmp('ecurrency').setValue(myData.data.currency);
	Ext.getCmp('eproj_ref_desc').setValue(myData.data.proj_ref_desc);
	Ext.getCmp('eproj_ref_id').setValue(myData.data.proj_ref_id);
	Ext.getCmp('etopix_article_id').setValue(myData.data.topix_article_id);
	Ext.getCmp('eproj_ref_activated').setValue({eproj_ref_activated:myData.data.activated});
	editItem.show();
}

function deleteItem(proj_ref_id){
	Ext.MessageBox.show({
		title : 'Confirm',
		msg : 'Are you sure you want to delete this?',
		buttons : Ext.MessageBox.YESNO,
		animateTarget : 'del',
		icon : Ext.MessageBox.QUESTION,
		fn : function(btn){
			if (btn === 'yes') {
				Ext.Ajax.request({
					url : 'deleteProjectsReference.htm',
					params : {
						id : proj_ref_id
					},
					success : function(response, opts) {
						Ext.MessageBox.show({
							title : 'Infomation',
							msg : 'Item has been deleted!',
							buttons : Ext.MessageBox.OK,
							animateTarget : 'del',
							fn : function(){
								store.projectsRef.reload();
								setTimeout(function(){store.projects.reload()},500);
							},
							icon : Ext.MessageBox.INFO
						});
					},
					failure : function(response, opts) {
						store.projectsRef.reload();
						setTimeout(function(){store.projects.reload()},500);
						var responseOject = Ext.util.JSON
								.decode(response.responseText);
						Ext.Msg.alert(responseOject.messageHeader,
								responseOject.message);
					}
				});
	        }
		}
		
	});
}

function contactBilling(){
	Ext.MessageBox.show({
		title : 'Information',
		msg : 'Please contact Billing Department for delete !',
		buttons : Ext.MessageBox.OK,
		animateTarget : 'del',
		icon : Ext.MessageBox.INFO
	});
}

function confirmChk(btn) {
	if (btn == "yes") {
		Ext.Ajax.request({
					url : 'deleteProjects.htm',
					params : {
						id : Ext.getCmp('projid').getValue(),
						fid : Ext.getCmp('fid').getValue()
					},
					success : function(response, opts) {
						Ext.MessageBox.show({
							title : 'Infomation',
							msg : 'Project has been deleted!',
							buttons : Ext.MessageBox.OK,
							animateTarget : 'del',
							fn : function(){
								store.projectsRef.reload();
								store.projects.reload();
							},
							icon : Ext.MessageBox.INFO
						});
					},
					failure : function(response, opts) {
						var responseOject = Ext.util.JSON
								.decode(response.responseText);
						Ext.Msg.alert(responseOject.messageHeader,
								responseOject.message);
					}
				});
	}
}

function getParamValues() {
	var url = "";
	var param = "";
	var prefix = "&";
	var queryStr = "";
	var i = 1;
	var count = 0;

	for (param in panels.search.getValues()) {

		count += panels.search.getValues()[param].length;

		if (i == 1) {
			queryStr += param + "=" + panels.search.getValues()[param];
		} else {
			queryStr += "&" + param + "=" + panels.search.getValues()[param];
		}

		i++;
	}

	if (count == 0) {
		url = "";
	} else {
		url = prefix + queryStr;
	}

	return encodeURI(url);
}

function combinecols(value, meta, record, rowIndex, colIndex, store) {
	if(record.get('currency') !== ""){
		return record.get('currency')+": "+(Math.round(record.get('price')*100)/100);
	}else{
		return record.get('price');
	}
}

function combinecols2(value, meta, record, rowIndex, colIndex, store) {
    return record.get('cus_name')+' - '+record.get('cus_code');
}

function renderTime(value, meta, record, rowIndex, colIndex, store) {
    return record.get('time')+' mins';
} 

function currencyName(value, meta, record, rowIndex, colIndex, store) {
	
	var myCurrency = record.get('proj_currency');
	var fullName = "";
	if(myCurrency == "AUD"){
		fullName = "Australian Dollar[AUD]";
	}else if(myCurrency == "CHF"){
		fullName = "Swiss Franc[CHF]";
	}else if(myCurrency == "EUR"){
		fullName = "Euro[EUR]";
	}else if(myCurrency == "GBP"){
		fullName = "British Pound[GBP]";
	}else if(myCurrency == "THB"){
		fullName = "Thai Bath[THB]";
	}else if(myCurrency == "USD"){
		fullName = "US Dollar[USD]";
	}else if(myCurrency == "SGD"){
		fullName = "Singapore Dollar[SGD]";
	}
	
	return fullName;
} 

//function currencyToEuro(value, meta, record, rowIndex, colIndex) {
//	if(record.get('currency') == "USD"){
//    return '€ '+(Math.round(((Math.round(record.get('price')*100)/100)*store.exchangeRates.getAt(0).data.EUR)*10000)/10000);
//	}else if(record.get('currency') == "EUR"){
//		return '€ '+(Math.round(record.get('price')*100)/100);
//	}else if(record.get('currency') == "THB"){
//		return '€ '+(Math.round(((Math.round(record.get('price')*100)/100)/store.exchangeRates.getAt(0).data.THB*store.exchangeRates.getAt(0).data.EUR)*10000)/10000);
//	}else if(record.get('currency') == "AUD"){
//		return '€ '+(Math.round(((Math.round(record.get('price')*100)/100)/store.exchangeRates.getAt(0).data.AUD*store.exchangeRates.getAt(0).data.EUR)*10000)/10000);
//	}else if(record.get('currency') == "GBP"){
//		return '€ '+(Math.round(((Math.round(record.get('price')*100)/100)/store.exchangeRates.getAt(0).data.GBP*store.exchangeRates.getAt(0).data.EUR)*10000)/10000);
//	}else if(record.get('currency') == "CHF"){
//		return '€ '+(Math.round(((Math.round(record.get('price')*100)/100)/store.exchangeRates.getAt(0).data.CHF*store.exchangeRates.getAt(0).data.EUR)*10000)/10000);
//	}else{
//		return '-';
//	}
//} 