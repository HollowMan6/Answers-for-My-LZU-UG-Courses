<?php
function display_login_form()
{
?>

    <body>
        <form action="usercheck.php" method="post">
            <fieldset>
                <legend>Login Now!</legend>
                UserID：<input type="text" name="userid" /><br />
                Password：<input type="text" name="password" /><br />
            </fieldset>
            <input type="submit" />
        </form>
    </body>
<?php
}
function do_html_header($title)
{
?>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title><?php echo $title ?></title>
    </head>

    <body>
        <h1><?php echo $title ?></h1>
    <?php
}
function do_html_footer()
{
    ?>
        <div id="footer">Copyright Hollow Man &copy; 2020</div>
    </body>

    </html>
<?php
}
function do_html_url($url, $text)
{
?>
    <p><a href="<?php echo $url ?>"><?php echo $text ?></a></p>
<?php
}
function do_log_info($content)
{
    if (isset($_SESSION['valid_user'])) {
        echo "<p>You are logged in as: " . $_SESSION['valid_user'] . "</p>";
        echo "<p><a href='logout.php'>Log Out</a></p>";
        if ($content == "members") {
            echo "Members Only Page........<br/>";
            include "welcome.php";
        }
    } else {
        if ($content == "members") {
            echo "<p>You are not logged in, couldn't visit this page.</p>";
        } else if (isset($_POST['userid'])) {
            echo "<p>Could not log you in.</p>";
        } else {
            echo "<p>You are not logged in.</p>";
        }
        display_login_form();
    }
}
