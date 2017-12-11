package com.grunka.adventofcode;

import java.util.*;
import java.util.stream.Collectors;

public class Day7 {
    public static void main(String[] args) {
        Map<String, Program> mapping = parseTree(INPUT);

        Set<String> allNonRoot = mapping.values().stream().flatMap(p -> p.programs.stream()).collect(Collectors.toSet());
        List<Program> roots = mapping.values().stream().filter(p -> !allNonRoot.contains(p.name)).collect(Collectors.toList());
        System.out.println("roots = " + roots);
    }

    private static Map<String, Program> parseTree(String input) {
        Map<String, Program> mapping = new HashMap<>();
        for (String row : input.split("\n")) {
            String node;
            String branches;
            String[] split = row.split(" -> ");
            if (split.length == 2) {
                node = split[0];
                branches = split[1];
            } else {
                node = row;
                branches = "";
            }
            String[] nameAndWeight = node.split(" ");
            mapping.put(nameAndWeight[0], new Program(nameAndWeight[0], Integer.parseInt(nameAndWeight[1].substring(1, nameAndWeight[1].length() - 1)), Arrays.stream(branches.split(", ")).collect(Collectors.toList())));
        }
        return mapping;
    }

    private static class Program {
        final String name;
        final int weight;
        final List<String> programs;

        private Program(String name, int weight, List<String> programs) {
            this.name = name;
            this.weight = weight;
            this.programs = programs;
        }

        @Override
        public String toString() {
            return "Program{" +
                    "name='" + name + '\'' +
                    ", weight=" + weight +
                    ", programs=" + programs +
                    '}';
        }
    }

    private static final String TEST_INPUT = "pbga (66)\n" +
            "xhth (57)\n" +
            "ebii (61)\n" +
            "havc (66)\n" +
            "ktlj (57)\n" +
            "fwft (72) -> ktlj, cntj, xhth\n" +
            "qoyq (66)\n" +
            "padx (45) -> pbga, havc, qoyq\n" +
            "tknk (41) -> ugml, padx, fwft\n" +
            "jptl (61)\n" +
            "ugml (68) -> gyxo, ebii, jptl\n" +
            "gyxo (61)\n" +
            "cntj (57)";

