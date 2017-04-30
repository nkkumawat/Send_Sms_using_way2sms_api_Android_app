<?php

include('curl.php');
error_reporting(0);
set_time_limit(0);
 $i=0;
$i++;
echo $i;
$ser="http://site24.way2sms.com/";
$ckfile = tempnam ("/tmp", "CURLCOOKIE");

$login=$ser."Login1.action";
$uid=urldecode($_GET['id']);
$pwd=urldecode($_GET['pass']);
$to=urldecode($_GET['mob']);
$msg=urldecode($_GET['mess']);

if($uid && $pwd)
{
$ibal="0";
$sbal="0";
$lhtml="0";
$shtml="0";
$khtml="0";
$qhtml="0";
$fhtml="0";
$te="0";

$loginpost="gval=&username=".$uid."&password=".$pwd."&Login=Login";

$ch = curl_init();
$lhtml=post($login,$loginpost,$ch,$ckfile);


if(stristr($lhtml,'Location: '.$ser.'vem.action') || stristr($lhtml,'Location: '.$ser.'MainView.action') || stristr($lhtml,'Location: '.$ser.'ebrdg.action'))
{
preg_match("/~(.*?);/i",$lhtml,$id);
$id=$id['1'];
}

bal:

$msg=urlencode($msg);
$main=$ser."smstoss.action";
$ref=$ser."sendSMS?Token=".$id;
$conf=$ser."smscofirm.action?SentMessage=".$msg."&Token=".$id."&status=0";

$post="ssaction=ss&Token=".$id."&mobile=".$to."&message=".$msg."&Send=Send Sms&msgLen=".strlen($msg);
$mhtml=post($main,$post,$ch,$ckfile,$proxy,$ref);
if(stristr($mhtml,'smscofirm.action?SentMessage='))

echo '<div class="w3-container w3-section w3-green">
<span onclick="this.parentElement.style.display="none"" class="w3-closebtn">&times;</span>
  <p>SMS Sended Sucessfully..!!</p>
</div>'.$to.'.';

curl_close($ch);

end:

echo "</div>";

}

header( "refresh:10; url=index.php" );


?>
