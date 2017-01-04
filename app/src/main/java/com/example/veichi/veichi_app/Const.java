package com.example.veichi.veichi_app;

class Const {

    final static boolean bGroup_C_Chose_AC100 = false;
    final static boolean bGroup_C_Chose_SD650 = true;


    final static String WIFIPASSWORD = "87654321";

    final static String SOCKET_SERVER = "192.168.11.201";

    final static int SOCKET_PORT = 8008;

    // 默认timeout 时间 60s
    final static int SOCKET_TIMOUT = 60 * 1000;

    final static int SOCKET_READ_TIMOUT = 15 * 1000;

    //如果没有连接无服务器。读线程的sleep时间
    final static int SOCKET_SLEEP_SECOND = 3 ;

    //心跳包发送间隔时间
    final static int SOCKET_HEART_SECOND =3 ;

    final static String BC = "BC";

    final static int ANDWERED_MONITOR_RESTART = 0;
    final static int ANDWERED_MONITOR_1 = 1;
    final static int ANDWERED_MONITOR_2 = 2;
    final static int ANDWERED_MONITOR_3 = 3;
    public final static int ANDWERED_MONITOR_4 = 4;
    public final static int ANDWERED_MONITOR_5 = 5;

    public final static int START_PARAMETER = 44;
    public final static int READ_PARAMETER = 55;
    public final static int WRITE_PARAMETER = 66;

    public final static int MONITOR_BUTTON_FWDRUNNING = 71;
    public final static int MONITOR_BUTTON_REVRUNNING = 72;
    public final static int MONITOR_BUTTON_FREESTOP = 73;
    public final static int MONITOR_BUTTON_SLOWSTOP = 74;
    public final static int MONITOR_BUTTON_ALARMRESET = 75;


    public final static String FDW_RUNNING_CMD = "01063001000116CA";
    public final static String REV_RUNNING_CMD = "01063001000256CB";
    public final static String FREE_STOP_CMD = "0106300100065708";
    public final static String SLOW_STOP_CMD = "0106300100051709";
    public final static String ALRM_RESET_CMD = "01063001000796C8";

    ///////////////...............................F0
    public final static int F0_00 = 0;
    public final static int F0_01 = 1;
    public final static int F0_02 = 2;
    public final static int F0_03 = 3;
    public final static int F0_04 = 4;
    public final static int F0_05 = 5;
    public final static int F0_06 = 6;
    public final static int F0_07 = 7;
    public final static int F0_08 = 8;
    public final static int F0_09 = 9;

    public final static int F0_10 = 10;
    public final static int F0_11 = 11;
    public final static int F0_12 = 12;
    public final static int F0_13 = 13;
    public final static int F0_14 = 14;
    public final static int F0_15 = 15;
    public final static int F0_16 = 16;
    public final static int F0_17 = 17;
    public final static int F0_18 = 18;
    public final static int F0_19 = 19;

    public final static int F0_20 = 20;
    public final static int F0_21 = 21;
    public final static int F0_22 = 22;
    public final static int F0_23 = 23;
    public final static int F0_24 = 24;
    public final static int F0_25 = 25;
    public final static int F0_26 = 26;
    public final static int F0_27 = 27;
    public final static int F0_28 = 28;
    public final static int F0_29 = 29;

    public final static int F0_30 = 30;
    public final static int F0_31 = 31;
    public final static int F0_32 = 32;
    public final static int F0_33 = 33;
    public final static int F0_34 = 34;
    public final static int F0_35 = 35;
    public final static int F0_36 = 36;
    public final static int F0_37 = 37;
    public final static int F0_38 = 38;
    public final static int F0_39 = 39;
    ///////////////...............................F1
    public final static int F1_00 = 100;
    public final static int F1_01 = 101;
    public final static int F1_02 = 102;
    public final static int F1_03 = 103;
    public final static int F1_04 = 104;
    public final static int F1_05 = 105;
    public final static int F1_06 = 106;
    public final static int F1_07 = 107;
    public final static int F1_08 = 108;
    public final static int F1_09 = 109;

