PCSubnetwork = function () {
	console.log(subnet_type);
    this.box = new ElementBox();
    this.network = demo.Util.createDraggableNetwork();
    this.sheet = new PropertySheet();
    this.autoLayouter = new twaver.layout.AutoLayouter(this.box);
    this.springLayouter = new twaver.layout.SpringLayouter(this.network);
    this.springLayouter.setStepCount(100);
    
    images.registImages(this.network);

    this.nodeColor = '#FF0000';
    this.linkColor = '#66CCFF';
    this.nodeOverColor = '#FFFF00';
    this.linkOverColor = '#FF00FF';
    this.selectColor = '#00FF00';
    this.clickObj = null;
    this.dialog = null;
    this.showLayerId="";
    this.layerRelation = new Object();
    //定义存储每个子网的BOX数据
    this.allBoxObj= new Object();
    //用于存储network的属性，现在存储的是zoom缩放级别，用于子网切换时保持拓扑图不变
    this.netWorkParamObj= new Object();
    this.lastData;
    this.lastPoint;
    this.dbSelectSubnetWork=null;
    this.searchLabel = null;
    this.highLightArr = new Array();

};
twaver.Util.ext('PCSubnetwork', Object, {
    init: function () {
        var self = this;
        var toolbar = demo.Util.createNetworkToolbar3(this.network,function() {
        	self.showLastLayer(); 
          }
        )

        //round 圆形布局 symmetry 对称布局 hierarchic 层次布局 topbottom 从上到下布局 bottomtop 从下到上布局 rightleft 从右到左布局 leftright 从左到右布局
//        var items = ['请选择布局方式','对称布局', '层次布局', '圆形布局', '从上到下布局', '从下到上布局', '从右到左布局', '从左到右布局'];
//        var layoutSelect = demo.Util.addComboBox(toolbar, items, function () {
//        	console.log(layoutSelect.value);
//        	self.changeLayoutBox(layoutSelect.value);
//        },'');
        
        searchLabel = demo.Util.addInputNoLabel2(toolbar, '', 'highlightQuery', function () {
        	console.log(searchLabel.value);
        });
        var queryNode = demo.Util.addButton2(toolbar, '查询', null, function () {
        	console.log(searchLabel.value);
        	self.searchNodeInfo(searchLabel.value);
        });
        
        var main = document.getElementById('main');
//        demo.Util.appendChild(toolbar, main, 0, 0, null, 0);
        var borderBane = new BorderPane(this.network, toolbar);
        demo.Util.appendChild(borderBane.getView(), main, 20, 0, 0, 0);
//        this.sheet.getView().style.width = '250px';
//        this.sheet.getView().style.height = '150px';
//        demo.Util.appendChild(this.sheet.getView(), main, 20, 17, null, null);
//        this.initSheet();
        
        this.dialog = document.getElementById('detaildiv');

        this.initBox();
        this.network.setElementBox(this.box);
        this.network.addInteractionListener(function(e){
        	if (e.kind == 'doubleClickElement') {
        		console.log("doubleClickElement");
        		console.log(e.element);
        		if (e.element) {
            		console.log("is city rsg subnet");
            		var type = e.element.getClient("topo_node_type");
            		if (type=='subnetwork') {
            			console.log("type"+type);
            			var networkLevel = e.element.getClient("subnet_type");
            			//self.loadDataSubnetworkByNode(e.element);
            		}
        		}
        	}
        	if (e.kind == 'clickElement') {
                var type = e.element.getClient("topo_node_type");
                console.log("clickType=="+type);
                if(type=='node'){
                    var clientAttri = e.element.getClient("clientAttri");
                    console.log(clientAttri);
                    toPodata.showDetail(JSON.stringify(clientAttri));
                }else if (type=='subnetwork') {
                    console.log("type"+type);
                    var node_city_id=e.element.getClient("city_id");
                    var node_ems_id=e.element.getClient("ems_id");
                    var node_subnet_id=e.element.getClient("subnet_id");
                    var node_subnet_type=e.element.getClient("subnet_type");
                    var node_county_id="";
                    if ('county_ems_top_subnet'==node_subnet_type) {
                        var node_county_id=e.element.getClient("county_id");
                        console.log("node_county_id:"+node_county_id);
                    } else {
                    }
                    toPodata.showNextTopo(node_city_id,node_ems_id,node_subnet_id,node_subnet_type,node_county_id);
                 }else if(type=='link'){
                     var clientAttri = e.element.getClient("clientAttri");
                     console.log(JSON.stringify(e.element.getFromNode().getClient("clientAttri")));
                     console.log(e.element.getFromNode().getClient("topo_node_type"));
                     var fromType = e.element.getFromNode().getClient("topo_node_type");
                     var toType = e.element.getToNode().getClient("topo_node_type");
                     if(fromType=='node' && toType == 'node'){
                       toPodata.showInsideTOpo(JSON.stringify(clientAttri));
                     }

                 }


//        		if (e.element && ( e.element.getClassName() == 'twaver.Node'|| e.element.getClassName() == 'twaver.Link'||e.element.getClassName() == 'twaver.SubNetwork')) {
//        			console.log(e.element);
//        			clickObj = e;
//        			var titleTxt = document.getElementById('titleTxt');
//                    var txt = '';
//                    if(e.element.getName()){
//                        txt = e.element.getName();
//                    }
//                    titleTxt.innerHTML = txt;
//                    console.log(self.dialog);
//                    var s = self.dialog.style;    
//                    s.display = 'none';
//                    if (e.event.clientX && e.event.clientY) {
//						s.left = e.event.clientX+'px';
//						s.top = e.event.clientY+'px';
//					} else if (e.event.changedTouches && e.event.changedTouches[0]) {
//						s.left = e.event.changedTouches[0].clientX+'px';
//						s.top = e.event.changedTouches[0].clientY+'px';
//					}
//					var networkLevel = e.element.getClient("subnet_type");
//					var showDetail = true;
//					var dialogBody = document.getElementById('dialogBody');
//					var emsHTML = "";
//					console.log("networkLevel=="+networkLevel);
//					if (networkLevel) {
//						if (networkLevel=="city_rsg_subnet") {
//							//commonProviceCityObj = e.element;
//							console.log(e.element);
//							showDetail=false;
//							var cityId = e.element.getClient("city_id");
//							var clientAttri = e.element.getClient("clientAttri");
//							if (clientAttri && clientAttri.length>0) {
//								emsHTML = "<table class='table3'>";
//								for (var m=0; m<clientAttri.length; m++) {
//									emsHTML =  emsHTML+"<tr><td class='td2'><a href='javascript:void(0)' style='font-size:1.5em' onclick='showCityRsgSubnet("+cityId+","+clientAttri[m].value+")'>"+clientAttri[m].cnName+"</a></td></tr>";
//								}
//								emsHTML =  emsHTML+"</table>";
//			                    s.display = 'block';
//							}
//						}
//					}
//					if (showDetail) {
//                        console.log(e.element);
//                        var clientAttri = e.element.getClient("clientAttri");
//                        if (clientAttri && clientAttri.length>0) {
//                            console.log("aaa="+clientAttri.length);
//                            emsHTML = "<table class='table1' border=1>";
//                            if(e.element.getClassName() == 'twaver.Node'){
//                                var int_id;
//                                var clientAttri = e.element.getClient("clientAttri");
//                                if (clientAttri && clientAttri.length>0) {
//                                    for (var m=0; m<clientAttri.length; m++) {
//                                        if(clientAttri[m].enName=='int_id'){
//                                            int_id = clientAttri[m].value;
//                                        }
//                                    }
//                                }
//                                var type = e.element.getClient("topo_node_type");
//                                if(type=='node'){
//                                	emsHTML = emsHTML+"<tr><td class='td2' colspan='2'><a href='javascript:void(0)' onclick='showDeviceInfo("+int_id+")'>设备拓扑</a></td></tr>";	
//                                }
//                                
//                            }
//
//                            emsHTML =  emsHTML+"<tr><td>属性名</td><td>属性值</td></tr>";
//                            for (var m=0; m<clientAttri.length; m++) {
//                                emsHTML =  emsHTML+"<tr><td>"+clientAttri[m].cnName+"</td><td>"+clientAttri[m].value+"</td></tr>";
//                            }
//                            emsHTML =  emsHTML+"</table>";
//                            s.display = 'block';
//                        } else {
//                            s.display = 'none';
//                        }
//                    }
//					dialogBody.innerHTML=emsHTML;
//        		}
        	} else {
        		self.dialog.style.display = 'none';
        	}
        	if (e.kind == 'doubleClickBackground' && !e.element) {
        		//self.showLastLayer();
        	}
        });

        this.network.adjustBounds({x:0,y:0,width:document.documentElement.clientWidth,height:document.documentElement.clientHeight});
        window.onresize = function (e) { 
            this.network.adjustBounds({x:0,y:0,width:document.documentElement.clientWidth,height:document.documentElement.clientHeight});
        };
        this.network.getView().addEventListener('mousemove', function (e) { self.handleMouseMove(e); });
        this.network.setZoom(0.6);
        autoLayouter.doLayout('symmetry', function () {
            self.springLayouter.start();
            //self.springLayouter.stop();
        });
        setTimeout(function () {console.log("ffffff"); self.springLayouter.stop(); }, 2000);
        
    },
    
    initSheet: function () {
    },
    
    //add by liyk 
    showLoader: function () {
    	var loader = document.getElementById('loaddiv');
    	loader.style.display = "block";
    },
    hideLoader: function() {
    	var loader = document.getElementById('loaddiv');
    	loader.style.display = "none";
    },

    initBox: function () {
    console.log("url="+serverUrl+"/api/v1/topoManager/getEmsTopoInfo");
    	var self = this;
        if(subnet_id=="undefined"){
        	subnet_id = undefined;
        }
        if(city_id=="undefined"){
                	city_id = undefined;
        }
        if(ems_id=="undefined"){
                	ems_id = undefined;
        }
        if(county_id=="undefined"){
                    county_id = undefined;
        }
        if(subnet_type=="undefined"){
                    subnet_type = undefined;
        }

    	this.showLayerId=city_id+'_'+ems_id+'_'+subnet_id+'_'+subnet_type;
        var params = {
                'city_id':city_id,
                'ems_id':ems_id,
                'county_id':county_id,
                'subnet_id':subnet_id,
                'subnet_type':subnet_type
            };
        this.showLoader();
        console.log("url="+serverUrl+"/api/v1/topoManager/getEmsTopoInfo");
        console.log(JSON.stringify(params));
    	$.ajax({
			url: serverUrl+"/api/v1/topoManager/getEmsTopoInfo",
		    type: "GET",
		    data: params,
		    dataType: "json",
		    contentType: 'application/json',
            timeout: 50000, //超时时间：50秒
		    success: function (data) {
                self.hideLoader();
		    	console.log(data);
		    	//self.layerBox.getDefaultLayer().setVisible(false);
		    	if (subnet_type=="province_subnet"||subnet_type=="city_ems_subnet"||subnet_type=="county_ems_subnet") {
		    		self.setProvinceTopoNodeLocation(data);
		    	} 
		    	console.log(data);
		    	self.parseSubnetworkData(data, city_id+'_'+ems_id+'_'+subnet_id+'_'+subnet_type);
		    	//self.doLayoutBox();
		    	if (subnet_type=="province_subnet"||subnet_type=="city_ems_subnet"||subnet_type=="county_ems_subnet") {
		    	} else {
		    		self.doLayoutBox();
		    		setTimeout(function () { self.searchNodeIDInfo(aNeId,zNeId); }, 4000);

		    	}
		    },
		    error: function (err) {
		    	self.hideLoader();
		    	console.log(err);
		    	console.log(JSON.stringify(err));
		    	console.log("network error");
		    }
		});
    },
    setTopoDoubleClickToSubNetwork: function (status) {
    	console.log("status======"+status);
    	this.network.setDoubleClickToSubNetwork(status);
    },
    doLayoutBox: function () {
    	var self = this;
    	self.autoLayouter = new twaver.layout.AutoLayouter(self.allBoxObj[self.showLayerId]);
    	self.autoLayouter.doLayout('symmetry', function () {
            self.springLayouter.start();
        });
        setTimeout(function () { self.springLayouter.stop(); }, 3000);

/*    	var layouttoolbefor = +new Date();
        console.log(self.autoLayouter.getLayoutResult('symmetry'));
    	var layouttoolend = +new Date();
        console.log("排序用时共计tool="+(layouttoolend-layouttoolbefor)+"ms");*/
    },
    changeLayoutBox: function (mode) {
    	var self = this;
    	console.log(mode);
    	self.autoLayouter = new twaver.layout.AutoLayouter(self.network.getElementBox());
    	//round 圆形布局 symmetry 对称布局 hierarchic 层次布局 topbottom 从上到下布局 bottomtop 从下到上布局 rightleft 从右到左布局 leftright 从左到右布局
    	if (mode=='圆形布局') {
    		self.autoLayouter.doLayout('round');
    	} else if (mode=='对称布局') {
    		self.autoLayouter.doLayout('symmetry');
    	} else if (mode=='层次布局') {
    		self.autoLayouter.doLayout('hierarchic');
    	} else if (mode=='从上到下布局') {
    		self.autoLayouter.doLayout('topbottom');
    	} else if (mode=='从下到上布局') {
    		self.autoLayouter.doLayout('bottomtop');
    	} else if (mode=='从右到左布局') {
    		self.autoLayouter.doLayout('rightleft');
    	} else if (mode=='从左到右布局') {
    		self.autoLayouter.doLayout('leftright');
    	}
//    	self.autoLayouter.doLayout('symmetry', function () {
//            self.springLayouter.start();
//        });
//        setTimeout(function () { self.springLayouter.stop(); }, 3000);
    },
    showCityRsgSubnetA: function (city_id,ems_id) {
    	var self = this;
    	this.dialog.style.display = 'none';
    	console.log(self.showLayerId);
    	self.loadDataSubnetwork(city_id,ems_id,'','city_rsg_subnet');
    },
    loadDataSubnetworkByNode: function (obj) {
    	console.log("loadDataSubnetworkByNode=this.showLayerId=="+this.showLayerId);
    	console.log(obj);
    	var self = this;
    	self.dbSelectSubnetWork=obj;
    	var node_city_id=obj.getClient("city_id");
    	var node_ems_id=obj.getClient("ems_id");
    	var node_subnet_id=obj.getClient("subnet_id");
    	var node_subnet_type=obj.getClient("subnet_type");
    	self.clearSearchNodeInfo();
    	console.log("node_subnet_type:"+node_subnet_type);
    	if ('county_ems_top_subnet'==node_subnet_type) {
        	var node_county_id=obj.getClient("county_id");
        	console.log("node_county_id:"+node_county_id);
        	self.loadDataSubnetwork(node_city_id,node_ems_id,node_subnet_id,node_subnet_type,node_county_id);
    	} else {
        	self.loadDataSubnetwork(node_city_id,node_ems_id,node_subnet_id,node_subnet_type);
    	}
    },
    loadDataSubnetwork: function (city_id,ems_id,subnet_id,subnet_type,county_id) {
    	var self = this;
    	self.netWorkParamObj[self.showLayerId]=this.network.getZoom();
    	console.log(self.netWorkParamObj);
    	var newLayerId = city_id+'_'+ems_id+'_'+subnet_id+'_'+subnet_type;
    	if (self.allBoxObj[newLayerId]!=null) {
    		console.log("this.layerBox.containsById(newLayerId)");
    		self.network.setElementBox(this.allBoxObj[newLayerId]);
        	self.showLayerId=newLayerId;
        	//var randomnode = self.allBoxObj[newLayerId].getRandomData("Node");
    		var randomnode = self.getRandomData(self.allBoxObj[newLayerId]);
    		self.network.setZoom(self.netWorkParamObj[newLayerId]);
    		self.network.centerByLogicalPoint(randomnode.getX(),randomnode.getY());
    	} else {
    		var params = {
                    'city_id':city_id,
                    'ems_id':ems_id,
                    'subnet_id':subnet_id,
                    'subnet_type':subnet_type,
                    'county_id':county_id
                };
    		this.showLoader();
        	$.ajax({
    			url: serverUrl+"/api/v1/topoManager/getEmsTopoInfo",
    		    type: "GET",
    		    data: params,
    		    dataType: "json",
    		    success: function (data) {
    		    	self.hideLoader();
    		    	console.log(data);
    		    	self.layerRelation[city_id+'_'+ems_id+'_'+subnet_id+'_'+subnet_type]=self.showLayerId;
    		    	self.showLayerId=city_id+'_'+ems_id+'_'+subnet_id+'_'+subnet_type;
    		    	console.log(self.layerRelation);
    		    	self.parseSubnetworkData(data, city_id+'_'+ems_id+'_'+subnet_id+'_'+subnet_type);
    		    	//self.doLayoutBox();
    		    	if (subnet_type=="province_subnet"||subnet_type=="city_ems_subnet"||subnet_type=="county_ems_subnet") {
//    			    	setTopoDoubleClickToSubNetwork(false);
    		    	} else {
    		    		self.doLayoutBox();
    		    	}
    		    },
    		    error: function (err) {
    		    	self.hideLoader();
    		    }
    		});
    	}
    },
    showLastLayer: function () {
    	var self = this;
    	console.log(self.layerRelation);
    	console.log(self.showLayerId);
    	var lastLayerId = self.layerRelation[self.showLayerId];
    	console.log(lastLayerId);
    	console.log(self.network.getLocationZoom());
    	self.netWorkParamObj[self.showLayerId]=self.network.getZoom();
    	self.clearSearchNodeInfo();
    	if (lastLayerId!=null) {
    		console.log(self.allBoxObj);
    		self.network.setElementBox(self.allBoxObj[lastLayerId]);
    		console.log(self.netWorkParamObj);

    		var randomnode = self.getRandomData(self.allBoxObj[lastLayerId]);
    		console.log(randomnode);
    		self.network.setZoom(self.netWorkParamObj[lastLayerId]);
    		self.network.centerByLogicalPoint(randomnode.getX(),randomnode.getY());
    		self.network.panToCenter();
    		self.showLayerId=lastLayerId;
    		self.getLastSubnetWork(lastLayerId);
    	}
		self.network.centerByLogicalPoint(0,0);
		self.network.panByOffset(0,0);
		self.network.zoomOverview();
		self.network.adjustBounds({x:0,y:0,width:document.documentElement.clientWidth,height:document.documentElement.clientHeight});
    },
    getLastSubnetWork: function (layerId) {
    	var self = this;
    	var layerParamArr=layerId.split('_');
    	var subnetId=layerParamArr[2];
    	if (subnetId!=null &&subnetId!='null') {
        	var parentLayerId = self.layerRelation[layerId];
        	var parentBox= self.allBoxObj[parentLayerId];
        	if (parentBox.containsById(subnetId)) {
        		self.dbSelectSubnetWork=parentBox.getDataById(subnetId);
            }
    	}
//    	var node = null;
//    	for (var i=0;i<1000; i++) {
//    		node=box.getRandomData();
//    		console.log(node);
//    		if (node instanceof twaver.Node) {
//    			return node;
//    		}
//    	}
//    	return null;
    },
    changeLayerAndBox: function (subLayerId,parentLayerId) {
    	var self = this;
    	console.log(self.layerRelation);
    	console.log(self.showLayerId);
    	for (var item in self.layerRelation) {
    		if (self.layerRelation[item]==subLayerId) {
    			self.layerRelation[item]==parentLayerId;
    		}
    	}
    	delete self.layerRelation[subLayerId];
    	delete self.allBoxObj[subLayerId];
    },
    getRandomData: function (box) {
    	var node = null;
    	for (var i=0;i<1000; i++) {
    		node=box.getRandomData();
    		console.log(node);
    		if (node instanceof twaver.Node) {
    			return node;
    		}
    	}
    	return null;
    },
    parseSubnetworkData: function (jsonobj, layerId) {
    	console.log("parseSubnetworkData="+layerId);
    	var onebox = new ElementBox();
    	/*onebox.setStyle('background.type', 'vector');
    	onebox.setStyle('background.vector.fill', true);
    	onebox.setStyle('background.vector.gradient', 'radial.center');
    	onebox.setStyle('background.vector.fill.color', twaver.Colors.blue_light);
    	onebox.setStyle('background.vector.gradient.color', 'white');*/
    	var currLayer=null;
    	if (this.allBoxObj[layerId]!=null) {
        	console.log("parseSubnetworkData=this.layerBox.containsById(layerId)=="+layerId);
        	onebox=this.allBoxObj[layerId];
    	} else {
    		if (jsonobj.nodeList && jsonobj.nodeList instanceof Array) {
        		for (var m = 0; m < jsonobj.nodeList.length; m++) {
        			var oneNodeObj = jsonobj.nodeList[m];
        			//console.log(oneNodeObj);
        			this.createNode(onebox,currLayer,oneNodeObj);
        		}
        	}
        	if (jsonobj.linkList && jsonobj.linkList instanceof Array) {
        		for (var m = 0; m < jsonobj.linkList.length; m++) {
        			var oneLinkObj = jsonobj.linkList[m];
        			//console.log(oneLinkObj);
        			this.createLink(onebox,currLayer,oneLinkObj);
        		}
        	}
        	this.allBoxObj[layerId]=onebox;
    	}
    	console.log(this.allBoxObj);
    	this.network.setElementBox(onebox);
    },
    loadDataSubnetworkRefresh: function (city_id,ems_id,subnet_id,subnet_type) {
    	var self = this;
    	var params = {
                'city_id':city_id,
                'ems_id':ems_id,
                'subnet_id':subnet_id,
                'subnet_type':subnet_type
            };
    	this.showLoader();
    	$.ajax({
			url: serverUrl+"/api/v1/topoManager/getEmsTopoInfo",
		    type: "GET",
		    data: params,
		    dataType: "json",
		    success: function (data) {
		    	self.hideLoader();
		    	console.log(data);
		    	self.parseSubnetworkDataRefresh(data, city_id+'_'+ems_id+'_'+subnet_id+'_'+subnet_type);
		    },
		    error: function (err) {
		    	self.hideLoader();
		    }
		});
    },
    parseSubnetworkDataRefresh: function (jsonobj, layerId) {
    	var self = this;
    	console.log("parseSubnetworkData="+layerId);
    	var currBox = null;
    	if (layerId==self.showLayerId) {
    		currBox = self.network.getElementBox();
        	console.log(currBox);
        	var currLayer=null;
        	if (jsonobj.nodeList && jsonobj.nodeList instanceof Array) {
        		for (var m = 0; m < jsonobj.nodeList.length; m++) {
        			var oneNodeObj = jsonobj.nodeList[m];
        			//console.log(oneNodeObj);
        			self.createNode(currBox,currLayer,oneNodeObj);
        		}
        	}
        	if (jsonobj.linkList && jsonobj.linkList instanceof Array) {
        		for (var m = 0; m < jsonobj.linkList.length; m++) {
        			var oneLinkObj = jsonobj.linkList[m];
        			self.createLink(currBox,currLayer,oneLinkObj);
        		}
        	}
    	} else {
    		currBox=self.allBoxObj[layerId];
    		var currLayer=null;
        	if (jsonobj.nodeList && jsonobj.nodeList instanceof Array) {
        		for (var m = 0; m < jsonobj.nodeList.length; m++) {
        			var oneNodeObj = jsonobj.nodeList[m];
        			//console.log(oneNodeObj);
        			self.createNode(currBox,currLayer,oneNodeObj);
        		}
        	}
        	if (jsonobj.linkList && jsonobj.linkList instanceof Array) {
        		for (var m = 0; m < jsonobj.linkList.length; m++) {
        			var oneLinkObj = jsonobj.linkList[m];
        			self.createLink(currBox,currLayer,oneLinkObj);
        		}
        	}
    	}
    	self.allBoxObj[layerId]=currBox;
    },
    createNode: function (ctl_box,ctl_layer,nodeObj) {
//    	console.log(ctl_box);
//    	console.log(ctl_layer);
//    	console.log(nodeObj);
        if (ctl_box.containsById(nodeObj.id)) {
            return ctl_box.getDataById(nodeObj.id);
        }
        var node = null;
        if ("subnetwork"==nodeObj.type) {
//        	console.log("nodeObj.id-"+nodeObj.id);
        	console.log(nodeObj);
			//node = new twaver.SubNetwork(nodeObj.id);
			node = new twaver.Node(nodeObj.id);
			node.setName(nodeObj.name);
			if(nodeObj.image){
				node.setImage(nodeObj.image);
				//node.setImage(twaver.Defaults.IMAGE_SUBNETWORK);
				if(nodeObj.image=='group') node.setImage(twaver.Defaults.IMAGE_GROUP);
				if(nodeObj.image=='subnetwork') node.setImage(twaver.Defaults.IMAGE_SUBNETWORK);
			}
			if(nodeObj.icon){
				node.setStyle('icons.names', [nodeObj.icon]);
                node.setStyle('icons.position', 'topleft.topright');
			} else {
				node.setStyle('icons.names', 'Text');
                node.setStyle('icons.position', 'topleft.topright');
			}
            /*node.setStyle('background.type','vector');
            node.setStyle('background.vector.fill',true);
            node.setStyle('background.vector.gradient','radial.center');
            node.setStyle('background.vector.fill.color',twaver.Colors.blue_light);
            node.setStyle('background.vector.gradient.color','white');*/
            node.setWidth(204);
            node.setHeight(123);
            node.setStyle('shadow.blur',10);
            node.setStyle('shadow.xoffset',5);
            node.setStyle('shadow.yoffset',5);
            //node.setSize({width:150,height:100});
            node.setStyle('label.color',"#000000");
            node.setStyle('label.font', '45px "Microsoft Yahei"');
            node.setClient("clientAttri", nodeObj.clientAttri);
            node.setClient("text", "This is a SubNetwork, please double click it.");
            node.setClient("index", "0");
            node.setClient("underline", false);
            node.setClient("animate", 'playAnimateTurnPage');
            node.setClient("city_id", nodeObj.city_id);
            node.setClient("county_id", nodeObj.county_id);
            node.setClient("ems_id", nodeObj.ems_id);
            node.setClient("subnet_id", nodeObj.subnet_id);
            node.setClient("subnet_type", nodeObj.subnet_type);
            node.setLocation(Number(nodeObj.locationX),Number(nodeObj.locationY));
//            node.setClient("topo_node_type", nodeObj.type);
            node.setClient("topo_node_type", "subnetwork");
            ctl_box.add(node);
		} else {
			node = new twaver.Node(nodeObj.id);
			node.setName(nodeObj.name);
        	console.log(nodeObj);
			var neattr = nodeObj.clientAttri;
			var ne_service_level="";
			var equ_type="";
            if (neattr && neattr.length>0) {
                for (var p=0; p<neattr.length; p++) {
                    if (neattr[p].enName=="service_level") {
                        ne_service_level = neattr[p].value;
                    }
                    if (neattr[p].enName=="equ_type") {
                        equ_type = neattr[p].value;
                    }
                }
                console.log("ne_service_level=22="+ne_service_level);
                console.log("equ_type=22="+equ_type);
            }

            //			if (ne_service_level=="4") {
            //				node.setImage("PTN45");
            //				//node.setSize({width:150,height:100});
            //			} else if (ne_service_level=="5") {
            //				node.setImage("PTN45");
            //				//node.setSize({width:150,height:100});
            //			} else if (ne_service_level=="6") {
            //				node.setImage("PTN30");
            //			} else {
            //				if(nodeObj.image){
            //    				node.setImage(nodeObj.image);
            //    				if(nodeObj.image=='group') node.setImage(twaver.Defaults.IMAGE_GROUP);
            //    				if(nodeObj.image=='subnetwork') node.setImage(twaver.Defaults.IMAGE_SUBNETWORK);
            //    			}
            //			}

            if (ne_service_level=="4"||ne_service_level=="5") {
                    if (equ_type=="8") {
                        node.setImage("PTNBIG");
                    } else if (equ_type=="11") {
                    node.setImage("OTNBIG");
                } else if (equ_type=="1") {
                    node.setImage("SDHBIG");
                } else {
                    node.setImage("PTNBIG");
                }
            } else if (ne_service_level=="6") {
                if (equ_type=="8") {
                    node.setImage("PTNSMALL");
                } else if (equ_type=="11") {
                    node.setImage("OTNSMALL");
                } else if (equ_type=="1") {
                    node.setImage("SDHSMALL");
                } else {
                    node.setImage("PTNSMALL");
                }
            } else {
                if (equ_type=="8") {
                    node.setImage("PTNSMALL");
                } else if (equ_type=="11") {
                    node.setImage("OTNSMALL");
                } else if (equ_type=="1") {
                    node.setImage("SDHSMALL");
                } else {
                    node.setImage("PTNSMALL");
                }
            }

			if(nodeObj.icon){
				node.setStyle('icons.names', [nodeObj.icon]);
                node.setStyle('icons.position', 'bottomright.topleft');
			}
            node.setStyle('shadow.blur',10);
            node.setStyle('shadow.xoffset',5);
            node.setStyle('shadow.yoffset',5);
            //node.setSize({width:150,height:100});
            node.setStyle('label.color',"#000000");
            node.setStyle('label.font', '45px "Microsoft Yahei"');
            node.setClient("clientAttri", nodeObj.clientAttri);
            node.setClient("city_id", nodeObj.city_id);
            node.setClient("county_id", nodeObj.county_id);
            node.setClient("ems_id", nodeObj.ems_id);
            node.setClient("subnet_id", nodeObj.subnet_id);
            node.setClient("subnet_type", nodeObj.subnet_type);
            node.setLocation(Number(nodeObj.locationX),Number(nodeObj.locationY));
//            node.setClient("topo_node_type", nodeObj.type);
            node.setClient("topo_node_type", "node");
            ctl_box.add(node);
		}
        return node;
    },
    createLink: function (ctl_box,ctl_layer,nodeObj) {
    	if (ctl_box.containsById(nodeObj.id)) {
            return ctl_box.getDataById(nodeObj.id);
        }
        var from = ctl_box.getDataById(nodeObj.fromId);
        var to = ctl_box.getDataById(nodeObj.toId);
        var link = new twaver.Link(nodeObj.id,from, to);
//        link.setName(oneLinkObj.name);
        link.setStyle('shadow.blur',10);
        link.setStyle('shadow.xoffset',6);
        link.setStyle('shadow.yoffset',6);
        link.setStyle('link.width',2);
        link.setStyle('link.color','#4DA492');
        link.setStyle('link.bundle.expanded', false);
        link.setStyle('outer.width', 0);            
        link.setStyle('arrow.from.color', '#4DA492');
        link.setStyle('arrow.to.color', '#4DA492');
        link.setClient("clientAttri", nodeObj.clientAttri);
        link.setClient("topo_node_type", 'link');
		if(nodeObj.arrow){
			if(nodeObj.arrow=="10" || nodeObj.arrow=="11") link.setStyle('arrow.from', true);
			if(nodeObj.arrow=="01" || nodeObj.arrow=="11") link.setStyle('arrow.to', true);
		}
        ctl_box.add(link);
        return link;
    },
    showNePannel: function (lastData) {
    	if (lastData) {
			var node_id=lastData.getId();
			var nodeIds=node_id.split("_");
			var ne_id=nodeIds[nodeIds.length-1];
			window.open("nePanel?ne_id="+ne_id,"_blank");
		}
    },
    showNodeDetail: function (lastData,lastPoint) {
    	var self = this;
    	var titleTxt = document.getElementById('titleTxt');
        var txt = '';
        if(lastData.getName()){
            txt = lastData.getName();
        }
        titleTxt.innerHTML = txt;
        var s = self.dialog.style;    
		s.width="400px";   
		///s.height="300px";                
		//s.left = lastPoint.x+'px';
		//s.top = lastPoint.y+'px';      
		if (clickObj.event.clientX && clickObj.event.clientY) {
			s.left = clickObj.event.clientX+'px';
			s.top = clickObj.event.clientY+'px';
		} else if (clickObj.event.changedTouches && clickObj.event.changedTouches[0]) {
			s.left = clickObj.event.changedTouches[0].clientX+'px';
			s.top = clickObj.event.changedTouches[0].clientY+'px';
		}
		s.display = 'none';
		var networkLevel = lastData.getClient("subnet_type");
		var showDetail = true;
		var dialogBody = document.getElementById('dialogBody');
		var emsHTML = "";
		if (networkLevel) {
			if (networkLevel=="city_rsg_subnet") {
				//地市子网拓扑图时，控制显示EMS网管选择窗口
				commonProviceCityObj = lastData;
				showDetail=false;
				console.log(lastData);
				var clientAttri = lastData.getClient("clientAttri");
				if (clientAttri && clientAttri.length>0) {
					emsHTML = "<table class='table1'>";
					for (var m=0; m<clientAttri.length; m++) {
						//emsHTML =  emsHTML+"<tr><td class='td2'><a href='CountyNetwork?city_id="+e.element.getId()+"&ems_id="+clientAttri[m].value+"&subnet_type="+networkLevel+"' target='_blank'>"+clientAttri[m].cnName+"</a></td></tr>";
						//emsHTML =  emsHTML+"<tr><td class='td2'><a href='javascript:void(0)' onclick='showCityRsgSubnet("+clientAttri[m].value+")' target='_blank'>"+clientAttri[m].cnName+"</a></td></tr>";
						emsHTML =  emsHTML+"<tr><td class='td2'><a href='javascript:void(0)' onclick='showCityRsgSubnet("+clientAttri[m].value+")'>"+clientAttri[m].cnName+"</a></td></tr>";
					}
					emsHTML =  emsHTML+"</table>";
                    s.display = 'block';
				}
			}
		}
		
		if (showDetail) {
			//展示属性信息
			var clientAttri = lastData.getClient("clientAttri");
			if (clientAttri && clientAttri.length>0) {
				emsHTML = "<table class='table2'>";
				emsHTML =  emsHTML+"<tr><td>属性名</td><td>属性值</td></tr>";
				for (var m=0; m<clientAttri.length; m++) {
					emsHTML =  emsHTML+"<tr><td width='30%'>"+clientAttri[m].cnName+":</td><td  width='70%'>"+clientAttri[m].value+"</td></tr>";
				}
				emsHTML =  emsHTML+"</table>";
                s.display = 'block';
			} else {
				s.display = 'none';
			}
		}
		dialogBody.style.width="400px";
		dialogBody.innerHTML=emsHTML;
    },
    //设备查询高亮展示控制
    searchNodeInfo:function (search_nodeName) {
    	var self = this;
    	console.log(search_nodeName);
    	if (search_nodeName==null || search_nodeName=='') {
    		alert("请输入名称");
    	}
    	//先清楚上一次高亮展示的节点信息
    	self.clearSearchNodeInfo();
		var currBox = self.network.getElementBox();
    	//获取当前子网下的元素
    	var childrenList = currBox.getDatas();
    	self.highLightArr = new Array();
    	if (childrenList.size()>0) {
    		//循环处理所有元素
    		for (var m=0; m<childrenList.size(); m++) {
    			var oneChildObj = childrenList.get(m);
    			if (oneChildObj instanceof twaver.SubNetwork || oneChildObj instanceof twaver.Node ) {
    				//元素为子网或设备节点时
    				if (oneChildObj.getName().indexOf(search_nodeName)!=-1) {
    					self.highLightArr.push(oneChildObj.getId());
    					var oldNode = currBox.getDataById(oneChildObj.getId());

    					oldNode.setStyle('label.color',"#ef6c00");
    					oldNode.setStyle('icons.names', "icon_air");
    					oldNode.setStyle('icons.position', 'bottomleft.topleft');
    				}
    			}


    		}
    	}
    	if (self.highLightArr.length>0) {
            		var zoom = this.network.getZoom();
                    this.network.setZoom(1);
                    var nodeId = self.highLightArr[0];
                    var oldNode = currBox.getDataById(nodeId);
                    var centerX = this.network.getViewRect().x+this.network.getViewRect().width/2;
                    var centerY = this.network.getViewRect().y+this.network.getViewRect().height/2;
                    self.network.panByOffset(oldNode.getX()-centerX,oldNode.getY()-centerY);
                    this.network.setZoom(zoom);
            	}
    },

       //设备查询高亮展示控制
        searchNodeIDInfo:function (search_ID,endId) {
        	var self = this;
        	console.log(search_ID);
        	//先清楚上一次高亮展示的节点信息
        	self.clearSearchNodeInfo();
    		var currBox = self.network.getElementBox();
        	//获取当前子网下的元素
        	var childrenList = currBox.getDatas();
        	self.highLightArr = new Array();
        	if (childrenList.size()>0) {
        		//循环处理所有元素
        		var oldNode;
        		for (var m=0; m<childrenList.size(); m++) {
        			var oneChildObj = childrenList.get(m);

        			if (oneChildObj instanceof twaver.SubNetwork || oneChildObj instanceof twaver.Node ) {
        				//元素为子网或设备节点时
        				if (oneChildObj.getId().indexOf(search_ID)!=-1) {
        					self.highLightArr.push(oneChildObj.getId());
        					 oldNode = currBox.getDataById(oneChildObj.getId());

        					oldNode.setStyle('label.color',"#ef6c00");
        					oldNode.setStyle('icons.names', "icon_air");
        					oldNode.setStyle('icons.position', 'bottomleft.topleft');
        				}
        			}
        		}

        		var links = oldNode.getLinks();
        		console.log(links.size());
        		console.log(search_ID+":"+endId);
        		for(var n=0;n<links.size();n++){
        		   console.log(links.get(n).getId());
        		   console.log(links.get(n).getFromNode().getId()+":"+links.get(n).getToNode().getId());
        		   if((links.get(n).getFromNode().getId().indexOf(search_ID)!=-1 && links.get(n).getToNode().getId().indexOf(endId) != -1) ||(links.get(n).getFromNode().getId().indexOf(endId) != -1 && links.get(n).getToNode().getId().indexOf(search_ID)!=-1)){
        		     links.get(n).setStyle('link.color','#ef6c00');
        		     if(links.get(n).getToNode().getId().indexOf(endId) != -1){
        		         links.get(n).getToNode().setStyle('label.color',"#ef6c00");
                         links.get(n).getToNode().setStyle('icons.names', "icon_air");
                         links.get(n).getToNode().setStyle('icons.position', 'bottomleft.topleft');
        		     }else{
        		         links.get(n).getFromNode().setStyle('label.color',"#ef6c00");
                         links.get(n).getFromNode().setStyle('icons.names', "icon_air");
                         links.get(n).getFromNode().setStyle('icons.position', 'bottomleft.topleft');
        		     }

        		   }
        		}


        	}
        	if (self.highLightArr.length>0) {
                		var zoom = this.network.getZoom();
                        this.network.setZoom(1);
                        var nodeId = self.highLightArr[0];
                        var oldNode = currBox.getDataById(nodeId);
                        var centerX = this.network.getViewRect().x+this.network.getViewRect().width/2;
                        var centerY = this.network.getViewRect().y+this.network.getViewRect().height/2;
                        self.network.panByOffset(oldNode.getX()-centerX,oldNode.getY()-centerY);
                        this.network.setZoom(zoom);
                	}
        },

    //清除节点高亮展示
    clearSearchNodeInfo:function () {
    	var self = this;
    	if (self.highLightArr.length>0) {
    		var currBox = self.network.getElementBox();
//        	var childrenList = currBox.getDatas();
    		for (var m=0; m<self.highLightArr.length; m++) {
    			var nodeId = self.highLightArr[m];
    			var oldNode = currBox.getDataById(nodeId);
    			if (oldNode) {
    				oldNode.setStyle('label.color',"#000000");
					oldNode.setStyle('icons.names', "");
    			}
				
    		}
        	self.highLightArr = new Array();
    	}
    },
     setProvinceTopoNodeLocation: function (jsonobj) {
        	var w =document.documentElement.clientWidth;
        	var h =document.documentElement.clientHeight;



        	if (jsonobj.nodeList && jsonobj.nodeList instanceof Array) {
        		var rowNum=Math.ceil(jsonobj.nodeList.length/3);
        		console.log("rowNum==="+rowNum);
        		if (rowNum<4) {
        			rowNum=4;
        		}
        		console.log("rowNum==222="+rowNum);
        		var w_distance=w*0.9/3;
            	var h_distance=h*0.9/4;
            	console.log(w+"_"+h);
            	console.log(w_distance+"_"+h_distance);
        		var startX=100;
        		var startY=10;
        		var count=0;
        		for (var m = 0; m < jsonobj.nodeList.length; m++) {
        			console.log(jsonobj.nodeList[m]);

        			jsonobj.nodeList[m].locationX=startX;
        			jsonobj.nodeList[m].locationY=startY;
        			if ((m+1)%3==0) {
        				startY=startY+h_distance;
        				startX=100;
        			} else {
        				startX=startX+w_distance;
        			}

        			console.log(jsonobj.nodeList[m].locationX+"____"+jsonobj.nodeList[m].locationY);
        		}
        	}
        },

    handleMouseMove: function (e) {
        var element = this.network.getElementAt(e);
        if (element !== this.lastElement && !this.network.isMovingElement() && !this.network.isSelectingElement()) {
            if (this.lastElement instanceof Node) {
                this.lastElement.setStyle('vector.fill.color', this.nodeColor);
                this.lastElement.setStyle('label.fill', false);
            } else if (this.lastElement instanceof Link) {
                this.lastElement.setStyle('link.color', this.linkColor);
            }

            if (element instanceof Node) {
                element.setStyle('vector.fill.color', this.nodeOverColor);
                element.setStyle('label.fill', true);
            } else if (element instanceof Link) {
                element.setStyle('link.color', this.linkOverColor);
            }

            this.lastElement = element;
        }
    },
    handlePropertyChange: function (e) {
//        var name = demo.Util.getPropertyName(e);
//        if (name === 'interval') {
//            this.springLayouter.setInterval(e.newValue);
//        } else if (name === 'step.count') {
//            this.springLayouter.setStepCount(e.newValue);
//        } else if (name === 'node.repulsion.factor') {
//            this.springLayouter.setNodeRepulsionFactor(e.newValue);
//        } else if (name === 'link.repulsion.factor') {
//            this.springLayouter.setLinkRepulsionFactor(e.newValue);
//        } else if (name === 'node.shape.type') {
//            this.handleShapeChange('vector.shape', e.newValue);
//        } else if (name === 'node.gradient.type') {
//            this.handleShapeChange('vector.gradient', e.newValue);
//        }
    },
    handleShapeChange: function (property, value) {
        var datas = this.box.getDatas();
        for (var i = 0, s = this.box.size(); i < s; i++) {
            var element = datas.get(i);
            if (element instanceof Node) {
                element.setStyle(property, value);
            }
        }
    }
});