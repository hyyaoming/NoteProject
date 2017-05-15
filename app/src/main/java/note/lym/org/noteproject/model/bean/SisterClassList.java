package note.lym.org.noteproject.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author yaoming.li
 * @since 2017-05-12 17:32
 */
public class SisterClassList implements Serializable {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"total":894,"ret_code":0,"ret_message":"Success","data":[{"id":3000,"title":"曼陀罗","ctime":1490462310,"imgcount":14,"imgurl":"http://images.seqier.com/2017032601/58d6a6675f631.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":2999,"title":"情绪私房{欲望}！","ctime":1490462298,"imgcount":9,"imgurl":"http://images.seqier.com/2017032601/58d6a65b35870.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":2998,"title":"刘亚峰作品《待我长发及腰，你娶我可好》","ctime":1490462291,"imgcount":5,"imgurl":"http://images.seqier.com/2017032601/58d6a65396a32.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":2997,"title":"【清欢映像】agirlinher20s","ctime":1490462276,"imgcount":9,"imgurl":"http://images.seqier.com/2017032601/58d6a645172a4.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":2996,"title":"心.逸","ctime":1490462268,"imgcount":10,"imgurl":"http://images.seqier.com/2017032601/58d6a63c9b171.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":2995,"title":"伶","ctime":1490462255,"imgcount":10,"imgurl":"http://images.seqier.com/2017032601/58d6a6304e07d.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":2994,"title":"弓道少女","ctime":1490462239,"imgcount":16,"imgurl":"http://images.seqier.com/2017032601/58d6a6207e70c.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":2993,"title":"【碧水天颜2015作品】明天我要嫁给你了","ctime":1490462230,"imgcount":10,"imgurl":"http://images.seqier.com/2017032601/58d6a617630c2.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":2992,"title":"【碧水天颜2015作品】夜色撩人","ctime":1490462218,"imgcount":9,"imgurl":"http://images.seqier.com/2017032601/58d6a60b1c1e4.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":2991,"title":"暗夜精灵【碧水天颜2015作品】","ctime":1490462208,"imgcount":9,"imgurl":"http://images.seqier.com/2017032601/58d6a600d1b1c.jpg?imageView2/2/w/240/q/75|imageslim"}]}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        /**
         * total : 894
         * ret_code : 0
         * ret_message : Success
         * data : [{"id":3000,"title":"曼陀罗","ctime":1490462310,"imgcount":14,"imgurl":"http://images.seqier.com/2017032601/58d6a6675f631.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":2999,"title":"情绪私房{欲望}！","ctime":1490462298,"imgcount":9,"imgurl":"http://images.seqier.com/2017032601/58d6a65b35870.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":2998,"title":"刘亚峰作品《待我长发及腰，你娶我可好》","ctime":1490462291,"imgcount":5,"imgurl":"http://images.seqier.com/2017032601/58d6a65396a32.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":2997,"title":"【清欢映像】agirlinher20s","ctime":1490462276,"imgcount":9,"imgurl":"http://images.seqier.com/2017032601/58d6a645172a4.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":2996,"title":"心.逸","ctime":1490462268,"imgcount":10,"imgurl":"http://images.seqier.com/2017032601/58d6a63c9b171.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":2995,"title":"伶","ctime":1490462255,"imgcount":10,"imgurl":"http://images.seqier.com/2017032601/58d6a6304e07d.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":2994,"title":"弓道少女","ctime":1490462239,"imgcount":16,"imgurl":"http://images.seqier.com/2017032601/58d6a6207e70c.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":2993,"title":"【碧水天颜2015作品】明天我要嫁给你了","ctime":1490462230,"imgcount":10,"imgurl":"http://images.seqier.com/2017032601/58d6a617630c2.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":2992,"title":"【碧水天颜2015作品】夜色撩人","ctime":1490462218,"imgcount":9,"imgurl":"http://images.seqier.com/2017032601/58d6a60b1c1e4.jpg?imageView2/2/w/240/q/75|imageslim"},{"id":2991,"title":"暗夜精灵【碧水天颜2015作品】","ctime":1490462208,"imgcount":9,"imgurl":"http://images.seqier.com/2017032601/58d6a600d1b1c.jpg?imageView2/2/w/240/q/75|imageslim"}]
         */

        private int total;
        private int ret_code;
        private String ret_message;
        private List<DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public String getRet_message() {
            return ret_message;
        }

        public void setRet_message(String ret_message) {
            this.ret_message = ret_message;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 3000
             * title : 曼陀罗
             * ctime : 1490462310
             * imgcount : 14
             * imgurl : http://images.seqier.com/2017032601/58d6a6675f631.jpg?imageView2/2/w/240/q/75|imageslim
             */

            private int id;
            private String title;
            private int ctime;
            private int imgcount;
            private String imgurl;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getCtime() {
                return ctime;
            }

            public void setCtime(int ctime) {
                this.ctime = ctime;
            }

            public int getImgcount() {
                return imgcount;
            }

            public void setImgcount(int imgcount) {
                this.imgcount = imgcount;
            }

            public String getImgurl() {
                return imgurl;
            }

            public void setImgurl(String imgurl) {
                this.imgurl = imgurl;
            }
        }
    }
}