    public final static int F1_10 = 110;
    public final static int F1_11 = 111;
    public final static int F1_12 = 112;
    public final static int F1_13 = 113;
    public final static int F1_14 = 114;
    public final static int F1_15 = 115;
    public final static int F1_16 = 116;
    public final static int F1_17 = 117;
    public final static int F1_18 = 118;
    public final static int F1_19 = 119;

    public final static int F1_20 = 120;
    public final static int F1_21 = 121;
    public final static int F1_22 = 122;
    public final static int F1_23 = 123;
    public final static int F1_24 = 124;
    public final static int F1_25 = 125;
    public final static int F1_26 = 126;
    public final static int F1_27 = 127;
    public final static int F1_28 = 128;
    public final static int F1_29 = 129;

    public final static int F1_30 = 130;
    public final static int F1_31 = 131;
    public final static int F1_32 = 132;
    public final static int F1_33 = 133;
    public final static int F1_34 = 134;
    public final static int F1_35 = 135;
    public final static int F1_36 = 136;
    public final static int F1_37 = 137;
    public final static int F1_38 = 138;
    public final static int F1_39 = 139;

    ///////////////...............................F2
    public final static int F2_00 = 200;
    public final static int F2_01 = 201;
    public final static int F2_02 = 202;
    public final static int F2_03 = 203;
    public final static int F2_04 = 204;
    public final static int F2_05 = 205;
    public final static int F2_06 = 206;
    public final static int F2_07 = 207;
    public final static int F2_08 = 208;
    public final static int F2_09 = 209;

    public final static int F2_10 = 210;
    public final static int F2_11 = 211;
    public final static int F2_12 = 212;
    public final static int F2_13 = 213;
    public final static int F2_14 = 214;
    public final static int F2_15 = 215;
    public final static int F2_16 = 216;
    public final static int F2_17 = 217;
    public final static int F2_18 = 218;
    public final static int F2_19 = 219;

    public final static int F2_20 = 220;
    public final static int F2_21 = 221;
    public final static int F2_22 = 222;
    public final static int F2_23 = 223;
    public final static int F2_24 = 224;
    public final static int F2_25 = 225;
    public final static int F2_26 = 226;
    public final static int F2_27 = 227;
    public final static int F2_28 = 228;
    public final static int F2_29 = 229;

    public final static int F2_30 = 230;
    public final static int F2_31 = 231;
    public final static int F2_32 = 232;
    public final static int F2_33 = 233;
    public final static int F2_34 = 234;
    public final static int F2_35 = 235;
    public final static int F2_36 = 236;
    public final static int F2_37 = 237;
    public final static int F2_38 = 238;
    public final static int F2_39 = 239;

    public final static int F2_40 = 240;
    public final static int F2_41 = 241;
    public final static int F2_42 = 242;
    public final static int F2_43 = 243;
    public final static int F2_44 = 244;
    public final static int F2_45 = 245;
    public final static int F2_46 = 246;
    public final static int F2_47 = 247;
    public final static int F2_48 = 248;
    public final static int F2_49 = 249;

    public final static int F2_50 = 250;
    public final static int F2_51 = 251;
    public final static int F2_52 = 252;
    public final static int F2_53 = 253;
    public final static int F2_54 = 254;
    public final static int F2_55 = 255;
    public final static int F2_56 = 256;
    public final static int F2_57 = 257;
    public final static int F2_58 = 258;
    public final static int F2_59 = 259;

    public final static int F2_60 = 260;
    public final static int F2_61 = 261;
    public final static int F2_62 = 262;
    public final static int F2_63 = 263;
    public final static int F2_64 = 264;
    public final static int F2_65 = 265;
    public final static int F2_66 = 266;
    public final static int F2_67 = 267;
    public final static int F2_68 = 268;
    public final static int F2_69 = 269;

