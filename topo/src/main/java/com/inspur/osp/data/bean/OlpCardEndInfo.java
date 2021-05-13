package com.inspur.osp.data.bean;

/**
 * Created by liuchen01 on 2019/10/25.
 */

public class OlpCardEndInfo {


    /**
     * INT_ID : 1936533430
     * ZH_LABEL : 石家庄开发区干线
     * card : {"INT_ID":1954167100,"ZH_LABEL":"石家庄开发区干线-OASN3#-二干中继环八 石家庄市公司方向"}
     */

    private String INT_ID;
    private String ZH_LABEL;
    private CardBean card;

    public String getINT_ID() {
        return INT_ID;
    }

    public void setINT_ID(String INT_ID) {
        this.INT_ID = INT_ID;
    }

    public String getZH_LABEL() {
        return ZH_LABEL;
    }

    public void setZH_LABEL(String ZH_LABEL) {
        this.ZH_LABEL = ZH_LABEL;
    }

    public CardBean getCard() {
        return card;
    }

    public void setCard(CardBean card) {
        this.card = card;
    }

    public static class CardBean {
        /**
         * INT_ID : 1954167100
         * ZH_LABEL : 石家庄开发区干线-OASN3#-二干中继环八 石家庄市公司方向
         */

        private String INT_ID;
        private String ZH_LABEL;

        public String getINT_ID() {
            return INT_ID;
        }

        public void setINT_ID(String INT_ID) {
            this.INT_ID = INT_ID;
        }

        public String getZH_LABEL() {
            return ZH_LABEL;
        }

        public void setZH_LABEL(String ZH_LABEL) {
            this.ZH_LABEL = ZH_LABEL;
        }
    }
}
