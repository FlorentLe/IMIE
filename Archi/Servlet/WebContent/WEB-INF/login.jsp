<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK rel=stylesheet type="text/css" href="css/theme.css">
<title>Connexion</title>
</head>
<body>
	<div class="container">
		<div class="content">
			<div>
				<form method="post">
				<div class="header largerfont rounded ">Authentification</div>
					<div>
						<label for="login" class="label">Identifiant : </label>
						<input type="text" id="login" name="login" placeholder="Login" class="inputText" required/>
					</div>
					<div>
						<label for="password" class="label">Mot de passe : </label>
						<input type="password" id="password" name="password" placeholder="Mot de passe" class="inputText" required/>
					</div>
					<div>
					 	<input type="submit" value="Se Connecter" name="submitLogin" class="fullWidth largerfont lightgreen rounded submit"/>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>