    public final static int F2_70 = 270;
    public final static int F2_71 = 271;
    public final static int F2_72 = 272;
    public final static int F2_73 = 273;
    public final static int F2_74 = 274;
    public final static int F2_75 = 275;
    public final static int F2_76 = 276;
    public final static int F2_77 = 277;
    public final static int F2_78 = 278;
    public final static int F2_79 = 279;
    ///////////////...............................F3
    public final static int F3_00 = 300;
    public final static int F3_01 = 301;
    public final static int F3_02 = 302;
    public final static int F3_03 = 303;
    public final static int F3_04 = 304;
    public final static int F3_05 = 305;
    public final static int F3_06 = 306;
    public final static int F3_07 = 307;
    public final static int F3_08 = 308;
    public final static int F3_09 = 309;

    public final static int F3_10 = 310;
    public final static int F3_11 = 311;
    public final static int F3_12 = 312;
    public final static int F3_13 = 313;
    public final static int F3_14 = 314;
    public final static int F3_15 = 315;
    public final static int F3_16 = 316;
    public final static int F3_17 = 317;
    public final static int F3_18 = 318;
    public final static int F3_19 = 319;

    public final static int F3_20 = 320;
    public final static int F3_21 = 321;
    public final static int F3_22 = 322;
    public final static int F3_23 = 323;
    public final static int F3_24 = 324;
    public final static int F3_25 = 325;
    public final static int F3_26 = 326;
    public final static int F3_27 = 327;
    public final static int F3_28 = 328;
    public final static int F3_29 = 329;

    public final static int F3_30 = 330;
    public final static int F3_31 = 331;
    public final static int F3_32 = 332;
    public final static int F3_33 = 333;
    public final static int F3_34 = 334;
    public final static int F3_35 = 335;
    public final static int F3_36 = 336;
    public final static int F3_37 = 337;
    public final static int F3_38 = 338;
    public final static int F3_39 = 339;

    public final static int F3_40 = 340;
    public final static int F3_41 = 341;
    public final static int F3_42 = 342;
    public final static int F3_43 = 343;
    public final static int F3_44 = 344;
    public final static int F3_45 = 345;
    public final static int F3_46 = 346;
    public final static int F3_47 = 347;
    public final static int F3_48 = 348;
    public final static int F3_49 = 349;

    public final static int F3_50 = 350;
    public final static int F3_51 = 351;
    public final static int F3_52 = 352;
    public final static int F3_53 = 353;
    public final static int F3_54 = 354;
    public final static int F3_55 = 355;
    public final static int F3_56 = 356;
    public final static int F3_57 = 357;
    public final static int F3_58 = 358;
    public final static int F3_59 = 359;
    ///////////////...............................F4
    public final static int F4_00 = 400;
    public final static int F4_01 = 401;
    public final static int F4_02 = 402;
    public final static int F4_03 = 403;
    public final static int F4_04 = 404;
    public final static int F4_05 = 405;
    public final static int F4_06 = 406;
    public final static int F4_07 = 407;
    public final static int F4_08 = 408;
    public final static int F4_09 = 409;

    public final static int F4_10 = 410;
    public final static int F4_11 = 411;
    public final static int F4_12 = 412;
    public final static int F4_13 = 413;
    public final static int F4_14 = 414;
    public final static int F4_15 = 415;
    public final static int F4_16 = 416;
    public final static int F4_17 = 417;
    public final static int F4_18 = 418;
    public final static int F4_19 = 419;
    ///////////////...............................F5
    public final static int F5_00 = 500;
    public final static int F5_01 = 501;
    public final static int F5_02 = 502;
    public final static int F5_03 = 503;
    public final static int F5_04 = 504;
    public final static int F5_05 = 505;
    public final static int F5_06 = 506;
    public final static int F5_07 = 507;
    public final static int F5_08 = 508;
    public final static int F5_09 = 509;

