package com.zack.article.Data;

import com.zack.article.bean.ArticleBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by Zackv on 2017/10/8.
 */

public class DataUtils {

    public static String TODAYURL = "http://meiriyiwen.com";
    public static String RANDOMURL = "http://meiriyiwen.com/random";

    public static ArticleBean getArticle(){

        String author = "zack";
        String title = "你好，陌生人";
        String content = "我端着满满一纸箱子垃圾，向马路尽头的垃圾堆走去。半路上，路过的一头牛看了我一眼，然后立刻两眼发光──当时我还以为是错觉，也没管那么多，继续往前走。那牛则从栏杆那边绕过来，寸步不离跟着我，而且愈发加快了速度，想超过我。真是奇怪。远远地，马路南边又有两头牛几乎在同一时间发现了我，也争先恐后跑来了。我扭头往东边看，不知什么时候又跟上了五六头。真有些急了，不禁加快了步子，后来干脆小跑了起来。后面的牛越跟越多，也不知从哪儿突然就冒出来的，好像半个库尔图的牛都从各个旮旯角落集中过来了，浩浩荡荡，追着我狂奔。我魂都骇飞了，回头瞟一眼，一大片又尖又硬的牛角，乱纷纷的牛蹄子。我大喊：“这是怎么了！咋回事？”马路上人虽然不多，三三两两的也不少，都隔了篱笆冲我哈哈大笑。我也来不及去恨他们了，魂飞胆裂，还没冲到垃圾堆就“啪”地把垃圾扔了，箱子也不要了。人也不停住，直直地冲向垃圾堆，冲上垃圾堆，冲过垃圾堆，头也不回向对面的雪野跑去。远远地又听到有人在大笑。我气喘吁吁回头一看，奇怪，追兵一个也没了，比突然跟上我时还要突然。再一看，它们此时正扎扎实实围在垃圾堆边起内讧。好像在争抢什么东西，你拱我刨，撕抢追抵，好不热闹。这时有一头牛左右突围，杀开一条血路冲将出来，嘴里牢牢衔着它的战利品──我恍然大悟，那是我用来装垃圾的纸箱子。\n" +
                "\n" +
                "    我就那样站在茫茫雪原上，远远看着百牛奔腾，追逐前面的那头心犹不甘的牛英雄──就跟追我时一个架势。\n" +
                "\n" +
                "    经常被这种情景打动的还有我外婆。她刚从南方来，哪里见过这等场面！每每唏嘘不已，一有时间就在柜台里清腾东西，腾出不少空纸箱，跑去喂牛。没办法，她信佛，很有好生之德。这下好了，整条马路两边的门面房前，就我家门口聚集的牛最多，整整齐齐一直排到三岔路口，脑袋齐刷刷冲我家大门望着，门一开便闻风而动。我家哪里有那么多纸箱子喂它们啊？\n" +
                "\n" +
                "    牛在冬天实在可怜，一个夏天来狠积狠攒的大块肥膘，不到两个月便消得屁股尖尖，一身骨架子。只好咬紧牙关，熬到下一个夏天再报复一般地猛吃几个月。如此一张一弛，反差剧烈，弄得牛可能想不通世界到底咋回事，既然会有暖和碧绿的夏天，为什么又会有积雪覆盖、寸草不生的冬天呢？因此我们这里的牛都非常神经质，非常吓人。\n" +
                "\n" +
                "    有一次我一推开门就迎面撞上一头牛，被死死堵在门口，出不了门。它的脑袋伸进门框，牛角直直硬硬地戳着，牛眼一动不动盯着你──我上门讨债也这样看过人。于是我也不动，静静望着它。两下较劲，很快败下阵来。我不是它的对手，我目光的神威只能维持一到两分钟，久了便虚了，不由自主换了苦苦哀求的神情：“你咋还不走？求你走吧？”──它仍牛眼炯炯，意味深长。若是个人，我一把推他个转身就出去了。可它是牛，几百公斤的东西，况且还有角……我妈才可笑──也可能在逗我们开心吧。她学电影里的，一个劲儿说：“喂，你后面是什么？快看，看你的后面……”──它要是能上当就是天下最聪明的牛了。\n" +
                "\n" +
                "    反正死活不走，于是我们的门也没法关上，房间里白气腾腾，越来越冷。\n" +
                "\n" +
                "    至于后来怎么解决的？还是纸箱子的功劳。\n" +
                "\n" +
                "    我妈便一个劲儿地埋怨外婆，说都是她把附近这一带的牛全惯坏了，我家简直成了牛的慈善机构。\n" +
                "\n" +
                "    后来我妈又埋怨本地的哈萨克老乡不好好喂牛，都太懒了。此言一出，引致众愤。她缄默。但还是没办法相信那些路上整天到处转悠的牛全是喂过的。我们不止一次看到它们在冰天雪地中不安地四处拱嗅，啃木头桩子，并啃吃自己粪便──真是饿疯了。我外婆叹口气，又去翻天翻地找纸箱子。\n" +
                "\n" +
                "    有时候，得了只空箱子，附近却一时不见牛踪，她老人家便冒着零下二三十度的大冷天，满村找牛。找到了扔过去就赶紧往家跑。自己冻坏了不说，还让牛们为此起内讧，打群架。我妈说：“就把箱子撂在门口，让它自己来吃嘛。”我外婆一想也是。可到了下一次，还是忍不住跑出去，大老远的亲自送到牛嘴边。亲眼看着被施予者接受自己心意是不是很快乐？冬天太冷，除了这个，她很少有出门的借口。外婆多么寂寞。\n" +
                "\n" +
                "    我们家乡的黄牯牛啊水牛啊都是用来犁地的，她从来没有见过新疆的牛干过活，甚至连牛车都很少见一辆。可是，她可能认定新疆的牛一定是因为好吃懒做才落得如此下场──三九寒天还流落街上没人管，自己四处找吃的。到处是冰雪，皑皑到天边，哪有吃的！而牛一个劲地长流透明的涎液，她则认为是它们感冒了，类似于人流清鼻涕。她都不知道牛皮有多厚，迟暮的老人，总是会像孩子一样天真。\n" +
                "\n" +
                "    我常常在一旁悄悄观察我外婆、我妈两人与牛之间的……暂且称之为是“交往”吧。我知道她们对万物始终保持着一种天生的亲近，却不能明白这亲近从何而来。为什么我就没有那样的亲近感呢？是不是每个人到了一定年龄后，才会顺着最初一路走来的痕迹，再原路走回去？衰老是一种什么样的力量？是一种什么样的冬天？我每天看着我妈进进出出都在与身边的牛自然地打着招呼，别人可能只会觉得她是一个天真风趣的人。而我，则总是想到冥冥之中类似于因缘的某种事物的作崇。细想之下，不禁恐怖。母亲离我多么遥远，好像我们分别处在夏天与冬天。很多时候我都感觉不到她，就像感觉不到一头牛在冬天所能感觉到的那些。\n" +
                "\n" +
                "    我猜想牛在冬天一定比夏天想得多一点，否则它不会那么不安。在冬天里，牛们因饥饿而更加寒冷，因其身空乏、世界白寂而不安，于是它们失去了夏日的天真驯和。其实我们也不喜欢冬天，我们被重重大雪困在村庄里，焦躁、沉闷，围着室中炉火，想着春天。牛在冰天雪地中四处徘徊，就像我们在深暗的货架柜台后面一整天一整天地静坐冥想。没有生意。冬天多么漫长难熬，牛在身边走来走去，我想它们所寻找的可能不仅仅是食物，还有出口，通向暖和天气的出口。然后我们就跟着它一起走出去。\n" +
                "\n" +
                "    呵呵，其实我们还是挺喜欢牛的，如果它后来不偷吃我家储存在门楣上的芹菜和大葱的话。──放那么高，亏它也能够得着！我妈气得要死，那天几乎围着库尔图把那头牛撵了一大圈。回家后我们就只好吃咸菜炖土豆。从那以后，那头牛就经常来，长时间翘首往我们家门上观望。可惜再没有这样的好事了。但它还是每天都来，一直守株待兔到春天为止。我们谁都没想到绿色食品如此强烈地刺激了它的记忆──第二年冬天它还来，还那样吓人地仰着脖子往我家门楣上看。";


        return new ArticleBean(author,title,content);
    }

