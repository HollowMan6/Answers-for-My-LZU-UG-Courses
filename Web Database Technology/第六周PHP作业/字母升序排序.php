<?php
    $letter = array('p','o','i','u','y','t','r','e','w','q');
    sort($letter);
    while($i<count($letter)){
        echo $letter[$i]."</br>";
        $i++;
    }
?>