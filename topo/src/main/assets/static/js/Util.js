var Util = {
    registerNormalImage: (function () {
        var loadingImages = {};
        return function (url, name, view) {
            if (loadingImages[url]) {
                loadingImages[url].push(callback);
                return;
            }
            if (twaver.Util.getImageAsset(name)) {
                return;
            }
            loadingImages[url] = [callback];
            var image = new Image();
            image.src = url;

            // Fix IE bug
            if (twaver.Util.isIE && url.substr(url.length - 4) === '.svg') {
                image.style.visibility = 'hidden';
                network.getView().appendChild(image);
                image.onload = function () {
                    setTimeout(function () {
                        twaver.Util.registerImage(name, image, image.clientWidth, image.clientHeight);
                        image.onload = null;
                        network.getView().removeChild(image);
                        loadingImages[url].forEach(function (cb) {
                        	callback();
                        });
                        delete loadingImages[url];
                    }, 200);
                };
            } else {
                image.onload = function () {
                    twaver.Util.registerImage(name, image, image.width, image.height);
                    image.onload = null;
                    loadingImages[url].forEach(function (cb) {
                    	callback();
                    });
                    delete loadingImages[url];
                };
            }

            function callback() {
                /*if (view.invalidateElementUIs) {
                    view.invalidateElementUIs();
                } else if (view.invalidateDisplay) {
                    view.invalidateDisplay();
                } else {
                    view(image);
                }*/
            }
        };
    })(),
    registerImage: function (url, svg) {
        var image = new Image();
        image.src = url;
        var views = arguments;
        image.onload = function () {
            twaver.Util.registerImage(Util.getImageName(url), image, image.width, image.height, svg === true);
            image.onload = null;
            for (var i = 1; i < views.length; i++) {
                var view = views[i];
                if (view.invalidateElementUIs) {
                    view.invalidateElementUIs();
                }
                if (view.invalidateDisplay) {
                    view.invalidateDisplay();
                }
            }
        };
    },
    getImageName: function (url) {
        var index = url.lastIndexOf('/');
        var name = url;
        if (index >= 0) {
            name = url.substring(index + 1);
        }
        index = name.lastIndexOf('.');
        if (index >= 0) {
            name = name.substring(0, index);
        }
        return name;
    },
    addButton: function (div, nameText, callback, src, float, fontSty) {
        var button = document.createElement('input');
        button.setAttribute('type', src ? 'image' : 'button');
        var name = nameText,
            text, textNode;
        if (name.indexOf('/') > 0) {
            name = nameText.split('/')[0];
            text = nameText.split('/')[1];
            textNode = document.createElement('i');
            textNode.innerText = text;
            //兼容火狐
            textNode.textContent = text;
            textNode.style.font = (fontSty && fontSty[0]) ? fontSty[0] : Defaults.font12;
            textNode.style.color = (fontSty && fontSty[1]) ? fontSty[1] : 'blue';
        }
        button.setAttribute('title', name);
        if (float) {
            button.style.float = float;
        }
        //??为什么看起来这句似乎没有作用？？
        button.style.verticalAlign = 'middle';
        if (src) {
            button.style.padding = '3px 3px 3px 3px';
            button.style.margin = '3px 3px 3px 3px';
            if (src.indexOf('/') < 0) {
                src = '../../images/toolbar/' + src + '.png';
            }

            button.setAttribute('src', src);
        } else {
            button.value = name;
        }
        button.style.outline = '2px solid  ' + Defaults.toolbarColor;
        button.onmouseover = function () {
            button.style.backgroundColor = 'LightBlue';
        }
        button.onmouseout = function () {
            button.style.backgroundColor = Defaults.toolbarColor;
        }
        button.addEventListener('click', function () {
            callback.call(this,button);
            setTimeout(function () {
                button.style.backgroundColor = Defaults.toolbarColor;
            }, 2000);
        }, false);
        name != ' ' && div.appendChild(button);
        textNode && div.appendChild(textNode);
        return button;
    },
    // findDevice: function(element) {
    //     while(element) {
    //         if(element.getClient('device')) {
    //             return element;
    //         } else {
    //             if(!element.getClient('rack')) {
    //                 element = element.getHost && element.getHost();
    //             } else {
    //                 return null
    //             }
    //         }
    //     }
    //     return null;
    // },
    parseAttr2Client:function (obj, ele) {
        for(var name in obj){
            if(!(obj[name] instanceof Array)){
                ele.setClient(name,obj[name]);
            }
        }
    },
    findModelById:function (model_id) {
        for (var i = 0, len = main.model.length; i < len; i++) {
            var modelObj=main.model[i];
            if(modelObj['resClassEnName']==model_id){
                return modelObj;
            }
        }
    },
    findEleByUUID:function (uuid) {
        var returnEle;
        main.box.forEach(function (ele) {
            if(ele.getClient('uuid')==uuid){
                returnEle=ele;
                return true;
            }
        })
        return returnEle;
    },
    findEleByTypeUUID:function (uuid,classType) {
        var returnEle;
        main.box.forEach(function (ele) {
            if(ele instanceof classType&&ele.getClient('uuid')==uuid){
                returnEle=ele;
                return true;
            }
        })
        return returnEle;
    },
    addCheckBox: function (div, checked, name, callback) {
        var checkBox = document.createElement('input');
        checkBox.id = name;
        checkBox.type = 'checkbox';
        checkBox.style.padding = '4px 4px 4px 4px';
        checkBox.checked = checked;
        if (callback) checkBox.addEventListener('click', callback, false);
        div.appendChild(checkBox);
        var label = document.createElement('label');
        label.htmlFor = name;
        label.innerHTML = name;
        div.appendChild(label);
        return checkBox;
    },
    addInteractionComboBox: function (div, network, interaction) {
        var items = twaver.Util.isTouchable ? ['Touch', 'None'] :
            ['Default-Live', 'Default-Lazy', 'Edit-Live', 'Edit-Lazy', 'Magnify', 'None'];
        var callback = function () {
            if (this.value === 'Default-Live') {
                network.setDefaultInteractions();
            } else if (this.value === 'Default-Lazy') {
                network.setDefaultInteractions(true);
            } else if (this.value === 'Edit-Live') {
                network.setEditInteractions();
            } else if (this.value === 'Edit-Lazy') {
                network.setEditInteractions(true);
            } else if (this.value === 'Pan') {
                network.setPanInteractions();
            } else if (this.value === 'Magnify') {
                network.setMagnifyInteractions();
            } else if (this.value === 'Touch') {
                network.setTouchInteractions();
            } else if (this.value === 'None') {
                network.setInteractions(null);
            }
        };
        return Util.addComboBox(div, items, callback, interaction);
    },
    addComboBox: function (div, items, callback, value) {
        var comboBox = document.createElement('select');
        comboBox.style.verticalAlign = 'top';
        items.forEach(function (item) {
            var option = document.createElement('option');
            option.appendChild(document.createTextNode(item));
            option.setAttribute('value', item);
            comboBox.appendChild(option);
        });

        if (callback) {
            comboBox.addEventListener('change', callback, false);
        }

        if (value) {
            comboBox.value = value;
        }
        div.appendChild(comboBox);
        return comboBox;
    },
    initOverviewPopupMenu: function (overview) {
        var popupMenu = new twaver.controls.PopupMenu(overview);
        popupMenu.setMenuItems([
            {label: 'Show Mask', type: 'check', selected: true, action: function (menuItem) {
                if (menuItem.selected) {
                    overview.setFillColor(overview.oldFillColor);
                    delete overview.oldFillColor;
                } else {
                    overview.oldFillColor = overview.getFillColor();
                    overview.setFillColor('rgba(0, 0, 0, 0)');
                }
            }},
            {label: 'Show Border', type: 'check', selected: true, action: function (menuItem) {
                if (menuItem.selected) {
                    overview.setOutlineColor(overview.oldOutlineColor);
                    delete overview.oldOutlineColor;
                } else {
                    overview.oldOutlineColor = overview.getOutlineColor();
                    overview.setOutlineColor('rgba(0, 0, 0, 0)');
                }
            }}
        ]);
    },
    addInput: function (div, value, name, callback) {
        var input = document.createElement('input');
        input.id = name;
        input.value = value;
        input.addEventListener('keydown', function (e) {
            if (e.keyCode == 13) {
                callback(input.value);
            }
        }, false);
        var label = document.createElement('label');
        label.htmlFor = name;
        label.innerHTML = name;
        div.appendChild(label);
        div.appendChild(input);
        return input;
    },
    addTab: function (tabPane, name, view, selected, closable) {
        var tab = new twaver.Tab(name);
        tab.setName(name);
        tab.setView(view);
        tabPane.getTabBox().add(tab);
        tab.setClosable(closable);
        if (selected) {
            tabPane.getTabBox().getSelectionModel().setSelection(tab);
        }
        return tab;
    },
    randomInt: function (n) {
        return Math.floor(Math.random() * n);
    },
    randomBoolean: function () {
        return Util.randomInt(2) != 0;
    },
    randomNonClearedSeverity: function () {
        while (true) {
            var severity = Util.randomSeverity();
            if (!twaver.AlarmSeverity.isClearedAlarmSeverity(severity)) {
                return severity;
            }
        }
        return null;
    },
    randomSeverity: function () {
        var severities = twaver.AlarmSeverity.severities;
        return severities.get(Util.randomInt(severities.size()));
    },
    randomColor: function () {
        var r = Util.randomInt(255);
        var g = Util.randomInt(255);
        var b = Util.randomInt(255);
        return '#' + Util._formatNumber((r << 16) | (g << 8) | b);
    },
    randomAlarm: function (alarmID, elementID, nonClearedSeverity) {
        var alarm = new twaver.Alarm(alarmID, elementID);
        alarm.setAcked(Util.randomBoolean());
        alarm.setCleared(Util.randomBoolean());
        alarm.setAlarmSeverity(nonClearedSeverity ? Util.randomNonClearedSeverity() : Util.randomSeverity());
        alarm.setClient('raisedTime', new Date());
        return alarm;
    },
    createColor: function (rgb, a) {
        if (typeof rgb == 'string' && rgb.indexOf('#') == 0) {
            rgb = parseInt(rgb.substring(1, rgb.length), 16);
        }
        var r = (rgb >> 16) & 0xFF;
        var g = (rgb >> 8) & 0xFF;
        var b = rgb & 0xFF;
        return 'rgba(' + r + ',' + g + ',' + b + ',' + a.toFixed(3) + ')';
    },
    _formatNumber: function (value) {
        var result = value.toString(16);
        while (result.length < 6) {
            result = '0' + result;
        }
        return result;
    },
    loadXmlString: function (xml) {
        var xmlDoc;
        if (!twaver.Util.isIE && window.DOMParser) {
            var parser = new DOMParser();
            xmlDoc = parser.parseFromString(xml, 'text/xml');
        } else {
            xmlDoc = new ActiveXObject('Microsoft.XMLDOM');
            xmlDoc.async = false;
            xmlDoc.loadXML(xml);
        }
        return xmlDoc;
    },
    loadXmlFile: function (url) {
        var xhttp = new XMLHttpRequest();
        xhttp.open('GET', url, false);
        xhttp.send();
        return xhttp.responseXML;
    },
    addStyleProperty: function (box, propertyName, category, name) {
        return Util._addProperty(box, propertyName, category, name, 'style');
    },
    addClientProperty: function (box, propertyName, category, name) {
        return Util._addProperty(box, propertyName, category, name, 'client');
    },
    addAccessorProperty: function (box, propertyName, category, name) {
        return Util._addProperty(box, propertyName, category, name, 'accessor');
    },
    _addProperty: function (box, propertyName, category, name, proprtyType) {
        var property = new twaver.Property();
        property.setCategoryName(category);
        if (!name) {
            name = Util._getNameFromPropertyName(propertyName);
        }
        property.setName(name);
        property.setEditable(true);
        property.setPropertyType(proprtyType);
        property.setPropertyName(propertyName);

        var valueType;
        if (proprtyType === 'style') {
            valueType = twaver.SerializationSettings.getStyleType(propertyName);
        } else if (proprtyType === 'client') {
            valueType = twaver.SerializationSettings.getClientType(propertyName);
        } else {
            valueType = twaver.SerializationSettings.getPropertyType(propertyName);
        }
        if (valueType) {
            property.setValueType(valueType);
        }

        box.add(property);
        return property;
    },
    _getNameFromPropertyName: function (propertyName) {
        var names = propertyName.split('.');
        var name = '';
        for (var i = 0; i < names.length; i++) {
            if (names[i].length > 0) {
                name += names[i].substring(0, 1).toUpperCase() + names[i].substring(1, names[i].length);
            }
            if (i < names.length - 1) {
                name += ' ';
            }
        }
        return name;
    },
    createColumn: function (table, name, propertyName, propertyType, valueType, editable) {
        var column = new twaver.Column(name);
        column.setName(name);
        column.setPropertyName(propertyName);
        column.setPropertyType(propertyType);
        if (valueType) column.setValueType(valueType);
        column.setEditable(editable);
        column.renderHeader = function (div) {
            var span = document.createElement('span');
            span.style.whiteSpace = 'nowrap';
            span.style.verticalAlign = 'middle';
            span.style.padding = '1px 2px 1px 2px';
            span.innerHTML = column.getName() ? column.getName() : column.getPropertyName();
            span.setAttribute('title', span.innerHTML);
            span.style.font = 'bold 12px Helvetica';
            div.style.textAlign = 'center';
            div.appendChild(span);
        };
        table.getColumnBox().add(column);
        return column;
    },
    formatDate: function (date, format) {
        var o = {
            'M+': date.getMonth() + 1,
            'd+': date.getDate(),
            'h+': date.getHours(),
            'm+': date.getMinutes(),
            's+': date.getSeconds(),
            'q+': Math.floor((date.getMonth() + 3) / 3),
            'S': date.getMilliseconds()
        };
        if (/(y+)/.test(format))
            format = format.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp('(' + k + ')').test(format))
                format = format.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)));
        return format;
    },
    getPropertyName: function (e) {
        var name = e.property;
        if (name.indexOf('C:') == 0) {
            return name.substring(2, name.length);
        }
        if (name.indexOf('S:') == 0) {
            return name.substring(2, name.length);
        }
        return name;
    },
    createDraggableNetwork: function (box) {
        var network = new twaver.vector.Network(box);

        network.getView().addEventListener('dragover', function (e) {
            if (e.preventDefault) {
                e.preventDefault();
            } else {
                e.returnValue = false;
            }
            e.dataTransfer.dropEffect = 'copy';
            return false;
        }, false);
        network.getView().addEventListener('drop', function(e) {
            var target = network.getElementAt(e); 
            if (e.stopPropagation) {
                e.stopPropagation();
            }
            if (e.preventDefault) {
                e.preventDefault();
            } else {
                e.returnValue = false;
            }
            var text = e.dataTransfer.getData('Text');
            var imageText = e.dataTransfer.getData('Image');
            var shapeText = e.dataTransfer.getData('Shape');
            if (!text) {
                return false;
            }
            if (text && text.indexOf('className:') == 0) {
                var className = text.substr(10, text.length)
                if(className === "twaver.Node"){
                    if(imageText && imageText.indexOf('image:') == 0){
                        Util._createElement(network, text.substr(10, text.length), network.getLogicalPoint(e), imageText.substr(6, imageText.length));
                    }else if(shapeText && shapeText.indexOf('shape:') == 0){
                        Util._createElement(network, text.substr(10, text.length), network.getLogicalPoint(e), null, shapeText.substr(6, shapeText.length));
                    } else {
                        Util._createElement(network, text.substr(10, text.length), network.getLogicalPoint(e), null, null, target);
                    }
                } else {
                    Util._createElement(network, text.substr(10, text.length), network.getLogicalPoint(e), null, null, target);
                }
            }
            if (text && text.indexOf('<twaver') == 0) {
                network.getElementBox().clear();
                new twaver.XmlSerializer(network.getElementBox()).deserialize(text);
            }
            return false;
        }, false);

        network.getView().setAttribute('draggable', 'true');
        network.getView().addEventListener('dragstart', function (e) {
            e.dataTransfer.effectAllowed = 'copy';
            e.dataTransfer.setData('Text', new twaver.XmlSerializer(network.getElementBox()).serialize());
        }, false);

        return network;
    },
    appendChild: function (e, parent, top, right, bottom, left) {
        e.style.position = 'absolute';
        if (left != null) e.style.left = left + 'px';
        if (top != null) e.style.top = top + 'px';
        if (right != null) e.style.right = right + 'px';
        if (bottom != null) e.style.bottom = bottom + 'px';
        parent.appendChild(e);
    },
    createNetworkToolbar: function (network, interaction) {
        var toolbar = document.createElement('div');
        Util.addButton(toolbar, 'Default', 'select', function () {
            if (twaver.Util.isTouchable) {
                network.setTouchInteractions();
            } else {
                network.setDefaultInteractions();
            }
        });
        Util.addButton(toolbar, 'Magnify', 'magnify', function () {
            network.setMagnifyInteractions();
        });
//        Util.addButton(toolbar, 'Pan', 'pan', function () { network.setPanInteractions(); });

        Util.addButton(toolbar, 'Zoom In', 'zoomIn', function () { network.zoomIn(); });
        Util.addButton(toolbar, 'Zoom Out', 'zoomOut', function () { network.zoomOut(); });
        Util.addButton(toolbar, 'Zoom Reset', 'zoomReset', function () { network.zoomReset(); });
        Util.addButton(toolbar, 'Zoom Overview', 'zoomOverview', function () { network.zoomOverview(); });
        Util.addInteractionComboBox(toolbar, network, interaction);
        Util.addButton(toolbar, 'XML', 'save', function () {
            var box = network.getElementBox();
            var text = new twaver.XmlSerializer(box).serialize();
            if (twaver.Util.isIE) {
                /*
                var iframe = document.createElement('iframe');
                iframe.style.display = 'none';
                iframe.document.body = text;
                document.appendChild(iframe);
                iframe.document.execCommand("SaveAs");
                */
                var iframe = document.createElement('iframe');
                document.body.insertBefore(iframe);
                iframe.style.display = 'none';
                iframe.contentDocument.write(text);
                iframe.contentDocument.execCommand('SaveAs', true, 'file.xml');
                document.body.removeChild(iframe);
            } else {
                var uriContent = "data:text/xml," + encodeURIComponent(text);
                window.open(uriContent, 'network');
            }
            box.clear();
            new twaver.XmlSerializer(box).deserialize(text);

            text = new twaver.JsonSerializer(box).serialize();
            box.clear();
            new twaver.JsonSerializer(box).deserialize(text);

            if (console) {
                console.log(new twaver.JsonSerializer(box).serialize());
            }
        });
        Util.addButton(toolbar, 'Export Image', 'export', function () {
            var canvas;
            if (network.getCanvasSize) {
            var bounds = network.getUnionBounds();
            var canvaSize = network.getCanvasSize();
            var rect = _twaver.math.unionRect({x:0,y:0,width:canvaSize.width,height:canvaSize.height},bounds);
            canvas = network.toCanvas(rect.width,rect.height, null,network.getZoom(), rect.x, rect.y);
            } else {
            canvas = network.toCanvas(network.getView().scrollWidth, network.getView().scrollHeight);
            }
            if (twaver.Util.isIE) {
                var w = window.open();
                w.document.open();
                w.document.write("<img src='" + canvas.toDataURL() + "'/>");
                w.document.close();
            } else {
                window.open(canvas.toDataURL(), 'network.png');
            }
        });
        
        if (Util.isFullScreenSupported()) {
            Util.addButton(toolbar, 'Full screen', 'fullscreen', function () {
                Util.toggleFullscreen();
            });
        }
        return toolbar;
    },
    addButton: function (div, name, src, callback) {
        var button = document.createElement('input');
        button.setAttribute('type', src ? 'image' : 'button');
        button.setAttribute('title', name);
        button.style.verticalAlign = 'top';
        if (src) {
            button.style.padding = '4px 4px 4px 4px';
            if (src.indexOf('/') < 0) {
                src = '../../images/toolbar/' + src + '.png';
            }
            button.setAttribute('src', src);
        } else {
            button.value = name;
        }
        button.addEventListener('click', callback, false);
        div.appendChild(button);
        return button;
    },
    _createElement: function(network, className, centerLocation, imageSrc, vectorShape, parent) {
        var element = twaver.Util.newInstance(className);
        element.setCenterLocation(centerLocation);

        if (!parent) {
            element.setParent(network.getCurrentSubNetwork());
        } else {
            element.setParent(parent);
        }

        if (imageSrc) {
            element.setImage(imageSrc);
        }
        if(vectorShape){
            element.setStyle('body.type', 'vector');
            element.setStyle('vector.shape', vectorShape);
        }
        network.getElementBox().add(element);
        network.getElementBox().getSelectionModel().setSelection(element);
    },
    isFullScreenSupported: function () {
        var docElm = document.documentElement;
        return docElm.requestFullscreen || docElm.webkitRequestFullScreen || docElm.mozRequestFullScreen;
    },
    toggleFullscreen: function () {
        if (Util.isFullScreenSupported()) {
            var fullscreen = document.fullscreen || document.mozFullScreen || document.webkitIsFullScreen;
            if (!fullscreen) {
                var docElm = document.documentElement;
                if (docElm.requestFullscreen) {
                    docElm.requestFullscreen();
                } else if (docElm.webkitRequestFullScreen) {
                    docElm.webkitRequestFullScreen(Element.ALLOW_KEYBOARD_INPUT);
                } else if (docElm.mozRequestFullScreen) {
                    docElm.mozRequestFullScreen();
                }
            } else {
                if (document.exitFullscreen) {
                    document.exitFullscreen();
                } else if (document.mozCancelFullScreen) {
                    document.mozCancelFullScreen();
                } else if (document.webkitCancelFullScreen) {
                    document.webkitCancelFullScreen();
                }
            }
        }
    }

};