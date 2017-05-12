package note.lym.org.noteproject.model.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * 健康资讯详情
 *
 * @author yaoming.li
 * @since 2017-05-12 14:39
 */
public class HealthDetail extends DataSupport implements Serializable {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"ret_code":0,"item":{"content":"牙病虽小，但与全身器官关系非常密切，龋坏的牙齿会引起远离器官发病，这要涉及到\u201c病灶\u201d学说。所谓\u201c病灶\u201d学说即在局限的病变组织区域中有外源性致病微生物感染，感染的微生物或它所产生的毒素发生转移，引起不与病灶直接相连的远离器官组织发病。有些风湿病、肾炎、眼病如虹膜睫状体炎、视神经炎等或者不明原因的全身发热等可能与病灶牙有关。因此，定期进行口腔健康检查很有必要，一旦发现牙病应该尽早治疗，对不能治愈的，且无保留价值的患牙，应该拔除，以彻底消除病灶。肾炎的早期症状表现有哪些1、急性肾小球肾炎和慢性肾小球肾炎病人在急性发作的时候常常与咽炎、扁桃体炎、上呼吸道及皮肤等感染一样。为此，当得了上述感染性疾病时应检查尿常规2、肾小球肾炎病人虽然缺乏特异性病症，但初期常常出现疲劳，乏力，腰痛，眼睑、颜面、踝关节浮肿，尿中泡沫增加，尿色异常。为此，浮现这些症状时，应到医院检查尿常规。3、早期尿毒症患者常常浮现食欲减退、恶心、皮肤瘙痒等现象。为此，有这些症状，特别是合并高血压、贫血的患者肯定要检查肾脏功能。4、中度上面的慢性肾功能不全病人会合并贫血，显现乏力、头晕、面色苍白等症状。贫血的患者如除外血液系统疾病，应留意是否存在慢性肾功能不全。5、1/3的肾小球肾炎患者会显现血压升高，现象为头痛、记忆力减退、睡眠不佳症状等。因上述症状就医、并且发觉血压升高的患者务必要检查尿常规，特别是年轻患者。6、慢性肾功能不全的病人在早期会表现为夜间排尿的次数增多和尿量的增加。健康人一旦睡前没有大量饮水，夜间睡眠后应不排尿或仅排尿1次，如若经常夜间排尿2次总而言之，应到医院检查尿常规和肾脏功能。肾炎患者在日常生活中要注意以下几点：1、注意休息，避免劳累，避免情绪波动及精神刺激。防止昆虫叮咬。去除可能的过敏原，2、注意保暖，防止感冒。控制和预防感染，在有明确的感染或感染灶时选用敏感的抗生素，但应避免盲目地预防性使用抗生素。3、注意饮食， 因过敏性紫癜多为过敏原引起，应禁食 生葱、生蒜、辣椒、酒类等刺激性食品，肉类、海鲜、应避免与花粉等过敏原相接触。","id":"050255","author":"家庭医生在线 ","time":"2017-05-11 20:22:37","title":"牙痛或是肾炎的预警信号 肾炎的症状有哪些","img":"http://img1.gtimg.com/health/pics/hv1/60/34/2209/143648955.jpeg","tname":"疾病资讯","tid":"2"}}
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
         * item : {"content":"牙病虽小，但与全身器官关系非常密切，龋坏的牙齿会引起远离器官发病，这要涉及到\u201c病灶\u201d学说。所谓\u201c病灶\u201d学说即在局限的病变组织区域中有外源性致病微生物感染，感染的微生物或它所产生的毒素发生转移，引起不与病灶直接相连的远离器官组织发病。有些风湿病、肾炎、眼病如虹膜睫状体炎、视神经炎等或者不明原因的全身发热等可能与病灶牙有关。因此，定期进行口腔健康检查很有必要，一旦发现牙病应该尽早治疗，对不能治愈的，且无保留价值的患牙，应该拔除，以彻底消除病灶。肾炎的早期症状表现有哪些1、急性肾小球肾炎和慢性肾小球肾炎病人在急性发作的时候常常与咽炎、扁桃体炎、上呼吸道及皮肤等感染一样。为此，当得了上述感染性疾病时应检查尿常规2、肾小球肾炎病人虽然缺乏特异性病症，但初期常常出现疲劳，乏力，腰痛，眼睑、颜面、踝关节浮肿，尿中泡沫增加，尿色异常。为此，浮现这些症状时，应到医院检查尿常规。3、早期尿毒症患者常常浮现食欲减退、恶心、皮肤瘙痒等现象。为此，有这些症状，特别是合并高血压、贫血的患者肯定要检查肾脏功能。4、中度上面的慢性肾功能不全病人会合并贫血，显现乏力、头晕、面色苍白等症状。贫血的患者如除外血液系统疾病，应留意是否存在慢性肾功能不全。5、1/3的肾小球肾炎患者会显现血压升高，现象为头痛、记忆力减退、睡眠不佳症状等。因上述症状就医、并且发觉血压升高的患者务必要检查尿常规，特别是年轻患者。6、慢性肾功能不全的病人在早期会表现为夜间排尿的次数增多和尿量的增加。健康人一旦睡前没有大量饮水，夜间睡眠后应不排尿或仅排尿1次，如若经常夜间排尿2次总而言之，应到医院检查尿常规和肾脏功能。肾炎患者在日常生活中要注意以下几点：1、注意休息，避免劳累，避免情绪波动及精神刺激。防止昆虫叮咬。去除可能的过敏原，2、注意保暖，防止感冒。控制和预防感染，在有明确的感染或感染灶时选用敏感的抗生素，但应避免盲目地预防性使用抗生素。3、注意饮食， 因过敏性紫癜多为过敏原引起，应禁食 生葱、生蒜、辣椒、酒类等刺激性食品，肉类、海鲜、应避免与花粉等过敏原相接触。","id":"050255","author":"家庭医生在线 ","time":"2017-05-11 20:22:37","title":"牙痛或是肾炎的预警信号 肾炎的症状有哪些","img":"http://img1.gtimg.com/health/pics/hv1/60/34/2209/143648955.jpeg","tname":"疾病资讯","tid":"2"}
         */

        private int ret_code;
        private ItemBean item;

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public ItemBean getItem() {
            return item;
        }

        public void setItem(ItemBean item) {
            this.item = item;
        }

        public static class ItemBean {
            /**
             * content : 牙病虽小，但与全身器官关系非常密切，龋坏的牙齿会引起远离器官发病，这要涉及到“病灶”学说。所谓“病灶”学说即在局限的病变组织区域中有外源性致病微生物感染，感染的微生物或它所产生的毒素发生转移，引起不与病灶直接相连的远离器官组织发病。有些风湿病、肾炎、眼病如虹膜睫状体炎、视神经炎等或者不明原因的全身发热等可能与病灶牙有关。因此，定期进行口腔健康检查很有必要，一旦发现牙病应该尽早治疗，对不能治愈的，且无保留价值的患牙，应该拔除，以彻底消除病灶。肾炎的早期症状表现有哪些1、急性肾小球肾炎和慢性肾小球肾炎病人在急性发作的时候常常与咽炎、扁桃体炎、上呼吸道及皮肤等感染一样。为此，当得了上述感染性疾病时应检查尿常规2、肾小球肾炎病人虽然缺乏特异性病症，但初期常常出现疲劳，乏力，腰痛，眼睑、颜面、踝关节浮肿，尿中泡沫增加，尿色异常。为此，浮现这些症状时，应到医院检查尿常规。3、早期尿毒症患者常常浮现食欲减退、恶心、皮肤瘙痒等现象。为此，有这些症状，特别是合并高血压、贫血的患者肯定要检查肾脏功能。4、中度上面的慢性肾功能不全病人会合并贫血，显现乏力、头晕、面色苍白等症状。贫血的患者如除外血液系统疾病，应留意是否存在慢性肾功能不全。5、1/3的肾小球肾炎患者会显现血压升高，现象为头痛、记忆力减退、睡眠不佳症状等。因上述症状就医、并且发觉血压升高的患者务必要检查尿常规，特别是年轻患者。6、慢性肾功能不全的病人在早期会表现为夜间排尿的次数增多和尿量的增加。健康人一旦睡前没有大量饮水，夜间睡眠后应不排尿或仅排尿1次，如若经常夜间排尿2次总而言之，应到医院检查尿常规和肾脏功能。肾炎患者在日常生活中要注意以下几点：1、注意休息，避免劳累，避免情绪波动及精神刺激。防止昆虫叮咬。去除可能的过敏原，2、注意保暖，防止感冒。控制和预防感染，在有明确的感染或感染灶时选用敏感的抗生素，但应避免盲目地预防性使用抗生素。3、注意饮食， 因过敏性紫癜多为过敏原引起，应禁食 生葱、生蒜、辣椒、酒类等刺激性食品，肉类、海鲜、应避免与花粉等过敏原相接触。
             * id : 050255
             * author : 家庭医生在线
             * time : 2017-05-11 20:22:37
             * title : 牙痛或是肾炎的预警信号 肾炎的症状有哪些
             * img : http://img1.gtimg.com/health/pics/hv1/60/34/2209/143648955.jpeg
             * tname : 疾病资讯
             * tid : 2
             */

            private String content;
            private String id;
            private String author;
            private String time;
            private String title;
            private String img;
            private String tname;
            private String tid;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

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