    public  static ArticleBean getArticleToday() throws IOException {
        // 从 URL 直接加载 HTML 文档
        Document doc2=null;

        doc2 = Jsoup.connect(TODAYURL).get();
        //if(doc2.toString()==null)return  null;
        String context = doc2.body().toString();
        context=context.replace("id=\"article_show\"", "class=\"container\"");
        context=context.replace("article_author", "articleAuthorName");
        context=context.replace("article_text", "articleContent");
        context=context.replaceAll("<h1>", "<h1 class=\"articleTitle\">");
        context=context.replaceAll("<a.*/a>","");
        context=context.replaceAll("<p style.*/p>","");
        doc2 = Jsoup.parse(context);
        String title = doc2.getElementsByAttributeValue("class","articleTitle").text();
        String author = doc2.getElementsByAttributeValue("class","articleAuthorName").text();
        String text = doc2.getElementsByAttributeValue("class","articleContent").toString();
        text = text.replace("<div class=\"articleContent\">","");
        text = text.replace("</div>","");
        text = text.replaceAll("<.*?>","");
        //return context_html;
        return new ArticleBean(author,title,text);
    }

    public static ArticleBean getArticleRandom(){
        // 从 URL 直接加载 HTML 文档
        Document doc2=null;
        try {
            doc2 = Jsoup.connect(RANDOMURL).get();
            //if(doc2.toString()==null)return  null;
            String context = doc2.body().toString();
            context=context.replace("id=\"article_show\"", "class=\"container\"");
            context=context.replace("article_author", "articleAuthorName");
            context=context.replace("article_text", "articleContent");
            context=context.replaceAll("<h1>", "<h1 class=\"articleTitle\">");
            context=context.replaceAll("<a.*/a>","");
            context=context.replaceAll("<p style.*/p>","");
            doc2 = Jsoup.parse(context);
            String title = doc2.getElementsByAttributeValue("class","articleTitle").text();
            String author = doc2.getElementsByAttributeValue("class","articleAuthorName").text();
            String text = doc2.getElementsByAttributeValue("class","articleContent").toString();
            text = text.replace("<div class=\"articleContent\">","");
            text = text.replace("</div>","");
            text = text.replace("</p>","\n");
            text = text.replaceAll("<.*?>","");
            text = text.replace(" ","");
            text = text.substring(0,text.length()-1);
            //return context_html;
            return new ArticleBean(author,title,text);
        } catch (IOException e) {
            e.printStackTrace();
        }
            return null;
    }

}