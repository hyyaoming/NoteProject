package note.lym.org.noteproject.model.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;

/**
 *  joke bean
 * @author yaoming.li
 * @since 2017-05-11 15:58
 */
public class Joke extends DataSupport implements Serializable{

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"allPages":561,"ret_code":0,"contentlist":[{"id":"585e403c6e36392559c741ef","title":"姿势我已经很老练了，就差个女友了！","img":"http://www.zbjuran.com/uploads/allimg/161224/10-1612241H504D9.gif","type":3,"ct":"2016-12-24 17:30:36.985"},{"id":"585e1cff6e36392559c6c8d8","title":"下次去海边也要试一下","img":"http://www.zbjuran.com/uploads/allimg/161224/10-161224144225N6.gif","type":3,"ct":"2016-12-24 15:00:15.867"},{"id":"585e1cff6e36392559c6c8d7","title":"应该让我来帮你们解决啊","img":"http://www.zbjuran.com/uploads/allimg/161224/10-161224144641359.gif","type":3,"ct":"2016-12-24 15:00:15.866"},{"id":"585e16056e36392559c6ac51","title":"来，接我一球。","img":"http://www.zbjuran.com/uploads/allimg/161224/10-16122414162IO.gif","type":3,"ct":"2016-12-24 14:30:29.134"},{"id":"585e16056e36392559c6ac50","title":"妹子好臂力！","img":"http://www.zbjuran.com/uploads/allimg/161224/10-161224141T1504.gif","type":3,"ct":"2016-12-24 14:30:29.134"},{"id":"585e16056e36392559c6ac4f","title":"给你们感受一下男友力","img":"http://www.zbjuran.com/uploads/allimg/161224/10-161224141324221.gif","type":3,"ct":"2016-12-24 14:30:29.134"},{"id":"585e16056e36392559c6ac4e","title":"什么鱼，游泳的速度太快了！","img":"http://www.zbjuran.com/uploads/allimg/161224/10-161224142353C2.gif","type":3,"ct":"2016-12-24 14:30:29.134"},{"id":"585e16056e36392559c6ac4d","title":"你是想体验一下你家松鼠的生活吗！","img":"http://www.zbjuran.com/uploads/allimg/161224/10-161224142150505.gif","type":3,"ct":"2016-12-24 14:30:29.134"},{"id":"585ceec56e36392559c198c8","title":"如何拍出有B格的照片\u2026\u2026","img":"http://www.zbjuran.com/uploads/allimg/161223/10-1612231AS63R.gif","type":3,"ct":"2016-12-23 17:30:45.604"},{"id":"585ccba56e36392559c11ee2","title":"一广东佛山男子两月内三次被抢手机，实在忍无可忍，正好看到抢他","img":"http://www.zbjuran.com/uploads/allimg/161223/10-161223144TOH.gif","type":3,"ct":"2016-12-23 15:00:53.299"},{"id":"585ccba56e36392559c11ede","title":"滚开点，老娘不搞蕾丝边","img":"http://www.zbjuran.com/uploads/allimg/161223/10-16122314393I25.gif","type":3,"ct":"2016-12-23 15:00:53.046"},{"id":"585cc4836e36392559c100b6","title":"居然就这样屈服了\u2026\u2026","img":"http://www.zbjuran.com/uploads/allimg/161223/10-161223141115I6.gif","type":3,"ct":"2016-12-23 14:30:27.967"},{"id":"585cc4836e36392559c100b3","title":"干脆面好可爱=w=","img":"http://www.zbjuran.com/uploads/allimg/161223/10-161223140AA27.gif","type":3,"ct":"2016-12-23 14:30:27.888"},{"id":"585cbd626e36392559c0e8b0","title":"比赛不如尬舞","img":"http://www.zbjuran.com/uploads/allimg/161223/10-1612231349492H.gif","type":3,"ct":"2016-12-23 14:00:02.408"},{"id":"585cbd626e36392559c0e8af","title":"刚吃了午餐还没消化呢","img":"http://www.zbjuran.com/uploads/allimg/161223/10-161223135311a2.gif","type":3,"ct":"2016-12-23 14:00:02.407"},{"id":"585cbd626e36392559c0e8ae","title":"笨蛋~你不解冻要怎么吃啊~","img":"http://www.zbjuran.com/uploads/allimg/161223/10-161223135133434.gif","type":3,"ct":"2016-12-23 14:00:02.407"},{"id":"585b810d6e36392559bb7e67","title":"风向判断错误\u2026\u2026","img":"http://www.zbjuran.com/uploads/allimg/161222/10-161222145953Q2.gif","type":3,"ct":"2016-12-22 15:30:21.286"},{"id":"585b810d6e36392559bb7e65","title":"这位司机闪避技能点满了吧\u2026\u2026","img":"http://www.zbjuran.com/uploads/allimg/161222/10-16122215035G03.gif","type":3,"ct":"2016-12-22 15:30:21.185"},{"id":"585b7a036e36392559bb6c4c","title":"这车到底有多长！我看半小时了还没全部出来！","img":"http://www.zbjuran.com/uploads/allimg/161222/10-161222144252431.gif","type":3,"ct":"2016-12-22 15:00:19.197"},{"id":"585b79fd6e36392559bb6c44","title":"\u201c我TM就跟你同归于尽！\u201d","img":"http://www.zbjuran.com/uploads/allimg/161222/10-16122214521K06.gif","type":3,"ct":"2016-12-22 15:00:13.730"}],"currentPage":1,"allNum":11204,"maxResult":20}
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
         * allPages : 561
         * ret_code : 0
         * contentlist : [{"id":"585e403c6e36392559c741ef","title":"姿势我已经很老练了，就差个女友了！","img":"http://www.zbjuran.com/uploads/allimg/161224/10-1612241H504D9.gif","type":3,"ct":"2016-12-24 17:30:36.985"},{"id":"585e1cff6e36392559c6c8d8","title":"下次去海边也要试一下","img":"http://www.zbjuran.com/uploads/allimg/161224/10-161224144225N6.gif","type":3,"ct":"2016-12-24 15:00:15.867"},{"id":"585e1cff6e36392559c6c8d7","title":"应该让我来帮你们解决啊","img":"http://www.zbjuran.com/uploads/allimg/161224/10-161224144641359.gif","type":3,"ct":"2016-12-24 15:00:15.866"},{"id":"585e16056e36392559c6ac51","title":"来，接我一球。","img":"http://www.zbjuran.com/uploads/allimg/161224/10-16122414162IO.gif","type":3,"ct":"2016-12-24 14:30:29.134"},{"id":"585e16056e36392559c6ac50","title":"妹子好臂力！","img":"http://www.zbjuran.com/uploads/allimg/161224/10-161224141T1504.gif","type":3,"ct":"2016-12-24 14:30:29.134"},{"id":"585e16056e36392559c6ac4f","title":"给你们感受一下男友力","img":"http://www.zbjuran.com/uploads/allimg/161224/10-161224141324221.gif","type":3,"ct":"2016-12-24 14:30:29.134"},{"id":"585e16056e36392559c6ac4e","title":"什么鱼，游泳的速度太快了！","img":"http://www.zbjuran.com/uploads/allimg/161224/10-161224142353C2.gif","type":3,"ct":"2016-12-24 14:30:29.134"},{"id":"585e16056e36392559c6ac4d","title":"你是想体验一下你家松鼠的生活吗！","img":"http://www.zbjuran.com/uploads/allimg/161224/10-161224142150505.gif","type":3,"ct":"2016-12-24 14:30:29.134"},{"id":"585ceec56e36392559c198c8","title":"如何拍出有B格的照片\u2026\u2026","img":"http://www.zbjuran.com/uploads/allimg/161223/10-1612231AS63R.gif","type":3,"ct":"2016-12-23 17:30:45.604"},{"id":"585ccba56e36392559c11ee2","title":"一广东佛山男子两月内三次被抢手机，实在忍无可忍，正好看到抢他","img":"http://www.zbjuran.com/uploads/allimg/161223/10-161223144TOH.gif","type":3,"ct":"2016-12-23 15:00:53.299"},{"id":"585ccba56e36392559c11ede","title":"滚开点，老娘不搞蕾丝边","img":"http://www.zbjuran.com/uploads/allimg/161223/10-16122314393I25.gif","type":3,"ct":"2016-12-23 15:00:53.046"},{"id":"585cc4836e36392559c100b6","title":"居然就这样屈服了\u2026\u2026","img":"http://www.zbjuran.com/uploads/allimg/161223/10-161223141115I6.gif","type":3,"ct":"2016-12-23 14:30:27.967"},{"id":"585cc4836e36392559c100b3","title":"干脆面好可爱=w=","img":"http://www.zbjuran.com/uploads/allimg/161223/10-161223140AA27.gif","type":3,"ct":"2016-12-23 14:30:27.888"},{"id":"585cbd626e36392559c0e8b0","title":"比赛不如尬舞","img":"http://www.zbjuran.com/uploads/allimg/161223/10-1612231349492H.gif","type":3,"ct":"2016-12-23 14:00:02.408"},{"id":"585cbd626e36392559c0e8af","title":"刚吃了午餐还没消化呢","img":"http://www.zbjuran.com/uploads/allimg/161223/10-161223135311a2.gif","type":3,"ct":"2016-12-23 14:00:02.407"},{"id":"585cbd626e36392559c0e8ae","title":"笨蛋~你不解冻要怎么吃啊~","img":"http://www.zbjuran.com/uploads/allimg/161223/10-161223135133434.gif","type":3,"ct":"2016-12-23 14:00:02.407"},{"id":"585b810d6e36392559bb7e67","title":"风向判断错误\u2026\u2026","img":"http://www.zbjuran.com/uploads/allimg/161222/10-161222145953Q2.gif","type":3,"ct":"2016-12-22 15:30:21.286"},{"id":"585b810d6e36392559bb7e65","title":"这位司机闪避技能点满了吧\u2026\u2026","img":"http://www.zbjuran.com/uploads/allimg/161222/10-16122215035G03.gif","type":3,"ct":"2016-12-22 15:30:21.185"},{"id":"585b7a036e36392559bb6c4c","title":"这车到底有多长！我看半小时了还没全部出来！","img":"http://www.zbjuran.com/uploads/allimg/161222/10-161222144252431.gif","type":3,"ct":"2016-12-22 15:00:19.197"},{"id":"585b79fd6e36392559bb6c44","title":"\u201c我TM就跟你同归于尽！\u201d","img":"http://www.zbjuran.com/uploads/allimg/161222/10-16122214521K06.gif","type":3,"ct":"2016-12-22 15:00:13.730"}]
         * currentPage : 1
         * allNum : 11204
         * maxResult : 20
         */

        private int allPages;
        private int ret_code;
        private int currentPage;
        private int allNum;
        private int maxResult;
        private List<ContentlistBean> contentlist;

        public int getAllPages() {
            return allPages;
        }

        public void setAllPages(int allPages) {
            this.allPages = allPages;
        }

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getAllNum() {
            return allNum;
        }

        public void setAllNum(int allNum) {
            this.allNum = allNum;
        }

        public int getMaxResult() {
            return maxResult;
        }

        public void setMaxResult(int maxResult) {
            this.maxResult = maxResult;
        }

        public List<ContentlistBean> getContentlist() {
            return contentlist;
        }

        public void setContentlist(List<ContentlistBean> contentlist) {
            this.contentlist = contentlist;
        }

        public static class ContentlistBean {
            /**
             * id : 585e403c6e36392559c741ef
             * title : 姿势我已经很老练了，就差个女友了！
             * img : http://www.zbjuran.com/uploads/allimg/161224/10-1612241H504D9.gif
             * type : 3
             * ct : 2016-12-24 17:30:36.985
             */

            private String id;
            private String title;
            private String img;
            private int type;
            private String ct;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getCt() {
                return ct;
            }

            public void setCt(String ct) {
                this.ct = ct;
            }
        }
    }
}
