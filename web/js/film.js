/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var _filmID = "";

 $.urlParam = function(name){
        var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
        if (results===null){
           return null;
        }
        else{
           return results[1] || 0;
        }
    };

$(document).ready(function(){
    _filmID = $.urlParam('id');
    showSpinner();
    getFilmInfo();
});


function showSpinner(){
    $('#spinner').show();
}

function hideSpinner(){
    $('#spinner').hide();
}

function getFilmInfo(){
    
    var id = _filmID;
    
    if(id !== undefined && id !== null && id !== ""){     
        $.ajax({
            type: "GET",
            url: "/LOG660-LAB02/webresources/film/getFilmInfo/"+id,
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            contentType: "application/json",
            dataType: "json",
            data: id,
            success: function (data) {
                hideSpinner();
                setFilmInfo(data);
            },
            error: function (xhr, status, error) {
                // Mettre les champs en erreur
                hideSpinner();
                alert("ERROR! See console...");
                console.log(xhr.responseText);
                console.log(status);
                console.log(error);
            }
        });
    }
    else{
        hideSpinner();
    }
}

function setFilmInfo(film){
    var title = film.title;
    var duration = film.duration;
    var year = film.year;
    var language = film.language;
    var synopsis = film.synopsis;
    var directorId = film.director.directorId;
    var directorName = film.director.directorName;
    
    $('#title').html(title);
    $('#length').html(duration);
    $('#year').html(year);
    $('#language').html(language);
    $('#synopsis').html(synopsis);
    
    $('#director').html(directorName);
    $('#director').attr("data-directorid",directorId);
    
    var actorsDiv = document.getElementById("actors");
    for(var i = 0; i < film.actors.length; i++){
        var actor = film.actors[i];
        var actorId = actor.actorId;
        var actorName = actor.actorName;
        var characterName = actor.characterName;
        
        var newActor = document.createElement("span");
        newActor.innerHTML = actorName + " (" + characterName + ")";
        newActor.setAttribute("data-actorid",actorId);
        
        actorsDiv.appendChild(newActor);
        
        if(i < film.actors.length - 1){
            actorsDiv.innerHTML += ", ";
        }
    }
    
    var genresDiv = document.getElementById("genres");
    for(var i = 0; i < film.genres.length; i++){
        var genre = film.genres[i];
        genresDiv.innerHTML += genre;
        if(i < film.genres.length - 1){
            genresDiv.innerHTML += ", ";
        }
    }
    
    var screenwritersDiv = document.getElementById("screenwriters");
    for(var i = 0; i < film.screenwriters.length; i++){
        var screenwriter = film.screenwriters[i];
        screenwritersDiv.innerHTML += screenwriter;
        if(i < film.screenwriters.length - 1){
            screenwritersDiv.innerHTML += ", ";
        }
    }
    
    var countriesDiv = document.getElementById("countries");
    for(var i = 0; i < film.countries.length; i++){
        var country = film.countries[i];
        countriesDiv.innerHTML += country;
        if(i < film.countries.length - 1){
            countriesDiv.innerHTML += ", ";
        }
    }
    
    $('#filmContent').show();
}