    public final static int F5_10 = 510;
    public final static int F5_11 = 511;
    public final static int F5_12 = 512;
    public final static int F5_13 = 513;
    public final static int F5_14 = 514;
    public final static int F5_15 = 515;
    public final static int F5_16 = 516;
    public final static int F5_17 = 517;
    public final static int F5_18 = 518;
    public final static int F5_19 = 519;

    public final static int F5_20 = 520;
    public final static int F5_21 = 521;
    public final static int F5_22 = 522;
    public final static int F5_23 = 523;
    public final static int F5_24 = 524;
    public final static int F5_25 = 525;
    public final static int F5_26 = 526;
    public final static int F5_27 = 527;
    public final static int F5_28 = 528;
    public final static int F5_29 = 529;
    ///////////////...............................F6
    public final static int F6_00 = 600;
    public final static int F6_01 = 601;
    public final static int F6_02 = 602;
    public final static int F6_03 = 603;
    public final static int F6_04 = 604;
    public final static int F6_05 = 605;
    public final static int F6_06 = 606;
    public final static int F6_07 = 607;
    public final static int F6_08 = 608;
    public final static int F6_09 = 609;

    public final static int F6_10 = 610;
    public final static int F6_11 = 611;
    public final static int F6_12 = 612;
    public final static int F6_13 = 613;
    public final static int F6_14 = 614;
    public final static int F6_15 = 615;
    public final static int F6_16 = 616;
    public final static int F6_17 = 617;
    public final static int F6_18 = 618;
    public final static int F6_19 = 619;

    public final static int F6_20 = 620;
    public final static int F6_21 = 621;
    public final static int F6_22 = 622;
    public final static int F6_23 = 623;
    public final static int F6_24 = 624;
    public final static int F6_25 = 625;
    public final static int F6_26 = 626;
    public final static int F6_27 = 627;
    public final static int F6_28 = 628;
    public final static int F6_29 = 629;

    public final static int F6_30 = 630;
    public final static int F6_31 = 631;
    public final static int F6_32 = 632;
    public final static int F6_33 = 633;
    public final static int F6_34 = 634;
    public final static int F6_35 = 635;
    public final static int F6_36 = 636;
    public final static int F6_37 = 637;
    public final static int F6_38 = 638;
    public final static int F6_39 = 639;

    public final static int F6_40 = 640;
    public final static int F6_41 = 641;
    public final static int F6_42 = 642;
    public final static int F6_43 = 643;
    public final static int F6_44 = 644;
    public final static int F6_45 = 645;
    public final static int F6_46 = 646;
    public final static int F6_47 = 647;
    public final static int F6_48 = 648;
    public final static int F6_49 = 649;

    public final static int F6_50 = 650;
    public final static int F6_51 = 651;
    public final static int F6_52 = 652;
    public final static int F6_53 = 653;
    public final static int F6_54 = 654;
    public final static int F6_55 = 655;
    public final static int F6_56 = 656;
    public final static int F6_57 = 657;
    public final static int F6_58 = 658;
    public final static int F6_59 = 659;
    ///////////////...............................F7
    public final static int F7_00 = 700;
    public final static int F7_01 = 701;
    public final static int F7_02 = 702;
    public final static int F7_03 = 703;
    public final static int F7_04 = 704;
    public final static int F7_05 = 705;
    public final static int F7_06 = 706;
    public final static int F7_07 = 707;
    public final static int F7_08 = 708;
    public final static int F7_09 = 709;

