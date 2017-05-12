package note.lym.org.noteproject.model.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;

/**
 * 咨询列表
 *
 * @author yaoming.li
 * @since 2017-05-12 10:58
 */
public class HealthList extends DataSupport implements Serializable {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"ret_code":0,"pagebean":{"allPages":256,"contentlist":[{"id":"050082","author":"家庭医生在线 ","time":"2017-05-11 20:19:01","title":"久坐容易加重颈腰椎疾病 久坐族保健小常识","img":"http://img1.gtimg.com/health/pics/hv1/39/34/2209/143648934.jpg","tname":"疾病资讯","tid":"2"},{"id":"050183","author":" 家庭医生在线","time":"2017-05-11 20:20:45","title":"三文鱼有助于预防关节炎 患者饮食注意事项","img":"http://img1.gtimg.com/health/pics/hv1/53/34/2209/143648948.jpg","tname":"疾病资讯","tid":"2"},{"id":"050220","author":" 家庭医生在线","time":"2017-05-11 20:21:38","title":"主食吃得少患心脏病风险大 心脏病如何预防","img":"http://img1.gtimg.com/health/pics/hv1/54/34/2209/143648949.jpg","tname":"疾病资讯","tid":"2"},{"id":"050255","author":"家庭医生在线 ","time":"2017-05-11 20:22:37","title":"牙痛或是肾炎的预警信号 肾炎的症状有哪些","img":"http://img1.gtimg.com/health/pics/hv1/60/34/2209/143648955.jpeg","tname":"疾病资讯","tid":"2"},{"id":"050328","author":" 家庭医生在线","time":"2017-05-11 20:23:46","title":"宝宝口腔溃疡护理注意4要点 食疗方法治溃疡","img":"http://img1.gtimg.com/health/pics/hv1/75/34/2209/143648970.jpg","tname":"疾病资讯","tid":"2"},{"id":"050440","author":"99健康网","time":"2017-05-11 20:25:50","title":"卵巢囊肿怎么引起的 治疗方法有哪些","img":"http://img1.gtimg.com/health/pics/hv1/80/34/2209/143648975.jpg","tname":"疾病资讯","tid":"2"},{"id":"049022","author":"人民网","time":"2017-05-11 19:58:49","title":"首批医保跨省结算医院确定 北京19家入选","img":"http://img1.gtimg.com/health/pics/hv1/189/33/2209/143648829.jpg","tname":"综合资讯","tid":"1"},{"id":"049244","author":"新华社","time":"2017-05-11 20:03:21","title":"国际护士节：优质护理实现三级医院全覆盖 ","img":"http://img1.gtimg.com/health/pics/hv1/219/33/2209/143648859.jpg","tname":"综合资讯","tid":"1"},{"id":"016520","author":"环球网","time":"2017-05-11 10:01:09","title":"国家卫计委：注册护士超350万医护比提升","img":"http://img1.gtimg.com/health/pics/hv1/138/233/2208/143634753.jpg","tname":"综合资讯","tid":"1"},{"id":"016802","author":"环球网","time":"2017-05-11 10:05:04","title":"85%以上地市开展家庭医生签约服务工作！","img":"http://img1.gtimg.com/health/pics/hv1/78/234/2208/143634948.jpg","tname":"综合资讯","tid":"1"},{"id":"023725","author":"家庭医生在线 ","time":"2017-05-10 11:46:54","title":"长期白天驾车增皮肤癌风险 皮肤癌病因有？","img":"http://img1.gtimg.com/health/pics/hv1/62/173/2208/143619377.jpg","tname":"疾病资讯","tid":"2"},{"id":"023167","author":"家庭医生在线","time":"2017-05-10 11:39:16","title":"你知道诱发乳腺癌的原因吗 抑郁情绪要不得","img":"http://img1.gtimg.com/health/pics/hv1/149/172/2208/143619209.jpg","tname":"疾病资讯","tid":"2"},{"id":"023292","author":"家庭医生在线 ","time":"2017-05-10 11:40:52","title":"爱喝咖啡易骨质疏松 骨质疏松的个防治误区","img":"http://img1.gtimg.com/health/pics/hv1/175/172/2208/143619235.jpg","tname":"疾病资讯","tid":"2"},{"id":"023383","author":"家庭医生在线 ","time":"2017-05-10 11:42:10","title":"肝硬化要检测白蛋白 白蛋白低是病情恶化","img":"http://img1.gtimg.com/health/pics/hv1/210/172/2208/143619270.jpg","tname":"疾病资讯","tid":"2"},{"id":"023532","author":"家庭医生在线 ","time":"2017-05-10 11:44:13","title":"常失眠或增心力衰竭风险 心力衰竭如何预防","img":"http://img1.gtimg.com/health/pics/hv1/18/173/2208/143619333.jpg","tname":"疾病资讯","tid":"2"},{"id":"023638","author":" 家庭医生在线 ","time":"2017-05-10 11:45:36","title":"胰岛素使用需避开四误区 保存方法要选对","img":"http://img1.gtimg.com/health/pics/hv1/43/173/2208/143619358.jpg","tname":"疾病资讯","tid":"2"},{"id":"016844","author":"人民网","time":"2017-05-10 10:06:55","title":"食药监总局：密切关注甲氨蝶呤片误用风险","img":"http://img1.gtimg.com/health/pics/hv1/161/134/2208/143609531.jpg","tname":"综合资讯","tid":"1"},{"id":"016848","author":"人民网","time":"2017-05-10 10:07:03","title":"全国各省市修订地方人口与计划生育条例","img":"http://img1.gtimg.com/health/pics/hv1/119/134/2208/143609489.jpg","tname":"综合资讯","tid":"1"},{"id":"016853","author":"人民网","time":"2017-05-10 10:07:09","title":"北京医改\u201c满月\u201d：药费降了 基层就诊多了","img":"http://img1.gtimg.com/health/pics/hv1/75/134/2208/143609445.jpg","tname":"综合资讯","tid":"1"},{"id":"041166","author":"家庭医生在线 ","time":"2017-05-09 18:02:58","title":"缺铁增加患脑卒中风险 脑卒中的病因有哪些","img":"http://img1.gtimg.com/health/pics/hv1/87/129/2208/143608182.jpg","tname":"疾病资讯","tid":"2"}],"currentPage":1,"allNum":5115,"maxResult":20}}
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
         * ret_code : 0
         * pagebean : {"allPages":256,"contentlist":[{"id":"050082","author":"家庭医生在线 ","time":"2017-05-11 20:19:01","title":"久坐容易加重颈腰椎疾病 久坐族保健小常识","img":"http://img1.gtimg.com/health/pics/hv1/39/34/2209/143648934.jpg","tname":"疾病资讯","tid":"2"},{"id":"050183","author":" 家庭医生在线","time":"2017-05-11 20:20:45","title":"三文鱼有助于预防关节炎 患者饮食注意事项","img":"http://img1.gtimg.com/health/pics/hv1/53/34/2209/143648948.jpg","tname":"疾病资讯","tid":"2"},{"id":"050220","author":" 家庭医生在线","time":"2017-05-11 20:21:38","title":"主食吃得少患心脏病风险大 心脏病如何预防","img":"http://img1.gtimg.com/health/pics/hv1/54/34/2209/143648949.jpg","tname":"疾病资讯","tid":"2"},{"id":"050255","author":"家庭医生在线 ","time":"2017-05-11 20:22:37","title":"牙痛或是肾炎的预警信号 肾炎的症状有哪些","img":"http://img1.gtimg.com/health/pics/hv1/60/34/2209/143648955.jpeg","tname":"疾病资讯","tid":"2"},{"id":"050328","author":" 家庭医生在线","time":"2017-05-11 20:23:46","title":"宝宝口腔溃疡护理注意4要点 食疗方法治溃疡","img":"http://img1.gtimg.com/health/pics/hv1/75/34/2209/143648970.jpg","tname":"疾病资讯","tid":"2"},{"id":"050440","author":"99健康网","time":"2017-05-11 20:25:50","title":"卵巢囊肿怎么引起的 治疗方法有哪些","img":"http://img1.gtimg.com/health/pics/hv1/80/34/2209/143648975.jpg","tname":"疾病资讯","tid":"2"},{"id":"049022","author":"人民网","time":"2017-05-11 19:58:49","title":"首批医保跨省结算医院确定 北京19家入选","img":"http://img1.gtimg.com/health/pics/hv1/189/33/2209/143648829.jpg","tname":"综合资讯","tid":"1"},{"id":"049244","author":"新华社","time":"2017-05-11 20:03:21","title":"国际护士节：优质护理实现三级医院全覆盖 ","img":"http://img1.gtimg.com/health/pics/hv1/219/33/2209/143648859.jpg","tname":"综合资讯","tid":"1"},{"id":"016520","author":"环球网","time":"2017-05-11 10:01:09","title":"国家卫计委：注册护士超350万医护比提升","img":"http://img1.gtimg.com/health/pics/hv1/138/233/2208/143634753.jpg","tname":"综合资讯","tid":"1"},{"id":"016802","author":"环球网","time":"2017-05-11 10:05:04","title":"85%以上地市开展家庭医生签约服务工作！","img":"http://img1.gtimg.com/health/pics/hv1/78/234/2208/143634948.jpg","tname":"综合资讯","tid":"1"},{"id":"023725","author":"家庭医生在线 ","time":"2017-05-10 11:46:54","title":"长期白天驾车增皮肤癌风险 皮肤癌病因有？","img":"http://img1.gtimg.com/health/pics/hv1/62/173/2208/143619377.jpg","tname":"疾病资讯","tid":"2"},{"id":"023167","author":"家庭医生在线","time":"2017-05-10 11:39:16","title":"你知道诱发乳腺癌的原因吗 抑郁情绪要不得","img":"http://img1.gtimg.com/health/pics/hv1/149/172/2208/143619209.jpg","tname":"疾病资讯","tid":"2"},{"id":"023292","author":"家庭医生在线 ","time":"2017-05-10 11:40:52","title":"爱喝咖啡易骨质疏松 骨质疏松的个防治误区","img":"http://img1.gtimg.com/health/pics/hv1/175/172/2208/143619235.jpg","tname":"疾病资讯","tid":"2"},{"id":"023383","author":"家庭医生在线 ","time":"2017-05-10 11:42:10","title":"肝硬化要检测白蛋白 白蛋白低是病情恶化","img":"http://img1.gtimg.com/health/pics/hv1/210/172/2208/143619270.jpg","tname":"疾病资讯","tid":"2"},{"id":"023532","author":"家庭医生在线 ","time":"2017-05-10 11:44:13","title":"常失眠或增心力衰竭风险 心力衰竭如何预防","img":"http://img1.gtimg.com/health/pics/hv1/18/173/2208/143619333.jpg","tname":"疾病资讯","tid":"2"},{"id":"023638","author":" 家庭医生在线 ","time":"2017-05-10 11:45:36","title":"胰岛素使用需避开四误区 保存方法要选对","img":"http://img1.gtimg.com/health/pics/hv1/43/173/2208/143619358.jpg","tname":"疾病资讯","tid":"2"},{"id":"016844","author":"人民网","time":"2017-05-10 10:06:55","title":"食药监总局：密切关注甲氨蝶呤片误用风险","img":"http://img1.gtimg.com/health/pics/hv1/161/134/2208/143609531.jpg","tname":"综合资讯","tid":"1"},{"id":"016848","author":"人民网","time":"2017-05-10 10:07:03","title":"全国各省市修订地方人口与计划生育条例","img":"http://img1.gtimg.com/health/pics/hv1/119/134/2208/143609489.jpg","tname":"综合资讯","tid":"1"},{"id":"016853","author":"人民网","time":"2017-05-10 10:07:09","title":"北京医改\u201c满月\u201d：药费降了 基层就诊多了","img":"http://img1.gtimg.com/health/pics/hv1/75/134/2208/143609445.jpg","tname":"综合资讯","tid":"1"},{"id":"041166","author":"家庭医生在线 ","time":"2017-05-09 18:02:58","title":"缺铁增加患脑卒中风险 脑卒中的病因有哪些","img":"http://img1.gtimg.com/health/pics/hv1/87/129/2208/143608182.jpg","tname":"疾病资讯","tid":"2"}],"currentPage":1,"allNum":5115,"maxResult":20}
         */

        private int ret_code;
        private PagebeanBean pagebean;

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public PagebeanBean getPagebean() {
            return pagebean;
        }

        public void setPagebean(PagebeanBean pagebean) {
            this.pagebean = pagebean;
        }

        public static class PagebeanBean {
            /**
             * allPages : 256
             * contentlist : [{"id":"050082","author":"家庭医生在线 ","time":"2017-05-11 20:19:01","title":"久坐容易加重颈腰椎疾病 久坐族保健小常识","img":"http://img1.gtimg.com/health/pics/hv1/39/34/2209/143648934.jpg","tname":"疾病资讯","tid":"2"},{"id":"050183","author":" 家庭医生在线","time":"2017-05-11 20:20:45","title":"三文鱼有助于预防关节炎 患者饮食注意事项","img":"http://img1.gtimg.com/health/pics/hv1/53/34/2209/143648948.jpg","tname":"疾病资讯","tid":"2"},{"id":"050220","author":" 家庭医生在线","time":"2017-05-11 20:21:38","title":"主食吃得少患心脏病风险大 心脏病如何预防","img":"http://img1.gtimg.com/health/pics/hv1/54/34/2209/143648949.jpg","tname":"疾病资讯","tid":"2"},{"id":"050255","author":"家庭医生在线 ","time":"2017-05-11 20:22:37","title":"牙痛或是肾炎的预警信号 肾炎的症状有哪些","img":"http://img1.gtimg.com/health/pics/hv1/60/34/2209/143648955.jpeg","tname":"疾病资讯","tid":"2"},{"id":"050328","author":" 家庭医生在线","time":"2017-05-11 20:23:46","title":"宝宝口腔溃疡护理注意4要点 食疗方法治溃疡","img":"http://img1.gtimg.com/health/pics/hv1/75/34/2209/143648970.jpg","tname":"疾病资讯","tid":"2"},{"id":"050440","author":"99健康网","time":"2017-05-11 20:25:50","title":"卵巢囊肿怎么引起的 治疗方法有哪些","img":"http://img1.gtimg.com/health/pics/hv1/80/34/2209/143648975.jpg","tname":"疾病资讯","tid":"2"},{"id":"049022","author":"人民网","time":"2017-05-11 19:58:49","title":"首批医保跨省结算医院确定 北京19家入选","img":"http://img1.gtimg.com/health/pics/hv1/189/33/2209/143648829.jpg","tname":"综合资讯","tid":"1"},{"id":"049244","author":"新华社","time":"2017-05-11 20:03:21","title":"国际护士节：优质护理实现三级医院全覆盖 ","img":"http://img1.gtimg.com/health/pics/hv1/219/33/2209/143648859.jpg","tname":"综合资讯","tid":"1"},{"id":"016520","author":"环球网","time":"2017-05-11 10:01:09","title":"国家卫计委：注册护士超350万医护比提升","img":"http://img1.gtimg.com/health/pics/hv1/138/233/2208/143634753.jpg","tname":"综合资讯","tid":"1"},{"id":"016802","author":"环球网","time":"2017-05-11 10:05:04","title":"85%以上地市开展家庭医生签约服务工作！","img":"http://img1.gtimg.com/health/pics/hv1/78/234/2208/143634948.jpg","tname":"综合资讯","tid":"1"},{"id":"023725","author":"家庭医生在线 ","time":"2017-05-10 11:46:54","title":"长期白天驾车增皮肤癌风险 皮肤癌病因有？","img":"http://img1.gtimg.com/health/pics/hv1/62/173/2208/143619377.jpg","tname":"疾病资讯","tid":"2"},{"id":"023167","author":"家庭医生在线","time":"2017-05-10 11:39:16","title":"你知道诱发乳腺癌的原因吗 抑郁情绪要不得","img":"http://img1.gtimg.com/health/pics/hv1/149/172/2208/143619209.jpg","tname":"疾病资讯","tid":"2"},{"id":"023292","author":"家庭医生在线 ","time":"2017-05-10 11:40:52","title":"爱喝咖啡易骨质疏松 骨质疏松的个防治误区","img":"http://img1.gtimg.com/health/pics/hv1/175/172/2208/143619235.jpg","tname":"疾病资讯","tid":"2"},{"id":"023383","author":"家庭医生在线 ","time":"2017-05-10 11:42:10","title":"肝硬化要检测白蛋白 白蛋白低是病情恶化","img":"http://img1.gtimg.com/health/pics/hv1/210/172/2208/143619270.jpg","tname":"疾病资讯","tid":"2"},{"id":"023532","author":"家庭医生在线 ","time":"2017-05-10 11:44:13","title":"常失眠或增心力衰竭风险 心力衰竭如何预防","img":"http://img1.gtimg.com/health/pics/hv1/18/173/2208/143619333.jpg","tname":"疾病资讯","tid":"2"},{"id":"023638","author":" 家庭医生在线 ","time":"2017-05-10 11:45:36","title":"胰岛素使用需避开四误区 保存方法要选对","img":"http://img1.gtimg.com/health/pics/hv1/43/173/2208/143619358.jpg","tname":"疾病资讯","tid":"2"},{"id":"016844","author":"人民网","time":"2017-05-10 10:06:55","title":"食药监总局：密切关注甲氨蝶呤片误用风险","img":"http://img1.gtimg.com/health/pics/hv1/161/134/2208/143609531.jpg","tname":"综合资讯","tid":"1"},{"id":"016848","author":"人民网","time":"2017-05-10 10:07:03","title":"全国各省市修订地方人口与计划生育条例","img":"http://img1.gtimg.com/health/pics/hv1/119/134/2208/143609489.jpg","tname":"综合资讯","tid":"1"},{"id":"016853","author":"人民网","time":"2017-05-10 10:07:09","title":"北京医改\u201c满月\u201d：药费降了 基层就诊多了","img":"http://img1.gtimg.com/health/pics/hv1/75/134/2208/143609445.jpg","tname":"综合资讯","tid":"1"},{"id":"041166","author":"家庭医生在线 ","time":"2017-05-09 18:02:58","title":"缺铁增加患脑卒中风险 脑卒中的病因有哪些","img":"http://img1.gtimg.com/health/pics/hv1/87/129/2208/143608182.jpg","tname":"疾病资讯","tid":"2"}]
             * currentPage : 1
             * allNum : 5115
             * maxResult : 20
             */

            private int allPages;
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
                 * id : 050082
                 * author : 家庭医生在线
                 * time : 2017-05-11 20:19:01
                 * title : 久坐容易加重颈腰椎疾病 久坐族保健小常识
                 * img : http://img1.gtimg.com/health/pics/hv1/39/34/2209/143648934.jpg
                 * tname : 疾病资讯
                 * tid : 2
                 */

                private String id;
                private String author;
                private String time;
                private String title;
                private String img;
                private String tname;
                private String tid;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAuthor() {
                    return author;
                }

                public void setAuthor(String author) {
                    this.author = author;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
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

                public String getTname() {
                    return tname;
                }

                public void setTname(String tname) {
                    this.tname = tname;
                }

                public String getTid() {
                    return tid;
                }

                public void setTid(String tid) {
                    this.tid = tid;
                }
            }
        }
    }
}
