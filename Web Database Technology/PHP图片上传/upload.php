<style type="text/css">
    #user_logo {
        width: 100px;
    }
</style>

<?php

if (isset($_FILES['upfile'])) {
    if ($_FILES['upfile']['error'] != 0) {
        echo "</br>Upload Failed: " . $_FILES['upfile']['error'] . "<br/>";
        exit;
    }
    $type = strrchr($_FILES['upfile']['name'], ".");
    $upload_dir = './upload/';
    $filename = time() . rand(100, 900) . $type;
    $newfile = $upload_dir . $filename;
    if (is_uploaded_file($_FILES['upfile']['tmp_name'])) {
        $finfo = finfo_open(FILEINFO_MIME_TYPE);
        $mime = finfo_file($finfo, $_FILES['upfile']['tmp_name']);
        if (strchr($mime, "/", true) == "image") {
            $is = move_uploaded_file($_FILES['upfile']['tmp_name'], $newfile);
            if (!$is) {
                echo "<br>Unable to move to the specific destination!<br/>";
                exit;
            } else {
                echo "<br>Upload Succeed!<br/>";
                echo "<img id='user_logo' src=" . $newfile . ">";
            }
        } else {
            echo "<br/>The upload file is not in picture format!<br/>";
            exit;
        }
    }
}

?>

<form method="post" action="upload.php" enctype="multipart/form-data">
    <input type="hidden" name="MAX_FILE_SIZE" value="41943040">
    <input type="file" name="upfile">
    <input type="submit" value="Upload File">
</form>