    public final static int F7_10 = 710;
    public final static int F7_11 = 711;
    public final static int F7_12 = 712;
    public final static int F7_13 = 713;
    public final static int F7_14 = 714;
    public final static int F7_15 = 715;
    public final static int F7_16 = 716;
    public final static int F7_17 = 717;
    public final static int F7_18 = 718;
    public final static int F7_19 = 719;
    ///////////////...............................F8
    public final static int F8_00 = 800;
    public final static int F8_01 = 801;
    public final static int F8_02 = 802;
    public final static int F8_03 = 803;
    public final static int F8_04 = 804;
    public final static int F8_05 = 805;
    public final static int F8_06 = 806;
    public final static int F8_07 = 807;
    public final static int F8_08 = 808;
    public final static int F8_09 = 809;

    public final static int F8_10 = 810;
    public final static int F8_11 = 811;
    public final static int F8_12 = 812;
    public final static int F8_13 = 813;
    public final static int F8_14 = 814;
    public final static int F8_15 = 815;
    public final static int F8_16 = 816;
    public final static int F8_17 = 817;
    public final static int F8_18 = 818;
    public final static int F8_19 = 819;

    public final static int F8_20 = 820;
    public final static int F8_21 = 821;
    public final static int F8_22 = 822;
    public final static int F8_23 = 823;
    public final static int F8_24 = 824;
    public final static int F8_25 = 825;
    public final static int F8_26 = 826;
    public final static int F8_27 = 827;
    public final static int F8_28 = 828;
    public final static int F8_29 = 829;
    ///////////////...............................FA
    public final static int FA_00 = 1000;
    public final static int FA_01 = 1001;
    public final static int FA_02 = 1002;
    public final static int FA_03 = 1003;
    public final static int FA_04 = 1004;
    public final static int FA_05 = 1005;
    public final static int FA_06 = 1006;
    public final static int FA_07 = 1007;
    public final static int FA_08 = 1008;
    public final static int FA_09 = 1009;

    public final static int FA_10 = 1010;
    public final static int FA_11 = 1011;
    public final static int FA_12 = 1012;
    public final static int FA_13 = 1013;
    public final static int FA_14 = 1014;
    public final static int FA_15 = 1015;
    public final static int FA_16 = 1016;
    public final static int FA_17 = 1017;
    public final static int FA_18 = 1018;
    public final static int FA_19 = 1019;

    public final static int FA_20 = 1020;
    public final static int FA_21 = 1021;
    public final static int FA_22 = 1022;
    public final static int FA_23 = 1023;
    public final static int FA_24 = 1024;
    public final static int FA_25 = 1025;
    public final static int FA_26 = 1026;
    public final static int FA_27 = 1027;
    public final static int FA_28 = 1028;
    public final static int FA_29 = 1029;

    public final static int FA_30 = 1030;
    public final static int FA_31 = 1031;
    final static int FA_32 = 1032;
    final static int FA_33 = 1033;
    final static int FA_34 = 1034;
    final static int FA_35 = 1035;
    final static int FA_36 = 1036;
    final static int FA_37 = 1037;
    final static int FA_38 = 1038;
    final static int FA_39 = 1039;

    final static int FA_40 = 1040;
    final static int FA_41 = 1041;
    final static int FA_42 = 1042;
    final static int FA_43 = 1043;
    final static int FA_44 = 1044;
    public final static int FA_45 = 1045;
    public final static int FA_46 = 1046;
    public final static int FA_47 = 1047;
    public final static int FA_48 = 1048;
    public final static int FA_49 = 1049;
    ///////////////...............................FB
    public final static int FB_00 = 1100;
    public final static int FB_01 = 1101;
    public final static int FB_02 = 1102;
    public final static int FB_03 = 1103;
    public final static int FB_04 = 1104;
    public final static int FB_05 = 1105;
    public final static int FB_06 = 1106;
    public final static int FB_07 = 1107;
    public final static int FB_08 = 1108;
    public final static int FB_09 = 1109;

