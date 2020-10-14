<html>

<head>
    <title>学校食堂调查问卷结果</title>
    <meta charset="utf-8">
    <style>
    div{
        width: 400px;
        height: 100px;
        position: absolute;
        left: 42%;
        top: 15%;
        margin: -20px 0 0 -20px;
    }
    </style>
</head>

<body>
    <div>
        <h1>学校食堂调查问卷结果</h1>
        <span style="color:red"><small>* </small></span><strong>姓名</strong>：
        <?php
            echo $_POST["username"];
        ?>
        <br /><br /> 
        <span style="color:red"><small>* </small></span><strong>性别</strong>：
        <?php
            echo $_POST["gender"];
        ?>
        <br /><br /> 
        <span style="color:red"><small>* </small></span><strong>年级</strong>：
        <?php
            echo $_POST["grade"];
        ?>
        <br /><br /> 
        <strong>最满意的食品</strong>：
        <br />
        <?php
            if(empty($_POST["favorfood"]))
                echo "无<br />";
            else
                foreach($_POST["favorfood"] as $value)
                    echo $value."<br />";
        ?> 
        <br /> 
        <strong>最不满意的食品</strong>：
        <br />
        <?php
            if(empty($_POST["unfavorfood"]))
                echo "无<br />";
            else
                foreach($_POST["unfavorfood"] as $value)
                    echo $value."<br />";
        ?>
        <br /> 
        <span style="color:red"><small>* </small></span><strong>意见和建议</strong>：
        <br />
        <?php
            echo $_POST["feedback"];
        ?>
        <br /><br /> 
        <small>注：标<span style="color:red">*</span>的为必填项</small>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </div>
</body>

</html>