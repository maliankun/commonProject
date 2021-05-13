package com.inspur.osp.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liuchen01 on 2019/10/21.
 */

public class TopoRouteBean implements Serializable {
    private String city_uuid;

    private String county_uuid;

    private String int_id;

    private List<LinesBean> lines;

    private List<PointsBean> points;

    private String shape;

    private TopoInfoBean topoInfo;

    private String typecode;

    private String zh_label;

    public String getCity_uuid() {
        return this.city_uuid;
    }

    public String getCounty_uuid() {
        return this.county_uuid;
    }

    public String getInt_id() {
        return this.int_id;
    }

    public List<LinesBean> getLines() {
        return this.lines;
    }

    public List<PointsBean> getPoints() {
        return this.points;
    }

    public String getShape() {
        return this.shape;
    }

    public TopoInfoBean getTopoInfo() {
        return this.topoInfo;
    }

    public String getTypecode() {
        return this.typecode;
    }

    public String getZh_label() {
        return this.zh_label;
    }

    public void setCity_uuid(String paramString) {
        this.city_uuid = paramString;
    }

    public void setCounty_uuid(String paramString) {
        this.county_uuid = paramString;
    }

    public void setInt_id(String paramString) {
        this.int_id = paramString;
    }

    public void setLines(List<LinesBean> paramList) {
        this.lines = paramList;
    }

    public void setPoints(List<PointsBean> paramList) {
        this.points = paramList;
    }

    public void setShape(String paramString) {
        this.shape = paramString;
    }

    public void setTopoInfo(TopoInfoBean paramTopoInfoBean) {
        this.topoInfo = paramTopoInfoBean;
    }

    public void setTypecode(String paramString) {
        this.typecode = paramString;
    }

    public void setZh_label(String paramString) {
        this.zh_label = paramString;
    }

    public static class LinesBean {
        private int jump;

        private List<RoutesBean> routes;

        public int getJump() {
            return this.jump;
        }

        public List<RoutesBean> getRoutes() {
            return this.routes;
        }

        public void setJump(int param1Int) {
            this.jump = param1Int;
        }

        public void setRoutes(List<RoutesBean> param1List) {
            this.routes = param1List;
        }

        public static class RoutesBean {
            private String a_rack_id;

            private String a_rack_id_val;

            private String a_room_id;

            private String a_room_id_val;

            private String a_site_id;

            private String a_site_id_val;

            private String endNode;

            private String endNodeId;

            private String endNodeType;

            private String id;

            private String length;

            private String name;

            private String startNode;

            private String startNodeId;

            private String startNodeType;

            private String z_rack_id;

            private String z_rack_id_val;

            private String z_room_id;

            private String z_room_id_val;

            private String z_site_id;

            private String z_site_id_val;

            public String getA_rack_id() {
                return this.a_rack_id;
            }

            public String getA_rack_id_val() {
                return this.a_rack_id_val;
            }

            public String getA_room_id() {
                return this.a_room_id;
            }

            public String getA_room_id_val() {
                return this.a_room_id_val;
            }

            public String getA_site_id() {
                return this.a_site_id;
            }

            public String getA_site_id_val() {
                return this.a_site_id_val;
            }

            public String getEndNode() {
                return this.endNode;
            }

            public String getEndNodeId() {
                return this.endNodeId;
            }

            public String getEndNodeType() {
                return this.endNodeType;
            }

            public String getId() {
                return this.id;
            }

            public String getLength() {
                return this.length;
            }

            public String getName() {
                return this.name;
            }

            public String getStartNode() {
                return this.startNode;
            }

            public String getStartNodeId() {
                return this.startNodeId;
            }

            public String getStartNodeType() {
                return this.startNodeType;
            }

            public String getZ_rack_id() {
                return this.z_rack_id;
            }

            public String getZ_rack_id_val() {
                return this.z_rack_id_val;
            }

            public String getZ_room_id() {
                return this.z_room_id;
            }

            public String getZ_room_id_val() {
                return this.z_room_id_val;
            }

            public String getZ_site_id() {
                return this.z_site_id;
            }

            public String getZ_site_id_val() {
                return this.z_site_id_val;
            }

            public void setA_rack_id(String param2String) {
                this.a_rack_id = param2String;
            }

            public void setA_rack_id_val(String param2String) {
                this.a_rack_id_val = param2String;
            }

            public void setA_room_id(String param2String) {
                this.a_room_id = param2String;
            }

            public void setA_room_id_val(String param2String) {
                this.a_room_id_val = param2String;
            }

            public void setA_site_id(String param2String) {
                this.a_site_id = param2String;
            }

            public void setA_site_id_val(String param2String) {
                this.a_site_id_val = param2String;
            }

