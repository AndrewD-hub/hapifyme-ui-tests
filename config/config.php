<?php
ob_start();

if (session_status() === PHP_SESSION_NONE) {
    session_start();
}

date_default_timezone_set("Europe/Bucharest");

$con = mysqli_connect("mysql", "root", "root", "hapify_social");

if (mysqli_connect_errno()) {
    echo "Failed to connect: " . mysqli_connect_errno();
}
?>