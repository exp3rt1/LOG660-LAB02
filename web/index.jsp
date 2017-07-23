<%-- 
    Document   : index
    Created on : 2016-10-17, 17:14:49
    Author     : Olivier Proulx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!-- JQuery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        
        <!-- Bootstrap -->
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        
        <!-- Our -->
        <script src="js/index.js"></script>
    </head>
    <body>
        <div class="container col-md-offset-3 col-md-6">
            <div id="errorBox" class="alert alert-danger" style="display:none">
                <strong>Error!</strong> Login failed!
            </div>
            <br />
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h1>Client login</h1>
                </div>
                <div class="panel-body">
                    <form method="POST" class="form-horizontal" action="./login">
                        <div id="courrielContainer" class="form-group">
                            <div class="col-md-12">
                                <input id="clientCourriel" type="email" class="form-control" name="courriel" placeholder="Courriel" required/>
                            </div>
                        </div>
                        <div id="motPasseContainer" class="form-group">
                            <div class="col-md-12" >
                                <input id="clientMotPasse" type="password" class="form-control" name="motPasse" placeholder="Mot de passe" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <button type="submit" id="boutonLogin" class="btn btn-primary col-md-12" style="width:100%">Login</button>
                            </div>
                        </div>
                    </form>    
                </div>
            </div>
        </div>
    </body>
</html>