            public void setEndNode(String param2String) {
                this.endNode = param2String;
            }

            public void setEndNodeId(String param2String) {
                this.endNodeId = param2String;
            }

            public void setEndNodeType(String param2String) {
                this.endNodeType = param2String;
            }

            public void setId(String param2String) {
                this.id = param2String;
            }

            public void setLength(String param2String) {
                this.length = param2String;
            }

            public void setName(String param2String) {
                this.name = param2String;
            }

            public void setStartNode(String param2String) {
                this.startNode = param2String;
            }

            public void setStartNodeId(String param2String) {
                this.startNodeId = param2String;
            }

            public void setStartNodeType(String param2String) {
                this.startNodeType = param2String;
            }

            public void setZ_rack_id(String param2String) {
                this.z_rack_id = param2String;
            }

            public void setZ_rack_id_val(String param2String) {
                this.z_rack_id_val = param2String;
            }

            public void setZ_room_id(String param2String) {
                this.z_room_id = param2String;
            }

            public void setZ_room_id_val(String param2String) {
                this.z_room_id_val = param2String;
            }

            public void setZ_site_id(String param2String) {
                this.z_site_id = param2String;
            }

            public void setZ_site_id_val(String param2String) {
                this.z_site_id_val = param2String;
            }
        }
    }

    public static class RoutesBean {
        private String a_rack_id;

        private String a_rack_id_val;

        private String a_room_id;

        private String a_room_id_val;

        private String a_site_id;

        private String a_site_id_val;

        private String endNode;

        private String endNodeId;

        private String endNodeType;

        private String id;

        private String length;

        private String name;

        private String startNode;

        private String startNodeId;

        private String startNodeType;

        private String z_rack_id;

        private String z_rack_id_val;

        private String z_room_id;

        private String z_room_id_val;

        private String z_site_id;

        private String z_site_id_val;

        public String getA_rack_id() {
            return this.a_rack_id;
        }

        public String getA_rack_id_val() {
            return this.a_rack_id_val;
        }

        public String getA_room_id() {
            return this.a_room_id;
        }

        public String getA_room_id_val() {
            return this.a_room_id_val;
        }

        public String getA_site_id() {
            return this.a_site_id;
        }

        public String getA_site_id_val() {
            return this.a_site_id_val;
        }

        public String getEndNode() {
            return this.endNode;
        }

        public String getEndNodeId() {
            return this.endNodeId;
        }

        public String getEndNodeType() {
            return this.endNodeType;
        }

        public String getId() {
            return this.id;
        }

        public String getLength() {
            return this.length;
        }

        public String getName() {
            return this.name;
        }

        public String getStartNode() {
            return this.startNode;
        }

        public String getStartNodeId() {
            return this.startNodeId;
        }

        public String getStartNodeType() {
            return this.startNodeType;
        }

        public String getZ_rack_id() {
            return this.z_rack_id;
        }

        public String getZ_rack_id_val() {
            return this.z_rack_id_val;
        }

        public String getZ_room_id() {
            return this.z_room_id;
        }

        public String getZ_room_id_val() {
            return this.z_room_id_val;
        }

        public String getZ_site_id() {
            return this.z_site_id;
        }

        public String getZ_site_id_val() {
            return this.z_site_id_val;
        }

        public void setA_rack_id(String param1String) {
            this.a_rack_id = param1String;
        }

        public void setA_rack_id_val(String param1String) {
            this.a_rack_id_val = param1String;
        }

        public void setA_room_id(String param1String) {
            this.a_room_id = param1String;
        }

        public void setA_room_id_val(String param1String) {
            this.a_room_id_val = param1String;
        }

        public void setA_site_id(String param1String) {
            this.a_site_id = param1String;
        }

        public void setA_site_id_val(String param1String) {
            this.a_site_id_val = param1String;
        }

        public void setEndNode(String param1String) {
            this.endNode = param1String;
        }

        public void setEndNodeId(String param1String) {
            this.endNodeId = param1String;
        }

        public void setEndNodeType(String param1String) {
            this.endNodeType = param1String;
        }

        public void setId(String param1String) {
            this.id = param1String;
        }

        public void setLength(String param1String) {
            this.length = param1String;
        }

        public void setName(String param1String) {
            this.name = param1String;
        }

        public void setStartNode(String param1String) {
            this.startNode = param1String;
        }

        public void setStartNodeId(String param1String) {
            this.startNodeId = param1String;
        }

        public void setStartNodeType(String param1String) {
            this.startNodeType = param1String;
        }

        public void setZ_rack_id(String param1String) {
            this.z_rack_id = param1String;
        }

