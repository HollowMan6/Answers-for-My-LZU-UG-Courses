<?php
function display_login_form()
{
?>

    <body>
        <form align="center" action="usercheck.php" method="post">
            <fieldset>
                <legend>Login Now!</legend>
                UserID：<input type="text" name="userid" /><br />
                Password：<input type="text" name="password" /><br />
            </fieldset>
            <br />
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
        <h1 align="center"><?php echo $title ?></h1>
    <?php
}
function do_html_footer()
{
    ?>
        <div align='center' id="footer">Copyright Hollow Man &copy; 2020</div>
    </body>

    </html>
    <?php
}
function display_goods()
{
    if (isset($_SESSION['valid_user'])) {
        $conn = connect_database();
        $sql = "select * from goods";
        $result = $conn->query($sql);
        $conn->close();

    ?>
        <h2 align="center">Goods</h2>
        <table border='1' width="100%">
            <tr>
                <td>Name</td>
                <td>Price</td>
                <td>Action</td>
            </tr>
            <?php
            while ($row = $result->fetch_assoc()) {
            ?>
                <tr>
                    <td><?php echo $row['name'] ?></td>
                    <td><?php echo $row['price'] ?></td>
                    <td><a href="<?php echo "cart.php?name=" . $row['name'] . "&price=" . $row['price'] ?>">Buy</a></td>
                </tr>
            <?php
            }
            ?>
        </table>
    <?php
    }
}
function do_html_url($url, $text)
{
    ?>
    <p align='center'><a href="<?php echo $url ?>"><?php echo $text ?></a></p>
    <?php
}
function do_log_info($content)
{
    if (isset($_SESSION['valid_user'])) {
        echo "<p align='center'>You are logged in as: " . $_SESSION['valid_user'] . "</p>";
        echo "<p align='center'><a href='logout.php'>Log Out</a></p>";
        if ($content == "cart") {
            if (!empty($_GET)) {
                if (!empty($_GET['id'])) {
                    array_splice($_SESSION["cart"], $_GET['id'] - 1, 1);
                } else if (!empty($_GET['name']) && !empty($_GET['price'])) {
                    $name = $_GET['name'];
                    $price = $_GET['price'];
                    $goods = array("name" => $name, "price" => $price);
                    if (empty($_SESSION["cart"])) {
                        $_SESSION["cart"] = array($goods);
                    } else {
                        array_push($_SESSION["cart"], $goods);
                    }
                }
            }
            display_cart_goods();
        } else if ($content == "shopping") {
            display_goods();
        }
    } else {
        if ($content == "cart") {
            echo "<p align='center'>You are not logged in, couldn't visit this page.</p>";
        } else if (isset($_POST['userid'])) {
            echo "<p align='center'>Could not log you in.</p>";
        } else {
            echo "<p align='center'>You are not logged in.</p>";
        }
        display_login_form();
    }
}
function display_cart_goods()
{
    if (empty($_SESSION["cart"])) {
        echo "<p align='center'>The shopping cart is empty. Please buy something.</p>";
    } else {
    ?>
        <table border='1' width="100%">
            <tr>
                <td>Name</td>
                <td>Price</td>
                <td>Action</td>
            </tr>
            <?php
            $countid = 1;
            foreach ($_SESSION["cart"] as $goods) {
            ?>
                <tr>
                    <td><?php echo $goods['name'] ?></td>
                    <td><?php echo $goods['price'] ?></td>
                    <td><a href="<?php echo "cart.php?id=" . $countid ?>">Delete</a></td>
                </tr>
            <?php
                $countid += 1;
            }
            ?>
        </table>
<?php
    }
}