    public final static int FB_10 = 1110;
    public final static int FB_11 = 1111;
    public final static int FB_12 = 1112;
    public final static int FB_13 = 1113;
    public final static int FB_14 = 1114;
    public final static int FB_15 = 1115;
    public final static int FB_16 = 1116;
    public final static int FB_17 = 1117;
    public final static int FB_18 = 1118;
    public final static int FB_19 = 1119;
    ///////////////...............................FC
    public final static int FC_00 = 1200;
    public final static int FC_01 = 1201;
    public final static int FC_02 = 1202;
    public final static int FC_03 = 1203;
    public final static int FC_04 = 1204;
    public final static int FC_05 = 1205;
    public final static int FC_06 = 1206;
    public final static int FC_07 = 1207;
    public final static int FC_08 = 1208;
    public final static int FC_09 = 1209;

    public final static int FC_10 = 1210;
    public final static int FC_11 = 1211;
    public final static int FC_12 = 1212;
    public final static int FC_13 = 1213;
    public final static int FC_14 = 1214;
    public final static int FC_15 = 1215;
    public final static int FC_16 = 1216;
    public final static int FC_17 = 1217;
    public final static int FC_18 = 1218;
    public final static int FC_19 = 1219;

    public final static int FC_20 = 1220;
    public final static int FC_21 = 1221;
    public final static int FC_22 = 1222;
    public final static int FC_23 = 1223;
    public final static int FC_24 = 1224;
    public final static int FC_25 = 1225;
    public final static int FC_26 = 1226;
    public final static int FC_27 = 1227;
    public final static int FC_28 = 1228;
    public final static int FC_29 = 1229;

    public final static int FC_30 = 1230;
    public final static int FC_31 = 1231;
    public final static int FC_32 = 1232;
    public final static int FC_33 = 1233;
    public final static int FC_34 = 1234;
    public final static int FC_35 = 1235;
    public final static int FC_36 = 1236;
    public final static int FC_37 = 1237;
    public final static int FC_38 = 1238;
    public final static int FC_39 = 1239;

    public final static int FC_40 = 1240;
    public final static int FC_41 = 1241;
    public final static int FC_42 = 1242;
    public final static int FC_43 = 1243;
    public final static int FC_44 = 1244;
    public final static int FC_45 = 1245;
    public final static int FC_46 = 1246;
    public final static int FC_47 = 1247;
    public final static int FC_48 = 1248;
    public final static int FC_49 = 1249;

    public final static int FC_50 = 1250;
    public final static int FC_51 = 1251;
    public final static int FC_52 = 1252;
    public final static int FC_53 = 1253;
    public final static int FC_54 = 1254;
    public final static int FC_55 = 1255;
    public final static int FC_56 = 1256;
    public final static int FC_57 = 1257;
    public final static int FC_58 = 1258;
    public final static int FC_59 = 1259;
    ///////////////...............................FD
    public final static int FD_00 = 1300;
    public final static int FD_01 = 1301;
    public final static int FD_02 = 1302;
    public final static int FD_03 = 1303;
    public final static int FD_04 = 1304;
    public final static int FD_05 = 1305;
    public final static int FD_06 = 1306;
    public final static int FD_07 = 1307;
    public final static int FD_08 = 1308;
    public final static int FD_09 = 1309;

    public final static int FD_10 = 1310;
    public final static int FD_11 = 1311;
    public final static int FD_12 = 1312;
    public final static int FD_13 = 1313;
    public final static int FD_14 = 1314;
    public final static int FD_15 = 1315;
    public final static int FD_16 = 1316;
    public final static int FD_17 = 1317;
    public final static int FD_18 = 1318;
    public final static int FD_19 = 1319;
    ///////////////...............................FE
    public final static int FE_00 = 1400;
    public final static int FE_01 = 1401;
    public final static int FE_02 = 1402;
    public final static int FE_03 = 1403;
    public final static int FE_04 = 1404;
    public final static int FE_05 = 1405;
    public final static int FE_06 = 1406;
    public final static int FE_07 = 1407;
    public final static int FE_08 = 1408;
    public final static int FE_09 = 1409;