        public void setZ_rack_id_val(String param1String) {
            this.z_rack_id_val = param1String;
        }

        public void setZ_room_id(String param1String) {
            this.z_room_id = param1String;
        }

        public void setZ_room_id_val(String param1String) {
            this.z_room_id_val = param1String;
        }

        public void setZ_site_id(String param1String) {
            this.z_site_id = param1String;
        }

        public void setZ_site_id_val(String param1String) {
            this.z_site_id_val = param1String;
        }
    }

    public static class PointsBean {
        private String city_uuid;

        private String county_uuid;

        private String int_id;

        private String shape;

        private String typecode;

        private String zh_label;

        public String getCity_uuid() {
            return this.city_uuid;
        }

        public String getCounty_uuid() {
            return this.county_uuid;
        }

        public String getInt_id() {
            return this.int_id;
        }

        public String getShape() {
            return this.shape;
        }

        public String getTypecode() {
            return this.typecode;
        }

        public String getZh_label() {
            return this.zh_label;
        }

        public void setCity_uuid(String param1String) {
            this.city_uuid = param1String;
        }

        public void setCounty_uuid(String param1String) {
            this.county_uuid = param1String;
        }

        public void setInt_id(String param1String) {
            this.int_id = param1String;
        }

        public void setShape(String param1String) {
            this.shape = param1String;
        }

        public void setTypecode(String param1String) {
            this.typecode = param1String;
        }

        public void setZh_label(String param1String) {
            this.zh_label = param1String;
        }
    }

    public static class TopoInfoBean {
        private String destNeId;

        private String destNeName;

        private String destPointId;

        private String destPointName;

        private String destRoomId;

        private String destRoomName;

        private String destSiteId;

        private String destSiteName;

        private String intId;

        private String origNeId;

        private String origNeName;

        private String origPointId;

        private String origPointName;

        private String origRoomId;

        private String origRoomName;

        private String origSiteId;

        private String orignSiteName;

        private String zh_label;

        public String getDestNeId() {
            return this.destNeId;
        }

        public String getDestNeName() {
            return this.destNeName;
        }

        public String getDestPointId() {
            return this.destPointId;
        }

        public String getDestPointName() {
            return this.destPointName;
        }

        public String getDestRoomId() {
            return this.destRoomId;
        }

        public String getDestRoomName() {
            return this.destRoomName;
        }

        public String getDestSiteId() {
            return this.destSiteId;
        }

        public String getDestSiteName() {
            return this.destSiteName;
        }

        public String getIntId() {
            return this.intId;
        }

        public String getOrigNeId() {
            return this.origNeId;
        }

        public String getOrigNeName() {
            return this.origNeName;
        }

        public String getOrigPointId() {
            return this.origPointId;
        }

        public String getOrigPointName() {
            return this.origPointName;
        }

        public String getOrigRoomId() {
            return this.origRoomId;
        }

        public String getOrigRoomName() {
            return this.origRoomName;
        }

        public String getOrigSiteId() {
            return this.origSiteId;
        }

        public String getOrignSiteName() {
            return this.orignSiteName;
        }

        public String getZh_label() {
            return this.zh_label;
        }

        public void setDestNeId(String param1String) {
            this.destNeId = param1String;
        }

        public void setDestNeName(String param1String) {
            this.destNeName = param1String;
        }

        public void setDestPointId(String param1String) {
            this.destPointId = param1String;
        }

        public void setDestPointName(String param1String) {
            this.destPointName = param1String;
        }

        public void setDestRoomId(String param1String) {
            this.destRoomId = param1String;
        }

        public void setDestRoomName(String param1String) {
            this.destRoomName = param1String;
        }

        public void setDestSiteId(String param1String) {
            this.destSiteId = param1String;
        }

        public void setDestSiteName(String param1String) {
            this.destSiteName = param1String;
        }

        public void setIntId(String param1String) {
            this.intId = param1String;
        }

        public void setOrigNeId(String param1String) {
            this.origNeId = param1String;
        }

        public void setOrigNeName(String param1String) {
            this.origNeName = param1String;
        }

        public void setOrigPointId(String param1String) {
            this.origPointId = param1String;
        }

        public void setOrigPointName(String param1String) {
            this.origPointName = param1String;
        }

        public void setOrigRoomId(String param1Object) {
            this.origRoomId = param1Object;
        }

        public void setOrigRoomName(String param1String) {
            this.origRoomName = param1String;
        }

        public void setOrigSiteId(String param1String) {
            this.origSiteId = param1String;
        }

        public void setOrignSiteName(String param1String) {
            this.orignSiteName = param1String;
        }

        public void setZh_label(String param1String) {
            this.zh_label = param1String;
        }
    }
}
