package br.com.cuidebem;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class Emailsvc {
	
	private static final String subject_email = "Confirmar cadastro cuidebem";
	private static final String desbloqued_subject_email = "Desbloquear usuário cuidebem";
	private static String content_email = "<!DOCTYPE html>"+
"<html>"+
"<head>"+
"<meta charset='UTF-8'>"+
"<style>"+
".ui-button {"+
"display: inline-block;"+
 "position: relative;"+
  "padding: 0;"+
   "margin-right: .1em;"+
    "text-decoration: none!important;"+
    "cursor: pointer;"+
    "text-align: center;"+
    "zoom: 1;"+
    "overflow: visible;"+
"} "+
".btn {"+
 "display: inline-block;"+
  "padding: 6px 12px;"+
  "margin-bottom: 0;"+
  "font-size: 14px;"+
    "font-weight: 400;"+
    "line-height: 1.42857143;"+
    "text-align: center;"+
    "white-space: nowrap;"+
    "vertical-align: middle;"+
    "-ms-touch-action: manipulation;"+
    "touch-action: manipulation;"+
    "cursor: pointer;"+
    "-webkit-user-select: none;"+
    "-moz-user-select: none;"+
    "-ms-user-select: none;"+
    "user-select: none;"+
    "background-image: none;"+
    "border: 1px solid transparent;"+
    "border-radius: 4px;"+
"} "+
".btn-success {"+
    "background-image: -webkit-linear-gradient(top,#5cb85c 0,#419641 100%);"+
    "background-image: -o-linear-gradient(top,#5cb85c 0,#419641 100%);"+
    "background-image: -webkit-gradient(linear,left top,left bottom,from(#5cb85c),to(#419641));"+
    "background-image: linear-gradient(to bottom,#5cb85c 0,#419641 100%);"+
    "filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff5cb85c', endColorstr='#ff419641', GradientType=0);"+
    "filter: progid:DXImageTransform.Microsoft.gradient(enabled=false);"+
    "background-repeat: repeat-x;"+
    "border-color: #3e8f3e;"+
	"color: #fff;"+
    "background-color: #5cb85c;"+
"} "+
".ui-button-text {"+
    "display: block;"+
    "line-height: normal;"+
	 "padding: .3em 1em;"+
"} "+
"</style>"+
"</head>"+
"<body>"+
"<blockquote>"+
"#{bodyemail}"+
"</body>"+
"</html>";

private static String bodyActivation = "<div style='text-align:center'>"+
		"<h2>Termo de Aceite</h2>"+
		"</div>"+
		"<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.</p>"+
		"<footer style='text-align:center'>"+
		"<a href='#{link}' class='btn btn-success'>Aceito os termos do acordo</a>"+
		"</footer>"+
		"<div style='text-align:center'>"+
		"<img src='http://www.cuidebemapp.com/resources/img/logo.png'/>"+
		"</div>"+
		"</blockquote>";	
	
	private static String bodyDesblocked= 
"<blockquote>"+
"<div style='text-align:center'>"+
"<h2>Desbloqueio de Usuário</h2>"+
"</div>"+
"<p>Seu usuário está bloqueado. Para desbloqueá-lo, favor clicar no botão abaixo:</p>"+
"<footer style='text-align:center'>"+
"<a href='#{link}' class='btn btn-success'>Desbloquear Usuário</a>"+
"</footer>"+
"<div style='text-align:center'>"+
"<img src='http://www.cuidebemapp.com/resources/img/logo.png'/>"+
"</div>"+
"</blockquote>";
	
	
	
	private static final String replace = "#{link}";
	private static final String replace_user = "#{user}";
	private static final String link = "http://cuidebemapp.com/rest/confirma/#{user}";
	private static final String desbloqueio_link = "http://cuidebemapp.com/rest/desbloqueio/#{user}";
	private static String content_type = "text/html";
	private static final String bodyEmail = "#{bodyemail}";
	
	@Inject 
	private SendEmail sendEmail;
	
	@Asynchronous
	public void confirmarEmail(String email){
		String confirma = "";
		confirma = content_email.replace(bodyEmail, bodyActivation);
		confirma = confirma.replace(replace, link);
		confirma = confirma.replace(replace_user, email);		
		sendEmail.send(email, subject_email, confirma, content_type);
	}
	
	@Asynchronous
	public void desbloquearEmail(String email){
		String desbloquea = "";
		desbloquea = content_email.replace(bodyEmail, bodyDesblocked);
		desbloquea = desbloquea.replace(replace, desbloqueio_link);
		desbloquea = desbloquea.replace(replace_user, email);
		
		sendEmail.send(email, desbloqued_subject_email, desbloquea, content_type);
	}
	
	

}