    public final static int FE_10 = 1410;
    public final static int FE_11 = 1411;
    public final static int FE_12 = 1412;
    public final static int FE_13 = 1413;
    public final static int FE_14 = 1414;
    public final static int FE_15 = 1415;
    public final static int FE_16 = 1416;
    public final static int FE_17 = 1417;
    public final static int FE_18 = 1418;
    public final static int FE_19 = 1419;

    public final static int FE_20 = 1420;
    public final static int FE_21 = 1421;
    public final static int FE_22 = 1422;
    public final static int FE_23 = 1423;
    public final static int FE_24 = 1424;
    public final static int FE_25 = 1425;
    public final static int FE_26 = 1426;
    public final static int FE_27 = 1427;
    public final static int FE_28 = 1428;
    public final static int FE_29 = 1429;

    public final static int FE_30 = 1430;
    public final static int FE_31 = 1431;
    public final static int FE_32 = 1432;
    public final static int FE_33 = 1433;
    public final static int FE_34 = 1434;
    public final static int FE_35 = 1435;
    public final static int FE_36 = 1436;
    public final static int FE_37 = 1437;
    public final static int FE_38 = 1438;
    public final static int FE_39 = 1439;

    public final static int FE_40 = 1440;
    public final static int FE_41 = 1441;
    public final static int FE_42 = 1442;
    public final static int FE_43 = 1443;
    public final static int FE_44 = 1444;
    public final static int FE_45 = 1445;
    public final static int FE_46 = 1446;
    public final static int FE_47 = 1447;
    public final static int FE_48 = 1448;
    public final static int FE_49 = 1449;

    public final static int FE_50 = 1450;
    public final static int FE_51 = 1451;
    public final static int FE_52 = 1452;
    public final static int FE_53 = 1453;
    public final static int FE_54 = 1454;
    public final static int FE_55 = 1455;
    public final static int FE_56 = 1456;
    public final static int FE_57 = 1457;
    public final static int FE_58 = 1458;
    public final static int FE_59 = 1459;

    ///////////////...............................C
    public final static int C_00 = 1600;
    public final static int C_01 = 1601;
    public final static int C_02 = 1602;
    public final static int C_03 = 1603;
    public final static int C_04 = 1604;
    public final static int C_05 = 1605;
    public final static int C_06 = 1606;
    public final static int C_07 = 1607;
    public final static int C_08 = 1608;
    public final static int C_09 = 1609;

    public final static int C_10 = 1610;
    public final static int C_11 = 1611;
    public final static int C_12 = 1612;
    public final static int C_13 = 1613;
    public final static int C_14 = 1614;
    public final static int C_15 = 1615;
    public final static int C_16 = 1616;
    public final static int C_17 = 1617;
    public final static int C_18 = 1618;
    public final static int C_19 = 1619;

    public final static int C_20 = 1620;
    public final static int C_21 = 1621;
    public final static int C_22 = 1622;
    public final static int C_23 = 1623;
    public final static int C_24 = 1624;
    public final static int C_25 = 1625;
    public final static int C_26 = 1626;
    public final static int C_27 = 1627;
    public final static int C_28 = 1628;
    public final static int C_29 = 1629;

    public final static int C_30 = 1630;
    public final static int C_31 = 1631;
    public final static int C_32 = 1632;
    public final static int C_33 = 1633;
    public final static int C_34 = 1634;
    public final static int C_35 = 1635;
    public final static int C_36 = 1636;
    public final static int C_37 = 1637;
    public final static int C_38 = 1638;
    public final static int C_39 = 1639;

    public final static int C_40 = 1640;
    public final static int C_41 = 1641;
    public final static int C_42 = 1642;
    public final static int C_43 = 1643;
    public final static int C_44 = 1644;
    public final static int C_45 = 1645;
    public final static int C_46 = 1646;
    public final static int C_47 = 1647;
    public final static int C_48 = 1648;
    public final static int C_49 = 1649;

}
