<?php
// 1.读取当前客户端硬盘上的cookies信息，如果存在计数信息，读取；如果不存在，则刷新
if (isset($_COOKIE["count"])) {
    $count = $_COOKIE["count"];
} else
    $count = 0;
//2.阅读次数+1
$count++;
//3.把新的$count写入cookies
setcookie("count", $count, time() + 60 * 60 * 24 * 30);
//4.显示计数信息
if ($count > 1) {
    echo "已访问本页面" . $count . "次<br>";
} else
    echo "欢迎首次访问本页面";
