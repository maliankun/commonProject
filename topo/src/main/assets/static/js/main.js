/**
 * Created by zhengshsh on 2018/1/17.
 */
var main = {
    treebox: new twaver.ElementBox(),
    ptreebox: new twaver.ElementBox(),
    box: new twaver.ElementBox(),
    //tablebox:new twaver.ElementBox(),
    tree: null,
    ptree: null,
    network: null,
    //table:null,
    topDiv: null,

    attrDiv: null,
    autoLayouter: null,
    springLayouter: null,
    model: '',
    rightPane: '',
    init: function () {
        var tree = new inspurTree(this.treebox);
        var ptree = new inspurTree(this.ptreebox);
        var network = new InspurNetwork(this.box);
        //var table=new inspurTable(this.tablebox);
        this.tree = tree;
        this.ptree = ptree;
        this.network = network;

        images.registImages();

        var tdiv = this.tree.getView();
        $(tdiv).addClass('scrollbar');
        this.tree.setCollapseIcon("GROUPCLOSE");
        this.tree.setExpandIcon("GROUPOPEN");

        var ptdiv = this.ptree.getView();
        $(ptdiv).addClass('scrollbar');
        this.ptree.setCollapseIcon("GROUPCLOSE");
        this.ptree.setExpandIcon("GROUPOPEN");

        this.network.setScrollBarVisible(false);

        this.box.setStyle('background.type', 'image');
        this.box.setStyle('background.image', 'NETWORKBG');
        //this.table=table;
        this.autoLayouter = new twaver.layout.AutoLayouter(this.box);
        this.springLayouter = new twaver.layout.SpringLayouter(this.network);
        this.topDiv = document.getElementById('topbar');
        this.attrDiv = document.getElementById('attrbar');
        //$('#attrbar').css("overflow","visible !important");
        this.tree.setVisibleFunction(function (element) {
            return element instanceof twaver.Node;
        });
        this.ptree.setVisibleFunction(function (element) {
            return element instanceof twaver.Node;
        });


        var lefttPane = new twaver.controls.BorderPane(this.tree);
        var leftptPane = new twaver.controls.BorderPane(this.ptree);
        var lefttabpane = new twaver.controls.TabPane();
        //lefttabpane.setTabRadius(8);
        lefttabpane.setTabGap(0);
        lefttabpane.setTabBackground('#000080');
        lefttabpane.setSelectBackground('#2D79DA');


        this.addTab(lefttabpane, '分子网', lefttPane, true);
        this.addTab(lefttabpane, '分省', leftptPane);

        //lefttPane.setBottomHeight(300);

        //this.rightPane = new twaver.controls.BorderPane(this.network, null,null,this.attrDiv);
        this.rightPane = new twaver.controls.SplitPane(this.network, this.attrDiv, 'vertical', 1);
        this.rightPane.setExpandable(true);
        this.rightPane.setDividerBackground("#3A26A4");
        this.rightPane.setDividerWidth(0);
        //var rightpanediv=this.rightPane.getView();
        //$(rightpanediv).css('bottom','20px');
        //$(rightpanediv).css('right','20px');


        //rightPane.setTopHeight(30);
        //this.rightPane.setBottomHeight(0);


        var mainPanel = new twaver.controls.SplitPane(lefttabpane, this.rightPane, 'horizontal', 0.18);
        mainPanel.setDividerBackground("#040E26");
        mainPanel.setDividerWidth(1);

        var wholepanel = new twaver.controls.BorderPane(mainPanel, this.topDiv);
        wholepanel.setTopHeight(60);

        $('#topo').append(wholepanel.getView());

        wholepanel.adjustBounds({
            x: 0,
            y: 0,
            width: document.getElementById('topo').clientWidth,
            height: document.getElementById('topo').clientHeight
        });

        window.onresize = function (e) {
            wholepanel.adjustBounds({
                x: 0,
                y: 0,
                width: document.getElementById('topo').clientWidth,
                height: document.getElementById('topo').clientHeight
            });
        };

        //初始化左侧树图,请求后台数据
        //buttonHandle.initButton(this);
        this.initStyle();

        InterationHandle.init(this.network, this.tree, this.treebox, this.box, this);
        InterationHandle.init(this.network, this.ptree, this.ptreebox, this.box, this);
        InterationHandle.initViewChange(this.network);
        this.initModel();
        dialogdiv = this.initDialog();
        PopupMenus.initPopupMenuTopo(network)
    },
    initDialog: function () {
        var div = document.createElement('div');
        div.className = 'dialog';
        div.innerHTML = "<div id='dialogHeader'>" +
            "<img id='titleImg'></img>" +
            "<b><label id='titleTxt'></label></b>" +
            "</div>" +
            "<table class='table1'>" +
            "<tr><td class='td1'>Type</td><td class='td2'>Database Instance</td></tr>" +
            "<tr><td class='td1'>Host</td><td class='td21'>msdbsvr 1</td></tr>" +
            "<tr><td class='td1'>Incidents</td><td class='td21'>alert 2</td></tr>" +
            "</table>" +
            "<table class='table2'>" +
            "<tr><td class='td1'>Wait Time(%)</td><td class='td2'>15.73</td></tr>" +
            "<tr><td class='td1'>Active Sessions Using CPU</td><td class='td2'>0.002</td></tr>" +
            "<tr><td class='td1'>Active Sessions Waiting:I/O</td><td class='td2'>0</td></tr>" +
            "<tr><td class='td1'>Host CPU Utilization(%)</td><td class='td2'>4.51</td></tr>" +
            "</table>";
        document.body.appendChild(div);
        div.style.display = 'none';
        div.style.zIndex = 100;
        return div;
    },
    addTab: function (tablepane, name, view, selected) {
        var tab = new twaver.Tab(name);
        tab.setName(name);
        tab.setIcon(null);
        tab.setView(view);
        var topowidth = $('#topo').width();
        var tabwidht = topowidth * 0.09;
        tab.setWidth(tabwidht);
        //tab.getView().style.color="#FFFFFF";
        var tabview = tab.getView();
        tablepane.getTabBox().add(tab);
        if (selected) {
            tablepane.getTabBox().getSelectionModel().setSelection(tab);
        }
    },
    getTreeData: function () {
        //请求后台树图数据
        var self = this;
        var index = layer.load(2, {time: 30 * 1000, shade: [0.2, '#ffffff']});
        $.ajax({
            type: "post",
            async: false,
            url: rootContext + "/topoManage/queryEmsByAllVendor.ilf",
            success: function (json) {
                var jsonobj = eval('(' + json + ')');
                if (jsonobj.resultxml.success == true) {
                    dataHandle.parseData(jsonobj, self.treebox, self.box, "tree");
                    dataHandle.parseData(jsonobj, self.ptreebox, self.box, "ptree");
                }
                layer.close(index);
            },
            error: function (e) {
                // alert("查询异常！");
                layer.alert("查询异常！", {icon: 2, title: "错误"});
                layer.close(index);
            }

        })
    },
    initStyle: function () {
        var self = this;
        this.autoLayouter.setExplicitYOffset(100);
        this.autoLayouter.setAnimate(false);
        self.springLayouter.setInterval(50);
        self.springLayouter.setStepCount(8);
        self.springLayouter.setNodeRepulsionFactor(1);
        self.springLayouter.setLinkRepulsionFactor(1);
        this.autoLayouter.doLayout('symmetry', function () {
            self.springLayouter.start();
        });
    },
    initTable: function (table) {
        CreateTable.init(table);
    },
    initModel: function () {
        var self = this;
        // var model_names ="trans_ems,trans_sub_network,trans_element,topo_link";
        // $.jBox.tip("正在加载数据...", 'loading');
        // $.ajax({
        //     method: 'POST',
        //     url: rootContext + '/model/queryModelDataForJson.ilf',
        //     dataType: 'json',
        //     data: {'model_names': model_names},//传数据到后台。
        //     success: function (json) {
        //         self.model = json.root;
        //         self.initTable(self.table);
        self.getTreeData();
        if (ems_id && province_id) {
            var index = layer.load(2, {time: 30 * 1000, shade: [0.2, '#ffffff']});
            var params = {
                "id": province_id,
                "ems_id": ems_id,
                'type': "city"
            };
            $.ajax({
                type: "post",
                dataType: "json",
                data: params,
                async: true,
                url: rootContext + "/topoManage/queryTransNe.ilf",
                success: function (json) {
                    if (json.resultxml.success == true) {
                        self.box.clear();
                        dataHandle.parseTopoData(json, self.treebox, self.box);
                        var node;
                        self.box.forEach(function (ele) {
                            if(ele.getClient("type")=="trans_ne"){
                                node=ele;
                            }
                        })
                        if(node){
                            self.network.getSelectionModel().setSelection(node);
                        }

                    }
                    //$.jBox.closeTip();
                    layer.close(index);
                },
                error: function (e) {
                    layer.alert("查询EMS拓扑异常！", {icon: 2, title: "错误"});
                    //layer.closeAll('loading');
                    //$.jBox.closeTip();
                    layer.close(index);
                }
            })
        } else if (sub_id && ne_id&&ems_id) {

            var index = layer.load(2, {time: 30 * 1000, shade: [0.2, '#ffffff']});
            var params = {
                "id": sub_id,
                "type": "trans_sub_network",
                'ems_id':ems_id,
            };
            $.ajax({
                type: "post",
                dataType: "json",
                data: params,
                async: true,
                url: rootContext + "/topoManage/queryTransNe.ilf",
                success: function (json) {
                    if (json.resultxml.success == true) {
                        self.box.clear();
                        //buttonHandle.initSubNeButton(self);
                        dataHandle.parseTopoData(json, self.treebox, self.box);

                        var nodeQuickFinder = new twaver.QuickFinder(self.box, 'int_id', 'client', null, function (e) {
                            if (e instanceof twaver.Node) {
                                return true;
                            }
                            return false;
                        });
                        var node = nodeQuickFinder.findFirst(ne_id);
                        node.setStyle('outer.color', "#ADFF2F");
                        self.network.getSelectionModel().setSelection(node);
                    } else {
                        self.box.clear();
                        layer.alert(json.resultxml.msg, {icon: 2, title: "错误"});
                    }
                    //$.jBox.closeTip();
                    layer.close(index);
                },
                error: function (e) {
                    layer.alert("查询异常！", {icon: 2, title: "错误"});
                    //layer.closeAll('loading');
                    //$.jBox.closeTip();
                    layer.close(index);
                }
            })
        }
        //         $.jBox.closeTip();
        //     },
        //     error: function (response) {
        //         layer.alert("查询异常！", {icon: 2, title: "错误"});
        //         layer.closeAll('loading');
        //     }
        //
        // });
    }
}