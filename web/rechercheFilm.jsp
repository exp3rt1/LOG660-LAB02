<%-- 
    Document   : rechercheFilm
    Created on : 2016-10-21, 08:26:31
    Author     : Oli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Recherche de films</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!-- JQuery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        
        <!-- Bootstrap -->
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        
        <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css" rel="stylesheet">
        
        <!-- TagsInput -->
        <link rel="stylesheet" type="text/css" href="css/bootstrap-tagsinput.css">
        <script src="js/bootstrap-tagsinput.js"></script>
           
        <!-- DataTables -->
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/r/bs-3.3.5/jq-2.1.4,dt-1.10.8/datatables.min.css" />
        <script src="https://cdn.datatables.net/r/bs-3.3.5/jqc-1.11.3,dt-1.10.8/datatables.min.js"></script>
        
        <!-- Our shit -->
        <script src="js/rechercheFilm.js"></script>
        <link rel="stylesheet" type="text/css" href="css/rechercheFilm.css" />
        
    </head>
    <body>
        <div class="container">
            
            <div style="float: right; margin-top: 10px;">
                <form method="POST" class="form-horizontal" action="./logout">
                    <button type="submit" class="btn btn-primary btn-sm">
                        <span class="glyphicon glyphicon-log-out"></span> Déconnexion
                    </button>
                </form>
            </div>
            <div id="pageTitle">Recherche de films</div>
            
            
            <div id="spinner" class="spinner">
                <div align="center" class="cssload-fond">
                <div class="cssload-container-general">
                <div class="cssload-internal"><div class="cssload-ballcolor cssload-ball_1"></div></div>
                <div class="cssload-internal"><div class="cssload-ballcolor cssload-ball_2"></div></div>
                <div class="cssload-internal"><div class="cssload-ballcolor cssload-ball_3"></div></div>
                <div class="cssload-internal"><div class="cssload-ballcolor cssload-ball_4"></div></div>
                </div>
                </div>
            </div>
            
            
            <!--C'est la barre de recherche avec les options avancées.-->
            <div class="panel-group accordion-group accordion-caret" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="input-group col-md-12">
                            <input id="titre" type="text" class="form-control input-lg" 
                                   placeholder="Titre" style="height: 46px; font-size: 20px; padding-left: 15px;"/>
                            <span class="input-group-btn">
                                <button id="rechercheBouton" class="btn btn-info btn-lg" type="button">
                                    <i class="glyphicon glyphicon-search"></i>
                                </button>
                            </span>
                        </div> 
                        <div class="text-right">
                            <a href="#rechercheAvancee" data-toggle="collapse" data-target="#rechercheAvancee" role="button" class="btn btn-link btn-large">
                                <span class="glyphicon glyphicon-cog"></span> Recherche Avancée
                            </a>
                        </div>
                    </div>
                    <div id="rechercheAvancee" class="panel-collapse collapse">
                        
                        <table>
                            <tr>
                                <td class="labelTD"><label>Titre(s) : </label></td>
                                <td class="inputTD"><input id="titres" type="text" value="" data-role="tagsinput" /></td>
                                <td class="labelTD"><label>Pays de production : </label></td>
                                <td class="inputTD"><select id="pays" class=""  multiple data-actions-box="true"></select></td>
                            </tr>
                            <tr>
                                <td class="labelTD"><label>Réalisateur(s) : </label></td>
                                <td class="inputTD"><input id="realisateurs" type="text" value="" data-role="tagsinput" /></td>
                                <td class="labelTD"><label>Langue(s) originale(s) : </label></td>
                                <td class="inputTD"><select id="languesOriginales" class=""  multiple data-actions-box="true"></select></td>
                            
                            </tr>
                            <tr>
                                <td class="labelTD"><label>Acteur(s) : </label></td>
                                <td class="inputTD"><input id="acteurs" type="text" value="" data-role="tagsinput" /></td>
                                <td class="labelTD"><label>Genre(s) : </label></td>
                                <td class="inputTD"><select id="genres" class=""  multiple data-actions-box="true" ></select></td>
                            </tr>                      

                            <tr>
                                <td class="labelTD"><label>Année(s) de sortie : </label></td>
                                <td class="inputTD">
                                    <select id="anneesSortie" class="" multiple data-actions-box="true"></select>   
                                </td>
                                <td colspan="2">
                                    <div id="addDateInterval" onclick="addDateInterval();">
                                       <span class="glyphicon glyphicon-plus"></span> Ajouter une intervalle d'années</div>
                                </td>
                            </tr> 
                            <tr>
                                <td colspan="4">
                                    <div id="dateDiv" style="padding-top: 15px;"></div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4" id="rechercheAvanceeBoutonTD">
                                    <button id="rechercheAvanceeBouton" class="btn btn-info" type="button">
                                        <i class="glyphicon glyphicon-search" style="margin-right: 5px;"></i>
                                        Rechercher
                                    </button>
                                </td>
                            </tr>
                        </table>
                       
                        
                        
                    </div>
                </div>
            </div>
            <!-- Bar de recherche -->
            
            <table id="listeFilms" class="table table-hover" cellspacing="0" width="100%">
                <thead class="thead-default">
                    <tr>
                        <th>Titre</th>
                        <th>Année de sortie</th>
                    </tr>
                </thead>
                <tbody id="filmTableBody">
                    
                </tbody>
            </table>
            
        </div>
        
        <!-- Select -->
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/css/bootstrap-select.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/bootstrap-select.min.js"></script>
        
        
    </body>
</html>

