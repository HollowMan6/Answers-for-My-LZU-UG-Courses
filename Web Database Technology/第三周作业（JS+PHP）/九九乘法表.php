<!DOCTYPE html>
<html>

<head>
    <title>九九乘法表</title>
</head>


<body>
    <table border="1px" cellspacing="2px">
        <?php
            for ($i=1; $i<=9; $i++)
            {
                echo("<tr>");

                for ($j=1; $j<=$i; $j++)
                {
                    echo("<td>");
                    $s = $i * $j;
                    echo "$i x $j = $s";
                    echo("</td>");
                }
                
                echo("</tr>");
            }
         ?>
    </table>
</body>

</html>