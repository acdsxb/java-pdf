<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>test</title>
    <style type="text/css">

        body {
            font: 100% SimSun, Microsoft YaHei, sans-serif;
            margin: 0;
            padding: 0;
            text-align: center;
            color: #000000;
        }

        #container {
            width: 100%;
            margin: 0 auto;
            text-align: left;
        }

        div.header-left {
            font-size: 12px;
            width: 120px;
            position: absolute;
            margin-left: 45px;
        }

        div.header-center {
            margin-left: 63px;
            text-align: center;
            font-size: 14px;
            width: 120px;
            position: absolute;
        }

        div.header-right {
            font-size: 12px;
            width: 120px;
            position: absolute;
            margin-left: 100px;
            text-align: right;
        }

        div.footer-left {
            font-size: 12px;
            width: 120px;
            position: absolute;
            margin-left: 45px;
        }

        div.footer-center {
            font-size: 14px;
            width: 160px;
            text-align: center;
            position: absolute;
            margin-left: 55px;
        }

        div.footer-right {
            font-size: 12px;
            width: 120px;
            position: absolute;
            text-align: right;
            margin-left: 85px;
        }

        @media print {
            div.header-left {
                position: running(header-left);
            }

            div.header-right {
                position: running(header-right);
            }

            div.header-center {
                position: running(header-center);
            }

            div.footer-left {
                position: running(footer-left);
            }

            div.footer-center {
                position: running(footer-center);
            }

            div.footer-right {
                position: running(footer-right);
            }
        }

        @page {
            size:210mm 290mm;
            padding: 1em 3em;
            @top-left {
                content: element(header-left);
            };
            @top-right {
                content: element(header-right)
            };
            @top-center {
                content: element(header-center)
            };
            @bottom-left {
                content: element(footer-left)
            };
            @bottom-center {
                content: element(footer-center)
            };
            @bottom-right {
                content: element(footer-right)
            };
        }

        #pagenumber:before {
            content: counter(page);
        }

        #pagecount:before {
            content: counter(pages);
        }

        div.portrait {
            page: portrait;
        }

        .list {
            font-weight: bold;
            font-size: 18px;
            text-align: center;
        }

        ul {
            list-style: none;
        }

        li {
            line-height: 23px;
        }

        ul li div{
            display:inline-block;
        }

        .list-nap1 {
            margin-left: -41px;
            text-align: left;
            background: #fff;
            padding-right: 5px;
            z-index: 100;
            font-size: 16px;
        }

        .list-nap2 {
            margin-left: -41px;
            text-align: left;
            background: #fff;
            padding-left: 30px;
            padding-right: 5px;
            z-index: 100;
            font-size: 16px;
        }

        .list-line{
            border-top: 1px dashed #333;
            position: absolute;
            margin-top: 11px;
            width: 100%;
            z-index: -111;
            right: 25px;
        }

        .list-con1{
            position: absolute;
            right: 0;
            padding: 0 5px;
            z-index: 100;
            text-align: center;
            background: #fff;
        }

        ul li a {
            text-decoration: none;
            color: #000;
        }

        h4 {
            font-size: 24px;
            font-weight: normal;
            margin: 15px 0;
        }

        h5 {
            font-size: 20px;
            font-weight: normal;
            margin: 13px 0;
        }

        h6 {
            font-size: 16px;
            font-weight: normal;
            margin: 11px 0;
        }

        .sign {
            width: auto;
            text-align: right;
            font-size: 20px;
        }

        .sign > p {
            margin: 10px;
        }

    </style>
</head>
<body>
<div id="container">
    <div class="header">
        <!--***************页眉_start*****************-->
        <div id="header-left" class="header-left">
            <a href="https://www.baidu.com">百度</a>
        </div>
        <div id="header-center" class="header-center">
            <a href="https://www.baidu.com">百度</a>
        </div>
        <div id="header-right" class="header-right">
            <div class="right-img">
                <a href="https://www.baidu.com">百度</a>
            </div>
        </div>
        <!--***************页眉_end*****************-->
    </div>
    <div id="footer">
        <!--***************页脚_start*****************-->
        <div id="footer-left" class="footer-left">
            <a href="https://www.baidu.com">百度</a>
        </div>
        <div id="footer-center" class="footer-center">
            <div> 第 <span id="pagenumber"></span> 页 / 共 <span id="pagecount"></span> 页</div>
        </div>
            <div id="footer-right" class="footer-right">
                <a href="https://www.baidu.com">百度</a>
            </div>
        <!--***************页脚_endt*****************-->
    </div>
    <div class="portrait" style="page-break-after:always">
        <div style="margin-top: 300px;">
            <div style="font-family: Microsoft YaHei;font-size: 26px;text-align: center; margin-bottom: 30px;">
                123
            </div>
            <div style="text-align: center;display: block;font-family: Microsoft YaHei;font-size: 26px;">
                封面
            </div>
            <div style="text-align: center;display: block; font-size:18px;margin-top: 40px;">
                ${.now?string("yyyy")} 年度
            </div>
        </div>
        <div style="text-align: center;display: block; font-size:20px;margin-top: 300px;">
            xxx有限公司
        </div>
        <div style="text-align: center;display: block; font-size:20px;margin-top: 20px;">
            xxx制
        </div>
    </div>
    <div class="portrait" style="page-break-after:always">
        <p class="list">
            目录
        </p>
        <div>
            <ul>
                <li>
                    <div class="list-nap1">
                        <a href="#1">一、标题1</a>
                    </div>
                    <div class="list-line"></div>
                    <div class="list-con1">
                    </div>
                </li>

                <li>
                    <div class="list-nap1">
                        <a href="#2">二、标题2</a>
                    </div>
                    <div class="list-line"></div>
                    <div class="list-con1">
                    </div>
                </li>

                <li>
                    <div class="list-nap2">
                        <a href="#2-1">1.标题2-1</a>
                    </div>
                    <div class="list-line"></div>
                    <div class="list-con1">
                    </div>
                </li>

                <li>
                    <div class="list-nap2">
                        <a href="#2-2">2.标题2-2</a>
                    </div>
                    <div class="list-line"></div>
                    <div class="list-con1">
                    </div>
                </li>

                <li>
                    <div class="list-nap1">
                        <a href="#3">三、标题3</a>
                    </div>
                    <div class="list-line"></div>
                    <div class="list-con1">
                    </div>
                </li>

                <li>
                    <div class="list-nap1">
                        <a href="#last">结尾</a>
                    </div>
                    <div class="list-line"></div>
                    <div class="list-con1">
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <div class="portrait" style="page-break-after:always">
        <h4 id="1">
            一、标题1
        </h4>
    </div>

    <div class="portrait" style="page-break-after:always">
        <h4 id="2">
            二、标题2
        </h4>
    </div>

    <div class="portrait" style="page-break-after:always">
        <h5 id="2-1">
            1.标题2-1
        </h5>
    </div>

    <div class="portrait" style="page-break-after:always">
        <h5 id="2-2">
            2.标题2-2
        </h5>
    </div>

    <div class="portrait" style="page-break-after:always">
        <h4 id="3">
            三、标题3
        </h4>
    </div>

    <div class="portrait">
        <h4 id="last">
            结尾
        </h4>
        <div class="sign">
            <p class="time">
                ${.now?string("yyyy 年 M 月 d 日")}
            </p>
        </div>
    </div>
</div>
</body>
</html>