    private static final String INPUT = "bxlur (38)\n" +
            "vgeifn (90)\n" +
            "ehsqyyb (174) -> xtcdt, tujcuy, wiqohmb, cxdwmu\n" +
            "xeqle (55)\n" +
            "rvycd (1905) -> hspnsfg, fyvlf, lgkbca\n" +
            "lvohqo (41)\n" +
            "vbiwa (88)\n" +
            "jwvkdyy (25) -> eblot, chpvnf, uoasog, upilubg\n" +
            "ymednx (75)\n" +
            "snqeb (24)\n" +
            "rrnhzu (72)\n" +
            "arouyz (59)\n" +
            "bgkxjiw (75)\n" +
            "jhxdgxv (7)\n" +
            "atkes (219) -> cbwagy, dtzjt\n" +
            "zqicjh (86)\n" +
            "rurhdvo (19)\n" +
            "aijgw (5) -> ikegi, gafbnc\n" +
            "aymrru (41)\n" +
            "vmreal (6560) -> fpqpaes, fjxvx, nyrjj\n" +
            "qdqkaw (2229) -> hyukvw, oahdsnf, qizgiau\n" +
            "xhibern (90)\n" +
            "tzclfir (8)\n" +
            "eyumraw (99)\n" +
            "datez (58)\n" +
            "ddbixap (88)\n" +
            "ipvdfc (84)\n" +
            "hycmwzu (89)\n" +
            "gjfmlc (263) -> gyehzo, qflfg\n" +
            "cbrfntx (21)\n" +
            "xpjgixx (58)\n" +
            "wonqggh (7)\n" +
            "rsjswd (199) -> lrqffb, lvgrf\n" +
            "fihqkla (7)\n" +
            "mdubrq (69)\n" +
            "kjjuo (8)\n" +
            "xgefj (81)\n" +
            "yjuzr (45)\n" +
            "jxqmtj (62) -> ryahia, cidce, vkdslcc, kdnrfhs, agyir, bukih, cdmidbh\n" +
            "rrqud (63)\n" +
            "gykhcku (31) -> tthspe, ohxvh\n" +
            "ikmldw (97) -> hjjchx, ecbgp\n" +
            "gqauurp (79)\n" +
            "xzmax (49)\n" +
            "pvtmulz (5)\n" +
            "hwgbp (34)\n" +
            "imafbhs (8)\n" +
            "qfxeio (21)\n" +
            "xtvqhc (10)\n" +
            "gybqyo (214) -> azdtrv, oiyow\n" +
            "dqtls (5)\n" +
            "pxxvg (63)\n" +
            "aulkaf (42)\n" +
            "xkdjnv (1697) -> kwenr, sszjzj, ierxej\n" +
            "maokm (1440) -> hrixd, ixxaaz, cewfc\n" +
            "jably (202) -> tfxzu, ntnjsb, ueoyysn, qrmbao\n" +
            "pxijthu (72)\n" +
            "nfebqt (61)\n" +
            "ywvxjtz (76)\n" +
            "ylwwnxo (79)\n" +
            "raqfhc (21)\n" +
            "pzpwzpp (61)\n" +
            "ctcykka (7)\n" +
            "zxrpdab (185) -> rjzmzv, spfma\n" +
            "fhmeku (190) -> jvjim, dgwfi\n" +
            "brwizly (150) -> feogbnr, nygkcd, pfolrs\n" +
            "zssrg (8)\n" +
            "kfmkk (74)\n" +
            "blcjl (61)\n" +
            "fdtzsv (21)\n" +
            "lxqing (71)\n" +
            "vdxxgsn (51)\n" +
            "tytnilq (6) -> avgmu, ipvdfc, cgyxd\n" +
            "nwipkns (76)\n" +
            "klnuy (83) -> sbcry, glcrysh\n" +
            "cdadt (37)\n" +
            "bjjxgjb (88)\n" +
            "nyrjj (308) -> mcmxown, xogbq, cdlqgyh, cdcwgnc, iiwvkl\n" +
            "cfcqxkp (231) -> zssrg, uicmcph, xhjfj, qsdjow\n" +
            "uevmjdz (1776) -> koqxg, knhfwbt, yoyvf\n" +
            "ztnsgk (76)\n" +
            "zfasn (48)\n" +
            "pwabsd (76)\n" +
            "yrmop (210) -> zmvsw, skvukpc\n" +
            "warhhhp (75)\n" +
            "caylbg (31)\n" +
            "gdnzlar (49)\n" +
            "jzmhc (41) -> ngftkym, idksgg, kfmkk\n" +
            "xsodot (254) -> voiho, gjnnotg\n" +
            "xcllyx (86) -> pctuggx, xpjgixx\n" +
            "cxocay (90)\n" +
            "hutkx (33)\n" +
            "qnknn (23)\n" +
            "ftaxy (51) -> lkjbvw, gexwzw, cqoca, xezjpye, ftquc, gggibf, ferhk\n" +
            "tptuqv (812) -> qgofl, encsf, fevzwrt\n" +
            "vpuqhsd (32)\n" +
            "cyetn (86) -> mnezur, nvmkhp\n" +
            "dsdvxuw (49)\n" +
            "wwdvt (95)\n" +
            "tetsf (17)\n" +
            "oilpcq (38)\n" +
            "kampbl (65)\n" +
            "zuimm (41)\n" +
            "yjpmbe (80)\n" +
            "rnkrh (25)\n" +
            "ufknphd (26)\n" +
            "rigjtc (55)\n" +
            "rihloeh (2556) -> zyghjdm, ydnxkn, uzaousy\n" +
            "upilubg (75)\n" +
            "madhzgb (17)\n" +
            "uisdgr (57)\n" +
            "xtgrt (33)\n" +
            "lwcbxxt (19) -> claeea, abjmnp\n" +
            "ipgoewc (58)\n" +
            "jwioxco (789) -> yaezieu, ofrehpy, lgaruuy\n" +
            "lmvbhm (64)\n" +
            "qtdha (85)\n" +
            "jvjim (47)\n" +
            "iszzan (8)\n" +
            "ukdtleu (50) -> wmhucb, cxbjh, khfpks, lgmfk\n" +
            "jznwy (56) -> ufnve, adrate, ynrpdhv, szyya, uacotzj, keleq\n" +
            "fgctru (72) -> ydxbbv, dssoru\n" +
            "amydoj (87) -> jqmzy, jkjtbt, xysxg\n" +
            "czvjqk (53) -> azsvcju, gpvyac, hygnrpp\n" +
            "ohxvh (34)\n" +
            "gbiqrgk (169) -> orsmk, owagdf\n" +
            "urgiwa (19)\n" +
            "tmzut (80)\n" +
            "yeovck (4685) -> efrqc, uhvca, cpajvuw, emucaaw\n" +
            "jybhrc (275) -> srxvc, xbopb\n" +
            "xjddt (5)\n" +
            "karmp (29) -> rfjga, qjdum, nzojiy, ysuoctn\n" +
            "fkemma (64) -> jghceu, pvwdc\n" +
            "dzmsn (42)\n" +
            "bxtzlhx (88)\n" +
            "womays (310)\n" +
            "lxukyz (18)\n" +
            "kylpez (95) -> jtnoixl, otozhke, ucadng\n" +
            "amdcb (45)\n" +
            "oubxwbc (44)\n" +
            "wtksb (125) -> xyoenl, prewuzd\n" +
            "vlwhjq (325)\n" +
            "hpoawju (8)\n" +
            "bqypkb (49)\n" +
            "jwqnma (34)\n" +
            "gvfit (33) -> mwybm, pzyax\n" +
            "meonp (43)\n" +
            "cxbjh (72)\n" +
            "fuzizpc (68)\n" +
            "pvzri (141) -> gzvnmz, rexbt\n" +
            "lqtckpk (40)\n" +
            "oiyow (20)\n" +
            "cpajvuw (95) -> qdygxw, pduav, bpopmlv, stwrufp\n" +
            "txkyw (85)\n" +
            "juukhpw (42) -> ltpprm, dchgfut\n" +
            "rmqhdxq (31)\n" +
            "jymsax (70) -> vtzpyp, ybsetv, eagtrv\n" +
            "uhguep (98)\n" +
            "eagtrv (55)\n" +
            "sfwbytd (1939) -> fkemma, yrahjwp, qgiwga\n" +
            "occdw (108) -> frqzl, lpbhq\n" +
            "sypqjit (37)\n" +
            "axarhk (16) -> cbysja, kglxzv, nhrymw, huantpz, szeii, cigtx, ensfvaq\n" +
            "fyvlf (88) -> hhsge, jbmclpv\n" +
            "qqxng (19)\n" +
            "hvtqmrn (44)\n" +
            "mrmebqc (37)\n" +
            "azdtrv (20)\n" +
            "iusyvt (29)\n" +
            "rsebxf (53)\n" +
            "uoasog (75)\n" +
            "ivflnax (7)\n" +
            "hktqef (136) -> tslksz, tamkjw\n" +
            "iowcjno (56) -> qzwkx, temdg\n" +
            "gfzqihm (195) -> whurga, cihne\n" +
            "lcgdtru (49)\n" +
            "qceysy (23)\n" +
            "hjqlg (15)\n" +
            "fxgehjm (149) -> imafbhs, kjjuo, tzclfir\n" +
            "oaiybyl (30)\n" +
            "nsenvi (72)\n" +
            "tkhlk (371) -> rlxnjv, jvyfuqy\n" +
            "wiumq (10)\n" +
            "xqrgxx (3249) -> oqrht, uevmjdz, lyvwjt\n" +
            "szeii (230) -> ntftdmt, cjvxdq\n" +
            "bucnil (25) -> mlneyln, gdndd, tvooztp, lpycpao\n" +
            "rvbrodj (49)\n" +
            "lxxfs (16640) -> vxgsa, eicae, pkgqybo\n" +
            "uxunlz (25)\n" +
            "trvqvd (81) -> iwuvvl, ludvj, yiqqluf, muiela, yrmop, pwxllbx\n" +
            "pclpjq (21) -> omoin, aifwngi\n" +
            "oatryy (1767) -> taljv, qxylp, hqyhx, ahkztpf\n" +
            "xtcdt (26)\n" +
            "wbaldd (13)\n" +
            "sqenog (22) -> dhnttwg, exryas, rrnhzu\n" +
            "hwdfkq (15)\n" +
            "tihsmo (81)\n" +
            "swhwwgj (37)\n" +
            "eunqr (31)\n" +
            "erxep (66)\n" +
            "edzfey (84)\n" +
            "nbutjk (36)\n" +
            "ymfatu (59)\n" +
            "prucq (12)\n" +
            "oopwi (18)\n" +
            "xzhus (76)\n" +
            "sbvgp (281) -> treywsr, ahvzxw\n" +
            "spcwbv (54)\n" +
            "otont (24) -> bilbca, okard\n" +
            "ymexya (85)\n" +
            "rrfjfe (9)\n" +
            "dxonm (46)\n" +
            "fnedd (49) -> ddbixap, bxtzlhx\n" +
            "deuwm (277)\n" +
            "mluyp (57)\n" +
            "ialxayo (17)\n" +
            "zmekfc (17)\n" +
            "sltfq (135) -> nofof, xzfbr\n" +
            "vhyyv (54)\n" +
            "hbjhi (20)\n" +
            "kfjztke (28)\n" +
            "srkft (650) -> wixxqy, aajfrf, enlcjc, bfkcjn\n" +
            "sjqnci (50) -> rvycd, oatryy, tjqcdi, fantkyj, gastvq, tildgc, wvwqp\n" +
            "aprln (53)\n" +
            "qweyg (82)\n" +
            "nbidkx (82)\n" +
            "xjmhhb (3669) -> kmtyyw, foghvqs, ruadem\n" +
            "eqdoyk (89)\n" +
            "dchgfut (90)\n" +
            "anzjm (34)\n" +
            "drvhp (117) -> jxwvdj, ymfatu\n" +
            "qxwiktp (77)\n" +
            "vvfrqo (33)\n" +
            "jmcvwof (54)\n" +
            "yylhcwe (4583) -> jxqmtj, eesynky, wsuetci, rihloeh\n" +
            "wlxkzyu (69) -> ekild, kuiik\n" +
            "earhrts (28) -> yeqsa, pzbruav, nqobvf\n" +
            "cyxdrb (42)\n" +
            "kilai (70) -> phbyft, cdurt\n" +
            "teekb (2069) -> lzwvg, wfdxzo, sjaimla\n" +
            "kyrfsv (22)\n" +
            "luzyhbe (252) -> rijys, ktwba\n" +
            "jwytokt (20)\n" +
            "yalvg (41)\n" +
            "ivejr (78)\n" +
            "isdcga (90) -> fltpoim, colszxn\n" +
            "jvyfuqy (13)\n" +
            "nyfzqsd (93)\n" +
            "fwqle (324) -> hwgbp, anzjm, jwqnma\n" +
            "ogfsdjs (132) -> bjctm, kbapj\n" +
            "watbsp (86)\n" +
            "koviipt (5)\n" +
            "jeeyjk (89)\n" +
            "vxlbjn (37)\n" +
            "uaojqj (36)\n" +
            "ebargy (93)\n" +
            "ojqkqas (105) -> lmmde, xzhus, joohyti\n" +
            "zxsoo (126) -> fzmhn, pvzhrs\n" +
            "nygkcd (25)\n" +
            "elqusuf (7)\n" +
            "ppkmo (49) -> jwdtam, raqadrs\n" +
            "muahqz (65)\n" +
            "qrmbao (13)\n" +
            "dhilxlz (85)\n" +
            "yexjxnd (74) -> nlnqo, irvuv\n" +
            "pvzhrs (66)\n" +
            "qsdjow (8)\n" +
            "phbyft (55)\n" +
            "yixjhrx (27)\n" +
            "foghvqs (79) -> vlwhjq, jwvkdyy, juflvu, hvwtr, kicca, dzlygu\n" +
            "iilaoq (7)\n" +
            "qnwlb (45) -> rqxgeqm, iqtij, ypqsdaa, nsenvi\n" +
            "hxexq (79)\n" +
            "wksouwn (241) -> dbuzcp, lhclwhi\n" +
            "lpbbxoq (86)\n" +
            "lnpla (97) -> zocuuld, zuvar, zxsxos, awmjc\n" +
            "smmjrpi (38)\n" +
            "fqjhd (83)\n" +
            "tujcuy (26)\n" +
            "rfjga (88)\n" +
            "npjyxjn (39)\n" +
            "eayxrho (559) -> ymqaag, ninnxgi, yexjxnd, zwrifke, tytnilq, zxsoo, euask\n" +
            "wfvumx (31)\n" +
            "ekdrgm (76)\n" +
            "sbcry (78)\n" +
            "durycn (53) -> nifzp, jhhnkho, jlzlxib, cqhgy\n" +
            "otxmmd (235)\n" +
            "bpmqk (80)\n" +
            "eicae (121) -> rddasm, xhibern\n" +
            "zcvtef (72)\n" +
            "nsoes (37)\n" +
            "dsmuem (31)\n" +
            "jwvgc (89)\n" +
            "sgabt (9)\n" +
            "deoiu (23)\n" +
            "arqruaa (76)\n" +
            "ryqaxd (23)\n" +
            "mlhqqjt (15)\n" +
            "nndiv (153) -> wmqzq, wgqus\n" +
            "tthspe (34)\n" +
            "ahgtamk (58)\n" +
            "unwpat (75)\n" +
            "lyhuihb (8)\n" +
            "jejhp (329) -> hbpbcf, dafryf, ojwprv\n" +
            "vqomvt (4087) -> wknjhvo, mrexh, fjjgfl, rfldln\n" +
            "kwvkbv (35)\n" +
            "tyati (38)\n" +
            "hspnsfg (164) -> pzgagh, madhzgb\n" +
            "ujpjxn (54)\n" +
            "opwmti (95)\n" +
            "swwwls (226)\n" +
            "gwhqhzb (54)\n" +
            "krbtc (54)\n" +
            "txqrl (60)\n" +
            "yrucl (349) -> qfxeio, mkrkkri, kimkb, rllniuw\n" +
            "kumyda (328) -> azkwjyi, cyfuqq, tydgx\n" +
            "ljlbpd (91)\n" +
            "fzmhn (66)\n" +
            "yjvyqo (295) -> gnirnf, tflkhk, dyfioe\n" +
            "jyvgy (93) -> uhguep, idpng\n" +
            "zlrwizb (66)\n" +
            "nhrymw (85) -> slvygt, lqhud, putleq\n" +
            "goaeny (60) -> wwdvt, gbuxs, oacpgj, opwmti\n" +
            "palczfm (92)\n" +
            "elwic (60)\n" +
            "inqto (55)\n" +
            "gpvyac (64)\n" +
            "ebtazb (312) -> hpoawju, cidfe, iszzan, qimhnpk\n" +
            "pawxyqe (42)\n" +
            "piigjh (80)\n" +
            "tyviul (199) -> tgedm, unxjpl\n" +
            "sazkwox (76)\n" +
            "kvrmjma (9)\n" +
            "qgofl (84) -> dugorw, srybbkw\n" +
            "pegrxl (13)\n" +
            "mzxyyfo (20)\n" +
            "onpzd (26)\n" +
            "pdumcu (94)\n" +
            "yxdkhlx (23) -> izgqj, smmjrpi\n" +
            "cxdwmu (26)\n" +
            "ypqsdaa (72)\n" +
            "nzojiy (88)\n" +
            "xyoenl (50)\n" +
            "avgmu (84)\n" +
            "yqrjk (2271) -> ovdsrbq, rgnjr\n" +
            "ivfbcy (40)\n" +
            "knhfwbt (131)\n" +
            "fxdfhtc (24)\n" +
            "vwepws (69) -> mluyp, unfrsh\n" +
            "qxdfu (5)\n" +
            "mwuhto (85) -> aisrfn, piigjh\n" +
            "wymbxxi (186) -> amfdzum, otont, xcllyx, iowcjno\n" +
            "gdzcx (68)\n" +
            "mqtmr (40)\n" +
            "kwavxzh (39)\n" +
            "anpyn (22)\n" +
            "rnrxeb (80)\n" +
            "dzlrn (51) -> kcqshd, rmqhdxq, caylbg\n" +
            "vbutj (14)\n" +
            "edguzhg (6)\n" +
            "wlffbug (65) -> qavye, anpyn\n" +
            "qimhnpk (8)\n" +
            "uhottvw (36) -> ymexya, ezespi, mjeni\n" +
            "pcfilur (52242) -> yeovck, vmreal, cqqmsv\n" +
            "vfkzaix (193) -> pegrxl, wbaldd\n" +
            "ecbgp (58)\n" +
            "vkugys (36)\n" +
            "kmiubc (249) -> sqenog, jchpv, bryzg, owzsiku, coiuo, hbvroo\n" +
            "yokbals (19)\n" +
            "muivti (17)\n" +
            "fzjuimt (12161) -> maokm, ntvpob, qyrope\n" +
            "jxwvdj (59)\n" +
            "bswzafr (12) -> dlmmqrm, hznqtl\n" +
            "mkbny (230) -> fmfksfr, ocriw\n" +
            "azsurm (19)\n" +
            "vkdslcc (360) -> ndvnqb, pjrtq\n" +
            "jeabki (206) -> bkpzlxc, rrqud\n" +
            "qflfg (18)\n" +
            "lojah (51)\n" +
            "voiho (23)\n" +
            "mwybm (70)\n" +
            "csrjic (58)\n" +
            "wcdlx (84)\n" +
            "bmjswlz (11)\n" +
            "qxoljws (47)\n" +
            "gbuxs (95)\n" +
            "kbapj (47)\n" +
            "niexbbc (72)\n" +
            "qeebfg (270) -> aqrawj, wjxqn, prsysf, ukdtleu\n" +
            "fjxvx (688) -> gphoz, bmoed, ookaq, nxrhbhh, dfezv\n" +
            "kimkb (21)\n" +
            "unfrsh (57)\n" +
            "ijwptaq (73)\n" +
            "zocuuld (45)\n" +
            "ddjoygw (75)\n" +
            "lwdgty (222) -> ofexdqx, xizhxs\n" +
            "bexoza (57) -> pzgqbxm, warhhhp, wzcmmr, phxjq\n" +
            "uiqwd (89)\n" +
            "pvmgp (114) -> byduope, atkagyc, ibbtm\n" +
            "ydxbbv (95)\n" +
            "kicca (325)\n" +
            "ptfuyxs (30)\n" +
            "gyehzo (18)\n" +
            "itnpmen (29)\n" +
            "mjmjubw (79) -> hixroxf, hmxzkwe\n" +
            "enlcjc (59)\n" +
            "kwenr (79) -> ehajus, pxxvg\n" +
            "lvbgz (62)\n" +
            "vhauylt (58)\n" +
            "eixgei (54)\n" +
            "usuyhvw (76)\n" +
            "pwxllbx (186) -> oaiybyl, roquwk\n" +
            "hjgaun (40)\n" +
            "ucadng (40)\n" +
            "lpkak (207) -> azsurm, kyxphrr\n" +
            "idpng (98)\n" +
            "ddpqp (95)\n" +
            "qxwmd (67)\n" +
            "qeidnw (298) -> qvtxsu, jjqpm\n" +
            "eivaq (75) -> utydsr, swhwwgj\n" +
            "hrixd (31) -> keuzyp, iusyvt, perxaan\n" +
            "ejygwyb (932) -> txagu, mbzah, bcnwjsb, eivaq, bcicoat\n" +
            "ddhzzxi (23)\n" +
            "kzjfx (42)\n" +
            "qllpxw (19)\n" +
            "hmxzkwe (51)\n" +
            "fhayyvm (24)\n" +
            "lvauv (42)\n" +
            "woasw (88)\n" +
            "cmpldn (75)\n" +
            "ibxkkj (45)\n" +
            "abvvbtl (48)\n" +
            "idksgg (74)\n" +
            "pmyvcx (64)\n" +
            "sbeizwi (31)\n" +
            "xsaqyd (49)\n" +
            "fevzwrt (178) -> bruxjz, zjbmj\n" +
            "cqqmsv (79) -> axnnyq, pbmhfx, teekb, popwmey, gomgkol\n" +
            "clwnxrx (39)\n" +
            "ikegr (61)\n" +
            "hsagb (87)\n" +
            "jchpv (68) -> lrvwrl, quaey\n" +
            "jauxs (180) -> xcubkli, zuimm, lvohqo, mpkgky\n" +
            "midyfh (140) -> zkmtesi, yalvg, aymrru\n" +
            "msnicb (42)\n" +
            "ahxnebz (18)\n" +
            "skhjb (163) -> czllgn, hwdfkq\n" +
            "ziyhyly (25) -> hizhauj, niexbbc\n" +
            "jkjtbt (62)\n" +
            "hjeeb (33)\n" +
            "mdgqhkw (56)\n" +
            "boblgpx (95)\n" +
            "lvgrf (65)\n" +
            "anbdqml (31)\n" +
            "jrrgvqf (82) -> xsaqyd, zcmud\n" +
            "otadvyl (60)\n" +
            "dpiexx (13) -> edxsfa, sfvfge, womays\n" +
            "otvta (39)\n" +
            "slttwe (60)\n" +
            "byegnb (85)\n" +
            "agbhqmu (38)\n" +
            "fmtmlgd (64)\n" +
            "sjaimla (95)\n" +
            "ctpfl (132) -> xxlzsza, ekdrgm\n" +
            "xbmbzn (29)\n" +
            "cnrgfkq (11963) -> tptuqv, lfjkju, fkinwya\n" +
            "khfpks (72)\n" +
            "emkuxtl (107) -> elsqz, iymnx, lxgjn, oyjrsw, sbvgp\n" +
            "zxalnm (49) -> gdzcx, emrzoi\n" +
            "lzwvg (35) -> atcrc, msutjz, emhikp\n" +
            "kpyped (23)\n" +
            "uytekqt (14)\n" +
            "bzwof (397) -> uulxzg, eswhpi\n" +
            "atcckfh (131) -> ujpjxn, jmcvwof\n" +
            "nyojm (309) -> zwprtx, myaklce, nqnvc, xjddt\n" +
            "lmdteh (61)\n" +
            "jjukrje (7)\n" +
            "wgcckll (19)\n" +
            "ferhk (568) -> kjegcl, dzlrn, vdkjcp, tefpuyj\n" +
            "kemkox (61)\n" +
            "wkyhcm (72)\n" +
            "gjkrkw (83)\n" +
            "pvwdc (39)\n" +
            "clgsp (33)\n" +
            "qalkey (7)\n" +
            "sewpop (245)\n" +
            "wvihp (8)\n" +
            "ragqe (10)\n" +
            "qtfpf (5)\n" +
            "hqvvtr (43)\n" +
            "tiqmony (24)\n" +
            "enuqt (94)\n" +
            "uzxbp (24)\n" +
            "hkorax (80)\n" +
            "wocartl (67)\n" +
            "cubeivy (49) -> iyywca, pkafj, nqmrbb\n" +
            "ylbprac (21)\n" +
            "xrowfq (121) -> tyati, agbhqmu, ypetsiw\n" +
            "txwypo (59) -> npjyxjn, uhpbxq, otvta, hoemev\n" +
            "nofof (82)\n" +
            "vmwmulw (4725) -> lriegav, ejygwyb, kmiubc\n" +
            "shuqg (47)\n" +
            "ampno (43)\n" +
            "ndmej (73)\n" +
            "gdndd (93)\n" +
            "xvafyvk (13) -> clxdcd, sxakrws, afwufw, fwqle, ctaya\n" +
            "lqhud (81)\n" +
            "byijdr (8) -> lpbbxoq, zqicjh\n" +
            "vdjoaq (20)\n" +
            "wjuyj (62)\n" +
            "ugmwpnx (183)\n" +
            "lshtqgp (80)\n" +
            "tufamsz (37)\n" +
            "piztdav (6)\n" +
            "zcvlo (23) -> eyumraw, hnulxpf\n" +
            "gzvnmz (16)\n" +
            "wbmahc (80)\n" +
            "ensfvaq (146) -> ljlbpd, pkvblpt\n" +
            "iefpjj (76)\n" +
            "jsjxl (78)\n" +
            "sxfptlh (62)\n" +
            "jwmtw (97) -> eoeiflq, oilpcq\n" +
            "ojxixj (290) -> xtvqhc, rvosgv\n" +
            "ylftgo (166) -> dxspx, ctcykka\n" +
            "xsepgg (156) -> lcgdtru, rvbrodj\n" +
            "yrwzi (288) -> wqqmvoe, bxeum\n" +
            "efriw (135) -> uxunlz, ejkioj\n" +
            "xlkuot (35)\n" +
            "nhqgb (85)\n" +
            "nxian (58)\n" +
            "oscxinx (44)\n" +
            "ncdbvaa (54)\n" +
            "mfcryoc (57)\n" +
            "cqoyqhn (85)\n" +
            "slifb (40)\n" +
            "rcelntk (66)\n" +
            "axnnyq (1358) -> wpirdu, jeabki, yorypq\n" +
            "rhxhumv (67)\n" +
            "jgxph (92)\n" +
            "dalhs (108) -> nbutjk, uaojqj\n" +
            "zmvsw (18)\n" +
            "hkgcdih (763) -> ndprukh, vgeifn\n" +
            "ntvpob (789) -> ouabu, ucyuje, enjrxrw\n" +
            "jdzlhqw (99)\n" +
            "peqsua (153) -> zntwpqo, nhale, bjdfo, wiumq\n" +
            "cpdapys (39)\n" +
            "ysqronq (75)\n" +
            "putleq (81)\n" +
            "dyvgigf (41)\n" +
            "xocsd (5)\n" +
            "pipax (40) -> dzmsn, ujmboh, xcrvb, lvauv\n" +
            "cqfebqe (52)\n" +
            "fitaq (283) -> ajrle, baqiy\n" +
            "lsmylfm (217) -> jdzlhqw, lrsoyci\n" +
            "kukri (70)\n" +
            "hqyhx (31) -> ztnsgk, yzlzly\n" +
            "tydgx (102) -> synxk, smcru\n" +
            "keuzyp (29)\n" +
            "lpilno (122) -> hqvvtr, bbenpz\n" +
            "wfdxzo (41) -> wbxcy, bfbbi\n" +
            "azfrf (44)\n" +
            "hdzls (58881) -> ayeric, vqomvt, huqgqdb, scngxng\n" +
            "kdgummr (149) -> deoiu, kvwjces, mpnodas\n" +
            "nqnvc (5)\n" +
            "cbysja (268) -> bzuiexp, tsrob, hbjhi\n" +
            "xiecr (20)\n" +
            "cuajqvk (95)\n" +
            "rwupp (8)\n" +
            "qdygxw (268) -> ykxkahj, kwavxzh, vkpgf, clwnxrx\n" +
            "dtzjt (89)\n" +
            "urnlyx (5)\n" +
            "emucaaw (13) -> jably, xsepgg, ywwovs, cyetn, clnzh, gybqyo, mdwrg\n" +
            "avugbx (8)\n" +
            "zxtxfdm (882) -> jnvrw, jyvgy, gjpgk\n" +
            "pkgqybo (179) -> kemkox, jzkkmio\n" +
            "hnulxpf (99)\n" +
            "atkagyc (28)\n" +
            "iukhsi (41) -> zcvtef, bwiee\n" +
            "rgdsxy (12)\n" +
            "uwwmhl (75)\n" +
            "klgolly (19)\n" +
            "ypetsiw (38)\n" +
            "igoifku (241) -> njoqwl, prucq\n" +
            "raqadrs (93)\n" +
            "ejkfjsb (109)\n" +
            "hbqxmkz (51)\n" +
            "yaezieu (238) -> izqne, rrfjfe\n" +
            "yblpan (70)\n" +
            "kycvcdb (26)\n" +
            "zazwgl (30)\n" +
            "ayeric (2991) -> cwcmr, mcwevy, duyvc\n" +
            "cewfc (22) -> usudfnb, zfasn\n" +
            "zfbolo (24)\n" +
            "jcortpe (57)\n" +
            "vdppt (74) -> awgqw, qxdfu, hkljrrq, yhkof\n" +
            "ujtyd (62)\n" +
            "cweqffl (16)\n" +
            "dhpxdx (85) -> puvhodg, cikitqj\n" +
            "jbqfn (84)\n" +
            "colszxn (86)\n" +
            "qxerd (152) -> cqhnl, cnksi\n" +
            "scngxng (3999) -> jejhp, chpcn, ejwrem\n" +
            "keleq (141) -> otadvyl, txqrl\n" +
            "iwuvvl (80) -> fqjhd, gjkrkw\n" +
            "fvord (24)\n" +
            "nyvjvra (49)\n" +
            "vabwqou (68)\n" +
            "byduope (28)\n" +
            "lgkbca (141) -> klgolly, yokbals, mpamey\n" +
            "qogcdog (97)\n" +
            "aqzibdb (65)\n" +
            "ocriw (57)\n" +
            "tnvtn (97)\n" +
            "flgljqv (22)\n" +
            "utyriu (62)\n" +
            "flikvl (49)\n" +
            "tplrfil (32)\n" +
            "xiuxqpp (403) -> moqub, ysexyc\n" +
            "ndpjdmr (87)\n" +
            "ocsqis (14)\n" +
            "rdzzlw (17) -> ikkxpuo, kdjoii\n" +
            "avgsmcl (89)\n" +
            "uhvca (603) -> hgnkw, kxlbaue, nnyzm, cfcmep, pvmgp, dviuy\n" +
            "ymqaag (144) -> dzkqrjf, yhfjs, sunnnk\n" +
            "gadpuyh (85)\n" +
            "pkaqx (1639) -> rigjtc, inqto\n" +
            "bjctm (47)\n" +
            "nqvntc (14885) -> eemyrwb, srkft, wvlri\n" +
            "kapmsr (54)\n" +
            "txagu (51) -> nyvjvra, flikvl\n" +
            "mxnvqf (43)\n" +
            "orwwh (64)\n" +
            "awwicqq (19)\n" +
            "tzepblp (60)\n" +
            "bmxdb (54)\n" +
            "ayntidu (1251) -> dalhs, jrrgvqf, bikrg\n" +
            "sxakrws (426)\n" +
            "zkpbt (97)\n" +
            "zcmud (49)\n" +
            "isvfqpy (33)\n" +
            "ikegi (94)\n" +
            "oxdqy (71)\n" +
            "dtedfv (559) -> ortfml, hktqef, xbhhxq, yheme, lwdgty, iftzv, rcvhb\n" +
            "otozhke (40)\n" +
            "wdanz (277) -> szejcb, hudgw\n" +
            "xfnmqd (75) -> ggmgzhd, krbtc\n" +
            "tpafkv (46) -> watbsp, xuoags\n" +
            "rllniuw (21)\n" +
            "ltpprm (90)\n" +
            "kyxphrr (19)\n" +
            "yhkof (5)\n" +
            "ofexdqx (18)\n" +
            "uqyqwny (58)\n" +
            "yfxxnzi (221)\n" +
            "lekky (97) -> xflldsr, hgfxb, nxian, vhauylt\n" +
            "lriegav (32) -> tbimd, nrvgs, jymsax, ppkmo, oukjop, drvhp, xrowfq\n" +
            "qrlcg (80)\n" +
            "hethruu (7)\n" +
            "zwrifke (216) -> ylbprac, yywuo\n" +
            "cjvxdq (49)\n" +
            "ezespi (85)\n" +
            "acfzx (10)\n" +
            "lzuon (70)\n" +
            "dnwlq (86) -> lugtnb, vvfrqo, gujrqf\n" +
            "gqejmuy (43)\n" +
            "jcmbf (185)\n" +
            "pohtiuv (47)\n" +
            "chmmaa (221)\n" +
            "tjqcdi (1759) -> okmrsd, jcmbf, iukhsi, zxalnm\n" +
            "skvukpc (18)\n" +
            "dwxem (80)\n" +
            "dyfioe (5)\n" +
            "bmoed (179) -> ahxnebz, blqvp\n" +
            "eswhpi (9)\n" +
            "mdsms (80)\n" +
            "aifwngi (76)\n" +
            "qhttqrn (71) -> hbqxmkz, ldcdes\n" +
            "nrwrhsp (62) -> satjbta, iycds, lekky, lccfqhm, jybhrc, zxrpdab, ycxsmt\n" +
            "beesdrz (487) -> xhkbihe, oxxyy, ziyhyly\n" +
            "oukxnyz (77) -> jeeyjk, avgsmcl, izmwsib\n" +
            "oxxyy (51) -> arouyz, qleml\n" +
            "zzufnfx (76)\n" +
            "nhale (10)\n" +
            "vrezr (80)\n" +
            "gphoz (167) -> nlnwd, hzcbok, djfzb, wbtniv\n" +
            "oihlb (339) -> wztqxw, shuqg\n" +
            "uhpbxq (39)\n" +
            "fpqpaes (17) -> yatllw, uhottvw, mqtksj, jpzmpas, aylev, ueusbib\n" +
            "tsrob (20)\n" +
            "ljedj (221)\n" +
            "pkafj (20)\n" +
            "pwzlax (273)\n" +
            "ekzsv (201) -> umdid, ehpmmve\n" +
            "bikrg (108) -> ayqsng, zuytxxd, rxoja, oopwi\n" +
            "bryzg (218) -> dqtls, xocsd, urnlyx, jscgykv\n" +
            "znitfpo (70) -> usuyhvw, nshqf\n" +
            "hvwtr (249) -> wflwje, lhkwv\n" +
            "bvjkr (176) -> vdxxgsn, lojah\n" +
            "arvmgnd (54)\n" +
            "mpnodas (23)\n" +
            "ruxilvv (82) -> vfipmtl, bjjxgjb\n" +
            "zvhjz (93)\n" +
            "wjxqn (248) -> hgnqq, amdcb\n" +
            "uhcrfl (21409) -> yyjweq, fzzbt, kaylk, cnrgfkq\n" +
            "hgfxb (58)\n" +
            "zuytxxd (18)\n" +
            "ltpwxbx (2760) -> kcqlos, wnsnr, zxtxfdm, pkaqx\n" +
            "qvdkqw (309) -> sxfptlh, ujtyd\n" +
            "upksf (10) -> yfxxnzi, ghpotnp, ljedj, gfzqihm, gopynr, waaostj, cilkhan\n" +
            "eyqpybt (75)\n" +
            "rvosgv (10)\n" +
            "bfbbi (27)\n" +
            "vkpgf (39)\n" +
            "oghwfv (122) -> klfkq, kemaenc, wfvumx\n" +
            "lyvwjt (1269) -> wtksb, fnedd, brwizly, jvmpgso\n" +
            "encsf (182) -> sbeizwi, eunqr\n" +
            "zigchs (90)\n" +
            "eihea (27) -> ndmej, ijwptaq\n" +
            "nrneucv (5)\n" +
            "wgxlaau (60)\n" +
            "ctnzjui (267) -> cweqffl, jyjyy\n" +
            "skhjuz (80)\n" +
            "exvcki (55)\n" +
            "xflldsr (58)\n" +
            "hudgw (28)\n" +
            "cnksi (53)\n" +
            "njmxjzn (65)\n" +
            "nnyzm (150) -> chozum, fhayyvm\n" +
            "mqkaob (31)\n" +
            "mpkgky (41)\n" +
            "oqrht (76) -> appwc, wksouwn, auoba, ctnzjui, gjfmlc, ekzsv, sltfq\n" +
            "fvvrow (68)\n" +
            "ontvyu (80)\n" +
            "yorypq (236) -> tivuls, nqgfa, uzxbp, fxdfhtc\n" +
            "iycds (315) -> ivflnax, hnevf\n" +
            "ywwovs (102) -> nwipkns, pwabsd\n" +
            "mlneyln (93)\n" +
            "hrbmwq (22)\n" +
            "pxora (55)\n" +
            "azsvcju (64)\n" +
            "flrpj (67)\n" +
            "hyvfpe (99)\n" +
            "wsuetci (2388) -> ctpfl, fhmeku, bstmzd\n" +
            "coegpi (97)\n" +
            "gfnhg (45)\n" +
            "hbpbcf (207) -> fdtzsv, cekovn\n" +
            "xbopb (27)\n" +
            "uacotzj (261)\n" +
            "jrqht (42)\n" +
            "fslbtk (57)\n" +
            "xbhhxq (189) -> qceysy, kcftmnv, vkbpp\n" +
            "eblot (75)\n" +
            "puvhodg (58)\n" +
            "spfma (72)\n" +
            "dfezv (201) -> fihqkla, hethruu\n" +
            "bqxiezq (49)\n" +
            "tildgc (1627) -> tpafkv, xwpuhf, vcqgruj, kdgummr\n" +
            "dkhyk (19)\n" +
            "snxzn (81)\n" +
            "zmnaori (75)\n" +
            "lhkwv (38)\n" +
            "emrzoi (68)\n" +
            "ysexyc (15)\n" +
            "ueoyysn (13)\n" +
            "rpdslp (18)\n" +
            "jzzng (44)\n" +
            "rzqdqj (80)\n" +
            "pdfzwaz (37)\n" +
            "dzjvx (53) -> bxvaxw, lucdfi\n" +
            "nflgixa (21)\n" +
            "myaklce (5)\n" +
            "yyjweq (40) -> nrwrhsp, eayxrho, udyms, dtedfv, sfwbytd, vmghntr, yqrjk\n" +
            "bcnwjsb (107) -> nflgixa, ygjhtt\n" +
            "cilkhan (167) -> pfchj, yixjhrx\n" +
            "hjkfjle (11)\n" +
            "cihne (13)\n" +
            "fkinwya (1480) -> sdljelr, vpuqhsd\n" +
            "kirybeg (44)\n" +
            "jmhafl (70) -> fedjn, sazkwox\n" +
            "ebjym (38)\n" +
            "ofrehpy (91) -> exvcki, xzawv, pxora\n" +
            "jqmzy (62)\n" +
            "gggibf (112) -> qhfpnc, ruxilvv, gklao, qxerd\n" +
            "smcru (60)\n" +
            "dqggh (433)\n" +
            "lpbhq (99)\n" +
            "xzbry (52)\n" +
            "iczndtj (201) -> jncdjr, edguzhg\n" +
            "mcmxown (120) -> ygnys, hpxoxk, uisdgr\n" +
            "cfegyp (27)\n" +
            "ndvnqb (47)\n" +
            "bnrgfa (5)\n" +
            "bivovn (88) -> cuajqvk, ddpqp\n" +
            "omiwktf (71)\n" +
            "mkxbl (162) -> kjzvcr, lnmmac\n" +
            "gujrqf (33)\n" +
            "szejcb (28)\n" +
            "tslksz (61)\n" +
            "vsshf (67) -> sypqjit, cdadt\n" +
            "jhfdmw (20)\n" +
            "ngftkym (74)\n" +
            "vjfzfkf (69)\n" +
            "odhnwli (41)\n" +
            "popwmey (1793) -> ncsro, lwcbxxt, apzzew\n" +
            "mjeni (85)\n" +
            "perxaan (29)\n" +
            "edxsfa (85) -> grxfsjm, uwwmhl, ddjoygw\n" +
            "oknhrfd (30)\n" +
            "kbbgkmr (2136) -> occdw, hawmox, uzjpp\n" +
            "nvglnd (29)\n" +
            "dviuy (186) -> puyxatg, piztdav\n" +
            "baesgig (67)\n" +
            "qcylfon (93)\n" +
            "wmhucb (72)\n" +
            "xlpgtmn (31)\n" +
            "wgqus (41)\n" +
            "rqxgeqm (72)\n" +
            "cdcwgnc (189) -> xzfggf, gebon\n" +
            "grnafmr (956) -> jmhafl, juukhpw, znitfpo\n" +
            "yoddspj (66)\n" +
            "zvkpq (83) -> apsinu, vwgvrw\n" +
            "kcqlos (84) -> wtfhx, wdanz, loksq, qnwlb, ojqkqas\n" +
            "ueusbib (231) -> taewvbl, xhrgl\n" +
            "agyir (409) -> mjxqlx, vnpjttm, mlhqqjt\n" +
            "aqrawj (158) -> yjuzr, gfnhg, ibxkkj, pealhre\n" +
            "blqvp (18)\n" +
            "kaylk (12619) -> wymbxxi, kumyda, beesdrz, dfrdid\n" +
            "pgyabg (29)\n" +
            "tamkjw (61)\n" +
            "lkjbvw (847) -> gykhcku, piaeb, yxdkhlx\n" +
            "bcicoat (131) -> sgabt, gjmaeb\n" +
            "uulxzg (9)\n" +
            "onnfacs (63612) -> ftaxy, nrkctj, tfnsk\n" +
            "hepedt (45)\n" +
            "gebon (51)\n" +
            "rtoecx (45)\n" +
            "hfqgoz (20)\n" +
            "taewvbl (30)\n" +
            "hhsge (55)\n" +
            "immxnu (23)\n" +
            "mkrkkri (21)\n" +
            "tfxzu (13)\n" +
            "netxsyl (93)\n" +
            "tyvztv (22)\n" +
            "xzfbr (82)\n" +
            "gastvq (2076) -> boyyulo, dbics, vsshf\n" +
            "slvygt (81)\n" +
            "wixxqy (59)\n" +
            "cddgm (149) -> ahgtamk, ortqko\n" +
            "scjjqzv (2105) -> rzkgb, sahdqia\n" +
            "amfdzum (122) -> ivfbcy, slifb\n" +
            "ntftdmt (49)\n" +
            "wiqohmb (26)\n" +
            "vbnobqv (76)\n" +
            "xuoags (86)\n" +
            "uzaousy (98) -> muahqz, shojrc\n" +
            "gjmaeb (9)\n" +
            "nhxtvr (35)\n" +
            "jscpi (26)\n" +
            "ofjopcm (94)\n" +
            "bkpzlxc (63)\n" +
            "omoin (76)\n" +
            "roquwk (30)\n" +
            "xwyzhc (19)\n" +
            "mdtrudc (76)\n" +
            "pjrtq (47)\n" +
            "fcxdhb (59)\n" +
            "qigwdd (70)\n" +
            "ajzenm (249) -> spcwbv, eixgei\n" +
            "kcqshd (31)\n" +
            "oyjrsw (161) -> lxqing, omiwktf\n" +
            "iflde (189) -> xlkuot, kwvkbv, nhxtvr, qryupbf\n" +
            "gjnnotg (23)\n" +
            "xzawv (55)\n" +
            "quaey (85)\n" +
            "jakdiea (74) -> yylhcwe, lxxfs, fzjuimt, sjqnci, nqvntc\n" +
            "frqzl (99)\n" +
            "isrgun (270) -> nhqgb, byegnb\n" +
            "mabydz (78)\n" +
            "dbpxdxl (26)\n" +
            "tvooztp (93)\n" +
            "mpamey (19)\n" +
            "srxvc (27)\n" +
            "tflkhk (5)\n" +
            "vrkihvw (7) -> arqruaa, vbnobqv, zzufnfx\n" +
            "rlxnjv (13)\n" +
            "wbtniv (12)\n" +
            "uwnvz (66)\n" +
            "ouabu (95) -> skhjuz, yjpmbe, nvaiuzb\n" +
            "hzcbok (12)\n" +
            "oqfan (123) -> umrrz, ozhoji\n" +
            "hejkytc (89)\n" +
            "waukis (18)\n" +
            "imzuug (26)\n" +
            "lmmde (76)\n" +
            "uzjpp (156) -> ysqronq, zmnaori\n" +
            "isrqnr (68)\n" +
            "bpopmlv (264) -> lshtqgp, vrezr\n" +
            "hznqtl (41)\n" +
            "vfipmtl (88)\n" +
            "kqlwcz (45)\n" +
            "kjzvcr (69)\n" +
            "hoemev (39)\n" +
            "eepyf (42)\n" +
            "qhfpnc (244) -> elqusuf, jjukrje\n" +
            "aisrfn (80)\n" +
            "duyvc (47) -> amydoj, pwzlax, wiuxjsd, tfkrv, gbiqrgk\n" +
            "wflwje (38)\n" +
            "ykxkahj (39)\n" +
            "fovzx (55)\n" +
            "okard (89)\n" +
            "izmwsib (89)\n" +
            "iymnx (174) -> azdiwj, gqejmuy, kxrorv\n" +
            "lpycpao (93)\n" +
            "hkljrrq (5)\n" +
            "xxnqt (139) -> dhpxdx, zvkpq, kajmeo, dzxzka\n" +
            "xtmjhz (14)\n" +
            "stwrufp (84) -> qtdha, isaicgr, dhilxlz, gadpuyh\n" +
            "mpnqbac (71)\n" +
            "ludvj (172) -> nsoes, vxlbjn\n" +
            "pctuggx (58)\n" +
            "xhjfj (8)\n" +
            "dbuzcp (29)\n" +
            "edgaqnw (80)\n" +
            "istew (69)\n" +
            "waaostj (63) -> hxexq, ylwwnxo\n" +
            "prewuzd (50)\n" +
            "vmghntr (1313) -> jzmhc, oqfan, cfcqxkp, midyfh\n" +
            "nrvgs (189) -> ryqaxd, owjjchv\n" +
            "jtnoixl (40)\n" +
            "mhrsu (97)\n" +
            "wvlri (55) -> smhbvd, lnpla, deuwm\n" +
            "cyfuqq (222)\n" +
            "furgqht (93)\n" +
            "euask (26) -> datez, csrjic, puuiqr, wfwfpat\n" +
            "ikkxpuo (83)\n" +
            "dnigru (52) -> pmyvcx, fmtmlgd\n" +
            "ufaxhkb (46) -> fluqhsw, vbiwa, woasw\n" +
            "ajrle (75)\n" +
            "lplefi (65)\n" +
            "eemyrwb (346) -> tayge, qdoiidp, kilai\n" +
            "jpzmpas (235) -> kfjztke, idfhr\n" +
            "dgwfi (47)\n" +
            "lrqffb (65)\n" +
            "isaicgr (85)\n" +
            "uxsrdpn (1843) -> qjtan, eihea, nlknxtv, pclpjq, pvzri, fxgehjm, dzjvx\n" +
            "loksq (293) -> fpejk, qxmdjq, ragqe, acfzx\n" +
            "ynrpdhv (219) -> cbrfntx, raqfhc\n" +
            "mhnprqw (97)\n" +
            "vuatju (52)\n" +
            "bxvaxw (60)\n" +
            "awmjc (45)\n" +
            "vdkjcp (56) -> azfrf, gdkka\n" +
            "knofme (7) -> ipgoewc, pjprk, uqyqwny\n" +
            "atcrc (20)\n" +
            "szyya (149) -> mdgqhkw, hgignr\n" +
            "lhclwhi (29)\n" +
            "ktwba (94)\n" +
            "tgedm (91)\n" +
            "gklao (150) -> zdmgb, ncdbvaa\n" +
            "cwcmr (857) -> iudquk, dnwlq, efriw\n" +
            "djfzb (12)\n" +
            "kuiik (72)\n" +
            "umrrz (70)\n" +
            "jgfgg (5) -> edzfey, tvbizrm\n" +
            "ortfml (150) -> arvmgnd, gwhqhzb\n" +
            "qzwkx (73)\n" +
            "bzxcjoz (80)\n" +
            "eblyrp (381) -> dbpxdxl, imzuug\n" +
            "sbkqck (329) -> kycvcdb, ufknphd\n" +
            "bskvox (68)\n" +
            "ygjhtt (21)\n" +
            "synxk (60)\n" +
            "nvaiuzb (80)\n" +
            "ehvljub (97) -> vjikgc, nfebqt\n" +
            "ygnys (57)\n" +
            "lgaruuy (96) -> dwxem, rzqdqj\n" +
            "eesynky (858) -> vzgukn, dyvizny, cjvfvb, tkhlk, bucnil, atkes\n" +
            "huqgqdb (63) -> hdxbz, wxsok, ayntidu, bnhfx\n" +
            "pfolrs (25)\n" +
            "fltpoim (86)\n" +
            "ehpmmve (49)\n" +
            "bwiee (72)\n" +
            "bfkcjn (59)\n" +
            "hyukvw (79) -> hhgop, cnrznxh, gdnzlar, xzmax\n" +
            "xmwcvfq (95) -> iscthbd, ontvyu, bpmqk, hkorax\n" +
            "bruxjz (33)\n" +
            "rvshkx (9)\n" +
            "auoba (233) -> hjeeb, clgsp\n" +
            "abuszt (195) -> avugbx, rwupp, mjvpc\n" +
            "aagrt (35) -> isrqnr, fvvrow, fuzizpc\n" +
            "zwprtx (5)\n" +
            "vzgukn (279) -> fcxdhb, xxswx\n" +
            "wbxcy (27)\n" +
            "njoqwl (12)\n" +
            "rgnjr (47)\n" +
            "ejkioj (25)\n" +
            "yatllw (107) -> palczfm, jgxph\n" +
            "ibokijo (145) -> vbutj, hpcncb\n" +
            "qopbuhe (33)\n" +
            "rkujtq (7)\n" +
            "qizgiau (275)\n" +
            "ysuoctn (88)\n" +
            "ntmldq (301) -> ocsqis, xtmjhz\n" +
            "sahdqia (19)\n" +
            "hviil (3327) -> xvafyvk, jqjzfu, scjjqzv\n" +
            "boncwia (42)\n" +
            "xvcxtx (20)\n" +
            "tshqhis (97)\n" +
            "rlvfcl (357)\n" +
            "fzzbt (9659) -> sddaxt, xkdjnv, axarhk\n" +
            "yeqsa (66)\n" +
            "xxlzsza (76)\n" +
            "fzqhw (33)\n" +
            "xefpxn (95) -> cqoyqhn, txkyw\n" +
            "xcrvb (42)\n" +
            "xwpuhf (98) -> tzepblp, slttwe\n" +
            "qryupbf (35)\n" +
            "ryahia (361) -> mqkaob, anbdqml, aqkdmt\n" +
            "apsinu (59)\n" +
            "nqmede (101) -> xbmbzn, mypzbk\n" +
            "gexwzw (277) -> ehvljub, abuszt, vfkzaix, ghzsq\n" +
            "owagdf (52)\n" +
            "cekovn (21)\n" +
            "jqjzfu (283) -> qcvigx, ojxixj, yjvyqo, kvoeo, dmufjvd, ufaxhkb\n" +
            "clxdcd (50) -> srnuyz, aulrhvv, oofhjqx, ofjopcm\n" +
            "nshqf (76)\n" +
            "ljzqzlt (26)\n" +
            "gopynr (95) -> boncwia, ugoyb, pawxyqe\n" +
            "mcwevy (1085) -> cubeivy, ejkfjsb, wlffbug\n" +
            "jghceu (39)\n" +
            "cigtx (286) -> cshmi, uytekqt, bhwirm\n" +
            "jxdpzr (208)\n" +
            "kxlbaue (126) -> vkugys, gkgxg\n" +
            "kxrorv (43)\n" +
            "dzkqrjf (38)\n" +
            "mgrolho (54)\n" +
            "kvwjces (23)\n" +
            "grdozg (37)\n" +
            "hygnrpp (64)\n" +
            "shojrc (65)\n" +
            "qleml (59)\n" +
            "hjjchx (58)\n" +
            "uicmcph (8)\n" +
            "coiuo (174) -> tplrfil, nxnegu\n" +
            "lccfqhm (173) -> ivejr, mabydz\n" +
            "udyms (1120) -> lsmylfm, bzwof, xmwcvfq\n" +
            "taljv (79) -> ploofc, xzbry\n" +
            "ybsetv (55)\n" +
            "qxmdjq (10)\n" +
            "nlnwd (12)\n" +
            "xzfggf (51)\n" +
            "qxylp (169) -> dujat, wonqggh\n" +
            "ekild (72)\n" +
            "cidfe (8)\n" +
            "pzbruav (66)\n" +
            "wnsnr (1086) -> chmmaa, zcvlo, lktenr\n" +
            "qcgirq (99)\n" +
            "kcftmnv (23)\n" +
            "mpunhg (12)\n" +
            "bhwirm (14)\n" +
            "wztqxw (47)\n" +
            "jzkkmio (61)\n" +
            "anxrcns (19)\n" +
            "rddasm (90)\n" +
            "gomgkol (1877) -> nqmede, vtrhhwq, tiseyn\n" +
            "treywsr (11)\n" +
            "zyghjdm (168) -> bbehb, zazwgl\n" +
            "fantkyj (1356) -> tyviul, sbkqck, karmp\n" +
            "puyxatg (6)\n" +
            "mbzah (35) -> mfcryoc, nnjtb\n" +
            "bjdzogu (145) -> vkcixm, jsjxl\n" +
            "qxzeexk (182) -> hfqgoz, dwsljog, jhfdmw, mzxyyfo\n" +
            "orsmk (52)\n" +
            "wdcathn (68)\n" +
            "nfbln (70)\n" +
            "hnevf (7)\n" +
            "iiwvkl (291)\n" +
            "bbehb (30)\n" +
            "tvbizrm (84)\n" +
            "abjmnp (84)\n" +
            "cairgd (22)\n" +
            "nnjtb (57)\n" +
            "ucyuje (205) -> kampbl, lplefi\n" +
            "pjprk (58)\n" +
            "aulrhvv (94)\n" +
            "pealhre (45)\n" +
            "xbomtaj (84)\n" +
            "nqmrbb (20)\n" +
            "vxgsa (37) -> uwnvz, dzone, yoddspj, erxep\n" +
            "afwufw (410) -> lyhuihb, wvihp\n" +
            "iyywca (20)\n" +
            "ejwrem (452) -> pipax, jxdpzr, lpilno\n" +
            "guwoota (144) -> kpyped, vtxpy, immxnu\n" +
            "dyvizny (317) -> yrpolf, hjgaun\n" +
            "ploofc (52)\n" +
            "bukih (82) -> ebargy, netxsyl, qcylfon, furgqht\n" +
            "ilstw (97)\n" +
            "sszjzj (205)\n" +
            "aqkdmt (31)\n" +
            "koqxg (55) -> bxlur, ebjym\n" +
            "nrkctj (4287) -> xxnqt, dpiexx, hkgcdih, lrslve\n" +
            "okmrsd (21) -> gcsiaut, ppakqms, odhnwli, dyvgigf\n" +
            "crkcm (80)\n" +
            "ycxsmt (167) -> snxzn, abhtg\n" +
            "fmfksfr (57)\n" +
            "oofhjqx (94)\n" +
            "apzzew (77) -> xeqle, fovzx\n" +
            "pbmhfx (51) -> ffncznl, nyojm, rfhbuox, rsjswd, ntmldq, iflde, ythhfq\n" +
            "jnvrw (159) -> njmxjzn, aqzibdb\n" +
            "zqkhpn (5)\n" +
            "wfwfpat (58)\n" +
            "bilbca (89)\n" +
            "osbaf (63)\n" +
            "wqqmvoe (76)\n" +
            "oyhgs (93)\n" +
            "wpirdu (80) -> wcdlx, jbqfn, xbomtaj\n" +
            "bnhfx (753) -> gvfit, jgfgg, daebu, jwmtw, qhttqrn, ibokijo\n" +
            "zuvar (45)\n" +
            "ldcdes (51)\n" +
            "puuiqr (58)\n" +
            "vegnaz (3528) -> upksf, lzife, jwioxco, trvqvd\n" +
            "amliqh (47)\n" +
            "pkvblpt (91)\n" +
            "crtmtt (81)\n" +
            "oahdsnf (241) -> tetsf, zmekfc\n" +
            "fjjgfl (188) -> waturpr, fedmbom, ieebgv\n" +
            "rhfcxzg (79)\n" +
            "kjegcl (87) -> xwyzhc, dxmuy, wgcckll\n" +
            "rxxft (393) -> jwytokt, xiecr\n" +
            "usudfnb (48)\n" +
            "vtrhhwq (159)\n" +
            "hawmox (30) -> mdubrq, puwktt, istew, vjfzfkf\n" +
            "cshmi (14)\n" +
            "vtkaia (37)\n" +
            "yiqqluf (200) -> ddhzzxi, qnknn\n" +
            "rfhbuox (185) -> wkyhcm, jydmmzj\n" +
            "hbvroo (60) -> hejkytc, uiqwd\n" +
            "jlick (240) -> vuatju, cqfebqe\n" +
            "sunnnk (38)\n" +
            "umdid (49)\n" +
            "qcvigx (152) -> rhfcxzg, gqauurp\n" +
            "keyfi (37)\n" +
            "axilhr (41) -> yblpan, qigwdd\n" +
            "tuwix (82) -> kucajqv, ymednx, bgkxjiw\n" +
            "yrmjwj (29)\n" +
            "claeea (84)\n" +
            "yrahjwp (104) -> qllpxw, euhgp\n" +
            "qdoiidp (180)\n" +
            "hdxbz (31) -> yrwzi, isrgun, luzyhbe, goaeny\n" +
            "ctaya (426)\n" +
            "sddaxt (2232) -> mqtmr, lqtckpk\n" +
            "yhfjs (38)\n" +
            "xysxg (62)\n" +
            "ahvzxw (11)\n" +
            "tnpiig (97)\n" +
            "hyvzyy (18)\n" +
            "wckuhkd (15)\n" +
            "lgmfk (72)\n" +
            "wfinqez (63)\n" +
            "efrqc (1019) -> skhjb, peqsua, aijgw, zzzcrp\n" +
            "wvwqp (91) -> mkbny, oukxnyz, qeidnw, jlick, jauxs, ebtazb, ceghgg\n" +
            "dzlygu (229) -> zmrjb, abvvbtl\n" +
            "rtydk (2202) -> wlxkzyu, ikmldw, guwoota, iczndtj\n" +
            "prsysf (318) -> bnrgfa, pvtmulz, koviipt, qtfpf\n" +
            "bjdfo (10)\n" +
            "qavye (22)\n" +
            "phxjq (75)\n" +
            "ujmboh (42)\n" +
            "mrexh (68) -> aagrt, klnuy, atcckfh\n" +
            "dxspx (7)\n" +
            "boyyulo (67) -> vtkaia, tufamsz\n" +
            "lrslve (403) -> dnigru, ylftgo, byijdr\n" +
            "dzone (66)\n" +
            "qjdum (88)\n" +
            "rzkgb (19)\n" +
            "lrriax (89)\n" +
            "mypzbk (29)\n" +
            "vnpjttm (15)\n" +
            "feogbnr (25)\n" +
            "euhgp (19)\n" +
            "dujat (7)\n" +
            "jncdjr (6)\n" +
            "fedjn (76)\n" +
            "ydnxkn (34) -> zkpbt, tnvtn\n" +
            "vkcixm (78)\n" +
            "xcubkli (41)\n" +
            "daebu (41) -> zlrwizb, rcelntk\n" +
            "emhikp (20)\n" +
            "jydmmzj (72)\n" +
            "yrpolf (40)\n" +
            "temdg (73)\n" +
            "chpcn (28) -> fgctru, uiaqaai, qxzeexk, isdcga\n" +
            "gnirnf (5)\n" +
            "ffncznl (315) -> jhxdgxv, iilaoq\n" +
            "klfkq (31)\n" +
            "ierxej (205)\n" +
            "bstmzd (96) -> kgwuliy, pohtiuv, qxoljws, amliqh\n" +
            "xxswx (59)\n" +
            "azdiwj (43)\n" +
            "ceghgg (287) -> urgiwa, dkhyk, awwicqq\n" +
            "jgtled (95)\n" +
            "vtzpyp (55)\n" +
            "adrate (75) -> hfyevuq, nyfzqsd\n" +
            "elsqz (253) -> rnkrh, obfxoch\n" +
            "mkxke (74) -> uhcrfl, bnhfnlw, hdzls, pcfilur, onnfacs, wdugfj, jakdiea\n" +
            "msutjz (20)\n" +
            "osevo (61) -> crkcm, bzxcjoz, qrlcg\n" +
            "rfldln (53) -> vwepws, rdzzlw, xfnmqd, ugmwpnx\n" +
            "glcrysh (78)\n" +
            "izgqj (38)\n" +
            "azkwjyi (34) -> fgdbbei, enuqt\n" +
            "kemaenc (31)\n" +
            "dzwgtf (68)\n" +
            "ruadem (1126) -> osevo, bjdzogu, ablbrw\n" +
            "pduav (264) -> mdsms, edgaqnw\n" +
            "cqcbbbr (46)\n" +
            "yoyvf (131)\n" +
            "zntwpqo (10)\n" +
            "xozkvy (19)\n" +
            "kvoeo (202) -> mgrolho, vhyyv\n" +
            "iqtij (72)\n" +
            "tbimd (59) -> hvtqmrn, oscxinx, oubxwbc, fzugjg\n" +
            "owjjchv (23)\n" +
            "enjrxrw (219) -> pgyabg, nvglnd, itnpmen, yrmjwj\n" +
            "xfcoi (23) -> ykpcq, dqggh, fitaq, rxxft, jkmzstd, xiuxqpp, qvdkqw\n" +
            "satjbta (241) -> jzzng, kirybeg\n" +
            "hgnkw (9) -> osbaf, wfinqez, oksum\n" +
            "mqtksj (281) -> nrneucv, zqkhpn\n" +
            "ghzsq (97) -> pzpwzpp, lmdteh\n" +
            "muiela (120) -> jrqht, eepyf, njqjd\n" +
            "xhkbihe (169)\n" +
            "biqeev (68)\n" +
            "mjxqlx (15)\n" +
            "vjikgc (61)\n" +
            "ncsro (109) -> cpdapys, niphm\n" +
            "eadol (72)\n" +
            "ywshnoc (19)\n" +
            "appwc (74) -> eyqpybt, unwpat, cmpldn\n" +
            "vkbpp (23)\n" +
            "nlknxtv (9) -> qweyg, nbidkx\n" +
            "kdnrfhs (454)\n" +
            "ppakqms (41)\n" +
            "wiuxjsd (249) -> rgdsxy, mpunhg\n" +
            "owzsiku (52) -> zvhjz, oyhgs\n" +
            "jlzlxib (48)\n" +
            "qyrope (62) -> yrucl, ldxqsu, oihlb, eblyrp\n" +
            "nqobvf (66)\n" +
            "rjzmzv (72)\n" +
            "hizhauj (72)\n" +
            "utydsr (37)\n" +
            "mdwrg (122) -> hutkx, hbdsnvx, xtgrt, fzqhw\n" +
            "dxmuy (19)\n" +
            "dugorw (80)\n" +
            "jjqpm (23)\n" +
            "ninnxgi (258)\n" +
            "nqgfa (24)\n" +
            "zpzdnz (62)\n" +
            "juflvu (85) -> rnrxeb, wbmahc, tmzut\n" +
            "cqhgy (48)\n" +
            "xezjpye (862) -> bswzafr, pdumcu, vdppt\n" +
            "lrvwrl (85)\n" +
            "cdlqgyh (291)\n" +
            "hyhvgle (19)\n" +
            "wzzey (54)\n" +
            "hhgop (49)\n" +
            "rdcew (31)\n" +
            "rxoja (18)\n" +
            "dlmmqrm (41)\n" +
            "kvmxfku (54)\n" +
            "ayqsng (18)\n" +
            "niphm (39)\n" +
            "nxrhbhh (87) -> lmvbhm, orwwh\n" +
            "fpejk (10)\n" +
            "sfvfge (292) -> kvrmjma, rvshkx\n" +
            "gdkka (44)\n" +
            "smhbvd (263) -> qalkey, rkujtq\n" +
            "ieebgv (85) -> jcortpe, fslbtk\n" +
            "clnzh (254)\n" +
            "fluqhsw (88)\n" +
            "igwetl (68)\n" +
            "dhnttwg (72)\n" +
            "gcsiaut (41)\n" +
            "hgignr (56)\n" +
            "yywuo (21)\n" +
            "pzgagh (17)\n" +
            "cnrznxh (49)\n" +
            "cgyxd (84)\n" +
            "yheme (192) -> isvfqpy, qopbuhe\n" +
            "iftzv (258)\n" +
            "wknjhvo (107) -> swwwls, earhrts, ogfsdjs\n" +
            "cbwagy (89)\n" +
            "zdmgb (54)\n" +
            "chozum (24)\n" +
            "yqndxmd (43)\n" +
            "vwgvrw (59)\n" +
            "mnezur (84)\n" +
            "kajmeo (115) -> yqndxmd, mxnvqf\n" +
            "ftquc (84) -> grctgck, igoifku, xefpxn, cddgm\n" +
            "rcvhb (10) -> lvbgz, utyriu, zpzdnz, wjuyj\n" +
            "ghpotnp (181) -> vdjoaq, xvcxtx\n" +
            "tefpuyj (90) -> dbirye, cfegyp\n" +
            "exryas (72)\n" +
            "ixxaaz (88) -> wckuhkd, hjqlg\n" +
            "dtjmr (61)\n" +
            "jhhnkho (48)\n" +
            "tfnsk (7354) -> otxmmd, vrkihvw, nndiv\n" +
            "idfhr (28)\n" +
            "dssoru (95)\n" +
            "piaeb (99)\n" +
            "cfcmep (51) -> dsdvxuw, bqxiezq, bqypkb\n" +
            "dmufjvd (310)\n" +
            "bbenpz (43)\n" +
            "jwdtam (93)\n" +
            "ehajus (63)\n" +
            "unxjpl (91)\n" +
            "xogbq (19) -> igwetl, dzwgtf, wdcathn, bskvox\n" +
            "njqjd (42)\n" +
            "hpxoxk (57)\n" +
            "nlnqo (92)\n" +
            "kdjoii (83)\n" +
            "xhrgl (30)\n" +
            "cjvfvb (301) -> zfbolo, fvord, tiqmony, snqeb\n" +
            "glbnsvc (17)\n" +
            "huantpz (186) -> oxdqy, mpnqbac\n" +
            "lktenr (135) -> meonp, ampno\n" +
            "cqhnl (53)\n" +
            "cidce (454)\n" +
            "vcqgruj (218)\n" +
            "tfkrv (93) -> cxocay, zigchs\n" +
            "kgwuliy (47)\n" +
            "baqiy (75)\n" +
            "whurga (13)\n" +
            "eoeiflq (38)\n" +
            "fzugjg (44)\n" +
            "mjvpc (8)\n" +
            "tayge (104) -> hyhvgle, anxrcns, ywshnoc, qqxng\n" +
            "hgnqq (45)\n" +
            "oacpgj (95)\n" +
            "chpvnf (75)\n" +
            "ovdsrbq (47)\n" +
            "pzgqbxm (75)\n" +
            "sdljelr (32)\n" +
            "rijys (94)\n" +
            "qgiwga (64) -> onpzd, jscpi, ljzqzlt\n" +
            "kucajqv (75)\n" +
            "ntnjsb (13)\n" +
            "zxsxos (45)\n" +
            "bzuiexp (20)\n" +
            "ozhoji (70)\n" +
            "cdmidbh (388) -> tyvztv, flgljqv, cairgd\n" +
            "wmqzq (41)\n" +
            "wtfhx (159) -> hsagb, ndpjdmr\n" +
            "kmtyyw (958) -> bexoza, rlvfcl, ajzenm\n" +
            "izqne (9)\n" +
            "iudquk (37) -> keyfi, mrmebqc, grdozg, pdfzwaz\n" +
            "grctgck (111) -> umbwe, qxwiktp\n" +
            "kglxzv (306) -> bmjswlz, hjkfjle\n" +
            "ndprukh (90)\n" +
            "dzxzka (67) -> rhxhumv, qxwmd\n" +
            "ortqko (58)\n" +
            "jkmzstd (223) -> kukri, lzuon, nfbln\n" +
            "kchwzc (22)\n" +
            "ggmgzhd (54)\n" +
            "umbwe (77)\n" +
            "hhmxdvr (1584) -> rurhdvo, xozkvy\n" +
            "yzlzly (76)\n" +
            "wtxinor (22)\n" +
            "cikitqj (58)\n" +
            "dbics (53) -> wtxinor, kyrfsv, kchwzc, hrbmwq\n" +
            "ldxqsu (45) -> qogcdog, mhnprqw, tshqhis, ilstw\n" +
            "cqoca (601) -> mjmjubw, knofme, axilhr\n" +
            "ufnve (210) -> muivti, glbnsvc, ialxayo\n" +
            "nvmkhp (84)\n" +
            "lxgjn (60) -> tihsmo, xgefj, crtmtt\n" +
            "irvuv (92)\n" +
            "lrsoyci (99)\n" +
            "ctsvgc (67)\n" +
            "ugoyb (42)\n" +
            "joohyti (76)\n" +
            "ykpcq (77) -> eqdoyk, jwvgc, lrriax, hycmwzu\n" +
            "dwsljog (20)\n" +
            "srnuyz (94)\n" +
            "bxeum (76)\n" +
            "ibbtm (28)\n" +
            "nifzp (48)\n" +
            "ookaq (81) -> wocartl, baesgig\n" +
            "aylev (63) -> iefpjj, mdtrudc, ywvxjtz\n" +
            "obfxoch (25)\n" +
            "pfchj (27)\n" +
            "pzyax (70)\n" +
            "jyjyy (16)\n" +
            "awgqw (5)\n" +
            "tldwabo (24) -> jznwy, hhmxdvr, grnafmr, qeebfg, emkuxtl, rdwgzr\n" +
            "aajfrf (59)\n" +
            "lucdfi (60)\n" +
            "rdwgzr (788) -> ehsqyyb, bivovn, bvjkr\n" +
            "muxuwcv (68)\n" +
            "lzife (657) -> mkxbl, xsodot, sbwqnh\n" +
            "oukjop (37) -> hyvfpe, qcgirq\n" +
            "wxsok (1146) -> oghwfv, txwypo, kylpez\n" +
            "ahkztpf (123) -> ptfuyxs, oknhrfd\n" +
            "lnmmac (69)\n" +
            "zmrjb (48)\n" +
            "hpcncb (14)\n" +
            "tivuls (24)\n" +
            "zqmmc (111) -> flrpj, ctsvgc\n" +
            "vtxpy (23)\n" +
            "ythhfq (38) -> tnpiig, coegpi, mhrsu\n" +
            "waturpr (16) -> ikegr, dtjmr, blcjl\n" +
            "lfjkju (74) -> zqmmc, mwuhto, sewpop, durycn, lpkak, czvjqk\n" +
            "puwktt (69)\n" +
            "czllgn (15)\n" +
            "dbirye (27)\n" +
            "hixroxf (51)\n" +
            "zjbmj (33)\n" +
            "oksum (63)\n" +
            "dfrdid (73) -> tuwix, ptczh, abphjyv\n" +
            "jvmpgso (171) -> hyvzyy, waukis, uhcach\n" +
            "wzcmmr (75)\n" +
            "grxfsjm (75)\n" +
            "sbwqnh (96) -> muxuwcv, vabwqou, biqeev\n" +
            "fedmbom (93) -> rsebxf, aprln\n" +
            "qjtan (29) -> pxijthu, eadol\n" +
            "ptczh (172) -> kqlwcz, hepedt, rtoecx\n" +
            "uiaqaai (72) -> jgtled, boblgpx\n" +
            "srybbkw (80)\n" +
            "cdurt (55)\n" +
            "bnhfnlw (19497) -> xqrgxx, vegnaz, tldwabo, ltpwxbx, xjmhhb, vmwmulw, hviil\n" +
            "fgdbbei (94)\n" +
            "ablbrw (265) -> lxukyz, rpdslp\n" +
            "zkmtesi (41)\n" +
            "gjpgk (121) -> kzjfx, cyxdrb, aulkaf, msnicb\n" +
            "tiseyn (67) -> cqcbbbr, dxonm\n" +
            "dafryf (249)\n" +
            "gafbnc (94)\n" +
            "ojwprv (33) -> bmxdb, kapmsr, wzzey, kvmxfku\n" +
            "hfyevuq (93)\n" +
            "zzzcrp (100) -> dsmuem, xlpgtmn, rdcew\n" +
            "moqub (15)\n" +
            "hbdsnvx (33)\n" +
            "rexbt (16)\n" +
            "abhtg (81)\n" +
            "iscthbd (80)\n" +
            "gkgxg (36)\n" +
            "wdugfj (72519) -> qdqkaw, xfcoi, rtydk, kbbgkmr, uxsrdpn\n" +
            "xizhxs (18)\n" +
            "qvtxsu (23)\n" +
            "nxnegu (32)\n" +
            "lugtnb (33)\n" +
            "jscgykv (5)\n" +
            "jbmclpv (55)\n" +
            "abphjyv (187) -> elwic, wgxlaau\n" +
            "uhcach (18)";
}
