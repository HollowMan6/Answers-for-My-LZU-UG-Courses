<form method="post" action="index.php">
    用户名: <input type="text" name="username" value="<?php
                                                    if (isset($_COOKIE["username"]))
                                                        echo $_COOKIE['username'] ?>"> <br />
    密码: <input type="password" name="password" value="<?php
                                                        if (isset($_COOKIE["password"]))
                                                            echo $_COOKIE['password'] ?>"> <br />
    <input type="checkbox" name="saveinfo"> 保存我的登录信息 <br />
    <input type="submit">
</form>