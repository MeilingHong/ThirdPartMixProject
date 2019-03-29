package com.meiling.qq.common.bean;

/**
 * Created by Hz on 2018-12-11.
 */

public class QQInfoBean {
        private String union_id;
        private String access_token;
        private String expires_in;//单位秒---long型
        private String openId;

        public String getUnion_id() {
            return union_id;
        }

        public void setUnion_id(String union_id) {
            this.union_id = union_id;
        }

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(String expires_in) {
            this.expires_in = expires_in;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

    @Override
    public String toString() {
        return "QQInfoBean{" +
                "union_id='" + union_id + '\'' +
                ", access_token='" + access_token + '\'' +
                ", expires_in='" + expires_in + '\'' +
                ", openId='" + openId + '\'' +
                '}';
    }
}
